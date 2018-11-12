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
import com.melodiam.model.AvaliacaoLista;
import com.melodiam.persistencia.AvaliacaoListaDAO;

@Controller
@RequestMapping(path = "/avaliacao-lista/")
public class AvaliacaoListaController {

	private AvaliacaoListaDAO AvaliacaoListaDAO;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<AvaliacaoLista> cadastrarAvaliacao(@RequestBody AvaliacaoLista avaliacao) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		avaliacao = AvaliacaoListaDAO.cadastrarAvaliacao(avaliacao);
		return new ResponseEntity<AvaliacaoLista>(avaliacao, HttpStatus.CREATED);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarAvaliacao(@RequestBody AvaliacaoLista avaliacao) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		AvaliacaoListaDAO.editarAvaliacao(avaliacao);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirAvaliacao(@PathVariable("id") long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		AvaliacaoListaDAO.excluirAvaliacao(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "usuario/{id_usuario}", method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoLista>> buscarPorUsuario(@PathVariable("id_usuario") long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		List<AvaliacaoLista> listaAvaliacoes = AvaliacaoListaDAO.buscarPorUsuario(id);
		return new ResponseEntity<List<AvaliacaoLista>>(listaAvaliacoes, HttpStatus.OK);

	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<AvaliacaoLista> buscarPorId(@PathVariable("id") long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		AvaliacaoLista avaliacao = AvaliacaoListaDAO.buscarPorId(id);
		if (avaliacao != null) {
			return new ResponseEntity<AvaliacaoLista>(avaliacao, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<AvaliacaoLista>(HttpStatus.NOT_FOUND);

		}
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoLista>> buscarTodasAvaliacoes() {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		List<AvaliacaoLista> listaAvaliacoes = AvaliacaoListaDAO.buscarTodasAvaliacoes();
		if (listaAvaliacoes != null) {
			return new ResponseEntity<List<AvaliacaoLista>>(listaAvaliacoes, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<AvaliacaoLista>>(HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(value = "media/{id_lista}", method = RequestMethod.GET)
	public ResponseEntity<Float> calcularMediaLista(@PathVariable("id_lista") long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		float media = AvaliacaoListaDAO.calcularMedia(id);
		return new ResponseEntity<Float>(media, HttpStatus.OK);
	}

	@RequestMapping(value = "lista/{id_lista}", method = RequestMethod.GET)
	public ResponseEntity<List<AvaliacaoLista>> buscarPorLista(@PathVariable("id_lista") long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		List<AvaliacaoLista> listaAvaliacoes = AvaliacaoListaDAO.buscarPorLista(id);
		if (listaAvaliacoes != null) {
			return new ResponseEntity<List<AvaliacaoLista>>(listaAvaliacoes, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<List<AvaliacaoLista>>(HttpStatus.NOT_FOUND);

		}
	}

}