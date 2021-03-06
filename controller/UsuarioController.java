package com.melodiam.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.melodiam.model.Usuario;
import com.melodiam.persistencia.UsuarioDAO;

@Controller    
@RequestMapping(path="/usuario/") 
public class UsuarioController {

	private UsuarioDAO uDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
		uDAO = new UsuarioDAO();
		System.out.println("NOME:"+usuario.getLogin());
		usuario = uDAO.cadastrarUsuario(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarUsuario(@RequestBody Usuario usuario) {
		uDAO = new UsuarioDAO();
		uDAO.editarUsuario(usuario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirUsuario(@PathVariable long id) {
		uDAO = new UsuarioDAO();
		uDAO.excluirUsuario(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> listaTodosUsuarios() {
		uDAO = new UsuarioDAO();
		List<Usuario> listaUsuarios = uDAO.buscarTodos();		
		return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorId(@PathVariable long id) {
		uDAO = new UsuarioDAO();
		Usuario usuario = uDAO.buscarPorId(id);
		if(usuario!=null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.FOUND);
		}else{		
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "login/{login}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarPorLogin(@PathVariable String login) {
		uDAO = new UsuarioDAO();
		Usuario usuario = uDAO.buscarPorLogin(login);
		if(usuario!=null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);

		}
	}
}
