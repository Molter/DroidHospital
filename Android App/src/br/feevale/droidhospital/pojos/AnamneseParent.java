package br.feevale.droidhospital.pojos;

import java.util.ArrayList;

import br.feevale.droidhospital.R;

public class AnamneseParent {
	
	public static ArrayList<AnamneseParent> anamneseParents = new ArrayList<AnamneseParent>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			add(new AnamneseParent(1,"Dados Gerais", R.layout.dados_gerais));
			add(new AnamneseParent(2,"Aplica��es Efetuadas", R.layout.dados_gerais));
			add(new AnamneseParent(3,"Aplica��es Futuras", R.layout.dados_gerais));
		}
	};
	
	
	private long id;
	private String name;
	private int layout;
	
	public AnamneseParent(long id, String name, int layout) {
		this.setId(id);
		this.setName(name);
		this.setLayout(layout);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLayout() {
		return layout;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}
	
}
