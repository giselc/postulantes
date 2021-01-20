<%-- 
    Document   : resultadosFinales
    Created on : 24-ene-2017, 10:56:24
    Author     : gcincunegui
--%>

<%@page import="Classes.ManejadorSeleccionBD"%>
<%@ include file="header.jsp" %>
    <% if(u.isAdmin()){%>
<script>

function subir(entra, casosAnalizar){
    var r=true;
    if(casosAnalizar){
        r=confirm("Existen casos a analizar ¿Desea continuar?");
    }
    if (r==true){
        var f= document.getElementById("imprimir");
        var input = document.createElement('input');input.type = 'hidden';input.name = 'entra';
        if(entra==0){
            input.value=0;
        }
        else{
            if(entra==1){
                input.value=1;
            }
            else{
                input.value=2;
            }
        }
        f.appendChild(input);
        f.submit();
    }
}

</script>
<form method="post" action='ImprimirResultados' id="imprimir">
<h2 align="center">Seleccione los campos que desea que aparezcan en el listado.</h2>
    <%@include file="camposFiltros.jsp" %>
<table style="width: 30%;" align="right">
    <% 
        ManejadorSeleccionBD ms = new ManejadorSeleccionBD();
        boolean b= ms.existenCasosAAnalizar();
    %>
    <tr>
        <td>
            <button onclick="subir(0,<%= b %>);">ENTRAN</button>
        </td>
        <td>
            <button onclick="subir(2,<%= b %>);">LISTA DE ESPERA</button>
        </td>
        <td>
            <button onclick="subir(1,<%= b %>);">NO ENTRAN</button>
        </td>
    </tr>
</table>
    
    
</form>




<%}else{
    response.sendRedirect("/listar.jsp");
    }%>
<%@ include file="footer.jsp" %> 