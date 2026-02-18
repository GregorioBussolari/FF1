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
<%@ page import="net.codejava.user.dao.LegaDAO"%>
<%@ page import="net.codejava.user.dao.LegaDAOImpl"%>
<%@ page import="net.codejava.user.dao.IscrizioneLegaDAOImpl"%>
<%@ page import="net.codejava.user.dao.IscrizioneLegaDAO"%>
<%@ page import="net.codejava.user.model.Lega"%>
<%@ page import="net.codejava.user.model.IscrizioneLega"%>
<%@ page import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%
	String username = (String) session.getAttribute("utente");

	final DriverManagerDataSource dataSource;
	
	
	final LegaDAO daoLega;
	


	daoLega = new LegaDAOImpl();

	
	  List<Lega> leghe = daoLega.list();
	 
	  Controller controller = new Controller();
	  
	  String errorMsg = null;

	    if ("POST".equalsIgnoreCase(request.getMethod())) {
	        
	        String codiceLega = request.getParameter("codiceLega");
	        Lega l = daoLega.get(codiceLega);
			if(l!=null) {
				daoLega.delete(codiceLega);
				 response.sendRedirect("leTueLeghe.jsp");
	            return;
			}else {
	            errorMsg = "Impossibile cancellare la Lega";
	        }

	    }
	 Lega legaAttuale;

%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Le tue leghe</title>
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
        
        .header h1 {
		    text-align: center;
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
	    
	    .hidden {
		    display: none;
		}
        
    </style>
</head>
<body>



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
<% for(int i=0; i<leghe.size(); i++){
		legaAttuale = leghe.get(i);
		List<Utente> partecipanti = legaAttuale.getPartecipanti();
		boolean trovato=false;
		for(Utente u : partecipanti){
			if(u.getUsername().equals(username)){
				trovato=true;
				break;
			}
		}
		if(trovato){
		%>
 <h2><%= "LEGA: " + legaAttuale.getCodiceLega() + " - "+ legaAttuale.getNomeLega()  +"" %></h2>
 <%if(legaAttuale.getCreatore().equals(username)) { %>
 	<div>  <form action="leTueLeghe.jsp" method="post" onsubmit="return confermaEliminazione();" style="margin: 0;">
		            	<input type="hidden" name="codiceLega" value="<%= legaAttuale.getCodiceLega()%>">
		            	<button type="submit" 
					            style="background-color: #e10600; color: white; border: none; padding: 5px 10px; cursor: pointer; border-radius: 4px;">
					      Elimina
					    </button>
		            </form>
					</div>
	<%} %>
    <table id=<%="miaTabella"+i %> >
        <thead>
            <tr>
            	<th>Posizione</th>
                <th>Utente</th>
                <th>Squadra</th>
                <th>Componenti</th>
                <th>Punteggio</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <tbody>
		        <%
		        	List<Squadra> squadre = legaAttuale.getClassificaLega();
		        
		          for (Squadra s : squadre) {
		        	  
		        %>
		          <tr>
		            <td><%= (squadre.indexOf(s)+1) %></td>
		            <td><%= s.getUtente().getUsername() %></td>
		            <td><%= s.getNomeSquadra() %></td>
		            <td class="pilots">
                    <div><%= s.getPiloti()[0].getNomeCompleto() %></div>
                    <div><%= s.getPiloti()[1].getNomeCompleto() %></div>
                    <div><%= s.getPiloti()[2].getNomeCompleto() %></div>
                    <div><%= s.getCostruttori().getNomeCompleto() %></div>
                    <div><%= s.getLeggende().getNomeCompleto() %></div>
                </td>
		            <td><%= controller.calcolaPunteggioSquadra(s) %> </td>
		        
		          </tr>
		        <%}%>
		      </tbody>
            
        </tbody>
    </table>
  <%} } %>
</div>

	
<script>
  function confermaEliminazione() {
    return confirm("Sei sicuro di voler eliminare questa lega?");
  }
</script>

</body>
</html>
