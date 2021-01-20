/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorSeleccionBD;
import Classes.RecordPostulanteFiltro;
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
public class ImprimirResultados extends HttpServlet {

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
                try (PrintWriter out = response.getWriter()) {
                    ManejadorSeleccionBD ms = new ManejadorSeleccionBD();
                    String[] lista = request.getParameterValues("lista");
                    int entra= Integer.valueOf(request.getParameter("entra"));
                    RecordPostulanteFiltro rf =  new RecordPostulanteFiltro();
                    rf.lmga = request.getParameter("filtrolmga");
                    rf.pd = request.getParameter("filtropd");
                    rf.anio_aprobado = request.getParameter("filtroanio_aprobado");
                    rf.ps = request.getParameter("filtrops");
                    rf.psEjercito = request.getParameter("filtropsEjercito");
                    rf.sexo = request.getParameter("filtrosexo");
                    rf.canthijos = request.getParameterValues("filtrocanthijos");
                    rf.depDom = request.getParameterValues("filtrodepDom");
                    rf.depNac = request.getParameterValues("filtrodepNac");
                    rf.unidadInsc = request.getParameterValues("filtrounidadInsc");
                    rf.condicional = request.getParameter("filtrocondicional");
                    rf.alojamiento = request.getParameter("filtroalojamiento");
                    rf.nsp = request.getParameter("filtronsp");
                    rf.renuncio = request.getParameter("filtrorenuncio");
                    ms.imprimirResultadosFinales(lista,rf,entra,out);
                }
                catch(Exception ex){
                    Logger.getLogger(Sabana.class.getName()).log(Level.SEVERE, null, ex);
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
