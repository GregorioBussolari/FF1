package net.codejava.user.dao;

import java.util.List;

import net.codejava.user.model.Utente;

public interface UtenteDAO {
	public int save(Utente u);
	
	public int update(Utente u);
	
	public Utente get(String username);
	
	public int delete(String username);
	
	public List<Utente> list();
}
