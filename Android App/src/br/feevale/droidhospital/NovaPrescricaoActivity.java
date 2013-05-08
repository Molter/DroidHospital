package br.feevale.droidhospital;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NovaPrescricaoActivity extends Activity {

	EditText edPrincipio;
	EditText edReferencia;
	EditText edLaboratorio;
	EditText edConcentracao;
	EditText edPosologia;
	EditText edHora_inicial;
	EditText edQtd_aplicacoes;
	EditText edIntervalo;	
	
	private final int LISTA_MEDICAMENTOS = 301; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nova_prescricao);
		
	
		Intent intent = getIntent();
		long id = intent.getLongExtra(AnamneseActivity.ID_PACIENTE, 0);
		
		if(id == 0) {
			Toast.makeText(getApplicationContext(), "Paciente not found", Toast.LENGTH_LONG).show();
			finish();
		}
		
		setUpPaciente(id);
		
		edPrincipio 	 = (EditText) findViewById(R.id.principio_edit_text);
		edReferencia     = (EditText) findViewById(R.id.referencia_edit_text);
		edLaboratorio    = (EditText) findViewById(R.id.laboratorio_edit_text);
		edConcentracao   = (EditText) findViewById(R.id.concentracao_edit_text);
		edPosologia      = (EditText) findViewById(R.id.posologia_edit_text);
		edHora_inicial   = (EditText) findViewById(R.id.hora_inicial_edit_text);
		edQtd_aplicacoes = (EditText) findViewById(R.id.qtd_aplicacoes_edit_text);
		edIntervalo      = (EditText) findViewById(R.id.intervalo_edit_text);		
		
	}
	
	private void setUpPaciente(long id) {
		TextView  pacientName = (TextView) findViewById(R.id.descricao_paciente_textView);
		TextView  numLeito    = (TextView) findViewById(R.id.descricao_leito_textView);
		pacientName.setText("");
		numLeito.setText("01 a");		
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nova_prescricao, menu);
		return true;
	}
	
    public void listaMedicamentos(View v) {
		Intent chamaTelaLista = new Intent(getBaseContext(), ListaMedicamentosActivity.class);
		startActivityForResult(chamaTelaLista, LISTA_MEDICAMENTOS);
    	
	}

	public void adicionarPrescricao(View v){

		Toast.makeText(getApplicationContext(), "Paciente Jo�o adicionado", Toast.LENGTH_LONG).show();
		finish();
		
		/*
		String descPrincipio     = edPrincipio.getText().toString();
		String descReferencia    = edReferencia.getText().toString();
		String descLaboratorio   = edLaboratorio.getText().toString();
		String descConcentracao  = edConcentracao.getText().toString();
		String descPosologia     = edPosologia.getText().toString();
		String descHoraInicial   = edHora_inicial.getText().toString();
		String descQtdAplicacoes = edQtd_aplicacoes.getText().toString();
		String descIntervalo     = edIntervalo.getText().toString();	
		
		edPrincipio.setText("");
		edReferencia.setText("");
		edLaboratorio.setText("");
		edConcentracao.setText("");
		edPosologia.setText("");
		edHora_inicial.setText("");
		edQtd_aplicacoes.setText("");
		edIntervalo.setText("");	
		
		Prescricao p = new Prescricao();
		p.setPrincipio(descPrincipio);
		p.setReferencia(descReferencia);
		p.setLaboratorio(descLaboratorio);
		p.setConcentracao(descConcentracao);
		p.setPosologia(descPosologia);
		p.setHora_inicial(descHoraInicial);
		p.setQtd_aplicacoes(descQtdAplicacoes);
		p.setIntervalo(descIntervalo);
		*/
		
	}
	
}
