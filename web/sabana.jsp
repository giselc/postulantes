<%-- 
    Document   : nota
    Created on : 28-nov-2016, 11:15:27
    Author     : gcincunegui
--%>

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

</script>
<form method="post" action='Sabana' id="imprimir">
<h2 align="center">Seleccione los campos que desea que aparezcan en la sábana.</h2>
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
                <input type="checkbox" name="lista" checked="checked" value="SegundoNombre"/> SegundoNombre
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
                <input type="checkbox" name="lista" checked="checked" value="Medico"/> Médico
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="Psico"/> Psicotécnica
            </td>
           
            
        </tr>
        <tr>
             <td>
                <input type="checkbox" name="lista" checked="checked" value="Odont"/> Odontológico
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="EdFis"/> Ed. Física
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="Mat"/> Matemática
            </td>
            
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="IdEsp"/> Idioma Español
            </td>
            <td>
                <input type="checkbox" name="lista" checked="checked" value="Hist"/> Historia
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Arrojo"/> Arrojo
            </td>
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="ObsOdont"/> Obs. Odontológico
            </td>
            <td>
                <input type="checkbox" name="lista"  value="ObsMedico"/> Obs. Médico
            </td>
            <td>
                <input type="checkbox" name="lista"  value="ObsPsico"/> Obs. Psicotécnica
            </td>
            
        </tr>
         <tr>
             <td>
                <input type="checkbox" name="lista" checked="checked" value="Promedio"/> Promedio
            </td>
            <td>
                <input type="checkbox" name="lista"  value="ObsEdFisica"/> Obs. Ed.Fisica
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Obs"/> Observaciones de Ingreso
            </td>
        </tr>
    </table>
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




<%}else{
    response.sendRedirect("/listar.jsp");
    }%>
<%@ include file="footer.jsp" %> 