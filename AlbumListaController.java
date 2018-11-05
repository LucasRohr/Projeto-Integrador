package com.melodiam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.melodiam.model.AlbumLista;
import com.melodiam.model.Lista;
import com.melodiam.persistencia.AlbumListaDAO;;


public class AlbumListaController {
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<AlbumLista> inserirEmLista(@RequestBody AlbumLista albumLista) {
		AlbumListaDAO albumListaDAO = new AlbumListaDAO();
		albumLista = albumListaDAO.inserirEmLista(albumLista);
		return new ResponseEntity<AlbumLista>(albumLista, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirDaLista(@PathVariable long id) {
		AlbumListaDAO albumListaDAO = new AlbumListaDAO();
		albumListaDAO.excluirDaLista(id);;
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "{id_lista}", method = RequestMethod.GET)
	public ResponseEntity<List<AlbumLista>> buscarPorLista(@PathVariable long id) {
		AlbumListaDAO albumListaDAO = new AlbumListaDAO();
		List<AlbumLista> listaA = albumListaDAO.buscarPorLista(id);
		if(listaA != null) {
			return new ResponseEntity<List<AlbumLista>>(listaA, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<List<AlbumLista>>(HttpStatus.NOT_FOUND);

		}
	}	
	

}
