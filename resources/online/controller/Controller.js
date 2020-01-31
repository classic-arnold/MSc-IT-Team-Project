

// This class control the action of the page.

// @author Team:Try-Catch Jialiang Song 2410536s


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

function shuffleCards(){
	
}

function createDeck(){
	
}

function 

