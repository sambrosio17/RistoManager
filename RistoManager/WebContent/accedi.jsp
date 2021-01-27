<!DOCTYPE html>
<html lang="en">

<%
	String error = (String) session.getAttribute("error");
	String errorType = (String) session.getAttribute("errorType");
%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./public/style/style.css">
<title>Accedi - Risto Manager</title>
</head>
<body>
	<div class="main_container">
		<div class="title_container">
			<h1 class="logo_title">RistoManager</h1>
			<p class="logo_subtitle">Ordina in sicurezza</p>
		</div>
		<div class="prenotazione_container">
			<div class="upper_section">
				<h1>Accedi</h1>
				<p>
					<br>Inserisci il codice che ti &egrave; stato fornito.
				</p>
			</div>
			<div class="form_container">
				<form action="./dispatch" method="POST">

					<div class="single_field">
						<label for="codiceTavolo">Codice</label> <input
							name="codiceTavolo" type="text" required>
					</div>

					<span id="checkCodice" class="formError"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("codice")) {
 %> <%=error%> <%
 	}
 %>
					</span>
					<button type="submit">ACCEDI</button>

				</form>
			</div>
		</div>
	</div>
</body>
</html>