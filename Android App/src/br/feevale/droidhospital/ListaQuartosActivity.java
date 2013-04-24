package br.feevale.droidhospital;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.feevale.droidhospital.adapters.QuartosAdapter;
import br.feevale.droidhospital.pojos.Quarto;

public class ListaQuartosActivity extends Activity implements OnItemClickListener {
	public static final String ID_VALUE = "id";
	ArrayList<Quarto> quartos;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quartos_layout);
		
		ListView quartosListView = (ListView) findViewById(R.id.quartos_list_view);
		
		setUpQuartos();
		
		QuartosAdapter quartosAdapter= new QuartosAdapter(getApplicationContext(), quartos);
		
		quartosListView.setAdapter(quartosAdapter);
		
		quartosListView.setOnItemClickListener(this);
	}


	private void setUpQuartos() {
		quartos = Quarto.quartos;
		ArrayList<Quarto> newQuartos = new ArrayList<Quarto>();
		String ultimoQuarto = ""; 
		
		for (int i = 0; i < quartos.size(); i++) {
			if(ultimoQuarto != quartos.get(i).getNumero()) {
				newQuartos.add(quartos.get(i));
				ultimoQuarto = quartos.get(i).getNumero();
			}else{
				ultimoQuarto = quartos.get(i).getNumero();
			}
		}
		quartos = newQuartos;
	}


	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		//Toast.makeText(getApplicationContext(), "Quarto" + String.valueOf(id), Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getApplicationContext(), ListaPacientesActivity.class);
		intent.putExtra(ID_VALUE, id);
		startActivity(intent);
	}
}
