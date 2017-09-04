<%@page import="Classes.Postulante"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Classes.ManejadorPostulanteDB"%>
<% 
    HttpSession sesion = request.getSession();
    /* TODO output your page here. You may use following sample code. */
    ManejadorPostulanteDB mp = new ManejadorPostulanteDB();
    if(request.getParameterValues("List[]")!=null){
        for (String parameterValue : request.getParameterValues("List[]")) {
            mp.imprimirFichaPostulante(Integer.valueOf(parameterValue), Integer.valueOf(sesion.getAttribute("usuarioID").toString()), jspContext.getOut());
        }
    }
    else{
        int i=0;
        String color;
        ArrayList<Postulante> ap= (ArrayList<Postulante>)sesion.getAttribute("listaTodosC");

        out.println("<table style=\"width: 70%; margin:auto;\">");
            out.print("<tr style='background-color:#ffcc66'>");
                    out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>Numero</h3></td>");
                    out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Primer nombre</h3></td>");
                    out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Segundo nombre</h3></td>");
                    out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Primer apellido</h3></td>");
                    out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Segundo apellido</h3></td>");
                    out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Cedula</h3></td>");
                    out.print("<td style='width: 15%' align='center'><h3 style='margin:2%;'>Carrera</h3></td>");
            out.print("</tr>");
        for (Postulante p : ap){

            if ((i%2)==0){
                color=" #ccccff";
            }
            else{
                color=" #ffff99";
            }
            i++;

           out.print("<tr style='background-color:"+color+"'>");
           if (p.getId()!=-1){
                out.print("<td style='width: 10%' align='center'>"+p.getId()+"</td>");
            }
            else{
                out.print("<td style='width: 10%' align='center'>COND.</td>");
            }
            out.print("<td style='width: 15%' align='center'>"+p.getPrimerNombre()+"</td>");
            out.print("<td style='width: 15%' align='center'>"+p.getSegundoNombre()+"</td>");
            out.print("<td style='width: 15%' align='center'>"+p.getPrimerApellido()+"</td>");
            out.print("<td style='width: 15%' align='center'>"+p.getSegundoApellido()+"</td>");
            out.print("<td style='width: 15%' align='center'>"+String.valueOf(p.getCi())+"</td>");
            out.print("<td style='width: 15%' align='center'>"+p.getCarrera().getDescripcion()+"</td>"); 
            out.print("</tr>");


        }
        out.print("</table>");
    }
}
%>