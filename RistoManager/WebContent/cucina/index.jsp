<%@page import="it.RistoManager.Model.ComandaItemBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.RistoManager.Model.ComandaBean"%>
<%@page import="java.util.List"%>
<%
	ArrayList<ComandaBean> comande=(ArrayList<ComandaBean>) request.getAttribute("comande");
	if(comande==null){
		response.sendRedirect("../cucina?action=visualizza");
	}
	
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/9b886a1068.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="./public/style/style.css">
<meta http-equiv="refresh" content="60">
<title>Cucina - RistoManager</title>
</head>
<body>
	<div class="main_container">
		<div class="header cucina">
			<h1>Cucina - RistoManager</h1>
		</div>
		
		<div class="subheader">
            <a href="./logout">LOGOUT</a>
        </div>

		<div class="comanda_container">
			<%if(comande!=null){ %>
			<div class="container_row">
				<%for(int i=0; i<comande.size(); i++){
        	ComandaBean c=comande.get(i);
    		int id=c.getId();
    		List<ComandaItemBean> items=c.getProdotti();
        	if(i%4==0){
        		
        	%>
			</div>
			<div class="container_row">
				<div class="single_comanda">
					<h2>
						Tavolo:
						<%=c.getCliente().getCodiceTavolo() %></h2>
					<ul>
						<li><span>Qnt:</span> <span>Prodotto</span></li>
						<%for(ComandaItemBean item : items){ %>
						<li onclick="done(this)"><span><%=item.getQuantita() %></span>
							<span><%=item.getProdotto().getNomeprodotto() %></span></li>
						<%} %>
					</ul>
					<div class="buttons">
						<a href="./cucina?action=accetta&idComanda=<%=c.getId()%>"
							class="dettaglio">dettaglio</a> <a
							href="./cucina?action=conferma&idComanda=<%=c.getId()%>"
							class="termina">termina</a>
					</div>
				</div>
				<%}else {
                    %>
				<div class="single_comanda">
					<h2>
						Tavolo:
						<%=c.getCliente().getCodiceTavolo() %></h2>
					<ul>
						<li><span>Qnt:</span> <span>Prodotto</span></li>
						<%for(ComandaItemBean item : items){ %>
						<li onclick="done(this)"><span><%=item.getQuantita() %></span>
							<span><%=item.getProdotto().getNomeprodotto() %></span></li>
						<%} %>
					</ul>
					<div class="buttons">
						<a href="./cucina?action=accetta&idComanda=<%=c.getId()%>"
							class="dettaglio">dettaglio</a> <a
							href="./cucina?action=conferma&idComanda=<%=c.getId()%>"
							class="termina">termina</a>
					</div>
				</div>
				<%}
                    }%>
			</div>
			<%} %>
		</div>

	</div>
	<script src="./public/script/script.js" ></script>
</body>
</html>