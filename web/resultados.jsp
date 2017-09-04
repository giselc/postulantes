<%@page import="Classes.RecordPostulanteNota"%>
<%@page import="Classes.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Classes.ManejadorPostulanteDB"%>

<%@ include file="header.jsp" %>

    <% if(u.isAdmin()){%>
    <script src="js/jquery-1.9.1.min.js"></script>
    <script>
        jQuery(document).ready(function($){
            var cajas=document.getElementsByName("notas[]");
            for(var i=0; i<cajas.length; i++){
                cajas[i].value =  cajas[i].valueAsNumber.toFixed(3)
            }
        });
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
    <table style="width: 100%; background-color: #CDCDCD">
        <% int carrera =Integer.valueOf(request.getParameter("carrera")); %>
        <tr>
            <td>
                <h4><% if(carrera==1){out.print("CUERPO COMANDO");}else{out.print("APOYO DE SERVICIO Y COMBATE");}; %></h4>
            </td>
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
                    Acta de Examen de Ingreso <%= ManejadorPostulanteDB.getAnioPostula() %>
                </h4>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <h4>
                    EXAMEN DE: <%
                    int materia=Integer.valueOf(request.getParameter("id"));
                    
                    switch (materia){
                        case 1: out.print("MATEMÁTICA"); break;
                        case 2: out.print("HISTORIA"); break;
                        case 3: out.print("IDIOMA ESPAÑOL"); break;
                        case 4: out.print("EDUCACION FÍSICA"); break;
                        case 5: out.print("PSICOTÉCNICA"); break;
                        case 6: out.print("MÉDICO"); break;
                        case 7: out.print("ODONTOLÓGICO"); break;
                    } 
                    %>
                </h4>
            </td>
        </tr>
    </table>
    <%
        ManejadorNotasBD mn = new ManejadorNotasBD();
        int anio = ManejadorPostulanteDB.getAnioPostula();
        ArrayList<RecordPostulanteNota> an = mn.getNotas(carrera,anio);
    %>  
    
    <form method="post" onsubmit="return confirmacion(this);" action='Notas?carrera=<% out.print(carrera); %>&materia=<% out.print(materia); %>' >
        <input name="materia" type="number" <%out.print(" value=\""+materia+"\"");%> hidden="hidden"/>
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
                    <h4><% switch(materia){ case 1: case 2: case 3: case 4: out.print("Nota (1-10)");break; case 5: case 6: out.print("Resultados");};%></h4>
                </td>
                <td>
                    <h4>Observaciones</h4>
                </td>
                <%
                    if(materia==4){
                        out.print("<td><h4>Arrojo</h4></td>");
                        out.print("<td><h4>Observaciones Médico</h4></td>");
                    }
                %>
            </tr>
            <%
                int i=0;
                if(!an.isEmpty()){
                    for(RecordPostulanteNota n: an){
                        out.print("<tr >"+
                            "<td style='width:8%; text-align:center'>"+n.id+
                            "</td>"+
                            "<td style='display:none'><input style='background-color: #CDCDCD;border-width: 0px;width:90%' name='ci[]' type= namber value='"+n.ci+
                            "'hidden='hidden'/></td>"+
                            "<td>"+n.primerApellido+
                            "</td>"+
                            "<td>"+n.primerNombre+
                           "</td>");
                            switch(materia){
                                case 1: 
                                    out.print("</td>"+
                                    "<td><input name='notas[]' type=number min='0.00' max='10.00' step='0.001'");
                                    if(n.pd || n.reingreso){
                                        if(n.reingreso){
                                            out.print("readonly value='0");
                                        }
                                        else{
                                            out.print("readonly value='"+n.notapd);
                                        }
                                    }else{
                                        if(n.matematica<1){
                                            out.print(" value='0");
                                        }
                                        else{
                                            out.print(" value='"+n.matematica);
                                        }
                                    }
                                    out.print("' /></td>"+
                                    "<td><input name='observaciones[]' type=text value='");
                                    if(!n.matematicaObs.toUpperCase().contains("REINGRESO") && n.reingreso){
                                        out.print("REINGRESO ");
                                    }
                                    if(!n.matematicaObs.toUpperCase().contains("PD") && !n.matematicaObs.toUpperCase().contains("P.D")  && n.pd){
                                        out.print("PD ");
                                    }
                                    out.print(n.matematicaObs);
                                    out.print("'/></td></tr>");break;
                                case 2: 
                                    out.print("</td>"+
                                    "<td><input name='notas[]' type=number min='0.00' max='10.00' step='0.001'");
                                    if(n.pd || n.reingreso){
                                        if(n.reingreso){
                                            out.print("readonly value='0");
                                        }
                                        else{
                                            out.print("readonly value='"+n.notapd);
                                        }
                                    }else{
                                        out.print("value='"+n.historia);
                                    }
                                    out.print("' /></td>"+
                                    "<td><input name='observaciones[]' type=text value='");
                                    if(!n.historiaObs.contains("REINGRESO") && n.pd){
                                        out.print("REINGRESO ");
                                    }
                                    if(!n.historiaObs.contains("PD") && n.pd){
                                        out.print("PD ");
                                    }
                                    out.print(n.historiaObs);
                                    out.print("'/></td></tr>");break;
                                case 3: 
                                    out.print("</td>"+
                                    "<td><input name='notas[]' type=number min='0.00' max='10.00' step='0.001'" );
                                    if(n.pd || n.reingreso){
                                        if(n.reingreso){
                                            out.print("readonly value='0");
                                        }
                                        else{
                                            out.print("readonly value='"+n.notapd);
                                        }
                                    }else{
                                        out.print("value='"+n.idiomaEspaniol);
                                    }
                                    out.print("' /></td>"+
                                    "<td><input name='observaciones[]' type=text value='");
                                    if(!n.idiomaEspaniolObs.contains("REINGRESO") && n.reingreso){
                                        out.print("REINGRESO ");
                                    }
                                    if(!n.idiomaEspaniolObs.contains("PD") && n.pd){
                                        out.print("PD ");
                                    }
                                    out.print(n.idiomaEspaniolObs);
                                    out.print("'/></td></tr>");break;
                                case 4: 
                                    out.print("</td>"+
                                    "<td><input name='notas[]' type=number min='0.00' max='10.00' step='0.001' value='"+n.educacionFisica+
                                    "' /></td>"+
                                    "<td><input name='observaciones[]' type=text value='"+n.edFisicaObs+
                                    "'/></td>"+
                                    "<td><input name='arrojo"+i+"' type=checkbox ");
                                    if(n.arrojo==null || n.arrojo){
                                        out.print("checked='checked'");
                                    };
                                    out.print("/></td>"+
                                    "<td>"+n.medicoObs+
                                    "</td></tr>");break;
                                case 5: 
                                    out.print("<td><table style='width:100%'><tr>"+
                                        "<td style='padding-right:5%'>Apto</td>" +
                                        "<td style='padding-right:5%'>No apto</td>"+
                                        "<td style='padding-right:5%'>Apto C/S</td>"+
                                        
                                    "</tr><tr>"+
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"3\"");if(n.psicotecnica!=2 || (n.psicotecnica==1 && !n.psicoSeg)){out.print("checked='checked'");};out.print(" /></td>" +
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"1\"");if(n.psicotecnica==2){out.print("checked='checked'");};out.print(" /></td>"+
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"2\"");if(n.psicotecnica==1 && n.psicoSeg){out.print("checked='checked'");};out.print(" /></td>"+
                                        
                                        
                                    "</tr></table></td>"+
                                    "<td><input name='observaciones[]' type=text value='"+n.psicoObs+
                                    "'/></td></tr>");break;
                                case 6: 
                                   out.print("<td><table style='width:100%'><tr>"+
                                        "<td style='padding-right:5%'>Apto</td>" +
                                        "<td style='padding-right:5%'>No apto</td>"+
                                        "<td style='padding-right:5%'>Apto C/S</td>"+
                                        
                                    "</tr><tr>"+
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"3\"");if(n.medico!=2 || (n.medico==1 && !n.medicoSeg)){out.print("checked='checked'");};out.print(" /></td>" +
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"1\"");if(n.medico==2){out.print("checked='checked'");};out.print(" /></td>"+
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"2\"");if(n.medico==1 && n.medicoSeg){out.print("checked='checked'");};out.print(" /></td>"+
                                        
                                        
                                    "</tr></table></td>"+
                                    "<td><input name='observaciones[]' type=text value='"+n.medicoObs+
                                    "'/></td></tr>");break;
                                case 7:
                                    out.print("<td><table style='width:100%'><tr>"+
                                        "<td style='padding-right:5%'>Apto</td>" +
                                        "<td style='padding-right:5%'>No apto</td>"+
                                        "<td style='padding-right:5%'>Apto C/S</td>"+
                                        
                                    "</tr><tr>"+
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"3\"");if(n.odontologico!=2 || (n.odontologico==1 && !n.odontSeg)){out.print("checked='checked'");};out.print(" /></td>" +
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"1\"");if(n.odontologico==2){out.print("checked='checked'");};out.print(" /></td>"+
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"2\"");if(n.odontologico==1 && n.odontSeg){out.print("checked='checked'");};out.print(" /></td>"+
                                        
                                        
                                    "</tr></table></td>"+
                                    "<td><input name='observaciones[]' type=text value='"+n.odontObs+
                                    "'/></td></tr>");break;
                            }
                            i++;
                    } 
                }
                else{
                    an= mn.getPasesDirectosYReingresos(carrera, anio);
                    for(RecordPostulanteNota n: an){
                        out.print("<tr >"+
                            "<td style='width:8%; text-align:center'>"+n.id+
                            "</td>"+
                            "<td style='display:none'><input style='background-color: #CDCDCD;border-width: 0px;width:90%' name='ci[]' type= namber value='"+n.ci+
                            "'hidden='hidden'/></td>"+
                            "<td>"+n.primerApellido+
                            "</td>"+
                            "<td>"+n.primerNombre+
                           "</td>");
                            switch(materia){
                                case 1: 
                                    out.print("</td>"+
                                    "<td><input name='notas[]' type=number min='0.00' max='10.00' step='0.001'");
                                    if(n.pd || n.reingreso){
                                        if(n.reingreso){
                                            out.print("readonly value='0");
                                        }
                                        else{
                                            out.print("readonly value='"+n.notapd);
                                        }
                                    }
                                    else{
                                         out.print(" value='0");
                                    }
                                    out.print("' /></td>"+
                                    "<td><input name='observaciones[]' type=text value='");
                                    if (n.reingreso){
                                        out.print("REINGRESO");
                                    }
                                    if (n.pd){
                                        out.print("PD");
                                    }
                                    out.print("'/></td></tr>");break;
                                case 2: 
                                    out.print("</td>"+
                                    "<td><input name='notas[]' type=number min='0.00' max='10.00' step='0.001'");
                                    if(n.pd || n.reingreso){
                                        if(n.reingreso){
                                            out.print("readonly value='0");
                                        }
                                        else{
                                            out.print("readonly value='"+n.notapd);
                                        }
                                    }
                                    else{
                                         out.print(" value='0");
                                    }
                                    out.print("' /></td>"+
                                    "<td><input name='observaciones[]' type=text value='");
                                    if (n.reingreso){
                                        out.print("REINGRESO");
                                    }
                                    if (n.pd){
                                        out.print("PD");
                                    }
                                    out.print("'/></td></tr>");break;
                                case 3: 
                                    out.print("</td>"+
                                    "<td><input name='notas[]' type=number min='0.00' max='10.00' step='0.001'" );
                                    if(n.pd || n.reingreso){
                                        if(n.reingreso){
                                            out.print("readonly value='0");
                                        }
                                        else{
                                            out.print("readonly value='"+n.notapd);
                                        }
                                    }
                                    out.print("' /></td>"+
                                    "<td><input name='observaciones[]' type=text value='");
                                    if (n.reingreso){
                                        out.print("REINGRESO");
                                    }
                                    if (n.pd){
                                        out.print("PD");
                                    }
                                    out.print("'/></td></tr>");break;
                                case 4: 
                                    out.print("</td>"+
                                    "<td><input name='notas[]' type=number min='0.00' max='10.00' step='0.001' value='"+n.educacionFisica+
                                    "' /></td>"+
                                    "<td><input name='observaciones[]' type=text value=''/></td>"+
                                    "<td><input name='arrojo"+i+"' type=checkbox /></td>"+
                                    "<td></td></tr>");break;
                                case 5: case 6: case 7:
                                    out.print("<td><table style='width:100%'><tr>"+
                                        "<td style='padding-right:5%'>Apto</td>" +
                                        "<td style='padding-right:5%'>No apto</td>"+
                                        "<td style='padding-right:5%'>Apto C/S</td>"+
                                    "</tr><tr>"+
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"3\" checked='checked'");out.print(" /></td>" +
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"1\"");out.print(" /></td>"+
                                        "<td style='padding-right:5%'><input type=radio name=\"notas["+i+"][]\" value=\"2\"");out.print(" /></td>"+
                                        
                                    "</tr></table></td>"+
                                    "<td><input name='observaciones[]' type=text value=''/></td></tr>");break;
                               
                            }
                            i++;
                    }
                }
            %>
            <input name="i" type="number" value="<%= i%>" hidden="hidden"/>
        </table>
        <p align="right"><input type="submit" value="Aceptar"/></p>
    </form>
        
    <%}else{
    response.sendRedirect("/listar.jsp");
    }%>
<%@ include file="footer.jsp" %> 