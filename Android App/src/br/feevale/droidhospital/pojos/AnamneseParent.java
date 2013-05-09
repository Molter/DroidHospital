package br.feevale.droidhospital.pojos;


public class AnamneseParent {
	
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
