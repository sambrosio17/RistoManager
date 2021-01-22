<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/9b886a1068.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./public/style/style.css">
    <title>Login - RistoManager</title>
</head>
<body>
    <form action="login" method="POST" class="login_form">
        <div class="single_login_input">
            <label for="email">email:</label>
            <input type="text" name="email">
        </div>

        <div class="single_login_input">
            <label for="password">password:</label>
            <input type="password" name="password">
        </div>
        <button type="submit">Accedi</button>
    </form>
</body>
</html>