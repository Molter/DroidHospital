package br.feevale.droidhospital;

import java.util.ArrayList;

import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.LeitosAdapter;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.Leito;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListaLeitosActivity extends Activity implements
		OnItemClickListener {

	public static final String ID_VALUE = "id";

	private ArrayList<Leito> leitos;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.quartos_layout);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);

		ListView quartosListView = (ListView) findViewById(R.id.quartos_list_view);

		setUpLeitos();

		LeitosAdapter leitosAdapter = new LeitosAdapter(
				getApplicationContext(), leitos);

		quartosListView.setAdapter(leitosAdapter);

		quartosListView.setOnItemClickListener(this);
	}

	private void setUpLeitos() {
		leitos = alimentaLeitos();
	}

	@SuppressWarnings("unchecked")
	private ArrayList<Leito> alimentaLeitos() {

		try {

			Interpretador interpretador = new Interpretador();

			interpretador.setCdTransacao(Interpretador.LISTA_LEITOS);

			EnviaTransacao enviador = new EnviaTransacao(interpretador);

			try {

				enviador.envia();

				return (ArrayList<Leito>) enviador.recebe();

			} finally {
				enviador.fechaSocket();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View view, int position,
			long id) {
		// Toast.makeText(getApplicationContext(), "Quarto" +
		// String.valueOf(id), Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getApplicationContext(),
				ListaPacientesActivity.class);
		intent.putExtra(ID_VALUE, id);
		startActivity(intent);
	}
}
