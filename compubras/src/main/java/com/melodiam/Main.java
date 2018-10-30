package com.melodiam;

import java.util.ArrayList;

import com.melodiam.model.Album;
import com.melodiam.model.Usuario;
import com.melodiam.persistencia.AlbumDAO;
import com.melodiam.persistencia.UsuarioDAO;

public class Main {
	public static void main(String[] args) {
		Usuario user = new Usuario();
		user.setLogin("skyrider22");
		user.setSenha("12345");
		UsuarioDAO uDAO = new UsuarioDAO(); 	
		//uDAO.cadastrar(user);
		
		//System.out.println(uDAO.buscarPorId(1).getLogin());
		
		//uDAO.editar(user);
		
		//System.out.println(uDAO.buscarPorId(1).getLogin());
		
		
		/*
		ArrayList<Usuario> lista = (ArrayList<Usuario>) uDAO.buscarTodos();
		for(int i = 0; i < lista.size(); i++){
			System.out.println(lista.get(i).getLogin());
		}
		
		System.out.println(uDAO.buscarPorLogin("skyrider22").getIdUsuario());
		
		*/
		
		Album al = new Album();
		al.setIdSpotify("umIdRandom");
		
		AlbumDAO alDAO = new AlbumDAO();
		
		alDAO.cadastrarAlbum(al);
		
	}

}
