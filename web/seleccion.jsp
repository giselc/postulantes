<%-- 
    Document   : seleccion
    Created on : 24-nov-2016, 7:59:33
    Author     : gcincunegui
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Classes.ManejadorSeleccionBD"%>
<%@page import="Classes.Postulante"%>

<%@ include file="header.jsp" %>
 <% if(u.isSuperAdmin()){%>
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script>
        function alertaSesion(){
            alert("RECUERDE GUARDAR CAMBIOS periódicamente.\nSi se le cierra la sesión, puede perder todo. ")
        }
        $( document ).ready(function() {
            var tablaContadores= document.getElementById("contadores");
            var i= document.getElementById("NoEntran").rows.length-1;
            var i1 = document.getElementById("Entran").rows.length-1;
            var i2 = document.getElementById("ListaEspera").rows.length-1;
            var i3 = document.getElementById("CasosAnalizar").rows.length-1;
            tablaContadores.rows[1].cells[2].innerText= i;
            tablaContadores.rows[1].cells[0].innerText= i1;
            tablaContadores.rows[1].cells[1].innerText= i2;
            tablaContadores.rows[1].cells[3].innerText= i3;
            tablaContadores.rows[2].cells[0].innerText= "TOTAL: "+(i+i1+i2+i3);
            alertaSesion();
        });
        function abrir_dialog(dialog) {
          $(dialog).dialog({
              modal: true
          });
        };
        function actualizarPrecedencia(){
            var tableEntra = document.getElementById("Entran");
            var len=tableEntra.rows.length;
            for(var i=1; i<len;i++){
               tableEntra.rows[i].cells[0].firstChild.value = i;
            }
        }
        function actualizarContadores(){
            var tablaContadores= document.getElementById("contadores");
            tablaContadores.rows[1].cells[2].innerText= document.getElementById("NoEntran").rows.length-1;
            tablaContadores.rows[1].cells[0].innerText= document.getElementById("Entran").rows.length-1;
            tablaContadores.rows[1].cells[1].innerText= document.getElementById("ListaEspera").rows.length-1;
            tablaContadores.rows[1].cells[3].innerText= document.getElementById("CasosAnalizar").rows.length-1;
        }
        function pasarCasosAnalizarANoEntran(){
            var tableAnalizar = document.getElementById("CasosAnalizar");
            var len=tableAnalizar.rows.length;
            for(var i=1; i<len;i++){
               tableAnalizar.rows[1].cells[2].firstChild.checked = "checked";
               agregarALista(tableAnalizar.rows[1].cells[2].firstChild,tableAnalizar.rows[1].cells[8].innerText );
            }
            actualizarContadores();
        }
        function agregarALista(input,ci){
            var tr = document.getElementById(ci);
            var tableEntra = document.getElementById("Entran");
            var tableListaEspera=document.getElementById("ListaEspera");
            var tableNoEntran=document.getElementById("NoEntran");
            var tableAnalizar=document.getElementById("CasosAnalizar");
            if(input.value == 0){
                var j=parseFloat(tr.cells[9].innerText);//promedio
                var len=tableEntra.rows.length;
                for(var i=0; i<len;i++){
                    if(i!=0){
                        if(parseFloat(tableEntra.rows[i].cells[9].innerText)<j){//cells[9]=promedio
                            tableEntra.rows[i].before(tr);
                            break;
                        }
                        if(parseFloat(tableEntra.rows[i].cells[9].innerText)==j){
                            if(tableListaEspera.rows[i].cells[5].innerText!="COND."&& parseFloat(tableEntra.rows[i].cells[5].innerText)<j){ //cells[5]=id
                                tableEntra.rows[i].before(tr);
                                break;
                            }
                            else{
                                tableNoEntran.rows[i].after(tr);
                                break;
                            }
                        }
                    }
                }
                if(i==len){
                    tableEntra.appendChild(tr);
                }
            }
            else{
                if(input.value == 2){
                    tr.cells[0].firstChild.value = 0;
                    var j=parseFloat(tr.cells[9].innerText);//promedio
                    var len=tableListaEspera.rows.length;
                    for(var i=0; i<len;i++){
                        if(i!=0){
                            if(parseFloat(tableListaEspera.rows[i].cells[9].innerText)<j){//cells[9]=promedio
                                tableListaEspera.rows[i].before(tr);
                                break;
                            }
                            if(parseFloat(tableListaEspera.rows[i].cells[9].innerText)==j){
                                if(tableListaEspera.rows[i].cells[5].innerText!="COND." && parseFloat(tableListaEspera.rows[i].cells[5].innerText)<j){ //cells[5]=id
                                    tableListaEspera.rows[i].before(tr);
                                    break;
                                }
                                else{
                                    tableNoEntran.rows[i].after(tr);
                                    break;
                                }
                            }
                        }
                    }
                    if(i==len){
                        tableListaEspera.appendChild(tr);
                    }                       
                }
                else{
                    if(input.value == 1){
                        tr.cells[0].firstChild.value = 0;
                        var j=parseFloat(tr.cells[9].innerText);//promedio
                        var len=tableNoEntran.rows.length;
                        for(var i=0; i<len;i++){
                            if(i!=0){
                                if(parseFloat(tableNoEntran.rows[i].cells[9].innerText)<j){//cells[9]=promedio
                                    tableNoEntran.rows[i].before(tr);
                                    break;
                                }
                                if(parseFloat(tableNoEntran.rows[i].cells[9].innerText)==j){
                                    if(tableNoEntran.rows[i].cells[5].innerText!="COND." && parseFloat(tableNoEntran.rows[i].cells[5].innerText)<j){ //cells[5]=id
                                        tableNoEntran.rows[i].before(tr);
                                        break;
                                    }
                                    else{
                                        tableNoEntran.rows[i].after(tr);
                                        break;
                                    }
                                }
                            }
                        }
                        if(i==len){
                            tableNoEntran.appendChild(tr);
                        }   
                    }
                    else{
                        if(input.value == 3){
                            tr.cells[0].firstChild.value = 0;
                            var j=parseFloat(tr.cells[9].innerText);//promedio
                            var len=tableAnalizar.rows.length;
                            for(var i=0; i<len;i++){
                                if(i!=0){
                                    if(parseFloat(tableAnalizar.rows[i].cells[9].innerText)<j){//cells[9]=promedio
                                        tableAnalizar.rows[i].before(tr);
                                        break;
                                    }
                                    if(parseFloat(tableAnalizar.rows[i].cells[9].innerText)==j){
                                        if(tableAnalizar.rows[i].cells[5].innerText!="COND." && parseFloat(tableAnalizar.rows[i].cells[5].innerText)<j){ //cells[5]=id
                                            tableAnalizar.rows[i].before(tr);
                                            break;
                                        }
                                        else{
                                            tableNoEntran.rows[i].after(tr);
                                            break;
                                        }
                                    }
                                }
                            }
                            if(i==len){
                                tableAnalizar.appendChild(tr);
                            }   
                        }
                    }
                }
            }
            
            
        }
    </script>
    
    <p id="mensaje" style="color: #990000"><% if(session.getAttribute("mensaje")!=null){out.print("<img src='images/icono-informacion.png' width='3%' /> &nbsp;&nbsp;"+session.getAttribute("mensaje"));}%></p>
    <%
        session.setAttribute("mensaje",null);
    %>
    <form method="post" action='Seleccion?carrera=<%out.print(request.getParameter("carrera"));%>'>
        <table>
            <tr>
                <td>
                  <table id="contadores">
                        <tr>
                            <td style="background-color: #66ff00;">
                                ENTRAN
                            </td>
                            <td style="background-color: #66ccff;">
                                LISTA DE ESPERA
                            </td>
                            <td style="background-color: #ff6666;">
                                NO ENTRAN
                            </td>
                            <td style="background-color: #ffff66;">
                                A ANALIZAR
                            </td>
                        </tr>

                        <tr>
                            <td style="background-color: #ccffcc;">

                            </td>
                            <td style="background-color: #ccccff;">

                            </td>
                            <td style="background-color: #ffcccc;">

                            </td>
                            <td style="background-color: #ffffcc;">

                            </td>
                        </tr>
                        <tr>
                            <td style="background-color: #cccccc" colspan="4">

                            </td>
                        </tr>
                    </table>  
                </td>
                <td>
                    <table style="background-color: #ffcc66">
                        <tr>
                            <td>Referencias:</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="color:blue">Texto azul: </td>
                            <td>Reingresos</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td style="background-color: #ff9999">Recuadro rojo: </td>
                            <td>Posee alguna prueba insuficiente</td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>    
    
        
        
    <table style="width: 100%">
   
    <tr>
        <td colspan="2"><a name="Entran1" id="Entran1"></a> <h2 style="text-shadow: -1px -1px 1px #ffffcc;">ENTRAN</h2></td>
    </tr>
    <tr>
        <td>
            <a href="#ListaEspera1"><img width="5%" src="images/flechaAbajo.png" />No Entran</a> 
            <a href="#ListaEspera1"><img width="5%" src="images/flechaAbajo.png" />Lista de Espera</a> 
            <a href="#CasosAnalizar1"><img width="5%" src="images/flechaAbajo.png" />Casos a Analizar</a> 
        </td>
    </tr>
    <tr >
       
        <td colspan="2" id="todos" style="align-content: center; ">
            <%
                ManejadorSeleccionBD ms= new ManejadorSeleccionBD();
                String datos="";
                String color = "#ffffcc";
                String colorNocond="#ff9999";
                String colorFinal="";
                int j=0;
                ArrayList<Postulante> listado = ms.getPostulantesSeleccion(0, Integer.valueOf(sesion.getAttribute("usuarioID").toString()), Integer.valueOf(request.getParameter("carrera")));
                if (!listado.isEmpty()){
                    datos = "<table id='Entran' style='width:100%'>";
                    datos += "<tr style=\"background-color: #ffcc66;\">"+
                                "<td align='center'>Prec.</td>"+
                                "<td align='center'>E</td>"+
                                "<td align='center'>NE</td>"+
                                "<td align='center'>L</td>"+
                                "<td align='center'>A</td>"+
                                "<td align='center'>ID</td>"+
                                "<td align='center'>Nombres</td>"+
                                "<td align='center'>Apellidos</td>"+
                                "<td align='center'>CI</td>"+
                                "<td align='center'>Promedio</td>"+
                                "<td align='center'>Ver Ficha</td>"+
                                "<td align='center'></td>"+
                            "</tr>"; 
                    
                    for (Postulante p : listado){
                        if(p.getNotas().reingreso){
                            if(!p.getNotas().arrojo||(p.getNotas().psicotecnica==2)||(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg)||(p.getNotas().medico==2)||(p.getNotas().medico==1 && p.getNotas().medicoSeg)||(p.getNotas().odontologico==2)||(p.getNotas().odontologico==1 && p.getNotas().odontSeg)||(p.getNotas().educacionFisica<5)){
                                colorFinal=colorNocond;
                            }
                            else{
                                colorFinal=color; 
                            }
                        }
                        else{
                            if(!p.getNotas().arrojo||(p.getNotas().psicotecnica==2)||(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg)||(p.getNotas().medico==2)||(p.getNotas().medico==1 && p.getNotas().medicoSeg)||(p.getNotas().odontologico==2)||(p.getNotas().odontologico==1 && p.getNotas().odontSeg)||(p.getNotas().matematica<5)||(p.getNotas().historia<5)||(p.getNotas().idiomaEspaniol<5)||(p.getNotas().educacionFisica<5)){
                                colorFinal=colorNocond;
                            }
                            else{
                                colorFinal=color; 
                            }
                        }
                        datos += "<tr style='background-color:"+colorFinal;
                        if(p.getNotas().reingreso){
                            datos+= ";font-weight: bold;color:blue;";
                        }
                        datos+= "' id="+p.getCi()+">";
                        datos +="<td><input type=text size='3' style='text-align:center;' readonly='readonly' name=\"precedencia["+j+"][]\" value=\"";
                        if(p.getPrecedencia()==0){
                            datos+=j+1;
                        }
                        else{
                            datos+=p.getPrecedencia();
                        }
                        
                        datos += "\"/></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"0\"";
                        if(p.getResultado()!=1 && p.getResultado()!=2 && p.getResultado()!=3)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td ><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"1\"";
                        if(p.getResultado()==1)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"2\"";
                        if(p.getResultado()==2)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"3\"";
                        if(p.getResultado()==3)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " />"
                                + "<input type=text name=\"ci["+j+"][]\" hidden='hidden' value=\""+p.getCi()+"\"/></td>";
                        if (p.getId() != -1){
                            datos +="<td style='width: 10%' align='center'>"+ p.getId() +"</td>";
                        }
                        else{
                            datos +="<td style='width: 10%' align='center'>COND.</td>";
                        }
                        datos +="<td style='width: 15%' align='center'>"+ p.getPrimerNombre()+"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getPrimerApellido()+"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getCi() +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getPromedio() +"</td>"; 
                        datos +="<td style='width: 10%' align='center'><a target='_blank' href='agregar.jsp?ci="+ p.getCi() +"'><img src='images/ver.png' width='25%' /></a></td>";
                        datos +="<td style='width: 10%' align='center'><a onclick=\"abrir_dialog('#"+ p.getCi() +"-1')\" >Notas</a>";
                        
                        datos += " <div id="+ p.getCi()+"-1" +" style=\"display:none;\" title=\"Resultados de: "+p.getPrimerNombre()+" "+p.getPrimerApellido()+"\">"+
                                "<table>";
                        if(!p.getNotas().reingreso){
                        datos+=        "<tr>"+
                                "<td>Historia:</td>"+
                                "<td>";
                                if(p.getNotas().historia<5){
                                    datos+="<font color=\"red\">"+p.getNotas().historia+"</font>";
                                }else{
                                     datos+= p.getNotas().historia;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().historiaObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Matematica:</td>"+
                                "<td>";
                                if(p.getNotas().matematica<5){
                                    datos+="<font color=\"red\">"+p.getNotas().matematica+"</font>";
                                }else{
                                     datos+= p.getNotas().matematica;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().matematicaObs+"</td>"+
                                "</tr>"+
                                 "<tr>"+
                                "<td>Idioma Español:</td>"+
                                "<td>";
                                if(p.getNotas().idiomaEspaniol<5){
                                    datos+="<font color=\"red\">"+p.getNotas().idiomaEspaniol+"</font>";
                                }else{
                                     datos+= p.getNotas().idiomaEspaniol;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().idiomaEspaniolObs+"</td>"+
                                "</tr>";
                        }
                        else{
                            datos+="<tr>"+
                                "<td>REINGRESO</td>"+
                                "</tr>";
                        }
                        datos+=        "<tr>"+
                                "<td>Educacion Fisica:</td>"+
                                "<td>";
                                if(p.getNotas().educacionFisica<5){
                                    datos+="<font color=\"red\">"+p.getNotas().educacionFisica+"</font>";
                                }else{
                                     datos+= p.getNotas().educacionFisica;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().edFisicaObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Arrojo:</td>"+
                                "<td>";
                                if(p.getNotas().arrojo){
                                    datos+="CUMPLE";
                                }
                                else{
                                    datos+="<font color=\"red\">NO CUMPLE</font>";
                                };
                                datos+="</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Psicotécnica:</td>"+
                                "<td id='"+p.getNotas().psicotecnica+"'>";
                                if(p.getNotas().psicotecnica==2){
                                       datos+="<font color=\"red\">NO APTO</font>";
                                }else{
                                    if(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg){
                                       datos+="<font color=\"red\">APTO C/S</font>";
                                    }else{
                                        if(p.getNotas().psicotecnica==1 && !p.getNotas().psicoSeg){
                                            datos+= "APTO";
                                        }
                                    };
                                };
                            datos+="</td>"+
                                    "</tr>"+
                                     "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().psicoObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Medico:</td>"+
                                "<td>";
                                if(p.getNotas().medico==2){
                                       datos+="<font color=\"red\">NO APTO</font>";
                                }else{
                                    if(p.getNotas().medico==1 && p.getNotas().medicoSeg){
                                       datos+="<font color=\"red\">APTO C/S</font>";
                                    }else{
                                        if(p.getNotas().medico==1 && !p.getNotas().medicoSeg){
                                            datos+= "APTO";
                                        }
                                    };
                                };
                            datos+="</td>"+
                                "</tr>"+
                                     "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().medicoObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Odontológico:</td>"+
                                "<td>";
                                if(p.getNotas().odontologico==2){
                                       datos+="<font color=\"red\">NO APTO</font>";
                                }else{
                                    if(p.getNotas().odontologico==1 && p.getNotas().odontSeg){
                                       datos+="<font color=\"red\">APTO C/S</font>";
                                    }else{
                                        if(p.getNotas().odontologico==1 && !p.getNotas().odontSeg){
                                            datos+= "APTO";
                                        }
                                    };
                                };
                            datos+="</td>"+
                                "</tr>"+
                                     "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().odontObs+"</td>"+
                                "</tr>"+
                                 "</table>"+
                                "</div></td>";
                        
                    j+=1;
                    }
                    datos +="</tr>";
                    datos+="</table>";
                    out.print(datos);
                }
                else{
                    datos = "<table id='Entran' style='width:100%'>";
                    datos += "<tr style=\"background-color: #ffcc66;\">"+
                                "<td align='center'>Prec.</td>"+
                                "<td align='center'>E</td>"+
                                "<td align='center'>NE</td>"+
                                "<td align='center'>L</td>"+
                                "<td align='center'>A</td>"+
                                "<td align='center'>ID</td>"+
                                "<td align='center'>Nombres</td>"+
                                "<td align='center'>Apellidos</td>"+
                                "<td align='center'>CI</td>"+
                                "<td align='center'>Promedio</td>"+
                                "<td align='center'>Ver Ficha</td>"+
                                "<td align='center'></td>"+
                            "</tr>"; 
                    datos+="</table>";
                    out.print(datos);
                }
            %>
            
        </td>
    </tr>
    <tr>
        <td>
            <h2 style="text-shadow: -1px -1px 1px #ffffcc;"><a name="ListaEspera1" id="ListaEspera1"></a> LISTA DE ESPERA</h2>
        </td>
        <td>
            <h2 style="text-shadow: -1px -1px 1px #ffffcc;">NO ENTRAN</h2>
        </td>
    </tr>
    <tr>
        <td>
            <a href="#Entran1"><img width="5%" src="images/flechaArriba.png" />Entran</a> 
            <a href="#CasosAnalizar1"><img width="5%" src="images/flechaAbajo.png" />Casos a Analizar</a> 
        </td>
    </tr>
     <tr>
        <td style="width: 50%; vertical-align: top" >
            <table id="ListaEspera">
                    <tr style="background-color: #ffcc66;">
                        <td align='center'>Prec.</td>
                        <td align='center'>E</td>
                        <td align='center'>NE</td>
                        <td align='center'>L</td>
                        <td align='center'>A</td>
                        <td align='center'>ID</td>
                        <td align='center'>Nombres</td>
                        <td align='center'>Apellidos</td>
                        <td align='center'>CI</td>
                        <td align='center'>Promedio</td>
                        <td align='center'>Ver Ficha</td>
                        <td align='center'></td>
                    </tr>
                <%
                ms= new ManejadorSeleccionBD();
                listado = ms.getPostulantesSeleccion(2, Integer.valueOf(sesion.getAttribute("usuarioID").toString()), Integer.valueOf(request.getParameter("carrera")));
                datos="";
                for (Postulante p : listado){
                    if(p.getNotas().reingreso){
                        if(!p.getNotas().arrojo||(p.getNotas().psicotecnica==2)||(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg)||(p.getNotas().medico==2)||(p.getNotas().medico==1 && p.getNotas().medicoSeg)||(p.getNotas().odontologico==2)||(p.getNotas().odontologico==1 && p.getNotas().odontSeg)||(p.getNotas().educacionFisica<5)){
                            colorFinal=colorNocond;
                        }
                        else{
                            colorFinal=color; 
                        }
                    }
                    else{
                        if(!p.getNotas().arrojo||(p.getNotas().psicotecnica==2)||(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg)||(p.getNotas().medico==2)||(p.getNotas().medico==1 && p.getNotas().medicoSeg)||(p.getNotas().odontologico==2)||(p.getNotas().odontologico==1 && p.getNotas().odontSeg)||(p.getNotas().matematica<5)||(p.getNotas().historia<5)||(p.getNotas().idiomaEspaniol<5)||(p.getNotas().educacionFisica<5)){
                            colorFinal=colorNocond;
                        }
                        else{
                            colorFinal=color; 
                        }
                    }    
                        datos += "<tr style='background-color:"+colorFinal;
                        if(p.getNotas().reingreso){
                            datos+= ";font-weight: bold;color:blue;";
                        }
                        datos+="' id="+p.getCi()+">";
                        datos +="<td><input type=text size='3' style='text-align:center;' readonly='readonly' name=\"precedencia["+j+"][]\" value=\"0\"/></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"0\"";
                        if(p.getResultado()!=1 && p.getResultado()!=2 && p.getResultado()!=3)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td ><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"1\"";
                        if(p.getResultado()==1)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"2\"";
                        if(p.getResultado()==2)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"3\"";
                        if(p.getResultado()==3)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " />"
                                + "<input type=text name=\"ci["+j+"][]\" hidden='hidden' value=\""+p.getCi()+"\"/></td>";
                        if (p.getId() != -1){
                            datos +="<td style='width: 10%' align='center'>"+ p.getId() +"</td>";
                        }
                        else{
                            datos +="<td style='width: 10%' align='center'>COND.</td>";
                        }
                        datos +="<td style='width: 15%' align='center'>"+ p.getPrimerNombre()+"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getPrimerApellido()+"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getCi() +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getPromedio() +"</td>"; 
                        datos +="<td style='width: 10%' align='center'><a target='_blank' href='agregar.jsp?ci="+ p.getCi() +"'><img src='images/ver.png' width='25%' /></a></td>";
                        datos +="<td style='width: 10%' align='center'><a onclick=\"abrir_dialog('#"+ p.getCi() +"-1')\" >Notas</a>";
                        
                        datos += " <div id="+ p.getCi()+"-1" +" style=\"display:none;\" title=\"Resultados de: "+p.getPrimerNombre()+" "+p.getPrimerApellido()+"\">"+
                                "<table>";
                        if(!p.getNotas().reingreso){
                        datos+=        "<tr>"+
                                "<td>Historia:</td>"+
                                "<td>";
                                if(p.getNotas().historia<5){
                                    datos+="<font color=\"red\">"+p.getNotas().historia+"</font>";
                                }else{
                                     datos+= p.getNotas().historia;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().historiaObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Matematica:</td>"+
                                "<td>";
                                if(p.getNotas().matematica<5){
                                    datos+="<font color=\"red\">"+p.getNotas().matematica+"</font>";
                                }else{
                                     datos+= p.getNotas().matematica;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().matematicaObs+"</td>"+
                                "</tr>"+
                                 "<tr>"+
                                "<td>Idioma Español:</td>"+
                                "<td>";
                                if(p.getNotas().idiomaEspaniol<5){
                                    datos+="<font color=\"red\">"+p.getNotas().idiomaEspaniol+"</font>";
                                }else{
                                     datos+= p.getNotas().idiomaEspaniol;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().idiomaEspaniolObs+"</td>"+
                                "</tr>";
                        }
                        else{
                            datos+="<tr>"+
                                "<td>REINGRESO</td>"+
                                "</tr>";
                        }
                        datos+= "<tr>"+
                                    "<td>Educacion Fisica:</td>"+
                                    "<td>";
                                    if(p.getNotas().educacionFisica<5){
                                        datos+="<font color=\"red\">"+p.getNotas().educacionFisica+"</font>";
                                    }else{
                                         datos+= p.getNotas().educacionFisica;
                                    }
                            datos+="</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td></td>"+
                                    "<td>"+p.getNotas().edFisicaObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Arrojo:</td>"+
                                    "<td>";
                                    if(p.getNotas().arrojo){
                                        datos+="CUMPLE";
                                    }
                                    else{
                                        datos+="<font color=\"red\">NO CUMPLE</font>";
                                    };
                                    datos+="</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Psicotécnica:</td>"+
                                    "<td id='"+p.getNotas().psicotecnica+"'>";
                                    if(p.getNotas().psicotecnica==2){
                                           datos+="<font color=\"red\">NO APTO</font>";
                                    }else{
                                        if(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg){
                                           datos+="<font color=\"red\">APTO C/S</font>";
                                        }else{
                                            if(p.getNotas().psicotecnica==1 && !p.getNotas().psicoSeg){
                                                datos+= "APTO";
                                            }
                                        };
                                    };
                                datos+="</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td></td>"+
                                    "<td>"+p.getNotas().psicoObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Medico:</td>"+
                                    "<td>";
                                    if(p.getNotas().medico==2){
                                           datos+="<font color=\"red\">NO APTO</font>";
                                    }else{
                                        if(p.getNotas().medico==1 && p.getNotas().medicoSeg){
                                           datos+="<font color=\"red\">APTO C/S</font>";
                                        }else{
                                            if(p.getNotas().medico==1 && !p.getNotas().medicoSeg){
                                                datos+= "APTO";
                                            }
                                        };
                                    };
                                datos+="</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td></td>"+
                                    "<td>"+p.getNotas().medicoObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Odontológico:</td>"+
                                    "<td>";
                                    if(p.getNotas().odontologico==2){
                                           datos+="<font color=\"red\">NO APTO</font>";
                                    }else{
                                        if(p.getNotas().odontologico==1 && p.getNotas().odontSeg){
                                           datos+="<font color=\"red\">APTO C/S</font>";
                                        }else{
                                            if(p.getNotas().odontologico==1 && !p.getNotas().odontSeg){
                                                datos+= "APTO";
                                            }
                                        };
                                    };
                                datos+="</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td></td>"+
                                    "<td>"+p.getNotas().odontObs+"</td>"+
                                "</tr>"+
                                 "</table>"+
                                "</div></td>";
                        
                    j+=1;
                    }
                out.print(datos);
                %>
            </table>
        </td>
        <td style="width: 50%; vertical-align: top">
            <table id="NoEntran">
                    <tr style="background-color: #ffcc66;">
                        <td align='center'>Prec.</td>
                        <td align='center'>E</td>
                        <td align='center'>NE</td>
                        <td align='center'>L</td>
                        <td align='center'>A</td>
                        <td align='center'>ID</td>
                        <td align='center'>Nombres</td>
                        <td align='center'>Apellidos</td>
                        <td align='center'>CI</td>
                        <td align='center'>Promedio</td>
                        <td align='center'>Ver Ficha</td>
                        <td align='center'></td>
                    </tr>
                <%
                ms= new ManejadorSeleccionBD();
                listado = ms.getPostulantesSeleccion(1, Integer.valueOf(sesion.getAttribute("usuarioID").toString()), Integer.valueOf(request.getParameter("carrera")));
                datos="";
                for (Postulante p : listado){
                        if(p.getNotas().reingreso){
                            if(!p.getNotas().arrojo||(p.getNotas().psicotecnica==2)||(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg)||(p.getNotas().medico==2)||(p.getNotas().medico==1 && p.getNotas().medicoSeg)||(p.getNotas().odontologico==2)||(p.getNotas().odontologico==1 && p.getNotas().odontSeg)||(p.getNotas().educacionFisica<5)){
                                colorFinal=colorNocond;
                            }
                            else{
                                colorFinal=color; 
                            }
                        }
                        else{
                            if(!p.getNotas().arrojo||(p.getNotas().psicotecnica==2)||(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg)||(p.getNotas().medico==2)||(p.getNotas().medico==1 && p.getNotas().medicoSeg)||(p.getNotas().odontologico==2)||(p.getNotas().odontologico==1 && p.getNotas().odontSeg)||(p.getNotas().matematica<5)||(p.getNotas().historia<5)||(p.getNotas().idiomaEspaniol<5)||(p.getNotas().educacionFisica<5)){
                                colorFinal=colorNocond;
                            }
                            else{
                                colorFinal=color; 
                            }
                        }
                        datos += "<tr style='background-color:"+colorFinal;
                        if(p.getNotas().reingreso){
                            datos+= ";font-weight: bold; color:blue;";
                        }
                        datos+="' id="+p.getCi()+">";
                        datos +="<td><input type=text size='3' style='text-align:center;' readonly='readonly' name=\"precedencia["+j+"][]\" value=\"0\"/></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"0\"";
                        if(p.getResultado()!=1 && p.getResultado()!=2 && p.getResultado()!=3)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td ><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"1\"";
                        if(p.getResultado()==1)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"2\"";
                        if(p.getResultado()==2)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"3\"";
                        if(p.getResultado()==3)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " />"
                                + "<input type=text name=\"ci["+j+"][]\" hidden='hidden' value=\""+p.getCi()+"\"/></td>";
                        if (p.getId() != -1){
                            datos +="<td style='width: 10%' align='center'>"+ p.getId() +"</td>";
                        }
                        else{
                            datos +="<td style='width: 10%' align='center'>COND.</td>";
                        }
                        datos +="<td style='width: 15%' align='center'>"+ p.getPrimerNombre()+"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getPrimerApellido()+"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getCi() +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getPromedio() +"</td>"; 
                        datos +="<td style='width: 10%' align='center'><a target='_blank' href='agregar.jsp?ci="+ p.getCi() +"'><img src='images/ver.png' width='25%' /></a></td>";
                        datos +="<td style='width: 10%' align='center'><a onclick=\"abrir_dialog('#"+ p.getCi() +"-1')\" >Notas</a>";
                        
                        datos += " <div id="+ p.getCi()+"-1" +" style=\"display:none;\" title=\"Resultados de: "+p.getPrimerNombre()+" "+p.getPrimerApellido()+"\">"+
                                "<table>";
                        if(!p.getNotas().reingreso){
                        datos+=        "<tr>"+
                                "<td>Historia:</td>"+
                                "<td>";
                                if(p.getNotas().historia<5){
                                    datos+="<font color=\"red\">"+p.getNotas().historia+"</font>";
                                }else{
                                     datos+= p.getNotas().historia;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().historiaObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Matematica:</td>"+
                                "<td>";
                                if(p.getNotas().matematica<5){
                                    datos+="<font color=\"red\">"+p.getNotas().matematica+"</font>";
                                }else{
                                     datos+= p.getNotas().matematica;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().matematicaObs+"</td>"+
                                "</tr>"+
                                 "<tr>"+
                                "<td>Idioma Español:</td>"+
                                "<td>";
                                if(p.getNotas().idiomaEspaniol<5){
                                    datos+="<font color=\"red\">"+p.getNotas().idiomaEspaniol+"</font>";
                                }else{
                                     datos+= p.getNotas().idiomaEspaniol;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().idiomaEspaniolObs+"</td>"+
                                "</tr>";
                        }
                        else{
                            datos+="<tr>"+
                                "<td>REINGRESO</td>"+
                                "</tr>";
                        }
                        datos+=   "<tr>"+
                                "<td>Educacion Fisica:</td>"+
                                "<td>";
                                if(p.getNotas().educacionFisica<5){
                                    datos+="<font color=\"red\">"+p.getNotas().educacionFisica+"</font>";
                                }else{
                                     datos+= p.getNotas().educacionFisica;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().edFisicaObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Arrojo:</td>"+
                                "<td>";
                                if(p.getNotas().arrojo){
                                    datos+="CUMPLE";
                                }
                                else{
                                    datos+="<font color=\"red\">NO CUMPLE</font>";
                                };
                                datos+="</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Psicotécnica:</td>"+
                                "<td id='"+p.getNotas().psicotecnica+"'>";
                                if(p.getNotas().psicotecnica==2){
                                       datos+="<font color=\"red\">NO APTO</font>";
                                }else{
                                    if(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg){
                                       datos+="<font color=\"red\">APTO C/S</font>";
                                    }else{
                                        if(p.getNotas().psicotecnica==1 && !p.getNotas().psicoSeg){
                                            datos+= "APTO";
                                        }
                                    };
                                };
                            datos+="</td>"+
                                    "</tr>"+
                                     "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().psicoObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Medico:</td>"+
                                "<td>";
                                if(p.getNotas().medico==2){
                                       datos+="<font color=\"red\">NO APTO</font>";
                                }else{
                                    if(p.getNotas().medico==1 && p.getNotas().medicoSeg){
                                       datos+="<font color=\"red\">APTO C/S</font>";
                                    }else{
                                        if(p.getNotas().medico==1 && !p.getNotas().medicoSeg){
                                            datos+= "APTO";
                                        }
                                    };
                                };
                            datos+="</td>"+
                                "</tr>"+
                                     "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().medicoObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Odontológico:</td>"+
                                "<td>";
                                if(p.getNotas().odontologico==2){
                                       datos+="<font color=\"red\">NO APTO</font>";
                                }else{
                                    if(p.getNotas().odontologico==1 && p.getNotas().odontSeg){
                                       datos+="<font color=\"red\">APTO C/S</font>";
                                    }else{
                                        if(p.getNotas().odontologico==1 && !p.getNotas().odontSeg){
                                            datos+= "APTO";
                                        }
                                    };
                                };
                            datos+="</td>"+
                                "</tr>"+
                                     "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().odontObs+"</td>"+
                                "</tr>"+
                                 "</table>"+
                                "</div></td>";
                        
                    j+=1;
                    }
                out.print(datos);
                %>
                
            </table>
        </td>
    </tr>
    
    <tr>
        
        <td colspan="2"><a name="CasosAnalizar1" id="CasosAnalizar1"></a> <h2 style="text-shadow: -1px -1px 1px #ffffcc;">CASOS A ANALIZAR</h2><button onclick="pasarCasosAnalizarANoEntran();" type="button">Pasar todos a NO ENTRAN</button></td>
    </tr>
    <tr>
        <td>
            <a href="#Entran1"><img width="5%" src="images/flechaArriba.png" />Entran</a> 
            <a href="#ListaEspera1"><img width="5%" src="images/flechaArriba.png" />Lista de Espera</a> 
            <a href="#ListaEspera1"><img width="5%" src="images/flechaArriba.png" />No Entran</a> 
        </td>
    </tr>
    <tr >
       
        <td colspan="2" id="todos" style="align-content: center; ">
            <%
                datos="";
                color = "#ffffcc";
                colorNocond="#ff9999";
                colorFinal="";
                listado = ms.getPostulantesSeleccion(3, Integer.valueOf(sesion.getAttribute("usuarioID").toString()), Integer.valueOf(request.getParameter("carrera")));
                if (!listado.isEmpty()){
                    datos = "<table id='CasosAnalizar' style='width:100%'>";
                    datos += "<tr style=\"background-color: #ffcc66;\">"+
                                "<td align='center'>Prec.</td>"+
                                "<td align='center'>E</td>"+
                                "<td align='center'>NE</td>"+
                                "<td align='center'>L</td>"+
                                "<td align='center'>A</td>"+
                                "<td align='center'>ID</td>"+
                                "<td align='center'>Nombres</td>"+
                                "<td align='center'>Apellidos</td>"+
                                "<td align='center'>CI</td>"+
                                "<td align='center'>Promedio</td>"+
                                "<td align='center'>Ver Ficha</td>"+
                                "<td align='center'></td>"+
                            "</tr>"; 
                    
                    for (Postulante p : listado){
                        if(p.getNotas().reingreso){
                            if(!p.getNotas().arrojo||(p.getNotas().psicotecnica==2)||(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg)||(p.getNotas().medico==2)||(p.getNotas().medico==1 && p.getNotas().medicoSeg)||(p.getNotas().odontologico==2)||(p.getNotas().odontologico==1 && p.getNotas().odontSeg)||(p.getNotas().educacionFisica<5)){
                                colorFinal=colorNocond;
                            }
                            else{
                                colorFinal=color; 
                            }
                        }
                        else{
                            if(!p.getNotas().arrojo||(p.getNotas().psicotecnica==2)||(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg)||(p.getNotas().medico==2)||(p.getNotas().medico==1 && p.getNotas().medicoSeg)||(p.getNotas().odontologico==2)||(p.getNotas().odontologico==1 && p.getNotas().odontSeg)||(p.getNotas().matematica<5)||(p.getNotas().historia<5)||(p.getNotas().idiomaEspaniol<5)||(p.getNotas().educacionFisica<5)){
                                colorFinal=colorNocond;
                            }
                            else{
                                colorFinal=color; 
                            }
                        }
                        datos += "<tr style='background-color:"+colorFinal;
                        if(p.getNotas().reingreso){
                            datos+= ";font-weight: bold;color:blue;";
                        }
                        datos+="' id="+p.getCi()+">";
                        datos +="<td><input type=text size='3' style='text-align:center;' readonly='readonly' name=\"precedencia["+j+"][]\" value=\"0\"/></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"0\"";
                        if(p.getResultado()!=1 && p.getResultado()!=2 && p.getResultado()!=3)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td ><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"1\"";
                        if(p.getResultado()==1)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"2\"";
                        if(p.getResultado()==2)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " /></td>";
                        datos += "<td><input type=radio name=\"resultados["+j+"][]\" onclick=\"agregarALista(this,"+p.getCi()+");actualizarPrecedencia();actualizarContadores();\" value=\"3\"";
                        if(p.getResultado()==3)
                        {
                               datos +="checked='checked'";
                        };
                        datos += " />"
                                + "<input type=text name=\"ci["+j+"][]\" hidden='hidden' value=\""+p.getCi()+"\"/><input type=text hidden='hidden' name=\"precedencia["+j+"][]\" value=\"";
                        if(p.getPrecedencia()==0){
                            datos+=j+1;
                        }
                        else{
                            datos+=p.getPrecedencia();
                        }
                        
                        datos += "\"/></td>";
                        if (p.getId() != -1){
                            datos +="<td style='width: 10%' align='center'>"+ p.getId() +"</td>";
                        }
                        else{
                            datos +="<td style='width: 10%' align='center'>COND.</td>";
                        }
                        datos +="<td style='width: 15%' align='center'>"+ p.getPrimerNombre()+"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getPrimerApellido()+"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getCi() +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ p.getPromedio() +"</td>"; 
                        datos +="<td style='width: 10%' align='center'><a target='_blank' href='agregar.jsp?ci="+ p.getCi() +"'><img src='images/ver.png' width='25%' /></a></td>";
                        datos +="<td style='width: 10%' align='center'><a onclick=\"abrir_dialog('#"+ p.getCi() +"-1')\" >Notas</a>";
                        
                        datos += " <div id="+ p.getCi()+"-1" +" style=\"display:none;\" title=\"Resultados de: "+p.getPrimerNombre()+" "+p.getPrimerApellido()+"\">"+
                                "<table>";
                         if(!p.getNotas().reingreso){
                        datos+=        "<tr>"+
                                "<td>Historia:</td>"+
                                "<td>";
                                if(p.getNotas().historia<5){
                                    datos+="<font color=\"red\">"+p.getNotas().historia+"</font>";
                                }else{
                                     datos+= p.getNotas().historia;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().historiaObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Matematica:</td>"+
                                "<td>";
                                if(p.getNotas().matematica<5){
                                    datos+="<font color=\"red\">"+p.getNotas().matematica+"</font>";
                                }else{
                                     datos+= p.getNotas().matematica;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().matematicaObs+"</td>"+
                                "</tr>"+
                                 "<tr>"+
                                "<td>Idioma Español:</td>"+
                                "<td>";
                                if(p.getNotas().idiomaEspaniol<5){
                                    datos+="<font color=\"red\">"+p.getNotas().idiomaEspaniol+"</font>";
                                }else{
                                     datos+= p.getNotas().idiomaEspaniol;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().idiomaEspaniolObs+"</td>"+
                                "</tr>";
                        }
                        else{
                            datos+="<tr>"+
                                "<td>REINGRESO</td>"+
                                "</tr>";
                        }
                        datos+=        "<tr>"+
                                "<td>Educacion Fisica:</td>"+
                                "<td>";
                                if(p.getNotas().educacionFisica<5){
                                    datos+="<font color=\"red\">"+p.getNotas().educacionFisica+"</font>";
                                }else{
                                     datos+= p.getNotas().educacionFisica;
                                }
                        datos+="</td></tr>"+
                                "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().edFisicaObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Arrojo:</td>"+
                                "<td>";
                                if(p.getNotas().arrojo){
                                    datos+="CUMPLE";
                                }
                                else{
                                    datos+="<font color=\"red\">NO CUMPLE</font>";
                                };
                                datos+="</td>"+
                                "</tr>"+
                                "<tr>"+
                                "<td>Psicotécnica:</td>"+
                                "<td id='"+p.getNotas().psicotecnica+"'>";
                                if(p.getNotas().psicotecnica==2){
                                       datos+="<font color=\"red\">NO APTO</font>";
                                }else{
                                    if(p.getNotas().psicotecnica==1 && p.getNotas().psicoSeg){
                                       datos+="<font color=\"red\">APTO C/S</font>";
                                    }else{
                                        if(p.getNotas().psicotecnica==1 && !p.getNotas().psicoSeg){
                                            datos+= "APTO";
                                        }
                                    };
                                };
                            datos+="</td>"+
                                    "</tr>"+
                                     "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().psicoObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Medico:</td>"+
                                "<td>";
                                if(p.getNotas().medico==2){
                                       datos+="<font color=\"red\">NO APTO</font>";
                                }else{
                                    if(p.getNotas().medico==1 && p.getNotas().medicoSeg){
                                       datos+="<font color=\"red\">APTO C/S</font>";
                                    }else{
                                        if(p.getNotas().medico==1 && !p.getNotas().medicoSeg){
                                            datos+= "APTO";
                                        }
                                    };
                                };
                            datos+="</td>"+
                                "</tr>"+
                                     "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().medicoObs+"</td>"+
                                "</tr>"+
                                "<tr>"+
                                    "<td>Odontológico:</td>"+
                                "<td>";
                                if(p.getNotas().odontologico==2){
                                       datos+="<font color=\"red\">NO APTO</font>";
                                }else{
                                    if(p.getNotas().odontologico==1 && p.getNotas().odontSeg){
                                       datos+="<font color=\"red\">APTO C/S</font>";
                                    }else{
                                        if(p.getNotas().odontologico==1 && !p.getNotas().odontSeg){
                                            datos+= "APTO";
                                        }
                                    };
                                };
                            datos+="</td>"+
                                "</tr>"+
                                     "<tr>"+
                                "<td></td>"+
                                "<td>"+p.getNotas().odontObs+"</td>"+
                                "</tr>"+
                                 "</table>"+
                                "</div></td>";
                        
                    j+=1;
                    }
                    datos +="</tr>";
                    datos+="</table>";
                    out.print(datos);
                }
                else{
                    datos = "<table id='CasosAnalizar' style='width:100%'>";
                    datos += "<tr style=\"background-color: #ffcc66;\">"+
                                "<td align='center'>Prec.</td>"+
                                "<td align='center'>E</td>"+
                                "<td align='center'>NE</td>"+
                                "<td align='center'>L</td>"+
                                "<td align='center'>A</td>"+
                                "<td align='center'>ID</td>"+
                                "<td align='center'>Nombres</td>"+
                                "<td align='center'>Apellidos</td>"+
                                "<td align='center'>CI</td>"+
                                "<td align='center'>Promedio</td>"+
                                "<td align='center'>Ver Ficha</td>"+
                                "<td align='center'></td>"+
                            "</tr>"; 
                    datos+="</table>";
                    out.print(datos);
                }
            %>
            
        </td>
    </tr>
    <tr>
    <td style="text-align: right">
        <input type=text name="cant" hidden='hidden' value='<%=j%>'/>
        <input type="submit" value="GUARDAR"/>
    </td>
    </tr>
</table>
</form>

<%}else{
    response.sendRedirect("/listar.jsp");
    }%>
<%@ include file="footer.jsp" %> 