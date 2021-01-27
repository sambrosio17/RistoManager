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
<title>Prenota Tavolo - Risto Manager</title>
</head>
<body>
	<div class="main_container">
		<div class="title_container">
			<h1 class="logo_title">RistoManager</h1>
			<p class="logo_subtitle">Ordina in sicurezza</p>
		</div>
		<div class="prenotazione_container">
			<div class="upper_section">
				<h1>Prenota Tavolo</h1>
				<p>
					<br>Completa il modulo per prenotare un tavolo.</br> Al termine
					della procedura le verrà inviata una mail di conferma.
				</p>
			</div>
			<div class="form_container">
				<form action="./prenotazione" method="POST">

					<div class="single_field">
						<label for="nome">Nome</label> <input name="nome" type="text"
							required>
					</div>
					<span class="formError" id="checkNome"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("nome")) {
 %> <%=error%> <%
 	}
 %>
					</span>
					<div class="single_field">
						<label for="cognome">Cognome</label> <input name="cognome"
							type="text" required>
					</div>
					<span class="formError" id="checkCognome"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("cognome")) {
 %> <%=error%> <%
 	}
 %>
					</span>
					<div class="single_field">
						<label for="documento">Documento</label> <input name="documento"
							type="text" required>
					</div>
					<span class="formError" id="checkDocumento"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("documento")) {
 %> <%=error%> <%
 	}
 %>
					</span>
					<div class="single_field">
						<label for="cellulare">Cellulare</label> <input name="cellulare"
							type="tel" required>
					</div>
					<span class="formError" id="checkCellulare"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("cellulare")) {
 %> <%=error%> <%
 	}
 %>
					</span>
					<div class="single_field">
						<label for="email">Email</label> <input name="email" type="email"
							required>
					</div>
					<span class="formError" id="checkEmail"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("email")) {
 %> <%=error%> <%
 	}
 %>
					</span>
					<div class="single_field">
						<label for="data">Data</label> <input name="data" type="date"
							min="2017-04-01" required>
					</div>
					<span class="formError" id="checkData"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("data")) {
 %> <%=error%> <%
 	}
 %>
					</span>
					<div class="single_field">
						<label for="ora">Ora</label> <input name="ora" type="time"
							min="12:00" max="23:00" step="600" required>
					</div>
					<span class="formError" id="checkOra"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("ora")) {
 %> <%=error%> <%
 	}
 %>
					</span>
					<div class="single_field">
						<label for="numPosti">Numero Posti</label> <input name="numPosti"
							type="number" min="1" required>
					</div>
					<span class="formError" id="checkNumeroPosti"> <%
 	session.removeAttribute("errorType");
 	session.removeAttribute("error");
 	if (errorType != null && errorType.equals("numeroPosti")) {
 %> <%=error%> <%
 	}
 %>
					</span>

					<div class="single_field_last">
						<label class="checkbox"><input type="checkbox" required />
							Acconsento al trattamento dei dati personali.</label>
					</div>

					<button type="submit">Prenota</button>

				</form>
			</div>
		</div>
	</div>
</body>
</html>