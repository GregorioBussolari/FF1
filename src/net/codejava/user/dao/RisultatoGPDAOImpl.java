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
import net.codejava.user.model.Leggenda;
import net.codejava.user.model.Pilota;
import net.codejava.user.model.RisultatoGP;

public class RisultatoGPDAOImpl implements RisultatoGPDAO {

	
private JdbcTemplate jdbcTemplate;
	
	
	private PilotaDAO daoP;
	private CostruttoreDAO daoC;
	private LeggendaDAO daoL;
	
	
	
	public RisultatoGPDAOImpl() {
		DataSource dataSource = DBDataSource.setup();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(RisultatoGP p) {
		String sql = "INSERT INTO risultatigp (punteggio, granpremio, componente) VALUES (?, ?, ?) ";
		return jdbcTemplate.update(sql, p.getPunteggioOttenuto(), p.getGranPremio(), p.getComponente().getNomeCompleto());
	}

	@Override
	public int update(RisultatoGP u) {
		String sql = "UPDATE risultatigp SET punteggio=?, granpremio=?, componente=? WHERE granpremio=? AND componente=?";
		return jdbcTemplate.update(sql, u.getPunteggioOttenuto(), u.getGranPremio(), u.getComponente().getNomeCompleto(), u.getGranPremio(), u.getComponente().getNomeCompleto());
	}

	@Override
	public RisultatoGP get(String granpremio, String c) {
		String sql = "SELECT * FROM risultatigp WHERE granpremio=\""+ granpremio+"\" AND componente=\""+c+"\"  " ;
		ResultSetExtractor<RisultatoGP> extractor = new ResultSetExtractor<RisultatoGP>() {
			@Override
			public RisultatoGP extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()) {
					Float punteggio = rs.getFloat("punteggio");
					String granpremio = rs.getString("granpremio");
					String compo = rs.getString("componente");
					daoP = new PilotaDAOImpl();
					Pilota p1;
					Costruttore c;
					Leggenda l;
					if((p1 = daoP.get(compo))==null) {
						daoC = new CostruttoreDAOImpl();
						if((c = daoC.get(compo))==null) {
							daoL = new LeggendaDAOImpl();
							l = daoL.get(compo);
							return new RisultatoGP(punteggio, granpremio, l);
						}
						else {
							return new RisultatoGP(punteggio, granpremio, c);
						}
					}
					else {
						return new RisultatoGP(punteggio, granpremio, p1);
					}
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(String granpremio, String c) {
		
		String sql = "DELETE FROM risultatigp WHERE granpremio=\""+ granpremio+"\" AND componente=\""+c+"\"  ";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<RisultatoGP> list() {
		String sql= "SELECT * FROM risultatigp";
		
		RowMapper<RisultatoGP> rowmapper = new RowMapper<RisultatoGP>() {
			@Override
			public RisultatoGP mapRow(ResultSet rs, int rowNum) throws SQLException{
				Float punteggio = rs.getFloat("punteggio");
				String granpremio = rs.getString("granpremio");
				String compo = rs.getString("componente");
				daoP = new PilotaDAOImpl();
				Pilota p1;
				Costruttore c;
				Leggenda l;
				if((p1 = daoP.get(compo))==null) {
					daoC = new CostruttoreDAOImpl();
					if((c = daoC.get(compo))==null) {
						daoL = new LeggendaDAOImpl();
						l = daoL.get(compo);
						return new RisultatoGP(punteggio, granpremio, l);
					}
					else {
						return new RisultatoGP(punteggio, granpremio, c);
					}
				}
				else {
					return new RisultatoGP(punteggio, granpremio, p1);
				}
			}
		};
		
		return jdbcTemplate.query(sql, rowmapper);
	}
}
