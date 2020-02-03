<!--    Remember copy out this class into the NOTEBOOK folder   --> 


<!DOCTYPE html>
<html>
<head> 
<meta charset="utf-8"> 
<title>(runoob.com)</title> 
</head>

<body>
	


<!--  JavaScript could change the content -->

<h1> the JavaScript exercise one. </h1>
<p id="demo1">
</p>

<script>
function myFunction() 
{
	x=document.getElementById("demo1");  // find the element
	x.innerHTML="Hello JavaScript!";    // change the content
}
</script>
<button type="button" onclick="myFunction()">click here</button>
	
	
<!--   JavaScript could change the content colour -->

<h1> the JavaScript exercise two. </h1>

<p id = "demo2">
</p>

<script>
function colourChange(){ 
	x = document.getElementById("demo2"); 
	x.style.color = "#FF0000";
}
</script>

<button type = "button" onclick = "colourChange()">click here </button> 



</body>
</html>