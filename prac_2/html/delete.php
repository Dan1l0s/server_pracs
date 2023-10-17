<!DOCTYPE html>
<html>

<head>
    <title>Delete</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
</head>

<body>
    <h1>Введите ID для удаления</h1>
    <h1>
        <div class="div-form">
            <form action="" method="post">
                <input type="number" name="id" placeholder="ID" required>
                <input type="submit" value="Удалить строку">
            </form>
        </div>
    </h1>
    <?php
    $mysqli = new mysqli("db", "user", "password", "appDB");

    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $id = $_POST["id"];
        $query = "DELETE FROM users WHERE ID=$id";
        $result = $mysqli->query($query);
    }

    $mysqli->close();
    ?>
    </table>
</body>

</html>