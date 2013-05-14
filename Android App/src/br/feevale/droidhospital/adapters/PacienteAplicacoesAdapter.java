package br.feevale.droidhospital.adapters;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.db.Aplicacao;

public class PacienteAplicacoesAdapter extends BaseAdapter {

	long idEnfermeiro;
	Context context;
	ArrayList<Aplicacao> aplicacoes;
	
	public PacienteAplicacoesAdapter (Context context, ArrayList<Aplicacao> aplicacoes) {
		this.aplicacoes = aplicacoes;
		this.context    = context; 
	}
	
	@Override
	public int getCount() {
		return aplicacoes.size();
	}
	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public Object getItem(int position) {
		return aplicacoes.get(position);
	}

	@Override
	public long getItemId(int position) {
		return aplicacoes.get(position).getIdAplicacao();
	}

	@Override
	public View getView(int position, View oldView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout =  inflater.inflate(R.layout.lista_aplicacoes, null);
		
		Aplicacao aplicacao = aplicacoes.get(position);
		
		TextView dataAplicacaoTextView = (TextView)layout.findViewById(R.id.descricao_data_textView);
		TextView medicamentoTextView   = (TextView)layout.findViewById(R.id.descricao_medicamento_textView);
		TextView horaAplicacaoTextView = (TextView)layout.findViewById(R.id.descricao_horario_textView);
		

		String myDate = DateFormat.getDateInstance().format(aplicacao.getHoraPrevisto());
		dataAplicacaoTextView.setText(myDate);
		
		Calendar c = Calendar.getInstance();
		c.setTime(aplicacao.getHoraPrevisto());
		
		StringBuilder horaString = new StringBuilder();
		
		Formatter hourFormatter = new Formatter();
		hourFormatter.format("%02d", c.get(Calendar.HOUR_OF_DAY));
		horaString.append(hourFormatter.toString());
		
		horaString.append(":");
		
		Formatter minuteFormatter = new Formatter();
		minuteFormatter.format("%02d", c.get(Calendar.MINUTE));
		horaString.append(minuteFormatter.toString());
		
		horaAplicacaoTextView.setText(horaString.toString());

		
		String nomeMedicamento   = aplicacao.getNomeMedicamento() + " " + aplicacao.getConcentracaoMedicamento();
		medicamentoTextView.setText(nomeMedicamento);
		
		if(position % 2 == 0) {
			layout.setBackgroundColor(Color.GRAY);
		}else{
			layout.setBackgroundColor(Color.WHITE);
		}
		
		return layout;

	}
	

}
