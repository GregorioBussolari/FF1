package net.codejava.user.model;

//ComponenteFactory.java

import java.util.HashMap;
import java.util.Map;

public class ComponenteFactory {

	private static final Map<String, Componente> componentiInstanziati = new HashMap<>();

 private ComponenteFactory() {
 }

 // Metodo statico per ottenere le istanze dei componenti
 public static Componente getComponente(String tipoComponente, float prezzo, String nomeCompleto, String descrizione, String nazione, String attributoSpecifico) {
    
     String cacheKey = tipoComponente + "_" + nomeCompleto;

     if (componentiInstanziati.containsKey(cacheKey)) {
         return componentiInstanziati.get(cacheKey);
     }

     Componente componente;
     switch (tipoComponente.toLowerCase()) {
         case "leggenda":
             componente = new Leggenda(prezzo, nomeCompleto, descrizione, nazione, attributoSpecifico);
             break;
         case "pilota":
             componente = new Pilota(prezzo, nomeCompleto, descrizione, nazione, attributoSpecifico);
             break;
         case "costruttore":
             componente = new Costruttore(prezzo, nomeCompleto, descrizione, nazione);
             break;
         default:
             throw new IllegalArgumentException("Tipo di componente sconosciuto: " + tipoComponente);
     }
     componentiInstanziati.put(cacheKey, componente);
     return componente;
 }

 // Metodo per pulire la cache (utile per test o gestione risorse)
 public static void clearCache() {
     componentiInstanziati.clear();
 }
}
