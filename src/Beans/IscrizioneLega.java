package Beans;

//IscrizioneLega.java

import java.util.Objects;

//Questa classe modella l'associazione ternaria tra Lega, Squadra e la posizione in classifica
public class IscrizioneLega {
 // Attributo dell'associazione
 private String posizioneClassifica; // Potrebbe essere int, a seconda della logica

 // Riferimenti alle classi coinvolte nell'associazione
 private Lega lega;
 private Squadra squadra;

 public IscrizioneLega() {
 }

 public IscrizioneLega(String posizioneClassifica, Lega lega, Squadra squadra) {
     this.posizioneClassifica = posizioneClassifica;
     this.lega = lega;
     this.squadra = squadra;
 }

 public String getPosizioneClassifica() {
     return posizioneClassifica;
 }

 public void setPosizioneClassifica(String posizioneClassifica) {
     this.posizioneClassifica = posizioneClassifica;
 }

 public Lega getLega() {
     return lega;
 }

 public void setLega(Lega lega) {
     this.lega = lega;
 }

 public Squadra getSquadra() {
     return squadra;
 }

 public void setSquadra(Squadra squadra) {
     this.squadra = squadra;
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     IscrizioneLega that = (IscrizioneLega) o;
     return Objects.equals(lega, that.lega) &&
            Objects.equals(squadra, that.squadra); // Un'iscrizione è unica per lega e squadra
 }

 @Override
 public int hashCode() {
     return Objects.hash(lega, squadra);
 }

 @Override
 public String toString() {
     return "IscrizioneLega{" +
            "posizioneClassifica='" + posizioneClassifica + '\'' +
            ", lega=" + (lega != null ? lega.getNomeLega() : "N/A") +
            ", squadra=" + (squadra != null ? squadra.getNomeSquadra() : "N/A") +
            '}';
 }
}
