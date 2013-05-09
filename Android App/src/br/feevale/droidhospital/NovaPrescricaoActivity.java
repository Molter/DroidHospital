package br.feevale.droidhospital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.droidhospital.fragments.HoraInicialDatePicker;
import br.feevale.droidhospital.fragments.IntervaloDialog;
import br.feevale.droidhospital.fragments.QtdAplicacoesDialog;


public class NovaPrescricaoActivity extends FragmentActivity{

	EditText edPrincipio;
	EditText edReferencia;
	EditText edLaboratorio;
	EditText edConcentracao;
	EditText edPosologia;
	TextView edHora_inicial;
	TextView edQtd_aplicacoes;
	TextView edIntervalo;	
	
	int horaInicial, minutoInicial, qtdAplicacoes, horaIntervalo, minutoIntervalo;
	
	
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
		
		String nomePaciente = intent.getStringExtra(AnamneseActivity.NOME_PACIENTE);
		String leitoPaciente = intent.getStringExtra(AnamneseActivity.LEITO_PACIENTE);
		TextView  pacientName = (TextView) findViewById(R.id.descricao_paciente_textView);
		TextView  numLeito    = (TextView) findViewById(R.id.descricao_leito_textView);
		
		pacientName.setText(nomePaciente);
		numLeito.setText(leitoPaciente);
		
		setTitle(getString(R.string.title_activity_nova_prescricao) + " " + nomePaciente);
		
		edPrincipio 	 = (EditText) findViewById(R.id.principio_edit_text);
		edReferencia     = (EditText) findViewById(R.id.referencia_edit_text);
		edLaboratorio    = (EditText) findViewById(R.id.laboratorio_edit_text);
		edConcentracao   = (EditText) findViewById(R.id.concentracao_edit_text);
		edPosologia      = (EditText) findViewById(R.id.posologia_edit_text);
		edHora_inicial   = (TextView) findViewById(R.id.hora_inicial_edit_text);
		edQtd_aplicacoes = (TextView) findViewById(R.id.qtd_aplicacoes_picker);
		
		edIntervalo      = (TextView) findViewById(R.id.intervalo_edit_text);		
		
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nova_prescricao, menu);
		return true;
	}
	
    public void listaMedicamentos(View v) {
		Intent chamaTelaLista = new Intent(getBaseContext(), ListaMedicamentosActivity.class);
		startActivity(chamaTelaLista);
    	
    	Toast.makeText(getApplicationContext(), "Chama lista de medicamentos", Toast.LENGTH_LONG).show();
    	//finish();
	}
    
    public void setHoraInicial(View v) {
    	DialogFragment  horainicialFragment = new HoraInicialDatePicker();
		horainicialFragment.show(getSupportFragmentManager(), "timePicker");
    }
    
    public void setQtdAplicacoes(View v) {
    	DialogFragment  qtdAplicacoes = new QtdAplicacoesDialog();
		qtdAplicacoes.show(getSupportFragmentManager(), "timePicker");
    }
    
    public void setIntervalo(View v) {
    	DialogFragment  intervaloFragment = new IntervaloDialog();
    	intervaloFragment.show(getSupportFragmentManager(), "timePicker");
    }
    
    

	public void adicionarPrescricao(View v){

		Toast.makeText(getApplicationContext(), "Paciente João adicionado", Toast.LENGTH_LONG).show();
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
