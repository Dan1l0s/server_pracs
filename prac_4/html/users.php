<?php

class User implements \JsonSerializable
{
    private $id;
    private $login;
    private $real_name;

    public function __construct($id, $login, $real_name)
    {
        $this->id = $id;
        $this->login = $login;
        $this->real_name = $real_name;
    }

    #[\ReturnTypeWillChange]
    public function jsonSerialize()
    {
        $vars = get_object_vars($this);
        return $vars;
    }
}

$conn = new mysqli("db", "user", "password", "appDB");

if ($_SERVER["REQUEST_METHOD"] == "GET") {
    $id = $_GET['id'];

    $result = $conn->query("SELECT * FROM users WHERE user_id=$id");
    if (mysqli_num_rows($result) != 0) {
        foreach ($result as $row) {
            $user = new User($row['user_id'], $row['user_login'], $row['user_realname']);
            $json = json_encode($user);
            echo ($json);
        }
    } else {
        echo ('There is no user with provided ID');
    }
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $login = $_GET['login'];
    $realname = $_GET['realname'];

    $result = $conn->query("INSERT INTO users (user_login, user_realname) VALUES ('$login', '$realname')");
    if ($result) {
        echo ("User was added successfully!");
    } else {
        echo ('There was an error adding the user!');
    }

}

if ($_SERVER["REQUEST_METHOD"] == "PUT") {
    $id = $_GET['id'];
    $login = $_GET['login'];
    $realname = $_GET['realname'];

    $result = $conn->query("SELECT * FROM users WHERE user_id=$id");
    if (mysqli_num_rows($result) == 0) {
        echo ('There is no user with provided ID');
    } else {
        $result = $conn->query("UPDATE users SET user_login='$login', user_realname='$realname' WHERE user_id=$id");

        if ($result) {
            echo ("User was updated successfully!");
        } else {
            echo ('There was an error updating the user!');
        }
    }
}

if ($_SERVER["REQUEST_METHOD"] == "DELETE") {
    $id = $_GET['id'];

    $result = $conn->query("SELECT * FROM users WHERE user_id=$id");
    if (mysqli_num_rows($result) == 0) {
        echo ('There is no user with provided ID');
    } else {
        $result = $conn->query("DELETE FROM posts WHERE user_id=$id");
        $result = $conn->query("DELETE FROM users WHERE user_id=$id");

        if ($result) {
            echo ("User was deleted successfully!");
        } else {
            echo ('There was an error deleting the user!');
        }
    }
}

$conn->close();

?>