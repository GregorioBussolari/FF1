package net.codejava.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.codejava.user.model.Pilota;

class PilotaDAOTest {

	
	private PilotaDAO dao = new PilotaDAOImpl();
	
	
	
	
	@Test
	void testSave() {
		Pilota u = new Pilota(12, "Gabriel Bortoleto", "Pilota brasiliano rookie", "BRA", "Sauber");
		int result = dao.save(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		Pilota u = new Pilota(21, "Kimi Antonelli", "Giovane promessa di Casalecchio", "ITA", "Mercedes");
		int result = dao.update(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testGet() {
		String nome = "Charles Leclerc";
		Pilota u = dao.get(nome);
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
		List<Pilota> listPiloti = dao.list();
		
		for(Pilota u : listPiloti) {
			System.out.println(u);
		}
		
		assertTrue(!listPiloti.isEmpty());
	}

}

