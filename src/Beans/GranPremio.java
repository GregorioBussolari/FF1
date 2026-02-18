package Beans;

//GranPremio.java

import java.time.LocalDate;
import java.util.Objects;

public class GranPremio {
 private LocalDate data;
 private String localita;
 private String nome;

 public GranPremio() {
 }

 public GranPremio(LocalDate data, String localita, String nome) {
     this.data = data;
     this.localita = localita;
     this.nome = nome;
 }

 public LocalDate getData() {
     return data;
 }

 public void setData(LocalDate data) {
     this.data = data;
 }

 public String getLocalita() {
     return localita;
 }

 public void setLocalita(String localita) {
     this.localita = localita;
 }

 public String getNome() {
     return nome;
 }

 public void setNome(String nome) {
     this.nome = nome;
 }

 @Override
 public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     GranPremio that = (GranPremio) o;
     return Objects.equals(data, that.data) &&
            Objects.equals(localita, that.localita) &&
            Objects.equals(nome, that.nome);
 }

 @Override
 public int hashCode() {
     return Objects.hash(data, localita, nome);
 }

 @Override
 public String toString() {
     return "GranPremio{" +
            "data=" + data +
            ", localita='" + localita + '\'' +
            ", nome='" + nome + '\'' +
            '}';
 }
}