<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.codejava.user.model.*"%>
<%@ page import="net.codejava.user.dao.*"%>
<%@ page import="net.codejava.user.controller.*"%>
<%@ page import="java.util.List"%>
<%@ page import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<% //CONNESSIONE DB

	final DriverManagerDataSource dataSource;

	
	
	
  SquadraDAO daoSquadra = new SquadraDAOImpl();
  String username = (String) session.getAttribute("utente");


  List<Squadra> squadre = daoSquadra.list();
  String resultMsg = null;
  String errorMsg=null;
  String successMsg=null;

  if("POST".equalsIgnoreCase(request.getMethod())){

    String codiceLega = request.getParameter("leagueCode");
    String nomeSquadra = request.getParameter("teamSelect");
    Controller controller;
    Squadra squadraScelta = null;
    boolean ok = false;
    
    

	    //Ottengo squadra
	    for (Squadra s : squadre) {
		    if (s.getNomeSquadra().equals(nomeSquadra) && s.getUtente().getUsername().equals(username)) {
		    	squadraScelta = s;
		      	break;
		    }
	    }
	
	    //Aggiungo a DB
	    controller = new Controller();
	    ok = controller.iscriviALega(codiceLega, squadraScelta);
    
	
    //Risultato
    if(!ok) {
		  errorMsg= "C'è stato un errore";
	  }
	  else{
		  successMsg= "Iscrizione effettuata con successo";
	  }

  }
%>
<!DOCTYPE html>
<html lang="it">

<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Iscriviti ad una Lega</title>
    <style>
        html,
        body {
            margin: 0;
            padding: 0;
            height: 100%;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(to bottom, #1a1a1a, #2c2c2c);
            color: #fff;
        }

        .wrapper {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            text-align: center;
        }

        header {
            background-color: #e10600;
            padding: 20px;
            text-align: center;
            font-size: 2em;
            font-weight: bold;
        }

        .main {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 30px;
        }

        .container {
            width: 500px;
            padding: 30px;
            background-color: #111;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(255, 0, 0, 0.3);
        }

        label {
            display: block;
            margin-top: 20px;
            font-weight: bold;
            color: #ccc;
        }

        input[type="text"],
        select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            border: none;
            border-radius: 5px;
            background-color: #333;
            color: #fff;
            font-size: 1em;
        }

        button {
            margin-top: 30px;
            width: 100%;
            padding: 12px;
            background-color: #e10600;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 1.1em;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #b00000;
        }

        footer {
            text-align: center;
            padding: 20px;
            color: #aaa;
            font-size: 0.9em;
        }
        
        .error-message {
            color: #ff4c4c;
            margin-bottom: 15px;
            font-weight: 700;
            text-align: center;
        }
  .success-message {
            color: #00cc44;
            margin-bottom: 15px;
            font-weight: 700;
            text-align: center;
        }
    </style>
</head>

<body>
<div style="
    display: flex; 
    justify-content: space-between; 
    align-items: center; 
    margin: 20px auto; 
    padding: 0 40px;
    max-width: 1000px;
    font-family: system-ui, sans-serif; 
    font-size: 16px; 
    color: white;
">

    <!-- SINISTRA: link -->
    
    <div>
        <a href="leTueLeghe.jsp" style="color: #e10600; text-decoration: none; font-weight: bold;">Le Tue Leghe</a>
    </div>
    
	<div>
        <a href="menu.html" title="Torna al menu">
            <img src="images/logoFF1_small.png" alt="Indietro" style="height: 32px; cursor: pointer;">
        </a>
    </div>
	
    <!-- DESTRA: username -->
    <div style="font-weight: bold;">
        <%= username %>
    </div>
    
    
</div>
    <div class="wrapper">
		<% if (errorMsg != null) { %>
            <div class="error-message"><%= errorMsg %></div>
        <% } else if (successMsg != null) { %>
            <div class="success-message"><%= successMsg %></div>
        <% } %>
        <div class="main">
        
         
            <div class="container">
                <h2>Inserisci il codice della Lega e scegli la Squadra con cui vuoi competere</h2>

                <form action="iscrizioneLega.jsp" method="post">
                    <label for="leagueCode">Codice alfanumerico</label>
                    <input type="text" id="leagueCode" name="leagueCode" placeholder="Codice Lega" required />

                    <label for="teamSelect">Seleziona una tua squadra</label>
      		<select id="teamSelect" name="teamSelect" required>
    		<option value="">-- Seleziona squadra --</option>
    		<%
        		if (squadre != null && username != null) {
            		for (Squadra s : squadre) {
                		if (s.getUtente() != null && s.getUtente().getUsername().equals(username)) {
    		%>
    		<option value="<%= s.getNomeSquadra() %>"><%= s.getNomeSquadra() %></option>
    		<%
                		}
            		}
        		}
    		%>
	  </select>

                    <button type="submit">Conferma</button>
                </form>
            </div>
        </div>

        <footer>
            <p>&copy; 2025 Fantasy F1 - Powered by LMosca, BGregge, LZana</p>
        </footer>
    </div>
</body>

</html>
