package net.codejava.user.dao;

import java.util.List;

import net.codejava.user.model.Costruttore;

public interface CostruttoreDAO {
	
	public int save(Costruttore p);
	
	public int update(Costruttore p);
	
	public Costruttore get(String nome);
	
	public int delete(String nome);
	
	public List<Costruttore> list();
}

