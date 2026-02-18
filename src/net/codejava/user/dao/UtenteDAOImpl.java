package net.codejava.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.codejava.user.db.DBDataSource;
import net.codejava.user.model.Utente;

public class UtenteDAOImpl implements UtenteDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public UtenteDAOImpl() {
		DataSource dataSource = DBDataSource.setup();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Utente u) {
		String sql = "INSERT INTO user (username, password, nome, cognome, nazionalita) VALUES (?, ?, ?, ?, ?) ";
		return jdbcTemplate.update(sql, u.getUsername(), u.getPassword(), u.getNome(), u.getCognome(), u.getNazionalita());
	}

	@Override
	public int update(Utente u) {
		String sql = "UPDATE user SET username=?, password=?, nome=?, cognome=?, nazionalita=? WHERE username=?";
		return jdbcTemplate.update(sql, u.getUsername(), u.getPassword(), u.getNome(), u.getCognome(), u.getNazionalita(), u.getUsername());
	}

	@Override
	public Utente get(String username) {
		String sql = "SELECT * FROM user WHERE username=\""+username +"\"";
		ResultSetExtractor<Utente> extractor = new ResultSetExtractor<Utente>() {
			@Override
			public Utente extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()) {
					String username = rs.getString("username");
					String password = rs.getString("password");
					String nome = rs.getString("nome");
					String cognome = rs.getString("cognome");
					String nazionalita = rs.getString("nazionalita");
					return new Utente(username, password, nome, cognome, nazionalita);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(String username) {
		
		String sql = "DELETE FROM user WHERE username=\""+username+"\"";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Utente> list() {
		String sql= "SELECT * FROM user";
		
		RowMapper<Utente> rowmapper = new RowMapper<Utente>() {
			@Override
			public Utente mapRow(ResultSet rs, int rowNum) throws SQLException{
				String username = rs.getString("username");
				String password = rs.getString("password");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String nazionalita = rs.getString("nazionalita");
				return new Utente(username, password, nome, cognome, nazionalita);
			}
		};
		
		return jdbcTemplate.query(sql, rowmapper);
	}

}
