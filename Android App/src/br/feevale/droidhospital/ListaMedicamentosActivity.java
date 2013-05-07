package br.feevale.droidhospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.MedicamentosAdapter;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.Quarto;
import br.feevale.droidhospital.pojos.Medicamento;

public class ListaMedicamentosActivity extends Activity implements OnItemClickListener {
	public static final String ID_VALUE = "id";
	ArrayList<Medicamento> medicamentos;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medicamentos);
		
		ListView medicamentosListView = (ListView) findViewById(R.id.lista_medicamentos);
		
		setUpMedicamentos();
		
		MedicamentosAdapter medicamentosAdapter= new MedicamentosAdapter(getApplicationContext(), medicamentos);
		
		medicamentosListView.setAdapter(medicamentosAdapter);
		
		medicamentosListView.setOnItemClickListener(this);
	}


	private void setUpMedicamentos() {
		try {

			Interpretador interpretador = new Interpretador();

			interpretador.setCdTransacao(Interpretador.LISTA_MEDICAMENTOS);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {

				enviador.envia();

				medicamentos =  (ArrayList<Medicamento>) enviador.recebe();

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
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		//Toast.makeText(getApplicationContext(), "Medicamento" + String.valueOf(id), Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getApplicationContext(), ListaPacientesActivity.class);
		intent.putExtra(ID_VALUE, id);
		startActivity(intent);
	}
}
