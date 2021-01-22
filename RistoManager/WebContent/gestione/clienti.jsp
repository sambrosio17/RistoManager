<%@page import="it.RistoManager.Model.ClienteBean"%>
<%@page import="java.util.List"%>
<%List<ClienteBean> clienti= (List<ClienteBean>) request.getAttribute("listaClienti");
Boolean flag=(Boolean) request.getAttribute("flag");

String stylePath=(flag==null || !flag) ? "../public/style/style.css" : "./public/style/style.css";
String scriptPath=(flag==null || !flag) ? "../public/script/script.js" : "./public/script/script.js";
String clientiPath=(flag==null || !flag) ? "../gestione/clienti.jsp" : "./gestione/clienti.jsp";
String nuovoPath=(flag==null || !flag) ? "../gestione/nuovoProdotto.jsp" : "./gestione/nuovoProdotto.jsp";
String logoutPath=(flag==null || !flag) ? "../logout" : "./logout";
String gestisciPath=(flag==null || !flag) ? "../showProdotti" : "./showProdotti";
String personalePath=(flag==null || !flag) ? "../showPersonale" : "./showPersonale";

%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="<%=stylePath%>">
    <title>Visualizza Clienti - RistoManager</title>
</head>
<body>
    <div class="main_container">
        <div class="header cucina">
            <h1>Visualizza Ordini - RistoManager</h1>
        </div>
       <div class="subheader">
	<a href="<%=nuovoPath%>">Aggiungi Prodotto</a> 
	<a href="<%=gestisciPath%>">Gestisci Prodotti</a> 
	<a href="<%=clientiPath%>">Visualizza clienti</a>
	<a href="<%=personalePath%>">Gestisci Personale</a>
	<a href="<%=logoutPath%>">Logout</a>
</div>

        <form action="../showClienti" method="POST" class="dateForm">
            <div class="single_date_input">
                <label for="inizio">Inizio:</label>
                <input type="date" name="inizio">
            </div>
            <div class="single_date_input">
                <label for="inizio">Inizio:</label>
                <input type="date" name="fine">

                <button type="submit">Visualizza</button>
            </div>
        </form>

        <table class="products_table">
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Cellulare</th>
            <th>N.Documento</th>
            <th>N.Posti</th>
            <th>Data</th>
            <th>Ora</th>
		<%if(clienti!=null){
			for(ClienteBean c : clienti){%>
            <tbody>
                <tr>
                    <td><%=c.getNome() %></td>
                    <td><%=c.getCognome() %></td>
                    <td><%=c.getEmail() %></td>
                    <td><%=c.getCellulare() %></td>
                    <td><%=c.getNumeroDocumento() %></td>
                    <td><%=c.getNumeroPosti() %></td>
                    <td><%=c.getData() %></td>
                    <td><%=c.getOra() %></td>
                </tr>
                <%}
			}%>

            </tbody>
        </table>
     </div>

</body>
</html>