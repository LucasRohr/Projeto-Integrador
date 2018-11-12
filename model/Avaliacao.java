package com.melodiam.model;

public class Avaliacao {
	
	private long idAvaliacao;
	private float avaliacao;
	private Usuario autor;
	
	public Avaliacao() {
		this.autor = null;
	}
	
	public Avaliacao(long idAvaliacao, float avaliacao, Usuario autor) {
		super();
		this.idAvaliacao = idAvaliacao;
		this.avaliacao = avaliacao;
		this.autor = autor;
	}
	
	public long getIdAvaliacao() {
		return idAvaliacao;
	}

	public void setIdAvaliacao(long idAvaliacao) {
		this.idAvaliacao = idAvaliacao;
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