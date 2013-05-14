package br.feevale.droidhospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.PacienteAplicacoesAdapter;
import br.feevale.droidhospital.db.Aplicacao;
import br.feevale.droidhospital.db.AplicacaoEfetuada;
import br.feevale.droidhospital.db.ConfirmaTransacao;
import br.feevale.droidhospital.db.DadosId;
import br.feevale.droidhospital.db.DadosUsuario;
import br.feevale.droidhospital.db.Interpretador;

public class PacienteAplicacoesActivity extends Activity implements OnItemClickListener {

	private ArrayList<Aplicacao> aplicacoes;
	
	public static final String ID_VALUE = "id";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paciente_aplicacoes);
		
		ListView aplicacaoListView = (ListView) findViewById(R.id.listaAplicacoes);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		Intent intent = getIntent();
		long id = intent.getLongExtra(ListaMedicamentosActivity.ID_VALUE, 1);
		
		if(id == 0) {
			Toast.makeText(getApplicationContext(), "Pacient not found", Toast.LENGTH_LONG).show();
			finish();
		}
		
		
		TextView leitoPaciente = (TextView)findViewById(R.id.descricao_leito_textView);
		leitoPaciente.setText(intent.getStringExtra(ListaPacientesActivity.NOME_PACIENTE));
		
		TextView nomePaciente = (TextView)findViewById(R.id.descricao_paciente_textView);
		nomePaciente.setText(intent.getStringExtra(ListaPacientesActivity.LEITO_PACIENTE));
		
		
		setUpDadosSocket(id);
		
		PacienteAplicacoesAdapter pacienteAplicacoesAdapter= new PacienteAplicacoesAdapter(getApplicationContext(), aplicacoes);
		
		aplicacaoListView.setAdapter(pacienteAplicacoesAdapter);
		
		//aplicacaoListView.setOnItemClickListener(this);
			
	}
	
	@SuppressWarnings("unchecked")
	private void setUpDadosSocket(long id) {
 		try {
			Interpretador interpretador = new DadosId(String.valueOf(id));

			interpretador.setCdTransacao(Interpretador.LISTA_APLICACOES);

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
	public void onItemClick(AdapterView<?> parentAdapter, View viewClicked, int itemPosition, long idItem) {
		//Toast.makeText(getApplicationContext(), "item clicked", Toast.LENGTH_LONG).show();
		
		if(enviaAplicacao(idItem)) {
			TextView medicineDescription = (TextView)viewClicked.findViewById(R.id.descricao_medicamento_textView);
			medicineDescription.setPaintFlags(medicineDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			medicineDescription.invalidate();
			
			ImageView image = (ImageView)viewClicked.findViewById(R.id.aplicacao_injection);
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
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String userType = prefs.getString(MainActivity.USER_TYPE_PREFERENCE, DadosUsuario.FAIL);
		
		
		if (userType.equalsIgnoreCase(DadosUsuario.TIPO_ENFERMEIRO)) {
			
		    MenuItem menuItem = menu.findItem(R.id.action_agenda);
		    menuItem.setVisible(true);
	
		}
		
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_agenda:
			Intent agenda = new Intent(getApplicationContext(), AgendaActivity.class);
			startActivity(agenda);
			break;
		}
		return true;
	}

}
