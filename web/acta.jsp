<%-- 
    Document   : acta
    Created on : 21-oct-2016, 15:33:31
    Author     : GCINCUNEGUI
--%>

<%@page import="Classes.ManejadorNotasBD"%>
<%@page import="Classes.Usuario"%>
<%@page import="Classes.ManejadorCodigoBD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Classes.ManejadorPostulanteDB"%>
<%@page import="Classes.RecordPostulanteNota"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<style>
    tr{
        page-break-inside: avoid;
        page-break-after: auto;
    }    
</style>
<script src="js/jquery-1.9.1.min.js"></script>
    <script>
        jQuery(document).ready(function($){
            var cajas=document.getElementsByName("nota");
            for(var i=0; i<cajas.length; i++){
                cajas[i].value =  cajas[i].valueAsNumber.toFixed(3)
            }
        });
    </script>
    <% 
    ManejadorCodigoBD mc = new ManejadorCodigoBD();
    HttpSession sesion= request.getSession();
    ManejadorNotasBD mn = new ManejadorNotasBD();
    int carrera =Integer.valueOf(request.getParameter("carrera"));
    int anio = ManejadorPostulanteDB.getAnioPostula();
    ArrayList<RecordPostulanteNota> an = mn.getNotas(carrera,anio);
    
    if (sesion.getAttribute("usuarioID")!=null){
        Usuario u= mc.getUsuario(Integer.valueOf(sesion.getAttribute("usuarioID").toString()));
        if(u.isAdmin()||u.isSuperAdmin()){%>

        <style>
            h4 {
                margin: 0px;
            }
            h2 {
                margin: 0px;
            };
        </style>

        <table style="width: 100%;">
            
            <tr>
                <td>
                    <h4><% if(carrera==1){out.print("CUERPO COMANDO");}else{out.print("APOYO DE SERVICIO Y COMBATE");}; %></h4>
                </td>
                <td style="text-align: right">
                    <h4>Toledo,
                        <%
                            java.util.Calendar fecha = java.util.Calendar.getInstance();
                            int mes = fecha.get(java.util.Calendar.MONTH)+1;
                            out.print(fecha.get(java.util.Calendar.DATE) + " de ");
                            switch(mes){
                                case 1: out.print("enero");break;
                                case 2: out.print("febrero");break;
                                case 3: out.print("marzo");break;
                                case 4: out.print("abril");break;
                                case 5: out.print("mayo");break;
                                case 6: out.print("junio");break;
                                case 7: out.print("julio");break;
                                case 8: out.print("agosto");break;
                                case 9: out.print("setiembre");break;
                                case 10: out.print("octubre");break;
                                case 11: out.print("noviembre");break;
                                case 12: out.print("diciembre");break;
                                                            
                            }  
                            out.print(" de "
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
                        Acta de examen de ingreso <%= anio %>
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
        
            <table style="width: 100%; text-align: center;font-size: 12px;page-break-inside: auto;" border="1" cellspacing="0" >
                <tr>
                    <td style="width: 5%">
                        <h4></h4>
                    </td>
                    <td style="width: 5%"> 
                        <h4>N&deg;</h4>
                    </td>
                    <td style="width: 15%">
                        <h4>Apellido</h4>
                    </td>
                    <td style="width: 10%">
                        <h4>Nombre</h4>
                    </td>

                    <td style="width: 10%">
                        <h4><% switch(materia){ case 1: case 2: case 3: case 4: out.print("Nota (1-10)");break; case 5: case 6: out.print("Resultados");};%> </h4>
                    </td>
                    <td <%
                        if(materia==4){
                            out.print("style=\"width: 13%;\" ");
                        }
                        else{
                            out.print("style=\"width: 25%;\" ");
                        }
                    %>>
                        <h4>Observaciones</h4>
                    </td>
                    <%
                        if(materia==4){
                            out.print("<td style=\"width: 2%;\"><h4>Arrojo</h4></td>");
                            out.print("<td style=\"width: 10%;\"><h4>Observaciones Médico</h4></td>");
                        }
                    %>
                    <td style="width: 30%">
                        <h4>FIRMAS</h4>
                    </td>
                </tr>
                <%
                    int i=1;
                    if(!an.isEmpty()){
                        for(RecordPostulanteNota n: an){
                            if(!n.nsp&&!n.renuncio){
                            out.print("<tr >"+
                                    "<td style='width:8%; text-align:center'>"+i+
                                "</td>"+
                                "<td style='width:8%; text-align:center'>"+n.id+
                                "</td>"+
                                "<td>"+n.primerApellido+
                                "</td>"+
                                "<td>"+n.primerNombre+
                               "</td>");
                                switch(materia){
                                    case 1: 
                                        out.print("</td>"+
                                        "<td><input name='nota' readonly type=number style='border:0;text-align:center;font-size: 12px;' size 10 value='");
                                        if(n.pd){
                                            out.print(n.notapd);
                                        }
                                        else{
                                            if(!n.reingreso){
                                                if(n.matematica!=0){
                                                    out.print(n.matematica);
                                                };
                                            }
                                        }
                                        out.print(
                                        "'/></td>"+
                                        "<td style=\"text-align: left\">");
                                        if(n.pd && n.matematicaObs.toUpperCase().indexOf("PD")==-1){
                                            out.print("PD ");
                                        }
                                        if(n.reingreso && n.matematicaObs.toUpperCase().indexOf("REINGRESO")==-1){
                                            out.print("REINGRESO ");
                                        }
                                        out.print(n.matematicaObs+
                                        "</td></tr>");break;
                                    case 2: 
                                        out.print("</td>"+
                                        "<td><input name='nota' readonly type=number style='border:0;text-align:center;font-size: 12px;' size 10 value='");
                                        if(n.pd){
                                            out.print(n.notapd);
                                        }
                                        else{
                                            if(!n.reingreso){
                                                if(n.historia!=0){
                                                    out.print(n.historia);
                                                };
                                            }
                                        }
                                        out.print(
                                        "'/></td>"+
                                        "<td style=\"text-align: left\">");
                                        if(n.pd && n.historiaObs.toUpperCase().indexOf("PD")==-1){
                                            out.print("PD ");
                                        }
                                        if(n.reingreso && n.historiaObs.toUpperCase().indexOf("REINGRESO")==-1){
                                            out.print("REINGRESO ");
                                        }
                                        out.print(n.historiaObs+
                                        "</td></tr>");break;
                                    case 3: 
                                        out.print("</td>"+
                                        "<td><input name='nota' readonly type=number style='border:0;text-align:center;font-size: 12px;' size 10 value='");
                                        if(n.pd){
                                            out.print(n.notapd);
                                        }
                                        else{
                                            if(!n.reingreso){
                                                if(n.idiomaEspaniol!=0){
                                                    out.print(n.idiomaEspaniol);
                                                };
                                            }
                                        }
                                        out.print(
                                        "'/></td>"+
                                        "<td style=\"text-align: left\">");
                                        if(n.pd && n.idiomaEspaniolObs.toUpperCase().indexOf("PD")==-1){
                                            out.print("PD ");
                                        }
                                        if(n.reingreso && n.idiomaEspaniolObs.toUpperCase().indexOf("REINGRESO")==-1){
                                            out.print("REINGRESO ");
                                        }
                                        out.print(n.idiomaEspaniolObs+
                                        "</td></tr>");break;
                                    case 4: 
                                        out.print("</td>"+
                                        "<td><input name='nota' readonly type=number style='border:0;text-align:center;font-size: 12px;' size 10 value='");
                                        if(n.educacionFisica!=0){
                                            out.print(n.educacionFisica);
                                        };
                                        out.print(
                                        "'/></td>"+
                                        "<td>"+n.edFisicaObs+
                                        "</td>"+
                                        "<td>");
                                        if(n.arrojo!=null){
                                            if(n.arrojo){
                                                out.print("S");
                                            }
                                            else{
                                                out.print("N");
                                            };
                                        }
                                        out.print("</td>"+
                                        "<td>"+n.medicoObs+
                                        "</td></tr>");break;
                                    case 5: 
                                        out.print("</td>"+
                                        "<td>");
                                        
                                            if(n.psicotecnica==2){
                                                out.print("No Apto");
                                            }
                                            else{
                                                if(n.psicotecnica==1){
                                                    if(n.psicoSeg){
                                                        out.print("Apto C/S");
                                                    }
                                                    else{
                                                        out.print("Apto");
                                                    }
                                                }
                                            }; 
                                        out.print("</td>"+
                                        "<td>"+n.psicoObs+
                                        "</td></tr>");break;
                                    case 6: 
                                        out.print("</td>"+
                                        "<td>");
                                        
                                            if(n.medico==2){
                                                out.print("No Apto");
                                            }
                                            else{
                                                if(n.medico==1){
                                                    if(n.medicoSeg){
                                                        out.print("Apto C/S");
                                                    }
                                                    else{
                                                        out.print("Apto");
                                                    }
                                                }
                                            }; 
                                        out.print("</td>"+
                                        "<td>"+n.medicoObs+
                                        "</td></tr>");break;
                                    case 7:
                                        out.print("</td>"+
                                        "<td>");
                                        
                                            if(n.odontologico==2){
                                                out.print("No Apto");
                                            }
                                            else{
                                                if(n.odontologico==1){
                                                    if(n.odontSeg){
                                                        out.print("Apto C/S");
                                                    }
                                                    else{
                                                        out.print("Apto");
                                                    }
                                                }
                                            }; 
                                        out.print("</td>"+
                                        "<td>"+n.odontObs+
                                        "</td></tr>");break;
                                }
                                i++;
                        }
                        }
                    
                    }
                    else{
                        an= mn.getPasesDirectosYReingresos(carrera, anio);
                        if(materia==4){
                            out.print("<h2 style='color:#FF0000'>DEBE INGRESAR RESULTADOS MÉDICOS</h2>");
                        }
                        for(RecordPostulanteNota n: an){
                            if(!n.nsp&&!n.renuncio){
                            out.print("<tr >"+
                                    "<td style='width:8%; text-align:center'>"+i+
                                "</td>"+
                                "<td style='width:8%; text-align:center'>"+n.id+
                                "</td>"+
                                "<td>"+n.primerApellido+
                                "</td>"+
                                "<td>"+n.primerNombre+
                               "</td>");
                                switch(materia){
                                    case 1: 
                                        out.print("</td>"+
                                        "<td name='nota'>");
                                        if(n.pd){
                                            out.print(n.notapd);
                                        }
                                        out.print(
                                        "</td>"+
                                        "<td style=\"text-align: left\">");
                                        if(n.pd ){
                                            out.print("PD ");
                                        }
                                        out.print("</td></tr>");break;
                                    case 2: 
                                        out.print("</td>"+
                                        "<td name='nota'>");
                                        if(n.pd){
                                            out.print(n.notapd);
                                        }
                                        out.print(
                                        "</td>"+
                                        "<td style=\"text-align: left\">");
                                        if(n.pd){
                                            out.print("PD ");
                                        }
                                        out.print("</td></tr>");break;
                                    case 3: 
                                        out.print("</td>"+
                                        "<td name='nota'>");
                                        if(n.pd){
                                            out.print(n.notapd);
                                        }
                                        out.print(
                                        "</td>"+
                                        "<td style=\"text-align: left\">");
                                        if(n.pd){
                                            out.print("PD ");
                                        }
                                        out.print("</td></tr>");break;
                                    case 4: //ed.fisica
                                        out.print("</td><td></td><td></td><td></td><td></td></tr>");break;
                                    case 5: case 6: case 7: //psico, medico, odont
                                        out.print("</td><td></td><td></td></tr>");break;
                                    
                                }
                                i++;
                        }
                        }
                    }
                         
                %>
            </table>
            
            <table style="width: 100%">
                    <tr>
                        <td style="width: 50%"></td>
                        <td>EL JEFE DE ESTUDIOS DE LA E.M.</td>
                    </tr>
                    <tr>
                        <td style="width: 50%"></td>
                        <td>MAY.</td>
                    </tr>
                    <tr>
                        <td style="width: 50%"></td>
                        <td style="padding-left: 4%"><input type="text" style="border:none;border-top: #000000 solid; text-align: center" value="" /></td>
                        
                    </tr>
            </table>
            <table style="width: 100%; padding-right: 40%;padding-top: 1% ">
                    <tr>
                        <td style="width: 40%"></td>
                        <td>EL PRESIDENTE DE LOS TRIBUNALES EXAMINADORES</td>
                    </tr>
                    <tr>
                        <td style="width: 40%"></td>
                        <td>EL SUBDIRECTOR DE LA E.M.</td>
                    </tr>
                    <tr>
                        <td style="width: 40%"></td>
                        <td>TTE.CNEL.</td>
                    </tr>
                    <tr>
                        <td style="width: 40%"></td>
                        <td style="padding-left: 11%"><input type="text" style="border:none;border-top: #000000 solid; text-align: center" value=""/></td>
                    </tr>
                </table>
        
        <%}else{
        response.sendRedirect("/listar.jsp");
        }
}
else{
    sesion.setAttribute("login", "vacio");
    response.sendRedirect("");
}%>
