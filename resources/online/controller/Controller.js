

// This class control the action of the page.

// @author Team:Try-Catch Jialiang Song 2410536s

// -------Move to GameScreen later------- 


<script type="text/javascript">
			
function initalize() {

	// --------------------------------------------------------------------------
	// You can call other methods you want to run when the page first loads here
	// --------------------------------------------------------------------------
	
	await startGame();
	getRoundNumber();
	
//		getCategories();
//		selectCategory();
	playRound();
//		getStats();

			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
</script>


<script type="text/javascript">

// Here, to call the REST API Methods

function getDeckFile(){
	
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/displauCards");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		alert(responseText);
	}
	
	xhr.send();
	
}

function getCategoryForMenu(){
	
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/categoryMenu");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		alert(responseText);
	}
	
	xhr.send();
	
}

function getAICategory(){
	
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/aiCategorySelection");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		alert(responseText);
	}
	
	xhr.send();
	
}

function getActivePlayer(){
	
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/activePlayer");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		alert(responseText);
	}
	
	xhr.send();
	
}

function getRoundNumber(){
	
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/roundNumber");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		alert(responseText);
	}
	
	xhr.send();
	
}

function getHumanCards(){
	
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/humanCards");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		alert(responseText);
	}
	
	xhr.send();
	
}

function getAICards(){
	
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/AI1Cards");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		alert(responseText);
	}
	
	xhr.send();
	
}

function getRoundDescription(){
	
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/roundDescription");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		alert(responseText);
	}
	
	xhr.send();
	
}




var newGameButton; // start a new Top Trumps Game
var viewStatistics; //Get statistics from past games

var nextCategorySelectionButton; 
var showWinnerButton;
var nextRound;

var infoLine;
var statusLine;
var statusBar;

var selectCat1 = document.getElementById("selectCat2ButtonName###")；
var selectCat2 = document.getElementById("selectCat2ButtonName###")；
var selectCat3 = document.getElementById("selectCat3ButtonName###")；
var selectCat4 = document.getElementById("selectCat4ButtonName###")；
var selectCat5 = document.getElementById("selectCat5ButtonName###")；


selectCat1.addEventListener("click", chooseCategory1);
selectCat2.addEventListener("click", chooseCategory2);
selectCat3.addEventListener("click", chooseCategory3);
selectCat4.addEventListener("click", chooseCategory4);
selectCat5.addEventListener("click", chooseCategory5);


// --------------FLASH-----------------

function caterotyHighlight(){
	x = document.getElementById("certainCategory"); // need exact method to choose the certain category
	x.style.color = "FF0000"; //RGB-255,0,0-red
}
</script>

<button>
type = "button" onclick = "categoryHighlight()"  // > Here need the certain button name that press
</button>
