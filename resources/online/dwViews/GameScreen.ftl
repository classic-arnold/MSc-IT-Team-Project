<html>
	<head>
		<!-- Web page title -->
		<title>Top Trumps</title>

		<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
		<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
		<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
		<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
		<script>vex.defaultOptions.className = 'vex-theme-os';</script>
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
		<link href="https://fonts.googleapis.com/css?family=Montserrat|Orbitron&display=swap" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Lato|Roboto+Mono|Source+Sans+Pro&display=swap" rel="stylesheet">

		<!-- Additional styles -->
		<style type="text/css">
			/* Navbar Styles*/

			.navbar-brand {
				font-family: 'Orbitron', sans-serif;
				font-size: 27px;
				font-weight: bold;
			}

			.navbar {
				border-bottom: 5px solid;
				border-image-source: linear-gradient(to right, #659999, #f4791f);
				border-image-slice: 1;
			}

			.navbar .nav-link {
				font-size: 15px;
			}

			/*Section 1 Styles*/

			.section1 {
				background: #C33764;
				/* fallback for old browsers */
				background: -webkit-linear-gradient(to bottom, rgba(29, 38, 113, 0.7), rgba(195, 55, 100, 0.7));
				/* Chrome 10-25, Safari 5.1-6 */
				background: linear-gradient(to bottom, rgba(29, 38, 113, 0.7), rgba(195, 55, 100, 0.7));
				/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
				background: url(/assets/testsidebar.jpg);
				background-repeat: no repeat;
				background-size: 100vh;
				border-right: 2px solid black;
			}

			#actionButton {
				font-size: 13px;
				padding-top: 30px;
				padding-bottom: 30px;
				padding-right: 10px;
				padding-left: 10px;
			}

			.action-div {
		   
				margin-top: 100px;
			}

			/*Section 2 Styles*/
			/*Section 2 Styles*/
			.section2 {
				background-color: #DCE6E5;
			}

			#status-col {
				padding: 0px;

			}

			.alert {
				border-radius: 0;
				border: none;

			}


			/*Card Styles*/
			.list-group-item {
				border: 1px solid black;
				padding: 2px;
			}

			.cardrow2 {
				padding-top: 10px;
			}


			.card-img-top {
				width: 50%;
				border-radius: 20px;
				clip: rect(0px, 60px, 200px, 0px);
				margin-top:10px;
			
			}

			.card {
				box-shadow: 0 0 10px black;
				font-family: 'Roboto Mono', monospace;
				font-weight: bold;
			}

			.card-title {
				text-align: center;
			}
			.badge-primary {
				color: black;
				background-color: #fafafa;
				font-size: 15px;
				border: 1px solid grey;
				border-radius: 0px;
				padding-left: 10px;
				padding-right: 10px;
		  
			}
			.card-body {
				padding: 0.5rem;
			}
						
		</style>

	</head>

	<body onload="initalize()"> <!-- Call the initalize method when the page loads -->
		<nav class="navbar navbar-expand-lg navbar-dark " style="background-color:black">
			<a class="navbar-brand" href="#">STAR CITIZEN TOP TRUMPS</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<a class="nav-link" href="/toptrumps">Home <span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="/toptrumps/stats">View Statistics</a>
					</li>
				</ul>
			</div>
		</nav>


		<div class="container-fluid h-100">
			<div class="row h-100">
				<div class="col-sm-3 section1 ">
					<div class="row justify-content-center ">
						<div class="col-sm-6 action-div">

							<button id="actionButton" type="button" class="btn btn-dark btn-block">ACTION TITLE HERE</button>
						</div>
					</div>
				</div>
				<div class="col-sm-9 section2">
					<div class="row">
						<div id="status-col" class="col-sm-12">
							<div class="alert alert-info" role="alert">
								<p id="status-message"></p>
							</div>
						</div>
					</div>
					<div class="row justify-content-center">
						<div class="card-deck">
							<div class="card" style="width: 3rem;">
								<div id="playerOneCard" class="card-header">Player Name <span id="deckOne"
																							  class="badge badge-primary float-right">4</span>
								</div>
								<div class="row justify-content-center">
								<img class="card-img-top" src="http://dcs.gla.ac.uk/~richardm/TopTrumps/Avenger.jpg" width="100" height="100"
									 alt="Card image cap">
									 </div>
								<div class="card-body">
									<h5 id="cardOneTitle" class="card-title">Avenger</h5>
									<ul class="list-group list-group-flush">
										<li id="cardOneCatOne" class="list-group-item">Size<span id="cardOneValOne"
																								 class="float-right">4</span>
										</li>
										<li id="cardOneCatTwo" class="list-group-item">Speed<span id="cardOneValTwo"
																								  class="float-right">4</span>
										</li>
										<li id="cardOneCatThree" class="list-group-item">Range<span id="cardOneValThree"
																									class="float-right">4</span>
										</li>
										<li id="cardOneCatFour" class="list-group-item">Firepower<span id="cardOneValFour"
																									   class="float-right">4</span>
										</li>
										<li id="cardOneCatFive" class="list-group-item">Cargo<span id="cardOneValFive"
																								   class="float-right">4</span>
										</li>
									</ul>
								</div>
							</div>
							<div class="card">
								<div id="playerTwoCard" class="card-header">Player Name <span id="deckTwo"
																							  class="badge badge-primary float-right">4</span>
								</div>
								 <div class="row justify-content-center ">
								<img class="card-img-top" src="http://placekitten.com/300/300" alt="Card image cap">
								</div>
								<div class="card-body">
									<h5 class="card-title">m50</h5>
									<ul class="list-group list-group-flush">
										<li id="cardTwoCatOne" class="list-group-item">Size<span id="cardTwoValOne"
																								 class="float-right">4</span>
										</li>
										<li id="cardTwoCatTwo" class="list-group-item">Speed<span id="cardTwoValTwo"
																								  class="float-right">4</span>
										</li>
										<li id="cardTwoCatThree" class="list-group-item">Range<span id="cardTwoValThree"
																									class="float-right">4</span>
										</li>
										<li id="cardTwoCatFour" class="list-group-item">Firepower<span id="cardTwoValFour"
																									   class="float-right">4</span>
										</li>
										<li id="cardTwoCatFive" class="list-group-item">Cargo<span id="cardTwoValFive"
																								   class="float-right">4</span>
										</li>
									</ul>
								</div>
							</div>
							<div class="card">
								<div id="playerThreeCard" class="card-header">Player Name <span id="DeckThree"
																								class="badge badge-primary float-right">4</span>
								</div>
								 <div class="row justify-content-center ">
								<img class="card-img-top" src="http://placekitten.com/300/300" alt="Card image cap">
								</div>
								<div class="card-body">
									<h5 id="cardTwoTitle" class="card-title">Orion</h5>
									<ul class="list-group list-group-flush">
										<li id="cardThreeCatOne" class="list-group-item">Size<span id="cardThreeValOne"
																								   class="float-right">4</span>
										</li>
										<li id="cardThreeCatTwo" class="list-group-item">Speed<span id="cardThreeValTwo"
																									class="float-right">4</span>
										</li>
										<li id="cardThreeCatThree" class="list-group-item">Range<span id="cardThreeValThree"
																									  class="float-right">4</span>
										</li>
										<li id="cardThreeCarFour" class="list-group-item">Firepower<span id="cardThreeValFour"
																										 class="float-right">4</span>
										</li>
										<li id="cardThreeCatFive" class="list-group-item">Cargo<span id="cardThreeValFive"
																									 class="float-right">4</span>
										</li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<div class="row justify-content-center cardrow2">
						<div class="card-deck">
							<div class="card" style="width: 3rem;">
								<div id="playerFourCard" class="card-header">Player Name <span id="deckFour"
																							   class="badge badge-primary float-right">4</span>
								</div>
								 <div class="row justify-content-center ">
								<img class="card-img-top" src="http://placekitten.com/300/300" alt="Card image cap">
								</div>
								<div class="card-body">
									<h5 id="cardThreeTitle" class="card-title">Hawk</h5>
									<ul class="list-group list-group-flush">
										<li id="cardFourCatOne" class="list-group-item">Size<span id="cardFourValOne"
																								  class="float-right">4</span>
										</li>
										<li id="cardFourCatTwo" class="list-group-item">Speed<span id="cardFourValTwo"
																								   class="float-right">4</span>
										</li>
										<li id="cardFourCatThree" class="list-group-item">Range<span id="cardFourValThree"
																									 class="float-right">4</span>
										</li>
										<li id="cardFourCatFour" class="list-group-item">Firepower<span id="cardFourValFour"
																										class="float-right">4</span>
										</li>
										<li id="cardFourCatFive" class="list-group-item">Cargo<span id="cardFourValFive"
																									class="float-right">4</span>
										</li>
									</ul>
								</div>
							</div>
							<div class="card">
								<div id="playerFiveCard" class="card-header">Player Name <span id="DeckFive"
																							   class="badge badge-primary float-right">4</span>
								</div>
								 <div class="row justify-content-center ">
								<img class="card-img-top" src="http://placekitten.com/300/300" alt="Card image cap">
								</div>
								<div class="card-body">
									<h5 id="cardFourTitle" class="card-title">Hurricane</h5>
									<ul class="list-group list-group-flush">
										<li id="cardFiveCatOne" class="list-group-item ">Size<span id="cardFiveValOne"
																								   class="float-right">4</span>
										</li>
										<li id="cardFiveCatTwo" class="list-group-item ">Speed<span id="cardFiveValTwo"
																									class="float-right">4</span>
										</li>
										<li id="cardFiveCatThree" class="list-group-item ">Range<span id="cardFiveValThree"
																									  class="float-right">4</span>
										</li>
										<li id="cardFiveCatFour" class="list-group-item">Firepower<span id="cardFiveValFour"
																										class="float-right">4</span>
										</li>
										<li id="cardFiveCatFive" class="list-group-item">Cargo<span id="cardFiveValFive"
																									class="float-right">4</span>
										</li>
									</ul>
								</div>
							</div>
							<div class="card">
								<div class="card-header">Player Name <span class="badge badge-primary float-right">4</span>
								</div>
								 <div class="row justify-content-center ">
								<img class="card-img-top" src="http://placekitten.com/300/300" alt="Card image cap">
								</div>
								<div class="card-body">
									<h5 id="cardFiveTitle" class="card-title">Sabre</h5>
									<ul class="list-group list-group-flush">
										<li class="list-group-item">Size<span class="float-right">4</span></li>
										<li class="list-group-item">Speed<span class="float-right">4</span></li>
										<li class="list-group-item">Range<span class="float-right">4</span></li>
										<li class="list-group-item">Firepower<span class="float-right">4</span></li>
										<li class="list-group-item">Cargo<span class="float-right">4</span></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
		
