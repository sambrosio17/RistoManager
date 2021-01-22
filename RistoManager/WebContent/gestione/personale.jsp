<%@page import="it.RistoManager.Model.AccountStaffBean"%>

<%@page import="java.util.List"%>
<%
	List<AccountStaffBean> list=(List<AccountStaffBean>) request.getAttribute("utenti");
	if(list==null){
		System.out.println("account null");
		response.sendRedirect("./showPersonale");
		return;
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./public/style/style.css">
    <title>Gestione- RistoManager</title>
</head>
<body>
    <div class="main_container">
        <div class="header cucina">
            <h1>Gestisci Personale - RistoManager</h1>
        </div>
        <div class="subheader">
            <a href="./gestione/nuovoProdotto.jsp">Aggiungi Prodotto</a>
            <a href="./showProdotti">Gestisci Prodotti</a>
            <a href="./gestione/clienti.jsp">Visualizza Clienti</a>
            <a href="../gestione/personale.jsp">Gestisci Personale</a>
            <a href="./logout">Logout</a>
        </div>

        <table class="products_table">
            <th>Nome</th>
            <th>Cognome</th>
            <th>Email</th>
            <th>Ruolo</th>
            <th>Azione</th>

            <tbody>
            <%if(list!=null){
            	for(AccountStaffBean a : list){
            
            	%>
                <tr>
                    <td><%=a.getNome() %></td>
                    <td><%=a.getCognome() %></td>
                    <td><%=a.getEmail() %></td>
                    <td><%=a.getRuolo().toString() %></td>
                    <td class="action">
                        <a href="./rimuovi?id=<%=a.getId()%>"class="fas fa-minus-circle"/>
                    </td>
                    <%}
            }%>
                </tr>

            </tbody>
        </table>
    </div>
</body>
</html>