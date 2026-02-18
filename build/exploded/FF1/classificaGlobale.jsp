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
<%@ page import="net.codejava.user.model.RisultatoGP"%>
<%@ page import="net.codejava.user.dao.RisultatoGPDAO"%>
<%@ page import="net.codejava.user.dao.RisultatoGPDAOImpl"%>
<%@ page import="org.springframework.jdbc.datasource.DriverManagerDataSource"%>
<%
	String username = (String) session.getAttribute("utente");

	
	final SquadraDAO dao;
	
	dao = new SquadraDAOImpl();
	
	final RisultatoGPDAO rgpdao= new RisultatoGPDAOImpl();
	
	List<RisultatoGP> risultati = rgpdao.list();

	  List<Squadra> squadre = dao.list();
	 
	  Controller controller = new Controller();
	  

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
    <h1>Classifica Globale</h1>
    <div class="username"><%= username %></div>
</div>

<div class="table-container">


 	
    <table id=<%="miaTabella" %> >
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
	        	squadre.sort((s1, s2) -> Float.compare(s2.calcolaPunteggioTotale(risultati), s1.calcolaPunteggioTotale(risultati)));

		        
		          for (Squadra s : squadre) {
		        	  
		        %>
		          <tr>
		            <td><strong><%= (squadre.indexOf(s)+1) %></strong></td>
		            <td><strong><%= s.getUtente().getUsername() %></strong></td>
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
</div>

	
<script>
  function confermaEliminazione() {
    return confirm("Sei sicuro di voler eliminare questa lega?");
  }
</script>

</body>
</html>
