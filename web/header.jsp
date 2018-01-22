<%--
    Document   : postulantes
    Created on : Mar 18, 2016, 11:57:12 AM
    Author     : Gisel
--%>

<%@page import="Classes.ManejadorPostulanteDB"%>
<%@page import="Classes.Usuario"%>
<%@page import="Classes.ManejadorCodigoBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/postulantes.css" type="text/css"/>
        <link rel="stylesheet" href="css/jquery-ui.css" type="text/css"/>
        <link rel="stylesheet" href="css/tabs.css" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
                        
			ul, ol {
				list-style:none;
                               
			}
			.nav > li {
				float:left;
			}
			.nav li a {
                                
				background-color:rgba(158,158,158,0.8);
				color:#fff;
				text-decoration:none;
				padding:10px 12px;
				display:block;
			}
			
			.nav li a:hover {
				background-color:#434343;
			}
			
			.nav li ul {
				display:none;
				position:absolute;
                                z-index: 5;
                                margin-left: -40px;
				min-width:260px;
			}
			
			.nav li:hover > ul {
				display:block;
			}
			
			.nav li ul li {
				position:relative;
			}
			
			.nav li ul li ul {
				right:-260px;
				top:0px;
			}
			
		</style>
        <title>Postulantes Escuela Militar</title>
    </head>
    <body style="background-color: #bfd070;">
        <script>
            function alertaHistorial(f, anio){
                var r=confirm("¿Seguro que desea pasar los datos anteriores a "+anio+" al historial? \n- Se eliminará la documentación correspondiente\n- Se resetearán los números de postulantes");
                if (r==true)
                {
                    f.submit();
                    return true;
                }
                return false;
            }
        </script>
        <table style="width: 100%;">
            <tr>
                <td class="fondo">
                    
                    <% 
                    HttpSession sesion= request.getSession();
                    if (sesion.getAttribute("usuarioID")!=null){
                    %>
                    <table style="width: 100%">
                        <tr>
                            <td style="width: 20%; vertical-align: top" >
                                <p style="color: #000000; margin: 0px"><%
                                    ManejadorCodigoBD mc = new ManejadorCodigoBD();
                                    Usuario u= (Classes.Usuario)sesion.getAttribute("usuario");
                                    out.print("Bienvenido ");out.print(u.getNombreMostrar());
                                    %></p>
                                <form action="logout" method="POST" style="font-size: 100%">
                                    <ul class="nav" style="padding: 0px;margin: 0px">
                                        <li><a <% if(u.isAdmin()){out.print("href='postulantes.jsp'");}else{out.print("href='listar.jsp'");} %>><table><tr><td align="center"><img src="images/home.png" width="80%" /></td><td>Inicio</td></tr></table> </a></li>
                                        <li><a > <table><tr><td align="center"><img src="images/menu.png" width="80%" /></td><td>Menu</td></tr></table> </a>
                                            <ul>
                                                        <li><a href="cambiarContrasena.jsp?id=<%= u.getId() %>">Cambiar contrase&ntilde;a </a></li>
                                                        <li><a href="contacto.jsp">Contacto </a></li>
                                                        <%
                                                            if (u.isSuperAdmin()){
                                                        %>
                                                             <li><a href="usuarios.jsp">Usuarios </a></li>
                                                             <li>
                                                                <a>PASAJE A SISTEMA PERSONAL</a>
                                                                <ul>
                                                                    <li>
                                                                        <form method="post" action='Pasaje?entran=1&carrera=1' >
                                                                            <a href=""><input type="submit" value="Exportar Entran Comando a txt"/> </a>
                                                                        </form>
                                                                        <form method="post" action='Pasaje?entran=1&carrera=2' >
                                                                            <a href=""><input type="submit" value="Exportar Entran Apoyo a txt"/> </a>
                                                                        </form>

                                                                    </li>
                                                                    <li>
                                                                        <a href="pasaje.jsp">EXPORTAR POR CEDULA A TXT</a>
                                                                    </li>

                                                                </ul>
                                                            </li>
                                                            <li>
                                                                <form method="post" action='Pasaje?historial=1' onsubmit="return alertaHistorial(this,<%= ManejadorPostulanteDB.getAnioPostula() %>);" >
                                                                    <a href=""><input type="submit" value="Pasar datos a historial"/> </a>
                                                                </form>
                                                            </li>
                                                        <%
                                                            }
                                                        %>
                                                        <li><a align="center"><input type="submit" value="SALIR"/></a></li>
                                                </ul>
                                        </li>
                                    </ul>
                                </form>
                            </td>
                            <td style="width: 60%; vertical-align: top">
                                <p align="center" ><img src="images/logo-escuelaMilitar.png" title="INICIO" <% if(u.isAdmin()){out.print("onclick=location.href='/postulantes.jsp'");}else{out.print("onclick=location.href='/listar.jsp'");} %> style="height: 15%; width: 100%"/></p>
                            </td>
                            
                            <td style="width: 20%">
                                
                            </td>
                        </tr>
                    </table>
                    <table style="width: 100%" >
                        <tr>
                            <td style="width: 5%">
                            </td>
                            <td>
                                </td>
                        </tr>
                        <tr>
                            <td style="width: 5%">
                            </td>
                            <td style="width: 90%;" class="conteinermenu">
                                <p align="center" style="margin-top: 0px"><img src="images/POSTULANTES.png" style="width: 35%"/></p>