/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorCodigoBD;
import Classes.ManejadorPostulanteDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gisel
 */
@WebServlet(name = "EstadisticasActuales", urlPatterns = {"/EstadisticasActuales"})
public class EstadisticasActuales extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ManejadorPostulanteDB mp = new ManejadorPostulanteDB();
            ManejadorCodigoBD mc= new ManejadorCodigoBD();
            LinkedList<ArrayList<Integer>> lC= mp.getDatosEstadisticos(1);//cuerpo comando
            LinkedList<ArrayList<Integer>> lA= mp.getDatosEstadisticos(2);//Apoyo C y S
            String datos="    <table >\n" +
"        <tr>\n" +
"            <td colspan=\"2\">\n" +
"                <h3>Cuerpo Comando</h3>\n" +
"            </td>\n" +
"        </tr>\n" +
"        <tr>\n" +
"            <td>\n" +
"                Masculino:\n" +
"            </td>\n" +
"            <td>\n" ;
            ArrayList<Integer> datosGrales= lC.getFirst();
            datos+=datosGrales.get(1);
datos+="            </td>\n" +
"        </tr>\n" +
"        <tr>\n" +
"            <td>\n" +
"                Femenino:\n" +
"            </td>\n" +
"            <td>\n"  ;
            datos+=(datosGrales.get(0)-datosGrales.get(1));
datos+="            </td>\n" +
"        </tr>\n" +
"        <tr>\n" +
"            <td>\n" +
"                TOTAL:\n" +
"            </td>\n" +
"            <td>\n" ;
            datos+=datosGrales.get(0);
datos+="            </td>\n" +
"        </tr>\n" +
"        <tr>\n" +
"            <td colspan=\"2\"><h5 style='margin:0px'> \n" +
"                Inscriptos en:\n" +
"            </h5></td>\n";

for(int i=1; i<lC.size();i++){
    datos+="            <tr>\n" +
"                <td>\n" ;
datos+= mc.getUsuario(lC.get(i).get(0)).getNombreMostrar();
datos+="                </td>\n" +
"                <td>\n" ;
datos+=lC.get(i).get(1);
datos+="                </td>\n" +
"            </tr>\n" +
"        </tr>\n";
}



datos+="        <tr>\n" +
"            <td colspan=\"2\">\n" +
"                <h3>Apoyo S. y C.</h3>\n" +
"            </td>\n" +
"        </tr>\n" +
"        <tr>\n" +
"            <td>\n" +
"                Masculino:\n" +
"            </td>\n" +
"            <td>\n" ;
            datosGrales= lA.getFirst();
            datos+=datosGrales.get(1);
datos+="            </td>\n" +
"        </tr>\n" +
"        <tr>\n" +
"            <td>\n" +
"                Femenino:\n" +
"            </td>\n" +
"            <td>\n"  ;
            datos+=(datosGrales.get(0)-datosGrales.get(1));
datos+="            </td>\n" +
"        </tr>\n" +
"        <tr>\n" +
"            <td>\n" +
"                TOTAL:\n" +
"            </td>\n" +
"            <td>\n" ;
            datos+=datosGrales.get(0);
datos+="            </td>\n" +
"        </tr>\n" +
        
"        <tr>\n" +
"            <td>\n" +
"                PS:\n" +
"            </td>\n" +
"            <td>\n" ;
            datos+=datosGrales.get(2);
datos+="            </td>\n" +
"        </tr>\n" +
"        <tr>\n" +
"            <td colspan=\"2\"><h5 style='margin:0px'> \n" +
"                Inscriptos en:\n" +
"            </h5></td>\n";

for(int i=1; i<lA.size();i++){
    datos+="            <tr>\n" +
"                <td>\n" ;
datos+= mc.getUsuario(lA.get(i).get(0)).getNombreMostrar();
datos+="                </td>\n" +
"                <td>\n" ;
datos+=lA.get(i).get(1);
datos+="                </td>\n" +
"            </tr>\n" +
"        </tr>\n";
}


datos+="    </table>";
out.print(datos);
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
