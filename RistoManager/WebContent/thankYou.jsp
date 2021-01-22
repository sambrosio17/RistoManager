<%Boolean flag=(Boolean) request.getAttribute("flag");
	if(flag==null || !flag){
	response.sendRedirect("./404.jsp");
	return;
	}%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./public/style/style.css">
    <title>Accedi - Risto Manager</title>
</head>
<body>
    <div class="main_container">
        <div class="title_container">
            <h1 class="logo_title">RistoManager</h1>
            <p class="logo_subtitle">Ordina in sicurezza</p>
        </div>
        <div class="prenotazione_container">
            <div class="upper_section">
                <h1>Grazie per aver prenotato! </h1>
            <p><br>&Egrave; stata inviata una mail all'indirizzo fornito in fase di prenotazione.</br>Clicca sul link all'interno della mail per confermare la prenotazione</p>
            </div>
        </div>
    </div>
</body>
</html>