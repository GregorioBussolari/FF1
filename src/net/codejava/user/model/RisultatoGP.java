package net.codejava.user.model;

//RisultatoGP.java

import java.util.Objects;

public class RisultatoGP {
 private float punteggioOttenuto;
 private String granPremio;
 private Componente componente; 



 public RisultatoGP(float punteggioOttenuto, String granPremio, Componente componente) {
     this.punteggioOttenuto = punteggioOttenuto;
     this.granPremio = granPremio;
     this.componente = componente;
 }

 public float getPunteggioOttenuto() {
     return punteggioOttenuto;
 }

 public void setPunteggioOttenuto(float punteggioOttenuto) {
     this.punteggioOttenuto = punteggioOttenuto;
 }

 public String getGranPremio() {
     return granPremio;
 }

 public void setGranPremio(String granPremio) {
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
            Objects.equals(granPremio, that.granPremio);
 }

 @Override
 public int hashCode() {
     return Objects.hash(punteggioOttenuto, granPremio);
 }

 @Override
 public String toString() {
     return "RisultatoGP{" +
            "punteggioOttenuto=" + punteggioOttenuto +
            ", granPremio=" + granPremio +
            ", componente=" + componente +
            '}';
 }
}
