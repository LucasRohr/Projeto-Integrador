package com.melodiam.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.melodiam.model.Amizade;
import com.melodiam.model.AmizadeLista;
import com.melodiam.model.Lista;
import com.melodiam.model.Usuario;

public class AmizadeListaDAO {
	
	//compartilhar YEAA
	//buscarRecebidosPorUsuario
	//buscarCompartilhadosPorUsuario
	
	private ConexaoMysql conexao;
	
	public AmizadeListaDAO(){
		super();
		this.conexao = new ConexaoMysql("localhost", "melodiam", "root", "ifsul2017");
	}
	
	public AmizadeLista compartilhar(AmizadeLista amizadeLista) {
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO Amizade_Lista VALUES(null, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, amizadeLista.getIdAmizadeLista());
			statement.setLong(2, amizadeLista.getAmizade().getId());
			statement.setLong(3, amizadeLista.getLista().getIdLista());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				amizadeLista.setIdAmizadeLista(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return amizadeLista;
	}
	
	public List<AmizadeLista> buscarRecebidosPorUsuario(long idUsuario){
		this.conexao.abrirConexao();
		String sqlInsert = 
				"SELECT autor.id_usuario id_autor, autor.login_usuario login_autor, " + 
				"leitor.id_usuario id_leitor, leitor.login_usuario login_leitor, " + 
				"Amizade.*, Lista.* " + 
				"FROM Amizade_Lista " + 
				"INNER JOIN Lista ON Amizade_Lista.id_lista = Lista.id_lista " + 
				"INNER JOIN Amizade ON Amizade_Lista.id_amizade = Amizade.id_amizade " + 
				"INNER JOIN Usuario autor ON (autor.id_usuario = Amizade.id_usuario1 " + 
				"XOR autor.id_usuario = Amizade.id_usuario2) AND autor.id_usuario = Lista.id_usuario " + 
				"INNER JOIN Usuario leitor ON (leitor.id_usuario = Amizade.id_usuario1 " + 
				"XOR leitor.id_usuario = Amizade.id_usuario2) AND leitor.id_usuario <> Lista.id_usuario " + 
				"WHERE leitor.id_usuario = ?;";
		PreparedStatement statement;
		Usuario autor = null;
		Usuario leitor = null;
		Lista lista = null;
		Amizade amizade = null;
		AmizadeLista amizadeLista = null;
		List<AmizadeLista> listaAmizadeLista = new ArrayList<AmizadeLista>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, idUsuario);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) {
				autor = new Usuario(
						rs.getLong("id_autor"), rs.getString("login_autor"), "");
				leitor = new Usuario(
						rs.getLong("id_leitor"), rs.getString("login_leitor"), "");
				amizade = new Amizade();
				amizade.setId(rs.getLong("id_amizade"));
				amizade.setStatus(rs.getBoolean("status_amizade"));
				if(rs.getLong("id_autor") == rs.getLong("id_usuario1")) {
					amizade.setUsuario1(autor);
					amizade.setUsuario2(leitor);
				}else {
					amizade.setUsuario1(leitor);
					amizade.setUsuario2(autor);
				}
				lista = new Lista(
						rs.getLong("id_lista"), autor, rs.getString("nome_lista"), rs.getString("descricao_lista"));
				amizadeLista = new AmizadeLista(rs.getLong("id_amizade_lista"), amizade, lista);
				listaAmizadeLista.add(amizadeLista);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAmizadeLista;
		
	}
	
