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
import net.codejava.user.model.Pilota;

public class PilotaDAOImpl implements PilotaDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public PilotaDAOImpl() {
		DataSource dataSource = DBDataSource.setup();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Pilota p) {
		String sql = "INSERT INTO piloti (nome, descrizione, prezzo, nazione, costruttore) VALUES (?, ?, ?, ?, ?) ";
		return jdbcTemplate.update(sql, p.getNomeCompleto(), p.getDescrizione(), p.getPrezzo(), p.getNazione(), p.getCostruttore());
	}

	@Override
	public int update(Pilota u) {
		String sql = "UPDATE piloti SET nome=?, descrizione=?, prezzo=?, nazione=?, costruttore=? WHERE nome=?";
		return jdbcTemplate.update(sql, u.getNomeCompleto(), u.getDescrizione(), u.getPrezzo(), u.getNazione(), u.getCostruttore(), u.getNomeCompleto());
	}

	@Override
	public Pilota get(String nome) {
		String sql = "SELECT * FROM piloti WHERE nome=\""+nome +"\"";
		ResultSetExtractor<Pilota> extractor = new ResultSetExtractor<Pilota>() {
			@Override
			public Pilota extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()) {
					String nome = rs.getString("nome");
					String descrizione = rs.getString("descrizione");
					Float prezzo = rs.getFloat("prezzo");
					String nazione = rs.getString("nazione");
					String costruttore = rs.getString("costruttore");
					return new Pilota(prezzo,nome, descrizione, nazione, costruttore);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(String nome) {
		
		String sql = "DELETE FROM piloti WHERE nome=\""+nome+"\"";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Pilota> list() {
		String sql= "SELECT * FROM piloti";
		
		RowMapper<Pilota> rowmapper = new RowMapper<Pilota>() {
			@Override
			public Pilota mapRow(ResultSet rs, int rowNum) throws SQLException{
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				Float prezzo = rs.getFloat("prezzo");
				String nazione = rs.getString("nazione");
				String costruttore = rs.getString("costruttore");
				return new Pilota(prezzo,nome, descrizione, nazione, costruttore);
			}
		};
		
		return jdbcTemplate.query(sql, rowmapper);
	}

}
