<%@ include file="header.jsp" %>
    <style type="text/css">
                        
			.nav2 > li {
				float:left;
			}
			.nav2 li a {
                                
				background-color:rgba(158,158,158,0.8);
				color:#fff;
				text-decoration:none;
				padding:10px 5px;
				display:block;
			}
			
			.nav2 li a:hover{
				background-color:#990000;
			}
			
			.nav2 li ul {
				display:none;
				position:absolute;
                                z-index: 5;
                                margin-left: -40px;
				width:15%;
                                 
			}
			
			.nav2 li:hover > ul {
				display:block;
			}
			
			.nav2 li ul li {
				position:relative;
                                
			}
			
			.nav2 li ul li ul {
				right:-15%;
				top:0px;
                                
			}
                        .nav2 li ul li ul li{
				width:220px;
			}
			
    </style> 
    <% if(u.isAdmin()){%>
    <p id="mensaje" style="color: #ffffff"><% if(session.getAttribute("Mensaje")!=null){out.print("<img src='images/icono-informacion.png' width='3%' /> &nbsp;&nbsp;"+session.getAttribute("Mensaje"));}%></p>
    <%
        session.setAttribute("Mensaje",null);
    %>
    <ul class="nav2" style="width: 80%;padding-left: 13%;min-height: 350px;">
            <li  style="width: 30%">
                <a href="listar.jsp"><p align="center" style="margin:0px"><img  class="boton" title="Listar postulantes" src="images/POSTULANTES3.png"/></p></a>
                <ul>
                    <li>
                        <a href="listar.jsp">Datos Postulantes</a>
                    </li>
                    <li>
                        <a href="historial.jsp">Historial</a>
                    </li>
                </ul>
            </li>
            <li style="width: 30%">
                <a> <p align="center" style="margin:0px"><img  title="Exámenes y Actas" class="boton" src="images/EXAMENES3.png"/></p></a>
                <ul >
                    <%
                    for(int i=1;i<=7;i++){
                        out.print("<li><a>");
                        switch(i){
                            case 1: out.print("Matem&aacutetica"); break;
                            case 2: out.print("Historia"); break;
                            case 3: out.print("Idioma Español"); break;
                            case 4: out.print("Educación Física"); break;
                            case 5: out.print("Psicotécnica"); break;
                            case 6: out.print("Médico"); break;
                            case 7: out.print("Odontológico"); break;
                        }
                        out.print("</a>");
                    
                    %>
                        <ul>
                            <li>
                                <a>Acta</a>
                                <ul>
                                    <li>
                                        <a target="_blank"  href="acta.jsp?carrera=1&id=<%out.print(i);%>">Cuerpo Comando</a>
                                    </li>
                                    <li>
                                        <a target="_blank"  href="acta.jsp?carrera=2&id=<%out.print(i);%>">Apoyo S. y C.</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a>Resultados</a>
                                <ul>
                                    <li>
                                        <a style="word-break: keep-all" href="resultados.jsp?carrera=1&id=<%out.print(i);%>">Cuerpo Comando</a>
                                    </li>
                                    <li>
                                        <a style="word-break: keep-all" href="resultados.jsp?carrera=2&id=<%out.print(i);%>">Apoyo S. y C.</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    <%}%>
                   
                </ul>
            </li>
            
            <li style="width: 30%">
                <a><p align="center" style="margin:0px"><img  title="RESULTADOS" class="boton" src="images/SELECCION3.png" /></p></a>
                <ul>
                    <% 
                        if(u.isSuperAdmin()){

                    %>
                    <li>
                        <a>Promedios</a>
                        <ul>
                            <li>
                                <script>
                                    function confirmarGeneracionAuto(f){
                                        var r=confirm("Si genera automaticamente los promedios se perderá la información de selección. ¿Desea continuar?");
                                        if (r==true){
                                            f.submit();
                                            return true;
                                        }
                                        else{
                                            return false;
                                        }
                                    }
                                </script>
                                    
                                <form method="post" id="autoPromedios" action='Promedios?auto=true' onsubmit="return confirmarGeneracionAuto(this);">
                                    <a><input type="submit" value="Generar automaticamente"/></a>
                                </form>
                            </li>
                            <li>
                                <a href="promedios.jsp">Modificación manual</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a>Seleccion</a>
                        <ul>
                            <li>
                                <a href="seleccion.jsp?carrera=1">Cuerpo Comando</a>
                            </li>
                            <li>
                                <a href="seleccion.jsp?carrera=2">Apoyo S. y C.</a>
                            </li>
                            
                        </ul>
                    </li>
                    
                    <%
                        }
                    %>
                    <li>
                        <a href="sabana.jsp">Sábana</a>
                    </li>
                    <li>
                        <a target="_blank" href="resultadosFinales.jsp">IMPRIMIR RESULTADOS FINALES</a>
                    </li>
                </ul>
            </li>
            
    </ul>
    <%}else{
    response.sendRedirect("/listar.jsp");
    }%>
<%@ include file="footer.jsp" %> 
