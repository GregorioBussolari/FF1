package net.codejava.user.dao;
import net.codejava.user.model.Lega;
import java.util.List;

public interface LegaDAO {
	public int save(Lega l);
	
	public int update(Lega l);
	
	public Lega get(String codiceLega);
	
	public int delete(String codiceLega);
	
	public List<Lega> list();
}
