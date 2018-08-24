<%@page import="Classes.RecordPostulanteHistorial"%>
<%@page import="Classes.ManejadorPostulanteDB"%>
<%@page import="Classes.ManejadorHistorialBD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Classes.Postulante"%>
<div id='dialog1' style="display:none" title="Filtro">
    <%@include file="filtroComando.jsp" %>
</div> 
<form method="post" target="_blank" onsubmit="return listar(this)" name="formComandoHistorial" id="formComandoHistorial" action='Listar?anio=<%= ManejadorPostulanteDB.getAnioPostula()-1 %>'>
    
    <table style="float: right">
        <tr>
            <td style="width: 55%"><h3 style="float: left; font-family: sans-serif" id="tituloComando">Cuerpo Comando Historial <%= ManejadorPostulanteDB.getAnioPostula()-1 %></h3></td>
            <td style="width: 15%"><a onclick='abrir_dialog(dialog1)' title="Aplicar filtro"><img width="35%" src='images/filtro_1.png' /></a> </td>
            <td style="width: 15%"><input type="image" width="30%" title="Imprimir fichas seleccionadas"src="images/imprimir.png" alt="Submit Form" /></td>
            
        </tr>
        
    </table>
    
    <table style="width: 100%;" id="datosComando">
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
                ArrayList<RecordPostulanteHistorial> ap = mp.getPostulantesListarHistorial(null,Integer.valueOf(sesion.getAttribute("usuarioID").toString()),1, ManejadorPostulanteDB.getAnioPostula()-1);
                sesion.setAttribute("listaTodosC", ap);
                sesion.setAttribute("filtroMostrarC", "");
                out.print("<tr style='background-color:#ffcc66'>");
                            out.print("<td style='width: 5%' align='center'><h3 style='margin:2%;'></h3></td>");
                            out.print("<td style='width: 5%' align='center'><input type='checkbox' onclick='seleccionar_todo_comando()' id='selTodoComando'></td>");
                            out.print("<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Nombres</h3></td>");
                            out.print("<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Apellidos</h3></td>");
                            out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Cédula</h3></td>");
                            out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Unidad Insc.</h3></td>");
                            out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>Promedio</h3></td>");
                out.print("</tr>" );
                int i=0;
                String color;
                for (RecordPostulanteHistorial h: ap){
                        if (h.entra==0){
                            color="#ACFA58";
                        }
                        else{
                            if(h.entra==2){
                                color="#F4FA58";
                            }
                            else{
                                color="#FA8258";
                            }
                        }
                        i++;

                       out.print("<tr style='background-color:"+color+"'>");
                       out.print("<td style='width: 5%' align='center'>"+i+"</td>");
                       out.print("<td style='width: 5%' align='center'><input type='checkbox' name='List[]' value='"+String.valueOf(h.p.getCi())+"' /></td>");
                       
                       out.print("<td style='width: 10%' align='center'>"+h.p.getPrimerNombre()+"</td>");
                        out.print("<td style='width: 10%' align='center'>"+h.p.getSegundoNombre()+"</td>");
                        out.print("<td style='width: 10%' align='center'>"+h.p.getPrimerApellido()+"</td>");
                        out.print("<td style='width: 10%' align='center'>"+h.p.getSegundoApellido()+"</td>");
                        out.print("<td style='width: 15%' align='center'>"+String.valueOf(h.p.getCi())+"</td>");
                        out.print("<td style='width: 15%' align='center'>"+h.p.getUnidadInsc().getNombreMostrar()+"</td>"); 
                        out.print("<td style='width: 10%' align='center'>"+h.promedio+"</td>"); 
                        out.print("</tr>");
                }
            %> 
                
            </table>
            
</form>