//			Needed methods

// 			getRoundNumber();
// 			shouldHumanSelectCategory();
// 			getPlayerToChooseRound();
// 			getDeck();
// 			getRoundCategory();
// 			getRoundCards();
// 			getRoundWinner();
// 			getNumberOfCardInCommonPile();
// 			wasRoundDraw();
// 			getGamePlayers();
// 			getGameScores();
// 			getGameStatistics();
// 			getGameWinner();
// 			getPlayerDeck(playerName);
// 			getNumberOfRoundsInGame();

			// Method that is called on page load
			async function initalize() {

				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				await startGame();
				getRoundNumber();
				shouldHumanSelectCategory();
// 				getCategories();
// 				selectCategory();
				playRound();
// 				if(humanChooseCategory == "true"){
// 					selectCategory();
// 				}

			}

			// -----------------------------------------
			// Add your other Javascript methods Here
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
			
			function getNumberOfAIPlayersFromUser(){
				let numberOfAIPlayersFromUser = prompt("How many AI players do you want?");
				while(isNaN(numberOfAIPlayersFromUser) || numberOfAIPlayersFromUser == null || numberOfAIPlayersFromUser>4 || numberOfAIPlayersFromUser<1){
					numberOfAIPlayersFromUser = prompt("Invalid Input. Please pick a number between 0 and 4 (inclusive).\nHow many AI players do you want?");
				}
				return numberOfAIPlayersFromUser;
			}
	
			// This starts the game
			function startGame() {
				return new Promise(resolve=>{
					let numberOfAIPlayersFromUser = getNumberOfAIPlayersFromUser();
				
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
// 						alert(responseText); // lets produce an alert
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
					alert(responseText); // lets produce an alert
				};
		
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			function shouldHumanSelectCategory(){
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
					alert(responseText); // lets produce an alert
				};
		
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
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
						alert(responseText); // lets produce an alert
						resolve(responseText);
					};
		
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				});
			}
			
			async function selectCategory(){
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				let categoryList = await getCategories();
				
				categoryList = categoryList.split(",");
				
				categoryList.forEach((category, i)=>{
					categoryList[i] = category.replace(/[^A-Za-z]/g, "");
				});
				
				let categoryString = "Please enter a number to select a category from:\n";
				
				categoryList.forEach((category, i)=>{
					categoryString += (i+1) + " - " + category + "\n";
				});
				
				let categorySelectedByHuman = prompt(categoryString);
				while(isNaN(categorySelectedByHuman) || categorySelectedByHuman == null || categorySelectedByHuman>5 || categorySelectedByHuman<1){
					categorySelectedByHuman = prompt("Invalid Input. " + categoryString);
				}
				
				return categorySelectedByHuman;
				
			}
			
			function playRound(){
			
				let categorySelectedByHuman = selectCategory();
				
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
					alert(responseText); // lets produce an alert
				};
		
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
	
			// This calls the game REST method from TopTrumpsRESTAPI
			function stats() {
	
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/stats"); // Request type and URL
		
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
					var responseText = xhr.response; // the text of the response
					//alert(responseText); // lets produce an alert
				};
		
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
		</script>
	</body>
</html>
