



function parsificaJSON( jsonText ) {

		var item = JSON.parse(jsonText);

		itemNodes = new Array(),

		risultato = "";
		
	// ciclo di lettura degli elementi
	for (    var a = 0, b = item.length;    a < b;   a++   ) {
		risultato += '<li>' + item[a].nome+', ('+item[a].x+','+item[a].y+'), '+ item[a].nome+ ", " +item[a].descrizione +'</li>';	
	}
     return risultato;
}// parsificaJSON()


/*
 * Funzione di callback
 */
function callback( theXhr, element ) {

	
	// verifica dello stato
	if ( theXhr.readyState === 2 ) {
	    	element.innerHTML = "Richiesta inviata...";
	}// if 2
	else if ( theXhr.readyState === 3 ) {
    		element.innerHTML = "Ricezione della risposta...";
	}// if 3
	else if ( theXhr.readyState === 4 ) {
		// verifica della risposta da parte del server
		if ( theXhr.status === 200 ) {
			// operazione avvenuta con successo
			
			element.innerHTML = parsificaJSON(theXhr.responseText);
			
		}// if 200

		 else {
	        	// errore di caricamento
	        	element.innerHTML = "Impossibile effettuare l'operazione richiesta.<br />";
	        	element.innerHTML += "Errore riscontrato: " + theXhr.statusText;
	        }// else (if ! 200)
	}// if 4

} // callback();








function prefetchPointOfInterestIframe() {
	// qui faccio scaricare al browser direttamente il contenuto del feed come src dell'iframe.
	Alert("Impossibile effettuare l'operazione, il tuo browser è troppo vecchio")
	
	// non riesco tuttavia a intervenire per parsificarlo! è il browser che renderizza il src del iframe!
}// caricaFeedIframe()



function prefetchPointOfInterestAJAX(cordX, cordY, xhr, element) {
	// impostazione controllo e stato della richiesta
	xhr.onreadystatechange = function() { callback(xhr, element); };
	// impostazione richiesta asincrona in GET
	// del file specificato
	try {
		xhr.open("get", "Server?x="+cordX+"&y="+cordY, true);
	}
	catch(e) {
		// Exceptions are raised when trying to access cross-domain URIs 
		alert(e);
	}
	// rimozione dell'header "connection" come "keep alive"
	xhr.setRequestHeader("connection", "close");
	// invio richiesta
	xhr.send(null);
} 

function myFunction(element)
{
	var y = myGetElementById('y').value;
	var x = myGetElementById('x').value;
	if(x == "" || y == "")
		return;
	if(element.id == 'risProbabili')
		{
			x = parseInt(x)+50;
		}
	var pattern = /[0-9]{1,3}/;
	if(pattern.test(x) && pattern.test(y))
		{
		var xhr = myGetXmlHttpRequest();

		if ( xhr ) 
			prefetchPointOfInterestAJAX(x, y, xhr, element); 
		else 
			prefetchPointOfInterestIframe(); 
		}
	else
		{
		alert("le coordinate devono essere formate da numeri da 0 a 999");
		}
}