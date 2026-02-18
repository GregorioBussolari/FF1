package net.codejava.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.codejava.user.model.Costruttore;

class CostruttoreDAOTest {

	
	private CostruttoreDAO dao = new CostruttoreDAOImpl();
	
	
	@Test
	void testSave() {
		Costruttore u = new Costruttore(14, "Haas", "Scuderia americana", "USA");
		int result = dao.save(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		Costruttore u = new Costruttore(21, "Kimi Antonelli", "Giovane promessa di Casalecchio", "ITA");
		int result = dao.update(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testGet() {
		String nome = "Ferrari";
		Costruttore u = dao.get(nome);
		if(u!=null) {
			System.out.println(u);
		}
		assertNotNull(u);
	}

	@Test
	void testDelete() {
		String username = "Ferrari";
		int result = dao.delete(username);
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List<Costruttore> listPiloti = dao.list();
		
		for(Costruttore u : listPiloti) {
			System.out.println(u);
		}
		
		assertTrue(!listPiloti.isEmpty());
	}

}

