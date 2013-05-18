package br.feevale.droidhospital;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.db.DadosLogin;
import br.feevale.droidhospital.db.DadosUsuario;
import br.feevale.droidhospital.db.Interpretador;

public class MainActivity extends Activity {

	public static final String USER_TYPE_PREFERENCE = "br.feevale.droidhospital.usertype";
	public static final String USER_ID_PREFERENCE = "br.feevale.droidhospital.userid";

	public static String DROID_HOSPITAL_LOG_TAG = "br.feevale.droidhospital";

	private EditText loginEditText;
	private EditText passwordEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		loginEditText = (EditText) findViewById(R.id.login_edit_text);
		passwordEditText = (EditText) findViewById(R.id.password_edit_text);
	}

	public void login(View v) {

		String usuario = loginEditText.getText().toString();
		String senha = passwordEditText.getText().toString();

		if (setUpDadosSocket(usuario, senha)) {

			Intent intent = new Intent(getApplicationContext(),
					ListaQuartosActivity.class);
			startActivity(intent);

		} else {

			Toast.makeText(getApplicationContext(),
					getString(R.string.bad_login), Toast.LENGTH_LONG).show();
			// passwordEditText.setText( "" );
		}
	}

	private boolean setUpDadosSocket(String usuario, String senha) {
		String[] dados = new String[2];

		dados[0] = usuario;
		dados[1] = senha;

		DadosUsuario dadosUsuario = new DadosUsuario();
		;

		try {

			DadosLogin interpretador = new DadosLogin(dados);

			interpretador.setCdTransacao(Interpretador.VALIDA_LOGIN);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {
				enviador.envia();

				dadosUsuario = (DadosUsuario) enviador.recebe();

			} finally {
				enviador.fechaSocket();
			}

		} catch (Exception e) {
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG,
					getString(R.string.not_connected));
			Toast.makeText(getApplicationContext(),
					getString(R.string.not_connected), Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
			// finish();
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG, getString(R.string.not_connected));
			Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_LONG).show();
			e.printStackTrace();
			//finish();
		}

		if (dadosUsuario.getIdUsuario() == null) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.not_connected), Toast.LENGTH_LONG)
					.show();
			return false;
		}

		if ((dadosUsuario.getTipoUsuario().equals(DadosUsuario.TIPO_MEDICO))
				|| (dadosUsuario.getTipoUsuario()
						.equals(DadosUsuario.TIPO_ENFERMEIRO))) {

			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getApplicationContext());
			Editor editor = prefs.edit();
			editor.putString(USER_TYPE_PREFERENCE,
					dadosUsuario.getTipoUsuario());
			editor.putInt(USER_ID_PREFERENCE, dadosUsuario.getIdUsuario());
			editor.commit();

			return true;
		}

		return false;
	}
}