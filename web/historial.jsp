<%-- 
    Document   : historial
    Created on : Aug 1, 2018, 1:10:03 PM
    Author     : Gisel
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Classes.*"%>
<%@page import="Classes.ManejadorPostulanteDB"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="Classes.ConexionDB"%>
<%@ include file="header.jsp" %>   
<% if(u!=null && u.isSuperAdmin()){%>
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script>
        $(document).ready(function() {
                $("#content div").hide();
                $("#tabs li:first").attr("id","current");
                $("#content div:first").fadeIn();
                $("#loader").fadeOut();

            $('#tabs a').click(function(e) {
                e.preventDefault();
                $("#content div").hide();
                $("#tabs li").attr("id","");
                $(this).parent().attr("id","current");
                $('#' + $(this).attr('title')).fadeIn();
            });
        });
        function abrir_dialog(dialog) {
          $( dialog ).dialog({
              modal: true
          });
        };
        function cerrar_dialog(dialog) {
          $( dialog ).dialog('close');
        };
        function seleccionar_todo_comando(){ 
            if (document.getElementById("selTodoComando").checked){
                for (i=1;i<document.formComandoHistorial.elements.length;i++) 
               if(document.formComandoHistorial.elements[i].type == "checkbox")	
                  document.formComandoHistorial.elements[i].checked=1 ;
            }
            else{
                for (i=1;i<document.formComandoHistorial.elements.length;i++) 
               if(document.formComandoHistorial.elements[i].type == "checkbox")	
                  document.formComandoHistorial.elements[i].checked=0;
            }
        
        } 
        function seleccionar_todo_apoyo(){ 
            if (document.getElementById("selTodoApoyo").checked){
                for (i=1;i<document.formApoyoHistorial.elements.length;i++) 
               if(document.formApoyoHistorial.elements[i].type == "checkbox")	
                  document.formApoyoHistorial.elements[i].checked=1 ;
            }
            else{
                for (i=1;i<document.formApoyoHistorial.elements.length;i++) 
               if(document.formApoyoHistorial.elements[i].type == "checkbox")	
                  document.formApoyoHistorial.elements[i].checked=0;
            }
        
        } 
        function listar(form) {//Funcion creada para no perder la sesion luego del submit
            form.submit();
            return false;
        };
        function serialize(form) {
                if (!form || form.nodeName !== "FORM") {
                        return;
                }
                var i, j, q = [];
                for (i = form.elements.length - 1; i >= 0; i = i - 1) {
                        if (form.elements[i].name === "") {
                                continue;
                        }
                        switch (form.elements[i].nodeName) {
                        case 'INPUT':
                                switch (form.elements[i].type) {
                                case 'text':
                                case 'hidden':
                                case 'password':
                                case 'button':
                                case 'reset':
                                case 'submit':
                                        q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                                        break;
                                case 'checkbox':
                                case 'radio':
                                        if (form.elements[i].checked) {
                                                q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                                        }						
                                        break;
                                case 'file':
                                        break;
                                }
                                break;			 
                        case 'TEXTAREA':
                                q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                                break;
                        case 'SELECT':
                                switch (form.elements[i].type) {
                                case 'select-one':
                                        q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                                        break;
                                case 'select-multiple':
                                        for (j = form.elements[i].options.length - 1; j >= 0; j = j - 1) {
                                                if (form.elements[i].options[j].selected) {
                                                        q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].options[j].value));
                                                }
                                        }
                                        break;
                                }
                                break;
                        case 'BUTTON':
                                switch (form.elements[i].type) {
                                case 'reset':
                                case 'submit':
                                case 'button':
                                        q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                                        break;
                                }
                                break;
                        }
                }
                return q.join("&");
        }
        function aplicarFiltro(f,idDatos){
            // alert(serialize(f));
             xmlhttp=new XMLHttpRequest();
             xmlhttp.onreadystatechange = function() {
                if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                    var obj = jQuery.parseJSON( xmlhttp.responseText );
                    var listado = obj.listadoFiltroHistorial;
                    var color = "";
                    var datos = "";
                    var j;
                     
                    datos+="<tr>";
                    datos+="<td colspan=\"8\">";
                    
                    if(idDatos == "datosComando"){
                        datos+="<p style=\"font-size: 70%\" id=\"filtroTexto\"></p>";
                        datos+="</td></tr><tr><td><input name=\"carreraListar\" hidden=\"hidden\" value=\"Comando\"/></td></tr>";
                        datos+="<tr style='background-color:#ffcc66'>";
                        datos+="<td style='width: 5%' align='center'><h3 style='margin:2%;'></h3></td>";
                        datos+="<td style='width: 5%' align='center'><input type='checkbox' onclick='seleccionar_todo_comando()' id='selTodoComando'></td>";
                     }
                     else{
                         datos+="<p style=\"font-size: 70%\" id=\"filtroTextoApoyo\"></p>";
                        datos+="</td></tr><tr><td><input name=\"carreraListar\" hidden=\"hidden\" value=\"Apoyo\"/></td></tr>";
                        datos+="<tr style='background-color:#ffcc66'>";
                        datos+="<td style='width: 5%' align='center'><h3 style='margin:2%;'></h3></td>";
                        datos+="<td style='width: 5%' align='center'><input type='checkbox' onclick='seleccionar_todo_apoyo()' id='selTodoApoyo'></td>";
                     }
                     datos+="<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Nombres</h3></td>";
                    datos+="<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Apellidos</h3></td>";
                    datos+="<td style='width: 15%' align='center'><h3 style='margin:2%;'>Cédula</h3></td>";
                    datos+="<td style='width: 15%' align='center'><h3 style='margin:2%;'>Unidad Insc.</h3></td>";
                    datos+="<td style='width: 10%' align='center'><h3 style='margin:2%;'>Promedio</h3></td>";
                    datos+="</tr>" ;
                    for (var i=0; i<listado.length;i++) {
                        if (listado[i].entra==0){
                            color="#ACFA58";
                        }
                        else{
                            if(listado[i].entra==2){
                                color="#F4FA58";
                            }
                            else{
                                color="#FA8258";
                            }
                        }
                        j=i+1;
                        datos += "<tr style='background-color:"+color+"'>";
                        datos += "<td style='width: 5%' align='center'>"+j+"</td>";
                        datos +="<td style='width: 5%' align='center'><input type='checkbox' name='List[]' value='"+ listado[i].ci +"' /></td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].primerNombre +"</td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].segundoNombre +"</td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].primerApellido +"</td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].segundoApellido +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ listado[i].ci +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ listado[i].unidadInsc +"</td>"; 
                        datos+="<td style='width: 10%' align='center'>"+ listado[i].promedio +"</td>";
                        datos +="</tr>";
                    }
                     document.getElementById(idDatos).innerHTML = datos;
                     //alert(idDatos);
                     if(idDatos == "datosComando"){
                         document.getElementById("filtroTexto").innerHTML = obj.filtroMostrar;
                     }
                     else{
                         document.getElementById("filtroTextoApoyo").innerHTML = obj.filtroMostrar;
                     }
                     //alert(datos);
                 };
             };
             var carrera;
             if(idDatos == "datosComando"){
                 carrera = "carrera=1&";
             }
             else{
                 carrera = "carrera=2&";
             }
             xmlhttp.open("POST","FiltroHistorial?anio="+document.getElementById("anioHistorial").value+"&"+carrera+serialize(f));
             xmlhttp.send();
             f.reset();
             if(idDatos == "datosComando"){
                 document.getElementById("pse").style.display = 'none';
                 document.getElementById("hijos").style.display = 'none';
                 document.getElementById("orient").style.display = 'none';
             }
             else{
                 document.getElementById("pseA").style.display = 'none';
                 document.getElementById("hijosA").style.display = 'none';
                 document.getElementById("orientA").style.display = 'none';
             }
             return false;
         }
         function cambiarHistorial(){
             var anio=document.getElementById("anioHistorial").value;
             document.getElementById("tituloComando").innerHTML="Cuerpo Comando Historial "+anio;
             document.getElementById("tituloApoyo").innerHTML="Apoyo de Servicio y Combate "+anio;
             document.getElementById("formApoyoHistorial").action='Listar?anio='+anio;
             document.getElementById("formComandoHistorial").action='Listar?anio='+anio;
             document.getElementById("estadistica").action='Estadistica?anio='+anio;
             xmlhttp=new XMLHttpRequest();
             xmlhttp.onreadystatechange = function() {
                 if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                    var obj = jQuery.parseJSON( xmlhttp.responseText );
                    var listado = obj.listadoComandoHistorial;
                    var color = "";
                    var datos = "";
                    var j;
                     
                    datos+="<tr>";
                    datos+="<td colspan=\"8\">";
                    datos+="<p style=\"font-size: 70%\" id=\"filtroTexto\"></p>";
                    datos+="</td></tr><tr><td><input name=\"carreraListar\" hidden=\"hidden\" value=\"Comando\"/></td></tr>";   

                    datos+="<tr style='background-color:#ffcc66'>";
                    datos+="<td style='width: 5%' align='center'><h3 style='margin:2%;'></h3></td>";
                    datos+="<td style='width: 5%' align='center'><input type='checkbox' onclick='seleccionar_todo_comando()' id='selTodoComando'></td>";
                    
                    datos+="<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Nombres</h3></td>";
                    datos+="<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Apellidos</h3></td>";
                    datos+="<td style='width: 15%' align='center'><h3 style='margin:2%;'>Cédula</h3></td>";
                    datos+="<td style='width: 15%' align='center'><h3 style='margin:2%;'>Unidad Insc.</h3></td>";
                    datos+="<td style='width: 10%' align='center'><h3 style='margin:2%;'>Promedio</h3></td>";
                    datos+="</tr>" ;
                    for (var i=0; i<listado.length;i++) {
                        if (listado[i].entra==0){
                            color="#ACFA58";
                        }
                        else{
                            if(listado[i].entra==2){
                                color="#F4FA58";
                            }
                            else{
                                color="#FA8258";
                            }
                        }
                        j=i+1;
                        datos += "<tr style='background-color:"+color+"'>";
                        datos += "<td style='width: 5%' align='center'>"+j+"</td>";
                        datos +="<td style='width: 5%' align='center'><input type='checkbox' name='List[]' value='"+ listado[i].ci +"' /></td>";
                        
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].primerNombre +"</td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].segundoNombre +"</td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].primerApellido +"</td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].segundoApellido +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ listado[i].ci +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ listado[i].unidadInsc +"</td>"; 
                        datos+="<td style='width: 10%' align='center'>"+ listado[i].promedio +"</td>";
                        datos +="</tr>";

                    }
                    document.getElementById("datosComando").innerHTML = datos;

                    listado = obj.listadoApoyoHistorial;
                    color = "";
                    datos = "";
                    datos+="<tr>";
                    datos+="<td colspan=\"8\">";
                    datos+="<p style=\"font-size: 70%\" id=\"filtroTexto\"></p>";
                    datos+="</td></tr><tr><td><input name=\"carreraListar\" hidden=\"hidden\" value=\"Apoyo\"/></td></tr>";   

                    datos+="<tr style='background-color:#ffcc66'>";
                    datos+="<td style='width: 5%' align='center'><h3 style='margin:2%;'></h3></td>";
                    datos+="<td style='width: 5%' align='center'><input type='checkbox' onclick='seleccionar_todo_apoyo()' id='selTodoApoyo'></td>";
                    
                    datos+="<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Nombres</h3></td>";
                    datos+="<td colspan=2 style='width: 20%' align='center'><h3 style='margin:2%;'>Apellidos</h3></td>";
                    datos+="<td style='width: 15%' align='center'><h3 style='margin:2%;'>Cédula</h3></td>";
                    datos+="<td style='width: 15%' align='center'><h3 style='margin:2%;'>Unidad Insc.</h3></td>";
                    datos+="<td style='width: 10%' align='center'><h3 style='margin:2%;'>Promedio</h3></td>";
                    datos+="</tr>" ;
                    for (var i=0; i<listado.length;i++) {
                        if (listado[i].entra==0){
                            color="#ACFA58";
                        }
                        else{
                            if(listado[i].entra==2){
                                color="#F4FA58";
                            }
                            else{
                                color="#FA8258";
                            }
                        }
                        j=i+1;
                        datos += "<tr style='background-color:"+color+"'>";
                        datos += "<td style='width: 5%' align='center'>"+j+"</td>";
                        datos +="<td style='width: 5%' align='center'><input type='checkbox' name='List[]' value='"+ listado[i].ci +"' /></td>";
                        
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].primerNombre +"</td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].segundoNombre +"</td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].primerApellido +"</td>";
                        datos +="<td style='width: 10%' align='center'>"+ listado[i].segundoApellido +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ listado[i].ci +"</td>";
                        datos +="<td style='width: 15%' align='center'>"+ listado[i].unidadInsc +"</td>"; 
                        datos+="<td style='width: 10%' align='center'>"+ listado[i].promedio +"</td>";
                        datos +="</tr>";
                    }
                    document.getElementById("datosApoyo").innerHTML = datos;
                 };
             };
            
             xmlhttp.open("POST","ListarHistorial?anio="+anio);
             xmlhttp.send();
             return false;
         }
    </script> 
    <p id="mensaje" style="color: #ffffff"><% if(sesion.getAttribute("Mensaje")!=null){out.print("<img src='images/icono-informacion.png' width='3%' /> &nbsp;&nbsp;"+sesion.getAttribute("Mensaje"));}%></p>
