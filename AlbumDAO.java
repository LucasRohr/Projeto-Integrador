package com.melodiam.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.melodiam.model.Album;

public class AlbumDAO {

	private ConexaoMysql conexao;

	public AlbumDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "ifsul2017");
	}

	// INSERT INTO album VALUES(null, 'Rodrigo', 'remor', '123');
	public Album cadastrarAlbum(Album album) {
		// ABRIR A CONEX�O COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO Album VALUES(?, null);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO

			statement.setString(1, album.getIdSpotify());
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				// PEGA O ID
				album.setIdAlbum(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return album;
	}

	/*
	// DELETE FROM album WHERE id_album=3;
	public void excluirAlbum(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlDelete = "DELETE FROM album WHERE id_album=?;";
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
	}*/

	// SELECT * FROM album;
	public List<Album> buscarTodosAlbuns() {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlSelect = "SELECT * FROM Album;";
		PreparedStatement statement;
		Album album = null;
		List<Album> listaAlbums = new ArrayList<Album>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				// Converter um objeto ResultSet em um objeto album
				album = new Album();
				album.setIdSpotify(rs.getString("id_spotify"));
				album.setIdAlbum(rs.getLong("id_album"));
				listaAlbums.add(album);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAlbums;
	}

	// SELECT * FROM album WHERE id_album=2;
	public Album buscarPorId(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = "SELECT * FROM Album WHERE id_album=?;";
		PreparedStatement statement;
		Album album = null;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				// Converter um objeto ResultSet em um objeto album
				album = new Album();
				album.setIdSpotify(rs.getString("id_spotify"));
				album.setIdAlbum(rs.getLong("id_album"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return album;
	}

	// SELECT * FROM album WHERE id_album=2;
	public Album buscarPorIdSpotify(String idSpotify) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = "SELECT * FROM Album WHERE id_spotify=?;";
		PreparedStatement statement;
		Album album = null;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, idSpotify);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				// Converter um objeto ResultSet em um objeto album
				album = new Album();
				album.setIdSpotify(rs.getString("id_spotify"));
				album.setIdAlbum(rs.getLong("id_album"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return album;
	}

}