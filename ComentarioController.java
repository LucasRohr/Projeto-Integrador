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
import com.melodiam.model.Comentario;
import com.melodiam.model.Usuario;
import com.melodiam.persistencia.ComentarioDAO;

@Controller    
@RequestMapping(path="/comentario/") 
public class ComentarioController {

	private ComentarioDAO comentarioDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Comentario> publicar(@RequestBody Comentario comentario) {
		comentarioDAO = new ComentarioDAO();
		comentario = comentarioDAO.publicar(comentario);
		return new ResponseEntity<Comentario>(comentario, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarComentario(@RequestBody Comentario comentario) {
		comentarioDAO = new ComentarioDAO();
		comentarioDAO.editarComentario(comentario);;
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirComentario(@PathVariable long id) {
		comentarioDAO = new ComentarioDAO();
		comentarioDAO.excluirComentario(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Comentario>> buscarTodosComentario() {
		comentarioDAO = new ComentarioDAO();
		List<Comentario> listaComentarios = comentarioDAO.buscarTodosComentarios();		
		return new ResponseEntity<List<Comentario>>(listaComentarios, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Comentario> buscarPorId(@PathVariable long id) {
		comentarioDAO = new ComentarioDAO();
		Comentario comentario = comentarioDAO.buscarPorId(id);
		if(comentario != null) {
			return new ResponseEntity<Comentario>(comentario, HttpStatus.FOUND);
		}else{		
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<Comentario> buscarPorAutor(@PathVariable Usuario autor) {
		comentarioDAO = new ComentarioDAO();
		Comentario comentario = comentarioDAO.buscarPorAutor(autor);
		if(comentario != null) {
			return new ResponseEntity<Comentario>(comentario, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(value = "{id_album}", method = RequestMethod.GET)
	public ResponseEntity<Comentario> buscarPorAutor(@PathVariable Album album) {
		comentarioDAO = new ComentarioDAO();
		Comentario comentario = comentarioDAO.buscarPorAlbum(album);
		if(comentario != null) {
			return new ResponseEntity<Comentario>(comentario, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<Comentario>(HttpStatus.NOT_FOUND);

		}
	}
	
	

}
