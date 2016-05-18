<?php
session_start();

include_once 'conexion.php';

function Verificar_login($user, $password, &$result) {
    //$sql = "SELECT IFNULL( (SELECT U.`Name` FROM `user` as U WHERE U.`ID_User` = '".$user."' AND U.`Pass` = '".$password."' ),0);";
    $sql = "SELECT * FROM `users` as U WHERE U.`id_user` = '" . $user . "' AND U.`password` = '" . $password . "';";


    //SELECT IFNULL( (SELECT U.`Nombre` FROM usuario as U WHERE U.`ID_Usuario` = 1561237) ,0);
    $resultado = mysql_query($sql) or die('Error: ' . mysql_error());

    $count = 0;
    while ($row = mysql_fetch_object($resultado)) {
        $count++;
        $result = $row;
    }
    if ($count == 1) {
        return 1;
    } else {
        return 0;
    }
}

if (isset($_POST['login'])) {
    $usuario = $_POST['user'];
    $password = $_POST['password'];


    if (Verificar_login($usuario, $password, $result) == 1) {
        echo 'INICIO DE SESIÓN EXITOSO';
    } ELSE {
        echo 'DATOS DE USUARIO INCORRECTOS';
    }
}
?>

<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='resource/css/bootstrap.min.css' rel='stylesheet' media='screen'>
        <link href='resource/js/bootstrap.min.js' rel='stylesheet' media='screen'>
        <link href='resource/js/jquery-2.2.3.min.js' rel='stylesheet' media='screen'>
        <title>Login</title>
    </head>
    <body>
        <div class="login">
            <h1 class="title-page">Iniciar sesión</h1>
            <form action="" method="post" class="login">
                <p><input type="text" name="user" value="" placeholder="Usuario"></p>
                <p><input type="password" name="password" value="" placeholder="Contraseña"></p>
                <p class="submit"><input type="submit" name="login"  value="Iniciar sesión"></p>
            </form>
        </div>
    </body>

</html>
<!--<form action="" method="post" class="login">
    <div><label>Usuario</label><input name="user" type="text" ></div>
    <div><label>Contraseña</label><input name="password" type="password"></div>
    <div><input name="login" type="submit" value="login"></div>
</form>

<style type="text/css">
    *{
        font-size: 14px;
    }
    form.login {
        background: none repeat scroll 0 0 #F1F1F1;
        border: 1px solid #DDDDDD;
        font-family: sans-serif;
        margin: 0 auto;
        padding: 20px;
        width: 278px;
    }
    form.login div {
        margin-bottom: 15px;
        overflow: hidden;
    }
    form.login div label {
        display: block;
        float: left;
        line-height: 25px;
    }
    form.login div input[type="text"], form.login div input[type="password"] {
        border: 1px solid #DCDCDC;
        float: right;
        padding: 4px;
    }
    form.login div input[type="submit"] {
        background: none repeat scroll 0 0 #DEDEDE;
        border: 1px solid #C6C6C6;
        float: right;
        font-weight: bold;
        padding: 4px 20px;
    }
    .error{
        color: red;
        font-weight: bold;
        margin: 10px;
        text-align: center;
    }
</style>-->

