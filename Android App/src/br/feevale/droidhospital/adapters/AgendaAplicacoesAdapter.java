package br.feevale.droidhospital.adapters;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.MainActivity;
import br.feevale.droidhospital.PacienteAplicacoesActivity;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.db.Aplicacao;
import br.feevale.droidhospital.db.AplicacaoEfetuada;
import br.feevale.droidhospital.db.ConfirmaTransacao;
import br.feevale.droidhospital.db.Interpretador;

public class AgendaAplicacoesAdapter extends BaseAdapter {

	Integer idEnfermeiro;
	Context context;
	Aplicacao aplicacao;
	ArrayList<Aplicacao> aplicacoes;
	TextView medicamentoTextView   ;
	ImageView injection ;
	TextView dataAplicacaoTextView;
	TextView horaAplicacaoTextView;
	TextView nomePacienteTextView;
	TextView quartoTextView;

	
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
		
		aplicacao = aplicacoes.get(position);
		
		dataAplicacaoTextView = (TextView)layout.findViewById(R.id.agenda_data_textView);
		medicamentoTextView   = (TextView)layout.findViewById(R.id.agenda_medicamento_textView);
		horaAplicacaoTextView = (TextView)layout.findViewById(R.id.agenda_horario_textView);
		nomePacienteTextView = (TextView)layout.findViewById(R.id.agenda_paciente_textView);
		quartoTextView = (TextView)layout.findViewById(R.id.agenda_quarto_e_leito);
		injection = (ImageView)layout.findViewById(R.id.agenda_aplicacao_injection);
		
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
			
			injection.setVisibility(View.GONE);
			
			
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
		
		injection.setOnClickListener(new View.OnClickListener() {

			public void onClick(View viewClicked) {
				if(enviaAplicacao(aplicacao.getIdAplicacao())) {
					if (!aplicacao.isAplicada()){
						dataAplicacaoTextView.setPaintFlags(dataAplicacaoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
						medicamentoTextView.setPaintFlags(medicamentoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
						horaAplicacaoTextView.setPaintFlags(horaAplicacaoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
						nomePacienteTextView.setPaintFlags(nomePacienteTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
						quartoTextView.setPaintFlags(quartoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
						
						injection.setVisibility(View.GONE);
						Log.d(MainActivity.DROID_HOSPITAL_LOG_TAG, "onClick "+aplicacao.getIdAplicacao()+" "+medicamentoTextView.getText().toString());
						
						Toast.makeText(context, context.getString(R.string.application_mande), Toast.LENGTH_LONG).show();
						
						aplicacao.setAplicada(true);
					} else {
						Toast.makeText(context, context.getString(R.string.application_not_possible), Toast.LENGTH_LONG).show();
					}

				}else {
					Toast.makeText(context, context.getString(R.string.not_connected), Toast.LENGTH_LONG).show();
				}

			}
		});
		
		return layout;

	}
	

	
	private boolean enviaAplicacao(long id) {
		ConfirmaTransacao retorno = new ConfirmaTransacao();
		
 		try {
 			AplicacaoEfetuada interpretador = new AplicacaoEfetuada(String.valueOf(id));
 			interpretador.setIdEnfermeiro(idEnfermeiro);
 			
			interpretador.setCdTransacao(Interpretador.ENVIA_APLICACAO);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {

				enviador.envia();
				
				retorno = (ConfirmaTransacao) enviador.recebe();
				

			} finally {
				enviador.fechaSocket();
			}

		} catch (Exception e) {
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG, e.getMessage());
			Toast.makeText(context, ((PacienteAplicacoesActivity) context).getString(R.string.not_connected), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
 		if(retorno.getResult() == ConfirmaTransacao.RESULT_OK) {
 			return true;
 		}else {
 			return false;
 		}
	}


}
