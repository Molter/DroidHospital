package br.feevale.droidhospital.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.db.Quarto;

public class ListaQuartosAdapter extends BaseAdapter {
	
	private ArrayList<Quarto> leitos;
	private Context context;
	
	public ListaQuartosAdapter(Context context, ArrayList<Quarto> quartos) {
		this.context = context;
		this.leitos = quartos;
	}
	
	@Override
	public int getCount() {
		return leitos.size();
	}

	@Override
	public Object getItem(int position) {
		return leitos.get(position);
	}

	@Override
	public long getItemId(int position) {
		return Long.valueOf(leitos.get(position).getQuarto());
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Quarto leito = leitos.get(position);
		
		String nomeQuarto = leito.getQuarto();
			
		LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View layout = inflater.inflate(R.layout.quarto, null);
		TextView quartoTextView = (TextView)layout.findViewById(R.id.quarto_item_text_view);
		
		quartoTextView.setText(nomeQuarto);
		return layout;
	}
}