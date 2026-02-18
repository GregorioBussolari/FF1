package net.codejava.user.dao;
import net.codejava.user.model.IscrizioneLega;

import java.util.List;

public interface IscrizioneLegaDAO {
	public int save(IscrizioneLega l);
	
	public int update(IscrizioneLega l);
	
	public IscrizioneLega get(String codiceLega, String s, String u);
	
	public int delete(String codiceLega, String s, String u);
	
	public List<IscrizioneLega> list();
}
