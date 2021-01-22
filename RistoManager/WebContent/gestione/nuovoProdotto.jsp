<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../public/style/style.css">
    <title>Nuovo Prodotto - RistoManager</title>
</head>
<body>

    <div class="main_container">
        <div class="header cucina">
            <h1>Gestisci Prodotti - RistoManager</h1>
        </div>
        <div class="subheader">
            <a href="./nuovoProdotto.jsp">Aggiungi Prodotto</a>
            <a href="../showProdotti">Gestisci Prodotti</a>
            <a href="./clienti.jsp">Visualizza Clienti</a>
            <a href="../showPersonale">Gestisci Personale</a>
            <a href="../logout">Logout</a>
        </div>

        <form  class="product_form" action="../aggiungiProdotto" method="POST">
                <div class="single_input">
                    <label for="nome">Nome:</label>
                    <input type="text" name="nome"required>
                </div>
                <div class="single_input">
                  <label for="foto">Immagine:</label>
                  <input type="text" name="foto"required class="form_error">
              </div>
                <div class="single_input">
                    <label for="ingredienti">Ingredienti (separati da un virgola):</label>
                    <input type="text" name="ingredienti"required>
                </div>
                <div class="single_input">
                    <label for="descrizione">Descrizione:</label>
                    <textarea name="descrizione" required rows="4" cols="50"></textarea>
                </div>
                <div class="single_input">
                    <label for="categoria">Categoria:</label>
                    <select name="categoria" required>
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
                    <input type="text" name="prezzo" required>
                </div>

                <button type="submit">Aggiungi</button>
        </form>
    </div>
    
</body>
</html>