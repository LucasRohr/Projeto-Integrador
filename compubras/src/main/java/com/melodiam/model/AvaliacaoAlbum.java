package com.melodiam.model;

public class AvaliacaoAlbum extends Avaliacao{

	private long idAvaliacaoAlbum;
	private Album album;
	
	public AvaliacaoAlbum() {
		this.album = null;
	}
	
	public AvaliacaoAlbum(float avaliacao, Usuario autor, long idAvaliacaoAlbum, Album album) {
		super(avaliacao, autor);
		this.idAvaliacaoAlbum = idAvaliacaoAlbum;
		this.album = album;
	}

	public long getIdAvaliacaoAlbum() {
		return idAvaliacaoAlbum;
	}

	public void setIdAvaliacaoAlbum(long idAvaliacaoAlbum) {
		this.idAvaliacaoAlbum = idAvaliacaoAlbum;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
	
}
