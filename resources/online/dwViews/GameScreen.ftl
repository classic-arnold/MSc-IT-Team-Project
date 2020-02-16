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

        h2{
            color:white;
            font-size: 3em;
        }

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
        #actionButtonDiv{
            display:none;
            width:100%;
        }

        #selectPlayersMenu{
            display:inline;
        }

        .action-div {
            margin-top: 100px;
        }


        /*Section 2 Styles*/
           .card-front{
        display:block;
        }
        
        .card-back{
        
        font-size:50px;
     border-top:20px solid black;
     border-bottom:20px solid black;
    margin-top:20px;
    display:none;
        
        }
        .section2 {
            background-color: #DCE6E5;
        }

        #status-col {
            padding: 0px;
        }

        .alert {
            display: none;
            border-radius: 0;
            border: none;
            padding-top:0px;
            padding-bottom:0px;
        }
        .alert-info{
            background-color:#F0A202;

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
            width: 100%;
            height:100px;
            border-radius: 0;
        }


        .card {
            box-shadow: 0 0 10px black;
            /*color for on highlight*/
            /*box-shadow: 0 0 10px #FF8300;*/
            font-family: 'Roboto Mono', monospace;
            font-weight: bold;
            padding:0;
            width:250.938px;
            height:328.500px
            
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
        h3{
            color:white;
            font-size:20px;
            font-family: 'Roboto Mono', monospace;
            font-weight: bold;
        }

        #status-message{
            color:white;
            font-size:20px;
            font-family: 'Roboto Mono', monospace;
            font-weight: bold;
        }

        #common-pile{
            display: none;
            color: black;
            font-size:20px;
            font-family: 'Roboto Mono', monospace;
            font-weight: bold;
            border-color: #FFA500;
        }

        .table{
            color:white;
        }

        .table th, .table td {
            border-top:none;
        }

        .action-div .btn{
            background-color:rgba(9,36,55,0.6);
            font-size:20px;
            box-shadow: 0px 0px 5px #5DBCD2;
            transition-duration: 0.4s;
        }

        .action-div .btn:hover{

            box-shadow: 0px 0px 15px #FFA500;

        }

        .cat-selected{
            color: #F0A202;
            -webkit-transition: all 0.5s;
            -moz-transition: all 0.5s;
            -o-transition: all 0.5s;
            transition: all 0.5s;
        }

        .active-player-card{
            box-shadow: 0px 0px 15px #FFA500;
        }

        .winning-card{
            animation: shake 1.5s cubic-bezier(.50,.09,.23,.87) both;
            transform: translate3d(0, 0, 0);
            backface-visibility: hidden;
            perspective: 1000px;
        }

        @keyframes shake {
            10%, 90% {
                transform: translate3d(-1px, 0, 0);
            }

            20%, 80% {
                transform: translate3d(2px, 0, 0);
            }

            30%, 50%, 70% {
                transform: translate3d(-4px, 0, 0);
            }

            40%, 60% {
                transform: translate3d(4px, 0, 0);
            }
        }

        @keyframes changeCard {
            0%{
                transform: rotateY(0deg);
            }

            25%{
                transform: rotateY(45deg);
            }

            50%{
                transform: rotateY(90deg);
            }

            75% {
                transform: rotateY(45deg);
            }

            100% {
                transform: rotateY(0deg);
            }

        }

        .flip-card{
            animation: changeCard 0.5s;
        }

        .table, #selectPlayersMenu, #actionButtonReal, .categories{
            display: none;
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
        <div class="col-sm-3 section1">
            <div class="row justify-content-center p-5">
                <div id="actionButtonDiv" class="action-div">
                    <span id="actionButton" class="text-center"></span>
                </div>

                <div class="action-div">
                    <button id="actionButtonReal" class="btn btn-block text-light"></button>
                </div>

                <div id="selectPlayersMenu" class="action-div">
                    <h2 id="selection-choice-menu" class="font-weight-bold">Please select the number of AI Players</h2>
                    <!--
                                                <input type="hidden" class="custom-select w-100" id="num-player-select"/>
                    <!~~ 								<option>Select</option> ~~>
                                                    <!~~
                    <option value="1">One</option>
                                                    <option value="2">Two</option>
                                                    <option value="3">Three</option>
                                                    <option value="4">Four</option>
                     ~~>
                     -->
                    <div class="select-player-btns">
                        <button class="one btn btn-block text-white">1</button>
                        <button class="two btn btn-block text-white">2</button>
                        <button class="three btn btn-block text-white">3</button>
                        <button class="four btn btn-block text-white">4</button>
                    </div>
                </div>
                <div id="selectCategoryMenu" class="action-div categories">
                    <h2 id="selection-category-menu" class="font-weight-bold">Please select your category</h2>
                    <!--
