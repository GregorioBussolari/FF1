package Beans;

//GestioneLega.java

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GestoreLega extends TipoUtente {
 // Non ha attributi specifici oltre a quelli ereditati da TipoUtente, ma può avere metodi specifici.

 // Associazione con Lega (1 a molti)
 private List<Lega> legheGestite;

 public GestoreLega() {
     super();
     this.setTipoUtente("GestoreLega");
     this.legheGestite = new ArrayList<>();
 }

 public GestoreLega(LocalDateTime timeStampUpdate, Utente utente) {
     super("GestoreLega", timeStampUpdate, utente);
     this.legheGestite = new ArrayList<>();
 }

 public List<Lega> getLegheGestite() {
     return legheGestite;
 }

 public void setLegheGestite(List<Lega> legheGestite) {
     this.legheGestite = legheGestite;
 }

 public void aggiungiLegaGestita(Lega lega) {
     if (lega != null && !this.legheGestite.contains(lega)) {
         this.legheGestite.add(lega);
         // Potresti voler impostare anche il riferimento inverso, se Lega ha un riferimento a GestioneLega
         // lega.setGestore(this);
     }
 }

 // Metodo suggerito dal diagramma:
 public List<Lega> getLeghe() {
     // Implementazione dipenderà dalla logica di business.
     // In questo caso, restituisce le leghe gestite da questo GestioneLega.
     return new ArrayList<>(this.legheGestite);
 }
}