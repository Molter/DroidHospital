package br.feevale.droidhospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.PacientesAdapter;
import br.feevale.droidhospital.db.DadosId;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.Paciente;

public class ListaPacientesActivity extends Activity implements OnItemClickListener {
		ArrayList<Paciente> pacientes;

		public static final String ID_VALUE = "id";
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.pacientes_layout);
			
			ListView pacientesListView = (ListView) findViewById(R.id.pacientes_list_view);
			
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			Intent intent = getIntent();
			long id = intent.getLongExtra(ListaQuartosActivity.ID_VALUE, 0);
			
			if(id == 0) {
				Toast.makeText(getApplicationContext(), "Quarto not found", Toast.LENGTH_LONG).show();
				finish();
			}
			
			setUpDadosSocket(id);
			
			PacientesAdapter pacientesAdapter= new PacientesAdapter(getApplicationContext(), pacientes);
			
			pacientesListView.setAdapter(pacientesAdapter);
			
			pacientesListView.setOnItemClickListener(this);
			
		}
		
		private void setUpDadosSocket(long id) {
			pacientes = new ArrayList<Paciente>();
			try {

				Interpretador interpretador = new DadosId(String.valueOf(id));

				interpretador.setCdTransacao(Interpretador.LISTA_PACIENTES);

				EnviaTransacao enviador = new EnviaTransacao(interpretador);

				try {

					enviador.envia();

					pacientes = (ArrayList<Paciente>) enviador.recebe();

				} finally {
					enviador.fechaSocket();
				}

			} catch (Exception e) {
				Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG, e.getMessage());
				Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_LONG).show();
				e.printStackTrace();
				finish();
			}

			
			this.setTitle(getString(R.string.room) + " " + String.valueOf(id));
		}
		
		

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long id) {
			Intent intent = new Intent(getApplicationContext(), AnamneseActivity.class);
			intent.putExtra(ID_VALUE, id);
			startActivity(intent);
		}
}
