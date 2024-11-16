<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/INC/metas.inc"/>
        <title>Tienda</title>
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
        <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;700&display=swap" rel="stylesheet">
    </head>
    <body class="body-principal">

        <form action="FrontController" method="post" class="formulario">

            <div>
                <h1>Vinilos disponibles</h1>
            </div>
            <div><p>Seleccione el vinilo:</p></div>
            <div>
                <select name="vinilos">
                    <option selected disabled>Seleccione el vinilo</option>
                    <%-- <c:forEach var="elemento" items="${sessionScope.listaVinilos}">
                         <option value="${elemento}" ${elemento == param.vinilos ? 'selected' : ''}>${elemento}</option>
                     </c:forEach> --%>

                    <%--<c:forEach var="elemento" items="${sessionScope.listaVinilosObj}">
                        <c:set var="valor" value="${elemento.titulo}+${elemento.precio}" />
                        <option value="${elemento.titulo}+${elemento.precio}" ${valor == param.vinilos ? 'selected' : ''}>${elemento.titulo}</option>
                    </c:forEach>--%>

                    <option value="Nirvana - Nevermind+35.50" 
                            <c:if test="${param.vinilos eq 'Nirvana - Nevermind+35.50'}">selected</c:if>>Nirvana - Nevermind</option>

                            <option value="Maneskin - Ballo della vita+35.50" 
                            <c:if test="${param.vinilos eq 'Maneskin - Ballo della vita+30.00'}">selected</c:if>>Maneskin - Ballo della vita</option>

                            <option value="The Rolling Stones - Out of Our Heads+35.50" 
                            <c:if test="${param.vinilos eq 'The Rolling Stones - Out of Our Heads+45.00'}">selected</c:if>>The Rolling Stones - Out of Our Heads</option>

                            <option value="The Beatles - Please Please Me+35.50" 
                            <c:if test="${param.vinilos eq 'The Beatles - Please Please Me+60.00'}">selected</c:if>>The Beatles - Please Please Me</option>

                            <option value="Bob Dylan - Blonde on Blonde+35.50" 
                            <c:if test="${param.vinilos eq 'Bob Dylan - Blonde on Blonde+50.00'}">selected</c:if>>Bob Dylan - Blonde on Blonde</option>

                            <option value="Jimi Hendrix - Are You Experienced+35.50" 
                            <c:if test="${param.vinilos eq 'Jimi Hendrix - Are You Experienced+ 45.35'}">selected</c:if>>Jimi Hendrix - Are You Experienced</option>

                            <option value="Foo Fighters - The Colour and the Shape+35.50" 
                            <c:if test="${param.vinilos eq 'Foo Fighters - The Colour and the Shape+40.75'}">selected</c:if>>Foo Fighters - The Colour and the Shape</option>

                            <option value="Led Zeppelin - Physical Graffiti+35.50" 
                            <c:if test="${param eq 'Led Zeppelin - Physical Graffiti+35.50'}">selected</c:if>>Led Zeppelin - Physical Graffiti</option>

                            <option value="Pink Floyd - The Dark Side of the Moon+35.50" 
                            <c:if test="${param eq 'Pink Floyd - The Dark Side of the Moon+37.50'}">selected</c:if>>Pink Floyd - The Dark Side of the Moon</option>

                            <option value="The Who - Tommy+35.50" 
                            <c:if test="${param eq 'The Who - Tommy+42.90'}">selected</c:if>>The Who - Tommy</option>

                    </select>
                </div>
                <div><p>Seleccione la cantidad:</p></div>
                <div>
                    <label for="cantidad">Cantidad:</label>
                    <input type="number" name="cantidad" value="1" step="1"/>
                </div>
                <div><p class="aviso">${requestScope.mensaje}</p></div>
            <div>
                <input type="submit" name="aniadir" value="Añadir a la cesta"/>
                <input type="submit" name="verCarrito" value="Ver Carrito" ${sessionScope.vinilos.isEmpty() ? "disabled":""} />
                <input type="submit" name="finalizar" value="Finalizar Compra"${sessionScope.vinilos.isEmpty() ? "disabled":""} />
            </div>
        </form>

        <%--<div>
            <p class="blanco">${sessionScope.valorCookie}</p>
        </div> --%>

           
        <c:import url="/INC/pie.inc"/>
    </body>
</html>
