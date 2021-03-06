package com.melodiam.model;

public abstract class Avaliacao {
	
	private float avaliacao;
	private Usuario autor;
	
	public Avaliacao() {
		this.autor = null;
	}
	
	public Avaliacao(float avaliacao, Usuario autor) {
		super();
		this.avaliacao = avaliacao;
		this.autor = autor;
	}

	public float getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(float avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}
	
	

}
