package com.melodiam.model;

public class AvaliacaoAlbum extends Avaliacao{

//	private float avaliacao;
//	private Usuario autor;
//	private long idAvaliacao;
	private Album album;
	
	public AvaliacaoAlbum() {
		this.album = null;
	}
	
	public AvaliacaoAlbum(long idAvaliacao, float avaliacao, Usuario autor, long idAvaliacaoAlbum, Album album) {
		super(idAvaliacao, avaliacao, autor);
		this.album = album;
	}


	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "AvaliacaoAlbum [album=" + album + ", getIdAvaliacao()=" + getIdAvaliacao() + ", getAvaliacao()="
				+ getAvaliacao() + ", getAutor()=" + getAutor() + "]";
	}

	
	
	
	
}