package com.melodiam.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.melodiam.model.Amizade;
import com.melodiam.model.Usuario;

public class AmizadeDAO {
	private ConexaoMysql conexao;

	public AmizadeDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "root");
	}

	public Amizade solicitarAmizade(Amizade amizade) {
		// ABRIR A CONEX�O COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO Amizade VALUES(null, ?, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO
			statement.setBoolean(1, amizade.isStatus());
			statement.setLong(2, amizade.getUsuario1().getIdUsuario());
			statement.setLong(3, amizade.getUsuario2().getIdUsuario());
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				// PEGA O ID
				amizade.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return amizade;
	}

	// UPDATE usuario SET nome='Rodrigo', login='remor222', senha='1' WHERE
	// id_usuario=1;
	public void aceitarUsuario(long idAmizade) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlUpdate = "UPDATE Amizade SET status_amizade=?, id_usuario1=?, id_usuario2=? WHERE id_amizade=?;";
		Amizade amizade = new Amizade();
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL À SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			// SUBSTITUIR AS INTERROGAÇÕES PELOS VALORES QUE ESTÃO NO OBJETO
			// USUÁRIO
			amizade.setStatus(true);
			amizade.setId(idAmizade);
			statement.setBoolean(1, amizade.isStatus());
			statement.setLong(2, amizade.getUsuario1().getIdUsuario());
			statement.setLong(3, amizade.getUsuario2().getIdUsuario());
			statement.setLong(4, amizade.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	// DELETE FROM usuario WHERE id_usuario=3;
	public void deletarAmizade(long idAmizade) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlDelete = "DELETE FROM Amizade WHERE id_amizade=?;";
		// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
		// SQL À SER EXECUTADO
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
			statement.setLong(1, idAmizade);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	// SELECT * FROM usuario;
	public List<Amizade> buscarAmigosPorUsuario(long idUsuario) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlSelect = 
				"SELECT usuario1.id_usuario id_u1, usuario1.login_usuario login_u1, " + 
				"usuario2.id_usuario id_u2, usuario2.login_usuario login_u2, " + 
				"a.* " + 
				"FROM Amizade AS a " + 
				"INNER JOIN Usuario usuario1 ON usuario1.id_usuario = a.id_usuario1 " + 
				"INNER JOIN Usuario usuario2 ON usuario2.id_usuario = a.id_usuario2 " + 
				"WHERE (a.id_usuario1=? OR a.id_usuario2=?) AND a.status_amizade = TRUE;";
		PreparedStatement statement;

		List<Amizade> listaAmizades = new ArrayList<Amizade>();
		Usuario u1 = null;
		Usuario u2 = null;
		
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				// Converter um objeto ResultSet em um objeto Usuario
				u1 = new Usuario(rs.getLong("id_u1"), rs.getString("login_u1"), "");
				u2 = new Usuario(rs.getLong("id_u2"), rs.getString("login_u2"), "");
				Amizade amizade = new Amizade();
				amizade.setStatus(rs.getBoolean("status_amizade"));
				amizade.setUsuario1(u1);
				amizade.setUsuario1(u2);
				listaAmizades.add(amizade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAmizades;
	}
	
	public List<Amizade> buscarPendentesPorUsuario(long idUsuario) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlSelect = 
				"SELECT usuario1.id_usuario id_u1, usuario1.login_usuario login_u1, " + 
				"usuario2.id_usuario id_u2, usuario2.login_usuario login_u2, " + 
				"a.* " + 
				"FROM Amizade AS a " + 
				"INNER JOIN Usuario usuario1 ON usuario1.id_usuario = a.id_usuario1 " + 
				"INNER JOIN Usuario usuario2 ON usuario2.id_usuario = a.id_usuario2 " + 
				"WHERE a.id_usuario2=? AND a.status_amizade = FALSE;";
		PreparedStatement statement;

		List<Amizade> listaAmizades = new ArrayList<Amizade>();
		Usuario u1 = null;
		Usuario u2 = null;
		
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				// Converter um objeto ResultSet em um objeto Usuario
				u1 = new Usuario(rs.getLong("id_u1"), rs.getString("login_u1"), "");
				u2 = new Usuario(rs.getLong("id_u2"), rs.getString("login_u2"), "");
				Amizade amizade = new Amizade();
				amizade.setStatus(rs.getBoolean("status_amizade"));
				amizade.setUsuario1(u1);
				amizade.setUsuario1(u2);
				listaAmizades.add(amizade);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAmizades;
	}
	
	

}