package net.codejava.user.dao;

import java.util.List;

import net.codejava.user.model.RisultatoGP;

public interface RisultatoGPDAO {
	
	public int save(RisultatoGP p);
	
	public int update(RisultatoGP p);
	
	public RisultatoGP get(String granpremio, String componente);
	
	public int delete(String granpremio, String componente);
	
	public List<RisultatoGP> list();
}
