package com.melodiam.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.melodiam.model.Album;
import com.melodiam.model.Comentario;
import com.melodiam.model.Usuario;



public class ComentarioDAO {
	
	private ConexaoMysql conexao;
	
	public ComentarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "");
	}

	public Comentario publicar(Comentario comentario) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO comentario VALUES(null, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, comentario.getTexto());
			statement.setString(2, comentario.getData());
			statement.setLong(3, comentario.getAutor().getIdUsuario());
			statement.setLong(4, comentario.getAlbum().getIdAlbum());
			statement.executeUpdate();
			
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				// PEGA O ID
				comentario.setIdComentario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return comentario;
	}
	
	// UPDATE usuario SET nome='Rodrigo', login='remor222', senha='1' WHERE
	// id_usuario=1;
	public void editarComentario(Comentario comentario) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlUpdate = "UPDATE comentario SET id_usuario=?, texto=?, data=?, id_album=? WHERE id_comentario=?;";

		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL À SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			// SUBSTITUIR AS INTERROGAÇÕES PELOS VALORES QUE ESTÃO NO OBJETO
			// USUÁRIO
			
			statement.setLong(1, comentario.getAutor().getIdUsuario());
			statement.setString(2, comentario.getTexto());
			statement.setString(3, comentario.getData());
			statement.setLong(4, comentario.getAlbum().getIdAlbum());
			statement.setLong(5, comentario.getIdComentario());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	// DELETE FROM usuario WHERE id_usuario=3;
		public void excluir(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlDelete = "DELETE FROM comentario WHERE id_comentario=?;";
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
	
		// SELECT * FROM usuario;
		public List<Comentario> buscarTodos() {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlSelect = "INNER JOIN usuario ON comentario.id_usuario = usuario.id_usuario AND comentario.id_album = album.id_album;";
			PreparedStatement statement;
			Comentario comentario = null;
			Usuario autor = null;
			Album album = null;
			List<Comentario> listaComentarios = new ArrayList<Comentario>();
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlSelect);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					// Converter um objeto ResultSet em um objeto Comentario
					comentario = new Comentario();
					autor = new Usuario(rs.getLong("id_usuario"), rs.getString("usuario_login"), 
							rs.getString("usuario_senha"));
					album = new Album(rs.getString("id_spotify"), rs.getLong("id_album"));
					
					comentario.setIdComentario(rs.getLong("id_comentario"));
					comentario.setAutor(autor);
					comentario.setTexto(rs.getString("texto"));
					comentario.setData(rs.getString("data_hora"));
					comentario.setAlbum(album);
					listaComentarios.add(comentario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return listaComentarios;
		}
	
		// SELECT * FROM usuario WHERE id_usuario=2;
		public Comentario buscarPorId(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "INNER JOIN usuario ON comentario.id_usuario = usuario.id_usuario AND comentario.id_album = album.id_album WHERE comentario.id_comentario = ?;";
			PreparedStatement statement;
			Comentario comentario = null;
			Usuario autor = null;
			Album album = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto Comentario
					comentario = new Comentario();
					autor = new Usuario(rs.getLong("id_usuario"), rs.getString("usuario_login"), 
							rs.getString("usuario_senha"));
					album = new Album(rs.getString("id_spotify"), rs.getLong("id_album"));
					
					comentario.setIdComentario(rs.getLong("id_comentario"));
					comentario.setAutor(autor);
					comentario.setTexto(rs.getString("texto"));
					comentario.setData(rs.getString("data_hora"));
					comentario.setAlbum(album);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return comentario;
		}	

		// SELECT * FROM usuario WHERE login=? AND senha=?;
		public Comentario buscarPorAutor(Usuario autor) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "INNER JOIN usuario ON comentario.id_usuario = usuario.id_usuario AND comentario.id_album = album.id_album WHERE comentario.id_usuario = ?;";
			PreparedStatement statement;
			Comentario comentario = null;
			Album album = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, autor.getIdUsuario());
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					comentario = new Comentario();
					
					album = new Album(rs.getString("id_spotify"), rs.getLong("id_album"));
					
					comentario.setIdComentario(rs.getLong("id_comentario"));
					comentario.setAutor(autor);
					comentario.setTexto(rs.getString("texto"));
					comentario.setData(rs.getString("data_hora"));
					comentario.setAlbum(album);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return comentario;
		}
		
		public Comentario buscarPorAlbum(Album album) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "INNER JOIN usuario ON comentario.id_usuario = usuario.id_usuario AND comentario.id_album = album.id_album WHERE comentario.id_album = ?;";
			PreparedStatement statement;
			Comentario comentario = null;
			Usuario autor = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, album.getIdAlbum());
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto Comentario
					
					autor = new Usuario(rs.getLong("id_usuario"), rs.getString("usuario_login"), 
							rs.getString("usuario_senha"));
					
					comentario = new Comentario();
					comentario.setIdComentario(rs.getLong("id_comentario"));
					comentario.setAutor(autor);
					comentario.setTexto(rs.getString("texto"));
					comentario.setData(rs.getString("data_hora"));
					comentario.setAlbum(album);				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return comentario;
		}
}
