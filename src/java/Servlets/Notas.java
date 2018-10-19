/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorNotasBD;
import Classes.Usuario;
import Classes.ManejadorPostulanteDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gcincunegui
 */
public class Notas extends HttpServlet {

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
            Usuario u= (Usuario)sesion.getAttribute("usuario");
            if(u.isAdmin()||u.isSuperAdmin()){
                PrintWriter out = response.getWriter();
                String mensaje = "ERROR al modificar las notas.";
                try {
                    int carrera = Integer.valueOf(request.getParameter("carrera"));
                    int materia = Integer.valueOf(request.getParameter("materia"));
                    String[] ci = request.getParameterValues("ci[]");
                    String[] notas= null;
                    String[] observaciones=null;
                    String[] arrojo=null;
                    int i= Integer.valueOf(request.getParameter("i"));
                    switch(materia){
                        case 1: case 2: case 3:
                            notas= request.getParameterValues("notas[]");
                            observaciones = request.getParameterValues("observaciones[]");
                        break;
                        case 4:
                            notas= request.getParameterValues("notas[]");
                            observaciones = request.getParameterValues("observaciones[]");

                            arrojo = new String[i];
                            for(int j=0;j<i;j++){
                               arrojo[j] = request.getParameter("arrojo"+j);
                            }

                        break;
                        case 5: case 6: case 7:
                            notas = new String[i];
                            for(int j=0;j<i;j++){
                                notas[j]= request.getParameter("notas["+j+"][]");
                            }
                            observaciones = request.getParameterValues("observaciones[]");
                        break;
                    }
                    ManejadorNotasBD mn = new ManejadorNotasBD();
                    int anio = ManejadorPostulanteDB.getAnioPostula();
                    Boolean b= mn.modificarNotas(carrera, anio,materia, ci, notas, observaciones,i,arrojo, out);
                    if(b){
                        mensaje="Notas modificadas sastisfactoriamente.";
                    }
                } catch (Exception ex) {
                    mensaje = "ERROR: " + ex.getMessage();

                } finally {
                    // sets the message in request scope
                    sesion.setAttribute("Mensaje", mensaje);
                   response.sendRedirect("/resultados.jsp?carrera="+request.getParameter("carrera")+"&id="+request.getParameter("materia"));
                }
            }
            else{
                response.sendRedirect("listar.jsp");
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
