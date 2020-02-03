

// This class control the action of the page.

// @author Team:Try-Catch Jialiang Song 2410536s

// -------Move to GameScreen later-------


<script type="text/javascript">
			
// Method that is called on page load
			function initalize() {
				
			}
			
			// -----------------------------------------
			// Add other Javascript methods Here
			// -----------------------------------------
		
			
			// This is a reusable method for creating a CORS request. Do not edit this.
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
		
function initalize() {
			
	// call other methods need to run when the page first loads here
				
			}


function displayCard(){
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList");
	
	if(!xhr){
		alert ("No cards in the deck");
	}
	
	xhr.onload = function(e){
		var responseText = xhr.response;
		var list = JSON.parse(responseText);
		
		
//		setRoundNumber();
//		displayNumberOfCard();
//		cardSectionVisible();
		
		xhr.send();
	}
}

function humanSelectCategory(c){
	
	var number = c;
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		var responseText = xhr.response;
		alert(responseText);
		
		document.getElementById('roundWInner').innerHTML = responseText;
		
	}
	
	
	xhr.send();
}


function AISelectCategory(){
	
	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		document.getElementById('roundWinner').innerHTML = responseText;
	}
	
	xhr.send();
}

function activePlayer(){

	var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		document.getElementById('roundWinner').innerHTML = responseText;
	}
	
	xhr.send();
	
}


function roundNumber(){
var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList");
	
	if(!xhr){
		alert ("CORS NOT SUPPORTED");
	}
	
	xhr.onload = function(e){
		
		var responseText = xhr.response;
		document.getElementById('roundWinner').innerHTML = responseText;
	}
	
	xhr.send();
}



</script>









var chooseCard = "(Choose Card)";

var numberOfCategories = 5;

// model part

var data_header = ["Description", "Size", "Speed", "Range", "Firepower", "Cargo"];

//var card = {Description:"Avenger", 
//              Size:"2", 
//              Speed:"5", 
//              Range:"4", 
//              Firepower:"3",
//              Cargo:"2"}

var currentRound = 0;
//var roundWinPage = "";
//var roundLoseMsg = "";
//var roundDrawPage = "";

//var finalWinnerPage = "";



var newGameButton; // start a new Top Trumps Game
var gameStatisticsButton; //Get statistics from past games

var nextCategorySelectionButton; 
var showWinnerButton;
var nextRound;

var infoLine;
var statusLine;
var statusBar;


newGameButton = doucument.getElementById("newGame");
newGameButton.addEventListener("click",initiateNewGame);

gameStatisticsButton = doucument.doucument.getElementById("gameStatistic");
gameStatisticsButton.addEventListener("click",gameStatistic);

nextCategorySelectionButton = doucument.getElementById("selectCategory");
nextCategorySelectionButton.addEventListener("click",xxxfunction);

nextRound = doucument.getElementById("nextRound");
nextRound.addEventListener("click",xxxfunction);



function initiateNewGame(){
	window.location.reload();
}

function initiateGame(){
	shuffleCards();
	createDeck();
	
	//differentButton;
	
	initiateRound();
}





//function newGame()
//{
//	 
//	// innerHTML=xxx
//	window.location.reload()
//	
//	}
//

//
//<button type="newGame" onclick="newGame()"> Start a new Top Trumps Game </button>
//
// function to randomly shuffle arrays maybe..


// The later part get from the API 
function shuffleCards(){
	
}

function createDeck(){
	
}



// --------------FLASH-----------------
// For view

<p id = "certainCategory">
view.categoryChoose // Note: Fri meeting--不知道这里能不能行。 content here.

</p>

<script>
function caterotyHighlight(){
	x = document.getElementById("certainCategory"); // need exact method to choose the certain category
	x.style.color = "FF0000"; //RGB-255,0,0-red
}
</script>

<button>
type = "button" onclick = "categoryHighlight()"  // > Here need the certain button name that press
</button>