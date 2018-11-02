package com.melodiam.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.melodiam.model.Album;
import com.melodiam.model.AlbumLista;
import com.melodiam.model.Lista;

public class AlbumListaDAO {

	private ConexaoMysql conexao;

	public AlbumListaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "");
	}

	public AlbumLista inserirEmLista(AlbumLista albumLista) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO album_lista VALUES(null, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, albumLista.getAlbum().getIdAlbum());
			statement.setLong(2, albumLista.getLista().getIdLista());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				albumLista.setIdAlbumLista(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return albumLista;
	}

	public void excluirDaLista(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlDelete = "DELETE FROM album_lista WHERE id_album_lista=?;";
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

	public List<AlbumLista> buscarPorLista(Lista lista) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = "SELECT * FROM album_lista WHERE id_lista=?;";
		PreparedStatement statement;
		AlbumLista albumLista = null;
		Album album = null;
		List<AlbumLista> listaAlbumLista = new ArrayList<AlbumLista>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, lista.getIdLista());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				// Converter um objeto ResultSet em objetos Album e AlbumLista
				album = new Album();
				album.setIdSpotify(rs.getString("id_spotify"));
				album.setIdAlbum(rs.getLong("id_album"));
				albumLista = new AlbumLista(rs.getLong("id_album_lista"), album, lista);
				listaAlbumLista.add(albumLista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAlbumLista;
	}
}
