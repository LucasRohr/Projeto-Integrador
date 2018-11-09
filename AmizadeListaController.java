package com.melodiam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.melodiam.model.AmizadeLista;
import com.melodiam.persistencia.AmizadeListaDAO;

@Controller
@RequestMapping(path="/amizade-lista/")
public class AmizadeListaController {
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<AmizadeLista> compartilhar(@RequestBody AmizadeLista amizadeLista) {
		AmizadeListaDAO amizadeListaDAO = new AmizadeListaDAO();
		amizadeLista = amizadeListaDAO.compartilhar(amizadeLista);
		return new ResponseEntity<AmizadeLista>(amizadeLista, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "recebidos/{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<List<AmizadeLista>> buscarRecebidosPorUsuario(@PathVariable("id_usuario") long id) {
		AmizadeListaDAO amizadeListaDAO = new AmizadeListaDAO();
		List<AmizadeLista> listaAmizadeLista = amizadeListaDAO.buscarRecebidosPorUsuario(id);
		if(listaAmizadeLista != null) {
			return new ResponseEntity<List<AmizadeLista>>(listaAmizadeLista, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<List<AmizadeLista>>(HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(value = "{id_autor}/{id_leitor}", method = RequestMethod.GET)
	public ResponseEntity<List<AmizadeLista>> buscarPorAutorELeitor(@PathVariable("id_autor") long idAutor, @PathVariable("id_leitor") long idLeitor) {
		AmizadeListaDAO amizadeListaDAO = new AmizadeListaDAO();
		List<AmizadeLista> listaAmizadeLista = amizadeListaDAO.buscarPorAutorELeitor(idAutor, idLeitor);
		if(listaAmizadeLista != null) {
			return new ResponseEntity<List<AmizadeLista>>(listaAmizadeLista, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<List<AmizadeLista>>(HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(value = "compartilhados/{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<List<AmizadeLista>> buscarCompartilhadosPorUsuario(@PathVariable("id_usuario") long id) {
		AmizadeListaDAO amizadeListaDAO = new AmizadeListaDAO();
		List<AmizadeLista> listaAmizadeLista = amizadeListaDAO.buscarCompartilhadosPorUsuario(id);
		if(listaAmizadeLista != null) {
			return new ResponseEntity<List<AmizadeLista>>(listaAmizadeLista, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<List<AmizadeLista>>(HttpStatus.NOT_FOUND);

		}
	}
	
}
