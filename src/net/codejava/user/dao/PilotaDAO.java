package net.codejava.user.dao;

import java.util.List;

import net.codejava.user.model.Pilota;

public interface PilotaDAO {
	
	public int save(Pilota p);
	
	public int update(Pilota p);
	
	public Pilota get(String nome);
	
	public int delete(String nome);
	
	public List<Pilota> list();
}
