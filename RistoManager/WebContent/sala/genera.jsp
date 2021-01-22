<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../public/style/style.css">
    <title>Genera Codice - RistoManager</title>
</head>
<body>
    <div class="main_container">
        <div class="header cucina">
            <h1>GeneraCodice - RistoManager</h1>
        </div>
        <div class="subheader">
	<a href="../sala/genera.jsp">GeneraCodice</a> 
	<a href="../sala/cercaCodice.jsp">Cerca Cliente</a> 
	<a href="../accedi.jsp">Ordina per Cliente</a>
	<a href="./logout">Logout</a>
</div>
        <div class="codice_container">
            <h3>Codice:</h3>
            <h1 id="codice">Non generato</h1>
            <a onclick="genera()">GENERA</a>
        </div>
    </div>
    <script src="../public/script/script.js"></script>
</body>
</html>