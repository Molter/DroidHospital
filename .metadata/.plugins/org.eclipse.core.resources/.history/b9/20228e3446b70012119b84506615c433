package br.feevale.droidhospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.AnamneseAdapter;
import br.feevale.droidhospital.db.DadosId;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.PacienteDescription;

public class AnamneseActivity extends Activity {
	public static String ID_PACIENTE = "id";
	long idPaciente;
	PacienteDescription dadosPaciente;
	
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
		
		setUpDadosSocket();
		
		ExpandableListView expandableList = (ExpandableListView)findViewById(R.id.anamnese_expandablelistview);
		
		AnamneseAdapter adapter = new AnamneseAdapter(getApplicationContext(), dadosPaciente);
		expandableList.setAdapter(adapter);
	}
	
	public void setUpDadosSocket() {
		try {

			Interpretador interpretador = new DadosId(String.valueOf(idPaciente));

			interpretador.setCdTransacao(Interpretador.DADOS_PACIENTE);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {

				enviador.envia();
				
				dadosPaciente = (PacienteDescription) enviador.recebe();
				

			} finally {
				enviador.fechaSocket();
				
				TextView  pacientName = (TextView) findViewById(R.id.anamnese_pacient_name);
				
				pacientName.setText(dadosPaciente.getNome());
				setTitle(dadosPaciente.getQuarto() + " " + dadosPaciente.getLeito() + " - " + dadosPaciente.getNome());
			}

		} catch (Exception e) {
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG, getString(R.string.not_connected));
			Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_LONG).show();
			finish();
			
			e.printStackTrace();
		}
		
	}

	public void novaPrescricao(View v) {
		Intent novaPrecricaoIntent = new Intent(getApplicationContext(), NovaPrescricaoActivity.class);
		novaPrecricaoIntent.putExtra(ID_PACIENTE, idPaciente);
		
		startActivity(novaPrecricaoIntent);
	}
}
