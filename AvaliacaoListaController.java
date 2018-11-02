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
import com.melodiam.model.Avaliacao;
import com.melodiam.model.AvaliacaoLista;
import com.melodiam.model.Usuario;
import com.melodiam.persistencia.AvaliacaoListaDAO;

@Controller    
@RequestMapping(path="/avaliacao-lista/") 
public class AvaliacaoListaController {

	private AvaliacaoListaDAO AvaliacaoListaDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<AvaliacaoLista> cadastrarAvaliacao(@RequestBody Avaliacao avaliacao) {
		AvaliacaoListaDAO avaliacaoListaDAO = new AvaliacaoListaDAO();
		avaliacao = avaliacaoListaDAO.cadastrarAvaliacao(avaliacao);
		return new ResponseEntity<AvaliacaoLista>((AvaliacaoLista) avaliacao, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarAvaliacao(@RequestBody Avaliacao avaliacao) {
		AvaliacaoListaDAO avaliacaoListaDAO = new AvaliacaoListaDAO();
		avaliacaoListaDAO.editarAvaliacao(avaliacao);;
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirAvaliacao(@PathVariable long id) {
		AvaliacaoListaDAO avaliacaoListaDAO = new AvaliacaoListaDAO();
		avaliacaoListaDAO.excluirAvaliacao(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@RequestMapping(value = "{usuario}", method = RequestMethod.GET)
    public ResponseEntity<List<Avaliacao>> buscarPorUsuario(@PathVariable Usuario usuario) {
		AvaliacaoListaDAO avaliacaoListaDAO = new AvaliacaoListaDAO();
		List<Avaliacao> listaAvaliacaoListas = avaliacaoListaDAO.buscarPorUsuario(usuario);		
		return new ResponseEntity<List<Avaliacao>>(listaAvaliacaoListas, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<AvaliacaoLista> buscarPorId(@PathVariable long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		AvaliacaoLista AvaliacaoLista = AvaliacaoListaDAO.buscarPorId(id);
		if(AvaliacaoLista != null) {
			return new ResponseEntity<AvaliacaoLista>(AvaliacaoLista, HttpStatus.FOUND);
		}else{		
			return new ResponseEntity<AvaliacaoLista>(HttpStatus.NOT_FOUND);

		}
	}

//	@RequestMapping(value = "{id_AvaliacaoLista}", method = RequestMethod.GET)
//	public ResponseEntity<AvaliacaoLista> buscarPorAutor(@PathVariable AvaliacaoLista autor) {
//		AvaliacaoListaDAO = new AvaliacaoListaDAO();
//		AvaliacaoLista AvaliacaoLista = AvaliacaoListaDAO.buscarPorAutor(autor);
//		if(AvaliacaoLista != null) {
//			return new ResponseEntity<AvaliacaoLista>(AvaliacaoLista, HttpStatus.FOUND);
//		}else{
//			return new ResponseEntity<AvaliacaoLista>(HttpStatus.NOT_FOUND);
//
//		}
//	}
//	
//	@RequestMapping(value = "{id_album}", method = RequestMethod.GET)
//	public ResponseEntity<AvaliacaoLista> buscarPorAlbum(@PathVariable Album album) {
//		AvaliacaoListaDAO = new AvaliacaoListaDAO();
//		AvaliacaoLista AvaliacaoLista = AvaliacaoListaDAO.buscarPorAlbum(album);
//		if(AvaliacaoLista != null) {
//			return new ResponseEntity<AvaliacaoLista>(AvaliacaoLista, HttpStatus.FOUND);
//		}else{
//			return new ResponseEntity<AvaliacaoLista>(HttpStatus.NOT_FOUND);
//
//		}
//	}
	

}
