<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="pais" value="${requestScope.nombrePais}" scope="page"/>
<c:set var="codigoPais" value="${requestScope.codigoPais}" scope="page"/>
<c:set var="codigoLenguaje" value="${requestScope.codigoLenguaje}" scope="page"/>
<c:set var="lenguaje" value="${requestScope.lenguaje}" scope="page"/>
<c:set var="valor" value="1599.569235" scope="page" />
<fmt:bundle basename="gmt">
    <fmt:message var="gmt" key="${pais}_${codigoLenguaje}" />
</fmt:bundle>
<fmt:setLocale value="${codigoLenguaje}_${codigoPais}" scope="session" />
<fmt:setTimeZone value="${pageScope.gmt}"/>
<!DOCTYPE html>
<html lang="${codigoLenguaje}">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${pais} - ${lenguaje}</title>
        <link rel="stylesheet" type="text/css" href="${estilo}" />
    </head>
    <body>
        <c:import url="/INC/cabecera.inc"/>

        <c:choose>
            <c:when test="${fn:startsWith(gmt, '?')}">  
                <fmt:setLocale value="es_ES" scope="session" />
                <div class="encabezado">
                    <h1>Husos horarios del mundo </h1>
                    <h1><c:out value="${pais}" />, <fmt:formatDate pattern="HH:mm:ss" value="${requestScope.fecha}" timeZone="GMT+1"/></h1>
                    <p class="aviso-lenguaje">No disponemos de traducci&oacute;n al ${lenguaje} de ${pais}</p>
                </div>              
                <div class="separacion">
                    <h2 class="titulo-datos">Fecha:</h2>                    
                    <p>• <b>Personalizado: </b><fmt:formatDate pattern="MM/dd/yyyy" value="${requestScope.fecha}" /></p>
                    <p>• <b>Formato corto: </b><fmt:formatDate type="date" dateStyle="short" value="${requestScope.fecha}" /></p>
                    <p>• <b>Formato medio: </b><fmt:formatDate type="date" dateStyle="medium" value="${requestScope.fecha}" /></p>
                    <p>• <b>Formato largo: </b><fmt:formatDate type="date" dateStyle="long" value="${requestScope.fecha}" /></p>
                    <p>• <b>Formato completo: </b><fmt:formatDate type="date" dateStyle="full" value="${requestScope.fecha}" /></p>
                </div>
                <div class="separacion">
                    <h2 class="titulo-datos">Fecha y Hora:</h2>
                    <p>• <b>Hora: </b><fmt:formatDate type="time" timeStyle="medium" value="${requestScope.fecha}"/></p>
                    <p>• <b>Fecha, hora: </b>: </b><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${requestScope.fecha}"/></p>
                    <p>• <b>Formato corto: </b><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${requestScope.fecha}"/></p>
                    <p>• <b>Formato medio: </b><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${requestScope.fecha}"/></p>
                    <p>• <b>Formato largo: </b><fmt:formatDate type="both" dateStyle="long" timeZone="${gmt}" timeStyle="long" value="${requestScope.fecha}"/></p>
                </div>
                <div class="separacion">
                    <h2 class="titulo-datos">Números:</h2>
                    <p>• <b>Moneda: </b><fmt:formatNumber type="currency" value="${pageScope.valor}"/></p>
                    <p>• <b>Tanto(%): </b><fmt:formatNumber type="percent" maxIntegerDigits="4" value="${pageScope.valor}"/></p>
                    <p>• <b>Personalizado: </b><fmt:formatNumber type="number" pattern="####,###" value="${pageScope.valor}"/></p>
                    <p>• <b>Otro: </b><fmt:formatNumber type="number" groupingUsed="true" value="${pageScope.valor}"/></p>
                    <p>• <b>Otro: </b><fmt:formatNumber type="number" maxIntegerDigits="3" value="${pageScope.valor}"/></p> 
                    <p>• <b>Otro: </b><fmt:formatNumber type="number" maxFractionDigits="6" value="${pageScope.valor}"/></p>
                </div>
            </c:when>
            <c:otherwise>

                <fmt:bundle basename="${codigoLenguaje}_${codigoPais}">
                    <div class="encabezado">
                        <h1><fmt:message key="${pais}"/>, <fmt:formatDate pattern="HH:mm:ss" value="${requestScope.fecha}"/></h1>       
                        <h1><fmt:message key="Husos horarios del mundo"/></h1>
                    </div>
                    <div class="separacion">
                        <h2 class="titulo-datos"><fmt:message key="Fecha"/>:</h2>                    
                        <p>• <b><fmt:message key="Personalizado"/>: </b><fmt:formatDate pattern="MM/dd/yyyy" value="${requestScope.fecha}" /></p>
                        <p>• <b><fmt:message key="Formato corto"/>: </b><fmt:formatDate type="date" dateStyle="short" value="${requestScope.fecha}" /></p>
                        <p>• <b><fmt:message key="Formato medio"/>: </b><fmt:formatDate type="date" dateStyle="medium" value="${requestScope.fecha}" /></p>
                        <p>• <b><fmt:message key="Formato largo"/>: </b><fmt:formatDate type="date" dateStyle="long" value="${requestScope.fecha}" /></p>
                        <p>• <b><fmt:message key="Formato completo"/>: </b><fmt:formatDate type="date" dateStyle="full" value="${requestScope.fecha}" /></p>
                    </div>
                    <div class="separacion">
                        <h2 class="titulo-datos"><fmt:message key="Fecha y Hora"/>:</h2>
                        <p>• <b><fmt:message key="Hora"/>: </b><fmt:formatDate type="time" timeStyle="medium" value="${requestScope.fecha}"/></p>
                        <p>• <b><fmt:message key="Fecha"/>, <fmt:message key="Hora"/>: </b>: </b><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${requestScope.fecha}"/></p>
                        <p>• <b><fmt:message key="Formato corto"/>: </b><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${requestScope.fecha}"/></p>
                        <p>• <b><fmt:message key="Formato medio"/>: </b><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${requestScope.fecha}"/></p>
                        <p>• <b><fmt:message key="Formato largo"/>: </b><fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${requestScope.fecha}"/></p>
                    </div>
                    <div class="separacion">
                        <h2 class="titulo-datos"><fmt:message key="Números"/>:</h2>
                        <p>• <b><fmt:message key="Moneda"/>: </b><fmt:formatNumber type="currency" value="${pageScope.valor}"/></p>
                        <p>• <b><fmt:message key="Tanto"/>(%): </b><fmt:formatNumber type="percent" maxIntegerDigits="4" value="${pageScope.valor}"/></p>
                        <p>• <b><fmt:message key="Personalizado"/>: </b><fmt:formatNumber type="number" pattern="####,###" value="${pageScope.valor}"/></p>
                        <p>• <b><fmt:message key="Otro"/>: </b><fmt:formatNumber type="number" groupingUsed="true" value="${pageScope.valor}"/></p>
                        <p>• <b><fmt:message key="Otro"/>: </b><fmt:formatNumber type="number" maxIntegerDigits="3" value="${pageScope.valor}"/></p> 
                        <p>• <b><fmt:message key="Otro"/>: </b><fmt:formatNumber type="number" maxFractionDigits="6" value="${pageScope.valor}"/></p>
                    </div>
                </fmt:bundle>
            </c:otherwise>
        </c:choose>
        <div>
            <form action="${contexto}/FrontController" method="post" class="posicion-boton">
                <input type="submit" name="volver" value="Men&uacute; Principal"/>
            </form>    
        </div>
        <c:import url="/INC/pie.inc"/>       
    </body>
</html>
