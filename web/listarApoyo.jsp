<%-- 
    Document   : listarApoyo
    Created on : Jul 22, 2016, 6:39:15 PM
    Author     : Gisel
--%>
<%@page import="Classes.ManejadorPostulanteDB"%>
<div id='dialog' style="display:none" title="Filtro">
    <%@include file="filtroApoyo.jsp" %>
</div>
<form method="post" target="_blank" onsubmit="return listar(this)" name="formApoyo" action='Listar?anio=<%= ManejadorPostulanteDB.getAnioPostula()%>'>
    
    
    <table style="float: right;">
        <tr>
            <td style="width: 55%"><h3 style="float: left; font-family: sans-serif">Apoyo de Servicio y Combate <%= ManejadorPostulanteDB.getAnioPostula() %></h3></td>
            <td style="width: 15%"><a onclick='sePuedeAgregar(<%= mp.existeDatosAniosAnteriores() %>,2)' title="Agregar Apoyo"><img width="30%" src='images/agregarLista.png' /></a> </td>
            <td style="width: 15%"><a onclick='abrir_dialog(dialog)' title="Aplicar filtro"><img width="35%" src='images/filtro_1.png' /></a> </td>
            <td style="width: 15%"><input type="image" width="30%" title="Imprimir fichas seleccionadas"src="images/imprimir.png" alt="Submit Form" /></td>
        </tr>
    </table>
<table style="width: 100%;">
    
        <tr>
            <td colspan="8">
                <p style="font-size: 70%" id="filtroTextoApoyo"></p>
            </td>   

        </tr>
        <tr>
            <td>
                <input name="carreraListar" hidden="hidden" value="Apoyo"/>
            </td>
        </tr>
            <%
                mp = new ManejadorPostulanteDB();
                ap = mp.getPostulantesListar(null,Integer.valueOf(sesion.getAttribute("usuarioID").toString()),2, ManejadorPostulanteDB.getAnioPostula());
                sesion.setAttribute("listaTodosA", ap);
                sesion.setAttribute("filtroMostrarA", "");
                out.print("<tr style='background-color:#ffcc66'>");
                            out.print("<td style='width: 5%' align='center'><h3 style='margin:2%;'></h3></td>");
                            out.print("<td style='width: 5%' align='center'><input type='checkbox' onclick='seleccionar_todo_apoyo()' id='selTodoApoyo'></td>");
                            out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>Número</h3></td>");
                            out.print("<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Nombres</h3></td>");
                            out.print("<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Apellidos</h3></td>");
                            out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Cédula</h3></td>");
                            out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Unidad Insc.</h3></td>");
                            out.print("<td style='width: 10%' align='center'></td>");
                out.print("</tr></table>  <table id=\"datosApoyo\"style=\"width: 100%;\">" );
                i=0;
                for (Postulante p: ap){
                        if ((i%2)==0){
                            color=" #ccccff";
                        }
                        else{
                            color=" #ffff99";
                        }
                        i++;

                       out.print("<tr style='background-color:"+color+"'>");
                       out.print("<td style='width: 5%' align='center'>"+i+"</td>");
                       out.print("<td style='width: 5%' align='center'><input type='checkbox' name='List[]' value='"+String.valueOf(p.getCi())+"' /></td>");
                        if (p.getId()!=-1){
                            out.print("<td style='width: 10%' align='center'>"+p.getId()+"</td>");
                        }
                        else{
                            out.print("<td style='width: 10%' align='center'>COND.</td>");
                        }
                        out.print("<td style='width: 10%' align='center'>"+p.getPrimerNombre()+"</td>");
                        out.print("<td style='width: 10%' align='center'>"+p.getSegundoNombre()+"</td>");
                        out.print("<td style='width: 10%' align='center'>"+p.getPrimerApellido()+"</td>");
                        out.print("<td style='width: 10%' align='center'>"+p.getSegundoApellido()+"</td>");
                        out.print("<td style='width: 15%' align='center'>"+String.valueOf(p.getCi())+"</td>");
                        out.print("<td style='width: 15%' align='center'>"+p.getUnidadInsc().getNombreMostrar()+"</td>"); 
                        out.print("<td style='width: 10%' align='center'><a href='agregar.jsp?ci="+String.valueOf(p.getCi())+"'><img src='images/ver.png' width='25%' /></a></td>");
                        out.print("</tr>");
                }
            %> 
               
            </table>
            
</form>