package com.melodiam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.melodiam.model.Album;
import com.melodiam.persistencia.AlbumDAO;

@Controller    
@RequestMapping(path="/album/") 
public class AlbumController {

	private AlbumDAO albumDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Album> cadastrarAlbum(@RequestBody Album album) {
		albumDAO = new AlbumDAO();
		// System.out.println("Album:" + album.getIdSpotify());
		album = albumDAO.cadastrarAlbum(album);
		return new ResponseEntity<Album>(album, HttpStatus.CREATED);
	}
	
	
//	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<Void> excluir(@PathVariable long id) {
//		albumDAO = new AlbumDAO();
//		albumDAO.excluirAlbum(id);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Album>> buscarTodosAlbuns() {
		albumDAO = new AlbumDAO();
		List<Album> listaAlbuns = albumDAO.buscarTodosAlbuns();		
		return new ResponseEntity<List<Album>>(listaAlbuns, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Album> buscarPorId(@PathVariable long id) {
		albumDAO = new AlbumDAO();
		Album album = albumDAO.buscarPorId(id);
		if(album != null) {
			return new ResponseEntity<Album>(album, HttpStatus.FOUND);
		}else{		
			return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "{id_spotify}", method = RequestMethod.GET)
	public ResponseEntity<Album> buscarPorIdSpotify(@PathVariable String idSpotify) {
		albumDAO = new AlbumDAO();
		Album album = albumDAO.buscarPorIdSpotify(idSpotify);
		if(album != null) {
			return new ResponseEntity<Album>(album, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<Album>(HttpStatus.NOT_FOUND);

		}
	}
	
	

}
