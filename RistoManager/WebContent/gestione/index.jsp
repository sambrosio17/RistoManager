<%Boolean flag=(Boolean) request.getAttribute("flag");

String stylePath=(flag==null || !flag) ? "../public/style/style.css" : "./public/style/style.css";
String scriptPath=(flag==null || !flag) ? "../public/script/script.js" : "./public/script/script.js";
String clientiPath=(flag==null || !flag) ? "../gestione/clienti.jsp" : "./gestione/clienti.jsp";
String nuovoPath=(flag==null || !flag) ? "../gestione/nuovoProdotto.jsp" : "./gestione/nuovoProdotto.jsp";
String logoutPath=(flag==null || !flag) ? "../logout" : "./logout";
String gestisciPath=(flag==null || !flag) ? "../showProdotti" : "./showProdotti";
String personalePath=(flag==null || !flag) ? "../showPersonale" : "./showPersonale";%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/9b886a1068.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=stylePath%>">
<meta http-equiv="refresh" content="60">
<title>Gestione - RistoManager</title>
</head>
<body>
	<div class="main_container">
		<div class="header cucina">
			<h1>Gestione - RistoManager</h1>
		</div>
		<div class="subheader">
	<a href="<%=nuovoPath%>">Aggiungi Prodotto</a> 
	<a href="<%=gestisciPath%>">Gestisci Prodotti</a> 
	<a href="<%=clientiPath%>">Visualizza clienti</a>
	<a href="<%=personalePath%>">Gestisci Personale</a>
	<a href="<%=logoutPath%>">Logout</a>
</div>

	</div>
	
	
	<script src="<%=scriptPath %>" ></script>
</body>
</html>