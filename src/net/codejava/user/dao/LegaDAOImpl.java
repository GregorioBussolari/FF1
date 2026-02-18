package net.codejava.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import net.codejava.user.db.DBDataSource;
import net.codejava.user.model.*;



public class LegaDAOImpl implements LegaDAO {
	
	private IscrizioneLegaDAO daoL= new IscrizioneLegaDAOImpl();
	
	private JdbcTemplate jdbcTemplate;
	
	public LegaDAOImpl() {
		DataSource dataSource = DBDataSource.setup();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Lega l) {
		String sql = "INSERT INTO leghe (codiceLega, gestore, nome, numMaxPartecipanti) VALUES (?, ?, ?, ?) ";
		return jdbcTemplate.update(sql, l.getCodiceLega(),l.getCreatore(), l.getNomeLega(), l.getNumeroMaxPartecipanti());
	}

	@Override
	public int update(Lega l) {
		String sql = "UPDATE leghe SET codiceLega=?, gestore=?, nome=?, numMaxPartecipanti=? WHERE codiceLega=?";
		return jdbcTemplate.update(sql, l.getCodiceLega(),l.getCreatore(), l.getNomeLega(), l.getNumeroMaxPartecipanti(), l.getCodiceLega());
	}

	@Override
	public Lega get(String codiceLega) {
		String sql = "SELECT * FROM leghe WHERE codiceLega=\""+codiceLega +"\"";
		ResultSetExtractor<Lega> extractor = new ResultSetExtractor<Lega>() {
			@Override
			public Lega extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()) {
					String codice = rs.getString("codiceLega");
					String gestore = rs.getString("gestore");
					String nome = rs.getString("nome");
					int numPartecipanti = rs.getInt("numMaxPartecipanti");
					
					List<Squadra> squadre = new ArrayList<Squadra>();
					
					daoL = new IscrizioneLegaDAOImpl();
					List<IscrizioneLega> iscrizioni = daoL.list();
					
					for(IscrizioneLega i : iscrizioni) {
						if(i.getCodiceLega().equals(codiceLega)) {
							squadre.add(i.getSquadra());
						}
					}
					
					return new Lega(nome, codice,numPartecipanti,squadre,gestore);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(String codiceLega) {
		
		String sql = "DELETE FROM leghe WHERE codiceLega=\""+codiceLega +"\"";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Lega> list() {
		String sql= "SELECT * FROM leghe";
		
		RowMapper<Lega> rowmapper = new RowMapper<Lega>() {
			@Override
			public Lega mapRow(ResultSet rs, int rowNum) throws SQLException{
				String codice = rs.getString("codiceLega");
				String gestore = rs.getString("gestore");
				String nome = rs.getString("nome");
				int numPartecipanti = rs.getInt("numMaxPartecipanti");
				
				List<Squadra> squadre = new ArrayList<Squadra>();
				
				daoL = new IscrizioneLegaDAOImpl();
				List<IscrizioneLega> iscrizioni = daoL.list();
				
				for(IscrizioneLega i : iscrizioni) {
					if(i.getCodiceLega().equals(codice)) {
						squadre.add(i.getSquadra());
					}
				}
				return new Lega(nome, codice,numPartecipanti,squadre,gestore);
			}
		};
		
		return jdbcTemplate.query(sql, rowmapper);
	}

}
