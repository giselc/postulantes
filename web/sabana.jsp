<%-- 
    Document   : nota
    Created on : 28-nov-2016, 11:15:27
    Author     : gcincunegui
--%>

<%@page import="Classes.Departamento"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="header.jsp" %>
    <% if(u.isAdmin()){%>
<script>

function subir(carrera){
    var f= document.getElementById("imprimir");
    var input = document.createElement('input');input.type = 'hidden';input.name = 'carrera';
    if(carrera==1){
        input.value=1;
    }
    else{
        input.value=2;
    }
    f.appendChild(input);
    f.submit();
}
function mostrarPersonal(){
            if(document.getElementById("filtrops").value == "S"){
                document.getElementById("filtropse").style.display = '';
            }
            else{
                document.getElementById("filtropse").style.display = 'none';
            }
        }

</script>
<form method="post" action='Sabana' id="imprimir">
<h2 align="center">Seleccione los campos que desea que aparezcan en la sábana.</h2>
<%@include file="camposFiltros.jsp" %>

<table style="width: 30%;" align="right">
    <tr>
        <td>
            <button id="comando" onclick="subir(1);">IMPRIMIR SABANA COMANDO</button>
        </td>
        <td>
            <button id="apoyo" onclick="subir(2);">IMPRIMIR SABANA APOYO</button>
        </td>
    </tr>
</table>
    
    
</form>


<%--
<h1>SECCI&Oacute;N EN MANTENIMIENTO</h1>
--%>
<%}else{
    response.sendRedirect("/listar.jsp");
    }%>
<%@ include file="footer.jsp" %> 