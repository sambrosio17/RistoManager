<%@page import="it.RistoManager.Model.Enity.ComandaBean"%>
<%@page import="it.RistoManager.Model.Enity.RiepilogoBean"%>
<%@page import="it.RistoManager.Model.Enity.ComandaItemBean"%>
<%@page import="java.util.List"%>
<%@page import="it.RistoManager.Model.Enity.ClienteBean"%>
<%
	ClienteBean c = (ClienteBean) request.getSession().getAttribute("cliente");
	if (c == null) {
		response.sendRedirect("./404.jsp");
		return;
	}
	RiepilogoBean riepilogo = (RiepilogoBean) request.getSession().getAttribute("riepilogo");
	float prezzoTotale = 0;
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/9b886a1068.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="./public/style/style.css">
<title>Riepilogo - RistoManager</title>
</head>
<body>
	<div class="main_container">
		<jsp:include page="./fragments/headerCliente.jsp"></jsp:include>

		<div class="container ">
			<div class="category_container">
				<h1>IL TUO RIEPILOGO</h1>
				<h3>
					Codice Tavolo:
					<%=c.getCodiceTavolo()%></h3>
			</div>

			<div class="inner_comanda">
				<%
					if (riepilogo != null) {
						List<ComandaBean> comande = riepilogo.getComande();
						for (ComandaBean comanda : comande) {
							List<ComandaItemBean> prodotti = comanda.getProdotti();
							for (ComandaItemBean p : prodotti) {
								prezzoTotale += p.prezzoTotale();
				%>
				<div class="single_product sp_comanda">
					<div class="sx">
						<img src="<%=p.getProdotto().getImmagine()%>" />
					</div>
					<div class="dx">
						<div class="title_in_comanda">
							<h3 onclick="showDetails(<%=p.getProdotto().getId()%>)"><%=p.getProdotto().getNomeprodotto()%></h3>
						</div>
						<p><%=p.getProdotto().getDescrizione()%></p>
						<div class="mini_form">
							<h3>
								<%=p.getQuantita()%>
								pz. 
								<%=p.getQuantita() * p.getProdotto().getPrezzo()%>&euro;
							</h3>
							<div>
								<a href="./feedback?id=<%=p.getProdotto().getId()%>&action=up"><i class="far fa-thumbs-up fa-3x" ></i></a>
								<a href="./feedback?id=<%=p.getProdotto().getId()%>&action=down"><i class="far fa-thumbs-down fa-3x"></i></a>
							</div>
						</div>
					</div>
				</div>
				<%
					}
						}
					}
				%>
			</div>
		</div>
	</div>


	<div class="totale_container">
		<span align="center">Totale: <%=prezzoTotale%>&euro; 
	</div>




	<jsp:include page="./fragments/menuCliente.jsp"></jsp:include>
	<jsp:include page="./fragments/prodDetail.jsp"></jsp:include>


	<script src="./public/script/script.js"></script>
</body>
</html>