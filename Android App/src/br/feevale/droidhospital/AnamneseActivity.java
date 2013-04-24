package br.feevale.droidhospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.droidhospital.adapters.AnamneseAdapter;
import br.feevale.droidhospital.pojos.Paciente;

public class AnamneseActivity extends Activity {
	Paciente paciente;
	public static String ID_PACIENTE = "id";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.anamnese);
		
		Intent intent = getIntent();
		long id = intent.getLongExtra(ListaQuartosActivity.ID_VALUE, 0);
		
		if(id == 0) {
			Toast.makeText(getApplicationContext(), "Paciente not found!", Toast.LENGTH_LONG).show();
			finish();
		}
		//aff afffad
		setUpPaciente(id);
		ExpandableListView expandableList = (ExpandableListView)findViewById(R.id.anamnese_expandablelistview);
		
		AnamneseAdapter adapter = new AnamneseAdapter(getApplicationContext(), id);
		expandableList.setAdapter(adapter);
//		general_info
//		applications_made
//		applications_todo
		
	}

	private void setUpPaciente(long id) {
		paciente = Paciente.getPacienteById(id);
		TextView  pacientName = (TextView) findViewById(R.id.anamnese_pacient_name);
		pacientName.setText(paciente.getNome());
	}
	
	public void novaPrescricao(View v) {
		Intent novaPrecricaoIntent = new Intent(getApplicationContext(), NovaPrescricaoActivity.class);
		novaPrecricaoIntent.putExtra(ID_PACIENTE, paciente.getId());
		
		startActivity(novaPrecricaoIntent);
	}

}
