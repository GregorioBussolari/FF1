package net.codejava.user.dao;
import net.codejava.user.model.Squadra;
import java.util.List;

public interface SquadraDAO {
	public int save(Squadra s);
	
	public int update(Squadra s);
	
	public Squadra get(String nome, String username);
	
	public int delete(String nome,String username);
	
	public List<Squadra> list();
}
