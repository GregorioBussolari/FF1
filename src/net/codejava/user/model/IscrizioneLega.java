package net.codejava.user.model;


import java.util.Objects;

public class IscrizioneLega {
 

 private String codiceLega;
 private Squadra squadra;

 public IscrizioneLega() {
 }

 public IscrizioneLega(String codiceLega, Squadra squadra) {
     this.codiceLega = codiceLega;
     this.squadra = squadra;
 }

 public String getCodiceLega() {
     return codiceLega;
 }

 public void setCodiceLega(String lega) {
     this.codiceLega = lega;
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
     return Objects.equals(codiceLega, that.codiceLega) &&
            Objects.equals(squadra, that.squadra); // Un'iscrizione è unica per lega e squadra
 }

 @Override
 public int hashCode() {
     return Objects.hash(codiceLega, squadra);
 }

 @Override
 public String toString() {
     return "IscrizioneLega{" +
            ", lega=" + (codiceLega != null ? codiceLega : "N/A") +
            ", squadra=" + (squadra != null ? squadra : "N/A") +
            '}';
 }
}
