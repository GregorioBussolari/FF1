<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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

  if("POST".equalsIgnoreCase(request.getMethod())){

    String nomeLega = request.getParameter("leagueName");
    String numPartecipanti = request.getParameter("maxParticipants");
    int numMaxPartecipanti = Integer.parseInt(numPartecipanti);
    String nomeSquadra = request.getParameter("team");
    Lega legaCreata = null;
    Controller controller;
    Squadra squadraScelta = null;
    
	//Controllo input	
	if(Integer.parseInt(numPartecipanti)>0 && nomeLega != null && nomeSquadra != null){	

	    //Ottengo squadra
	    for (Squadra s : squadre) {
		    if (s.getNomeSquadra().equals(nomeSquadra) && s.getUtente().getUsername().equals(username)) {
		    	squadraScelta = s;
		      	break;
		    }
	    }
	
	    //Aggiungo a DB
	    controller = new Controller();
	    legaCreata = controller.creaLega(nomeLega, numMaxPartecipanti, username, squadraScelta);
    
	}
    //Risultato
    if(legaCreata == null){
      resultMsg = "C'è stato un errore";
    } else resultMsg = "Lega creata con successo";

  }
%>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Crea la tua Lega - Fantasy F1</title>
  <style>
    body {
      margin: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(to bottom, #1a1a1a, #2c2c2c);
      color: #fff;
      position: relative;
      z-index: 1;
    }

    header {
      background-color: #e10600; /* Rosso Ferrari/F1 */
      padding: 20px;
      text-align: center;
      font-size: 2em;
      font-weight: bold;
    }

    .container {
      position: relative;
      width: 800px;
      height: 600px;
      margin: 40px auto;
      background-color: #111;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 0 20px rgba(255, 0, 0, 0.3);
    }
    #creazioneLega {
      background-image: url('images/neoncar.jpeg');
    }

    h1, h3, h4 {
      text-align: center;
      margin-bottom: 20px;
      color: #ddd;
    }

    label {
      display: block;
      margin-top: 15px;
      font-weight: bold;
      color: #ddd;
    }
    

    input[type="text"],
    input[type="number"],
    select {
      width: 100%;
      padding: 10px;
      margin-top: 5px;
      border: none;
      border-radius: 5px;
      font-size: 1em;
      background-color: #333;
      color: #fff;
    }
    select option:hover {
    background-color: #e10600;
    color: white;
    }

    button {
      margin-top: 25px;
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
    button {
      position: relative;
      padding: 10px 20px;
      border-radius: 7px;
      border: 1px solid rgb(238, 65, 7);
      font-size: 14px;
      text-transform: uppercase;
      font-weight: 600;
      letter-spacing: 2px;
      background: transparent;
      color: #fff;
      overflow: hidden;
      box-shadow: 0 0 0 0 transparent;
      -webkit-transition: all 0.2s ease-in;
      -moz-transition: all 0.2s ease-in;
      transition: all 0.2s ease-in;
    }

    button:hover {
      background: rgb(255, 61, 61);
      box-shadow: 0 0 30px 5px rgba(236, 0, 0, 0.815);
      -webkit-transition: all 0.2s ease-out;
      -moz-transition: all 0.2s ease-out;
      transition: all 0.2s ease-out;
    }

    button:hover::before {
      -webkit-animation: sh02 0.5s 0s linear;
      -moz-animation: sh02 0.5s 0s linear;
      animation: sh02 0.5s 0s linear;
    }

    button::before {
      content: '';
      display: block;
      width: 0px;
      height: 86%;
      position: absolute;
      top: 7%;
      left: 0%;
      opacity: 0;
      background: #fff;
      box-shadow: 0 0 50px 30px #fff;
      -webkit-transform: skewX(-20deg);
      -moz-transform: skewX(-20deg);
      -ms-transform: skewX(-20deg);
      -o-transform: skewX(-20deg);
      transform: skewX(-20deg);
    }
    body::before {
    content: "";
    position: fixed;
    top: 0; left: 0;
    width: 100%; height: 100%;
    background: repeating-linear-gradient(
        45deg,
        rgba(255, 255, 255, 0.05),
        rgba(255, 255, 255, 0.05) 1px,
        transparent 1px,
        transparent 4px
    );
    z-index: 0;
    animation: moveBg 1s linear infinite;
    }

    @keyframes moveBg {
    0% { background-position: 0 0; }
    100% { background-position: 100px 100px; }
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
        
         body > * {
  position: relative;
  z-index: 1;
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
    z-index: 1;
">

    <!-- SINISTRA: link -->
    
    <div>
        <a href="leTueLeghe.jsp" style="color: #e10600; text-decoration: none; font-weight: bold;" style="pointer-events: auto; cursor: pointer;">Le Tue Leghe</a>
    </div>
    
	<div>
        <a href="menu.html" title="Torna al menu" style="pointer-events: auto; cursor: pointer;">
            <img src="images/logoFF1_small.png" alt="Indietro" style="height: 32px; cursor: pointer;">
        </a>
    </div>
	
    <!-- DESTRA: username -->
    <div style="font-weight: bold;">
        <%= username %>
    </div>
</div>
  
  <!-- Messaggio di successo/errore -->
  <% if (resultMsg != null) { %>
            <div class="error-message"><%= resultMsg %></div>
  <% } %>
           

  <div class="container" id="creazioneLega">
    <h1>CREA LA TUA LEGA</h1>
    <h3 class="subtitle">Sfida gli amici con una Lega personale</h3>
    
    <form id="creaLegaForm" action="creazioneLega.jsp" method="POST">
      <label for="leagueName">Nome della Lega</label>
      <input type="text" id="leagueName" name="leagueName"  required>

      <label for="maxParticipants">Numero massimo di partecipanti</label>
      <input type="number" id="maxParticipants" name="maxParticipants" min="2" max="20" required>

       <!-- Inserire qua le Squadre dell'utente -->
      <label for="team">Seleziona una tua squadra</label>
      <select id="team" name="team" required>
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

      <h4>Premi il pulsante per confermare le scelte e generare<br> il codice per condividere la tua Nuova Lega con gli amici</h4>
      <button type="submit">Crea Lega</button>
    </form>
  </div>
  <!-- Script check form -->
  <script>
	  document.getElementById("creaLegaForm").addEventListener("submit", function(event) {
	    const nomeLega = document.getElementById("leagueName").value.trim();
	    const maxPartecipanti = document.getElementById("maxParticipants").value.trim();
	    const squadraSelezionata = document.getElementById("team").value;
	
	    // Controllo nome lega
	    if (nomeLega === "") {
	      alert("Inserisci il nome della lega.");
	      //event.preventDefault();
	      return;
	    }
	
	    // Controllo numero partecipanti
	    const num = parseInt(maxPartecipanti, 10);
	    if (isNaN(num)) {
	      alert("Dev'essere numero");
	     // event.preventDefault();
	      return;
	    }
	
	    // Controllo squadra selezionata
	    if (squadraSelezionata === "") {
	      alert("Seleziona una squadra valida.");
	      //event.preventDefault();
	      return;
	    }
	
	    // Se tutti i controlli passano, il form viene inviato normalmente
	  });
  </script>
  <footer>
    <p>&copy; 2025 Fanta F1 - Powered by LMosca, BGregge, LZana</p>
  </footer>

</body>
</html>
