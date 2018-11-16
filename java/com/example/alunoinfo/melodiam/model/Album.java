package com.example.alunoinfo.melodiam.model;

import java.io.Serializable;

public class Album implements Serializable {

	private String idSpotify;
	private long idAlbum;
	
	public Album() {
		
	}
	
	public Album(String idSpotify, long idAlbum) {
		this.idSpotify = idSpotify;
		this.idAlbum = idAlbum;
	}

	public String getIdSpotify() {
		return idSpotify;
	}

	public void setIdSpotify(String idSpotify) {
		this.idSpotify = idSpotify;
	}

	public long getIdAlbum() {
		return idAlbum;
	}

	public void setIdAlbum(long idAlbum) {
		this.idAlbum = idAlbum;
	}

	@Override
	public String toString() {
		return "Album [idSpotify=" + idSpotify + ", idAlbum=" + idAlbum + "]";
	}
	
	
	
	
}