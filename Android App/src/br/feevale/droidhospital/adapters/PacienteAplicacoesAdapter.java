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
		
		final Aplicacao aplicacao = aplicacoes.get(position);
		
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

		ImageView injection = (ImageView) layout.findViewById(R.id.aplicacao_injection);

		if(aplicacao.isAplicada()){
			medicamentoTextView.setPaintFlags(medicamentoTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
			
			injection = (ImageView)layout.findViewById(R.id.aplicacao_injection);
			injection.setVisibility(View.GONE);
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
					View parent = (View) viewClicked.getParent();
					
					TextView medicineDescription = (TextView) parent.findViewById(R.id.descricao_medicamento_textView);
					medicineDescription.setPaintFlags(medicineDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
					medicineDescription.invalidate();
					
					ImageView image = (ImageView)viewClicked.findViewById(R.id.aplicacao_injection);
					image.setVisibility(View.GONE);
					
					Toast.makeText(context, ((PacienteAplicacoesActivity) context).getString(R.string.application_mande), Toast.LENGTH_LONG).show();
					
				}else {
					Toast.makeText(context, ((PacienteAplicacoesActivity) context).getString(R.string.not_connected), Toast.LENGTH_LONG).show();
				}

			}
		});
		
		aplicacao.getIdAplicacao();
		
		return layout;

	}
	
	
	private boolean enviaAplicacao(long id) {
		ConfirmaTransacao retorno = new ConfirmaTransacao();
		
 		try {
 			AplicacaoEfetuada interpretador = new AplicacaoEfetuada(String.valueOf(id));

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
			//finish();
		}
 		if(retorno.getResult() == ConfirmaTransacao.RESULT_OK) {
 			return true;
 		}else {
 			return false;
 		}
	}
	

}
