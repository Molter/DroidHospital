package br.feevale.droidhospital.adapters;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.db.Aplicacao;

public class AgendaAplicacoesAdapter extends BaseAdapter {

	long idEnfermeiro;
	Context context;
	ArrayList<Aplicacao> aplicacoes;
	
	public AgendaAplicacoesAdapter (Context context, ArrayList<Aplicacao> aplicacoes) {
		this.aplicacoes = aplicacoes;
		this.context    = context; 
	}
	
	@Override
	public int getCount() {
		return aplicacoes.size();
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
		View layout =  inflater.inflate(R.layout.item_agenda_aplicacoes, null);
		
		Aplicacao aplicacao = aplicacoes.get(position);
		
		TextView dataAplicacaoTextView = (TextView)layout.findViewById(R.id.agenda_data_textView);
		TextView medicamentoTextView   = (TextView)layout.findViewById(R.id.agenda_medicamento_textView);
		TextView horaAplicacaoTextView = (TextView)layout.findViewById(R.id.agenda_horario_textView);
		TextView nomePacienteTextView = (TextView)layout.findViewById(R.id.agenda_paciente_textView);
		TextView quartoTextView = (TextView)layout.findViewById(R.id.agenda_quarto_e_leito);
		
		nomePacienteTextView.setText(aplicacao.getNomePaciente());
		quartoTextView.setText(aplicacao.getQuartoELeito());
		
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

		if(aplicacao.isAplicada()){
			medicamentoTextView.setPaintFlags(medicamentoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			
			ImageView image = (ImageView)layout.findViewById(R.id.agenda_aplicacao_injection);
			image.setVisibility(View.GONE);
			
			
			dataAplicacaoTextView.setPaintFlags(dataAplicacaoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			medicamentoTextView.setPaintFlags(medicamentoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			horaAplicacaoTextView.setPaintFlags(horaAplicacaoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			nomePacienteTextView.setPaintFlags(nomePacienteTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			quartoTextView.setPaintFlags(quartoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		}
		
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
