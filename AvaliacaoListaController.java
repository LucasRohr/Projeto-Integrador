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
import com.melodiam.model.Lista;
import com.melodiam.model.Usuario;
import com.melodiam.persistencia.AvaliacaoListaDAO;

@Controller    
@RequestMapping(path="/avaliacao-lista/") 
public class AvaliacaoListaController {

	private AvaliacaoListaDAO AvaliacaoListaDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<AvaliacaoLista> cadastrarAvaliacao(@RequestBody Avaliacao avaliacao) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		avaliacao = AvaliacaoListaDAO.cadastrarAvaliacao(avaliacao);
		return new ResponseEntity<AvaliacaoLista>((AvaliacaoLista) avaliacao, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "", method = RequestMethod.PUT)
	public ResponseEntity<Void> editarAvaliacao(@RequestBody Avaliacao avaliacao) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		AvaliacaoListaDAO.editarAvaliacao(avaliacao);;
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluirAvaliacao(@PathVariable long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		AvaliacaoListaDAO.excluirAvaliacao(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@RequestMapping(value = "{id_usuario}", method = RequestMethod.GET)
    public ResponseEntity<List<Avaliacao>> buscarPorUsuario(@PathVariable long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		List<Avaliacao> listaAvaliacoesListas = AvaliacaoListaDAO.buscarPorUsuario(id);		
		return new ResponseEntity<List<Avaliacao>>(listaAvaliacoesListas, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<AvaliacaoLista> buscarPorId(@PathVariable long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		AvaliacaoLista avaliacaoLista = AvaliacaoListaDAO.buscarPorId(id);
		if(avaliacaoLista != null) {
			return new ResponseEntity<AvaliacaoLista>(avaliacaoLista, HttpStatus.FOUND);
		}else{		
			return new ResponseEntity<AvaliacaoLista>(HttpStatus.NOT_FOUND);

		}
	}
	
	@RequestMapping(value = "{id_lista}", method = RequestMethod.GET)
	public ResponseEntity<Float> calcularMediaLista(@PathVariable long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		float avaliacaoLista = AvaliacaoListaDAO.calcularMediaLista(id);
		return new ResponseEntity<Float>(avaliacaoLista, HttpStatus.OK);
	}


	
	@RequestMapping(value = "{id_lista}", method = RequestMethod.GET)
	public ResponseEntity<AvaliacaoLista> buscarPorLista(@PathVariable long id) {
		AvaliacaoListaDAO = new AvaliacaoListaDAO();
		AvaliacaoLista avaliacoesLista = (AvaliacaoLista) AvaliacaoListaDAO.buscarPorLista(id);
		if(avaliacoesLista != null) {
			return new ResponseEntity<AvaliacaoLista>(avaliacoesLista, HttpStatus.FOUND);
		}else{
			return new ResponseEntity<AvaliacaoLista>(HttpStatus.NOT_FOUND);

		}
	}
	
	
	
	
}
