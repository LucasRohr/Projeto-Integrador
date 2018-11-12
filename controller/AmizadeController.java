package com.melodiam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.melodiam.model.Amizade;
import com.melodiam.persistencia.AmizadeDAO;

@Controller    
@RequestMapping(path="/amizade/") 
public class AmizadeController {

	private AmizadeDAO aDAO;
	
	@RequestMapping(value = "solicitar", method = RequestMethod.POST)
	public ResponseEntity<Amizade> solicitarAmizade(@RequestBody Amizade amizade) {
		aDAO = new AmizadeDAO();
		Amizade amizadee = aDAO.solicitarAmizade(amizade);
		return new ResponseEntity<Amizade>(amizadee, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "aceitar/{id_amizade}", method = RequestMethod.PUT)
	public ResponseEntity<Void> aceitarUsuario(@PathVariable("id_amizade") long idAmizade) {
		aDAO = new AmizadeDAO();
		aDAO.aceitarUsuario(idAmizade);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "id/{id_amizade}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarAmizade(@PathVariable("id_amizade") long idAmizade) {
		aDAO = new AmizadeDAO();
		aDAO.deletarAmizade(idAmizade);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<Amizade>> buscarTodasAmizades() {
		aDAO = new AmizadeDAO();
	    List<Amizade> listaAmizade = aDAO.buscarTodasAmizades();
		if(listaAmizade != null) {
			return new ResponseEntity<List<Amizade>>(listaAmizade, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<List<Amizade>>(HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(value = "amigos/{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<List<Amizade>> buscarAmigosPorUsuario(@PathVariable("id_usuario") long idUsuario) {
		aDAO = new AmizadeDAO();
	    List<Amizade> listaAmizade = aDAO.buscarAmigosPorUsuario(idUsuario);
		if(listaAmizade != null) {
			return new ResponseEntity<List<Amizade>>(listaAmizade, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<List<Amizade>>(HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(value = "pendentes/{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<List<Amizade>> buscarPendentesPorUsuario(@PathVariable("id_usuario") long idUsuario) {
		aDAO = new AmizadeDAO();
	    List<Amizade> listaAmizade = aDAO.buscarPendentesPorUsuario(idUsuario);
		if(listaAmizade != null) {
			return new ResponseEntity<List<Amizade>>(listaAmizade, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<List<Amizade>>(HttpStatus.NOT_FOUND);

		}
	}
}
