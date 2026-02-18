package Beans;

//Lega.java

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lega {
 private String codiceLega;
 private String nomeLega;
 private int numeroMaxPartecipanti;

 // Associazione con Utente (molti a 1) - il creatore della lega
 private Utente creatore;

 // Associazione con Squadra (1 a molti)
 private List<Squadra> squadrePartecipanti;

 // Associazione con IscrizioneLega (1 a molti)
 private List<IscrizioneLega> iscrizioni;


 public Lega() {
     this.squadrePartecipanti = new ArrayList<>();
     this.iscrizioni = new ArrayList<>();
 }

 public Lega(String codiceLega, String nomeLega, int numeroMaxPartecipanti, Utente creatore) {
     this.codiceLega = codiceLega;
     this.nomeLega = nomeLega;
     this.numeroMaxPartecipanti = numeroMaxPartecipanti;
     this.creatore = creatore;
     this.squadrePartecipanti = new ArrayList<>();
     this.iscrizioni = new ArrayList<>();
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

 public Utente getCreatore() {
     return creatore;
 }

 public void setCreatore(Utente creatore) {
     this.creatore = creatore;
 }

 public List<Squadra> getSquadrePartecipanti() {
     return squadrePartecipanti;
 }

 public void setSquadrePartecipanti(List<Squadra> squadrePartecipanti) {
     this.squadrePartecipanti = squadrePartecipanti;
 }

 public List<IscrizioneLega> getIscrizioni() {
     return iscrizioni;
 }

 public void setIscrizioni(List<IscrizioneLega> iscrizioni) {
     this.iscrizioni = iscrizioni;
 }

 public void aggiungiSquadraPartecipante(Squadra squadra) {
     if (squadra != null && !this.squadrePartecipanti.contains(squadra)) {
         this.squadrePartecipanti.add(squadra);
     }
 }

 public void aggiungiIscrizione(IscrizioneLega iscrizione) {
     if (iscrizione != null && !this.iscrizioni.contains(iscrizione)) {
         this.iscrizioni.add(iscrizione);
         // Potresti voler impostare anche il riferimento inverso, se IscrizioneLega ha un riferimento a Lega
         // iscrizione.setLega(this);
     }
 }

 // Metodi suggeriti dal diagramma, implementazioni base:
 public List<Utente> getPartecipanti() {
     List<Utente> partecipanti = new ArrayList<>();
     for (IscrizioneLega iscrizione : iscrizioni) {
         if (iscrizione.getSquadra() != null && iscrizione.getSquadra().getUtente() != null) {
             partecipanti.add(iscrizione.getSquadra().getUtente());
         }
     }
     return partecipanti;
 }

 public List<Squadra> getSquadre() {
     return new ArrayList<>(this.squadrePartecipanti);
 }

 // Assumendo che `GranPremio` esista e sia rilevante per la classifica
 // Questo metodo dovrebbe implementare la logica per calcolare la classifica della lega in un certo GP.
 public List<Squadra> getClassificaGranPremio(GranPremio granPremio) {
     // Implementazione complessa: calcola i punteggi delle squadre per il dato GranPremio e ordina.
     // Richiede una logica di business più approfondita e l'accesso ai RisultatoGP.
     return new ArrayList<>(); // Placeholder
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
            ", creatore=" + (creatore != null ? creatore.getUsername() : "N/A") +
            ", squadrePartecipanti=" + squadrePartecipanti.size() +
            ", iscrizioni=" + iscrizioni.size() +
            '}';
 }
}