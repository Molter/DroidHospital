package br.feevale.droidhospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.ListaQuartosAdapter;
import br.feevale.droidhospital.db.DadosUsuario;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.Quarto;

public class ListaQuartosActivity extends Activity implements
		OnItemClickListener {

	public static final String ID_VALUE = "id";

	private ArrayList<Quarto> quartos;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.quartos_layout);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ListView quartosListView = (ListView) findViewById(R.id.quartos_list_view);

		setUpDadosSocket();

		ListaQuartosAdapter quartosAdapter = new ListaQuartosAdapter(
				getApplicationContext(), quartos);

		quartosListView.setAdapter(quartosAdapter);

		quartosListView.setOnItemClickListener(this);
	}

	@SuppressWarnings("unchecked")
	private void setUpDadosSocket() {
		try {

			Interpretador interpretador = new Interpretador();

			interpretador.setCdTransacao(Interpretador.LISTA_LEITOS);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {

				enviador.envia();

				quartos =  (ArrayList<Quarto>) enviador.recebe();

			} finally {
				enviador.fechaSocket();
			}

		} catch (Exception e) {
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG, getString(R.string.not_connected));
			Toast.makeText(getApplicationContext(), getString(R.string.not_connected), Toast.LENGTH_LONG).show();
			e.printStackTrace();
			finish();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {
		// Toast.makeText(getApplicationContext(), "Quarto" +
		// String.valueOf(id), Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getApplicationContext(),
				ListaPacientesActivity.class);
		intent.putExtra(ID_VALUE, id);
		startActivity(intent);
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
