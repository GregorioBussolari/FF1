package net.codejava.user.model;

//Squadra.java

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Squadra {
 private String nomeSquadra;
 private float wheelRimanenti;
 private Utente utente; 
 private static final float WHEELS = 100;

 // Associazione con Componenti (molti a molti con cardinalità specifiche)
 private Pilota[] piloti;
 private Costruttore costruttore;
 private Leggenda leggenda;

 public Squadra() {
     this.piloti = new Pilota[3];
     this.costruttore = new Costruttore();
     this.leggenda= new Leggenda();
 }

 public Squadra(String nomeSquadra, float wheelRimanenti, Utente utente, Pilota[] piloti, Costruttore costruttore, Leggenda leggenda) {
     this.nomeSquadra = nomeSquadra;
     this.utente = utente;
     this.piloti = piloti;
     this.costruttore = costruttore;
     this.leggenda = leggenda;
     
     this.wheelRimanenti = wheelRimanenti;
 }
 public Squadra(String nomeSquadra, Utente utente, Pilota[] piloti, Costruttore costruttore, Leggenda leggenda) throws IllegalArgumentException{
     this.nomeSquadra = nomeSquadra;
     this.utente = utente;
     this.piloti = piloti;
     this.costruttore = costruttore;
     this.leggenda = leggenda;
     
     if(calcolaWheelsRimanenti() < 0) {
    	 throw new IllegalArgumentException();
     }
     else {
    	 this.wheelRimanenti = calcolaWheelsRimanenti();
     }
 }
 
 private float calcolaWheelsRimanenti() {
	 List<Componente>comps = this.getComponenti();
	 float totPrezzo = 0;
	 for(Componente c : comps) {
		 totPrezzo+=c.getPrezzo();
	 }
	 return WHEELS - totPrezzo;
 }
  

 public String getNomeSquadra() {
     return nomeSquadra;
 }

 public void setNomeSquadra(String nomeSquadra) {
     this.nomeSquadra = nomeSquadra;
 }


 public float getWheelRimanenti() {
     return wheelRimanenti;
 }

 public void setWheelRimanenti(int wheelRimanenti) {
     this.wheelRimanenti = wheelRimanenti;
 }

 public Utente getUtente() {
     return utente;
 }

 public void setUtente(Utente utente) {
     this.utente = utente;
 }

 public Pilota[] getPiloti() {
     return piloti;
 }

 public void setPiloti(Pilota[] piloti) {
     this.piloti = piloti;
 }

 public Costruttore getCostruttori() {
     return costruttore;
 }

 public void setCostruttore(Costruttore costruttore) {
     this.costruttore = costruttore;
 }

 public Leggenda getLeggende() {
     return leggenda;
 }

 public void setLeggende(Leggenda leggenda) {
     this.leggenda = leggenda;
 }



 // Metodi suggeriti dal diagramma, implementazioni base:
 public List<Componente> getComponenti() {
     List<Componente> allComponents = new ArrayList<>();
     allComponents.add(piloti[0]);
     allComponents.add(piloti[1]);
     allComponents.add(piloti[2]);
     allComponents.add(costruttore);
     allComponents.add(leggenda);
     return allComponents;
 }

 public float calcolaPunteggioTotale(List<RisultatoGP> listaRisultatiGP) {
	 List<Componente> allComponents= this.getComponenti();
	 float result = 0.0f;
	 for(Componente c : allComponents) {
		 result+=c.calcolaPunteggioTot(listaRisultatiGP);
	 }
     return result; // Placeholder
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     Squadra squadra = (Squadra) o;
     return Objects.equals(nomeSquadra, squadra.nomeSquadra) &&
            Objects.equals(utente, squadra.utente); // Una squadra è unica per nome e utente
 }

 @Override
 public int hashCode() {
     return Objects.hash(nomeSquadra, utente);
 }

 @Override
 public String toString() {
     return "Squadra{" +
            "nomeSquadra='" + nomeSquadra + '\'' +
            ", wheelRimanenti=" + wheelRimanenti +
            ", utente=" + (utente != null ? utente.getUsername() : "N/A") +
            ", piloti=" + piloti +
            ", costruttori=" + costruttore +
            ", leggende=" + leggenda +
            '}';
 }
}
