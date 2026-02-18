package net.codejava.user.model;

import java.security.SecureRandom;

//Lega.java

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//import net.codejava.user.dao.IscrizioneLegaDAO;
import net.codejava.user.dao.LegaDAO;
import net.codejava.user.dao.LegaDAOImpl;
//import net.codejava.user.dao.LeggendaDAOImpl;
import net.codejava.user.dao.RisultatoGPDAO;
import net.codejava.user.dao.RisultatoGPDAOImpl;
//import net.codejava.user.model.*;

public class Lega {
 private String codiceLega;
 private String nomeLega;
 private int numeroMaxPartecipanti;

 private String creatore;

 private List<Squadra> squadrePartecipanti;
 
 private LegaDAO daoL=new LegaDAOImpl();;
 private RisultatoGPDAO daoR;
 
 private static final String CARATTERI = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
 private static final int LUNGHEZZA_CODICE = 10;
 private static final SecureRandom random = new SecureRandom();



  
	
 public Lega() {
     this.squadrePartecipanti = new ArrayList<>();
 }

 public Lega(String nomeLega, int numeroMaxPartecipanti, String creatore) {
     this.nomeLega = nomeLega;
     this.numeroMaxPartecipanti = numeroMaxPartecipanti;
     this.creatore = creatore;
     this.squadrePartecipanti = new ArrayList<>();
     String codiceLega=generaCodice();
     
     while(!validate(codiceLega)) {
    	 codiceLega=generaCodice();
     }
     this.codiceLega=codiceLega;
 }
 public Lega(String codice, String nomeLega, int numeroMaxPartecipanti, String creatore) {
	 this.codiceLega= codice;
     this.nomeLega = nomeLega;
     this.numeroMaxPartecipanti = numeroMaxPartecipanti;
     this.creatore = creatore;
     this.squadrePartecipanti = new ArrayList<>();
 }
 
 public Lega(String nomeLega, String codice,int numPartecipanti, List<Squadra> squadre, String creatore) {
     this.nomeLega = nomeLega;
     this.numeroMaxPartecipanti = numPartecipanti;
     this.creatore = creatore;
     this.squadrePartecipanti = squadre;
     this.codiceLega=codice;
 }
 
 private static String generaCodice() {
     StringBuilder sb = new StringBuilder(LUNGHEZZA_CODICE);
     for (int i = 0; i < LUNGHEZZA_CODICE; i++) {
         int index = random.nextInt(CARATTERI.length());
         sb.append(CARATTERI.charAt(index));
     }
     return sb.toString();
 }
 
 private boolean validate(String codice) {
	 
	 List<Lega> leghe=daoL.list();
	 for(Lega l : leghe) {
		 if(l.getCodiceLega().equals(codice)) {
			return false;
		 }
	 }
	 return true;
 }

 public String getCodiceLega() {
     return codiceLega;
 }

 public void setCodiceLega(String codiceLega) {
     this.codiceLega = codiceLega;
 }

 public String getNomeLega() {
     return nomeLega;
 }

 public void setNomeLega(String nomeLega) {
     this.nomeLega = nomeLega;
 }

 public int getNumeroMaxPartecipanti() {
     return numeroMaxPartecipanti;
 }

 public void setNumeroMaxPartecipanti(int numeroMaxPartecipanti) {
     this.numeroMaxPartecipanti = numeroMaxPartecipanti;
 }

 public String getCreatore() {
     return creatore;
 }

 public void setCreatore(String creatore) {
     this.creatore = creatore;
 }

 public List<Squadra> getSquadrePartecipanti() {
     return squadrePartecipanti;
 }

 public void setSquadrePartecipanti(List<Squadra> squadrePartecipanti) {
     this.squadrePartecipanti = squadrePartecipanti;
 }

 public void aggiungiSquadraPartecipante(Squadra squadra) {
     if (squadra != null && !this.squadrePartecipanti.contains(squadra)) {
         this.squadrePartecipanti.add(squadra);
     }
 }


 public List<Utente> getPartecipanti() {
     List<Utente> partecipanti = new ArrayList<>();
     for (Squadra s : squadrePartecipanti) {
         partecipanti.add(s.getUtente());
     }
     return partecipanti;
 }

 public List<Squadra> getSquadre() {
     return new ArrayList<>(this.squadrePartecipanti);
 }
 
 public List<Squadra>  getClassificaLega(){
	daoR = new RisultatoGPDAOImpl();
		
	//ordinamento
	List<RisultatoGP> risultati = daoR.list();
	this.squadrePartecipanti.sort((s1, s2) -> Float.compare(s2.calcolaPunteggioTotale(risultati), s1.calcolaPunteggioTotale(risultati)));
	return this.squadrePartecipanti;
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     Lega lega = (Lega) o;
     return Objects.equals(codiceLega, lega.codiceLega);
 }

 @Override
 public int hashCode() {
     return Objects.hash(codiceLega);
 }

 @Override
 public String toString() {
     return "Lega{" +
            "codiceLega='" + codiceLega + '\'' +
            ", nomeLega='" + nomeLega + '\'' +
            ", numeroMaxPartecipanti=" + numeroMaxPartecipanti +
            ", creatore=" + (creatore != null ? creatore : "N/A") +
            ", squadrePartecipanti=" + squadrePartecipanti.size() +
            '}';
 }
}