<select class="custom-select w-100" id="category-select">
								<option>Select</option>
								<option value=""></option>
								<option value=""></option>
								<option value=""></option>
								<option value=""></option>
								<option value=""></option>
							</select>
 -->
							
							<div class="select-cat-btns">
								<button class="cat-btn-1 btn btn-block text-white"></button>
								<button class="cat-btn-2 btn btn-block text-white"></button>
								<button class="cat-btn-3 btn btn-block text-white"></button>
								<button class="cat-btn-4 btn btn-block text-white"></button>
								<button class="cat-btn-5 btn btn-block text-white"></button>
							</div>
						</div>
						<div id="displayEndScores" class="action-div">
						<table class="table">
						 <thead>
    					<tr>
     					<th>Player</th>
            			<th>Score</th>
          				</tr>
            			</thead>
            			
						<tbody>
						</tbody>
						</table>
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
					<div id="common-pile" class="badge badge-primary text-center w-100">Common Pile: 0</div>
					
					<div class="card-deck row justify-content-center mt-3">
					
					<div id="cardOne" class="card col-3">
						<div id="playerOneCard" class="card-header"><span class="player-name"></span><span id="deckOne"
																										   class="badge badge-primary float-right cards-left"></span>
						</div>
	
						<p id="cardBackOne" class="card-back">STAR CITIZEN</p>
						<div class="card-front">
							<img class="card-img-top" src="http://placekitten.com/300/300"  alt="Card image cap">
							<div class="card-body">
					 
								<h5 id="cardOneTitle" class="card-title"></h5>
								<ul class="list-group list-group-flush">
									<li id="cardOneCatOne" class="list-group-item card-cat1"><span class="cat-1"></span><span id="cardOneValOne"
																															  class="float-right card-val1"></span>
									</li>
									<li id="cardOneCatTwo" class="list-group-item card-cat2"><span class="cat-2"></span><span id="cardOneValTwo"
																															  class="float-right card-val2"></span>
									</li>
									<li id="cardOneCatThree" class="list-group-item card-cat3"><span class="cat-3"></span><span id="cardOneValThree"
																																class="float-right card-val3"></span>
									</li>
									<li id="cardOneCatFour" class="list-group-item card-cat4"><span class="cat-4"></span><span id="cardOneValFour"
																															   class="float-right card-val4"></span>
									</li>
									<li id="cardOneCatFive" class="list-group-item card-cat5"><span class="cat-5"></span><span id="cardOneValFive"
																															   class="float-right card-val5"></span>
									</li>
								</ul>
				  
							</div>
						</div>
					</div>
					<div id="cardTwo" class="card col-3">
						<div id="playerTwoCard" class="card-header"><span class="player-name"></span><span id="deckTwo"
																										   class="badge badge-primary float-right cards-left"></span>
						</div>
						 <p id="cardBackTwo" class="card-back">STAR CITIZEN</p>
						 <div class="card-front">
							<img class="card-img-top" src="http://placekitten.com/300/300"  alt="Card image cap">
							<div class="card-body">
					  
								<h5 id="cardTwoTitle"class="card-title "></h5>
								<ul class="list-group list-group-flush">
									<li id="cardTwoCatOne" class="list-group-item card-cat1"><span class="cat-1"></span><span id="cardTwoValOne"
																															  class="float-right card-val1"></span>
									</li>
									<li id="cardTwoCatTwo" class="list-group-item card-cat2"><span class="cat-2"></span><span id="cardTwoValTwo"
																															  class="float-right card-val2"></span>
									</li>
									<li id="cardTwoCatThree" class="list-group-item card-cat3"><span class="cat-3"></span><span id="cardTwoValThree"
																																class="float-right card-val3"></span>
									</li>
									<li id="cardTwoCatFour" class="list-group-item card-cat4"><span class="cat-4"></span><span id="cardTwoValFour"
																															   class="float-right card-val4"></span>
									</li>
									<li id="cardTwoCatFive" class="list-group-item card-cat5"><span class="cat-5"></span><span id="cardTwoValFive"
																															   class="float-right card-val5"></span>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<div id="cardThree" class="card col-3">
						<div id="playerThreeCard" class="card-header"><span class="player-name"></span><span id="DeckThree"
																											 class="badge badge-primary float-right cards-left"></span>
						</div>
							<p id="cardBackThree" class="card-back">STAR CITIZEN</p>
							<div class="card-front">
								<img class="card-img-top" src="http://placekitten.com/300/300"  alt="Card image cap">
				
								<div class="card-body ">
		   
									<h5 id="cardThreeTitle" class="card-title"></h5>
									<ul class="list-group list-group-flush">
										<li id="cardThreeCatOne" class="list-group-item card-cat1"><span class="cat-1"></span><span id="cardThreeValOne"
																																	class="float-right card-val1"></span>
										</li>
										<li id="cardThreeCatTwo" class="list-group-item card-cat2"><span class="cat-2"></span><span id="cardThreeValTwo"
																																	class="float-right card-val2"></span>
										</li>
										<li id="cardThreeCatThree" class="list-group-item card-cat3"><span class="cat-3"></span><span id="cardThreeValThree"
																																	  class="float-right card-val3"></span>
										</li>
										<li id="cardThreeCarFour" class="list-group-item card-cat4"><span class="cat-4"></span><span id="cardThreeValFour"
																																	 class="float-right card-val4"></span>
										</li>
										<li id="cardThreeCatFive" class="list-group-item card-cat5"><span class="cat-5"></span><span id="cardThreeValFive"
																																	 class="float-right card-val5"></span>
										</li>
									</ul>
								</div>
						</div>
					</div>
				</div>
				<br/>
				<div class="card-deck row justify-content-center">
					<div id="cardFour" class="card col-3">
						<div id="playerFourCard" class="card-header"><span class="player-name"></span><span id="deckFour"
																											class="badge badge-primary float-right cards-left"></span>
						</div>
							 <p id="cardBackFour" class="card-back">STAR CITIZEN</p>
								<div class="card-front">
							<img class="card-img-top " src="http://placekitten.com/300/300"  alt="Card image cap">
							<div class="card-body ">
				 
								<h5 id="cardFourTitle" class="card-title"></h5>
								<ul class="list-group list-group-flush">
									<li id="cardFourCatOne" class="list-group-item card-cat1"><span class="cat-1"></span><span id="cardFourValOne"
																															   class="float-right card-val1"></span>
									</li>
									<li id="cardFourCatTwo" class="list-group-item card-cat2"><span class="cat-2"></span><span id="cardFourValTwo"
																															   class="float-right card-val2"></span>
									</li>
									<li id="cardFourCatThree" class="list-group-item card-cat3"><span class="cat-3"></span><span id="cardFourValThree"
																																 class="float-right card-val3"></span>
									</li>
									<li id="cardFourCatFour" class="list-group-item card-cat4"><span class="cat-4"></span><span id="cardFourValFour"
																																class="float-right card-val4"></span>
									</li>
									<li id="cardFourCatFive" class="list-group-item card-cat5"><span class="cat-5"></span><span id="cardFourValFive"
																																class="float-right card-val5"></span>
									</li>
								</ul>
								</div>
						</div>
					</div>
					<div id="cardFive" class="card col-3">
						<div id="playerFiveCard" class="card-header"><span class="player-name"></span><span id="DeckFive"
																											class="badge badge-primary float-right cards-left"></span>
						</div>
						 <p id="cardBackFive" class="card-back">STAR CITIZEN</p>
							<div class="card-front">
						<img class="card-img-top" src="http://placekitten.com/300/300"  alt="Card image cap">
						<div class="card-body ">
				
							<h5 id="cardFiveTitle" class="card-title"></h5>
							<ul class="list-group list-group-flush">
								<li id="cardFiveCatOne" class="list-group-item card-cat1"><span class="cat-1"></span><span id="cardFiveValOne"
																														   class="float-right card-val1"></span>
								</li>
								<li id="cardFiveCatTwo" class="list-group-item card-cat2"><span class="cat-2"></span><span id="cardFiveValTwo"
																														   class="float-right card-val2"></span>
								</li>
								<li id="cardFiveCatThree" class="list-group-item card-cat3"><span class="cat-3"></span><span id="cardFiveValThree"
																															 class="float-right card-val3"></span>
								</li>
								<li id="cardFiveCatFour" class="list-group-item card-cat4"><span class="cat-4"></span><span id="cardFiveValFour"
																															class="float-right card-val4"></span>
								</li>
								<li id="cardFiveCatFive" class="list-group-item card-cat5"><span class="cat-5"></span><span id="cardFiveValFive"
																															class="float-right card-val5"></span>
								</li>
							</ul>
						</div>
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			// the Game class
			class Game {
				// set the gameID
				setGameID(gameID){
					this.gameID = gameID;
				}
				
				// create url with params
				makeUrl(endpoint){
					return "http://localhost:7777/toptrumps/" + endpoint + "?gameID=" + this.gameID;
				}
				
				// create the standard url
				static makeUrlWithoutParam(endpoint){
					return "http://localhost:7777/toptrumps/" + endpoint;
				}
			}
			
			const game = new Game(); // our game instance

			// Method that is called on page load
			function initalize() {

				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				$(document).ready(function() {
					$(".card-deck").toggle(); // hide the deck
					
					// show number of player select menu and start game depending on users choice
					$("#selectPlayersMenu").slideDown(()=>{
						$(".select-player-btns .one").click(()=>{
							initGame(1);
							$("#selectPlayersMenu").slideUp("fast", "swing");
						});
						$(".select-player-btns .two").click(()=>{
							initGame(2);
							$("#selectPlayersMenu").slideUp("fast", "swing");
						});
						$(".select-player-btns .three").click(()=>{
							initGame(3);
							$("#selectPlayersMenu").slideUp("fast", "swing");
						});
						$(".select-player-btns .four").click(()=>{
							initGame(4);
							$("#selectPlayersMenu").slideUp("fast", "swing");
						});
					});
				});
			}
			
			// initialize game
			function initGame(noOfAiPlayers){
				$(document).ready(function() {
					// start the game and show the elements required to play game
					startGame(noOfAiPlayers).then((startGameResponseInt)=>{
						// if game started successfully, make the game object
						game.setGameID(startGameResponseInt);
						
						// warn user before exiting
						window.onbeforeunload = function() {
						  return 'Are you sure you want to leave? All game progress will be lost!';
						}
						
						$("#common-pile").slideDown("fast", "swing", ()=>{
							$(".alert").slideDown("fast", "swing");
						});
						$("#actionButtonDiv").fadeIn("fast", "swing", ()=>{
							playRound();
						});
					});
				});
			}

			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
			
			// This starts the game from the API
			function startGame(numberOfAIPlayersFromUser) {
				return new Promise((resolve)=>{
				
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', Game.makeUrlWithoutParam("game/startGame") + "?numberOfAIPlayers=" + numberOfAIPlayersFromUser); // Request type and URL
		
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						const responseText = xhr.response; // the text of the response
						const responseInt = parseInt(responseText);
						// 1 means game didn't start
						if(isNaN(responseInt)){
							alert("Error. Game refused to start. Please try again.");
						} else {
							// any other integer is the game ID
							resolve(responseInt);
						}
					};
		
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();
				});
			}
			
			// function that plays a round
			function playRound(){
				return new Promise(async (resolve)=>{
				
					// check it is humans turn to select category
					let humanSelectCategory = await shouldHumanSelectCategory();

					let categorySelected; // represents category selected
				
					// get the current round number and update status message
					let roundNumber = await getRoundNumber();
				
					// update card and player details for the 1st round
					if(roundNumber===1){
						await updateCardsAndPlayers();
					} else{
						$(".card").removeClass("winning-card").removeClass("flip-card").addClass("flip-card");
					}
					
					let categoryList = await getCategories();
					let activePlayer = await getRoundActivePlayer(); // get active player
					
					// get player choice
					if(humanSelectCategory === "true"){
						categorySelected = await selectCategoryForHuman(categoryList);
					} else {
						categorySelected = await selectCategoryForAI(activePlayer);
						if(categorySelected==="human"){
							categorySelected = await selectCategoryForHuman(categoryList);
						}
					}
				
					// highlight active category
					$(document).ready(function() {
						$("span:contains(" + categorySelected + ")").parent().addClass("cat-selected");
					});
				
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', game.makeUrl("game/playRound") + "&category=" + categorySelected); // Request type and URL
		
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						
						var responseText = xhr.response; // the text of the response
						
						// if this, game has ended
						if(responseText !== "running"){
							$(document).ready(function() {
								// remove action button
								$("#actionButton").hide();
								
								// show stats table
								$(".table").show(()=>{
									setPlayerScores();
								});
								
								changeStatusMessage("Game won by " + responseText + ".");
								
								$(".card").removeClass("winning-card").removeClass("flip-card").addClass("flip-card");
								
								updateCardsAndPlayers().then(()=>{
									$(".card").map((i, card)=>{
										$(card).find(".card-back").hide();
										$(card).find(".card-front").show();
									});
								})
								getNumberOfCardsInCommonPile();
								
								window.onbeforeunload = null;
								
								resolve();
							});
						} else{
							$(document).ready(function() {
								// all custom jQuery will go here
								$("#actionButton").html("<h2 class='font-weight-bold'>ROUND " + roundNumber + "...</h2>");
								
								var prog = 0;
								setTimeout(()=>{
									$("#actionButtonReal").html("SKIP").show("fast", "swing").click(async ()=>{
										$("#actionButtonReal").off("click");
										let max = setTimeout(()=>{
											()=>{}
										}, 1000);
									
										while (max--) {
											clearTimeout(max);
										}
									
										if (prog<=3){
											if (prog<=2){
												if(prog===0){
													getRoundWinner();
													
													$(".card").removeClass("winning-card").removeClass("flip-card").addClass("flip-card");
													$(".card").map((i, card)=>{
														if ($(card).find(".player-name").html() !== "You" && $(card).find(".player-name").html() !== ""){
															$(card).find(".card-back").hide();
															$(card).find(".card-front").show();
														}
													});
												}
												$("#actionButtonReal").hide("fast", "swing");
											}
											await updateCardsAndPlayers();
											await getNumberOfCardsInCommonPile();
											$("span:contains(" + categorySelected + ")").parent().removeClass("cat-selected");
											$(".card").removeClass("winning-card");
											$(".active-player-card").removeClass("active-player-card");
										
											playRound();
										}
									
									});
									
									var nextRound = (async ()=>{
										getRoundWinner();
										
										$(".card").removeClass("winning-card").removeClass("flip-card").addClass("flip-card");
										$(".card").map((i, card)=>{
											if ($(card).find(".player-name").html() !== "You" && $(card).find(".player-name").html() !== ""){
												$(card).find(".card-back").hide();
												$(card).find(".card-front").show();
											}
										});
								
										$("#actionButton").html("<h2 class='font-weight-bold'>3</h2>");
										
										prog=1;
										
										setTimeout(()=>{
											$("#actionButton").html("<h2 class='font-weight-bold'>2</h2>");
											prog=2;
											setTimeout(()=>{
												$("#actionButtonReal").off("click");
												$("#actionButtonReal").hide("fast", "swing");
												$("#actionButton").html("<h2 class='font-weight-bold'>1</h2>");
												prog=3;
												setTimeout(async ()=>{
													await updateCardsAndPlayers();
													await getNumberOfCardsInCommonPile();
													$("span:contains(" + categorySelected + ")").parent().removeClass("cat-selected");
													$(".active-player-card").removeClass("active-player-card");
													playRound();
										
												}, 3000);
											}, 3000);
										}, 3000);
									})();
								}, 1500);
							});	
						}
					};
		
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				});
			}
			
			function shouldHumanSelectCategory(){
				return new Promise(resolve=>{
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', game.makeUrl("game/shouldHumanSelectCategory")); // Request type and URL
		
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
			
			function getRoundNumber(){
				return new Promise((resolve)=>{
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', game.makeUrl("game/roundNumber")); // Request type and URL
		
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						const responseText = xhr.response; // the text of the response
						const roundNumber = parseInt(responseText);
						
						changeStatusMessage("Round Number " + roundNumber + ".");
						
						resolve(roundNumber);
					};
		
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();
				});
			}
			
			// change the status message
			function changeStatusMessage(message){
				$(document).ready(function() {
					$("#status-message").html(message);
				});
			}
			
			// append the status message
			function appendStatusMessage(message){
				$(document).ready(function() {
					$("#status-message").append(message);
				});
			}
			
			// get the players still in the game
			function getRoundActivePlayers(){
				return new Promise((resolve)=>{
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', game.makeUrl("game/activePlayers")); // Request type and URL
					
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						const responseText = xhr.response; // the text of the response//
						const players = JSON.parse(responseText);
						resolve(players);
					};
	
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				});
			}
			
			// update player details
			function updatePlayers(players){
				$(document).ready(function() {
					$(".card").map(async(i, card)=>{
						if(i<=players.length-1){
							$(card).find(".player-name").html(players[i].name);
							$(card).find(".cards-left").html(await getNoOfCardsLeft(players[i].name));
						}
					});
					$(".card").map((i, card)=>{
						if ($(card).find(".player-name").html() !== "You"){
							$(card).find(".card-back").show();
							$(card).find(".card-front").hide();
						}
					});
				});
			}
			
			function updateCards(cards){
				$(document).ready(function() {
					$(".card").map((i, card)=>{
						try{
							$(card).find(".card-title").html(cards[i].description);
							
							$(card).find(".card-img-top").attr("src", "http://dcs.gla.ac.uk/~richardm/TopTrumps/" + cards[i].description + ".jpg");
							
							$(card).find(".card-val1").html(cards[i].category1);
							$(card).find(".card-val2").html(cards[i].category2);
							$(card).find(".card-val3").html(cards[i].category3);
							$(card).find(".card-val4").html(cards[i].category4);
							$(card).find(".card-val5").html(cards[i].category5);
						} catch(e){
							if (e instanceof TypeError){
								$(card).fadeOut("slow", "linear");
							}
						}
					});
				});
				$(".card-deck").slideDown("fast", "swing");
				
			}
			
			function updateCardsAndPlayers(){
				return new Promise(async (resolve)=>{
					const players = await getRoundActivePlayers();
					updatePlayers(players);
				
					const cards = await getRoundActiveCards();
					updateCards(cards);
					
					resolve();
				});
			}
			
			function getRoundActiveCards(){
				return new Promise((resolve)=>{
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', game.makeUrl("game/roundCards")); // Request type and URL
				
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						const responseText = xhr.response; // the text of the response// 
						const cards = JSON.parse(responseText);
						resolve(cards);
					};
	
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				});
			}
			
			function selectCategoryForHuman(categoryList){
				return new Promise(async (resolve)=>{
					
					$(document).ready(function() {
						$("#actionButtonDiv").slideUp("fast", "swing", ()=>{
							$(".categories").slideDown("fast", "swing", ()=>{
								$("#selectCategoryMenu .btn").each((i, elem)=>{
									$(elem).html(categoryList[i]);
									$(elem).click(()=>{
										$("#selectCategoryMenu .btn").off("click");
										$(".categories").slideUp("fast", "swing", ()=>{
											$("#actionButtonDiv").slideDown("fast", "swing");
										});
										appendStatusMessage(" You selected " + categoryList[i] + ".");
										resolve(categoryList[i]);
									})
								});
							});
						});
					});
				});
			}
			
			async function selectCategoryForAI(activePlayer){
				return new Promise((resolve)=>{
				
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', game.makeUrl("game/getAIPlayerCategory")); // Request type and URL
					
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						const responseText = xhr.response; // the text of the response// 
						const categorySelected = responseText;
						$(document).ready(()=>{
							if(categorySelected!=="human"){
								appendStatusMessage(" " + activePlayer + " selected " + categorySelected + ".");
							}
						})
						resolve(categorySelected);
					};
					
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				
				});
				
			}
			
			function getCategories(){
				return new Promise(resolve=>{
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', Game.makeUrlWithoutParam("game/categoryMenu")); // Request type and URL
					
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						const responseText = xhr.response; // the text of the response
						const categories = JSON.parse(responseText);
						
						categories.map((cat, i)=>{
							$(".cat-" + (i+1)).html(cat);
						})
						
						resolve(categories);
					};
		
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				});
			}
			
			function getRoundActivePlayer(){
				return new Promise((resolve)=>{
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', game.makeUrl("game/getRoundActivePlayer")); // Request type and URL
				
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						const responseText = xhr.response; // the text of the response// 
						$(".card .player-name:contains(" + responseText + ")").closest(".card").addClass("active-player-card");
						resolve(responseText);
					};
	
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				});
			}
			
			function setPlayerScores(){
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', game.makeUrl("game/allPlayersScores")); // Request type and URL

				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
					const responseText = xhr.response; // the text of the response// 
					const players = JSON.parse(responseText);
					
					$(document).ready(function() {
						players.map((player, i)=>{
							var tableData = "<tr><th>" + players[i].name + "</th><th>" + players[i].score + "</th></tr>\n"
							$(".table").append(tableData);
						});
					});
				};

				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();	
			}
			
			function getRoundWinner(){
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', game.makeUrl("game/getRoundWinner")); // Request type and URL
				
	
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives
				xhr.onload = function(e) {
					const responseText = xhr.response; // the text of the response// 
					if(responseText === "draw"){
						appendStatusMessage(" Round was a draw.");
						$(".card").removeClass("winning-card").removeClass("flip-card", ()=>{
							$("#common-pile").addClass("winning-card");
						});
					} else {
						$(".card").removeClass("winning-card").removeClass("flip-card", ()=>{
							$(".card .player-name:contains(" + responseText + ")").closest(".card").addClass("winning-card");
						});
						appendStatusMessage(" " + responseText + " won this round.");
					}
					
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();
				
			}
			
			function getNumberOfCardsInCommonPile(){
				return new Promise((resolve)=>{
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', game.makeUrl("game/numberOfCardsInCommonPile")); // Request type and URL
					
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						const responseText = xhr.response; // the text of the response//
					
						$(document).ready(function() {
							$("#common-pile").html("Common Pile: " + responseText);
							resolve();
						});
					};

					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				});
			}
			
			function getNoOfCardsLeft(playersName){
				return new Promise((resolve)=>{
					// First create a CORS request, this is the message we are going to send (a get request in this case)
					var xhr = createCORSRequest('GET', game.makeUrl("game/cardsLeft") + "&playerName="+playersName); // Request type and URL
				
	
					// Message is not sent yet, but we can check that the browser supports CORS
					if (!xhr) {
						alert("CORS not supported");
					}

					// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
					// to do when the response arrives
					xhr.onload = function(e) {
						const responseText = xhr.response; // the text of the response//
						resolve(responseText);
					};
	
					// We have done everything we need to prepare the CORS request, so send it
					xhr.send();	
				});
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
					responseText = JSON.parse(responseText);
				};
		
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// ------------------------------------------------------------ //
			// ------------------------------------------------------------ //
			// ------------------------------------------------------------ //
			
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
	</body>
</html>
