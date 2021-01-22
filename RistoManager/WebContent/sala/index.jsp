<%Boolean flag=(Boolean) request.getAttribute("flag");

String stylePath=(flag==null || !flag) ? "../public/style/style.css" : "./public/style/style.css";
String scriptPath=(flag==null || !flag) ? "../public/script/script.js" : "./public/script/script.js";
String accediPath=(flag==null || !flag) ? "../accedi.jsp" : "./accedi.jsp";
String generaPath=(flag==null || !flag) ? "../sala/genera.jsp" : "./sala/genera.jsp";
String logoutPath=(flag==null || !flag) ? "../logout" : "./logout";
String cercaPath=(flag==null || !flag) ? "../sala/cercaCodice.jsp" : "./sala/cercaCodice.jsp";%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/9b886a1068.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=stylePath%>">
<meta http-equiv="refresh" content="60">
<title>Sala - RistoManager</title>
</head>
<body>
	<div class="main_container">
		<div class="header cucina">
			<h1>Sala - RistoManager</h1>
		</div>
		<div class="subheader">
	<a href="<%=generaPath%>">GeneraCodice</a> 
	<a href="<%=cercaPath%>">Cerca Cliente</a> 
	<a href="<%=accediPath%>">Ordina per Cliente</a>
	<a href="<%=logoutPath%>">Logout</a>
</div>

	</div>
	
	
	<script src="<%=scriptPath %>" ></script>
</body>
</html>