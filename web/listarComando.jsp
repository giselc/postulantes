<%@page import="java.util.ArrayList"%>
<%@page import="Classes.Postulante"%>
<%@page import="Classes.ManejadorPostulanteDB"%>
<div id='dialog1' style="display:none" title="Filtro">
    <%@include file="filtroComando.jsp" %>
</div>
<%
ManejadorPostulanteDB mp = new ManejadorPostulanteDB();
%>    

<form method="post" target="_blank"  name="formComando"  action="Listar?anio=<%= ManejadorPostulanteDB.getAnioPostula()%>">
    
    <table style="float: right">
        <tr>
            <td style="width: 40%"><h3 style="float: left; font-family: sans-serif">Cuerpo Comando <%= ManejadorPostulanteDB.getAnioPostula() %></h3></td>
            <td style="width: 15%"><a onclick='sePuedeAgregar(<%= mp.existeDatosAniosAnteriores() %>,1)' title="Agregar Comando"><img width="30%" src='images/agregarLista.png' /></a> </td>
            <td style="width: 15%"><a onclick='abrir_dialog(dialog1)' title="Aplicar filtro"><img width="35%" src='images/filtro_1.png' /></a> </td>
            <td style="width: 15%"><input type="image" width="30%" title="Imprimir fichas seleccionadas" src="images/imprimir.png" /></td>
       <% 
       if(u.getId()==2){    
       %>
            <td style="width: 15%"><input type="image" formaction="PasarCarrera" width="30%" title="Pasar de carrera" src="images/cambiarCarrera.png"  /></td>
         <%       
        }
       %>
            
        </tr>
    </table>
    
    <table style="width: 100%;">
        <tr>
            <td colspan="8">
                <p style="font-size: 70%" id="filtroTexto"></p>
            </td>   

        </tr>
        <tr>
            <td>
                <input name="carreraListar" hidden="hidden" value="Comando"/>
            </td>
        </tr>
            <%
                ArrayList<Postulante> ap = mp.getPostulantesListar(null,Integer.valueOf(sesion.getAttribute("usuarioID").toString()),1, ManejadorPostulanteDB.getAnioPostula());
                sesion.setAttribute("listaTodosC", ap);
                sesion.setAttribute("filtroMostrarC", "");
                out.print("<tr style='background-color:#ffcc66'>");
                            out.print("<td style='width: 5%' align='center'><h3 style='margin:2%;'></h3></td>");
                            out.print("<td style='width: 5%' align='center'><input type='checkbox' onclick='seleccionar_todo_comando()' id='selTodo'></td>");
                            out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>Número</h3></td>");
                            out.print("<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Nombres</h3></td>");
                            out.print("<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Apellidos</h3></td>");
                            out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Cédula</h3></td>");
                            out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Unidad Insc.</h3></td>");
                            out.print("<td style='width: 10%' align='center'></td>");
                out.print("</tr></table>  <table id=\"datosComando\"style=\"width: 100%;\">" );
                int i=0;
                String color;
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