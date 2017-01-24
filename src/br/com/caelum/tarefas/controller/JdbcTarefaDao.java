package br.com.caelum.tarefas.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.caelum.tarefas.modelo.Tarefa;

@Repository
public class JdbcTarefaDao {
	
	private  Connection connection;
	
	@Autowired
public JdbcTarefaDao(DataSource dataSource) {
		 try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}

public void adiciona(Tarefa tarefa){ 
	String sql = "insert into tarefa"
			+ "(descricao)"
			+ "values (?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
}

public List<Tarefa> lista() {
	try {
		List<Tarefa> tarefas = new ArrayList<>();
		PreparedStatement stmt = connection.prepareStatement("select * from tarefa");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Tarefa tarefa = new Tarefa();
			tarefa.setId(rs.getLong("id"));
			tarefa.setDescricao(rs.getString("descricao"));
			tarefa.setFinalizado(rs.getBoolean("finalizado"));
			Calendar instance = Calendar.getInstance();
			if (rs.getDate("dataFinalizacao") != null) {
			instance.setTime(rs.getDate("dataFinalizacao"));
			
			tarefa.setDataFinalizacao(instance);
			}
			tarefas.add(tarefa);
		}
		stmt.close();
			return tarefas;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	throw new RuntimeException(e);
	}
	}


public Tarefa pesquisa(Long id) {
	Tarefa tarefa = new Tarefa();
	String sql = "select * from tarefa where id=?";
	try {
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setLong(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
          tarefa.setId(rs.getLong("id"));
          tarefa.setDescricao(rs.getString("descricao"));
          if (rs.getDate("dataFinalizacao") != null) {
          Calendar data = Calendar.getInstance(); 
          data.setTime(rs.getDate("dataFinalizacao")); 
          tarefa.setDataFinalizacao(data);}
          tarefa.setFinalizado(rs.getBoolean("finalizado"));
		}
		stmt.close();
		return tarefa;	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
	throw new RuntimeException(e);
	}
	
	
}

public void altera(Tarefa tarefa) {
	String sql = "update tarefa set descricao=?,finalizado=?,dataFinalizacao=? where id=?";
	       
	try {
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, tarefa.getDescricao());
		stmt.setBoolean(2, tarefa.isFinalizado());
		 if (tarefa.getDataFinalizacao() != null) {
		stmt.setDate(3,new Date(tarefa.getDataFinalizacao().getTimeInMillis())); } else {
		stmt.setDate(3, null);	
		}
		stmt.setLong(4, tarefa.getId());
				stmt.execute();
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(e);
	}
}

public void remove(Tarefa tarefa) {
	String sql = "delete from tarefa where id=?";
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setLong(1, tarefa.getId());
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			throw new RuntimeException();
			}
}

public void finaliza(Long id) {
	// TODO Auto-generated method stub
	String sql = "update tarefa set finalizado=?,dataFinalizacao=? where id=?";
    Tarefa tarefa = new Tarefa();
    tarefa = this.pesquisa(id);
    	try {
		PreparedStatement stmt = connection.prepareStatement(sql);
	  stmt.setBoolean(1, true);
	  Calendar data = Calendar.getInstance();
		stmt.setDate(2, new Date(data.getTimeInMillis()));
		stmt.setLong(3, tarefa.getId());
 		stmt.execute();
		stmt.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(e);
	}
}




}
