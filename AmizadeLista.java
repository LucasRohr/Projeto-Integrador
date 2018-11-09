package com.melodiam.model;

public class AmizadeLista {

	private long idAmizadeLista;
	private Amizade amizade;
	private Lista lista;
	
	public AmizadeLista() {
	}
	
	public AmizadeLista(long idAmizadeLista, Amizade amizade, Lista lista) {
		super();
		this.idAmizadeLista = idAmizadeLista;
		this.amizade = amizade;
		this.lista = lista;
	}
	public long getIdAmizadeLista() {
		return idAmizadeLista;
	}
	public void setIdAmizadeLista(long idAmizadeLista) {
		this.idAmizadeLista = idAmizadeLista;
	}
	public Amizade getAmizade() {
		return amizade;
	}
	public void setAmizade(Amizade amizade) {
		this.amizade = amizade;
	}
	public Lista getLista() {
		return lista;
	}
	public void setLista(Lista lista) {
		this.lista = lista;
	}
	@Override
	public String toString() {
		return "AmizadeLista [idAmizadeLista=" + idAmizadeLista + ", amizade=" + amizade + ", lista=" + lista + "]";
	}
	
	
	
}
