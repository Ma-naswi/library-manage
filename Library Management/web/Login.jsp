<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="container">
        <h2>Login to Library Management System</h2>
        <form action="Login" method="POST">
            <div class="form-group">
                <label for="UserName">Username</label>
                <input type="text" id="UserName" name="UserName" required>
            </div>
            <div class="form-group">
                <label for="Password">Password</label>
                <input type="password" id="Password" name="Password" required>
            </div>
            <button type="submit">Login</button>
        </form>
        <p>New user? <a href="Register.jsp">Register here</a></p>
    </div>
</body>
</html>
