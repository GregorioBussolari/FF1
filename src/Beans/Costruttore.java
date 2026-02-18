package Beans;

//Costruttore.java

import java.util.List;

public class Costruttore extends Componente {
 // Non ha attributi specifici oltre a quelli ereditati da Componente

 public Costruttore() {
     super();
 }

 public Costruttore(float prezzo, String nomeCompleto, String descrizione, String nazione) {
     super(prezzo, nomeCompleto, descrizione, nazione);
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
     return super.equals(o); // Usa l'equals della superclasse
 }

 @Override
 public int hashCode() {
     return super.hashCode(); // Usa l'hashCode della superclasse
 }

 @Override
 public String toString() {
     return "Costruttore{" +
            "prezzo=" + getPrezzo() +
            ", nomeCompleto='" + getNomeCompleto() + '\'' +
            ", descrizione='" + getDescrizione() + '\'' +
            ", nazione='" + getNazione() + '\'' +
            '}';
 }
}
