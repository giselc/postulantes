/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorSeleccionBD;
import Classes.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gcincunegui
 */
public class Promedios extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion= request.getSession();
        PrintWriter out= response.getWriter();
        if (sesion.getAttribute("usuarioID")!=null){
            Usuario u= (Usuario)sesion.getAttribute("usuario");
            if(u.isAdmin()||u.isSuperAdmin()){
                String mensaje="";
                try{
                    /* TODO output your page here. You may use following sample code. */
                    String auto= request.getParameter("auto");
                    ManejadorSeleccionBD ms = new ManejadorSeleccionBD();
                    if(auto.equals("true")){
                        ms.guardarPromediosAuto(out);
                    }
                    else{
                        String[] ci = request.getParameterValues("ci[]");
                        String[] promedios= request.getParameterValues("promedios[]");
                        ms.guardarPromedios(ci, promedios,out);
                    }
                    mensaje="Promedios modificados correctamente";
                }
                catch(Exception ex){
                    sesion.setAttribute("Mensaje", ex.getMessage());
                    Logger.getLogger(Promedios.class.getName()).log(Level.SEVERE, null, ex);
                }
                finally{
                    sesion.setAttribute("Mensaje", mensaje);
                    response.sendRedirect("/promedios.jsp");   
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
