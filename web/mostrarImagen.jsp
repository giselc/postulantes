<%-- 
    Document   : mostrarImagen
    Created on : Jan 22, 2018, 10:24:30 AM
    Author     : Gisel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <img src="<%= session.getAttribute(request.getParameter("src")) %>"width="30%"/>
    </body>
</html>