<%
    sesion.setAttribute("Mensaje",null);
%> 
<table>
<tr>
    
<%
ManejadorHistorialBD mp = new ManejadorHistorialBD();
%>  
            <td><h1>A&ntilde;o:</h1> </td>
            <td>
                <select id="anioHistorial" form="formulario" required="required" onchange="cambiarHistorial()">
                    <%
                    ArrayList<Integer> ah = mp.getAniosHistorial();
                    int k=0;
                    String s="";
                    for(Integer i: ah ){
                        if(k==0){
                            s="selected";
                            k=1;
                        }
                        else{
                            s="";
                        }
                        out.print("<option " + s +" value='"+i +"'>"+ i +"</option>");
                    }
                    %>
                 </select>
            </td>
            
            <td>
                <table>
                    <TR>
                        <td style="background-color: #ffffff">REFERENCIAS:</td>
                    </TR>
                    <tr>
                        <td style="background-color: #ACFA58">ENTRA</td>
                    </tr>
                    <tr>
                        <td style="background-color: #F4FA58">LISTA DE ESPERA</td>
                    </tr>
                    <tr>
                        <td style="background-color: #FA8258">NO ENTRA</td>
                    </tr>
                </table>
            </td>
            <td>
                <form method="post" target="_blank" id="estadistica" action="Estadistica?anio=<%= ManejadorPostulanteDB.getAnioPostula()-1 %>">
                    <input type="image" width="30%" title="Estadísticas por unidad inscriptora" src="images/estadistica.png" alt="Submit Form" />
                </form>
                
            </td>
            
        </tr>
        
        </table>
     <ul id="tabs">
         <li><a href="#" title="Cuerpo-Comando"><b>Cuerpo Comando</b></a></li>
         <li><a href="#" title="Apoyo-SyC"><b>Apoyo S. y C.</b></a></li>
     </ul>
    <div id="loader" style="z-index: 50;position: fixed; top:0; left:0; width:100%; height: 100%;background: url('images/loading-verde.gif') center center no-repeat; background-size: 10%"></div>
     <div id="content">
         <div id="Cuerpo-Comando">
             <%@include file="listarComandoHistorial.jsp" %>
         </div>
         <div id="Apoyo-SyC">
             <%@include file="listarApoyoHistorial.jsp" %>
         </div>
     </div>   
    <%}else{
    response.sendRedirect("/listar.jsp");
    }%>
<%@ include file="footer.jsp" %> 

