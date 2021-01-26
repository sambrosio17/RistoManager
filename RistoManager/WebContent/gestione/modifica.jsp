<%@page import="it.RistoManager.Model.ProdottoBean"%>
<%ProdottoBean p= (ProdottoBean) request.getAttribute("prodotto");
	if(p==null){
	response.sendRedirect("./");}
	
	String errorType = (String) session.getAttribute("errorType"); 
	String error = (String) session.getAttribute("error");

	%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./public/style/style.css">
    <title>Modifica Prodotto - RistoManager</title>
</head>
<body>

    <div class="main_container">
        <div class="header cucina">
            <h1>Gestione - RistoManager</h1>
        </div>
        <div class="subheader">
            <a href="./gestione/nuovoProdotto.jsp">Aggiungi Prodotto</a>
            <a href="./showProdotti">Gestisci Prodotti</a>
            <a href="./gestione/clienti.jsp">Visualizza Clienti</a>
            <a href="./gestione/personale.jsp">Gestisci Personale</a>
            <a href="./logout">Logout</a>
        </div>

        <form  class="product_form" action="./modificaProdotto" method="POST">
        	<input name="id" value="<%=p.getId() %>" hidden>
                <div class="single_input">
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome" placeholder="<%=p.getNomeprodotto()%>">
                </div>
                 <span class="formError" id="checkNomeProdottoModifica">
				<%
				session.removeAttribute("errorType");
				session.removeAttribute("error");
				if(errorType!=null && errorType.equals("nome")){;
				%>
					<%=error %>
			   <%} %>
				</span>
				
                <div class="single_input">
                  <label for="foto">Immagine:</label>
                  <input type="text" name="immagine" placeholder="<%=p.getImmagine()%>">
              </div>
               <span class="formError" id="checkFotoProdottoModifica">
			<%
				session.removeAttribute("errorType");
				session.removeAttribute("error");
				if(errorType!=null && errorType.equals("foto")){;
			%>
					<%=error %>
			   <%} %>
			  </span>
			  
                <div class="single_input">
                    <label for="ingredienti">Ingredienti (separati da un virgola):</label>
                    <input type="text" name="ingredienti" placeholder="<%=p.getIngredienti().toString().substring(1, p.getIngredienti().toString().length()-1) %>">
                </div>
                  <span class="formError" id="checkIngredientiProdottoModifica">
			<%
				session.removeAttribute("errorType");
				session.removeAttribute("error");
				if(errorType!=null && errorType.equals("ingredienti")){;
			%>
					<%=error %>
			   <%} %>
			  </span>
			  
                <div class="single_input">
                    <label for="descrizione">Descrizione:</label>
                    <textarea name="descrizione" rows="4" cols="50" placeholder="<%=p.getDescrizione()%>"></textarea>
                </div>
                 <span class="formError" id="checkDescrizioneProdottoModifica">
			<%
				session.removeAttribute("errorType");
				session.removeAttribute("error");
				if(errorType!=null && errorType.equals("descrizione")){;
			%>
					<%=error %>
			   <%} %>
				</span>
                
                <div class="single_input">
                    <label for="categoria">Categoria:</label>
                    <select name="categoria" >
                        <option value="primo">Primo</option>
                        <option value="secondo">Secondo</option>
                        <option value="contorno">Contorno</option>
                        <option value="dolce">Dolce</option>
                        <option value="bibita">Bibita</option>
                        <option value="pizza">Pizza</option>
                    </select>
                </div>
                 <span class="formError" id="checkCategoriaProdotto">
			<%
				session.removeAttribute("errorType");
				session.removeAttribute("error");
				if(errorType!=null && errorType.equals("categoria")){;
			%>
					<%=error %>
			   <%} %>
				</span>
				
                <div class="single_input">
                    <label for="prezzo">Prezzo</label>
                    <input type="text" name="prezzo" placeholder="<%=p.getPrezzo()%>">
                </div>
                   <span class="formError" id="checkPrezzoProdotto">
			<%
				session.removeAttribute("errorType");
				session.removeAttribute("error");
				if(errorType!=null && errorType.equals("prezzo")){;
			%>
					<%=error %>
			   <%} %>
				</span>

                <button type="submit">Modifica</button>
        </form>
    </div>
    
</body>
</html>