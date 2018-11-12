package com.melodiam.model;

public class AlbumLista {
	
	private long idAlbumLista;
	private Album album;
	private Lista lista;
	
	public AlbumLista(){
	}

	public AlbumLista(long idAlbumLista, Album album, Lista lista) {
		super();
		this.album = album;
		this.lista = lista;
		this.idAlbumLista = idAlbumLista;
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

	public long getIdAlbumLista() {
		return idAlbumLista;
	}

	public void setIdAlbumLista(long idAlbumLista) {
		this.idAlbumLista = idAlbumLista;
	}

	@Override
	public String toString() {
		return "AlbumLista [idAlbumLista=" + idAlbumLista + ", album=" + album + ", lista=" + lista + "]";
	}
	
	
	
}