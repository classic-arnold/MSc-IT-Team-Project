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
		
		.navbar-brand{
			font-family: 'Orbitron', sans-serif;
			font-size:27px;
			font-weight:bold;
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
		
		/*Section 2 Styles*/
		/*Section 2 Styles*/
		.section2 {
	        background-color: #DCE6E5;

        }
        
        #new-game-col{
        
        padding-right:200px;
        
        }
        
		
		
		/*Card Styles*/
		
		.card {
			box-shadow: 0 0 5px black;
			border: 3px solid;
			border-image-source: linear-gradient(to right, #659999, #f4791f);
			border-image-slice: 1;
			background-color:#fafafa;
		
			
		}

		.card-title {
			text-align: center;
			font-family: 'Roboto Mono', monospace;
			font-weight: bold;
			font-size:40px;
		}
		
		.card-header{
		   background:linear-gradient(to right, #659999, #f4791f);
		   border:0px;
		}
		.card-header:first-child {
           border-radius: 0px;         
       }
         /*LINK STYLES*/
         a {
           color: black;
            }
          a:hover{
          color:black;
          text-decoration: none;
          
          
          }
		
		</style>
		
		

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->
    <nav class="navbar navbar-expand-lg navbar-dark"style="background-color:black">
   <a class="navbar-brand" href="#">STAR CITIZEN TOP TRUMPS</a>
   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
   <span class="navbar-toggler-icon"></span>
   </button>
   <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ml-auto">
         <li class="nav-item">
            <a class="nav-link" href="/toptrumps/game">Game <span class="sr-only">(current)</span></a>
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
   
</div>
<div class="col-sm-9 section2  d-flex justify-content-center">
<div class="row h-100 d-flex align-items-center">
<div id="new-game-col" class="col-sm-6">
<a href="/toptrumps/game"><div class="card" style="width: 18rem;">
 <div class="card-header"></div>
  <div class="card-body">
    <h5 class="card-title">New <br> Game</h5>
  </div>
</div>
</a>

</div>
<div class="col-sm-6">
<a href="/toptrumps/stats"><div class="card" style="width: 18rem;">
 <div class="card-header"></div>
  <div class="card-body">
    <h5 class="card-title">View Statistics</h5>
  </div>
</div>
</a>
</div>



</div>
      
         
      
         
      </div>
   </div>
</div>

		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				//helloJSONList();
				//helloWord("Student");
				
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
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
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
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
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

		</script>
		
		</body>
</html>