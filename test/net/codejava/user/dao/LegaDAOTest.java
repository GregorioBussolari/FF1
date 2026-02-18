package net.codejava.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.codejava.user.model.Lega;


class LegaDAOTest {

	
	private LegaDAO dao= new LegaDAOImpl();
	

	@Test
	void testSave() {
		String nomeLega="GreggeDiMucche";
		int num = 20;
		String utente= "Gregge";	
		Lega lega= new Lega(nomeLega, num, utente);
		int result = dao.save(lega);
		
		assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		String codice="exF97mR0JX";
		String nomeLega="LegaNord";
		String utente= "Lorenzo";	
		int num = 8;
		Lega lega= new Lega(codice,nomeLega, num, utente);
		int result = dao.update(lega);
		
		assertTrue(result > 0);
	}

	@Test
	void testGet() {
		String codice="exF97mR0JX";
		String nomeLega="LegaNord";
		String utente= "Lorenzo";	
		int num = 8;
		Lega lega= new Lega(codice,nomeLega, num, utente);
		
		if(lega!=null) {
			System.out.println(lega);
		}
		assertNotNull(lega);
	}

	@Test
	void testDelete() {
		String codice = "TmBwLWAcQZ";
		int result = dao.delete(codice);
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List<Lega> lista = dao.list();
		
		for(Lega isc : lista) {
			System.out.println(isc);
		}
		
		assertTrue(!lista.isEmpty());
	}

}

