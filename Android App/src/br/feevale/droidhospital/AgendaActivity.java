package br.feevale.droidhospital;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.AgendaAplicacoesAdapter;
import br.feevale.droidhospital.db.Aplicacao;
import br.feevale.droidhospital.db.AplicacaoEfetuada;
import br.feevale.droidhospital.db.ConfirmaTransacao;
import br.feevale.droidhospital.db.Interpretador;

public class AgendaActivity extends Activity implements OnItemClickListener {

	private ArrayList<Aplicacao> aplicacoes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agenda);
		
		ListView aplicacaoListView = (ListView) findViewById(R.id.agenda_list_view);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		
		setUpDadosSocket();
		
		AgendaAplicacoesAdapter pacienteAplicacoesAdapter= new AgendaAplicacoesAdapter(getApplicationContext(), aplicacoes);
		
		aplicacaoListView.setAdapter(pacienteAplicacoesAdapter);
		
		aplicacaoListView.setOnItemClickListener(this);
		
		
		
	
			
	}
	
	private void setUpDadosSocket() {
 		try {
			Interpretador interpretador = new Interpretador();

			interpretador.setCdTransacao(Interpretador.AGENDA_APLICACOES);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {

				enviador.envia();

				aplicacoes = (ArrayList<Aplicacao>) enviador.recebe();

			} finally {
				enviador.fechaSocket();
			}

		} catch (Exception e) {
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG, e.getMessage());
			Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_LONG).show();
			e.printStackTrace();
			finish();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parentAdapter, View layout, int itemPosition, long idItem) {
		//Toast.makeText(getApplicationContext(), "item clicked", Toast.LENGTH_LONG).show();
		
		if(enviaAplicacao(idItem)) {
			TextView dataAplicacaoTextView = (TextView)layout.findViewById(R.id.agenda_data_textView);
			TextView medicamentoTextView   = (TextView)layout.findViewById(R.id.agenda_medicamento_textView);
			TextView horaAplicacaoTextView = (TextView)layout.findViewById(R.id.agenda_horario_textView);
			TextView nomePacienteTextView = (TextView)layout.findViewById(R.id.agenda_paciente_textView);
			TextView quartoTextView = (TextView)layout.findViewById(R.id.agenda_quarto_e_leito);
			
			dataAplicacaoTextView.setPaintFlags(dataAplicacaoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			medicamentoTextView.setPaintFlags(medicamentoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			horaAplicacaoTextView.setPaintFlags(horaAplicacaoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			nomePacienteTextView.setPaintFlags(nomePacienteTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			quartoTextView.setPaintFlags(quartoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			
			
			ImageView image = (ImageView)layout.findViewById(R.id.agenda_aplicacao_injection);
			image.setVisibility(View.GONE);
			
			Toast.makeText(getApplicationContext(), getString(R.string.application_mande), Toast.LENGTH_LONG).show();
			
			aplicacoes.get(itemPosition).setAplicada(true);
			
		}else {
			Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_LONG).show();
		}
	}
	
	
	private boolean enviaAplicacao(long id) {
		ConfirmaTransacao retorno = new ConfirmaTransacao();
		
 		try {
 			AplicacaoEfetuada interpretador = new AplicacaoEfetuada(String.valueOf(id));

			interpretador.setCdTransacao(Interpretador.ENVIA_APLICACAO);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {

				enviador.envia();
				
				retorno = (ConfirmaTransacao) enviador.recebe();
				

			} finally {
				enviador.fechaSocket();
			}

		} catch (Exception e) {
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG, e.getMessage());
			Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_LONG).show();
			e.printStackTrace();
			finish();
		}
 		if(retorno.getResult() == ConfirmaTransacao.RESULT_OK) {
 			return true;
 		}else {
 			return false;
 		}
	}
}