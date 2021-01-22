<%@page import="it.RistoManager.Model.ProdottoBean"%>
<%@page import="java.util.List"%>
<%
	List<ProdottoBean> prodotti=(List<ProdottoBean>) request.getAttribute("prodotti");
	if(prodotti==null){
		response.sendRedirect("./showProdotti");
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
    <title>Gestisci Prodotti - RistoManager</title>
</head>
<body>
    <div class="main_container">
        <div class="header cucina">
            <h1>Gestisci Prodotti - RistoManager</h1>
        </div>
        <div class="subheader">
            <a href="./gestione/nuovoProdotto.jsp">Aggiungi Prodotto</a>
            <a href="./showProdotti">Gestisci Prodotti</a>
            <a href="./gestione/clienti.jsp">Visualizza Clienti</a>
            <a href="./gestione/personale.jsp">Gestisci Personale</a>
            <a href="./logout">Logout</a>
        </div>

        <table class="products_table">
            <th>Nome Prodotto</th>
            <th>Descrizione</th>
            <th>Ingredienti</th>
            <th>Categoria</th>
            <th>Prezzo</th>
            <th>Azione</th>

            <tbody>
            <%if(prodotti!=null){
            	for(ProdottoBean p : prodotti){
            	%>
                <tr>
                    <td><%=p.getNomeprodotto() %></td>
                    <td><%=p.getDescrizione() %></td>
                    <td><%=p.getIngredienti().toString().substring(1, p.getIngredienti().toString().length()-1) %></td>
                    <td><%=p.getCategoria() %></td>
                    <td><%=p.getPrezzo() %>&euro;</td>
                    <td class="action">
                        <a href="./rimuoviProdotto?id=<%=p.getId()%>"class="fas fa-minus-circle"/>
                         <a href="./prodotto?action=modifica&id=<%=p.getId()%>"class="fas fa-pen"/>
                    </td>
                </tr>
				<%}
            	}%>
            </tbody>
        </table>
    </div>
</body>
</html>