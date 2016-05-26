<%-- 
    Document   : Users
    Created on : May 17, 2016, 4:27:14 PM
    Author     : Francisco
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href='/resources/css/bootstrap.min.css' rel='stylesheet' media='screen'>
        <link href='/resources/js/bootstrap.min.js' rel='stylesheet' media='screen'>
        <link href='/resources/js/jquery-2.2.3.min.js' rel='stylesheet' media='screen'>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css" type="text/css"/>
        
        <title>Users Management</title>
    </head>
    <body>
        <h1>Users Data</h1>
        <!--Errores: commandName no lo detecto, <form:form> -->
        <form action="users.do" method="POST">
            <table>
                <tr>
                    <td>Username</td>
                    <td><input type="text" pattern="id_user"></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="text" pattern="password"></td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" class="btn-primary" name="action" value="add">
                        <input type="submit" name="action" value="edit">
                        <input type="submit" name="action" value="delete">
                        <input type="submit" name="action" value="search">
                    </td>
                </tr>
            </table>
        </form>
        <br>
        <table>
            <th>Username</tr>
            <th>Password</th>
                <c:forEach items="${usersList}" var="users">
                    <tr>
                        <td>${users.id_user}</td>
                        <td>${users.password}</td>
                    </tr>
                </c:forEach>
        </table>
    </body>
</html>
