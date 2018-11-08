package com.melodiam.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.melodiam.model.Avaliacao;
import com.melodiam.model.AvaliacaoAlbum;
import com.melodiam.persistencia.AvaliacaoAlbumDAO;

@Controller
@RequestMapping(path = "/avaliacao-Album/")
public class AvaliacaoAlbumController {

	private AvaliacaoAlbumDAO AvaliacaoAlbumDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<AvaliacaoAlbum> cadastrarAvaliacao(@RequestBody Avaliacao avaliacao) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		avaliacao = AvaliacaoAlbumDAO.cadastrarAvaliacao(avaliacao);
		return new ResponseEntity<AvaliacaoAlbum>((AvaliacaoAlbum) avaliacao, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarAvaliacao(@RequestBody Avaliacao avaliacao) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		AvaliacaoAlbumDAO.editarAvaliacao(avaliacao);
		;
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirAvaliacao(@PathVariable long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		AvaliacaoAlbumDAO.excluirAvaliacao(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<List<Avaliacao>> buscarPorUsuario(@PathVariable long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		List<Avaliacao> listaAvaliacoesAlbums = AvaliacaoAlbumDAO.buscarPorUsuario(id);
		return new ResponseEntity<List<Avaliacao>>(listaAvaliacoesAlbums, HttpStatus.OK);

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<AvaliacaoAlbum> buscarPorId(@PathVariable long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		AvaliacaoAlbum avaliacaoAlbum = (AvaliacaoAlbum) AvaliacaoAlbumDAO.buscarPorId(id);
		if (avaliacaoAlbum != null) {
			return new ResponseEntity<AvaliacaoAlbum>(avaliacaoAlbum, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<AvaliacaoAlbum>(HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "media/{id_album}", method = RequestMethod.GET)
	public ResponseEntity<Float> calcularMediaAlbum(@PathVariable long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		float avaliacaoAlbum = AvaliacaoAlbumDAO.calcularMedia(id);
		return new ResponseEntity<Float>(avaliacaoAlbum, HttpStatus.OK);
	}

	@RequestMapping(value = "{id_album}", method = RequestMethod.GET)
	public ResponseEntity<AvaliacaoAlbum> buscarPorAlbum(@PathVariable long id) {
		AvaliacaoAlbumDAO = new AvaliacaoAlbumDAO();
		AvaliacaoAlbum avaliacoesAlbum = (AvaliacaoAlbum) AvaliacaoAlbumDAO.buscarPorAlbum(id);
		if (avaliacoesAlbum != null) {
			return new ResponseEntity<AvaliacaoAlbum>(avaliacoesAlbum, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<AvaliacaoAlbum>(HttpStatus.NOT_FOUND);

		}
	}

}