<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Crea la tua Squadra di Formula 1</title>
  <style>
    /* Tutto il CSS identico, non lo riporto qui per brevità */
  </style>
</head>
<body>
  
  <div style="display: flex;justify-content: center; align-items: center; gap: 10px; margin: 20px 0; font-family: system-ui, sans-serif; font-size: 16px; color: white;">
    <label for="wheels" style="min-width: 160px;">Wheels rimanenti:</label>
    <input type="text" id="wheels" value="100" readonly 
          style="background-color: white; color: black; border: 2px solid #e10600; padding: 5px 10px; width: 80px; text-align: center; font-weight: bold; border-radius: 4px;">
    <span style="color: #e10600; font-weight: bold; font-size: 18px;">₩</span>
  </div>

  <!-- Il form manda i dati via POST a una servlet "CreaSquadraServlet" (da implementare) -->
  <form id="f1Form" action="CreaSquadraServlet" method="post" onsubmit="return validateForm()">

    <h2 style="text-align: center; color: #e10600; font-family: system-ui, sans-serif; text-transform: uppercase; margin-bottom: 20px; letter-spacing: 1px;">
      Scegli una Leggenda
    </h2>

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
        <%-- Uso radio per scegliere solo una leggenda --%>
        <tr>
          <td>GER</td>
          <td>Michael Schumacher</td>
          <td>+15 punti per ogni Pilota della scuderia Ferrari arrivato sul podio</td>
          <td>28 ₩</td>
          <td><input type="radio" name="leggenda" value="Michael Schumacher" required></td>
        </tr>
        <tr>
          <td>BRA</td>
          <td>Ayrton Senna</td>
          <td>+15 punti per ogni Pilota della scuderia McLaren arrivato sul podio</td>
          <td>30 ₩</td>
          <td><input type="radio" name="leggenda" value="Ayrton Senna"></td>
        </tr>
        <tr>
          <td>GER</td>
          <td>Sebastian Vettel</td>
          <td>+15 punti per ogni Pilota della scuderia RedBull arrivato sul podio</td>
          <td>27 ₩</td>
          <td><input type="radio" name="leggenda" value="Sebastian Vettel"></td>
        </tr>
        <tr>
          <td>FIN</td>
          <td>Kimi Raikkonen</td>
          <td>+15 punti per ogni Pilota della tua Squadra arrivato sul podio</td>
          <td>24 ₩</td>
          <td><input type="radio" name="leggenda" value="Kimi Raikkonen"></td>
        </tr>
        <tr>
          <td>ARG</td>
          <td>Juan Manuel Fangio</td>
          <td>+20 punti per ogni pilota sudamericano in top 10</td>
          <td>24 ₩</td>
          <td><input type="radio" name="leggenda" value="Juan Manuel Fangio"></td>
        </tr>
        <tr>
          <td>FRA</td>
          <td>Alain Prost</td>
          <td>+10 punti per ogni pilota della tua Squadra che arriva davanti al suo compagno di scuderia</td>
          <td>24 ₩</td>
          <td><input type="radio" name="leggenda" value="Alain Prost"></td>
        </tr>
        <tr>
          <td>UK</td>
          <td>James Hunt</td>
          <td>+10 punti per ogni pilota britannico sul podio</td>
          <td>23 ₩</td>
          <td><input type="radio" name="leggenda" value="James Hunt"></td>
        </tr>
        <tr>
          <td>UK</td>
          <td>Nigel Mansell</td>
          <td>+12 punti per ogni pilota della scuderia Williams in top 10</td>
          <td>22 ₩</td>
          <td><input type="radio" name="leggenda" value="Nigel Mansell"></td>
        </tr>
        <tr>
          <td>GER</td>
          <td>Nico Rosberg</td>
          <td>+15 punti per ogni pilota della scuderia Mercedes sul podio</td>
          <td>20 ₩</td>
          <td><input type="radio" name="leggenda" value="Nico Rosberg"></td>
        </tr>
        <tr>
          <td>AUT</td>
          <td>Niki Lauda</td>
          <td>+8 punti per ogni Pilota della tua Squadra che finisce la gara</td>
          <td>24 ₩</td>
          <td><input type="radio" name="leggenda" value="Niki Lauda"></td>
        </tr>
      </tbody>
    </table>

    <h2 style="text-align: center; color: #e10600; font-family: system-ui, sans-serif; text-transform: uppercase; margin-bottom: 20px; letter-spacing: 1px;">
      Scegli 3 Piloti
    </h2>

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
        <%-- Checkbox per poter scegliere fino a 3 piloti --%>
        <tr>
          <td>MCO</td>
          <td>Charles Leclerc</td>
          <td>Ferrari</td>
          <td>28 ₩</td>
          <td><input type="checkbox" name="piloti" value="Charles Leclerc"></td>
        </tr>
        <tr>
          <td>UK</td>
          <td>Lewis Hamilton</td>
          <td>Ferrari</td>
          <td>27 ₩</td>
          <td><input type="checkbox" name="piloti" value="Lewis Hamilton"></td>
        </tr>
        <tr>
          <td>UK</td>
          <td>Lando Norris</td>
          <td>McLaren</td>
          <td>33 ₩</td>
          <td><input type="checkbox" name="piloti" value="Lando Norris"></td>
        </tr>
        <tr>
          <td>AUS</td>
          <td>Oscar Piastri</td>
          <td>McLaren</td>
          <td>33 ₩</td>
          <td><input type="checkbox" name="piloti" value="Oscar Piastri"></td>
        </tr>
        <tr>
          <td>NED</td>
          <td>Max Vertappen</td>
          <td>RedBull</td>
          <td>34 ₩</td>
          <td><input type="checkbox" name="piloti" value="Max Vertappen"></td>
        </tr>
        <tr>
          <td>JAP</td>
          <td>Yuki Tsunoda</td>
          <td>RedBull</td>
          <td>18 ₩</td>
          <td><input type="checkbox" name="piloti" value="Yuki Tsunoda"></td>
        </tr>
        <tr>
          <td>UK</td>
          <td>George Russell</td>
          <td>Mercedes</td>
          <td>27 ₩</td>
          <td><input type="checkbox" name="piloti" value="George Russell"></td>
        </tr>
        <tr>
          <td>ITA</td>
          <td>Kimi Antonelli</td>
          <td>Mercedes</td>
          <td>21 ₩</td>
          <td><input type="checkbox" name="piloti" value="Kimi Antonelli"></td>
        </tr>
        <tr>
          <td>THA</td>
          <td>Alexander Albon</td>
          <td>Williams</td>
          <td>19 ₩</td>
          <td><input type="checkbox" name="piloti" value="Alexander Albon"></td>
        </tr>
        <tr>
          <td>ESP</td>
          <td>Carlos Sainz</td>
          <td>Williams</td>
          <td>17 ₩</td>
          <td><input type="checkbox" name="piloti" value="Carlos Sainz"></td>
        </tr>
        <tr>
          <td>ESP</td>
          <td>Fernando Alonso</td>
          <td>Aston Martin</td>
          <td>20 ₩</td>
          <td><input type="checkbox" name="piloti" value="Fernando Alonso"></td>
        </tr>
        <tr>
          <td>GER</td>
          <td>Mick Schumacher</td>
          <td>Aston Martin</td>
          <td>16 ₩</td>
          <td><input type="checkbox" name="piloti" value="Mick Schumacher"></td>
        </tr>
        <tr>
          <td>FRA</td>
          <td>Pierre Gasly</td>
          <td>Alpine</td>
          <td>19 ₩</td>
          <td><input type="checkbox" name="piloti" value="Pierre Gasly"></td>
        </tr>
        <tr>
          <td>ESP</td>
          <td>Esteban Ocon</td>
          <td>Alpine</td>
          <td>18 ₩</td>
          <td><input type="checkbox" name="piloti" value="Esteban Ocon"></td>
        </tr>
      </tbody>
    </table>

    <h2 style="text-align: center; color: #e10600; font-family: system-ui, sans-serif; text-transform: uppercase; margin-bottom: 20px; letter-spacing: 1px;">
      Scegli 1 Costruttore
    </h2>

    <table>
      <thead>
        <tr>
          <th>Nome Scuderia</th>
          <th>Prezzo</th>
          <th></th>
        </tr>
      </thead>
      <tbody>
        <%-- Radio per scegliere un solo costruttore --%>
        <tr>
          <td>Ferrari</td>
          <td>30 ₩</td>
          <td><input type="radio" name="costruttore" value="Ferrari" required></td>
        </tr>
        <tr>
          <td>Mercedes</td>
          <td>25 ₩</td>
          <td><input type="radio" name="costruttore" value="Mercedes"></td>
        </tr>
        <tr>
          <td>RedBull</td>
          <td>28 ₩</td>
          <td><input type="radio" name="costruttore" value="RedBull"></td>
        </tr>
        <tr>
          <td>McLaren</td>
          <td>22 ₩</td>
          <td><input type="radio" name="costruttore" value="McLaren"></td>
        </tr>
        <tr>
          <td>Williams</td>
          <td>18 ₩</td>
          <td><input type="radio" name="costruttore" value="Williams"></td>
        </tr>
        <tr>
          <td>Aston Martin</td>
          <td>16 ₩</td>
          <td><input type="radio" name="costruttore" value="Aston Martin"></td>
        </tr>
        <tr>
          <td>Alpine</td>
          <td>16 ₩</td>
          <td><input type="radio" name="costruttore" value="Alpine"></td>
        </tr>
      </tbody>
    </table>

    <div style="text-align: center; margin: 30px 0;">
      <input type="submit" value="Conferma Squadra" style="font-size: 16px; font-weight: bold; background-color: #e10600; color: white; border: none; padding: 10px 20px; border-radius: 6px; cursor: pointer;">
    </div>

  </form>

  <script>
    // Controllo lato client per limitare a massimo 3 piloti selezionati
    function validateForm() {
      const checkedPiloti = document.querySelectorAll('input[name="piloti"]:checked');
      if (checkedPiloti.length !== 3) {
        alert("Seleziona esattamente 3 piloti.");
        return false;
      }
      // Altri controlli possono essere aggiunti qui (budget, leggenda, costruttore)
      return true;
    }
  </script>

</body>
</html>
