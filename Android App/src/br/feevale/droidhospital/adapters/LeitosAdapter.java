package br.feevale.droidhospital.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.db.Leito;

public class LeitosAdapter extends BaseAdapter {
	
	private ArrayList<Leito> leitos;
	private Context context;
	
	public LeitosAdapter(Context context, ArrayList<Leito> leitos) {
		this.context = context;
		this.leitos = leitos;
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
		return leitos.get(position).getIdLeito();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Leito leito = leitos.get(position);
		String nomeLeito = leito.getLeito();
			
		LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
		View layout = inflater.inflate(R.layout.quarto, null);
		TextView quartoTextView = (TextView)layout.findViewById(R.id.quarto_item_text_view);
		
		quartoTextView.setText(nomeLeito);
		return layout;
	}
}