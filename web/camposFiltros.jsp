<%-- 
    Document   : camposFiltros
    Created on : 20/01/2021, 17:21:38
    Author     : Gisel
--%>
<%@page import="Classes.Departamento"%>
<%@page import="Classes.Usuario"%>
<%@page import="java.util.ArrayList"%>
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
                <input type="checkbox" name="lista"  value="cc"/> Credencial Civica
            </td>
            <td>
                <input type="checkbox" name="lista"  value="ps"/> Personal Subalterno
            </td>
            <td>
                <input type="checkbox" name="lista"  value="tel"/> Tel&eacute;fono
            </td>
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="LM"/> Liceo Militar
            </td>
            <td>
                <input type="checkbox" name="lista"  value="pd"/> Pase Directo
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Aprobado"/> Ultimo año aprobado
            </td>
            
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="Orient"/> Orientacion
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Dom"/> Domicilio
            </td>
            <td>
                <input type="checkbox" name="lista"  value="DepDom"/> Departamento domicilio
            </td>
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="LocDom"/> Localidad domicilio
            </td>
            <td>
                <input type="checkbox" name="lista"  value="DepNac"/> Departamento Nacimiento
            </td>
            <td>
                <input type="checkbox" name="lista"  value="LocNac"/> Localidad Nacimiento
            </td>
        </tr>
        <tr>
            
            <td>
                <input type="checkbox" name="lista" value="Medico"/> Médico
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Psico"/> Psicotécnica
            </td>
             <td>
                <input type="checkbox" name="lista"  value="Odont"/> Odontológico
            </td>
            
            
            
        </tr>
        <tr>
            
            <td>
                <input type="checkbox" name="lista"  value="EdFis"/> Ed. Física
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Mat"/> Matemática
            </td>
            <td>
                <input type="checkbox" name="lista"  value="IdEsp"/> Idioma Español
            </td>
            
        </tr>
        <tr>
            
            <td>
                <input type="checkbox" name="lista"  value="Hist"/> Historia
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Arrojo"/> Arrojo
            </td>
            
            <td>
                <input type="checkbox" name="lista"  value="ObsOdont"/> Obs. Odontológico
            </td>
            
        </tr>
         <tr>
             
            <td>
                <input type="checkbox" name="lista"  value="ObsMedico"/> Obs. Médico
            </td>
             <td>
                <input type="checkbox" name="lista"  value="ObsPsico"/> Obs. Psicotécnica
            </td>
            <td>
                <input type="checkbox" name="lista"  value="ObsEdFisica"/> Obs. Ed.Fisica
            </td>
            
        </tr>
        <tr>
            <td>
                <input type="checkbox" name="lista"  value="Obs"/> Observaciones de Ingreso
            </td>
            <td>
                <input type="checkbox" name="lista"  value="Promedio"/> Promedio
            </td>
        </tr>
    </table>
<h2 align="center">Seleccione el filtro que desea aplicar.</h2>

<table>
        
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
                <select name="filtrops" form="imprimir" onshow="mostrarPersonal();" onchange="mostrarPersonal();" id="filtrops">
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="S">SI</option>
                    <option value="N">NO</option>
                </select>
            </td>
        </tr>
        
        <tr id="filtropse" style="display: none">
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
        <tr style="display: none;">
            <td>
                 A cursar en el instituto:
            </td>
            <td>
                <select name="filtroanio_aprobado" form="imprimir" onchange="mostrarOrientacion(1);" id="anio">
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="5">Secundaria</option>
                    <option value="6">6to aprobado</option>
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