<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrito</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
        <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;700&display=swap" rel="stylesheet">
    </head>
    <body class="body-principal">

        <form action="CarritoController" method="post" class="formulario-carrito">

            <div class="titulo-carrito">
                <h1>Mi Carrito</h1>
            </div>
            <c:if test="${empty sessionScope.vinilos}">
                <div>
                    <p class="aviso">El carrito est&aacute; vac&iacute;o</p>
                </div>
            </c:if>
            <c:if test="${!empty sessionScope.vinilos}">
                <table>
                    <thead>
                    <th>Titulo</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th class="th-acciones">Acciones</th>
                    </thead>
                    <c:forEach var="elemento" items="${sessionScope.vinilos}">
                        <c:set var="precioVinilo" value="${elemento.precio}" />
                        <tr>
                            <td>${elemento.titulo}</td>        
                            <td><fmt:formatNumber type="currency" value="${precioVinilo}"/></td>
                            <td>${elemento.cantidad}</td>
                            <td>
                                <button name="accion" type="submit" class="boton-accion" value="sumar+${elemento.titulo}"><i class="fa-solid fa-plus"></i></button>
                                <button name="accion" type="submit" class="boton-accion" value="restar+${elemento.titulo}" ${elemento.cantidad==1? "disabled":""}><i class="fa-solid fa-minus"></i></button>
                                <button name="accion" type="submit" class="boton-accion" value="eliminar+${elemento.titulo}"><i class="fas fa-trash"></i></button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <div>
                <button name="comprar" type="submit" class="boton-carro" ${sessionScope.vinilos.isEmpty() ? "disabled":""}>Comprar <i class="fa-regular fa-credit-card"></i></button>
                <button name="vaciar" type="submit" class="boton-carro" ${sessionScope.vinilos.isEmpty() ? "disabled":""}>Eliminar Carrito <i class="fas fa-shopping-cart"></i><i class="fa-solid fa-x"></i></button>
                <button name="seguir" type="submit" class="boton-carro">Seguir Comprando <i class="fa-solid fa-cart-shopping"></i><i class="fa-solid fa-arrow-left-long"></i></button>
            </div>
        </form>
        <c:import url="/INC/pie.inc"/>
    </body>
</html>
