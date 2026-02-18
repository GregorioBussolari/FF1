package Beans;

//RisultatoGP.java

import java.util.Objects;

public class RisultatoGP {
 private float punteggioOttenuto;
 private String descrizione;
 private GranPremio granPremio; // Associazione 1 a 1 con GranPremio
 private Componente componente; 



 public RisultatoGP(float punteggioOttenuto, String descrizione, GranPremio granPremio, Componente componente) {
     this.punteggioOttenuto = punteggioOttenuto;
     this.descrizione = descrizione;
     this.granPremio = granPremio;
     this.componente = componente;
 }

 public float getPunteggioOttenuto() {
     return punteggioOttenuto;
 }

 public void setPunteggioOttenuto(float punteggioOttenuto) {
     this.punteggioOttenuto = punteggioOttenuto;
 }

 public String getDescrizione() {
     return descrizione;
 }

 public void setDescrizione(String descrizione) {
     this.descrizione = descrizione;
 }

 public GranPremio getGranPremio() {
     return granPremio;
 }

 public void setGranPremio(GranPremio granPremio) {
     this.granPremio = granPremio;
 }

 public Componente getComponente() {
     return componente;
 }

 public void setComponente(Componente componente) {
     this.componente = componente;
 }



 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     RisultatoGP that = (RisultatoGP) o;
     return Float.compare(that.punteggioOttenuto, punteggioOttenuto) == 0 &&
            Objects.equals(descrizione, that.descrizione) &&
            Objects.equals(granPremio, that.granPremio);
 }

 @Override
 public int hashCode() {
     return Objects.hash(punteggioOttenuto, descrizione, granPremio);
 }

 @Override
 public String toString() {
     return "RisultatoGP{" +
            "punteggioOttenuto=" + punteggioOttenuto +
            ", descrizione='" + descrizione + '\'' +
            ", granPremio=" + granPremio +
            ", componente=" + componente +
            '}';
 }
}
