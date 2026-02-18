package Beans;

//ClassificaGlobale.java

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassificaGlobale {
 private String classificaId;
 private String nome;

 // Associazione con Squadra (molti a molti)
 private List<Squadra> squadreClassificate;

 public ClassificaGlobale() {
     this.squadreClassificate = new ArrayList<>();
 }

 public ClassificaGlobale(String classificaId, String nome) {
     this.classificaId = classificaId;
     this.nome = nome;
     this.squadreClassificate = new ArrayList<>();
 }

 public String getClassificaId() {
     return classificaId;
 }

 public void setClassificaId(String classificaId) {
     this.classificaId = classificaId;
 }

 public String getNome() {
     return nome;
 }

 public void setNome(String nome) {
     this.nome = nome;
 }

 public List<Squadra> getSquadreClassificate() {
     return squadreClassificate;
 }

 public void setSquadreClassificate(List<Squadra> squadreClassificate) {
     this.squadreClassificate = squadreClassificate;
 }

 public void addSquadraClassificata(Squadra squadra) {
     if (squadra != null && !this.squadreClassificate.contains(squadra)) {
         this.squadreClassificate.add(squadra);
     }
 }

 // Metodi suggeriti dal diagramma:
 public List<Squadra> getSquadre() {
     return new ArrayList<>(this.squadreClassificate);
 }

 // Metodo per ottenere la classifica (potrebbe richiedere più parametri o logica)
 public List<Squadra> getClassifica() {
     // Implementazione complessa: ordinare le squadre in base ai loro punteggi globali.
     // Questo metodo dovrebbe calcolare i punteggi totali delle squadre e ordinarle.
     return new ArrayList<>(this.squadreClassificate); // Placeholder
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     ClassificaGlobale that = (ClassificaGlobale) o;
     return Objects.equals(classificaId, that.classificaId);
 }

 @Override
 public int hashCode() {
     return Objects.hash(classificaId);
 }

 @Override
 public String toString() {
     return "ClassificaGlobale{" +
            "classificaId='" + classificaId + '\'' +
            ", nome='" + nome + '\'' +
            ", squadreClassificate=" + squadreClassificate.size() +
            '}';
 }
}
