package com.melodiam.persistencia;

import java.util.List;

import com.melodiam.model.Avaliacao;
import com.melodiam.model.Usuario;


public interface AvaliacaoDAO {
	
	public Avaliacao cadastrarAvaliacao(Avaliacao avaliacao);
	public void editarAvaliacao(Avaliacao avaliacao);
	public void excluirAvaliacao(long id);
	
	public Avaliacao buscarPorId(long id);
	public List<Avaliacao> buscarPorUsuario(long id); 
	

}