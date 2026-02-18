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
	String username = (String) session.getAttribute("utente");

	final DriverManagerDataSource dataSource;
	
	final PilotaDAO daoP;
	final CostruttoreDAO daoC;
	final LeggendaDAO daoL;  
	final SquadraDAO daoS;
	

	daoP = new PilotaDAOImpl();
	daoC = new CostruttoreDAOImpl();
	daoL = new LeggendaDAOImpl();
	daoS = new SquadraDAOImpl();
	
	List<Pilota> piloti = daoP.list();                      
	  List<Costruttore> costruttori = daoC.list(); 
	  List<Leggenda> leggende = daoL.list();
	  List<Squadra> squadre = daoS.list();
	 
	  Controller controller = new Controller();
	  
	  String errorMsg = null;

	    if ("POST".equalsIgnoreCase(request.getMethod())) {
	        
	        String nomeSq = request.getParameter("nomeSquadra");
	        Squadra s = daoS.get(nomeSq, username);
			if(s!=null) {
				daoS.delete(nomeSq, username);
				 response.sendRedirect("leTueSquadre.jsp");
	            return;
			}else {
	            errorMsg = "Impossibile cancellare la squadra";
	        }

	    }

%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Le tue squadre</title>
    <style>
        body {
            margin: 0;
            font-family: 'Segoe UI', sans-serif;
            background-color: #121212;
            color: #fff;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 40px;
            background-color: #1c1c1c;
            border-bottom: 3px solid #e10600;
        }

        .header h1 {
            margin: 0 auto;
            font-size: 28px;
            color: #e10600;
        }

        .username {
            font-weight: bold;
        }

        .table-container {
            padding: 30px 40px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #1e1e1e;
        }

        th, td {
            border: 1px solid #333;
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #e10600;
            color: white;
        }

        td.pilots {
            display: flex;
            flex-direction: column;
            gap: 5px;
            justify-content: center;
            align-items: center;
        }

        tr:hover {
            background-color: #292929;
        }
        
        #loader {
	      position: fixed;
	      top: 0;
	      left: 0;
	      width: 100vw;
	      height: 100vh;
	      background-color: rgba(0, 0, 0, 0.75);
	      display: flex;
	      justify-content: center;
	      align-items: center;
	      z-index: 9999;
	    }
        
    </style>
</head>
<body>

<!-- LOADER -->
  <div id="loader">
    <img src="images/wait.gif" alt="Caricamento in corso..." style="width: 80px; height: 80px;">
  </div>

<div class="header">
		
    <div><a href="menu.html" title="Torna al menu">
    <img src="images/logoFF1_small.png" alt="Indietro" style="height: 32px; cursor: pointer;">
	</a></div> <!-- Placeholder per allineamento -->
    <h1>Le tue squadre</h1>
    <div class="username"><%= username %></div>
</div>
<div>
<% if (errorMsg != null) { %>
            <div class="error-message"><%= errorMsg %></div>
        <% } %>
</div>
<div class="table-container">
    <table>
        <thead>
            <tr>
                <th>Nome Squadra</th>
                <th>Piloti</th>
                <th>Costruttore</th>
                <th>Leggenda</th>
                <th>Punteggio</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tbody>
		        <%
		          for (Squadra s : squadre) {
		        	  if(s.getUtente().getUsername().equals(username)){
		        %>
		          <tr>
		            <td><%= s.getNomeSquadra() %></td>
		            <td class="pilots">
                    <div><%= s.getPiloti()[0].getNomeCompleto() %></div>
                    <div><%= s.getPiloti()[1].getNomeCompleto() %></div>
                    <div><%= s.getPiloti()[2].getNomeCompleto() %></div>
                </td>
		            <td><%= s.getCostruttori().getNomeCompleto() %></td>
		            <td><%= s.getLeggende().getNomeCompleto() %> </td>
		            <td><%= controller.calcolaPunteggioSquadra(s) %> </td>
		            <td>  <form action="leTueSquadre.jsp" method="post" onsubmit="return confermaEliminazione();" style="margin: 0;">
		            	<input type="hidden" name="nomeSquadra" value="<%= s.getNomeSquadra() %>">
		            	<button type="submit" 
					            style="background-color: #e10600; color: white; border: none; padding: 5px 10px; cursor: pointer; border-radius: 4px;">
					      Elimina
					    </button>
		            </form>
					</td>
		          </tr>
		        <%}}%>
		      </tbody>
            
        </tbody>
    </table>
</div>

	<script>
    window.addEventListener("load", function () {
      document.getElementById("loader").style.display = "none";
    });
  </script>
  
  <script>
  function showLoaderAndRedirect(url) {
    document.getElementById("loader").style.display = "flex";
    // Timeout per visibilità minima (opzionale)
    setTimeout(() => {
      window.location.href = url;
    }, 100); // attesa minima per mostrare il loader
  }
</script>
<script>
  function confermaEliminazione() {
    return confirm("Sei sicuro di voler eliminare questa squadra?");
  }
</script>

</body>
</html>
