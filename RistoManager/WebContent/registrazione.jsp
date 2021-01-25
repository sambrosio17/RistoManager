<%@page import="it.RistoManager.Model.ClienteBean"%>
<% Boolean flag=(Boolean) request.getAttribute("exist");
	ClienteBean c=(ClienteBean) request.getSession().getAttribute("cliente");
	if(c==null){
		System.out.println("non puoi passare");
	}
	%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./public/style/style.css">
    <title>Registrazione - Risto Manager</title>
</head>
<body>
    <div class="main_container">
        <div class="title_container">
            <h1 class="logo_title">RistoManager</h1>
            <p class="logo_subtitle">Ordina in sicurezza</p>
        </div>
        <div class="prenotazione_container">
            <div class="upper_section">
                <h1>Registrazione Cliente</h1>
            <p><br>Inserire i dati personali per accedere al men�.</br> I suoi dati verranno utilizzati in caso di esposizione al contagio da COVID-19.</p>
            </div>
            <div class="form_container">
                <form action="./registrazione" method="POST">

                   <div class="single_field">
                       <label for="nome">Nome</label>
                       <input name="nome" type="text" required>
                    </div>
                    <div class="single_field">
                        <label for="cognome">Cognome</label>
                        <input name="cognome" type="text" required>
                     </div>
                    <div class="single_field">
                        <label for="documento">Documento</label>
                        <input name="documento" type="text" required>
                     </div>
                     <div class="single_field">
                        <label for="cellulare">Cellulare</label>
                        <input name="cellulare" type="tel" required>
                     </div>
                     <div class="single_field">
                        <label for="email">Email</label>
                        <input name="email" type="email" required>
                     </div>
                     <div class="single_field">
                        <label for="numPosti">Numero Posti</label>
                        <input name="numPosti" type="number" min="1" required>
                     </div>

                     <div class="single_field_last"><label class="checkbox"><input type="checkbox" required/> Acconsento al trattamento dei dati personali.</label></div>
                    
                    <button type="submit">REGISTRA</button>

                </form>
            </div>
        </div>
    </div>
</body>
</html>