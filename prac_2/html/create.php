<!DOCTYPE html>
<html>

<head>
    <title>Create</title>
    <link rel="stylesheet" href="style.css">
</head>

<body>
    <h1>Введите имя и фамилию для добавления:</h1>
    <h1>
        <div class="div-form">
            <form action="" method="post">
                <input type="string" name="name" placeholder="Name" required>
                <input type="string" name="surname" placeholder="Surname" required>
                <input type="submit" value="Добавить строку">
            </form>
        </div>
    </h1>
    <?php
    $mysqli = new mysqli("db", "user", "password", "appDB");

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $name = $_POST["name"];
        $surname = $_POST["surname"];
        $query = "INSERT INTO users (name,surname) VALUES ('$name','$surname')";
        $result = $mysqli->query($query);
    }

    $mysqli->close();
    ?>
</body>

</html>