/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorNotasBD;
import Classes.ManejadorPostulanteDB;
import Classes.ManejadorSeleccionBD;
import Classes.Postulante;
import Classes.RecordPostulanteFiltro;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gcincunegui
 */
public class Seleccion extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String mensaje = "ERROR al modificar los resultados.";
        try {
            int carrera = Integer.valueOf(request.getParameter("carrera"));
            int cant = Integer.valueOf(request.getParameter("cant"));
           // out.print("CANT="+cant+" ");
            String[] ci = new String[cant];
            String[] resultados = new String[cant];
            String[] precedencia = new String[cant];
            for(int i=0; i<cant;i++){
                resultados[i]= request.getParameter("resultados["+i+"][]");
                ci[i]= request.getParameter("ci["+i+"][]");
                precedencia[i]= request.getParameter("precedencia["+i+"][]");
             //   out.print(i+"/ci="+ci[i]+"/resultado="+resultados[i]+"/precedencia="+precedencia[i]+"---");
            }
            //out.print("CANT="+cant);
            ManejadorSeleccionBD ms= new ManejadorSeleccionBD();
            boolean b = ms.guardarResultados(ci, resultados, precedencia, carrera);
            if(b){
                mensaje ="Resultados modificados correctamente.";
            }
        }catch (Exception ex){

         Logger.getLogger(Seleccion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // sets the message in request scope
            sesion.setAttribute("Mensaje", mensaje);
            response.sendRedirect("/seleccion.jsp?carrera="+request.getParameter("carrera"));
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
