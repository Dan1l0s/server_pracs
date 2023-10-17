<!DOCTYPE html>
<html>

<head>
    <title>Update</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
</head>

<body>
    <h1>Введите ID для обновления</h1>
    <h1>
        <div class="div-form">
            <form action="" method="post">
                <input type="number" name="id" placeholder="ID" required>
                <input type="string" name="name" placeholder="Name">
                <input type="string" name="surname" placeholder="Surname">
                <input type="submit" name="update" value="Update data">

                <?php
                $mysqli = new mysqli("db", "user", "password", "appDB");
                if ($_SERVER["REQUEST_METHOD"] == "POST") {
                    if (isset($_POST['update'])) {
                        $id = $_POST['id'];
                        $query = "SELECT name, surname FROM users WHERE ID = $id";
                        $result = $mysqli->query($query)->fetch_row();
                        $name = ($_POST['name'] == "") ? $result[0] : $_POST['name'];
                        $surname = ($_POST['surname'] == "") ? $result[1] : $_POST['surname'];

                        $query = "UPDATE users SET name='$name', surname='$surname' WHERE id=$id";
                        $result = $mysqli->query($query);
                    }
                }
                $mysqli->close();
                ?>

            </form>
        </div>
    </h1>
</body>

</html>