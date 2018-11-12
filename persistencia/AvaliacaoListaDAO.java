package com.melodiam.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.melodiam.model.Avaliacao;
import com.melodiam.model.AvaliacaoLista;
import com.melodiam.model.Lista;
import com.melodiam.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class AvaliacaoListaDAO implements AvaliacaoDAO {

	private ConexaoMysql conexao;

	public AvaliacaoListaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "root");
	}

	public AvaliacaoLista cadastrarAvaliacao(AvaliacaoLista avaliacao) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO Avaliacao_Lista VALUES(null, ?, ?, ?);";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			
			statement.setFloat(1, avaliacao.getAvaliacao());
			statement.setLong(2, avaliacao.getAutor().getIdUsuario());
			statement.setLong(3, avaliacao.getLista().getIdLista());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				avaliacao.setIdAvaliacao(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return avaliacao;
	}

	public void editarAvaliacao(AvaliacaoLista avaliacao) {
		this.conexao.abrirConexao();
		String sqlUpdate = 
				"UPDATE Avaliacao_Lista SET avaliacao_lista=?, id_usuario=?, id_lista=? " + 
				"WHERE id_avaliacao_lista=?;";

		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);

			statement.setFloat(1, avaliacao.getAvaliacao());
			statement.setLong(2, avaliacao.getAutor().getIdUsuario());
			statement.setLong(3, avaliacao.getLista().getIdLista());
			statement.setLong(4, avaliacao.getIdAvaliacao());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluirAvaliacao(long id) {
		this.conexao.abrirConexao();
		String sqlDelete = "DELETE FROM Avaliacao_Lista WHERE id_avaliacao_lista=?;";
		try {
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public List<AvaliacaoLista> buscarPorUsuario(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = 
				"SELECT autor.id_usuario id_autor, autor.login_usuario login_autor, " + 
				"avaliador.id_usuario id_avaliador, avaliador.login_usuario login_avaliador, " + 
				"l.*, al.* " + 
				"FROM Avaliacao_Lista al " + 
				"INNER JOIN Lista l ON l.id_lista = al.id_lista " + 
				"INNER JOIN Usuario avaliador ON avaliador.id_usuario = al.id_usuario " + 
				"INNER JOIN Usuario autor ON autor.id_usuario = l.id_usuario   " + 
				"WHERE al.id_usuario=?;";
		PreparedStatement statement;
		AvaliacaoLista avaliacaoLista = null;
		Lista lista = null;
		Usuario avaliador = null;
		Usuario autor = null;
		List<AvaliacaoLista> listaAvaliacoes = new ArrayList<AvaliacaoLista>();
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				avaliacaoLista = new AvaliacaoLista();
				autor = new Usuario(rs.getLong("id_autor"), rs.getString("login_autor"), ""); //enviando sem senha
				lista = new Lista(
						rs.getLong("id_lista"), autor, rs.getString("nome_lista"), rs.getString("descricao_lista"));
				avaliador = new Usuario(
						rs.getLong("id_avaliador"), rs.getString("login_avaliador"), ""); //enviando sem senha
				avaliacaoLista.setIdAvaliacao(rs.getLong("id_avaliacao_lista"));
				avaliacaoLista.setAvaliacao(rs.getFloat("avaliacao_lista"));
				avaliacaoLista.setAutor(avaliador);
				avaliacaoLista.setLista(lista);
				listaAvaliacoes.add(avaliacaoLista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAvaliacoes;
	}

	public AvaliacaoLista buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = 
				"SELECT autor.id_usuario id_autor, autor.login_usuario login_autor, " + 
				"avaliador.id_usuario id_avaliador, avaliador.login_usuario login_avaliador, " + 
				"l.*, al.* " + 
				"FROM Avaliacao_Lista al " + 
				"INNER JOIN Lista l ON l.id_lista = al.id_lista " + 
				"INNER JOIN Usuario avaliador ON avaliador.id_usuario = al.id_usuario " + 
				"INNER JOIN Usuario autor ON autor.id_usuario = l.id_usuario   " + 
				"WHERE al.id_avaliacao_lista=?;";
		PreparedStatement statement;
		AvaliacaoLista avaliacao = null;
		Usuario autor = null;
		Usuario avaliador = null;
		Lista lista = null;
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				avaliacao = new AvaliacaoLista();
				autor = new Usuario(rs.getLong("id_autor"), rs.getString("login_autor"), ""); //enviando sem senha
				lista = new Lista(
						rs.getLong("id_lista"), autor, rs.getString("nome_lista"), rs.getString("descricao_lista"));
				avaliador = new Usuario(
						rs.getLong("id_avaliador"), rs.getString("login_avaliador"), ""); //enviando sem senha
				avaliacao.setIdAvaliacao(rs.getLong("id_avaliacao_lista"));
				avaliacao.setAvaliacao(rs.getFloat("avaliacao_lista"));
				avaliacao.setAutor(avaliador);
				avaliacao.setLista(lista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return avaliacao;
	}
	
	public List<AvaliacaoLista> buscarTodasAvaliacoes() {
		this.conexao.abrirConexao();
		String sqlInsert = 
				"SELECT autor.id_usuario id_autor, autor.login_usuario login_autor, " + 
				"avaliador.id_usuario id_avaliador, avaliador.login_usuario login_avaliador, " + 
				"l.*, al.* " + 
				"FROM Avaliacao_Lista al " + 
				"INNER JOIN Lista l ON l.id_lista = al.id_lista " + 
				"INNER JOIN Usuario avaliador ON avaliador.id_usuario = al.id_usuario " + 
				"INNER JOIN Usuario autor ON autor.id_usuario = l.id_usuario;";
		PreparedStatement statement;
		AvaliacaoLista avaliacao = null;
		Usuario autor = null;
		Usuario avaliador = null;
		Lista lista = null;
		List<AvaliacaoLista> listaAvaliacoes = new ArrayList<AvaliacaoLista>();
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				avaliacao = new AvaliacaoLista();
				autor = new Usuario(rs.getLong("id_autor"), rs.getString("login_autor"), ""); //enviando sem senha
				lista = new Lista(
						rs.getLong("id_lista"), autor, rs.getString("nome_lista"), rs.getString("descricao_lista"));
				avaliador = new Usuario(
						rs.getLong("id_avaliador"), rs.getString("login_avaliador"), ""); //enviando sem senha
				avaliacao.setIdAvaliacao(rs.getLong("id_avaliacao_lista"));
				avaliacao.setAvaliacao(rs.getFloat("avaliacao_lista"));
				avaliacao.setAutor(avaliador);
				avaliacao.setLista(lista);
				listaAvaliacoes.add(avaliacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAvaliacoes;
	}

	public float calcularMedia(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT AVG(avaliacao_lista) FROM Avaliacao_Lista WHERE id_lista=?;";
		PreparedStatement statement;
		float media = 0;
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				media = rs.getFloat("AVG(avaliacao_lista)");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return media;
	}

	public List<AvaliacaoLista> buscarPorLista(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = 
				"SELECT autor.id_usuario id_autor, autor.login_usuario login_autor, " + 
				"avaliador.id_usuario id_avaliador, avaliador.login_usuario login_avaliador, " + 
				"l.*, Avaliacao_Lista.* " + 
				"FROM Avaliacao_Lista " + 
				"INNER JOIN Lista l ON l.id_lista = Avaliacao_Lista.id_lista " + 
				"INNER JOIN Usuario avaliador ON avaliador.id_usuario = Avaliacao_Lista.id_usuario " + 
				"INNER JOIN Usuario autor ON autor.id_usuario = l.id_usuario   " + 
				"WHERE Avaliacao_Lista.id_lista=?;";
		PreparedStatement statement;
		AvaliacaoLista avaliacao = null;
		Usuario autor = null;
		Usuario avaliador = null;
		Lista lista = null;
		List<AvaliacaoLista> listaAvaliacoes = new ArrayList<AvaliacaoLista>();
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				autor = new Usuario(rs.getLong("id_autor"), rs.getString("login_autor"), ""); //enviando sem senha
				lista = new Lista(
						rs.getLong("id_lista"), autor, rs.getString("nome_lista"), rs.getString("descricao_lista"));
				avaliador = new Usuario(
						rs.getLong("id_avaliador"), rs.getString("id_avaliador"), ""); //enviando sem senha
				avaliacao = new AvaliacaoLista();
				avaliacao.setIdAvaliacao(rs.getLong("id_avaliacao_lista"));
				avaliacao.setAvaliacao(rs.getFloat("avaliacao_lista"));
				avaliacao.setAutor(avaliador);
				avaliacao.setLista(lista);
				listaAvaliacoes.add(avaliacao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAvaliacoes;
	}

}