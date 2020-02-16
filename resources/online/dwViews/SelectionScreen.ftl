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
			.navbar {
				border-bottom: 5px solid;
				border-image-source: linear-gradient(to right, #659999, #f4791f);
				border-image-slice: 1;
			}

			/*Card Styles*/

			#main-screen {
				background: #000000;
				background-image: url(/assets/galaxy.jpg);
				background-repeat: no repeat;
				background-size: cover;
				background-position: center;
			}

			h1 {
				font-family: 'Orbitron', sans-serif;
				font-weight: bold;
				font-size: 70px;
				color: white;
			}

			.glow {
				color: #fff;
				-webkit-animation: glow 1s ease-in-out infinite alternate;
				-moz-animation: glow 1s ease-in-out infinite alternate;
				animation: glow 1s ease-in-out infinite alternate;
			}

			@-webkit-keyframes glow {
				from {
					text-shadow: 0 0 1px #fff, 0 0 1px #fff, 0 0 1px #ffff, 0 0 1px #ffff, 0 0 1px #ffff, 0 0 1px #ffff, 0 0 1px #ffff;
				}
				to {
					text-shadow: 0 0 2px #fff, 0 0 2px #ffff, 0 0 2px #ffff, 0 0 2px #ffff, 0 0 2px #ffff, 0 0 2px #ffff, 0 0 2px #ffff;
				}
			}

			.btn-lg {
				background-color: rgba(9, 36, 55, 0.6);
				border-radius: 0px;
				padding: 2rem 4rem;
				margin-right: 5rem;
				color: #5DBCD2;
				font-size: 20px;
				box-shadow: 0px 0px 5px #5DBCD2;
				transition-duration: 0.4s;
			}

			.btn-lg:hover {
				box-shadow: 0px 0px 15px #FFA500;
			}
		</style>
	</head>

	<body>
		<div id="main-screen" class="container-fluid h-100">
			<div class="row h-50 d-flex align-items-center justify-content-center">
				<div class="col-sm-12">
					<div id="title-div" class="row justify-content-center">
						<h1 class="glow">STAR CITIZEN TOP TRUMPS</h1>
					</div>
				</div>
			</div>
			<div class="row h-50 d-flex align-items-center justify-content-center">
				<a href="/toptrumps/game">
					<button type="button" class="btn btn-lg">New Game</button>
				</a>
				<a href="/toptrumps/stats">
					<button type="button" class="btn  btn-lg">View Statistics</button>
				</a>
			</div>
		</div>
	</body>
</html>