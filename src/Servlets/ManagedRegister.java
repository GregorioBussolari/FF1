package Servlets;

import Beans.Utente;
import DB.PersistenceException;
import DB.UtenteRepository;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
/*import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
*/
@ManagedBean
@RequestScoped
public class ManagedRegister {

 private String username;
 private String password;
 private String confirmPassword; // Per la conferma della password
 private String nazionalita;
 private String nome;
 private String cognome;

 private UtenteRepository utenteRepository;

 @PostConstruct
 public void init() {
     utenteRepository = new UtenteRepository(2);
 }

 // Getters e Setters per tutte le proprietà
 public String getUsername() { return username; }
 public void setUsername(String username) { this.username = username; }
 public String getPassword() { return password; }
 public void setPassword(String password) { this.password = password; }
 public String getConfirmPassword() { return confirmPassword; }
 public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
 public String getNome() { return nome; }
 public void setNome(String nome) { this.nome = nome; }
 public String getCognome() { return cognome; }
 public void setCognome(String cognome) { this.username = cognome; }
 public String getNazionalita() { return nazionalita; }
 public void setNazionalita(String nazionalita) { this.nazionalita = nazionalita; }


 public String doRegister() {
     FacesContext context = FacesContext.getCurrentInstance();

     // 1. Validazione base lato server
     if (!password.equals(confirmPassword)) {
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore", "Le password non corrispondono."));
         return null; // Rimani sulla stessa pagina
     }
     // Puoi aggiungere altre validazioni (es. formato email, lunghezza password, username già esistente)

     // 2. Hash della password
     /*String hashedPassword = null;
     try {
         // IN PRODUZIONE USA BCrypt, Argon2, scrypt, PBKDF2 - MAI SHA-256 da solo per le password!
         hashedPassword = hashPassword(password);
     } catch (Exception e) {
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore di sistema", "Impossibile processare la registrazione."));
         e.printStackTrace();
         return null;
     }*/

     // 3. Crea l'oggetto Utente
     Utente nuovoUtente = new Utente();
     nuovoUtente.setUsername(username);
     nuovoUtente.setPassword(password);
     nuovoUtente.setCognome(cognome);
     nuovoUtente.setNazionalita(nazionalita);
     nuovoUtente.setNome(nome);

     try {
         // 4. Salva l'utente nel database tramite il repository
         utenteRepository.persist(nuovoUtente);
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrazione Riuscita", "Utente '" + username + "' registrato con successo. Ora puoi accedere."));
         // Potresti reindirizzare l'utente alla pagina di login
         return "login?faces-redirect=true";
     } catch (RuntimeException e) {
         // Cattura l'eccezione lanciata dal repository in caso di errore DB
         context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errore di Registrazione", e.getMessage()));
         e.printStackTrace();
         return null; // Rimani sulla stessa pagina di registrazione
     } catch (PersistenceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
 }

 // Metodo helper per hashare la password (uguale a quello nel LoginBean)
 /*private String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
     // Ripeto: PER PRODUZIONE USA BCrypt, Argon2, scrypt, PBKDF2
     MessageDigest digest = MessageDigest.getInstance("SHA-256");
     byte[] hash = digest.digest(password.getBytes("UTF-8"));
     return Base64.getEncoder().encodeToString(hash);
 }*/
}
