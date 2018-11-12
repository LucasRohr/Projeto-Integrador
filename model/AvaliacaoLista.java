package com.melodiam.model;

public class AvaliacaoLista extends Avaliacao{
	
	private Lista lista;
	
	public AvaliacaoLista() {
		this.lista = null;
	}


	public AvaliacaoLista(long idAvaliacao, float avaliacao, Usuario autor, long idAvaliacaoLista, Lista lista) {
		super(idAvaliacao, avaliacao, autor);
		this.lista = lista;
	}

	public Lista getLista() {
		return lista;
	}


	public void setLista(Lista lista) {
		this.lista = lista;
	}


	@Override
	public String toString() {
		return "AvaliacaoLista [lista=" + lista + ", getIdAvaliacao()=" + getIdAvaliacao() + ", getAvaliacao()="
				+ getAvaliacao() + ", getAutor()=" + getAutor() + "]";
	}


	
	
	
	
}