package br.feevale.droidhospital;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.AnamneseAdapter;
import br.feevale.droidhospital.db.DadosId;
import br.feevale.droidhospital.db.DadosUsuario;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.PacienteDescription;

public class AnamneseActivity extends Activity {
	public static String ID_PACIENTE = "id";
	public static String NOME_PACIENTE = "nome";
	public static String LEITO_PACIENTE = "leito";

	private long idPaciente;
	private int idAtendimento;
	private PacienteDescription dadosPaciente;

	AnamneseAdapter adapter;
	ExpandableListView expandableList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anamnese);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		Intent intent = getIntent();
		idPaciente = intent.getLongExtra(ListaQuartosActivity.ID_VALUE, 0);

		if (idPaciente == 0) {
			Toast.makeText(getApplicationContext(),
					"Paciente not found! Plese Try Again", Toast.LENGTH_LONG)
					.show();
			finish();
		}
		idAtendimento = intent.getIntExtra(
				ListaPacientesActivity.ID_ATENDIMENTO, 0);

		try {
			setUpDadosSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}

		expandableList = (ExpandableListView) findViewById(R.id.anamnese_expandablelistview);

		adapter = new AnamneseAdapter(getApplicationContext(), dadosPaciente);
		expandableList.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			setUpDadosSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}

		adapter = new AnamneseAdapter(getApplicationContext(), dadosPaciente);
		expandableList.setAdapter(adapter);

		try {
			setUpDadosSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ExpandableListView expandableList = (ExpandableListView) findViewById(R.id.anamnese_expandablelistview);

		AnamneseAdapter adapter = new AnamneseAdapter(getApplicationContext(),
				dadosPaciente);
		expandableList.setAdapter(adapter);
	}

	public void setUpDadosSocket() throws IOException {
		Interpretador interpretador = new DadosId(String.valueOf(idPaciente));
		interpretador.setCdTransacao(Interpretador.DADOS_PACIENTE);
		EnviaTransacao enviador = new EnviaTransacao(interpretador);

		try {
			enviador.envia();
			dadosPaciente = (PacienteDescription) enviador.recebe();

		} catch (Exception e) {
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG,
					getString(R.string.not_connected));
			Toast.makeText(getApplicationContext(),
					getString(R.string.not_connected), Toast.LENGTH_LONG)
					.show();
			finish();

			e.printStackTrace();
		} finally {

			enviador.fechaSocket();

			TextView pacientName = (TextView) findViewById(R.id.anamnese_pacient_name);

			pacientName.setText(dadosPaciente.getNome());
			setTitle(dadosPaciente.getQuarto() + " " + dadosPaciente.getLeito()
					+ " - " + dadosPaciente.getNome());
		}

	}

	public void novaPrescricao(View v) {

		Intent novaPrecricaoIntent = new Intent(getApplicationContext(), NovaPrescricaoActivity.class);
		novaPrecricaoIntent.putExtra(ID_PACIENTE, idPaciente);
		novaPrecricaoIntent.putExtra(NOME_PACIENTE, dadosPaciente.getNome());
		novaPrecricaoIntent.putExtra(LEITO_PACIENTE, dadosPaciente.getQuarto()+ " " + dadosPaciente.getLeito());
		novaPrecricaoIntent.putExtra(ListaPacientesActivity.ID_ATENDIMENTO, idAtendimento);
		startActivity(novaPrecricaoIntent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplicationContext());
		String userType = prefs.getString(MainActivity.USER_TYPE_PREFERENCE,
				DadosUsuario.FAIL);

		if (userType.equalsIgnoreCase(DadosUsuario.TIPO_ENFERMEIRO)) {
			MenuItem menuItem = menu.getItem(R.id.action_agenda);
			menuItem.setVisible(true);
		}

		return true;
	}
}