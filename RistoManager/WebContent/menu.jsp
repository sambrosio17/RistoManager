<%@page import="it.RistoManager.FIA.KMeansExecutor"%>
<%@page import="it.RistoManager.Model.Enity.ProdottoBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="it.RistoManager.Model.Enity.ClienteBean"%>
<%
	String category= (String) request.getAttribute("category");
	ClienteBean c= (ClienteBean) request.getSession().getAttribute("cliente");
	ArrayList<ProdottoBean> prodotti= (ArrayList<ProdottoBean>) request.getAttribute("prodotti");
	if(c==null){
		response.sendRedirect("./accedi.jsp");
		return;
	}
	if(prodotti==null || prodotti.isEmpty()){
		response.sendRedirect("./menu");
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
<%if(category==null){
	%>
<title>Menu - RistoManager</title>
<%}else {
	%>
<title><%=category.toUpperCase() %> - RistoManager</title>
<%} %>

</head>
<body>
	<div class="main_container">
		<jsp:include page="./fragments/headerCliente.jsp"></jsp:include>
		<div class="container">
			<div class="category_container"></div>

			<%for(ProdottoBean p : prodotti) {%>
			<div class="single_product">
				<div class="sx">
					<img src="<%=p.getImmagine() %>" />
				</div>
				<div class="dx">
					<h3 onclick="showDetails(<%=p.getId()%>)"><%=p.getNomeprodotto() %>
						
						<%
							int index = KMeansExecutor.getLineById(p.getId());
								if (index >= 0 && index < KMeansExecutor.getAssignments().length) {
						%>
						[<%=KMeansExecutor.getAssignments()[KMeansExecutor.getLineById(p.getId())]%>]
						<%
							}
						%>
					</h3>
					<p><%=p.getDescrizione() %></p>
					<div class="mini_form">
						<h3><%=p.getPrezzo() %>&euro;
						</h3>
						<form action="./addComanda" method="POST">
							<label for="quantita">Quantita: </label> <input type="number"
								name="quantita" min=1 placeholder=1> <input type="text"
								value="<%=p.getId() %>" hidden name="productId">
							<button type="submit">AGGIUNGI</button>
						</form>
					</div>
				</div>
			</div>
			<%} %>
		</div>
	</div>

	<jsp:include page="./fragments/menuCliente.jsp"></jsp:include>
	<jsp:include page="./fragments/prodDetail.jsp"></jsp:include>

	<script src="./public/script/script.js"></script>
</body>
</html>