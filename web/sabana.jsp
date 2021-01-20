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
<%--
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
<h2 align="center">Seleccione el filtro que desea aplicar.</h2>

<table style="font-size: 70%">
        
            <tr>

                <td>
                    LMGA:
                </td>
                <td>
                    <select name="filtrolmga" form="imprimir"> 
                        <option value="todos" selected="selected">TODOS</option>
                        <option value="S">SI</option>
                        <option value="N">NO</option>
                    </select>
                </td>
            </tr>
            <tr>

                <td>
                    Pase directo:
                </td>
                <td>
                    <select name="filtropd" form="imprimir"> 
                        <option value="todos" selected="selected">TODOS</option>
                        <option value="S">SI</option>
                        <option value="N">NO</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    Sexo:
                </td>
                <td>
                    <select name="filtrosexo" form="imprimir">
                        <option value="todos" selected="selected">TODOS</option>
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    Unidad Inscriptora:
                </td>
                <td>
                    <select name="filtrounidadInsc" multiple="multiple" form="imprimir">
                        <%
                            ArrayList<Usuario> au= mc.getUsuarios();
                            for (Usuario usu : au){
                                out.print("<option value='"+String.valueOf(usu.getId())+"'>"+usu.getNombreMostrar()+"</option>");
                            }
                        %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    NECESITA ALOJAMIENTO:
                </td>
                <td>
                    <select name="filtroalojamiento" form="imprimir"> 
                        <option value="todos" selected="selected">TODOS</option>
                        <option value="S">SI</option>
                        <option value="N">NO</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    NSP:
                </td>
                <td>
                    <select name="filtronsp" form="imprimir"> 
                        <option value="todos" selected="selected">TODOS</option>
                        <option value="S">SI</option>
                        <option value="N">NO</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    Renunci&oacute;:
                </td>
                <td>
                    <select name="filtrorenuncio" form="imprimir"> 
                        <option value="todos" selected="selected">TODOS</option>
                        <option value="S">SI</option>
                        <option value="N">NO</option>
                    </select>
                </td>
            </tr>
        <tr>
            <td>
                Departamento Nacimiento:
            </td>
            <td>
                <select name="filtrodepNac" multiple="multiple" form="imprimir">
                    <%
                        ArrayList<Departamento> ad= mc.getDepartamentos();
                        int j = 0;
                        for (Departamento dep : ad){
                            if (j==0){
                                j++;
                            }
                            else{
                                out.print("<option value='"+String.valueOf(dep.getCodigo())+"'>"+dep.getDescripcion()+"</option>");
                            }
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Departamento Domicilio:
            </td>
            <td>
                <select name="filtrodepDom" multiple="multiple" form="imprimir">
                    <%
                        ad= mc.getDepartamentos();
                        j = 0;
                        for (Departamento dep : ad){
                            if (j==0){
                                j++;
                            }
                            else{
                                out.print("<option value='"+String.valueOf(dep.getCodigo())+"'>"+dep.getDescripcion()+"</option>");
                            }
                        }
                    %>
                </select>
            </td>
        </tr>  
        <tr>
            <td>
                Personal Subalterno:
            </td>
            <td> 
                <select name="filtrops" form="imprimir" onshow="mostrarPersonal();" onchange="mostrarPersonal();" id="filtropsA">
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="S">SI</option>
                    <option value="N">NO</option>
                </select>
            </td>
        </tr>
        <tr id="filtropseA" style="display: none">
            <td>
                Personal subalterno de Ej&eacute;rcito:
            </td>
            <td>
                <select name="filtropsEjercito" form="imprimir">
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="S">SI</option>
                    <option value="N">NO</option>
                </select>
            </td>
        </tr>
        <tr> 
            <td>
                Cantidad de hijos:
            </td>
            <td>
                <select name="filtrocanthijos" multiple="multiple" form="imprimir">
                    <option value="1">1</option>
                    <option value="2">2</option>
                     <option value="3">3</option>
                      <option value="4">+ de 3</option>
                </select>
            </td>
        </tr>
        
        <tr>
            <td>
                Condicional:
            </td>
            <td>
                <select name="filtrocondicional" form="imprimir"> 
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="S">SI</option>
                    <option value="N">NO</option>
                </select>
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


--%>
<h1>SECCI&Oacute;N EN MANTENIMIENTO</h1>

<%}else{
    response.sendRedirect("/listar.jsp");
    }%>
<%@ include file="footer.jsp" %> 