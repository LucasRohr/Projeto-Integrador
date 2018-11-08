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
	
	@RequestMapping(value = "solicitar-amizade", method = RequestMethod.POST)
	public ResponseEntity<Amizade> solicitarAmizade(@RequestBody Amizade amizade) {
		aDAO = new AmizadeDAO();
		Amizade amizadee = aDAO.solicitarAmizade(amizade);
		return new ResponseEntity<Amizade>(amizadee, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> aceitarUsuario(@RequestBody long idAmizade) {
		aDAO = new AmizadeDAO();
		aDAO.aceitarUsuario(idAmizade);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id_amizade}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarAmizade(@PathVariable long idAmizade) {
		aDAO = new AmizadeDAO();
		aDAO.deletarAmizade(idAmizade);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
	@RequestMapping(value = "{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<List<Amizade>> buscarAmigosPorUsuario(@PathVariable long idUsuario) {
		aDAO = new AmizadeDAO();
	    List<Amizade> listaAmizade = aDAO.buscarAmigosPorUsuario(idUsuario);
		if(listaAmizade != null) {
			return new ResponseEntity<List<Amizade>>(listaAmizade, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<List<Amizade>>(HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(value = "{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<List<Amizade>> buscarPendentesPorUsuario(@PathVariable long idUsuario) {
		aDAO = new AmizadeDAO();
	    List<Amizade> listaAmizade = aDAO.buscarPendentesPorUsuario(idUsuario);
		if(listaAmizade != null) {
			return new ResponseEntity<List<Amizade>>(listaAmizade, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<List<Amizade>>(HttpStatus.NOT_FOUND);

		}
	}
}
