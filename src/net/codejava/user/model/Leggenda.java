package net.codejava.user.model;

//Leggenda.java

import java.util.List;
import java.util.Objects;

public class Leggenda extends Componente {
 private String abilitaSpeciale;

 public Leggenda() {
     super();
 }

 public Leggenda(float prezzo, String nomeCompleto, String descrizione, String nazione, String abilitaSpeciale) {
     super(prezzo, nomeCompleto, descrizione, nazione);
     this.abilitaSpeciale = abilitaSpeciale;
 }

 public String getAbilitaSpeciale() {
     return abilitaSpeciale;
 }

 public void setAbilitaSpeciale(String abilitaSpeciale) {
     this.abilitaSpeciale = abilitaSpeciale;
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
     Leggenda leggenda = (Leggenda) o;
     return Objects.equals(abilitaSpeciale, leggenda.abilitaSpeciale);
 }

 @Override
 public int hashCode() {
     return Objects.hash(super.hashCode(), abilitaSpeciale);
 }

 @Override
 public String toString() {
     return "Leggenda{" +
            "prezzo=" + getPrezzo() +
            ", nomeCompleto='" + getNomeCompleto() + '\'' +
            ", descrizione='" + getDescrizione() + '\'' +
            ", nazione='" + getNazione() + '\'' +
            ", abilitaSpeciale='" + abilitaSpeciale + '\'' +
            '}';
 }
}
