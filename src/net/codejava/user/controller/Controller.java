package net.codejava.user.controller;
import java.util.List;


import net.codejava.user.dao.*;
import net.codejava.user.model.*;

public class Controller {
	private SquadraDAO daoS;
	private UtenteDAO daoU;
	private PilotaDAO daoP;
	private CostruttoreDAO daoC;
	private LeggendaDAO daoL;
	private RisultatoGPDAO daoR;
	private LegaDAO daoLega;
	IscrizioneLegaDAO daoIscrizioneLega;
	
	public Controller() {
	}
	
	
	
	public Squadra creaSquadra(String nome, String[] p, String costruttore, String leggenda, String username){
		
		daoU = new UtenteDAOImpl();
		Utente u = daoU.get(username);
		
		daoP = new PilotaDAOImpl();
		Pilota[] piloti = new Pilota[3];
		for(int i=0; i<3; i++) {
			piloti[i] = daoP.get(p[i]);
		}
		daoC = new CostruttoreDAOImpl();
		Costruttore c = daoC.get(costruttore);
		
		daoL = new LeggendaDAOImpl();
		Leggenda l = daoL.get(leggenda);
		Squadra newSquadra;
		try {
			boolean flag = false;
			//setup
			daoS = new SquadraDAOImpl();
			List<Squadra> squadreDB = daoS.list();
			//logica
			for(Squadra s : squadreDB) {
				if(s.getNomeSquadra().equals(nome) && s.getUtente().getNome().equals(username)) {
					flag = true;
				}
			}
			if(!flag) {
				newSquadra = new Squadra(nome, u, piloti, c, l);
				daoS.save(newSquadra);
			}
			else 
				newSquadra = null;
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Errore nella crezione della Squadra");
			newSquadra = null;
		}
		return newSquadra;
	}
	
	
	public float calcolaPunteggioSquadra(Squadra s) {
		
		
		daoR = new RisultatoGPDAOImpl();
		
		List<RisultatoGP> rgplist = daoR.list();
		
		float ris = 0;
		
		List<Componente> componenti = s.getComponenti();
		
		for(Componente c : componenti) {
			ris+=c.calcolaPunteggioTot(rgplist);
		}
		
		return ris;
	}
	
	public Lega creaLega(String nomeLega, int numeroPartecipanti, String creatore, Squadra squadra) {
		
		
		Lega resultLega = null;
		
		daoLega = new LegaDAOImpl();
		
		//Creazione Oggetto Lega
		resultLega = new Lega(nomeLega, numeroPartecipanti, creatore);
		daoLega.save(resultLega);	
		
		//iscrizione squadra scelta
		resultLega.aggiungiSquadraPartecipante(squadra);
		
		//salvataggio Iscrizione in modo conforme
		IscrizioneLega isc = new IscrizioneLega(resultLega.getCodiceLega(), squadra);
		
		
		daoIscrizioneLega = new IscrizioneLegaDAOImpl();
		daoIscrizioneLega.save(isc);
		
		return resultLega;
	}
	
	
	public boolean iscriviALega(String codiceLega, Squadra squadra) {
		
		boolean res = false;
		daoLega = new LegaDAOImpl();
		
		List<Lega> leghe = daoLega.list();
		
		Lega selected = null;
		
		for(Lega l : leghe) {
			if(l.getCodiceLega().equals(codiceLega)) {
				selected = l;
				res = true;
				break;
			}
		}
		
		if(res) {
			//iscrizione squadra scelta
			selected.aggiungiSquadraPartecipante(squadra);
			
			//salvataggio Iscrizione in modo conforme
			IscrizioneLega isc = new IscrizioneLega(selected.getCodiceLega(), squadra);
			
			
			
			daoIscrizioneLega = new IscrizioneLegaDAOImpl();
			
			List<IscrizioneLega> lista = daoIscrizioneLega.list();
			
			for(IscrizioneLega i : lista) {
				if(i.getCodiceLega().equals(codiceLega) && i.getSquadra().getUtente().getUsername().equals(squadra.getUtente().getUsername())) {
					res = false;
					break;
				}
			}
			
			
			daoIscrizioneLega.save(isc);
		}
		
		return res;
		
	}
	

}
