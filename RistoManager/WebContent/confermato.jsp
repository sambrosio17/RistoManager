<%@page import="it.RistoManager.Model.ClienteBean"%>
<% ClienteBean c=(ClienteBean) request.getAttribute("cliente");
	if(c==null){
		response.sendRedirect("./404.jsp");
		return;
	}
	%>


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
                <h1>Prenotazione Confermata! </h1>
                <br>
            <p>Gentile <%=c.getNome()%> <%=c.getCognome()%>, la sua prenotazione per la data <b><%=c.getData().toString() %></b>, ore <b><%=c.getOra().toString() %></b> &egrave; stata confermata!</p>
            </div>
        </div>
    </div>
</body>
</html>