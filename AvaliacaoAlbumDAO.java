package com.melodiam.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.melodiam.model.Album;
import com.melodiam.model.Avaliacao;
import com.melodiam.model.AvaliacaoAlbum;
import com.melodiam.model.Usuario;

public class AvaliacaoAlbumDAO implements AvaliacaoDAO {

	private ConexaoMysql conexao;

	public AvaliacaoAlbumDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "");
	
	}
	
	public AvaliacaoAlbum cadastrarAvaliacao(Avaliacao avaliacao) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO avaliacao_album VALUES(null, ?, ?, ?);";
			try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setFloat(1, avaliacao.getAvaliacao());
			statement.setLong(2, avaliacao.getAutor().getIdUsuario());
			statement.setLong(3, ((AvaliacaoAlbum) avaliacao).getAlbum().getIdAlbum());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				((AvaliacaoAlbum) avaliacao).setIdAvaliacaoAlbum(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return (AvaliacaoAlbum) avaliacao;
	}

	public void editarAvaliacao(Avaliacao avaliacao){
		this.conexao.abrirConexao();
		String sqlUpdate = "UPDATE avaliacao_album SET avalicao_album=?, id_usuario=?, id_album=? "
				+ "WHERE id_avaliacao_album=?;";

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
					
			statement.setFloat(1, avaliacao.getAvaliacao());
			statement.setLong(2, avaliacao.getAutor().getIdUsuario());
			statement.setLong(3, ((AvaliacaoAlbum) avaliacao).getAlbum().getIdAlbum());
			statement.setLong(4, ((AvaliacaoAlbum) avaliacao).getIdAvaliacaoAlbum());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluirAvaliacao(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlDelete = "DELETE FROM avaliacao_media WHERE id_avaliacao_lista=?;";
		// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
		// SQL À SER EXECUTADO
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public Avaliacao buscarPorId(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INNER JOIN avaliacao_lista ON avaliacao_lista.id_usuario = usuario.id_usuario AND "
				+ "avaliacao_lista.id_album = album.id_album WHERE id_avaliacao_lista =?;";
		PreparedStatement statement;
		Avaliacao avaliacao = null;
		Usuario autor = null;
		Album album = null;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				autor = new Usuario(rs.getLong("id_usuario"), rs.getString("login_usuario"), rs.getString("senha_usuario"));
				album = new Album(rs.getString("id_spotify"), rs.getLong("id_album"));
				
				avaliacao = new AvaliacaoAlbum();
				avaliacao.setAutor(autor);
				avaliacao.setAvaliacao(rs.getFloat("avaliacao_album"));
				((AvaliacaoAlbum) avaliacao).setIdAvaliacaoAlbum(id);
				((AvaliacaoAlbum) avaliacao).setAlbum(album);
				avaliacao.setAutor(autor);
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return avaliacao;
	}

	public List<Avaliacao> buscarPorUsuario(Usuario usuario) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INNER JOIN avaliacao_album ON avaliacao_album.id_album = album.id_album WHERE id_usuario=?;";
		PreparedStatement statement;
		Avaliacao avaliacao = null;
		Album album = null;
		List<Avaliacao> listaAvaliacoes = new ArrayList<Avaliacao>();;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, usuario.getIdUsuario());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// Converter um objeto ResultSet em um objeto Lista
				
				album = new Album(rs.getString("id_spotify"), rs.getLong("id_album"));
				avaliacao = new AvaliacaoAlbum();
				avaliacao.setAvaliacao(rs.getFloat("avaliacao_album"));
				avaliacao.setAutor(usuario);
				((AvaliacaoAlbum) avaliacao).setIdAvaliacaoAlbum(rs.getLong("id_avaliacao_album"));
				((AvaliacaoAlbum) avaliacao).setAlbum(album);
				listaAvaliacoes.add(avaliacao);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAvaliacoes;
	}

	public float calcularMedia(Album album) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = "SELECT AVG(avaliacao_album) FROM avaliacao_album WHERE album.album_id = ?;";
		PreparedStatement statement;
		float media = 0;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setFloat(1, album.getIdAlbum());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				media = rs.getFloat("avaliacao_lista"); //VER O NOME DO PARAMETRO

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return media;
	}

	public List<Avaliacao> buscarPorAlbum(Album album){
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INNER JOIN avaliacao_album ON avaliacao_album.id_usuario = usuario.id_usuario WHERE id_album=?;";
		PreparedStatement statement;
		AvaliacaoAlbum avaliacaoAlbum = null;
		Usuario autor = null;
		List<Avaliacao> listaAvaliacaoAlbums = new ArrayList<Avaliacao>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, album.getIdAlbum());				
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				// Converter um objeto ResultSet em um objeto avaliacaoAlbum
				autor = new Usuario();
				autor.setIdUsuario(rs.getLong("id_usuario"));
				autor.setLogin(rs.getString("login_usuario"));
				autor.setSenha(rs.getString("senha_usuario"));
				avaliacaoAlbum= new AvaliacaoAlbum();
				avaliacaoAlbum.setIdAvaliacaoAlbum(rs.getLong("id_avaliacao_album"));
				avaliacaoAlbum.setAvaliacao(rs.getFloat("avaliacao_album"));
				avaliacaoAlbum.setAutor(autor);
				avaliacaoAlbum.setAlbum(album);
				listaAvaliacaoAlbums.add(avaliacaoAlbum);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAvaliacaoAlbums;
	}
	
}
