<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.codejava.user.dao.UtenteDAOImpl"%>
<%@ page import="net.codejava.user.model.Utente"%>
<%@ page import="net.codejava.user.model.Pilota"%>
<%@ page import="net.codejava.user.model.Leggenda"%>
<%@ page import="net.codejava.user.model.Costruttore"%>
<%@ page import="net.codejava.user.model.Squadra"%>
<%@ page import="net.codejava.user.dao.UtenteDAO"%>
<%@ page import="net.codejava.user.dao.PilotaDAO"%>
<%@ page import="net.codejava.user.dao.LeggendaDAO"%>
<%@ page import="net.codejava.user.dao.CostruttoreDAO"%>
<%@ page import="net.codejava.user.dao.SquadraDAO"%>
<%@ page import="net.codejava.user.dao.PilotaDAOImpl"%>
<%@ page import="net.codejava.user.dao.LeggendaDAOImpl"%>
<%@ page import="net.codejava.user.dao.CostruttoreDAOImpl"%>
<%@ page import="net.codejava.user.dao.SquadraDAOImpl"%>
<%@ page import="net.codejava.user.controller.Controller"%>
<%@ page import="java.util.List"%>
<%@ page import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%

  final DriverManagerDataSource dataSource;

  final PilotaDAO daoP;
  final CostruttoreDAO daoC;
  final LeggendaDAO daoL;  

  

  daoP = new PilotaDAOImpl();
  daoC = new CostruttoreDAOImpl();
  daoL = new LeggendaDAOImpl();


  String username = (String) session.getAttribute("utente");
    
  List<Pilota> piloti = daoP.list();                      
  List<Costruttore> costruttori = daoC.list(); 
  List<Leggenda> leggende = daoL.list();
  
  String errorMsg = null;
  String successMsg = null;
  
  if ("POST".equalsIgnoreCase(request.getMethod())) {
	  String nome = request.getParameter("squadra");
	  String leggenda = request.getParameter("leggenda");
	  String[] pilotiCheck = request.getParameterValues("piloti");
	  String costruttore = request.getParameter("costruttore");
	  
	  Controller controller = new Controller();
	  Squadra squadra = controller.creaSquadra(nome, pilotiCheck, costruttore, leggenda, username);
	  if(squadra == null) {
		  errorMsg= "C'è stato un errore";
	  }
	  else{
		  successMsg= "Squadra creata con successo";
	  }
  }
