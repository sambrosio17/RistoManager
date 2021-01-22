<%@page import="it.RistoManager.Model.ComandaItemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.RistoManager.Model.ComandaBean"%>
<%@page import="java.util.List"%>
<%
	ComandaBean comanda=(ComandaBean) request.getAttribute("comanda");
	if(comanda==null){
		response.sendRedirect("./cucina");
	}
%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./public/style/style.css">
    <title>Comanda <%=comanda.getCliente().getCodiceTavolo() %> - RistoManager</title>
</head>
<body>

    <div class="main_container">
        <div class="header cucina">
            <h1>Cucina - RistoManager</h1>
        </div>
        <div class="subheader">
        	<a href="./cucina?action=visualizza">TUTTE LE COMANDE</a>
            <a href="./logout">LOGOUT</a>
        </div>

        <div class="dettaglio_comanda">
            <div class="single_comanda">
                <h2>Tavolo: <%=comanda.getCliente().getCodiceTavolo() %></h2>
                <ul>
                    <li><span>Qnt:</span> <span>Prodotto</span></li>
                    <%for(ComandaItemBean item : comanda.getProdotti()){ %>
                    <li onclick="done(this)"><span><%=item.getQuantita() %></span> <span><%=item.getProdotto().getNomeprodotto() %></span></li>
                    <%} %>
                </ul>
                <div class="buttons">
                    <a href="./cucina?action=conferma&idComanda=<%=comanda.getId()%>" class="termina">termina</a>
                </div>
            </div>

        </div>

    </div>
    

    <script src="./public/script/script.js"></script>
</body>
</html>