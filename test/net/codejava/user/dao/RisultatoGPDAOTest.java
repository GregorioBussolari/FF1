package net.codejava.user.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.codejava.user.model.Costruttore;
import net.codejava.user.model.Leggenda;
import net.codejava.user.model.Pilota;
import net.codejava.user.model.RisultatoGP;

public class RisultatoGPDAOTest {
	
	
	private RisultatoGPDAO dao= new RisultatoGPDAOImpl();
	private PilotaDAO daoP;
	private CostruttoreDAO daoC;
	private LeggendaDAO daoL;
	
	
	
	
	@Test
	void testSave() {
		daoP = new PilotaDAOImpl();
		//Pilota u = new Pilota(12, "Gabriel Bortoleto", "Pilota brasiliano rookie", "BRA", "Sauber");
		Pilota p = daoP.get("Liam Lawson");
		//Leggenda l = daoL.get("Juan Manuel Fangio");
		//Costruttore c = daoC.get("RB");
		RisultatoGP ris = new RisultatoGP(-10.0f, "CanadaGP", p);
		dao = new RisultatoGPDAOImpl();
		int result = dao.save(ris);
		
		assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		Pilota u = new Pilota(12, "Gabriel Bortoleto", "Pilota brasiliano rookie", "BRA", "Sauber");
		RisultatoGP ris = new RisultatoGP(6.0f, "MonacoGP", u);
		int result = dao.update(ris);
		
		assertTrue(result > 0);
	}

	@Test
	void testGet() {
		String granpremio = "MonacoGP";
		String compo = "Gabriel Bortoleto";
		RisultatoGP u = dao.get(granpremio, compo);
		if(u!=null) {
			System.out.println(u);
		}
		assertNotNull(u);
	}

	@Test
	void testDelete() {
		String gp = "CanadaGP";
		String compo = "Alexander Albon";
		int result = dao.delete(gp, compo);
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List<RisultatoGP> listPiloti = dao.list();
		
		for(RisultatoGP u : listPiloti) {
			System.out.println(u);
		}
		
		assertTrue(!listPiloti.isEmpty());
	}

}
