<%@page import="it.RistoManager.Model.Enity.ProdottoBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.RistoManager.Model.Enity.ClienteBean"%>
<%@page import="it.RistoManager.FIA.KMeansExecutor"%>
<%
	String category = (String) request.getAttribute("category");
	ClienteBean c = (ClienteBean) request.getSession().getAttribute("cliente");
	ArrayList<ProdottoBean> consigli = (ArrayList<ProdottoBean>) request.getAttribute("consigli");
	if (c == null) {
		response.sendRedirect("./accedi.jsp");
		return;
	}
	if (consigli == null) {
		response.sendRedirect("./consigliami");
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

<title>Consigli - RistoManager</title>

</head>
<body>
	<div class="main_container">
		<jsp:include page="./fragments/headerCliente.jsp"></jsp:include>
		<div class="container">
			<div class="category_container"></div>

			<%
				for (ProdottoBean prodotto : consigli) {
			%>
			<div class="single_product">
				<div class="sx">
					<img src="<%=prodotto.getImmagine()%>" />
				</div>
				<div class="dx">
					<h3 onclick="showDetails(<%=prodotto.getId()%>)"><%=prodotto.getNomeprodotto()%>
						<%
							int index = KMeansExecutor.getLineById(prodotto.getId());
								if (index >= 0 && index < KMeansExecutor.getAssignments().length) {
						%>
						[<%=KMeansExecutor.getAssignments()[KMeansExecutor.getLineById(prodotto.getId())]%>]
						<%
							}
						%>
					</h3>
					<p><%=prodotto.getDescrizione()%></p>
					<div class="mini_form">
						<h3><%=prodotto.getPrezzo()%>&euro;
						</h3>
						<form action="./addComanda" method="POST">
							<label for="quantita">Quantita: </label> <input type="number"
								name="quantita" min=1 placeholder=1> <input type="text"
								value="<%=prodotto.getId()%>" hidden name="productId">
							<button type="submit">AGGIUNGI</button>
						</form>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
	</div>

	<jsp:include page="./fragments/menuCliente.jsp"></jsp:include>
	<jsp:include page="./fragments/prodDetail.jsp"></jsp:include>

	<script src="./public/script/script.js"></script>
</body>
</html>