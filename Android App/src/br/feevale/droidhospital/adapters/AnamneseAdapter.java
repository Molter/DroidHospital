package br.feevale.droidhospital.adapters;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;
import br.feevale.droidhospital.R;
import br.feevale.droidhospital.pojos.AnamneseParent;
import br.feevale.droidhospital.pojos.Aplicacoes;
import br.feevale.droidhospital.pojos.Paciente;

public class AnamneseAdapter implements ExpandableListAdapter {

	Context context;

	ArrayList<AnamneseParent> parents;
	LayoutInflater inflater;

	private Paciente paciente;
	Aplicacoes aplicacoes;

	public AnamneseAdapter(Context context, long pacienteId) {
		this.context = context;
		parents = AnamneseParent.anamneseParents;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		paciente = Paciente.getPacienteById(pacienteId);

		aplicacoes = new Aplicacoes(pacienteId);
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
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		View layout = inflater.inflate(R.layout.grouplayout, null);

		TextView itemName = (TextView) layout
				.findViewById(R.id.amnese_expandable_group_name);

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

	@Override
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
		int x = r.nextInt(paciente.getPacientes().size());

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
		View layout = inflater.inflate(R.layout.dados_gerais, null);

		TextView idadeTextView = (TextView) layout
				.findViewById(R.id.dados_gerais_data_entrada);
		idadeTextView.setText(String.valueOf(paciente.getDataDeInternacao()));

		TextView idadeExtensoTextView = (TextView) layout
				.findViewById(R.id.dados_gerais_idade_extenso);
		idadeExtensoTextView.setText(paciente.getIdade());

		TextView pesoTextView = (TextView) layout
				.findViewById(R.id.dados_gerais_peso);
		String pesoString = String.valueOf(paciente.getPeso())
				+ context.getString(R.string.wheight_measure);
		pesoTextView.setText(pesoString);

		TextView alergiasTextView = (TextView) layout
				.findViewById(R.id.dados_gerais_alergias);
		alergiasTextView.setText(paciente.getAlergias());

		TextView fumanteTextView = (TextView) layout
				.findViewById(R.id.dados_gerais_fumante);
		String isSmoker;
		if (paciente.isFumante()) {
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
			return aplicacoes.countAplicacaoesEfetuadas();

		case 2:
			return aplicacoes.countAplicacaoesFuturas();

		default:
			break;
		}
		return 0;
	}

	@Override
	public long getCombinedChildId(long groupId, long childId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {

	}
}
