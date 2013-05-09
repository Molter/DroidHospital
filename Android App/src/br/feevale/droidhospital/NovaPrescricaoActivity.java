package br.feevale.droidhospital;

import java.util.Calendar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.db.ConfirmaTransacao;
import br.feevale.droidhospital.db.DadosUsuario;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.Medicamento;
import br.feevale.droidhospital.db.MedicamentoDescription;
import br.feevale.droidhospital.db.Prescricao;
import br.feevale.droidhospital.fragments.HoraInicialDatePicker;
import br.feevale.droidhospital.fragments.IntervaloDialog;
import br.feevale.droidhospital.fragments.QtdAplicacoesDialog;
import br.feevale.droidhospital.interfaces.OnDialogFinished;

public class NovaPrescricaoActivity extends FragmentActivity {

	Integer idMedicamento;
	EditText edPrincipio;
	EditText edReferencia;
	EditText edLaboratorio;
	EditText edConcentracao;
	EditText edPosologia;
	TextView edHora_inicial;
	TextView edQtd_aplicacoes;
	TextView edIntervalo;

	int horaInicial, minutoInicial;
	int qtdAplicacoes, horaIntervalo = 1;
	int minutoIntervalo = 0;

	ConfirmaTransacao resultStatus;

	OnDialogFinished horaInicialListener = new OnDialogFinished() {
		@Override
		public void onDialogFinished(int hora, int minuto) {

			horaInicial = hora;
			minutoInicial = minuto;
		}
	};

	OnDialogFinished qtdAplicacoesListener = new OnDialogFinished() {
		@Override
		public void onDialogFinished(int aplicacoes, int zero) {
			qtdAplicacoes = aplicacoes;
		}
	};

	OnDialogFinished intervaloListener = new OnDialogFinished() {
		@Override
		public void onDialogFinished(int hora, int minuto) {

			horaIntervalo = hora;
			minutoIntervalo = minuto;
		}
	};

	Medicamento medicamento;

