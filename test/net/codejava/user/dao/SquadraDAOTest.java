package net.codejava.user.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.codejava.user.model.*;

public class SquadraDAOTest {
	
	private SquadraDAO dao = new SquadraDAOImpl();
	private UtenteDAO daoU = new UtenteDAOImpl();
	private PilotaDAO daoP = new PilotaDAOImpl();
	private CostruttoreDAO daoC = new CostruttoreDAOImpl();
	private LeggendaDAO daoL = new LeggendaDAOImpl();
	
	
	@Test
	void testSave() {
		Utente ut= daoU.get("Lorenzo");
		Pilota p1 = daoP.get("Charles Leclerc");
		Pilota p2 = daoP.get("Gabriel Bortoleto");
		Pilota p3 = daoP.get("Max Verstappen");
		Costruttore c = daoC.get("Mercedes");
		Leggenda l = daoL.get("Micheal Schumacher");
		Pilota[] piloti = new Pilota[3];
		piloti[0] = p1;
		piloti[1] = p2;
		piloti[2] = p3;
		float wheelsRimanenti =100- p1.getPrezzo()-p2.getPrezzo()-p3.getPrezzo()- c.getPrezzo()-l.getPrezzo();
		
		Squadra s = new Squadra("Nigbean",wheelsRimanenti, ut,piloti,c,l);
		int result = dao.save(s);
		
		assertTrue(result > 0);
	}

	@Test
	void testUpdate() {
		Utente ut= daoU.get("Lorenzo");
		Pilota p1 = daoP.get("Franco Colapinto");
		Pilota p2 = daoP.get("Gabriel Bortoleto");
		Pilota p3 = daoP.get("Yuki Tsunoda");
		Costruttore c = daoC.get("Ferrari");
		Leggenda l = daoL.get("Ayrton Senna");
		Pilota[] piloti = new Pilota[3];
		piloti[0] = p1;
		piloti[1] = p2;
		piloti[2] = p3;
		float wheelsRimanenti =100- p1.getPrezzo()-p2.getPrezzo()-p3.getPrezzo()- c.getPrezzo()-l.getPrezzo();

		Squadra u = new Squadra("I carciofi",wheelsRimanenti, ut,piloti,c,l);
		int result = dao.update(u);
		
		assertTrue(result > 0);
	}

	@Test
	void testGet() {
		String nome = "I Carciofi";
		String utente = "Lorenzo";
		
		Squadra u = dao.get(nome, utente);
		if(u!=null) {
			System.out.println(u);
		}
		assertNotNull(u);
	}

	@Test
	void testDelete() {
		String nome = "RedBullRace";
		String utente = "Lorenzo";
		int result = dao.delete(nome, utente);
		assertTrue(result>0);
	}

	@Test
	void testList() {
		List<Squadra> listSq= dao.list();
		
		for(Squadra u : listSq) {
			System.out.println(u);
		}
		
		assertTrue(!listSq.isEmpty());
	}
}
