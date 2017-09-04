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
    <table style="width: 100%; background-color: #CDCDCD; " border="1" cellspacing="0">
        <tr>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="ID"/> ID
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="PrimerApellido"/> Primer Apellido
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="SegundoApellido"/> Segundo Apellido
            </td>
            
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="PrimerNombre"/> Primer Nombre
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="SegundoNombre"/> Segundo Nombre
            </td>
            <td>
                <input type="checkbox" name="lista" value="CI"/> CI
            </td>
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="Sexo"/> Sexo
            </td>
            <td>
                <input type="checkbox" name="lista"  value="UI"/> Unidad Inscriptora
            </td>
            <td>
                <input type="checkbox" name="lista"  value="FechaNac"/> Fecha de Nacimiento
            </td>
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="LM"/> Liceo Militar
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Aprobado"/> Ultimo año aprobado
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Orient"/> Orientacion
            </td>
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="DepDom"/> Departamento domicilio
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Obs"/> Observaciones de Ingreso
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="Medico"/> Médico
            </td>
           
            
        </tr>
        <tr>
             <td>
                <input type="checkbox" name="lista" checked="checked" value="Psico"/> Psicotécnica
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="Odont"/> Odontológico
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="EdFis"/> Ed. Física
            </td>
            
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="Mat"/> Matemática
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="IdEsp"/> Idioma Español
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="Hist"/> Historia
            </td>
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="Arrojo"/> Arrojo
            </td>
            <td>
                <input type="checkbox" name="lista"  value="ObsOdont"/> Obs. Odontológico
            </td>
            <td>
                <input type="checkbox" name="lista"  value="ObsMedico"/> Obs. Médico
            </td>
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="ObsPsico"/> Obs. Psicotécnica
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="Promedio"/> Promedio
            </td>
        </tr>
    </table>
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