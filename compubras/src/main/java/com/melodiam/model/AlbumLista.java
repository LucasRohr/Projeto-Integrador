package com.melodiam.model;

public class AlbumLista {
	
	private Album album;
	private Lista lista;
	private long id;
	
	public AlbumLista(){
	}

	public AlbumLista(Album album, Lista lista, long id) {
		super();
		this.album = album;
		this.lista = lista;
		this.id = id;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
