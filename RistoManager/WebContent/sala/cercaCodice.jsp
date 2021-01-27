<%@page import="java.util.List"%>
<%@page import="it.RistoManager.Model.ClienteBean"%>
<%
	List<ClienteBean> c = (List<ClienteBean>) request.getAttribute("clienti");
	Boolean flag = (Boolean) request.getAttribute("flag");

	String stylePath = (flag == null || !flag) ? "../public/style/style.css" : "./public/style/style.css";
	String scriptPath = (flag == null || !flag) ? "../public/script/script.js" : "./public/script/script.js";
	String accediPath = (flag == null || !flag) ? "../accedi.jsp" : "./accedi.jsp";
	String generaPath = (flag == null || !flag) ? "../sala/genera.jsp" : "./sala/genera.jsp";
	String logoutPath = (flag == null || !flag) ? "../logout" : "./logout";
	String cercaPath = (flag == null || !flag) ? "../sala/cercaCodice.jsp" : "./sala/cercaCodice.jsp";
	String servletPath = (flag == null || !flag) ? "../cercaCliente" : "./cercaCliente";

	String error = (String) session.getAttribute("error");
	String errorType = (String) session.getAttribute("errorType");
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/9b886a1068.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="<%=stylePath%>">
<title>Cerca Codice - RistoManager</title>
</head>
<body>
	<div class="main_container">
		<div class="header cucina">
			<h1>GeneraCodice - RistoManager</h1>
		</div>
		<div class="subheader">
			<a href="<%=generaPath%>">GeneraCodice</a> <a href="<%=cercaPath%>">Cerca
				Cliente</a> <a href="<%=accediPath%>">Ordina per Cliente</a> <a
				href="<%=logoutPath%>">Logout</a>
		</div>
		<form action="<%=servletPath%>" method="POST" class="dateForm">
			<div class="single_date_input">
				<label for="email">Email: </label> <input type="email" name="email">
				<button type="submit">Visualizza</button>
			</div>

			<span class="formError" id="checkEmail"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("email")) {
 %> <%=error%> <%
 	}
 %>
			</span>
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
			<th>Codice</th>

			<tbody>
				<%
					if (c != null) {
						for (ClienteBean cliente : c) {
				%>
				<tr>
					<td><%=cliente.getNome()%></td>
					<td><%=cliente.getCognome()%></td>
					<td><%=cliente.getEmail()%></td>
					<td><%=cliente.getCellulare()%></td>
					<td><%=cliente.getNumeroDocumento()%></td>
					<td><%=cliente.getNumeroPosti()%></td>
					<td><%=cliente.getData()%></td>
					<td><%=cliente.getOra()%></td>
					<td><%=cliente.getCodiceTavolo()%></td>
				</tr>
				<%
					}
					}
				%>

			</tbody>
		</table>
	</div>
</body>
</html>