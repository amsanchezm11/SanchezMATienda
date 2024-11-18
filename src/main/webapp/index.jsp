<jsp:directive.page contentType="text/html" pageEncoding="UTF-8"/>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:url var="estilo" value="/CSS/estilo.css" scope="application" />
<c:set var="contexto" value="${pageContext.request.contextPath}" scope="application" />
<c:url var="iconoTienda" value="/IMG/vinilo.png" scope="application" />
<fmt:setLocale value="es_ES" scope="application" />
<!DOCTYPE html>
<html lang="es">
    <head>
         <c:import url="/INC/metas.inc"/>
        <meta http-equiv="refresh" content="3, url='FrontController'" />
        <title>Beat &amp; Surco</title>
        <link rel="icon" type="image/png" href="${iconoTienda}">
        <link rel="stylesheet" type="text/css" href="${estilo}" media="screen" />
        <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;500;700&display=swap" rel="stylesheet">
    </head>
    <body class="body-principal">
        
        <div class="container-logo-principal"> 
            <div class="titulo-tienda">
                <h1>Beat & Surco</h1>
                <h1>Music Shop</h1>
            </div>
            <div class="container-vinilo">
                <div class="vinilo"></div>
            </div>
        </div>
        <div class="footer">
            <p>&copy; Beat &amp; Surco</p>
        </div>

    </body>
</html>