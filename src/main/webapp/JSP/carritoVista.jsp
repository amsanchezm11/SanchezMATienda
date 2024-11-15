<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito</title>
    </head>
    <body class="body-principal">
        <h1>Estas en carrito vista</h1>

        <table>
            <thead>
            <th>Titulo</th>
            <th>Precio</th>
            <th>Cantidad</th>
            <th>Cantidad</th>
        </thead>
        <c:forEach var="elemento" items="${sessionScope.vinilos}">
            <tr>
                <td>${elemento.titulo}</td>
                <td>${elemento.precio}</td>
                <td>${elemento.cantidad}</td>
                <td>
                    <button name="accion" value="sumar+${elemento.titulo}">+</button>
                    <button name="accion" value="restar+${elemento.titulo}">-</button>
                    <button name="accion" value="eliminar+${elemento.titulo}">Eliminar</button>
                </td>
            </tr>
        </c:forEach>
    </table>


</body>
</html>
