package com.melodiam.model;

public class Lista {

	private long idLista;
	private Usuario autor;
	private String nome, descricao;
	//VER SE AQUI VAI O ARRAYLIST DE ALBUM. NAO VAI
	
	public Lista() {
		this.autor = null;
	}
	
	//IMPLEMENTAR CONSTRUTOR COM METODOS. OK
	
	

	public long getIdLista() {
		return idLista;
	}

	public Lista(long idLista, Usuario autor, String nome, String descricao) {
		super();
		this.idLista = idLista;
		this.autor = autor;
		this.nome = nome;
		this.descricao = descricao;
	}

	public void setIdLista(long idLista) {
		this.idLista = idLista;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
