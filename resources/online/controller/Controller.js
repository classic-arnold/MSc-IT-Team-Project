

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
	
	
	// This starts the game
	function startGame() {
		return new Promise(resolve=>{
			let numberOfAIPlayersFromUser = 4; //change this to get from button
		
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/startGame?numberOfAIPlayers=" + numberOfAIPlayersFromUser); // Request type and URL

			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}

			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				if(responseText == "0"){
					resolve();
				}
			};

			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();
		});
	}
	
	function getRoundNumber(){
		
		// First create a CORS request, this is the message we are going to send (a get request in this case)
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/roundNumber"); // Request type and URL

		// Message is not sent yet, but we can check that the browser supports CORS
		if (!xhr) {
			alert("CORS not supported");
		}

		// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
		// to do when the response arrives
		xhr.onload = function(e) {
			var responseText = xhr.response; // the text of the response
			$("#status-message").html("Round Number: " + responseText);
		};

		// We have done everything we need to prepare the CORS request, so send it
		xhr.send();	
	}
	
	function shouldHumanSelectCategory(){
		return new Promise(resolve=>{
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/shouldHumanSelectCategory"); // Request type and URL

			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}

			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				resolve(responseText);
			};

			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();	
		})
	}
	
	function getCategories(){
		return new Promise(resolve=>{
			// First create a CORS request, this is the message we are going to send (a get request in this case)
			var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/categoryMenu"); // Request type and URL

			// Message is not sent yet, but we can check that the browser supports CORS
			if (!xhr) {
				alert("CORS not supported");
			}

			// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
			// to do when the response arrives
			xhr.onload = function(e) {
				var responseText = xhr.response; // the text of the response
				resolve(responseText);
			};

			// We have done everything we need to prepare the CORS request, so send it
			xhr.send();	
		});
	}
	
	async function selectCategoryForHuman(){
		// First create a CORS request, this is the message we are going to send (a get request in this case)
		let categoryList = await getCategories();
		
		categoryList = changeStringToArray(categoryList);
		
		categorySelectedByHuman = categoryList[0];
		
		return categorySelectedByHuman;
		
	}
	
	async function selectCategoryForAI(){
		// First create a CORS request, this is the message we are going to send (a get request in this case)
		let categoryList = await getCategories();
		
		categoryList = changeStringToArray(categoryList);
		
		categorySelectedByHuman = categoryList[0];
		
		return categorySelectedByHuman;
		
	}
	
	function playRound(){
		let humanChooseCategory = await shouldHumanSelectCategory();
		
		let categories = await getCategories();
		
		let categorySelected;
		
		if(humanChooseCategory == "true"){
			categorySelectedByHuman = selectCategory();
		} else {
			
		}
		
		// First create a CORS request, this is the message we are going to send (a get request in this case)
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/game/roundNumber"); // Request type and URL

		// Message is not sent yet, but we can check that the browser supports CORS
		if (!xhr) {
			alert("CORS not supported");
		}

		// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
		// to do when the response arrives
		xhr.onload = function(e) {
			var responseText = xhr.response; // the text of the response
		};

		// We have done everything we need to prepare the CORS request, so send it
		xhr.send();	
	}

	// This calls the game REST method from TopTrumpsRESTAPI
	function getStats() {

		// First create a CORS request, this is the message we are going to send (a get request in this case)
		var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/stats/statistics"); // Request type and URL

		// Message is not sent yet, but we can check that the browser supports CORS
		if (!xhr) {
			alert("CORS not supported");
		}

		// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
		// to do when the response arrives 
		xhr.onload = function(e) {
			var responseText = xhr.response; // the text of the response
			responseText = changeStringToArray(responseText);
		};

		// We have done everything we need to prepare the CORS request, so send it
		xhr.send();		
	}
	
	function changeStringToArray(string){
		arr = string.split(",");
		
		arr.forEach((arrElem, i)=>{
			arr[i] = arrElem.replace(/[^A-Za-z0-9]/g, "");
		});
		
		return arr;
	}
	
	
	
	

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

//This is a reusable method for creating a CORS request. Do not edit this.
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

            // --------------------------------//
            // --------------------------------//
            //---------------------------------//


var newGameButton; // start a new Top Trumps Game
var viewStatistics; //Get statistics from past games

var nextCategorySelectionButton; 
var showWinnerButton;
var nextRound;

var infoLine;
var statusLine;

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
