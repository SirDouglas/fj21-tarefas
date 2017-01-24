package br.com.caelum.tarefas.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.caelum.tarefas.ConnectionFactory;
import br.com.caelum.tarefas.modelo.Usuario;

public class JdbcUsuarioDao {
	private  Connection connection;
public JdbcUsuarioDao() {
this.connection = new ConnectionFactory().getConnection();
}

public boolean existeUsuario(Usuario usuario) {
	String sql ="select * from Usuario where login=? and senha=?";
	try {
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1,usuario.getLogin());
		stmt.setString(2, usuario.getSenha());
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
	return true;	
		}
	
		return false;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(e);
	}

	
}

}
