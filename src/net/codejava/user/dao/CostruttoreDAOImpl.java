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
import net.codejava.user.model.Costruttore;

public class CostruttoreDAOImpl implements CostruttoreDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public CostruttoreDAOImpl() {
		DataSource dataSource = DBDataSource.setup();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Costruttore p) {
		String sql = "INSERT INTO costruttori (nome, descrizione, prezzo, nazione) VALUES (?, ?, ?, ?) ";
		return jdbcTemplate.update(sql, p.getNomeCompleto(), p.getDescrizione(), p.getPrezzo(), p.getNazione());
	}

	@Override
	public int update(Costruttore u) {
		String sql = "UPDATE costruttori SET nome=?, descrizione=?, prezzo=?, nazione=? WHERE nome=?";
		return jdbcTemplate.update(sql, u.getNomeCompleto(), u.getDescrizione(), u.getPrezzo(), u.getNazione(), u.getNomeCompleto());
	}

	@Override
	public Costruttore get(String nome) {
		String sql = "SELECT * FROM costruttori WHERE nome=\""+nome +"\"";
		ResultSetExtractor<Costruttore> extractor = new ResultSetExtractor<Costruttore>() {
			@Override
			public Costruttore extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()) {
					String nome = rs.getString("nome");
					String descrizione = rs.getString("descrizione");
					Float prezzo = rs.getFloat("prezzo");
					String nazione = rs.getString("nazione");
					return new Costruttore(prezzo,nome, descrizione, nazione);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(String nome) {
		
		String sql = "DELETE FROM costruttori WHERE nome=\""+nome+"\"";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Costruttore> list() {
		String sql= "SELECT * FROM costruttori";
		
		RowMapper<Costruttore> rowmapper = new RowMapper<Costruttore>() {
			@Override
			public Costruttore mapRow(ResultSet rs, int rowNum) throws SQLException{
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				Float prezzo = rs.getFloat("prezzo");
				String nazione = rs.getString("nazione");
				return new Costruttore(prezzo,nome, descrizione, nazione);
			}
		};
		
		return jdbcTemplate.query(sql, rowmapper);
	}

}

