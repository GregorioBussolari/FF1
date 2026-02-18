package Beans;

//Squadra.java

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Squadra {
 private String nomeSquadra;
 private float punteggioFantasyConsiglio;
 private int wheelRimanenti;
 private String nomeGiocatore; // Da diagramma, sembra un attributo della squadra
 private String descrizione; // Da diagramma

 // Associazione con Utente (1 a 1)
 private Utente utente; // L'utente proprietario della squadra

 // Associazione con Componenti (molti a molti con cardinalità specifiche)
 private List<Pilota> piloti;
 private List<Costruttore> costruttori;
 private List<Leggenda> leggende;

 public Squadra() {
     this.piloti = new ArrayList<>();
     this.costruttori = new ArrayList<>();
     this.leggende = new ArrayList<>();
 }

 public Squadra(String nomeSquadra, float punteggioFantasyConsiglio, int wheelRimanenti, String nomeGiocatore, String descrizione, Utente utente) {
     this.nomeSquadra = nomeSquadra;
     this.punteggioFantasyConsiglio = punteggioFantasyConsiglio;
     this.wheelRimanenti = wheelRimanenti;
     this.nomeGiocatore = nomeGiocatore;
     this.descrizione = descrizione;
     this.utente = utente;
     this.piloti = new ArrayList<>();
     this.costruttori = new ArrayList<>();
     this.leggende = new ArrayList<>();
 }

 public String getNomeSquadra() {
     return nomeSquadra;
 }

 public void setNomeSquadra(String nomeSquadra) {
     this.nomeSquadra = nomeSquadra;
 }

 public float getPunteggioFantasyConsiglio() {
     return punteggioFantasyConsiglio;
 }

 public void setPunteggioFantasyConsiglio(float punteggioFantasyConsiglio) {
     this.punteggioFantasyConsiglio = punteggioFantasyConsiglio;
 }

 public int getWheelRimanenti() {
     return wheelRimanenti;
 }

 public void setWheelRimanenti(int wheelRimanenti) {
     this.wheelRimanenti = wheelRimanenti;
 }

 public String getNomeGiocatore() {
     return nomeGiocatore;
 }

 public void setNomeGiocatore(String nomeGiocatore) {
     this.nomeGiocatore = nomeGiocatore;
 }

 public String getDescrizione() {
     return descrizione;
 }

 public void setDescrizione(String descrizione) {
     this.descrizione = descrizione;
 }

 public Utente getUtente() {
     return utente;
 }

 public void setUtente(Utente utente) {
     this.utente = utente;
 }

 public List<Pilota> getPiloti() {
     return piloti;
 }

 public void setPiloti(List<Pilota> piloti) {
     this.piloti = piloti;
 }

 public List<Costruttore> getCostruttori() {
     return costruttori;
 }

 public void setCostruttori(List<Costruttore> costruttori) {
     this.costruttori = costruttori;
 }

 public List<Leggenda> getLeggende() {
     return leggende;
 }

 public void setLeggende(List<Leggenda> leggende) {
     this.leggende = leggende;
 }

 // Metodi per aggiungere componenti con controllo sulla cardinalità
 public void addPilota(Pilota pilota) {
     if (pilota != null && this.piloti.size() < 2) { // Max 2 piloti
         this.piloti.add(pilota);
     } else if (pilota != null) {
         System.out.println("Non è possibile aggiungere più di 2 piloti alla squadra.");
     }
 }

 public void addCostruttore(Costruttore costruttore) {
     if (costruttore != null && this.costruttori.size() < 1) { // Max 1 costruttore
         this.costruttori.add(costruttore);
     } else if (costruttore != null) {
         System.out.println("Non è possibile aggiungere più di 1 costruttore alla squadra.");
     }
 }

 public void addLeggenda(Leggenda leggenda) {
     if (leggenda != null && this.leggende.size() < 1) { // Max 1 leggenda
         this.leggende.add(leggenda);
     } else if (leggenda != null) {
         System.out.println("Non è possibile aggiungere più di 1 leggenda alla squadra.");
     }
 }

 // Metodi suggeriti dal diagramma, implementazioni base:
 public List<Componente> getComponenti() {
     List<Componente> allComponents = new ArrayList<>();
     allComponents.addAll(piloti);
     allComponents.addAll(costruttori);
     allComponents.addAll(leggende);
     return allComponents;
 }

 public float calcolaPunteggioTotale() {
     // Implementazione complessa: somma i punteggi di tutti i componenti della squadra.
     // Questo richiederà di passare la lista di RisultatoGP.
     return 0.0f; // Placeholder
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
            ", punteggioFantasyConsiglio=" + punteggioFantasyConsiglio +
            ", wheelRimanenti=" + wheelRimanenti +
            ", nomeGiocatore='" + nomeGiocatore + '\'' +
            ", descrizione='" + descrizione + '\'' +
            ", utente=" + (utente != null ? utente.getUsername() : "N/A") +
            ", piloti=" + piloti.size() +
            ", costruttori=" + costruttori.size() +
            ", leggende=" + leggende.size() +
            '}';
 }
}