%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Crea la tua Squadra di Fanta Formula 1</title>
  <style>


    body {
      font-family: 'Arial Black', sans-serif;
      background-color: #0f0f0f;
      color: #ffffff;
      margin: 0;
      padding: 0;
      background-image: url("images/blackcar.jpg");
      background-size: cover;        /* Adatta l'immagine per coprire tutto lo sfondo */
      background-repeat: no-repeat;  /* Evita la ripetizione */
      background-position: center;
    }

    header {
      font-family: 'Arial Black', sans-serif;
      font-size: 1 rem;
      color: #e10600;
      text-shadow: 0 0 10px #e10600;
      margin-bottom: 0.3em;
      letter-spacing: 0.15em;
    }

    header h1 {
      font-size: 3em;
      margin: 0;
      text-shadow: 2px 2px 5px #000;
    }

    form {
      background-color: rgba(28, 28, 28, 0.9); /* 0.8 = 80% opacità */
      padding: 30px;
      width: 100em;
      margin: 40px auto;
      border: 2px solid #e10600;
      border-radius: 10px;
    }

    h2 {
      color: #e10600;
      border-bottom: 2px solid #e10600;
      padding-bottom: 5px;
    }

    .section {
      margin-bottom: 25px;
    }

    label {
      display: block;
      margin: 10px 0;
    }

    input[type="checkbox"] {
      margin-right: 10px;
    }

    select {
      width: 100%;
      padding: 10px;
      font-size: 1em;
      background-color: #2b2b2b;
      color: white;
      border: 1px solid #e10600;
      border-radius: 5px;
    }

    button {
      background-color: #e10600;
      color: white;
      border: none;
      padding: 15px 30px;
      font-size: 1.2em;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    button:hover {
      background-color: #b00000;
    }

    footer {
      text-align: center;
      padding: 20px;
      background-color: #0f0f0f;
      color: #888;
    }

    table {
      width: 100%;
      border-collapse: collapse;
      background-color: rgba(28, 28, 28, 0.9);
      box-shadow: 0 0 10px #000;

    }

    thead {
      background-color: #e10600; /* Rosso F1 */
    }

    thead th {
      padding: 12px;
      font-size: 16px;
      text-transform: uppercase;
      color: white;
      border: 1px solid #333;

    }

    tbody td {
      padding: 10px;
      border: 1px solid #333;
      text-align: center;
      background-color: rgba(28, 28, 28, 0.9);

    }

    tbody tr:nth-child(even) {
            background-color: rgba(28, 28, 28, 0.9);

    }

    button {
        position: relative;
        padding: 10px 20px;
        border-radius: 7px;
        border: 1px solid rgb(255, 61, 61);
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

      @keyframes sh02 {
        from {
          opacity: 0;
          left: 0%;
        }

        50% {
          opacity: 1;
        }

        to {
          opacity: 0;
          left: 100%;
        }
      }

      button:active {
        box-shadow: 0 0 0 0 transparent;
        -webkit-transition: box-shadow 0.2s ease-in;
        -moz-transition: box-shadow 0.2s ease-in;
        transition: box-shadow 0.2s ease-in;
      }
      input[type="text"]{
            width: 98%;
            padding: 12px 15px;
            margin-bottom: 20px;
            border: 2px solid #444;
            border-radius: 6px;
            background: #222;
            color: #eee;
            font-size: 1rem;
            transition: border-color 0.3s ease;
        	text-align:center
        }
        input[type="text"]:focus{
            border-color: #e10600;
            outline: none;
            background: #1a1a1a;
        }
        
          .custom-option {
		    display: inline-block;
		    padding: 12px 20px;
		    margin: 10px 10px 0 0;
		    border: 2px solid #ff4d4d;
		    border-radius: 12px;
		    background: rgba(255, 77, 77, 0.1);
		    backdrop-filter: blur(8px);
		    color: #ff4d4d;
		    font-weight: 600;
		    cursor: pointer;
		    transition: all 0.3s ease;
		    position: relative;
		    user-select: none;
		  }

  .custom-option:hover {
    background: rgba(255, 77, 77, 0.2);
  }

  .custom-option.input[type="radio"],
  .custom-option.input[type="checkbox"] {
    display: none;
  }

  .custom-option.selected {
    background: #ff4d4d;
    color: #fff;
    transform: scale(1.05);
    box-shadow: 0 0 10px #ff4d4d80;
  }

  .custom-option:active {
    transform: scale(0.98);
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
        <a href="leTueSquadre.jsp" style="color: #e10600; text-decoration: none; font-weight: bold;">Le Tue Squadre</a>
    </div>

    <!-- CENTRO: wheels -->
    <div style="display: flex; justify-content: center; align-items: center; gap: 10px;">
        <label for="wheels" style="min-width: 160px;">Wheels rimanenti:</label>
        <input type="text" id="wheels" value="100" readonly
            style="background-color: white; color: black; border: 2px solid #e10600; padding: 5px 10px; width: 80px; text-align: center; font-weight: bold; border-radius: 4px;">
        <span style="color: #e10600; font-weight: bold; font-size: 18px;">₩</span>
    </div>

    <!-- DESTRA: username -->
    <div style="font-weight: bold;">
        <%= username %>
    </div>
</div>
</div>
  <% if (errorMsg != null) { %>
            <div class="error-message"><%= errorMsg %></div>
        <% } else if (successMsg != null) { %>
            <div class="success-message"><%= successMsg %></div>
        <% } %>
	
  <form id="f1Form" action="creaSquadra.jsp" method="POST" onsubmit="return validateForm()">
	<h2 style="
    text-align: center;
    color: #e10600;
    font-family: system-ui, sans-serif;
    text-transform: uppercase;
    margin-bottom: 20px;
    letter-spacing: 1px;
  ">Inserisci il nome della tua nuova squadra</h2>
    <input type="text" id="squadra" name="squadra" placeholder="Nome Squadra" required />
    
    <h2 style="
    text-align: center;
    color: #e10600;
    font-family: system-ui, sans-serif;
    text-transform: uppercase;
    margin-bottom: 20px;
    letter-spacing: 1px;
  ">Scegli una Leggenda</h2>

    <table>
      <thead>
        <tr>
          <th>Nazionalità</th>
          <th>Nome</th>
          <th>Abilità</th>
          <th>Prezzo</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <%
          for (Leggenda l : leggende) {
        %>
          <tr>
            <td><%= l.getNazione() %></td>
            <td><%= l.getNomeCompleto() %></td>
            <td><%= l.getAbilitaSpeciale() %></td>
            <td><%= (int)l.getPrezzo() %> ₩</td>
            <td><input type="radio" name="leggenda" value="<%= l.getNomeCompleto() %>"></td>
          </tr>
        <%}%>
      </tbody>
    </table>

    <h2 style="
    text-align: center;
    color: #e10600;
    font-family: system-ui, sans-serif;
    text-transform: uppercase;
    margin-bottom: 20px;
    letter-spacing: 1px;
  ">Scegli 3 Piloti</h2>
    <table>
      <thead>
        <tr>
          <th>Nazionalità</th>
          <th>Nome</th>
          <th>Scuderia</th>
          <th>Prezzo</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <%
          for (Pilota p : piloti) {
        %>
        <tr>
          <td><%= p.getNazione() %></td>
          <td><%= p.getNomeCompleto() %></td>
          <td><%= p.getCostruttore() %></td>
          <td><%= (int)p.getPrezzo() %> ₩</td>
          <td><input type="checkbox" name="piloti" value="<%= p.getNomeCompleto() %>"></td>
        </tr>
        <%}%>  
      </tbody>
    </table>

 <h2 style="
    text-align: center;
    color: #e10600;
    font-family: system-ui, sans-serif;
    text-transform: uppercase;
    margin-bottom: 20px;
    letter-spacing: 1px;
  ">Scegli un Costruttore</h2>
    <table>
      <thead>
        <tr>
          <th>Nazionalità</th>
          <th>Nome</th>
          <th>Prezzo</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <%
            for (Costruttore c : costruttori) {
        %>
        <tr>
          <td><%= c.getNazione() %></td>
          <td><%= c.getNomeCompleto() %></td>
          <td><%= (int)c.getPrezzo() %> ₩</td>
         <td><input type="radio" name="costruttore" value="<%= c.getNomeCompleto() %>"></td>
        </tr>
        <%}%>
      </tbody>
    </table>

    <div style="text-align: center; margin: 30px 0;">
      <button style="
        background-color: #e10600;
        color: white;
        border: none;
        padding: 16px 40px;
        font-size: 20px;
        font-weight: bold;
        border-radius: 8px;
        cursor: pointer;
        transition: background 0.3s ease;
      ">
        CREA SQUADRA
      </button>
    </div>

  </form>

  <footer>
    &copy; 2025 FF1
  </footer>

  <script>
    function validateForm() {
      const checkboxes = document.querySelectorAll('input[name="piloti"]:checked');
      if (checkboxes.length !== 3) {
        alert("Devi selezionare esattamente 3 piloti.");
        return false;
      }
      return true;
    }
    
    const maxWheels = 100;

    function estraiPrezzoDaRiga(input, colonna) {
      const tr = input.closest("tr");
      const prezzoTd = tr.cells[colonna]; // 4a colonna
      if (!prezzoTd) return 0;
      const prezzo = parseInt(prezzoTd.textContent.replace(/[^\d]/g, ""));
      return isNaN(prezzo) ? 0 : prezzo;
    }
    

    function aggiornaWheels() {
      let totale = 0;

      // Somma prezzi piloti
      document.querySelectorAll('input[name="piloti"]:checked').forEach(input => {
        totale += estraiPrezzoDaRiga(input,3);
      });

      // Somma leggenda
      const leggenda = document.querySelector('input[name="leggenda"]:checked');
      if (leggenda) {
        totale += estraiPrezzoDaRiga(leggenda,3);
      }

      // Somma costruttore
      const costruttore = document.querySelector('input[name="costruttore"]:checked');
      if (costruttore) {
        totale += estraiPrezzoDaRiga(costruttore,2);
      }

      const rimanenti = maxWheels - totale;
      const wheelsField = document.getElementById("wheels");
      wheelsField.value = rimanenti;

      // Stili e controllo pulsante
      const pulsante = document.querySelector('button[type="submit"]');
      if (rimanenti < 0) {
        wheelsField.style.borderColor = 'red';
        wheelsField.style.backgroundColor = '#ffcccc';
        pulsante.disabled = true;
        pulsante.style.opacity = 0.5;
        pulsante.style.cursor = 'not-allowed';
      } else {
        wheelsField.style.borderColor = '#e10600';
        wheelsField.style.backgroundColor = 'white';
        pulsante.disabled = false;
        pulsante.style.opacity = 1;
        pulsante.style.cursor = 'pointer';
      }
    }

    document.querySelectorAll('input[name="piloti"], input[name="leggenda"], input[name="costruttore"]').forEach(input => {
      input.addEventListener("change", aggiornaWheels);
    });

    window.addEventListener("DOMContentLoaded", aggiornaWheels);
    
  </script>

</body>
</html>
