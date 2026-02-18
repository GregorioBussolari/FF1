package Servlets;


import Beans.Utente;
import DB.UtenteRepository;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
/*import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;*/
import java.util.Optional;

@ManagedBean // JSF Managed Bean
@RequestScoped // Scope appropriato per un form di login
public class ManagedLogin {
	private String username;
	private String password;
	private UtenteRepository utenteRepository;
	@PostConstruct
	public void init() {
		utenteRepository = new UtenteRepository(2);
	}
	// Getters e Setters
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String doLogin() {
	// 1. Hashing della password fornita dall'utente
	/*String hashedPassword = null;
	try {
		hashedPassword = hashPassword(password);
	} catch (Exception e) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_FATAL, "Errore di sistema",
						"Impossibile processare il login."));
						e.printStackTrace();
				return null;
	}*/
	// 2. Chiamata all'UtenteRepository per validare le credenziali
	Optional<Utente> utenteOptional = utenteRepository.findByUsernameAndPassword(username, password);
	if (utenteOptional.isPresent()) {
	// Login riuscito!
		Utente utenteLoggato = utenteOptional.get();
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Riuscito", "Benvenuto, " +utenteLoggato.getUsername() + "!"));
	// 3. Gestione della sessione utente
	// NON salvare la password hashata qui.
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser",utenteLoggato.getUsername());
	FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUserId", utenteLoggato.getId());

	return "welcome?faces-redirect=true";
	} else {
		// Login fallito
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Fallito", "Username opassword non validi."));
		return null; // Rimani sulla stessa pagina di login
		}
	}
	// Metodo helper per hashare la password.
	// IN PRODUZIONE USA BCrypt, Argon2, scrypt, PBKDF2 - MAI SHA-256 da solo!
	/*private String hashPassword(String password) throws NoSuchAlgorithmException,
	UnsupportedEncodingException {
	MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Sostituisci con un
	algoritmo più forte per prod.
	byte[] hash = digest.digest(password.getBytes("UTF-8"));
	return Base64.getEncoder().encodeToString(hash);
	}*/
	// Metodo per il logout
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "login?faces-redirect=true"; // Reindirizza alla pagina di login dopo il logout
	}
}