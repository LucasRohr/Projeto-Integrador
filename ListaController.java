package com.melodiam.controller;

 import java.util.List;

 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.PathVariable;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;

 import com.melodiam.model.Lista;
 import com.melodiam.model.Usuario;
 import com.melodiam.persistencia.ListaDAO;

 @Controller
 @RequestMapping(path="/lista/")
 public class ListaController {
 	
 	private ListaDAO lDAO;
 	
 	@RequestMapping(value = "", method = RequestMethod.POST)
 	public ResponseEntity<Lista> cadastrarLista(@RequestBody Lista lista) {
 		lDAO = new ListaDAO();
 		// System.out.println("Lista:" + lista.getIdSpotify());
 		lista = lDAO.cadastrarLista(lista);
 		return new ResponseEntity<Lista>(lista, HttpStatus.CREATED);
 	}
 	
 	public ResponseEntity<Void> editarLista(@RequestBody Lista lista) {
 		lDAO = new ListaDAO();
 		// System.out.println("Lista:" + lista.getIdSpotify());
 		lDAO.editarLista(lista);
 		return new ResponseEntity<Void>(HttpStatus.CREATED);
 	}
 	
 	
 	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
 	public ResponseEntity<Void> excluir(@PathVariable long id) {
 		lDAO = new ListaDAO();
 		lDAO.excluirLista(id);
 		return new ResponseEntity<Void>(HttpStatus.OK);
 	}	
 	
 	@RequestMapping(value = "", method = RequestMethod.GET)
     public ResponseEntity<List<Lista>> buscarTodasListas() {
 		lDAO = new ListaDAO();
 		List<Lista> listaAlbuns = lDAO.buscarTodasListas();		
 		return new ResponseEntity<List<Lista>>(listaAlbuns, HttpStatus.OK);
 		
 	}
 	
 	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
 	public ResponseEntity<Lista> buscarPorId(@PathVariable long id) {
 		lDAO = new ListaDAO();
 		Lista lista = lDAO.buscarPorId(id);
 		if(lista != null) {
 			return new ResponseEntity<Lista>(lista, HttpStatus.FOUND);
 		}else{		
 			return new ResponseEntity<Lista>(HttpStatus.NOT_FOUND);

 		}
 	}

 	@RequestMapping(value = "autor/{id_usuario}", method = RequestMethod.GET)
 	public ResponseEntity<List<Lista>> buscarPorAutor(@PathVariable("id_usuario") long id) {
 		lDAO = new ListaDAO();
 		List<Lista> listaListas = lDAO.buscarPorAutor(id);
 		if(listaListas != null) {
 			return new ResponseEntity<List<Lista>>(listaListas, HttpStatus.FOUND);
 		}else{
 			return new ResponseEntity<List<Lista>>(HttpStatus.NOT_FOUND);

 		}
 	}
 	
 	@RequestMapping(value = "listas/{id_usuario}", method = RequestMethod.GET)
 	public ResponseEntity<Float> retornarNumeroListas(@PathVariable("id_usuario") long idUsuario) {
 		float numeroListas;
 		ListaDAO lDAO = new ListaDAO();
 		numeroListas = lDAO.retornarNumeroListas(idUsuario);
 		return new ResponseEntity<Float>(numeroListas, HttpStatus.FOUND); 		
 	}
 	
 }