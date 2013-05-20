package br.feevale.droidhospital.adapters;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.db.Aplicacao;
import br.feevale.droidhospital.db.PacienteDescription;
import br.feevale.droidhospital.pojos.AnamneseParent;

public class AnamneseAdapter implements ExpandableListAdapter {

	Context context;

	ArrayList<AnamneseParent> parents;
	private ArrayList<Aplicacao> aplicacoesEfetuadas;
	private ArrayList<Aplicacao> aplicacoesFuturas;

	LayoutInflater inflater;

	private PacienteDescription pacientDescription;

	public AnamneseAdapter(Context context, PacienteDescription pacientDescription) {
		this.context = context;

		parents = new ArrayList<AnamneseParent>();
		parents.add(new AnamneseParent(1, context.getString(R.string.general_info), R.layout.dados_gerais));
		parents.add(new AnamneseParent(2, context.getString(R.string.applications_made), R.layout.dados_gerais));
		parents.add(new AnamneseParent(3, context.getString(R.string.applications_todo), R.layout.dados_gerais));

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		this.pacientDescription = pacientDescription;

		aplicacoesFuturas = pacientDescription.getAplicacoesFuturas();
		aplicacoesEfetuadas = pacientDescription.getAplicacoesEfetuadas();
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
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

		View layout = inflater.inflate(R.layout.grouplayout, null);

		TextView itemName = (TextView) layout.findViewById(R.id.amnese_expandable_group_name);

		itemName.setText(parents.get(groupPosition).getName());

		return layout;
	}



	@Override
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
		}else {
			layout.setBackgroundColor(Color.WHITE);
		}

		return layout;
	}


	private View configureAnamnese() {
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
			return aplicacoesEfetuadas.size();
		case 2:
			return aplicacoesFuturas.size();
		default:
			break;
		}
		return 0;
	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCombinedChildId(long groupId, long childId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getCombinedGroupId(long groupId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}
}