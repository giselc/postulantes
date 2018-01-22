<%@page import="Classes.Departamento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Classes.Usuario"%>
<%@page import="Classes.ManejadorCodigoBD"%>
<form method="post" onsubmit='return aplicarFiltro(this,"datosComando")' id="formComandoFiltro" action="">
    <table style="font-size: 70%">
        <tr>
            <td>
                LMGA:
            </td>
            <td>
                <select name="lmga" form="formComandoFiltro"> 
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="S">SI</option>
                    <option value="N">NO</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                 A cursar en el instituto:
            </td>
            <td>
                <select name="anio_aprobado" form="formComandoFiltro" onchange="mostrarOrientacion(1);" id="anio">
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="5">Secundaria</option>
                    <option value="6">6to aprobado</option>
                </select>
            </td>
        </tr>
        <tr id="orient" style="display: none">
            <td>
                Orientaci&oacute;n:
            </td>
            <td>
                <select name="orientacion" form="formComandoFiltro">
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="SH">Social-Human&iacute;stico</option>
                    <option value="SE">Social-Econ&oacute;mico</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                Sexo:
            </td>
            <td>
                <select name="sexo" form="formComandoFiltro">
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="M">Masculino</option>
                    <option value="F">Femenino</option>
                </select>
            </td>
        </tr>
        
        <%
            if (u.isAdmin()){
                %>
                <tr>
                    <td>
                        Unidad Inscriptora:
                    </td>
                    <td>
                        <select name="unidadInsc" multiple="multiple" form="formComandoFiltro">
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
                        <select name="alojamiento" form="formComandoFiltro"> 
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
                        <select name="nsp" form="formComandoFiltro"> 
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
                        <select name="renuncio" form="formComandoFiltro"> 
                            <option value="todos" selected="selected">TODOS</option>
                            <option value="S">SI</option>
                            <option value="N">NO</option>
                        </select>
                    </td>
                </tr>
                 <%
                    }

        %>
        <tr>
            <td>
                Departamento Nacimiento:
            </td>
            <td>
                <select name="depNac" multiple="multiple" form="formComandoFiltro">
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
                <select name="depDom" multiple="multiple" form="formComandoFiltro">
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
                <select name="ps" form="formComandoFiltro" onchange="mostrarPersonal(1);" id="ps">
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="S">SI</option>
                    <option value="N">NO</option>
                </select>
            </td>
        </tr>
        <tr id="pse" style="display: none">
            <td>
                Personal subalterno de Ej&eacute;rcito:
            </td>
            <td>
                <select name="psEjercito" form="formComandoFiltro">
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="S">SI</option>
                    <option value="N">NO</option>
                </select>
            </td>
        </tr>
        <tr id="hijos" style="display: none">
            <td>
                Cantidad de hijos:
            </td>
            <td>
                <select name="canthijos" multiple="multiple" form="formComandoFiltro">
                    <option value="1">1</option>
                    <option value="2">2</option>
                     <option value="3">3</option>
                      <option value="4">+ de 3</option>
                </select>
            </td>
        </tr>
        
        <tr style="display: none">
            <td>
                Condicional:
            </td>
            <td>
                <select name="condicional" form="formComandoFiltro"> 
                    <option value="todos" selected="selected">TODOS</option>
                    <option value="S">SI</option>
                    <option value="N">NO</option>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right">
               <input type="submit" value="Aplicar" onclick='cerrar_dialog(dialog1)'/>
            </td>
        </tr>
    </table>
</form>