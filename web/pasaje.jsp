<%-- 
    Document   : pasaje
    Created on : 20-feb-2017, 8:38:50
    Author     : gcincunegui
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp" %>  
<% 
    if(u.isSuperAdmin()){

%>
<form method="post" action='Pasaje?entran=0' >
    <input type="number" maxlength="8" minlength="8" value="0" name="ci"/>
    <input type="submit" value="ACEPTAR"/>
</form>
<% 
    }
    else{
         response.sendRedirect("");
    }

%>
<%@ include file="footer.jsp" %> 