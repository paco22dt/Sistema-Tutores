<?php

session_start();
include_once "conexion.php";

if (!isset($_SESSION['us'])) {
    if (isset($_POST['pass'])) {
        $sql = "SELECT * FROM users WHERE id_user = '$user' and password = '$password'";
        $result = $conn->query($sql);
        if ($result->num_rows > 0) {
            // output data of each row
            while ($row = $result->fetch_assoc()) {
                if ($row["id_user"] == $user) {
                    if ($row["password"]) {
                        return 1;
                    } else {
                        return 2;
                    }
                }
            }
        } else {
            return 0;
        }
    }
}
            
