package net.codejava.user.model;

//UtenteStandard.java
import java.time.LocalDateTime;

public class UtenteStandard extends TipoUtente {

 public UtenteStandard() {
     super();
     this.setTipoUtente("Standard");
 }

 public UtenteStandard(LocalDateTime timeStampUpdate, Utente utente) {
     super("Standard", timeStampUpdate, utente);
 }

 // Metodi aggiuntivi se necessari per UtenteStandard
}
