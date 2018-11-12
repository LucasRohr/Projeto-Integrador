package com.melodiam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//import com.melodiam.model.Avaliacao;
import com.melodiam.model.AvaliacaoAlbum;
import com.melodiam.persistencia.AvaliacaoAlbumDAO;

@Controller
@RequestMapping(path = "/avaliacao-album/")
public class AvaliacaoAlbumController {

	private AvaliacaoAlbumDAO AvaliacaoAlbumDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<AvaliacaoAlbum> cadastrarAvaliacao(@RequestBody AvaliacaoAlbum avaliacao) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		avaliacao = AvaliacaoAlbumDAO.cadastrarAvaliacao(avaliacao);
		return new ResponseEntity<AvaliacaoAlbum>(avaliacao, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarAvaliacao(@RequestBody AvaliacaoAlbum avaliacao) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		AvaliacaoAlbumDAO.editarAvaliacao(avaliacao);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirAvaliacao(@PathVariable("id") long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		AvaliacaoAlbumDAO.excluirAvaliacao(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "usuario/{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoAlbum>> buscarPorUsuario(@PathVariable("id_usuario") long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		List<AvaliacaoAlbum> listaAvaliacoes = AvaliacaoAlbumDAO.buscarPorUsuario(id);
		return new ResponseEntity<List<AvaliacaoAlbum>>(listaAvaliacoes, HttpStatus.OK);

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<AvaliacaoAlbum> buscarPorId(@PathVariable("id") long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		AvaliacaoAlbum avaliacao = AvaliacaoAlbumDAO.buscarPorId(id);
		if (avaliacao != null) {
			return new ResponseEntity<AvaliacaoAlbum>(avaliacao, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<AvaliacaoAlbum>(HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoAlbum>> buscarTodasAvaliacoes() {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		List<AvaliacaoAlbum> listaAvaliacoes = AvaliacaoAlbumDAO.buscarTodasAvaliacoes();
		if (listaAvaliacoes != null) {
			return new ResponseEntity<List<AvaliacaoAlbum>>(listaAvaliacoes, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<AvaliacaoAlbum>>(HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "media/{id_album}", method = RequestMethod.GET)
	public ResponseEntity<Float> calcularMediaAlbum(@PathVariable("id_album") long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		float media = AvaliacaoAlbumDAO.calcularMedia(id);
		return new ResponseEntity<Float>(media, HttpStatus.OK);
	}

	@RequestMapping(value = "album/{id_album}", method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoAlbum>> buscarPorAlbum(@PathVariable("id_album") long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		List<AvaliacaoAlbum> listaAvaliacoes = AvaliacaoAlbumDAO.buscarPorAlbum(id);
		if (listaAvaliacoes != null) {
			return new ResponseEntity<List<AvaliacaoAlbum>>(listaAvaliacoes, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<AvaliacaoAlbum>>(HttpStatus.NOT_FOUND);

		}
	}

}