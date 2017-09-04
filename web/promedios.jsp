<%-- 
    Document   : promedios
    Created on : 30-nov-2016, 12:21:48
    Author     : gcincunegui
--%>

<%@page import="Classes.ManejadorPostulanteDB"%>
<%@page import="Classes.Postulante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Classes.ManejadorSeleccionBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

    <% if(u.isSuperAdmin()){%>
    <script>
        function confirmacion(f){
            var r=confirm("¿Seguro que desea guardar los cambios?");
            if (r==true)
            {
                f.submit();
                return true;
            }
            else{
                return false;
            }
        }
    </script>
    <style>
        h4 {
            margin: 0px;
        }
        h2 {
            margin: 0px;
        };
    </style>
    <p id="mensaje" style="color: #ffffff"><% if(session.getAttribute("Mensaje")!=null){out.print("<img src='images/icono-informacion.png' width='3%' /> &nbsp;&nbsp;"+session.getAttribute("Mensaje"));}%></p>
    <%
        session.setAttribute("Mensaje",null);
    %>
    <table style="width: 100%;">
        <tr>
            <td style="text-align: right">
                <h4>Toledo,
                    <%
                        java.util.Calendar fecha = java.util.Calendar.getInstance();
                        int mes = fecha.get(java.util.Calendar.MONTH)+1;
                        out.print(fecha.get(java.util.Calendar.DATE) + "/"
                          +   mes  + "/"
                          + fecha.get(java.util.Calendar.YEAR));
                    %>
                </h4>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <h2>
                    ESCUELA MILITAR
                </h2>
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <h4>
                    PROMEDIOS <%= ManejadorPostulanteDB.getAnioPostula() %>
                </h4>
            </td>
        </tr>
    </table>
    <%
        ManejadorSeleccionBD ms = new ManejadorSeleccionBD();
        
        ArrayList<Postulante> p1 = ms.getPostulantesSeleccion(4, u.getId(), 1);
        ArrayList<Postulante> p2 = ms.getPostulantesSeleccion(4, u.getId(), 2);
    %>  
    <h2>Cuerpo Comando</h2>
    <form method="post" onsubmit="return confirmacion(this);" action='Promedios?carrera=1&auto=false' >
        <table style="width: 100%; background-color: #CDCDCD; " border="1" >
            <tr>
                <td>
                    <h4>N&deg;</h4>
                </td>
                <td>
                    <h4>Apellido</h4>
                </td>
                <td>
                    <h4>Nombre</h4>
                </td>

                <td>
                    <h4>Promedio(1-10)</h4>
                </td>
            </tr>
            <%
                int i=0;
                String s;
                
                for(Postulante p: p1){
                    if(p.getId()==-1){
                        s="COND.";
                    }
                    else{
                       s= String.valueOf(p.getId());
                    }
                    out.print("<tr>"+
                        "<td style='width:8%; text-align:center'>"+s+
                        "</td>"+
                        "<td style='display:none'><input style='background-color: #CDCDCD;border-width: 0px;width:90%' name='ci[]' type= namber value='"+p.getCi()+
                        "'hidden='hidden'/></td>"+
                        "<td>"+p.getPrimerApellido()+
                        "</td>"+
                        "<td>"+p.getPrimerNombre()+
                       "</td><td><input name='promedios[]' type=number type=number min='1.00' max='10.00' step='0.001' value='"+p.getPromedio()+"'/></td>");
                        i++;
                }                
            %>
            <input name="i" type="number" value="<%= i%>" hidden="hidden"/>
        </table>
        <p align="right"><input type="submit" value="Modificar"/></p>
    </form>
    <h2>Apoyo de Servicio y Combate</h2>
    <form method="post" onsubmit="return confirmacion(this);" action='Promedios?auto=false' >
        <table style="width: 100%; background-color: #CDCDCD; " border="1" >
            <tr>
                <td>
                    <h4>N&deg;</h4>
                </td>
                <td>
                    <h4>Apellido</h4>
                </td>
                <td>
                    <h4>Nombre</h4>
                </td>

                <td>
                    <h4>Promedio(1-10)</h4>
                </td>
            </tr>
            <%
                i=0;
                
                for(Postulante p: p2){
                    if(p.getId()==-1){
                        s="COND.";
                    }
                    else{
                       s= String.valueOf(p.getId());
                    }
                    out.print("<tr>"+
                        "<td style='width:8%; text-align:center'>"+s+
                        "</td>"+
                        "<td style='display:none'><input style='background-color: #CDCDCD;border-width: 0px;width:90%' name='ci[]' type= namber value='"+p.getCi()+
                        "'hidden='hidden'/></td>"+
                        "<td>"+p.getPrimerApellido()+
                        "</td>"+
                        "<td>"+p.getPrimerNombre()+
                       "</td> <td><input name='promedios[]' type=number type=number min='1.00' max='10.00' step='0.001' value='"+p.getPromedio()+"'/></td>");
                        i++;
                }                
            %>
            <input name="i" type="number" value="<%= i%>" hidden="hidden"/>
        </table>
        <p align="right"><input type="submit" value="Modificar"/></p>
    </form>
        
    <%}else{
    response.sendRedirect("/listar.jsp");
    }%>
<%@ include file="footer.jsp" %> 