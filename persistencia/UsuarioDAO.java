package com.melodiam.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.melodiam.model.Usuario;

public class UsuarioDAO {

	private ConexaoMysql conexao;

	public UsuarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "root");
	}

	// INSERT INTO usuario VALUES(null, 'Rodrigo', 'remor', '123');
	public Usuario cadastrarUsuario(Usuario usuario) {
		// ABRIR A CONEX�O COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO Usuario VALUES(null, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO
			
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				// PEGA O ID
				usuario.setIdUsuario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return usuario;
	}
	
	// UPDATE usuario SET nome='Rodrigo', login='remor222', senha='1' WHERE
	// id_usuario=1;
	public void editarUsuario(Usuario usuario) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlUpdate = "UPDATE Usuario SET login_usuario=?, senha_usuario=? WHERE id_usuario=?;";

		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL À SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			// SUBSTITUIR AS INTERROGAÇÕES PELOS VALORES QUE ESTÃO NO OBJETO
			// USUÁRIO
			
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setLong(3, usuario.getIdUsuario());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	// DELETE FROM usuario WHERE id_usuario=3;
		public void excluirUsuario(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlDelete = "DELETE FROM Usuario WHERE id_usuario=?;";
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
		public List<Usuario> buscarTodos() {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlSelect = "SELECT * FROM Usuario;";
			PreparedStatement statement;
			Usuario usuario = null;
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlSelect);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					// Converter um objeto ResultSet em um objeto Usuario
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getLong("id_usuario"));
					usuario.setLogin(rs.getString("login_usuario"));
					usuario.setSenha(rs.getString("senha_usuario"));
					listaUsuarios.add(usuario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return listaUsuarios;
		}
	
		// SELECT * FROM usuario WHERE id_usuario=2;
		public Usuario buscarPorId(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM Usuario WHERE id_usuario=?;";
			PreparedStatement statement;
			Usuario usuario = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto Usuario
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getLong("id_usuario"));
					usuario.setLogin(rs.getString("login_usuario"));
					usuario.setSenha(rs.getString("senha_usuario"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return usuario;
		}	

		// SELECT * FROM usuario WHERE login=? AND senha=?;
		public Usuario buscarPorLogin(String login) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM Usuario WHERE login_usuario=?;";
			PreparedStatement statement;
			Usuario usuario = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setString(1, login);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto Usuario
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getLong("id_usuario"));
					usuario.setLogin(rs.getString("login_usuario"));
					usuario.setSenha(rs.getString("senha_usuario"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return usuario;
		}

}
