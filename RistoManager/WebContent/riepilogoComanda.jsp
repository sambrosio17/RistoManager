<%@page import="it.RistoManager.Model.ComandaBean"%>
<%@page import="it.RistoManager.Model.ComandaItemBean"%>
<%@page import="java.util.List"%>
<%@page import="it.RistoManager.Model.ClienteBean"%>
<%
	Boolean flag=(Boolean) request.getAttribute("flag");
	ClienteBean c= (ClienteBean) request.getSession().getAttribute("cliente");
	ComandaBean comanda= (ComandaBean) request.getAttribute("riepilogo");
	if(c==null || flag==null || !flag){
		response.sendRedirect("./404.jsp");
		return;
	}
	
	System.out.println("in riepilogo "+ comanda);
	%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./public/style/style.css">
    <title>Riepilogo Comanda - RistoManager</title>
</head>
<body>
    
    <div class="main_container">
       <jsp:include page="./fragments/headerCliente.jsp"></jsp:include>

        <div class="prenotazione_container">
            <div class="upper_section">
                <h1>Il tuo ordine è in preparazione! </h1>
            <p><br>Ecco un riepilogo:</p>
            </div>
        </div>

        <div class="dettaglio_comanda" style="width: 80%;">
            <div class="single_comanda" style="width: 100%;">
                <h2>Tavolo: <%=c.getCodiceTavolo() %></h2>
                <%if(comanda!=null){
                	%>
                <ul>
                <li><span>Qnt:</span> <span>Prodotto</span></li>
                	<%for(ComandaItemBean p : comanda.getProdotti()){ %>
                    <li><span><%=p.getQuantita() %></span> <span><%=p.getProdotto().getNomeprodotto() %></span></li>
                    <%} %>
                </ul>
                <%} %>
                <%if(comanda!=null) {
                %>
                <div class="buttons">
                    <a class="termina">Totale:<%=comanda.getTotale() %>&euro;</a>
                </div>
                <%} %>
            </div>
        </div>
    </div>
    
    <jsp:include page="./fragments/menuCliente.jsp"></jsp:include>
	<jsp:include page="./fragments/prodDetail.jsp"></jsp:include>

	<script src="./public/script/script.js"></script>

</body>
</html>