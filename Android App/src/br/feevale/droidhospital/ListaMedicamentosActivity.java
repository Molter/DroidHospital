package br.feevale.droidhospital;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.MedicamentosAdapter;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.Medicamento;
import br.feevale.droidhospital.db.MedicamentoDescription;

public class ListaMedicamentosActivity extends Activity implements
		OnItemClickListener {
	public static final String ID_VALUE = "id";
	ArrayList<Medicamento> medicamentos;

	ListView medicamentosListView;
	EditText edit_busca;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medicamentos);

		medicamentosListView = (ListView) findViewById(R.id.lista_medicamentos);

		setUpMedicamentos("");

		MedicamentosAdapter medicamentosAdapter = new MedicamentosAdapter(
				getApplicationContext(), medicamentos);

		medicamentosListView.setAdapter(medicamentosAdapter);

		medicamentosListView.setOnItemClickListener(this);

		edit_busca = (EditText) findViewById(R.id.edit_busca);

		edit_busca.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {

				edit_busca.removeTextChangedListener(this);

				setUpMedicamentos(edit_busca.getText().toString());

				if (start == 50) {
					Toast.makeText(getBaseContext(),
							"Limite do campo atingido.", Toast.LENGTH_SHORT)
							.show();
					edit_busca.setText(s.toString().substring(0,
							s.toString().length() - 1));
					edit_busca.setSelection(s.toString().length() - 1);
				}

				atualizaLista();

				edit_busca.addTextChangedListener(this);
			}

		});

	}

	@SuppressWarnings("unchecked")
	private void setUpMedicamentos(String nome_medicamento) {
		try {

			MedicamentoDescription interpretador = new MedicamentoDescription(
					nome_medicamento);

			interpretador.setCdTransacao(Interpretador.LISTA_MEDICAMENTOS);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {

				enviador.envia();

				medicamentos = (ArrayList<Medicamento>) enviador.recebe();
				Log.d(MainActivity.DROID_HOSPITAL_LOG_TAG, medicamentos.size()
						+ " - " + medicamentos.get(0).getFantasia());

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
			finish();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {
		// Toast.makeText(getApplicationContext(), "Medicamento" +
		// String.valueOf(id), Toast.LENGTH_LONG).show();
		/*
		 * Intent intent = new Intent(getApplicationContext(),
		 * ListaPacientesActivity.class); intent.putExtra(ID_VALUE, id);
		 * startActivity(intent); // implementar retorno para tela de prescrição
		 */
	}

	private void atualizaLista() {

		MedicamentosAdapter adapter = new MedicamentosAdapter(this,
				medicamentos);
		medicamentosListView.setAdapter(adapter);

		adapter.notifyDataSetChanged();

	}

}
