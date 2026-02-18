package net.codejava.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.codejava.user.model.Utente;

class UtenteDAOTest {

	
	private UtenteDAO dao=new UtenteDAOImpl();
	
	
	
	
	@Test
	void testSave() {
		Utente u = new Utente("Zana", "password3", "L", "Z", "ITA");
		int result = dao.save(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		Utente u = new Utente("prova", "password", "Utente", "DiProva", "USA");
		int result = dao.update(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testGet() {
		String username = "Gregge";
		Utente u = dao.get(username);
		if(u!=null) {
			System.out.println(u);
		}
		assertNotNull(u);
	}

	@Test
	void testDelete() {
		String username = "prova";
		int result = dao.delete(username);
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List<Utente> listUtenti = dao.list();
		
		for(Utente u : listUtenti) {
			System.out.println(u);
		}
		
		assertTrue(!listUtenti.isEmpty());
	}

}
