package br.feevale.droidhospital.adapters;

import java.text.DateFormat;
import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
<<<<<<< HEAD
import android.text.format.Formatter;
=======
=======
import java.util.Random;

import android.content.Context;
import android.database.DataSetObserver;
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import br.feevale.droidhospital.R;
<<<<<<< HEAD
import br.feevale.droidhospital.db.Aplicacao;
import br.feevale.droidhospital.db.PacienteDescription;
import br.feevale.droidhospital.pojos.AnamneseParent;
import br.feevale.droidhospital.pojos.Aplicacoes;

public class AnamneseAdapter implements ExpandableListAdapter {

	Context context;

	ArrayList<AnamneseParent> parents;
	LayoutInflater inflater;

	private PacienteDescription pacientDescription;
	
	Aplicacoes aplicacoes;

	public AnamneseAdapter(Context context, PacienteDescription pacientDescription) {
		this.context = context;
		parents = AnamneseParent.anamneseParents;
		Toast.makeText(context,String.valueOf( parents.size()), Toast.LENGTH_LONG).show();
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.pacientDescription = pacientDescription;
<<<<<<< HEAD
		//Ramdow Aplicacoes
		aplicacoes = new Aplicacoes();
=======
		
		this.aplicacoesEfetuadas = this.pacientDescription.getAplicacoesEfetuadas();
		this.aplicacoesFuturas = this.pacientDescription.getAplicacoesFuturas();
		
=======
import br.feevale.droidhospital.db.PacienteDescription;
import br.feevale.droidhospital.pojos.AnamneseParent;
import br.feevale.droidhospital.pojos.Aplicacoes;

public class AnamneseAdapter implements ExpandableListAdapter {

	Context context;

	ArrayList<AnamneseParent> parents;
	LayoutInflater inflater;

	private PacienteDescription pacientDescription;
	
	Aplicacoes aplicacoes;

	public AnamneseAdapter(Context context, PacienteDescription pacientDescription) {
		this.context = context;
		parents = AnamneseParent.anamneseParents;
		Toast.makeText(context,String.valueOf( parents.size()), Toast.LENGTH_LONG).show();
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.pacientDescription = pacientDescription;
		//Ramdow Aplicacoes
		aplicacoes = new Aplicacoes();
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	@Override
	public long getCombinedGroupId(long groupId) {
		return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return parents.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return parents.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return parents.get(groupPosition).getId();
	}

	@Override
<<<<<<< HEAD
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		View layout = inflater.inflate(R.layout.grouplayout, null);

		TextView itemName = (TextView) layout
				.findViewById(R.id.amnese_expandable_group_name);
=======
<<<<<<< HEAD
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		View layout = inflater.inflate(R.layout.grouplayout, null);

		TextView itemName = (TextView) layout.findViewById(R.id.amnese_expandable_group_name);
=======
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		View layout = inflater.inflate(R.layout.grouplayout, null);

		TextView itemName = (TextView) layout
				.findViewById(R.id.amnese_expandable_group_name);
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc

		itemName.setText(parents.get(groupPosition).getName());

		return layout;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {

	}

	@Override
	public void onGroupExpanded(int groupPosition) {

	}

	// child

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

<<<<<<< HEAD
=======
	@Override
<<<<<<< HEAD
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

		switch (groupPosition) {

			case 0:
				return configureAnamnese();
			case 1:
				return aplicacoesView(childPosition, false);
			case 2:
				return aplicacoesView(childPosition, true);
			default:
				return null;
		}
	}

>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
	private View aplicacoesView(int childPosition, boolean futura) {
		Aplicacao aplicacao;
		
		View layout = inflater.inflate(R.layout.aplicacoes, null);
		
		Date dataAplicacao;
		if(futura){
			aplicacao = aplicacoesFuturas.get( childPosition );
			dataAplicacao = aplicacao.getHoraPrevisto();
		}else{
			aplicacao = aplicacoesEfetuadas.get( childPosition );
			dataAplicacao = aplicacao.getHoraAplicado();
		}
		
		String myDateString = DateFormat.getDateInstance().format(dataAplicacao);
		
		TextView data = (TextView) layout.findViewById(R.id.aplicacaoes_data);
		data.setText(myDateString);
		
		TextView horaTextView = (TextView) layout.findViewById(R.id.aplicacaoes_horario);
		
		StringBuilder horaString = new StringBuilder();
		Calendar c = Calendar.getInstance();
		c.setTime(dataAplicacao);
		
		Formatter hourFormatter = new Formatter();
		//Toast.makeText(context, String.valueOf(c.get(Calendar.HOUR_OF_DAY)), Toast.LENGTH_LONG).show();
		
		hourFormatter.format("%02d", c.get(Calendar.HOUR_OF_DAY));
		horaString.append(hourFormatter.toString());
		
		horaString.append(":");
		
		Formatter minuteFormatter = new Formatter();
		minuteFormatter.format("%02d", c.get(Calendar.MINUTE));
		horaString.append(minuteFormatter.toString());
		
		horaTextView.setText(horaString.toString());

		TextView nome = (TextView) layout.findViewById(R.id.aplicacoes_nome);
		String nomeMedicamento = aplicacoesFuturas.get( childPosition ).getNomeMedicamento() + " " + aplicacoesFuturas.get( childPosition ).getConcentracaoMedicamento();
		nome.setText(nomeMedicamento);

		if(childPosition % 2 == 0) {
			layout.setBackgroundColor(Color.GRAY);
		}else{
			layout.setBackgroundColor(Color.WHITE);
		}
	}
	
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		switch (groupPosition) {

		case 0:
			return configureAnamnese();
		case 1:
			return aplicacoesEfetuadasView(childPosition);
		case 2:
			return aplicacoesEfetuadasView(childPosition);
			//return aplicacoesFuturasView(childPosition);
		default:
			return null;
		}
	}

	private View aplicacoesFuturasView(int childPosition) {
		// aplicacoes
		Random r = new Random();
		int x = r.nextInt(aplicacoes.countAplicacaoesFuturas());
		
<<<<<<< HEAD
=======
=======
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		switch (groupPosition) {

		case 0:
			return configureAnamnese();
		case 1:
			return aplicacoesEfetuadasView(childPosition);
		case 2:
			return aplicacoesEfetuadasView(childPosition);
			//return aplicacoesFuturasView(childPosition);
		default:
			return null;
		}
	}

	private View aplicacoesFuturasView(int childPosition) {
		// aplicacoes
		Random r = new Random();
		int x = r.nextInt(aplicacoes.countAplicacaoesFuturas());
		
		View layout = inflater.inflate(R.layout.aplicacoes, null);
		TextView data = (TextView) layout.findViewById(R.id.aplicacaoes_data);
		data.setText(aplicacoes.getAplicacoes().get(x).getDataHoraAplicacao());

		TextView nome = (TextView) layout.findViewById(R.id.aplicacoes_nome);
		nome.setText(aplicacoes.getAplicacoes().get(x).getNomeMedicamento());

		TextView dosagem = (TextView) layout
				.findViewById(R.id.aplicacoes_dosagem);
		dosagem.setText(aplicacoes.getAplicacoes().get(x).getDosagem());

		return layout;
	}

	private View aplicacoesEfetuadasView(int childPosition) {
		Random r = new Random();
		int totalAplicacoes = aplicacoes.getAplicacoes().size();
		
		int x = r.nextInt(totalAplicacoes);
		
		if(x >= totalAplicacoes){
			x = 2;
		}

>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
		View layout = inflater.inflate(R.layout.aplicacoes, null);
		TextView data = (TextView) layout.findViewById(R.id.aplicacaoes_data);
		data.setText(aplicacoes.getAplicacoes().get(x).getDataHoraAplicacao());

		TextView nome = (TextView) layout.findViewById(R.id.aplicacoes_nome);
		nome.setText(aplicacoes.getAplicacoes().get(x).getNomeMedicamento());

		TextView dosagem = (TextView) layout
				.findViewById(R.id.aplicacoes_dosagem);
		dosagem.setText(aplicacoes.getAplicacoes().get(x).getDosagem());

<<<<<<< HEAD
		return layout;
	}

	private View aplicacoesEfetuadasView(int childPosition) {
		Random r = new Random();
		int totalAplicacoes = aplicacoes.getAplicacoes().size();
		
		int x = r.nextInt(totalAplicacoes);
		
		if(x >= totalAplicacoes){
			x = 2;
		}

		View layout = inflater.inflate(R.layout.aplicacoes, null);
		TextView data = (TextView) layout.findViewById(R.id.aplicacaoes_data);
		data.setText(aplicacoes.getAplicacoes().get(x).getDataHoraAplicacao());

		TextView nome = (TextView) layout.findViewById(R.id.aplicacoes_nome);
		nome.setText(aplicacoes.getAplicacoes().get(x).getNomeMedicamento());

		TextView dosagem = (TextView) layout
				.findViewById(R.id.aplicacoes_dosagem);
		dosagem.setText(aplicacoes.getAplicacoes().get(x).getDosagem());

		return layout;
	}

	private View configureAnamnese() {
=======
>>>>>>> Web Services Sync Tasks
		return layout;
	}

	private View configureAnamnese() {
<<<<<<< HEAD

=======
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
		View layout = inflater.inflate(R.layout.dados_gerais, null);

		TextView idadeTextView = (TextView) layout.findViewById(R.id.dados_gerais_data_entrada);
		String myDate = DateFormat.getDateInstance().format(pacientDescription.getDataEntrada());

		idadeTextView.setText(myDate);

		TextView idadeExtensoTextView = (TextView) layout.findViewById(R.id.dados_gerais_idade_extenso);
		idadeExtensoTextView.setText(pacientDescription.getIdade());

		TextView pesoTextView = (TextView) layout.findViewById(R.id.dados_gerais_peso);
		
		String pesoString = String.valueOf(pacientDescription.getPeso())+ context.getString(R.string.wheight_measure);
		pesoTextView.setText(pesoString);

		TextView alergiasTextView = (TextView) layout.findViewById(R.id.dados_gerais_alergias);
		alergiasTextView.setText(pacientDescription.getAlergias());

		TextView fumanteTextView = (TextView) layout.findViewById(R.id.dados_gerais_fumante);
		String isSmoker;
		
		if (pacientDescription.getFumante().equalsIgnoreCase("S")) {
			isSmoker = context.getString(R.string.yes);
		} else {
			isSmoker = context.getString(R.string.no);
		}

		fumanteTextView.setText(isSmoker);

		return layout;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		
		switch (groupPosition) {
		case 0:
			return 1;
		case 1:
<<<<<<< HEAD
			return aplicacoesEfetuadas.size();

		case 2:
			return aplicacoesFuturas.size();
<<<<<<< HEAD
=======
=======
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
			return aplicacoes.countAplicacaoesEfetuadas();

		case 2:
			return aplicacoes.countAplicacaoesFuturas();
<<<<<<< HEAD
=======
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc

		default:
			break;
		}
		return 0;
	}

	@Override
	public long getCombinedChildId(long groupId, long childId) {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
<<<<<<< HEAD
=======
		// TODO Auto-generated method stub
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
		return 0;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {

	}
<<<<<<< HEAD
}
=======
<<<<<<< HEAD
}
=======
}
>>>>>>> Web Services Sync Tasks
>>>>>>> c07e21a29df35e41a9f0124f4b8c9cf74f2281dc
