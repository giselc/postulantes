<%-- 
    Document   : usuario
    Created on : Mar 3, 2017, 1:31:40 AM
    Author     : Gisel
--%>

<%@page import="Classes.ManejadorCodigoBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="header.jsp" %>
<script>
    function validarContrasena(f){
        if (f.elements["pass"].value != f.elements["pass1"].value){
            document.getElementById("textocontrasena").innerHTML = "No coinciden las contraseñas.";
            return false;
        }
        else{
            f.submit();
        }
    };
    function validarNoAdmin(f,u1Null){
        if(u1Null){
            if (f.value.toLowerCase()=="admin"){
                alert("El usuario no puede ser 'admin'.");
                f.value="";
            }
        }
    };
</script>
<% 
    if(u.isSuperAdmin()){

%>
<% 


    Usuario u1= null;
    if(request.getParameter("id")!=null){
        int id = Integer.valueOf(request.getParameter("id"));
        u1= mc.getUsuario(u, id);
    }
%>
<a href="usuarios.jsp"><img src="images/atras.png" width="15%"/></a>
<h1 align="center"><u><% if (u1!=null){out.print("Usuario: "+u1.getNombre());}else{out.print("Alta de usuario");}%></u></h1>


    <table  width='70%' style="font-size: 130%" >
        <tr>
            <td valign='top' width='40%'>
                <img src="images/usuario.png" />
            </td>
            <td width='10%'>
                
            </td>
            <td width='50%'>
                <form method="post" action="Usuario?id=<%if (u1!=null){out.print(u1.getId());}else{out.print("-1");} %>" onsubmit='return validarContrasena(this);'>
                <table>
                    <tr>
                        <td colspan="2" style="color: #cd0a0a">
                            <% if(session.getAttribute("mensaje")!=null){
                                out.print("<img src='images/icono-informacion.png' width='8%'/>"+session.getAttribute("mensaje"));
                                session.setAttribute("mensaje", null);
                            }%>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p><b>Usuario:</b></p>
                        </td>
                        <td>
                            <p align="center"><input type="text" onblur="validarNoAdmin(this,<%= u1==null %>);" value="<% if (u1!=null){out.print(u1.getNombre());}%>" <% if (u1!=null){out.print("readonly='readonly'");}else{out.print("required='required'");}%> name="usuario" /></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p><b>Nombre para mostrar:</b></p>
                        </td>
                        <td>
                            <p><input type="text" value="<% if (u1!=null){out.print(u1.getNombreMostrar());}%>" name="nombreMostrar" required="required"/></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p><b>Email:</b></p>
                        </td>
                        <td>
                            <p><input type=email value="<% if (u1!=null){out.print(u1.getEmail());}%>" name="email"/></p>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Super Administrador:</b>
                        </td>
                        <td>
                            <% if(u1!=null && u1.getNombre().equals("admin")){
                                out.print("No puede modificar este campo");
                            }
                            else{%>
                            <input  id="admin" type=checkbox name="superAdmin" <% if(u1!=null && u1.isSuperAdmin()){out.print("checked='checked'");} %>/>
                            <%
                            }
                            %>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <b>Administrativo:</b>
                        </td>
                        <td>
                            <% if(u1!=null && u1.getNombre().equals("admin")){
                                out.print("No puede modificar este campo");
                            }
                            else{%>
                            <input  id="admin" type=checkbox name="admin" <% if(u1!=null && u1.isAdmin()){out.print("checked='checked'");} %>/>
                            <%
                            }
                            %>
                        </td>
                    </tr>
                    <tr <% if (u1!=null){out.print("hidden='hidden'");}%>>
                        <td>
                            <p><b>Contrase&ntilde;a:</b></p>
                        </td>
                        <td>
                            <p><input type=password name="pass"/></p>
                        </td>
                    </tr>
                    <tr <% if (u1!=null){out.print("hidden='hidden'");}%>>
                        <td>
                            <p><b>Repita la contrase&ntilde;a:</b></p>
                        </td>
                        <td>
                            <p><input type=password name="pass1" /></p>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <h3 id="textocontrasena" style="color: red"></h3>
                        </td>
                    </tr>
                </table>
                        <p align='right'><input type="submit"  value="Aceptar" /></p>
                </form>
            </td>
        </tr>
    </table>  
            




<% 
    }
    else{
         response.sendRedirect("");
    }

%>
<%@ include file="footer.jsp" %>