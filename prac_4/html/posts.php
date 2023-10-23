<?php

class Post implements \JsonSerializable
{
    private $id;
    private $date;
    private $text;
    private $user_id;

    public function __construct($id, $date, $text, $user_id)
    {
        $this->id = $id;
        $this->date = $date;
        $this->text = $text;
        $this->user_id = $user_id;
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

    $result = $conn->query("SELECT * FROM posts WHERE post_id=$id");
    if (mysqli_num_rows($result) != 0) {
        foreach ($result as $row) {
            $post = new Post($row['post_id'], $row['post_date'], $row['post_text'], $row['user_id']);
            $json = json_encode($post);
            echo ($json);
        }
    } else {
        echo ('There is no post with provided ID');
    }
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $date = $_GET['date'];
    $text = $_GET['text'];
    $user_id = $_GET['user_id'];

    $result = $conn->query("SELECT * FROM users WHERE user_id=$user_id");
    if (mysqli_num_rows($result) == 0) {
        echo ('There is no user with provided ID');
    } else {
        $result = $conn->query("INSERT INTO posts (post_date, post_text, user_id) VALUES ('$date', '$text', '$user_id')");

        if ($result) {
            echo ("Post was added successfully!");
        } else {
            echo ('There was an error adding the post!');
        }
    }

}

if ($_SERVER["REQUEST_METHOD"] == "PUT") {
    $id = $_GET['id'];
    $date = $_GET['date'];
    $text = $_GET['text'];
    $user_id = $_GET['user_id'];

    $result = $conn->query("SELECT * FROM posts WHERE post_id=$id");
    $result_users = $conn->query("SELECT * FROM users WHERE user_id=$user_id");
    if (mysqli_num_rows($result) == 0) {
        echo ('There is no post with provided ID');
    } else if (mysqli_num_rows($result_users) == 0) {
        echo ('There is no user with provided ID');
    } else {
        $result = $conn->query("UPDATE posts SET post_date='$date', post_text='$text', user_id='$user_id' WHERE post_id=$id");

        if ($result) {
            echo ("Post was updated successfully!");
        } else {
            echo ('There was an error updating the post!');
        }
    }
}

if ($_SERVER["REQUEST_METHOD"] == "DELETE") {
    $id = $_GET['id'];

    $result = $conn->query("SELECT * FROM posts WHERE post_id=$id");
    if (mysqli_num_rows($result) == 0) {
        echo ('There is no post with provided ID');
    } else {
        $result = $conn->query("DELETE FROM posts WHERE post_id=$id");

        if ($result) {
            echo ("Post was deleted successfully!");
        } else {
            echo ('There was an error deleting the post!');
        }
    }
}

$conn->close();
?>