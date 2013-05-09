package br.feevale.droidhospital.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.db.Medicamento;

public class MedicamentosAdapter extends BaseAdapter {
	ArrayList<Medicamento> medicamentos;
	Context context;
	
	public MedicamentosAdapter(Context context, ArrayList<Medicamento> medicamentos){
		this.context = context;
		this.medicamentos = medicamentos;
	}
	
	@Override
	public int getCount() {
		return medicamentos.size();
	}

	@Override
	public Object getItem(int position) {
		return medicamentos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return medicamentos.get(position).getIdMedicamento();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Medicamento medicamento = medicamentos.get(position);
		String principio = medicamento.getPrincipio();
		String laboratorio = medicamento.getLaboratorio();
		String fantasia = medicamento.getFantasia();
		String concentracao = medicamento.getConcentracao();
		String forma = medicamento.getFormaFarmaceutica();

		LayoutInflater inflater = (LayoutInflater)context.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.medicamentos_item, null);
		TextView principioTextView = (TextView)layout.findViewById(R.id.txt_principio);
		TextView laboratorioTextView = (TextView)layout.findViewById(R.id.txt_laboratorio);
		TextView fantasiaTextView = (TextView)layout.findViewById(R.id.txt_fantasia);
		TextView concentracaoTextView = (TextView)layout.findViewById(R.id.txt_concentracao);
		TextView formaTextView = (TextView)layout.findViewById(R.id.txt_forma);
		
		principioTextView.setText(principio);
		laboratorioTextView.setText(laboratorio);
		fantasiaTextView.setText(fantasia+" - ");
		concentracaoTextView.setText(concentracao+" - ");
		formaTextView.setText(forma);
		return layout;
	}
}