	public final static int LISTA_MEDICAMENTOS = 301;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nova_prescricao);

		Intent intent = getIntent();
		long id = intent.getLongExtra(AnamneseActivity.ID_PACIENTE, 0);

		if (id == 0) {
			Toast.makeText(getApplicationContext(), "Paciente not found",
					Toast.LENGTH_LONG).show();
			finish();
		}

		String nomePaciente = intent
				.getStringExtra(AnamneseActivity.NOME_PACIENTE);
		String leitoPaciente = intent
				.getStringExtra(AnamneseActivity.LEITO_PACIENTE);
		TextView pacientName = (TextView) findViewById(R.id.descricao_paciente_textView);
		TextView numLeito = (TextView) findViewById(R.id.descricao_leito_textView);

		pacientName.setText(nomePaciente);
		numLeito.setText(leitoPaciente);

		setTitle(getString(R.string.title_activity_nova_prescricao) + " "
				+ nomePaciente);

		edPrincipio = (EditText) findViewById(R.id.principio_edit_text);
		edReferencia = (EditText) findViewById(R.id.referencia_edit_text);
		edLaboratorio = (EditText) findViewById(R.id.laboratorio_edit_text);
		edConcentracao = (EditText) findViewById(R.id.concentracao_edit_text);
		edPosologia = (EditText) findViewById(R.id.posologia_edit_text);

		// Seta hora inicial com a hora atual
		edHora_inicial = (TextView) findViewById(R.id.hora_inicial_edit_text);

		Calendar c = Calendar.getInstance();

		horaInicial = c.get(Calendar.HOUR_OF_DAY);
		minutoInicial = c.get(Calendar.MINUTE);

		edHora_inicial.setText(String.valueOf(horaInicial) + ": "
				+ String.valueOf(minutoInicial));

		edQtd_aplicacoes = (TextView) findViewById(R.id.qtd_aplicacoes_picker);

		edIntervalo = (TextView) findViewById(R.id.intervalo_edit_text);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.nova_prescricao, menu);
		return true;
	}

	public void listaMedicamentos(View v) {
		Intent chamaTelaLista = new Intent(getBaseContext(),
				ListaMedicamentosActivity.class);
		startActivityForResult(chamaTelaLista, LISTA_MEDICAMENTOS);

	}

	public void setHoraInicial(View v) {
		HoraInicialDatePicker horainicialFragment = new HoraInicialDatePicker();
		horainicialFragment.setCallback(horaInicialListener);

		horainicialFragment.show(getSupportFragmentManager(), "timePicker");
	}

	public void setQtdAplicacoes(View v) {
		QtdAplicacoesDialog qtdAplicacoes = new QtdAplicacoesDialog();
		qtdAplicacoes.setCallback(qtdAplicacoesListener);

		qtdAplicacoes.show(getSupportFragmentManager(), "timePicker");
	}

	public void setIntervalo(View v) {
		IntervaloDialog intervaloFragment = new IntervaloDialog();
		intervaloFragment.setCallback(intervaloListener);

		intervaloFragment.show(getSupportFragmentManager(), "timePicker");
	}

	public void adicionarPrescricao(View v) {

		Prescricao prescricao = new Prescricao();

		if (medicamento == null) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.medice_required), Toast.LENGTH_LONG)
					.show();
			return;
		}
		prescricao.setIdMedicamento(medicamento.getIdMedicamento());

		prescricao.setHoraInicioAplicacoes(horaInicial);
		prescricao.setMinutoInicioAplicacoes(minutoInicial);

		prescricao.setHoraIntervaloAplicacoes(horaIntervalo);
		prescricao.setMinutoIntervaloAplicacoes(minutoIntervalo);

		prescricao.setQuantidadeAplicacoes(qtdAplicacoes);
		
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		int idMedico = prefs.getInt(MainActivity.USER_ID_PREFERENCE, 0);
		
		if(idMedico != 0) {
			prescricao.setIdMedico(idMedico);
		}

		try {

			Interpretador interpretador = (Interpretador) prescricao;

			interpretador.setCdTransacao(Interpretador.CREATE_PRESCRICAO);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {

				enviador.envia();
				resultStatus = (ConfirmaTransacao) enviador.recebe();

			} finally {
				enviador.fechaSocket();
			}

		} catch (Exception e) {
			Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG,
					getString(R.string.not_connected));
			Toast.makeText(getApplicationContext(),
					getString(R.string.not_connected), Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}

		if (resultStatus.getResult() == ConfirmaTransacao.RESULT_OK) {
			Toast.makeText(getApplicationContext(),
					getString(R.string.saved_sucessuful), Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(getApplicationContext(),
					getString(R.string.saved_wrong), Toast.LENGTH_LONG).show();
		}
		
		
		finish();
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == LISTA_MEDICAMENTOS) {

			switch (resultCode) {
			case RESULT_OK:
				medicamento = new Medicamento();
				String id_medicamento;
				id_medicamento = data
						.getStringExtra(ListaMedicamentosActivity.ID_VALUE);
				Log.d(MainActivity.DROID_HOSPITAL_LOG_TAG, "id_medicamento: "
						+ id_medicamento);

				try {

					MedicamentoDescription interpretador = new MedicamentoDescription(
							id_medicamento);

					interpretador
							.setCdTransacao(Interpretador.BUSCA_MEDICAMENTO);

					EnviaTransacao enviador = new EnviaTransacao(interpretador);

					try {

						enviador.envia();

						medicamento = (Medicamento) enviador.recebe();

					} finally {
						enviador.fechaSocket();
					}

				} catch (Exception e) {
					Log.e(MainActivity.DROID_HOSPITAL_LOG_TAG,
							getString(R.string.not_connected));
					Toast.makeText(getApplicationContext(),
							getString(R.string.not_connected),
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

				idMedicamento = medicamento.getIdMedicamento();
				edPrincipio.setText(medicamento.getPrincipio());
				edReferencia.setText(medicamento.getFantasia());
				edLaboratorio.setText(medicamento.getLaboratorio());
				edConcentracao.setText(medicamento.getConcentracao());
				edPosologia.setText(medicamento.getFormaFarmaceutica());

				break;
			case RESULT_CANCELED:

				break;

			default:

				break;
			}

		}

	}

}
