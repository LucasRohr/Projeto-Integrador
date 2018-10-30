package com.melodiam.model;

public class Comentario {

	private long idComentario;
	private Usuario autor;
	private String texto;
	private Album album;
	
	public Comentario() {
		Usuario autor = null;
		Album album = null;
	}
	
	public Comentario(long idComentario, Usuario autor, String texto, Album album) {
		super();
		this.idComentario = idComentario;
		this.autor = autor;
		this.texto = texto;
		this.album = album;
	}

	public long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(long idComentario) {
		this.idComentario = idComentario;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
}
