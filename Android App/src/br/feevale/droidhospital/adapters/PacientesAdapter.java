package br.feevale.droidhospital.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.db.Paciente;

public class PacientesAdapter extends BaseAdapter {
	long idQuarto;
	Context context;
	ArrayList<Paciente> pacientes;
	
	
	public PacientesAdapter(Context context, ArrayList<Paciente> pacientes){
		this.context = context;
		this.pacientes = pacientes;
	}
	
	@Override
	public int getCount() {
		return pacientes.size();
	}

	@Override
	public Object getItem(int position) {
		return pacientes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return pacientes.get(position).getIdPaciente();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Paciente paciente = pacientes.get(position);
		
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
		View layout =  inflater.inflate(R.layout.paciente, null);
		TextView pacienteTextView = (TextView)layout.findViewById(R.id.paciente_item_text_view);
		
		String quartoELeito = paciente.getQuartoPaciente() + " " + paciente.getLeitoPaciente();
		
		String nomeELeitoPaciente = quartoELeito +" "+  paciente.getNomePaciente();
		pacienteTextView.setText(nomeELeitoPaciente);
		
		return layout;
	}
}