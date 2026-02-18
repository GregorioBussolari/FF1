package Beans;

//Pilota.java

import java.util.List;
import java.util.Objects;

public class Pilota extends Componente {
 private String costruttore; // Assumo che sia un riferimento al costruttore associato

 public Pilota() {
     super();
 }

 public Pilota(float prezzo, String nomeCompleto, String descrizione, String nazione, String costruttore) {
     super(prezzo, nomeCompleto, descrizione, nazione);
     this.costruttore = costruttore;
 }

 public String getCostruttore() {
     return costruttore;
 }

 public void setCostruttore(String costruttore) {
     this.costruttore = costruttore;
 }

 @Override
 public float calcolaPunteggio(RisultatoGP risultatoGP) {
	 if(risultatoGP.getComponente().equals(this)) {
		 return risultatoGP.getPunteggioOttenuto();
	 }
	 else return 0;
	 
     
 }

 @Override
 public float calcolaPunteggioTot(List<RisultatoGP> listaRisultatiGP) {
     float totalScore = 0;
     for(RisultatoGP rgp : listaRisultatiGP) {
    	 if(rgp.getComponente().equals(this)) {
			 totalScore += rgp.getPunteggioOttenuto();
		 }
     }	     
     return totalScore;
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     if (!super.equals(o)) return false;
     Pilota pilota = (Pilota) o;
     return Objects.equals(costruttore, pilota.costruttore);
 }

 @Override
 public int hashCode() {
     return Objects.hash(super.hashCode(), costruttore);
 }

 @Override
 public String toString() {
     return "Pilota{" +
            "prezzo=" + getPrezzo() +
            ", nomeCompleto='" + getNomeCompleto() + '\'' +
            ", descrizione='" + getDescrizione() + '\'' +
            ", nazione='" + getNazione() + '\'' +
            ", costruttore='" + costruttore + '\'' +
            '}';
 }
}
