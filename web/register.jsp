<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.codejava.user.dao.UtenteDAOImpl"%>
<%@ page import="net.codejava.user.model.Utente"%>
<%@ page import="net.codejava.user.dao.UtenteDAO"%>
<%@ page import="java.util.List"%>
<%@ page import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%
	final DriverManagerDataSource dataSource;
	
	final UtenteDAO dao;
	
	
	dao = new UtenteDAOImpl();

    String errorMsg = null;
    String successMsg = null;

    if ("POST".equalsIgnoreCase(request.getMethod())) {
        String username = request.getParameter("username");
        String nome = request.getParameter("nome");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String cognome = request.getParameter("cognome");
        String nazionalita = request.getParameter("nazionalita");

        if (username == null || username.trim().isEmpty() ||
            nome == null || nome.trim().isEmpty() ||
            password == null || password.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty() ||
            cognome == null || cognome.isEmpty() ||
            nazionalita == null || nazionalita.trim().isEmpty() 
            ) {
            errorMsg = "Per favore, compila tutti i campi.";
        } else if (!password.equals(confirmPassword)) {
            errorMsg = "Le password non corrispondono.";
        } else {
            // Provo a registrare l'utente con il bean
            Utente u = new Utente(username, password, nome, cognome, nazionalita);
            List<Utente> lista = dao.list();
            boolean trovato=false;
            for(Utente ut : lista){
            	if(u.getUsername().equals(ut.getUsername())){
            		trovato = true;
            	}
            }
            if(!trovato){
            int registered = dao.save(u);
            if (registered>0) {
                successMsg = "Registrazione avvenuta con successo! Puoi ora effettuare il login.";
            } else {
                errorMsg = "Errore nella registrazione.";
            }
            }
            else{
            	errorMsg = "Errore nella registrazione.";
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Registrazione - FF1</title>
    <style>
        /* Stessi stili CSS usati in login.jsp */
        body {
            margin: 0;
            padding: 0;
            background-image: url("images/neoncar.jpeg");
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
            font-family: 'Orbitron', sans-serif;
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        @keyframes fadeSlideIn {
            0% {
                opacity: 0;
                transform: translateY(-40px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }
        .login-container {
            background: #121212;
            border: 3px solid #e10600;
            border-radius: 12px;
            padding: 40px 50px;
            width: 320px;
            box-shadow: 0 0 20px #e10600cc;
            text-align: center;
            animation: fadeSlideIn 0.7s ease forwards;
        }
        .logo {
            width: 150px;
            height: 150px;
            background-image: url("images/logoFF1_small.png");
            background-size: contain;
            background-repeat: no-repeat;
            background-position: center;
            margin: 0 auto 20px auto;
        }
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .logo svg {
            width: 150px;
            height: auto;
            fill: #e10600;
            filter: drop-shadow(0 0 5px #e10600aa);
        }
        .login-container h1 {
            margin-bottom: 30px;
            font-size: 2.2rem;
            letter-spacing: 2px;
        }
        label {
            display: block;
            text-align: left;
            margin-bottom: 6px;
            font-weight: 600;
            font-size: 0.9rem;
        }
        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 12px 15px;
            margin-bottom: 20px;
            border: 2px solid #444;
            border-radius: 6px;
            background: #222;
            color: #eee;
            font-size: 1rem;
            transition: border-color 0.3s ease;
        }
        input[type="text"]:focus,
        input[type="email"]:focus,
        input[type="password"]:focus {
            border-color: #e10600;
            outline: none;
            background: #1a1a1a;
        }
        button {
            width: 100%;
            padding: 14px 0;
            background-color: #e10600;
            border: none;
            border-radius: 8px;
            color: white;
            font-size: 1.1rem;
            font-weight: 700;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #b00400;
        }
        .footer-text {
            margin-top: 15px;
            font-size: 0.9rem;
            color: #888;
        }
        .footer-text a {
            color: #e10600;
            text-decoration: none;
            font-weight: 600;
        }
        .footer-text a:hover {
            text-decoration: underline;
        }
        .error-message {
            color: #ff4c4c;
            margin-bottom: 15px;
            font-weight: 700;
        }
        .success-message {
            color: #00cc44;
            margin-bottom: 15px;
            font-weight: 700;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="container">
            <div class="logo" aria-label="Logo F1 Racing"></div>
        </div>
        <h1>Registrati a Fanta Formula 1</h1>

        <% if (errorMsg != null) { %>
            <div class="error-message"><%= errorMsg %></div>
        <% } else if (successMsg != null) { %>
            <div class="success-message"><%= successMsg %></div>
        <% } %>

        <form action="register.jsp" method="POST">
            <label for="username">Nome utente</label>
            <input type="text" id="username" name="username" placeholder="Inserisci nome utente" required />

            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Inserisci password" required />

            <label for="confirmPassword">Conferma Password</label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Conferma password" required />
            
            <label for="nome">Nome</label>
            <input type="text" id="nome" name="nome" placeholder="Inserisci nome" required />
            
            <label for="cognome">Cognome</label>
            <input type="text" id="cognome" name="cognome" placeholder="Inserisci cognome" required />
            
            <label for="nazionalita">Nazionalita</label>
            <input type="text" id="nazionalita" name="nazionalita" placeholder="Inserisci nazionalità" required />

            <button type="submit">Registrati</button>
        </form>

        <div class="footer-text" onclick="window.location.href='login.jsp'">
            Hai già un account? <a>Login</a>
        </div>
    </div>
</body>
</html>
