package Beans;

//TipoUtente.java

import java.time.LocalDateTime;
import java.util.Objects;

//Classe astratta come da diagramma
public abstract class TipoUtente {
 protected String tipoUtente; // Attributo esplicito per il tipo
 protected LocalDateTime timeStampUpdate; // Esempio di attributo comune

 // Associazione con Utente
 protected Utente utente;

 public TipoUtente() {
 }

 public TipoUtente(String tipoUtente, LocalDateTime timeStampUpdate, Utente utente) {
     this.tipoUtente = tipoUtente;
     this.timeStampUpdate = timeStampUpdate;
     this.utente = utente;
 }

 public String getTipoUtente() {
     return tipoUtente;
 }

 public void setTipoUtente(String tipoUtente) {
     this.tipoUtente = tipoUtente;
 }

 public LocalDateTime getTimeStampUpdate() {
     return timeStampUpdate;
 }

 public void setTimeStampUpdate(LocalDateTime timeStampUpdate) {
     this.timeStampUpdate = timeStampUpdate;
 }

 public Utente getUtente() {
     return utente;
 }

 public void setUtente(Utente utente) {
     this.utente = utente;
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     TipoUtente that = (TipoUtente) o;
     return Objects.equals(utente, that.utente) && Objects.equals(tipoUtente, that.tipoUtente);
 }

 @Override
 public int hashCode() {
     return Objects.hash(utente, tipoUtente);
 }

 @Override
 public String toString() {
     return "TipoUtente{" +
            "tipoUtente='" + tipoUtente + '\'' +
            ", timeStampUpdate=" + timeStampUpdate +
            ", utente=" + (utente != null ? utente.getUsername() : "N/A") +
            '}';
 }
}
