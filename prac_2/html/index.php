<html lang="en">

<head>
    <title>Hello world page</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
</head>

<body>

    <h1> <a href="create.php">Create</a> </h1>
    <h1> <a href="update.php">Update</a> </h1>
    <h1> <a href="delete.php">Delete</a> </h1>

    <h1>Таблица пользователей данного продукта</h1>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Surname</th>
        </tr>
        <?php
        $mysqli = new mysqli("db", "user", "password", "appDB");
        $result = $mysqli->query("SELECT * FROM users");
        foreach ($result as $row) {
            echo "<tr><td>{$row['ID']}</td><td>{$row['name']}</td><td>{$row['surname']}</td></tr>";
        }
        $mysqli->close();
        ?>
    </table>

    <!-- <?php
    phpinfo();
    ?> -->

</body>

</html>