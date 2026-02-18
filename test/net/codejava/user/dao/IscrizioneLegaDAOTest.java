package net.codejava.user.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.codejava.user.model.IscrizioneLega;
import net.codejava.user.model.Squadra;

class IscrizioneLegaDAOTest {

	
	private IscrizioneLegaDAO dao= new IscrizioneLegaDAOImpl();
	private SquadraDAO daoS= new SquadraDAOImpl();
	
	
	
	
	@Test
	void testSave() {
		Squadra s = daoS.get("GreggeDiMucche", "Gregge");
		IscrizioneLega u = new IscrizioneLega("AODA103231",s);
		int result = dao.save(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		Squadra s = daoS.get("GreggeDiMucche", "Gregge");
		IscrizioneLega u = new IscrizioneLega("AODA103231",s);
		int result = dao.update(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testGet() {
		String codice = "AODA103231";
		String squadra = "GreggeDiMucche";
		String utente = "Gregge";
		IscrizioneLega u = dao.get(codice, squadra, utente);
		
		if(u!=null) {
			System.out.println(u);
		}
		assertNotNull(u);
	}

	@Test
	void testDelete() {
		String codice = "ytvY1Yd1PF";
		String squadra = "I carciofi";
		String utente = "Lorenzo";
		int result = dao.delete(codice, squadra, utente);
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List<IscrizioneLega> lista = dao.list();
		
		for(IscrizioneLega isc : lista) {
			System.out.println(isc);
		}
		
		assertTrue(!lista.isEmpty());
	}

}

