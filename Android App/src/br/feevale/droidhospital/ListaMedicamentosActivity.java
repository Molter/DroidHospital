package br.feevale.droidhospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.feevale.droidhospital.adapters.MedicamentosAdapter;
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
		medicamentos = Medicamento.medicamentos;
		ArrayList<Medicamento> newQuartos = new ArrayList<Medicamento>();
		String ultimoMedicamento = ""; 
		
		for (int i = 0; i < medicamentos.size(); i++) {
			if(ultimoMedicamento != medicamentos.get(i).getPrincipio()) {
				newQuartos.add(medicamentos.get(i));
				ultimoMedicamento = medicamentos.get(i).getPrincipio();
			}else{
				ultimoMedicamento = medicamentos.get(i).getPrincipio();
			}
		}
		medicamentos = newQuartos;
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		//Toast.makeText(getApplicationContext(), "Medicamento" + String.valueOf(id), Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getApplicationContext(), ListaPacientesActivity.class);
		intent.putExtra(ID_VALUE, id);
		startActivity(intent);
	}
}
