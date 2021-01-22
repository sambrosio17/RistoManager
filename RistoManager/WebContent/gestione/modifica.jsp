<%@page import="it.RistoManager.Model.ProdottoBean"%>
<%ProdottoBean p= (ProdottoBean) request.getAttribute("prodotto");
	if(p==null){
	response.sendRedirect("./");}
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
                <div class="single_input">
                  <label for="foto">Immagine:</label>
                  <input type="text" name="immagine" placeholder="<%=p.getImmagine()%>">
              </div>
                <div class="single_input">
                    <label for="ingredienti">Ingredienti (separati da un virgola):</label>
                    <input type="text" name="foto" placeholder="<%=p.getIngredienti().toString().substring(1, p.getIngredienti().toString().length()-1) %>">
                </div>
                <div class="single_input">
                    <label for="descrizione">Descrizione:</label>
                    <textarea name="descrizione" rows="4" cols="50" placeholder="<%=p.getDescrizione()%>"></textarea>
                </div>
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
                <div class="single_input">
                    <label for="prezzo">Prezzo</label>
                    <input type="text" name="prezzo" placeholder="<%=p.getPrezzo()%>">
                </div>

                <button type="submit">Modifica</button>
        </form>
    </div>
    
</body>
</html>