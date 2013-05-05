package br.feevale.droidhospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.db.DadosLogin;
import br.feevale.droidhospital.db.Interpretador;

public class MainActivity extends Activity {

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//n�o haver� menu na tela de login
		
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void login(View v) {

		String usuario = loginEditText.getText().toString();
		String senha = passwordEditText.getText().toString();

		if (checkLogin(usuario, senha)) {

			Intent intent = new Intent(getApplicationContext(),
					ListaLeitosActivity.class);
			startActivity(intent);

		} else {

			Toast.makeText(getApplicationContext(),
					"Login ou senha inv�lida", Toast.LENGTH_LONG).show();
			// passwordEditText.setText( "" );
		}
	}

	private boolean checkLogin(String usuario, String senha) {
		if (true) {
			return true;
		}
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
			e.printStackTrace();
		}

		if (tipoUsuario == null) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.not_connected), Toast.LENGTH_LONG)
					.show();
			return false;
		}

		if (tipoUsuario.equals("N")) {
			return false;
		}
		
		return true;
	}
}