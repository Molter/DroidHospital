package br.feevale.droidhospital;

import java.util.ArrayList;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.PacientesAdapter;
import br.feevale.droidhospital.db.DadosId;
<<<<<<< HEAD
import br.feevale.droidhospital.db.DadosUsuario;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.Paciente;

public class ListaPacientesActivity extends Activity implements
		OnItemClickListener {
	ArrayList<Paciente> pacientes;

	public static final String ID_VALUE = "id";

<<<<<<< HEAD
	public static final String NOME_PACIENTE = "nome_paciente";

	public static final String LEITO_PACIENTE = "leito_paciente";

	public static final String ID_ATENDIMENTO = "id_atendimento";

	private String userType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pacientes_layout);

		ListView pacientesListView = (ListView) findViewById(R.id.pacientes_list_view);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		Intent intent = getIntent();
		long id = intent.getLongExtra(ListaQuartosActivity.ID_VALUE, 0);

		if (id == 0) {
			Toast.makeText(getApplicationContext(), "Quarto not found",
					Toast.LENGTH_LONG).show();
			finish();
		}
=======
public class ListaPacientesActivity extends Activity implements OnItemClickListener {
	
		private ArrayList<Paciente> pacientes;
=======
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.Paciente;

public class ListaPacientesActivity extends Activity implements OnItemClickListener {
		ArrayList<Paciente> pacientes;
>>>>>>> Web Services Sync Tasks

		public static final String ID_VALUE = "id";

		public static final String NOME_PACIENTE = "nome_paciente";

		public static final String LEITO_PACIENTE = "leito_paciente";

		public static final String ID_ATENDIMENTO = "id_atendimento";
		
		private String userType;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.pacientes_layout);
			
			ListView pacientesListView = (ListView) findViewById(R.id.pacientes_list_view);
			
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
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
			
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			String userType = prefs.getString(MainActivity.USER_TYPE_PREFERENCE, DadosUsuario.FAIL);
			
			this.userType = userType;
			
		}
		
<<<<<<< HEAD
		@SuppressWarnings("unchecked")
=======
>>>>>>> Web Services Sync Tasks
		private void setUpDadosSocket(long id) {
			pacientes = new ArrayList<Paciente>();
			try {
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc

		setUpDadosSocket(id);

		PacientesAdapter pacientesAdapter = new PacientesAdapter(
				getApplicationContext(), pacientes);

		pacientesListView.setAdapter(pacientesAdapter);

		pacientesListView.setOnItemClickListener(this);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String userType = prefs.getString(MainActivity.USER_TYPE_PREFERENCE,
				DadosUsuario.FAIL);

		this.userType = userType;

	}

	@SuppressWarnings("unchecked")
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
			Toast.makeText(getApplicationContext(),
					getString(R.string.not_connected), Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
			finish();
		}
<<<<<<< HEAD

		this.setTitle(getString(R.string.room) + " " + String.valueOf(id));
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long id) {

		Intent intent;
		if (userType.equalsIgnoreCase(DadosUsuario.TIPO_ENFERMEIRO)) {
			intent = new Intent(getApplicationContext(),
					PacienteAplicacoesActivity.class);

			intent.putExtra(NOME_PACIENTE, pacientes.get(position)
					.getNomePaciente());
			intent.putExtra(LEITO_PACIENTE, pacientes.get(position)
					.getQuartoPaciente()
					+ " "
					+ pacientes.get(position).getLeitoPaciente());
		} else {

			intent = new Intent(getApplicationContext(), AnamneseActivity.class);
			intent.putExtra(ID_ATENDIMENTO, pacientes.get(position)
					.getIdatendimento());
=======
		
		

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long id) {
			
			Intent intent;
			if (userType.equalsIgnoreCase(DadosUsuario.TIPO_ENFERMEIRO)) {
				intent = new Intent(getApplicationContext(), PacienteAplicacoesActivity.class);
				
				intent.putExtra(NOME_PACIENTE, pacientes.get(position).getNomePaciente());
				intent.putExtra(LEITO_PACIENTE, pacientes.get(position).getQuartoPaciente() + " " + pacientes.get(position).getLeitoPaciente());
			}else{
				
				intent = new Intent(getApplicationContext(), AnamneseActivity.class);
				intent.putExtra(ID_ATENDIMENTO, pacientes.get(position).getIdatendimento());
			}
				
			intent.putExtra(ID_VALUE, id);
			startActivity(intent);
<<<<<<< HEAD
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
		}

		intent.putExtra(ID_VALUE, id);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		if (userType.equalsIgnoreCase(DadosUsuario.TIPO_ENFERMEIRO)) {

			MenuItem menuItem = menu.findItem(R.id.action_agenda);
			menuItem.setVisible(true);

		}
<<<<<<< HEAD

		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_agenda:
			Intent agenda = new Intent(getApplicationContext(),
					AgendaActivity.class);
			startActivity(agenda);
			break;
=======
		
		@Override
		public boolean onMenuItemSelected(int featureId, MenuItem item) {
			switch (item.getItemId()) {
			case R.id.action_agenda:
				Intent agenda = new Intent(getApplicationContext(), AgendaActivity.class);
				startActivity(agenda);
				break;
			}
			return true;
=======
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
		}
		return true;
	}
}
