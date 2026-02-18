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
import net.codejava.user.model.*;



public class IscrizioneLegaDAOImpl implements IscrizioneLegaDAO {
	
	
	private SquadraDAO daoS = new SquadraDAOImpl();
	
	
	
	private JdbcTemplate jdbcTemplate;
	
	public IscrizioneLegaDAOImpl() {
		DataSource dataSource = DBDataSource.setup();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(IscrizioneLega l) {
		String sql = "INSERT INTO iscrizioni (codiceLega, squadra, utente) VALUES (?, ?, ?) ";
		return jdbcTemplate.update(sql, l.getCodiceLega(), l.getSquadra().getNomeSquadra(), l.getSquadra().getUtente().getUsername());
	}

	@Override
	public int update(IscrizioneLega l) {
		String sql = "UPDATE iscrizioni SET codiceLega=?, squadra=?, utente=? WHERE codiceLega=? AND squadra=? AND utente=?";
		return jdbcTemplate.update(sql, l.getCodiceLega(), l.getSquadra().getNomeSquadra(),l.getSquadra().getUtente().getUsername(), l.getCodiceLega(), l.getSquadra().getNomeSquadra(),l.getSquadra().getUtente().getUsername());
	}

	@Override
	public IscrizioneLega get(String codiceLega, String s, String u) {
	String sql = "SELECT * FROM iscrizioni WHERE codiceLega=\""+codiceLega +"\" AND squadra=\""+s+"\" AND utente=\""+u +"\" ";
		ResultSetExtractor<IscrizioneLega> extractor = new ResultSetExtractor<IscrizioneLega>() {
			@Override
			public IscrizioneLega extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()) {
					String codice = rs.getString("codiceLega");
					String squadra = rs.getString("squadra");
					String utente = rs.getString("utente");
					
					daoS = new SquadraDAOImpl();
					Squadra squad = daoS.get(squadra,utente);
					
					return new IscrizioneLega(codice, squad);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(String codiceLega, String s, String u) {
		
		String sql = "DELETE FROM iscrizioni WHERE codiceLega=\""+codiceLega +"\" AND squadra=\""+s+"\" AND utente=\""+u +"\" ";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<IscrizioneLega> list() {
		String sql= "SELECT * FROM iscrizioni";
		
		RowMapper<IscrizioneLega> rowmapper = new RowMapper<IscrizioneLega>() {
			@Override
			public IscrizioneLega mapRow(ResultSet rs, int rowNum) throws SQLException{
				String codice = rs.getString("codiceLega");
				String squadra = rs.getString("squadra");
				String utente = rs.getString("utente");
				
				daoS = new SquadraDAOImpl();
				Squadra squad = daoS.get(squadra,utente);
				
				return new IscrizioneLega(codice, squad);
			}
		};
		
		return jdbcTemplate.query(sql, rowmapper);
	}

}
