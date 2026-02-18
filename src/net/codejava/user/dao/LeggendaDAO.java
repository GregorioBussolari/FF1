package net.codejava.user.dao;

import java.util.List;

import net.codejava.user.model.Leggenda;

public interface LeggendaDAO {
	public int save(Leggenda p);
	
	public int update(Leggenda p);
	
	public Leggenda get(String nome);
	
	public int delete(String nome);
	
	public List<Leggenda> list();
}
