/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorHistorialBD;
import Classes.Postulante;
import Classes.RecordPostulanteFiltro;
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
public class FiltroHistorial extends HttpServlet {

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
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                ManejadorHistorialBD mh= new ManejadorHistorialBD();
                System.out.print(request.getParameter("anio"));
                int anio = Integer.valueOf(request.getParameter("anio"));
                RecordPostulanteFiltro rf =  new RecordPostulanteFiltro();
                rf.lmga = request.getParameter("lmga");
                rf.orientacion = request.getParameter("orientacion");
                rf.ps = request.getParameter("ps");
                rf.psEjercito = request.getParameter("psEjercito");
                rf.sexo = request.getParameter("sexo");
                rf.anio_aprobado = request.getParameter("anio_aprobado");
                rf.canthijos = request.getParameterValues("canthijos");
                rf.depDom = request.getParameterValues("depDom");
                rf.depNac = request.getParameterValues("depNac");
                rf.unidadInsc = request.getParameterValues("unidadInsc");
                rf.condicional = request.getParameter("condicional");
                rf.alojamiento = request.getParameter("alojamiento");
                rf.nsp = request.getParameter("nsp");
                rf.renuncio = request.getParameter("renuncio");
                int carrera = Integer.valueOf(request.getParameter("carrera"));
                ArrayList<Postulante> ap = mh.getPostulantesListarHistorial(rf, Integer.valueOf(sesion.getAttribute("usuarioID").toString()), carrera, anio);
               // System.out.print(ap.size());
                JsonObjectBuilder json = Json.createObjectBuilder(); 
                if (carrera==1){
                   sesion.setAttribute("listaTodosC", ap);
                   sesion.setAttribute("filtroMostrarC", rf.filtroMostrar);
               }
               else{
                   sesion.setAttribute("listaTodosA", ap);
                   sesion.setAttribute("filtroMostrarA", rf.filtroMostrar);
               }
               json.add("filtroMostrar",rf.filtroMostrar);
               if(ap.isEmpty()){
                   json.add("listadoFiltroHistorial", Json.createArrayBuilder().build());
                }
                else{
                    JsonArrayBuilder jab= Json.createArrayBuilder();
                    for (Postulante p : ap){
                        jab.add(Json.createObjectBuilder()
                            .add("ci", p.getCi())
                            .add("primerNombre", p.getPrimerNombre())
                            .add("segundoNombre", p.getSegundoNombre())
                            .add("primerApellido", p.getPrimerApellido())
                            .add("segundoApellido", p.getSegundoApellido())
                            .add("unidadInsc", p.getUnidadInsc().getNombreMostrar())
                            .add("id", p.getId())
                        );
                    };
                    json.add("listadoFiltroHistorial", jab);
                }
                out.print(json.build());
                 
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
