package net.codejava.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.codejava.user.model.Leggenda;

class LeggendaDAOTest {

	
	private LeggendaDAO dao= new LeggendaDAOImpl();
	

	
	@Test
	void testSave() {
		Leggenda u = new Leggenda(24, "Niki Lauda", "3 volte campione", "AUT", "+1 punto per ogni pilota che finisce la gara senza essere doppiato");
		int result = dao.save(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		Leggenda u = new Leggenda(28, "Micheal Schumacher", "7 volte campione", "GER", "+15 punti per ogni pilota della scuderia Ferrari sul podio");
		int result = dao.update(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testGet() {
		String nome = "Micheal Schumacher";
		Leggenda u = dao.get(nome);
		if(u!=null) {
			System.out.println(u);
		}
		assertNotNull(u);
	}

	@Test
	void testDelete() {
		String username = "Charles Leclerc";
		int result = dao.delete(username);
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List<Leggenda> listPiloti = dao.list();
		
		for(Leggenda u : listPiloti) {
			System.out.println(u);
		}
		
		assertTrue(!listPiloti.isEmpty());
	}

}