	public List<AmizadeLista> buscarPorAutorELeitor(long idAutor, long idLeitor){
		this.conexao.abrirConexao();
		String sqlInsert = 
				"SELECT autor.id_usuario id_autor, autor.login_usuario login_autor, " + 
				"leitor.id_usuario id_leitor, leitor.login_usuario login_leitor, " + 
				"Amizade.*, Lista.* " + 
				"FROM Amizade_Lista " + 
				"INNER JOIN Lista ON Amizade_Lista.id_lista = Lista.id_lista " + 
				"INNER JOIN Amizade ON Amizade_Lista.id_amizade = Amizade.id_amizade " + 
				"INNER JOIN Usuario autor ON (autor.id_usuario = Amizade.id_usuario1 " + 
				"XOR autor.id_usuario = Amizade.id_usuario2) AND autor.id_usuario = Lista.id_usuario " + 
				"INNER JOIN Usuario leitor ON (leitor.id_usuario = Amizade.id_usuario1 " + 
				"XOR leitor.id_usuario = Amizade.id_usuario2) AND leitor.id_usuario <> Lista.id_usuario " + 
				"WHERE autor.id_usuario = ? AND leitor.id_usuario=?;";
		PreparedStatement statement;
		Usuario autor = null;
		Usuario leitor = null;
		Lista lista = null;
		Amizade amizade = null;
		AmizadeLista amizadeLista = null;
		List<AmizadeLista> listaAmizadeLista = new ArrayList<AmizadeLista>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, idAutor);
			statement.setLong(2, idLeitor);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) {
				autor = new Usuario(
						rs.getLong("id_autor"), rs.getString("login_autor"), "");
				leitor = new Usuario(
						rs.getLong("id_leitor"), rs.getString("login_leitor"), "");
				amizade = new Amizade();
				amizade.setId(rs.getLong("id_amizade"));
				amizade.setStatus(rs.getBoolean("status_amizade"));
				if(rs.getLong("id_autor") == rs.getLong("id_usuario1")) {
					amizade.setUsuario1(autor);
					amizade.setUsuario2(leitor);
				}else {
					amizade.setUsuario1(leitor);
					amizade.setUsuario2(autor);
				}
				lista = new Lista(
						rs.getLong("id_lista"), autor, rs.getString("nome_lista"), rs.getString("descricao_lista"));
				amizadeLista = new AmizadeLista(rs.getLong("id_amizade_lista"), amizade, lista);
				listaAmizadeLista.add(amizadeLista);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAmizadeLista;
		
	}
	
	public List<AmizadeLista> buscarCompartilhadosPorUsuario(long idUsuario){
		this.conexao.abrirConexao();
		String sqlInsert = 
				"SELECT autor.id_usuario id_autor, autor.login_usuario login_autor, " + 
				"leitor.id_usuario id_leitor, leitor.login_usuario login_leitor, " + 
				"Amizade.*, Lista.* " + 
				"FROM Amizade_Lista " + 
				"INNER JOIN Lista ON Amizade_Lista.id_lista = Lista.id_lista " + 
				"INNER JOIN Amizade ON Amizade_Lista.id_amizade = Amizade.id_amizade " + 
				"INNER JOIN Usuario autor ON (autor.id_usuario = Amizade.id_usuario1 " + 
				"XOR autor.id_usuario = Amizade.id_usuario2) AND autor.id_usuario = Lista.id_usuario " + 
				"INNER JOIN Usuario leitor ON (leitor.id_usuario = Amizade.id_usuario1 " + 
				"XOR leitor.id_usuario = Amizade.id_usuario2) AND leitor.id_usuario <> Lista.id_usuario " + 
				"WHERE autor.id_usuario = ?;";
		PreparedStatement statement;
		Usuario autor = null;
		Usuario leitor = null;
		Lista lista = null;
		Amizade amizade = null;
		AmizadeLista amizadeLista = null;
		List<AmizadeLista> listaAmizadeLista = new ArrayList<AmizadeLista>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, idUsuario);
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			while (rs.next()) {
				autor = new Usuario(
						rs.getLong("id_autor"), rs.getString("login_autor"), "");
				leitor = new Usuario(
						rs.getLong("id_leitor"), rs.getString("login_leitor"), "");
				amizade = new Amizade();
				amizade.setId(rs.getLong("id_amizade"));
				amizade.setStatus(rs.getBoolean("status_amizade"));
				if(rs.getLong("id_autor") == rs.getLong("id_usuario1")) {
					amizade.setUsuario1(autor);
					amizade.setUsuario2(leitor);
				}else {
					amizade.setUsuario1(leitor);
					amizade.setUsuario2(autor);
				}
				lista = new Lista(
						rs.getLong("id_lista"), autor, rs.getString("nome_lista"), rs.getString("descricao_lista"));
				amizadeLista = new AmizadeLista(rs.getLong("id_amizade_lista"), amizade, lista);
				listaAmizadeLista.add(amizadeLista);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaAmizadeLista;
		
	}

}
