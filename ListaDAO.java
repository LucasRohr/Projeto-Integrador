package com.melodiam.persistencia;

//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.melodiam.model.Lista;
import com.melodiam.model.Usuario;
import com.mysql.jdbc.PreparedStatement;

public class ListaDAO {
	
	private ConexaoMysql conexao;

	public ListaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "root");
	}

	// INSERT INTO lista VALUES(null, 'Rodrigo', 'remor', '123');
	public Lista cadastrarLista(Lista Lista) {
		// ABRIR A CONEX�O COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO lista VALUES(null, ?, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO
			
			statement.setString(1, Lista.getNome());
			statement.setString(2, Lista.getDescricao());
			statement.setLong(3, Lista.getAutor().getIdUsuario());
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				// PEGA O ID
				Lista.setIdLista(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return Lista;
	}
	
	
	// UPDATE lista SET nome_lista='Algo', descricao_lista='ghjgg', id_usuario='1' WHERE id_lista=1;
		public void editar(Lista lista) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlUpdate = "UPDATE usuario SET login_usuario=?, senha_usuario=? WHERE id_usuario=?;";

			try {
				// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
				// SQL À SER EXECUTADO
				PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
				// SUBSTITUIR AS INTERROGAÇÕES PELOS VALORES QUE ESTÃO NO OBJETO
				// USUÁRIO
				
				statement.setLong(1, lista.getIdLista());
				statement.setString(2, lista.getNome());
				statement.setString(3, lista.getDescricao());
				statement.setLong(4, lista.getAutor().getIdUsuario());

				statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}
	
	
	// DELETE FROM Lista WHERE id_Lista=3;
		public void excluirLista(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlDelete = "DELETE FROM lista WHERE id_lista=?;";
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL À SER EXECUTADO
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
	
		// SELECT * FROM Lista;
		public List<Lista> buscarTodasListas() {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlSelect = "SELECT * FROM lista INNER JOIN usuario ON lista.id_usuario = usuario.id_usuario;";
			PreparedStatement statement;
			Lista lista = null;
			Usuario autor = null;
			List<Lista> listaListas = new ArrayList<Lista>();
			try {
				statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlSelect);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					System.out.println("o banco possui dados");
					// Converter um objeto ResultSet em um objeto Lista
					autor = new Usuario(rs.getLong("id_usuario"), rs.getString("login_usuario"), rs.getString("senha_usuario"));
					lista = new Lista();
					lista.setIdLista((rs.getLong("id_lista")));
					lista.setNome(rs.getString("nome_lista"));
					lista.setDescricao(rs.getString("descricao_lista"));
					lista.setAutor(autor);
					listaListas.add(lista);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return listaListas;
		}
	
		// SELECT * FROM Lista WHERE id_Lista=2;
		public Lista buscarPorId(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM lista WHERE id_lista=?;";
			PreparedStatement statement;
			Lista lista = null;
			try {
				statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto Lista
					lista = new Lista();
					lista.setIdLista(rs.getLong("id_lista"));
					lista.setNome(rs.getString("nome_lista"));
					lista.setDescricao(rs.getString("descricao_lista"));
					lista.getAutor().setIdUsuario(rs.getLong("id_usuario"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return lista;
		}	
		
		
		// SELECT * FROM lista WHERE id_lista=2;
			public List<Lista> buscarPorAutor(Usuario autor) {
				// ABRIR A CONEXÃO COM O BANCO
				this.conexao.abrirConexao();
				// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
				String sqlInsert = "SELECT * FROM lista WHERE id_usuario=?;";
				PreparedStatement statement;
				List<Lista> listaListas = new ArrayList<Lista>();
				Lista lista = null;
				try {
					statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
					statement.setLong(1, autor.getIdUsuario());
					ResultSet rs = statement.executeQuery();
					while(rs.next()) {
						// Converter um objeto ResultSet em um objeto Lista
						
						lista = new Lista();
						lista.setIdLista(rs.getLong("id_lista"));
						lista.setNome(rs.getString("nome_lista"));
						lista.setDescricao(rs.getString("descricao_lista"));
						lista.setAutor(autor);
						listaListas.add(lista);
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					this.conexao.fecharConexao();
				}
				return listaListas;
			}	

}
