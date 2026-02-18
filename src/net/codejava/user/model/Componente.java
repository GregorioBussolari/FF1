package net.codejava.user.model;

//Componente.java

import java.util.Objects;
import java.util.List; // Per List<RisultatoGP> nell'ipotesi che 'RisultatoGP' mantenga un riferimento a 'Componente'

public abstract class Componente { // Abstract perché ha sottoclassi concrete
 private float prezzo;
 private String nomeCompleto;
 private String descrizione;
 private String nazione;

 public Componente() {
 }

 public Componente(float prezzo, String nomeCompleto, String descrizione, String nazione) {
     this.prezzo = prezzo;
     this.nomeCompleto = nomeCompleto;
     this.descrizione = descrizione;
     this.nazione = nazione;
 }

 public float getPrezzo() {
     return prezzo;
 }

 public void setPrezzo(float prezzo) {
     this.prezzo = prezzo;
 }

 public String getNomeCompleto() {
     return nomeCompleto;
 }

 public void setNomeCompleto(String nomeCompleto) {
     this.nomeCompleto = nomeCompleto;
 }

 public String getDescrizione() {
     return descrizione;
 }

 public void setDescrizione(String descrizione) {
     this.descrizione = descrizione;
 }

 public String getNazione() {
     return nazione;
 }

 public void setNazione(String nazione) {
     this.nazione = nazione;
 }

 // Metodi astratti da implementare nelle sottoclassi
 public abstract float calcolaPunteggio(RisultatoGP risultatoGP);
 public abstract float calcolaPunteggioTot(List<RisultatoGP> listaRisultatiGP);

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     Componente that = (Componente) o;
     return Float.compare(that.prezzo, prezzo) == 0 &&
            Objects.equals(nomeCompleto, that.nomeCompleto) &&
            Objects.equals(descrizione, that.descrizione) &&
            Objects.equals(nazione, that.nazione);
 }

 @Override
 public int hashCode() {
     return Objects.hash(prezzo, nomeCompleto, descrizione, nazione);
 }

 @Override
 public String toString() {
     return "Componente{" +
            "prezzo=" + prezzo +
            ", nomeCompleto='" + nomeCompleto + '\'' +
            ", descrizione='" + descrizione + '\'' +
            ", nazione='" + nazione + '\'' +
            '}';
 }
}
