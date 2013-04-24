package br.feevale.droidhospital;

import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.db.DadosLogin;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.util.Util;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void login(View v){
		EditText loginEditText = (EditText) findViewById(R.id.login_edit_text);
		EditText passwordEditText = (EditText) findViewById(R.id.password_edit_text);
		
		if(validateLogin(loginEditText.getText().toString(), passwordEditText.getText().toString())) {
			//Toast.makeText(getApplicationContext(), "logado", Toast.LENGTH_LONG).show();
			Intent intent = new Intent(getApplicationContext(), ListaQuartosActivity.class);
			startActivity(intent);
			
		}else {
			Toast.makeText(getApplicationContext(), "Login ou Senha Inválido", Toast.LENGTH_LONG).show();
			passwordEditText.setText("");
		}
		
	}

	private boolean validateLogin(String login, String password) {
		
		String[] dados = new String[2];
		dados[0] = login;
		
		try {
			dados[1] = Util.md5(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		String tipoUsuario = checkLogin(dados);
		
		
		if(tipoUsuario == null){
			Toast.makeText(getApplicationContext(), "nao comunicou", Toast.LENGTH_LONG).show();
			return false;
		}
		
		if(tipoUsuario.equals("N")) {
			Toast.makeText(getApplicationContext(), "erro login", Toast.LENGTH_LONG).show();
			return false;
		}else {
			Toast.makeText(getApplicationContext(), "login bombou", Toast.LENGTH_LONG).show();
			return true;
		}
	}
	
	private String checkLogin(String[] dados) {
		
		try {

			DadosLogin interpretador = new DadosLogin(dados);
			
			interpretador.setCdTransacao( Interpretador.VALIDA_LOGIN );
			
			EnviaTransacao enviador = new EnviaTransacao( interpretador );
			
			try {
				
	    		enviador.envia();
	    		
	    		return (String) enviador.recebe();
	    		
	    	} finally {
	    		enviador.fechaSocket();
	    	}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
