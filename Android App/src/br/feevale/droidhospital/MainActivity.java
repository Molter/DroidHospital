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
import br.feevale.droidhospital.db.Interpretador;

public class MainActivity extends Activity {

	public static final String USER_TYPE_PREFERENCE = "N";
	public static final String USER_TYPE_DOCTOR = "M";
	public static final String USER_TYPE_NURSE = "E";
	public static final String USER_TYPE_NONE = "N";

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

			Intent intent = new Intent(getApplicationContext(),ListaQuartosActivity.class);
			startActivity(intent);

		} else {

			Toast.makeText(getApplicationContext(),getString(R.string.bad_login), Toast.LENGTH_LONG).show();
			// passwordEditText.setText( "" );
		}
	}

	private boolean setUpDadosSocket(String usuario, String senha) {
		String[] dados = new String[2];

		dados[0] = usuario;
		dados[1] = senha;

		String tipoUsuario = null;

		try {

			DadosLogin interpretador = new DadosLogin(dados);

			interpretador.setCdTransacao(Interpretador.VALIDA_LOGIN);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {
				enviador.envia();

				tipoUsuario = (String) enviador.recebe();
				
			} finally {
				enviador.fechaSocket();
			}

		} catch (Exception e) {
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG, getString(R.string.not_connected));
			Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_LONG).show();
			e.printStackTrace();
			//finish();
		}

		if (tipoUsuario == null) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.not_connected), Toast.LENGTH_LONG)
					.show();
			return false;
		}

		if (tipoUsuario.equalsIgnoreCase("N")) {
			return false;
		}
		
		if(tipoUsuario.equalsIgnoreCase("M") || tipoUsuario.equalsIgnoreCase("E")) {
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			Editor editor = prefs.edit();
			editor.putString(USER_TYPE_PREFERENCE, tipoUsuario);
			editor.commit();
			
			return true;
		}
		
		return false;
	}
}