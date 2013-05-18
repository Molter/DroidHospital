package br.feevale.droidhospital;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
<<<<<<< HEAD
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
=======
import android.util.Log;
>>>>>>> Web Services Sync Tasks
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.AnamneseAdapter;
import br.feevale.droidhospital.db.DadosId;
<<<<<<< HEAD
import br.feevale.droidhospital.db.DadosUsuario;
=======
>>>>>>> Web Services Sync Tasks
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.PacienteDescription;

public class AnamneseActivity extends Activity {
	public static String ID_PACIENTE = "id";
<<<<<<< HEAD
	public static String NOME_PACIENTE = "nome";
	public static String LEITO_PACIENTE = "leito";
	
	
	private long idPaciente;
	private int idAtendimento;
	private PacienteDescription dadosPaciente;
	
	AnamneseAdapter adapter;
	ExpandableListView expandableList;
<<<<<<< HEAD
=======
=======
	long idPaciente;
	PacienteDescription dadosPaciente;
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anamnese);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Intent intent = getIntent();
		idPaciente = intent.getLongExtra(ListaQuartosActivity.ID_VALUE, 0);
		
		if( idPaciente == 0) {
			Toast.makeText(getApplicationContext(), "Paciente not found! Plese Try Again", Toast.LENGTH_LONG).show();
			finish();
		}
<<<<<<< HEAD
		idAtendimento = intent.getIntExtra(ListaPacientesActivity.ID_ATENDIMENTO, 0);
		
		setUpDadosSocket();
		
		expandableList = (ExpandableListView) findViewById(R.id.anamnese_expandablelistview);
		
		adapter = new AnamneseAdapter(getApplicationContext(), dadosPaciente );
		expandableList.setAdapter(adapter);
	}
	@Override
	protected void onResume() {
		super.onResume();
		setUpDadosSocket();
		
		adapter = new AnamneseAdapter(getApplicationContext(), dadosPaciente );
		expandableList.setAdapter(adapter);
<<<<<<< HEAD
=======
=======
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
		
		setUpDadosSocket();
		
		ExpandableListView expandableList = (ExpandableListView)findViewById(R.id.anamnese_expandablelistview);
		
		AnamneseAdapter adapter = new AnamneseAdapter(getApplicationContext(), dadosPaciente);
		expandableList.setAdapter(adapter);
>>>>>>> Web Services Sync Tasks
	}
	
	public void setUpDadosSocket() {
		try {

			Interpretador interpretador = new DadosId(String.valueOf(idPaciente));

			interpretador.setCdTransacao(Interpretador.DADOS_PACIENTE);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {
<<<<<<< HEAD

				enviador.envia();
				
				dadosPaciente = (PacienteDescription) enviador.recebe();

			} finally {
				
				enviador.fechaSocket();
				
				TextView  pacientName = (TextView) findViewById(R.id.anamnese_pacient_name);
				
				pacientName.setText(dadosPaciente.getNome());
				setTitle(dadosPaciente.getQuarto() + " " + dadosPaciente.getLeito() + " - " + dadosPaciente.getNome());
			}

		} catch (Exception e) {
<<<<<<< HEAD
=======
			
=======

				enviador.envia();
				
				dadosPaciente = (PacienteDescription) enviador.recebe();
				

			} finally {
				enviador.fechaSocket();
				
				TextView  pacientName = (TextView) findViewById(R.id.anamnese_pacient_name);
				
				pacientName.setText(dadosPaciente.getNome());
				setTitle(dadosPaciente.getQuarto() + " " + dadosPaciente.getLeito() + " - " + dadosPaciente.getNome());
			}

		} catch (Exception e) {
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG, getString(R.string.not_connected));
			Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_LONG).show();
			finish();
			
			e.printStackTrace();
		}
		
	}

	public void novaPrescricao(View v) {
		Intent novaPrecricaoIntent = new Intent(getApplicationContext(), NovaPrescricaoActivity.class);
<<<<<<< HEAD
		
		novaPrecricaoIntent.putExtra(ID_PACIENTE, idPaciente);
		novaPrecricaoIntent.putExtra(NOME_PACIENTE, dadosPaciente.getNome());
		novaPrecricaoIntent.putExtra(LEITO_PACIENTE, dadosPaciente.getQuarto() + " " + dadosPaciente.getLeito());
		
		novaPrecricaoIntent.putExtra(ListaPacientesActivity.ID_ATENDIMENTO, idAtendimento);
		
		startActivity(novaPrecricaoIntent);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String userType = prefs.getString(MainActivity.USER_TYPE_PREFERENCE, DadosUsuario.FAIL);
		
		if (userType.equalsIgnoreCase(DadosUsuario.TIPO_ENFERMEIRO)) {
		    MenuItem menuItem = menu.getItem(R.id.action_agenda);
		    menuItem.setVisible(true);
		}
		
		return true;
	}
=======
		novaPrecricaoIntent.putExtra(ID_PACIENTE, idPaciente);
		
		startActivity(novaPrecricaoIntent);
	}
>>>>>>> Web Services Sync Tasks
}
