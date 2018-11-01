package com.melodiam.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.melodiam.model.Avaliacao;
import com.melodiam.model.AvaliacaoLista;
import com.melodiam.model.Lista;
import com.melodiam.model.Usuario;

public class AvaliacaoListaDAO implements AvaliacaoDAO {
	
	private ConexaoMysql conexao;

	public AvaliacaoListaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "programador2000");
	}

	// INSERT INTO avaliacaoLista VALUES(null, 'Rodrigo', 'remor', '123');
	public AvaliacaoLista cadastrarAvaliacao(Avaliacao avaliacao) {
		// ABRIR A CONEX�O COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO Avaliacao_Lista VALUES(null, ?, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO
						
			statement.setFloat(1, ((AvaliacaoLista) avaliacao).getAvaliacao());
			statement.setLong(2, ((AvaliacaoLista) avaliacao).getAutor().getIdUsuario());
			statement.setLong(3, ((AvaliacaoLista) avaliacao).getLista().getIdLista());
			
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				// PEGA O ID
				((AvaliacaoLista) avaliacao).setIdAvaliacaoLista(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return ((AvaliacaoLista) avaliacao);
	}
	
	// UPDATE avaliacaoLista SET nome='Rodrigo', login='remor222', senha='1' WHERE
	// id_avaliacaoLista=1;
	public void editarAvaliacao(Avaliacao avaliacao) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlUpdate = "UPDATE Avaliacao_Lista SET avaliacao_lista=?, id_usuario=?, id_lista=? WHERE id_avaliacao_lista=?;";

		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL À SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			// SUBSTITUIR AS INTERROGAÇÕES PELOS VALORES QUE ESTÃO NO OBJETO
			// USUÁRIO
			
			statement.setFloat(1, ((AvaliacaoLista) avaliacao).getAvaliacao());
			statement.setLong(2, ((AvaliacaoLista) avaliacao).getAutor().getIdUsuario());
			statement.setLong(3, ((AvaliacaoLista) avaliacao).getLista().getIdLista());
			statement.setLong(4, ((AvaliacaoLista) avaliacao).getIdAvaliacaoLista());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	// DELETE FROM avaliacaoLista WHERE id_avaliacaoLista=3;
		public void excluirAvaliacao(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlDelete = "DELETE FROM Avaliacao_Lista WHERE id_avaliacao_lista=?;";
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
	
		// SELECT * FROM avaliacaoLista;
		public List<Avaliacao> buscarPorUsuario(Usuario usuario) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM Avaliacao_Lista INNER JOIN Avaliacao_Lista ON Avaliacao_Lista.id_usuario = Usuario.id_usuario AND Avaliacao_Lista.id_lista = Lista.id_lista WHERE id_usuario=?;";
			PreparedStatement statement;
			AvaliacaoLista avaliacaoLista = null;
			List<Avaliacao> listaAvaliacaoListas = new ArrayList<Avaliacao>();
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, usuario.getIdUsuario());				
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					// Converter um objeto ResultSet em um objeto avaliacaoLista
					avaliacaoLista = new AvaliacaoLista();
					avaliacaoLista.setIdAvaliacaoLista(rs.getLong("id_avaliacao_lista"));
					avaliacaoLista.setAvaliacao(rs.getFloat("avaliacao_lista"));
					avaliacaoLista.getAutor().setIdUsuario(rs.getLong("id_usuario"));
					avaliacaoLista.getLista().setIdLista(rs.getLong("id_lista"));
					listaAvaliacaoListas.add(avaliacaoLista);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return listaAvaliacaoListas;
		}
	
		// SELECT * FROM avaliacaoLista WHERE id_avaliacaoLista=2;
		public AvaliacaoLista buscarPorId(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM Avaliacao_Lista WHERE id_avaliacaoLista=?;";
			PreparedStatement statement;
			AvaliacaoLista avaliacaoLista = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto avaliacaoLista
					avaliacaoLista = new AvaliacaoLista();
					avaliacaoLista.setIdAvaliacaoLista(rs.getLong("id_avaliacao_lista"));
					avaliacaoLista.setAvaliacao(rs.getFloat("avaliacao_lista"));
					avaliacaoLista.getAutor().setIdUsuario(rs.getLong("id_usuario"));
					avaliacaoLista.getLista().setIdLista(rs.getLong("id_lista"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return avaliacaoLista;
		}	
		
		public float calcularMedia(AvaliacaoLista avaliacaoLista) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT AVG(?) FROM Avaliacao_Lista;";
			PreparedStatement statement;
			float media = 0;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setFloat(1, avaliacaoLista.getAvaliacao());
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto avaliacaoLista
					media = rs.getFloat("avaliacao_lista");
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return media;
		}	
		
	
		public List<AvaliacaoLista> buscarPorLista(Lista lista) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM Avaliacao_Lista WHERE id_lista=?;";
			PreparedStatement statement;
			AvaliacaoLista avaliacaoLista = null;
			List<AvaliacaoLista> listaAvaliacaoListas = new ArrayList<AvaliacaoLista>();
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, lista.getIdLista());				
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					// Converter um objeto ResultSet em um objeto avaliacaoLista
					avaliacaoLista = new AvaliacaoLista();
					avaliacaoLista.setIdAvaliacaoLista(rs.getLong("id_avaliacao_lista"));
					avaliacaoLista.setAvaliacao(rs.getFloat("avaliacao_lista"));
					avaliacaoLista.getAutor().setIdUsuario(rs.getLong("id_usuario"));
					avaliacaoLista.getLista().setIdLista(rs.getLong("id_lista"));
					listaAvaliacaoListas.add(avaliacaoLista);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return listaAvaliacaoListas;
		}
		
		
		
		
		

}
