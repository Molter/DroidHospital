package br.feevale.droidhospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.feevale.droidhospital.adapters.PacientesAdapter;
import br.feevale.droidhospital.pojos.Quarto;

public class ListaPacientesActivity extends Activity implements OnItemClickListener {
		Quarto quarto;

		public static final String ID_VALUE = "id";
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.pacientes_layout);
			
			ListView pacientesListView = (ListView) findViewById(R.id.pacientes_list_view);
			
			
			Intent intent = getIntent();
			long id = intent.getLongExtra(ListaLeitosActivity.ID_VALUE, 0);
			
			if(id == 0) {
				Toast.makeText(getApplicationContext(), "Quarto not found", Toast.LENGTH_LONG).show();
				finish();
			}
			
			setUpQuarto(id);
			
			PacientesAdapter pacientesAdapter= new PacientesAdapter(getApplicationContext(), id);
			
			pacientesListView.setAdapter(pacientesAdapter);
			
			pacientesListView.setOnItemClickListener(this);
			
		}
		
		private void setUpQuarto(long id) {
			quarto = Quarto.getQuartoById(id);
			this.setTitle(quarto.getNumero());
		}

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long id) {
			Intent intent = new Intent(getApplicationContext(), AnamneseActivity.class);
			intent.putExtra(ID_VALUE, id);
			startActivity(intent);
			//Toast.makeText(getApplicationContext(), "Paciente" + String.valueOf(id), Toast.LENGTH_LONG).show();
		}
}
