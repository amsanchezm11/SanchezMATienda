<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="totalCarrito" value="${sessionScope.totalCarrito}" scope="page"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resumen Compra</title>
        <link rel="icon" type="image/png" href="${iconoTienda}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
        <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;700&display=swap" rel="stylesheet">
    </head>
    <body class="body-principal">
        
        
        <form action="FrontController" method="post" class="formulario-resumen">
        <h1>Estás en la vista de RESUMEN</h1>

        <table>
            <thead>
                <th>Cantidad</th>
                <th>Titulo</th>
                <th>Precio Unitario</th>
                <th>Importe</th>
            </thead>
            <c:forEach var="elemento" items="${sessionScope.vinilos}">
                <c:set var="precioVinilo" value="${elemento.precio}" />
                <c:set var="cantidadVinilo" value="${elemento.cantidad}" />
            <tr>
                <td class="numero">${elemento.cantidad}</td>
                <td>${elemento.titulo}</td>        
                <td class="numero"><fmt:formatNumber type="currency" value="${precioVinilo}"/></td>
                <td class="numero"><fmt:formatNumber type="currency" value="${precioVinilo*cantidadVinilo}"/></td>
            </tr>
            </c:forEach>
            <tr>
                <td>Base Imponible</td>
                <td></td>
                <td></td>
                <td class="numero"><fmt:formatNumber type="currency" value="${totalCarrito}"/></td>
            </tr>
            <tr>
                <td>IVA 10&#37;</td>
                <td></td>
                <td></td>
                <td class="numero"><fmt:formatNumber type="currency" value="${totalCarrito*0.10}"/></td>
            </tr>
            <tr>
                <td>Importe a pagar en Euros(&euro;)</td>
                <td></td>
                <td></td>
                <td class="numero"><fmt:formatNumber type="currency" value="${totalCarrito*1.10}"/></td>
            </tr>
        </table>
              <div>
                <button name="volver" type="submit" class="boton-carro">¡Gracias por tu compra! Te esperamos pronto para más sorpresas.</button>
              </div>
        </form>
        <c:import url="/INC/pie.inc"/>
    </body>
</html>
