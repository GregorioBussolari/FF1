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
import net.codejava.user.model.Leggenda;

public class LeggendaDAOImpl implements LeggendaDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public LeggendaDAOImpl() {
		DataSource dataSource = DBDataSource.setup();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Leggenda p) {
		String sql = "INSERT INTO leggende (nome, descrizione, prezzo, nazione, abilita) VALUES (?, ?, ?, ?, ?) ";
		return jdbcTemplate.update(sql, p.getNomeCompleto(), p.getDescrizione(), p.getPrezzo(), p.getNazione(), p.getAbilitaSpeciale());
	}

	@Override
	public int update(Leggenda u) {
		String sql = "UPDATE leggende SET nome=?, descrizione=?, prezzo=?, nazione=?, abilita=? WHERE nome=?";
		return jdbcTemplate.update(sql, u.getNomeCompleto(), u.getDescrizione(), u.getPrezzo(), u.getNazione(), u.getAbilitaSpeciale(), u.getNomeCompleto());
	}

	@Override
	public Leggenda get(String nome) {
		String sql = "SELECT * FROM leggende WHERE nome=\""+nome +"\"";
		ResultSetExtractor<Leggenda> extractor = new ResultSetExtractor<Leggenda>() {
			@Override
			public Leggenda extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()) {
					String nome = rs.getString("nome");
					String descrizione = rs.getString("descrizione");
					Float prezzo = rs.getFloat("prezzo");
					String nazione = rs.getString("nazione");
					String abilita = rs.getString("abilita");
					return new Leggenda(prezzo,nome, descrizione, nazione, abilita);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(String nome) {
		
		String sql = "DELETE FROM leggende WHERE nome=\""+nome+"\"";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Leggenda> list() {
		String sql= "SELECT * FROM leggende";
		
		RowMapper<Leggenda> rowmapper = new RowMapper<Leggenda>() {
			@Override
			public Leggenda mapRow(ResultSet rs, int rowNum) throws SQLException{
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				Float prezzo = rs.getFloat("prezzo");
				String nazione = rs.getString("nazione");
				String abilita = rs.getString("abilita");
				return new Leggenda(prezzo,nome, descrizione, nazione, abilita);
			}
		};
		
		return jdbcTemplate.query(sql, rowmapper);
	}

}
