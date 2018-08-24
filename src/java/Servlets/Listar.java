/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorHistorialBD;
import Classes.ManejadorPostulanteDB;
import Classes.Postulante;
import Classes.RecordPostulanteHistorial;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gisel
 */
@SuppressWarnings("unchecked")
public class Listar extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("usuarioID")!=null){
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                ManejadorPostulanteDB mp = new ManejadorPostulanteDB();
                String anio = request.getParameter("anio");
                if(request.getParameterValues("List[]")!=null){
                    
                    if(request.getParameterValues("List[]").length==1){
                        out.print("<html><head><script src=\"js/jquery-1.9.1.min.js\"></script>");
                        out.print("<script src=\"js/jquery.table2excel.js\"></script>");
                        out.print("<script> jQuery(document).ready(function() {\n" +
                                    "    $('#export-btn').on('click', function(e){\n" +
                                    "        e.preventDefault();\n" +
                                    "        ResultsToTable();\n" +
                                    "    });\n" +
                                    "    function ResultsToTable(){    \n" +
                                    "        $(\".resultsTable\").table2excel({\n" +
                                    "            exclude: \".noExl\",\n" +
                                    "            name: \"Results\",\n"+
    "					fileext: \".xls\",\n" +
                                    "        });\n" +
                                    "    }\n" +
                                    "}); </script></head><body>");
                        out.print("<BUTTON style='color:#0000ff' id=\"export-btn\">Export</BUTTON>");
                    }
                    if(Integer.valueOf(anio)==ManejadorPostulanteDB.getAnioPostula()){
                        for (String parameterValue : request.getParameterValues("List[]")) {
                            mp.imprimirFichaPostulante(Integer.valueOf(parameterValue), Integer.valueOf(sesion.getAttribute("usuarioID").toString()), out, Integer.valueOf(anio));
                        }
                    }
                    else{
                        ManejadorHistorialBD mh = new ManejadorHistorialBD();
                        for (String parameterValue : request.getParameterValues("List[]")) {
                            mh.imprimirFichaPostulante(Integer.valueOf(parameterValue), Integer.valueOf(sesion.getAttribute("usuarioID").toString()), out, Integer.valueOf(anio));
                        }
                    }
                    if(request.getParameterValues("List[]").length==1){
                        out.print("</body></html>");
                    }
                }
                else{
                    int i=0;
                    String color;
                    ArrayList<Postulante> ap = new ArrayList<>();
                    ArrayList<RecordPostulanteHistorial> ah = new ArrayList<>();
                    out.println("<table style=\"width: 70%; margin:auto;\">");
                    out.print("            <tr>\n" +
    "                <td colspan=\"3\">\n" +
    "                    <p align=\"left\">Escuela Militar</p>\n" +
    "                </td>\n" +
    "                <td colspan=\"3\">\n" +
    "                     <p align=\"right\">Jefatura de Estudios</p>\n" +
    "                </td>\n" +
    "            </tr>\n");
                    out.print("            <tr>\n" +
    "                <td colspan=\"6\" style=\"text-align: center;\">\n" +
    "                    <h1>POSTULANTES</h1>\n" +
    "                </td>\n" +
    "            </tr>\n");
                    if (request.getParameter("carreraListar").equals("Apoyo")){
                        out.print("            <tr>\n" +
    "                <td colspan=\"6\" style=\"text-align: center;\">\n" +
    "                    <h2>Apoyo de Servicio y Combate "+anio+"</h2>\n" +
    "                </td>\n" +
    "            </tr>\n");
                        if(Integer.valueOf(anio)==ManejadorPostulanteDB.getAnioPostula()){
                            ap= (ArrayList<Postulante>)sesion.getAttribute("listaTodosA");
                        }
                        else{
                            ah= (ArrayList<RecordPostulanteHistorial>)sesion.getAttribute("listaTodosA");
                        }
                        if(sesion.getAttribute("filtroMostrarA")!=null){
                        out.println("<tr>\n" +
                                "        <td colspan=\"6\">\n" +
                                "            <p>\n <b>FILTROS: </b> " +
                                                sesion.getAttribute("filtroMostrarA").toString() +
                                "            </p>\n" +
                                "        </td>\n" +
                                "    </tr>");
                        }
                    }
                    else{
                        out.print("            <tr>\n" +
        "                <td colspan=\"6\" style=\"text-align: center;\">\n" +
        "                    <h2>Cuerpo Comando "+anio+"</h2>\n" +
        "                </td>\n" +
        "            </tr>\n");
                        if(Integer.valueOf(anio)==ManejadorPostulanteDB.getAnioPostula()){
                            ap= (ArrayList<Postulante>)sesion.getAttribute("listaTodosC");
                        }
                        else{
                            ah= (ArrayList<RecordPostulanteHistorial>)sesion.getAttribute("listaTodosC");
                        }
                        if(sesion.getAttribute("filtroMostrarC")!=null){
                        out.println("<tr>\n" +
                                "        <td colspan=\"6\">\n" +
                                "            <p>\n<b>FILTROS: </b> " +
                                                sesion.getAttribute("filtroMostrarC").toString() +
                                "            </p>\n" +
                                "        </td>\n" +
                                "    </tr>");
                        }
                    }


                        out.print("<tr style='background-color:#ffcc66'>");
                        out.print("<td style='width: 5%' align='center'><h3 style='margin:2%;'></h3></td>");
                        if(Integer.valueOf(anio)==ManejadorPostulanteDB.getAnioPostula()){
                                out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>Numero</h3></td>");
                        }
                                out.print("<td style='width: 20%' align='center'><h3 style='margin:2%;'>Nombre</h3></td>");
                                out.print("<td style='width: 20%' align='center'><h3 style='margin:2%;'>Apellido</h3></td>");
                                out.print("<td style='width: 20%' align='center'><h3 style='margin:2%;'>Cedula</h3></td>");
                                out.print("<td style='width: 25%' align='center'><h3 style='margin:2%;'>Unidad Insc.</h3></td>");
                            if(Integer.valueOf(anio)!=ManejadorPostulanteDB.getAnioPostula()){
                                out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>Promedio</h3></td>");
                            }
                        out.print("</tr>");
                    if(Integer.valueOf(anio)==ManejadorPostulanteDB.getAnioPostula()){
                            for (Postulante p : ap){

                                if ((i%2)==0){
                                    color=" #ccccff";
                                }
                                else{
                                    color=" #ffff99";
                                }
                                i++;

                               out.print("<tr style='background-color:"+color+"'>");
                               out.print("<td style='width: 5%' align='center'><h3 style='margin:2%;'>"+i+"</h3></td>");
                                if (p.getId()!=-1){
                                    out.print("<td style='width: 10%' align='center'>"+p.getId()+"</td>");
                                }
                                else{
                                    out.print("<td style='width: 10%' align='center'>COND.</td>");
                                }
                                out.print("<td style='width: 20%' align='center'>"+p.getPrimerNombre()+"</td>");
                                out.print("<td style='width: 20%' align='center'>"+p.getPrimerApellido()+"</td>");
                                out.print("<td style='width: 20%' align='center'>"+String.valueOf(p.getCi())+"</td>");
                                out.print("<td style='width: 25%' align='center'>"+p.getUnidadInsc().getNombreMostrar()+"</td>"); 
                                out.print("</tr>");


                            }
                        }
                        else{
                            for (RecordPostulanteHistorial h : ah){

                                if ((i%2)==0){
                                    color=" #ccccff";
                                }
                                else{
                                    color=" #ffff99";
                                }
                                i++;

                               out.print("<tr style='background-color:"+color+"'>");
                               out.print("<td style='width: 5%' align='center'><h3 style='margin:2%;'>"+i+"</h3></td>");
                                out.print("<td style='width: 20%' align='center'>"+h.p.getPrimerNombre()+"</td>");
                                out.print("<td style='width: 20%' align='center'>"+h.p.getPrimerApellido()+"</td>");
                                out.print("<td style='width: 20%' align='center'>"+String.valueOf(h.p.getCi())+"</td>");
                                out.print("<td style='width: 25%' align='center'>"+h.p.getUnidadInsc().getNombreMostrar()+"</td>"); 
                                out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>"+h.promedio+"</h3></td>");
                                out.print("</tr>");


                            }
                        }   
                    
                    out.print("</table>");
                }
            }
        }
        else{
            sesion.setAttribute("login", "vacio");
            response.sendRedirect("");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
