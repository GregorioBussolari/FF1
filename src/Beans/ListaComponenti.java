package Beans;

//ListaComponenti.java

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//Questa classe può rappresentare una collezione o un servizio per gestire i componenti
public class ListaComponenti {
 private List<Componente> componenti;

 public ListaComponenti() {
     this.componenti = new ArrayList<>();
 }

 public List<Componente> getComponenti() {
     return componenti;
 }

 public void setComponenti(List<Componente> componenti) {
     this.componenti = componenti;
 }

 public void addComponente(Componente componente) {
     if (componente != null) {
         this.componenti.add(componente);
     }
 }

 public void removeComponente(Componente componente) {
     if (componente != null) {
         this.componenti.remove(componente);
     }
 }

 public Componente getComponenteByNome(String nomeCompleto) {
     return componenti.stream()
             .filter(c -> c.getNomeCompleto().equals(nomeCompleto))
             .findFirst()
             .orElse(null);
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     ListaComponenti that = (ListaComponenti) o;
     return Objects.equals(componenti, that.componenti);
 }

 @Override
 public int hashCode() {
     return Objects.hash(componenti);
 }

 @Override
 public String toString() {
     return "ListaComponenti{" +
            "componenti=" + componenti +
            '}';
 }
}