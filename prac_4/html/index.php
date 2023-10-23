<html lang="en">

<head>
    <title>Hello world page</title>
    <link rel="stylesheet" href="style.css" type="text/css" />
</head>

<body>
    <h1>Users<h1>
            <?php
            $conn = new mysqli("db", "user", "password", "appDB");

            $result = $conn->query("SELECT * FROM users");
            echo ('<table><tr><th>User ID</th><th>User login</th><th>User real name</th></tr>');
            foreach ($result as $row) {
                echo "<tr><td>{$row['user_id']}</td><td>{$row['user_login']}</td><td>{$row['user_realname']}</td></tr>";
            }
            echo ('</table>');


            $result = $conn->query("SELECT * FROM posts");
            if (mysqli_num_rows($result) != 0) {
                echo ('<h1>Users\' posts</h1><table>
        <tr><th>Post ID</th><th>Post date</th><th>Post text</th><th>Author ID</th></tr>');
                foreach ($result as $row) {
                    echo "<tr><td>{$row['post_id']}</td><td>{$row['post_date']}</td><td>{$row['post_text']}</td><td>{$row['user_id']}</td></tr>";
                }
                echo ('</table>');
            }
            $conn->close();
            ?>
</body>

</html>