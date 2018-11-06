package com.melodiam.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.melodiam.model.Avaliacao;
import com.melodiam.model.AvaliacaoLista;
import com.melodiam.model.Lista;
import com.melodiam.model.Usuario;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class AvaliacaoListaDAO implements AvaliacaoDAO {

	private ConexaoMysql conexao;

	public AvaliacaoListaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "root");
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
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO

			statement.setFloat(1, ((AvaliacaoLista) avaliacao).getAvaliacao());
			statement.setLong(2, ((AvaliacaoLista) avaliacao).getAutor().getIdUsuario());
			statement.setLong(3, ((AvaliacaoLista) avaliacao).getLista().getIdLista());

			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
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
		String sqlUpdate = 
				"UPDATE Avaliacao_Lista SET avaliacao_lista=?, id_usuario=?, id_lista=? " + 
				"WHERE id_avaliacao_lista=?;";

		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL À SER EXECUTADO
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlUpdate);
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
			PreparedStatement statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, id);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	// SELECT * FROM avaliacaoLista;
	public List<Avaliacao> buscarPorUsuario(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
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
		List<Avaliacao> listaAvaliacaoListas = new ArrayList<Avaliacao>();
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
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
		AvaliacaoLista avaliacaoLista = null;
		Usuario autor = null;
		Usuario avaliador = null;
		Lista lista = null;
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {//COMO PEGAR O AUTOR DA LISTA??
				// Converter um objeto ResultSet em um objeto avaliacaoLista
				autor = new Usuario(rs.getLong("id_autor"), rs.getString("login_autor"), ""); //enviando sem senha
				lista = new Lista(
						rs.getLong("id_lista"), autor, rs.getString("nome_lista"), rs.getString("descricao_lista"));
				avaliador = new Usuario(rs.getLong("id_avaliador"), rs.getString("id_avaliador"), ""); //enviando sem senha
				avaliacaoLista = new AvaliacaoLista();
				avaliacaoLista.setIdAvaliacaoLista(rs.getLong("id_avaliacao_lista"));
				avaliacaoLista.setAvaliacao(rs.getFloat("avaliacao_lista"));
				avaliacaoLista.setAutor(avaliador);
				avaliacaoLista.setLista(lista);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return avaliacaoLista;
	}

	public float calcularMediaLista(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = "SELECT AVG(avaliacao_lista) FROM Avaliacao_Lista WHERE id_lista=?;";
		PreparedStatement statement;
		float media = 0;
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
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

	public List<AvaliacaoLista> buscarPorLista(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = 
				"SELECT autor.id_usuario id_autor, autor.login_usuario login_autor, " + 
				"avaliador.id_usuario id_avaliador, avaliador.login_usuario login_avaliador, " + 
				"l.*, al.* " + 
				"FROM Avaliacao_Lista al" + 
				"INNER JOIN Lista l ON l.id_lista = al.id_lista " + 
				"INNER JOIN Usuario avaliador ON avaliador.id_usuario = al.id_usuario " + 
				"INNER JOIN Usuario autor ON autor.id_usuario = l.id_usuario   " + 
				"WHERE al.id_lista=?;";
		PreparedStatement statement;
		AvaliacaoLista avaliacaoLista = null;
		Usuario autor = null;
		Usuario avaliador = null;
		Lista lista = null;
		List<AvaliacaoLista> listaAvaliacaoListas = new ArrayList<AvaliacaoLista>();
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				autor = new Usuario(rs.getLong("id_autor"), rs.getString("login_autor"), ""); //enviando sem senha
				lista = new Lista(
						rs.getLong("id_lista"), autor, rs.getString("nome_lista"), rs.getString("descricao_lista"));
				avaliador = new Usuario(rs.getLong("id_avaliador"), rs.getString("id_avaliador"), ""); //enviando sem senha
				avaliacaoLista = new AvaliacaoLista();
				avaliacaoLista.setIdAvaliacaoLista(rs.getLong("id_avaliacao_lista"));
				avaliacaoLista.setAvaliacao(rs.getFloat("avaliacao_lista"));
				avaliacaoLista.setAutor(avaliador);
				avaliacaoLista.setLista(lista);
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