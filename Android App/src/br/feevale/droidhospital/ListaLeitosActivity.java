package br.feevale.droidhospital;

import java.util.ArrayList;

import br.feevale.comunicacao.EnviaTransacao;
import br.feevale.droidhospital.adapters.LeitosAdapter;
import br.feevale.droidhospital.db.Interpretador;
import br.feevale.droidhospital.db.Leito;
import br.feevale.droidhospital.pojos.Quarto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListaLeitosActivity extends Activity implements OnItemClickListener {
	
	public static final String ID_VALUE = "id";
	
	private ArrayList<Leito> leitos;
	
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.quartos_layout);
		
		ListView quartosListView = (ListView) findViewById(R.id.quartos_list_view);
		
		setUpLeitos();
		
		LeitosAdapter quartosAdapter= new LeitosAdapter( getApplicationContext(), leitos );
		
		quartosListView.setAdapter(quartosAdapter);
		
		quartosListView.setOnItemClickListener(this);
	}


	private void setUpLeitos() {
		
		leitos = alimentaLeitos();
		//leitos = leitosOffLine();
		
		ArrayList<Leito> newQuartos = new ArrayList<Leito>();
		String ultimoQuarto = ""; 
		
		for( int i = 0; i < leitos.size(); i++ ) {
			if( ultimoQuarto != leitos.get(i).getQuarto() ) {
				newQuartos.add(leitos.get(i));
				ultimoQuarto = leitos.get(i).getQuarto();
			} else{
				ultimoQuarto = leitos.get(i).getQuarto();
			}
		}
		
		leitos = newQuartos;
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<Leito> alimentaLeitos() {
		
		try {

			Interpretador interpretador = new Interpretador();
			
			interpretador.setCdTransacao( Interpretador.LISTA_LEITOS );
			
			EnviaTransacao enviador = new EnviaTransacao( interpretador );
			
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
	public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
		//Toast.makeText(getApplicationContext(), "Quarto" + String.valueOf(id), Toast.LENGTH_LONG).show();
		Intent intent = new Intent(getApplicationContext(), ListaPacientesActivity.class);
		intent.putExtra(ID_VALUE, id);
		startActivity(intent);
	}
	private ArrayList<Leito> leitosOffLine(){
		ArrayList<Leito> list_leitos = new ArrayList<Leito>();
		Leito leito1 = new Leito();
		Leito leito2 = new Leito();
		Leito leito3 = new Leito();
		Leito leito4 = new Leito();
		Leito leito5 = new Leito();
		Leito leito6 = new Leito();
		Leito leito7 = new Leito();
		Leito leito8 = new Leito();
		Leito leito9 = new Leito();
		Leito leito10 = new Leito();
		Leito leito11 = new Leito();
		Leito leito12 = new Leito();
		
		leito1.setIdLeito(1); leito1.setQuarto("101"); leito1.setLeito("A"); list_leitos.add(leito1);
		leito2.setIdLeito(2); leito2.setQuarto("101"); leito2.setLeito("B"); list_leitos.add(leito2);
		leito3.setIdLeito(3); leito3.setQuarto("101"); leito3.setLeito("C"); list_leitos.add(leito3);
		leito4.setIdLeito(4); leito4.setQuarto("102"); leito4.setLeito("A"); list_leitos.add(leito4);
		leito5.setIdLeito(5); leito5.setQuarto("103"); leito5.setLeito("A"); list_leitos.add(leito5);
		leito6.setIdLeito(6); leito6.setQuarto("104"); leito6.setLeito("A"); list_leitos.add(leito6);
		leito7.setIdLeito(7); leito7.setQuarto("104"); leito7.setLeito("B"); list_leitos.add(leito7);
		leito8.setIdLeito(8); leito8.setQuarto("104"); leito8.setLeito("C"); list_leitos.add(leito8);
		leito9.setIdLeito(9); leito9.setQuarto("105"); leito9.setLeito("A"); list_leitos.add(leito9);
		leito10.setIdLeito(10); leito10.setQuarto("105"); leito10.setLeito("B"); list_leitos.add(leito10);
		leito11.setIdLeito(11); leito11.setQuarto("106"); leito11.setLeito("A"); list_leitos.add(leito11);
		leito12.setIdLeito(12); leito12.setQuarto("106"); leito12.setLeito("B"); list_leitos.add(leito12);

		return list_leitos;
	}
}
