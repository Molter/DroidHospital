package br.feevale.droidhospital.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.pojos.Quarto;

public class QuartosAdapter extends BaseAdapter {
	ArrayList<Quarto> quartos;
	Context context;
	
	public QuartosAdapter(Context context, ArrayList<Quarto> quartos){
		this.context = context;
		this.quartos = quartos;
	}
	
	@Override
	public int getCount() {
		return quartos.size();
	}

	@Override
	public Object getItem(int position) {
		return quartos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return quartos.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Quarto quarto = quartos.get(position);
		String nomeQuarto = quarto.getNumero();
			
		LayoutInflater inflater = (LayoutInflater)context.getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.quarto, null);
		TextView quartoTextView = (TextView)layout.findViewById(R.id.quarto_item_text_view);
		
		quartoTextView.setText(nomeQuarto);
		return layout;
	}
}