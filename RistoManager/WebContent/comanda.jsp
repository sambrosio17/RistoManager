<%@page import="it.RistoManager.Model.Enity.ComandaBean"%>
<%@page import="it.RistoManager.Model.Enity.ComandaItemBean"%>
<%@page import="java.util.List"%>
<%@page import="it.RistoManager.Model.Enity.ClienteBean"%>
<%@page import="it.RistoManager.FIA.KMeansExecutor"%>
<%
	ClienteBean c = (ClienteBean) request.getSession().getAttribute("cliente");
	ComandaBean comanda = (ComandaBean) request.getSession().getAttribute("comanda");
	if (c == null) {
		response.sendRedirect("./404.jsp");
		return;
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
<title>Comanda - RistoManager</title>
</head>
<body>
	<div class="main_container">
		<jsp:include page="./fragments/headerCliente.jsp"></jsp:include>

		<div class="container ">
			<div class="category_container">
				<h1>LA TUA COMANDA</h1>
				<h3>
					Codice Tavolo:
					<%=c.getCodiceTavolo()%></h3>
			</div>

			<div class="inner_comanda">
				<%
					if (comanda != null) {
						List<ComandaItemBean> prodotti = comanda.getProdotti();
						for (ComandaItemBean p : prodotti) {
				%>
				<div class="single_product sp_comanda">
					<div class="sx">
						<img src="<%=p.getProdotto().getImmagine()%>" />
					</div>
					<div class="dx">
						<div class="title_in_comanda">
							<h3 onclick="showDetails(<%=p.getProdotto().getId()%>)"><%=p.getProdotto().getNomeprodotto()%>
								<%
									int index = KMeansExecutor.getLineById(p.getProdotto().getId());
											if (index >= 0 && index < KMeansExecutor.getAssignments().length) {
								%>
								[<%=KMeansExecutor.getAssignments()[KMeansExecutor.getLineById(p.getProdotto().getId())]%>]
								<%
									}
								%>
							</h3>
							<i class="fas fa-trash"
								onclick="removeItem(<%=p.getProdotto().getId()%>)"></i>
						</div>
						<p><%=p.getProdotto().getDescrizione()%></p>
						<div class="mini_form">
							<h3><%=p.getQuantita() * p.getProdotto().getPrezzo()%>&euro;
							</h3>
							<form action="./updateComanda" method="POST">
								<label for="quantita">Quantita: </label> <input type="number"
									name="quantita" min=1 default=1 value=<%=p.getQuantita()%>>
								<input type="text" value="<%=p.getProdotto().getId()%>"
									hidden="true" name="productId">
								<button type="submit">MODIFICA</button>
							</form>
						</div>
					</div>
				</div>
				<%
					}
					}
				%>
			</div>
		</div>
	</div>

	<%
		if (comanda.getProdotti().size() != 0) {
	%>

	<div class="totale_container">
		<span>Totale: <%=comanda.getTotale()%>&euro;
		</span> <a href="./sendComanda">INVIA L'ORDINE</a>
	</div>
	<%
		}
	%>



	<jsp:include page="./fragments/menuCliente.jsp"></jsp:include>
	<jsp:include page="./fragments/prodDetail.jsp"></jsp:include>


	<script src="./public/script/script.js"></script>
</body>
</html>