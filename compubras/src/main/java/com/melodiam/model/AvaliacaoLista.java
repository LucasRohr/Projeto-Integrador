package com.melodiam.model;

public class AvaliacaoLista extends Avaliacao{
	
	private long idAvaliacaoLista;
	private Lista lista;
	
	public AvaliacaoLista() {
		this.lista = null;
	}


	public AvaliacaoLista(float avaliacao, Usuario autor, long idAvaliacaoLista, Lista lista) {
		super(avaliacao, autor);
		this.idAvaliacaoLista = idAvaliacaoLista;
		this.lista = lista;
	}


	public long getIdAvaliacaoLista() {
		return idAvaliacaoLista;
	}


	public void setIdAvaliacaoLista(long idAvaliacaoLista) {
		this.idAvaliacaoLista = idAvaliacaoLista;
	}


	public Lista getLista() {
		return lista;
	}


	public void setLista(Lista lista) {
		this.lista = lista;
	}
	
	
}
