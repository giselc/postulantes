/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorHistorialBD;
import Classes.Usuario;
import Classes.RecordPostulanteHistorial;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
 * @author Gisel
 */
public class ListarHistorial extends HttpServlet {

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
        HttpSession sesion= request.getSession();
        if (sesion.getAttribute("usuarioID")!=null){
            Usuario u= (Usuario)sesion.getAttribute("usuario");
            if(u.isAdmin()||u.isSuperAdmin()){
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    ManejadorHistorialBD mh= new ManejadorHistorialBD();
                    int anio = Integer.valueOf(request.getParameter("anio"));
                    ArrayList<RecordPostulanteHistorial> ap = mh.getPostulantesListarHistorial(null, Integer.valueOf(sesion.getAttribute("usuarioID").toString()), 1, anio);
                    JsonObjectBuilder json = Json.createObjectBuilder(); 

                   //json.add("filtroMostrar",rf.filtroMostrar);
                   if(ap.isEmpty()){
                       json.add("listadoComandoHistorial", Json.createArrayBuilder().build());
                    }
                    else{
                        JsonArrayBuilder jab= Json.createArrayBuilder();
                        for (RecordPostulanteHistorial h : ap){
                            jab.add(Json.createObjectBuilder()
                                .add("ci", h.p.getCi())
                                .add("primerNombre", h.p.getPrimerNombre())
                                .add("segundoNombre", h.p.getSegundoNombre())
                                .add("primerApellido", h.p.getPrimerApellido())
                                .add("segundoApellido", h.p.getSegundoApellido())
                                .add("unidadInsc", h.p.getUnidadInsc().getNombreMostrar())
                                .add("id", h.p.getId())
                                .add("entra", h.entra)
                                .add("promedio", h.promedio)

                            );
                        };
                        json.add("listadoComandoHistorial", jab);
                    }
                    ap = mh.getPostulantesListarHistorial(null, Integer.valueOf(sesion.getAttribute("usuarioID").toString()), 2, anio);
                    if(ap.isEmpty()){
                       json.add("listadoApoyoHistorial", Json.createArrayBuilder().build());
                    }
                    else{
                        JsonArrayBuilder jab= Json.createArrayBuilder();
                        for (RecordPostulanteHistorial h : ap){
                            jab.add(Json.createObjectBuilder()
                                .add("ci", h.p.getCi())
                                .add("primerNombre", h.p.getPrimerNombre())
                                .add("segundoNombre", h.p.getSegundoNombre())
                                .add("primerApellido", h.p.getPrimerApellido())
                                .add("segundoApellido", h.p.getSegundoApellido())
                                .add("unidadInsc", h.p.getUnidadInsc().getNombreMostrar())
                                .add("id", h.p.getId())
                                .add("entra", h.entra)
                                .add("promedio", h.promedio)
                            );
                        };
                        json.add("listadoApoyoHistorial", jab);
                    }
                    out.print(json.build());

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
