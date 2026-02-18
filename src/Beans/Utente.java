package Beans;


import java.util.HashSet;
import java.util.Set;


public class Utente {

    private Long id;
    private String username;
    private String password; // Questa verrà memorizzata come hash
    private String nome;
    private String cognome;
    private String nazionalita;

    private Set<Squadra> squadre = new HashSet<>();


    // --- Costruttori ---
    public Utente() {
    }

    public Utente(String username, String password, String nome, String cognome, String nazionalita) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.nazionalita = nazionalita;
    }


    // --- Getters e Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }
    

    

   public Set<Squadra> getSquadre() {
        return squadre;
    }

    public void setSquadre(Set<Squadra> squadre) {
        this.squadre = squadre;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return true; // L'account è sempre abilitato
    }
}

