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
import net.codejava.user.model.Squadra;
import net.codejava.user.model.Utente;


public class SquadraDAOImpl implements SquadraDAO {
	
	
	private UtenteDAO daoU;
	private PilotaDAO daoP;
	private CostruttoreDAO daoC;
	private LeggendaDAO daoL;
	
	
	
	private JdbcTemplate jdbcTemplate;
	
	public SquadraDAOImpl() {
		DataSource dataSource = DBDataSource.setup();
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int save(Squadra s) {
		String sql = "INSERT INTO squadre (nome, utente, wheelsRimanenti, pilota1, pilota2, pilota3, costruttore, leggenda) VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
		return jdbcTemplate.update(sql, s.getNomeSquadra(), s.getUtente().getUsername(), s.getWheelRimanenti(), s.getPiloti()[0].getNomeCompleto(),s.getPiloti()[1].getNomeCompleto(),s.getPiloti()[2].getNomeCompleto(), s.getCostruttori().getNomeCompleto(), s.getLeggende().getNomeCompleto());
	}

	@Override
	public int update(Squadra s) {
		String sql = "UPDATE squadre SET nome=?, utente=?, wheelsRimanenti=?, pilota1=?, pilota2=?, pilota3=?, costruttore=?, leggenda=? WHERE nome=? AND utente=?";
		return jdbcTemplate.update(sql, s.getNomeSquadra(), s.getUtente().getUsername(), s.getWheelRimanenti(), s.getPiloti()[0].getNomeCompleto(),s.getPiloti()[1].getNomeCompleto(),s.getPiloti()[2].getNomeCompleto(), s.getCostruttori().getNomeCompleto(), s.getLeggende().getNomeCompleto(), s.getNomeSquadra(), s.getUtente().getUsername());
	}

	@Override
	public Squadra get(String nome, String username) {
		String sql = "SELECT * FROM squadre WHERE nome=\""+nome +"\" AND utente=\""+username+"\"";
		ResultSetExtractor<Squadra> extractor = new ResultSetExtractor<Squadra>() {
			@Override
			public Squadra extractData(ResultSet rs) throws SQLException, DataAccessException{
				if(rs.next()) {
					String nome = rs.getString("nome");
					String utente = rs.getString("utente");
					Float wheelsRimanenti = rs.getFloat("wheelsRimanenti");
					String pilota1 = rs.getString("pilota1");
					String pilota2 = rs.getString("pilota2");
					String pilota3 = rs.getString("pilota3");
					String costruttore = rs.getString("costruttore");
					String leggenda = rs.getString("leggenda");
					daoU = new UtenteDAOImpl();
					Utente ut= daoU.get(utente);
					daoP = new PilotaDAOImpl();
					Pilota p1 = daoP.get(pilota1);
					Pilota p2 = daoP.get(pilota2);
					Pilota p3 = daoP.get(pilota3);
					Pilota[] piloti = new Pilota[3];
					piloti[0] = p1;
					piloti[1] = p2;
					piloti[2] = p3;
					daoC = new CostruttoreDAOImpl();
					Costruttore costrut = daoC.get(costruttore);
					daoL = new LeggendaDAOImpl();
					Leggenda leg = daoL.get(leggenda);
					return new Squadra(nome,wheelsRimanenti, ut ,piloti,costrut,leg);
				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(String nome,String username) {
		
		String sql = "DELETE FROM squadre WHERE nome=\""+nome +"\" AND utente=\""+username+"\"";
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Squadra> list() {
		String sql= "SELECT * FROM squadre";
		
		RowMapper<Squadra> rowmapper = new RowMapper<Squadra>() {
			@Override
			public Squadra mapRow(ResultSet rs, int rowNum) throws SQLException{
				String nome = rs.getString("nome");
				String utente = rs.getString("utente");
				Float wheelsRimanenti = rs.getFloat("wheelsRimanenti");
				String pilota1 = rs.getString("pilota1");
				String pilota2 = rs.getString("pilota2");
				String pilota3 = rs.getString("pilota3");
				String costruttore = rs.getString("costruttore");
				String leggenda = rs.getString("leggenda");
				daoU = new UtenteDAOImpl();
				Utente ut= daoU.get(utente);
				daoP = new PilotaDAOImpl();
				Pilota p1 = daoP.get(pilota1);
				Pilota p2 = daoP.get(pilota2);
				Pilota p3 = daoP.get(pilota3);
				Pilota[] piloti = new Pilota[3];
				piloti[0] = p1;
				piloti[1] = p2;
				piloti[2] = p3;
				daoC = new CostruttoreDAOImpl();
				Costruttore costrut = daoC.get(costruttore);
				daoL = new LeggendaDAOImpl();
				Leggenda leg = daoL.get(leggenda);
				return new Squadra(nome,wheelsRimanenti, ut ,piloti,costrut,leg);
			}
		};
		
		return jdbcTemplate.query(sql, rowmapper);
	}

}
