/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ConexionDB;
import Classes.ManejadorPostulanteDB;
import Classes.RecordPostulanteBasico;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;

/**
 *
 * @author Gisel
 */
@MultipartConfig
public class guardarDatosBasicos extends HttpServlet {

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
        String mensaje = "ERROR al insertar el Postulante.";
        //response.getWriter().print("Holas2");
        PrintWriter out = response.getWriter();
        try {
        

            // connects to the database
           // constructs SQL statement
           //
           
            RecordPostulanteBasico rb = new RecordPostulanteBasico();
            rb.foto = request.getParameter("foto2");
            rb.fotoCIAnverso = request.getParameter("fotoCIAnverso2");
            rb.fotoCIReverso = request.getParameter("fotoCIReverso2");
            rb.fotoF69Hoja1 = request.getParameter("fotoF69Hoja12");
            rb.fotoF69Hoja2 = request.getParameter("fotoF69Hoja22");
            rb.fotoF69Hoja3 = request.getParameter("fotoF69Hoja32");
            rb.fotof1Hoja1 = request.getParameter("fotoF1Hoja12");
            rb.fotof1Hoja2 = request.getParameter("fotoF1Hoja22");
            String creadopor = sesion.getAttribute("usuarioID").toString();
            rb.ci = Integer.parseInt(request.getParameter("ci"));
            rb.primerNombre = request.getParameter("primerNombre");
            rb.segundoNombre = request.getParameter("segundoNombre");
            rb.primerApellido = request.getParameter("primerApellido");
            rb.segundoApellido = request.getParameter("segundoApellido");
            rb.FechaNac = request.getParameter("fechaNac");
            rb.sexo = request.getParameter("sexo[]");
            rb.departamentoNac = Integer.parseInt(request.getParameter("departamentoNac"));
            rb.localidadNac = request.getParameter("localidadNac");
            rb.cc = request.getParameter("cc");
            rb.talleOperacional = request.getParameter("talleOperacional");
            if (request.getParameter("talleBotas").equals("")){
                 rb.talleBotas = 0;
            }
            else{
                 rb.talleBotas = Integer.parseInt(request.getParameter("talleBotas"));
            }
            if (request.getParameter("talleQuepi").equals("")){
                 rb.talleQuepi = 0;
            }
            else{
                 rb.talleQuepi = Integer.parseInt(request.getParameter("talleQuepi"));
            }
            if (request.getParameter("CCNro").equals("")){
                 rb.ccNro = 0;
            }
            else{
                 rb.ccNro = Integer.parseInt(request.getParameter("CCNro"));
            }
           rb.estadoCivil = Integer.parseInt(request.getParameter("estadoCivil"));
           rb.domicilio = request.getParameter("domicilio");
           rb.departamento= Integer.parseInt(request.getParameter("departamento"));
           rb.localidad= request.getParameter("localidad");
           rb.telefono= request.getParameter("telefono");
           rb.email = request.getParameter("email");
           rb.quinto = request.getParameter("aprobado[]").equals("5");
           rb.orientacion = Integer.valueOf(request.getParameter("orientacion"));
          // response.getWriter().print("Hola4");
           if (request.getParameter("lmga")!=null){
                rb.lmga = request.getParameter("lmga").equals("on");
           }
           else{
               rb.lmga = false;
           }
           if (request.getParameter("reingreso")!=null){
                rb.reingreso = request.getParameter("reingreso").equals("on");
           }
           else{
               rb.reingreso = false;
           }
          // response.getWriter().print("Hola5");
           if (request.getParameter("paseDirecto")!=null){
                    rb.paseDirecto = request.getParameter("paseDirecto").equals("on");
            }
            else{
                rb.paseDirecto = false;
            }
          
           
            rb.notaPaseDirecto = Double.parseDouble(request.getParameter("notaPaseDirecto"));
            rb.materiasPendientes = request.getParameter("materiasPendientes");
           /*if (request.getParameter("buenaConducta")!=null){
                rb.buenaConducta = request.getParameter("buenaConducta").equals("on");
           }
           else{
               rb.buenaConducta = false;
           }*/
            rb.buenaConducta=true;
           //response.getWriter().print("Hola7");
           if (request.getParameter("ps")!=null){
                rb.ps = request.getParameter("ps").equals("on");
           }
           else{
               rb.ps = false;
           }
           //rb.psEjercito valido si rb.ps = true
           if (request.getParameter("psEjercito")!=null){
                rb.psEjercito = request.getParameter("psEjercito").equals("on");
           }
           else{
               rb.psEjercito = false;
           }
          // response.getWriter().print("Hola3");
           rb.hijos = Integer.valueOf(request.getParameter("hijos[]"));
           rb.observaciones = request.getParameter("observaciones");
            // sends the statement to the database server
           if (request.getParameter("alojamiento")!=null){
                rb.alojamiento = request.getParameter("alojamiento").equals("on");
           }
           else{
               rb.alojamiento = false;
           }
           if (request.getParameter("nsp")!=null){
                rb.nsp = request.getParameter("nsp").equals("on");
           }
           else{
               rb.nsp = false;
           }
           if (request.getParameter("renuncio")!=null){
                rb.renuncio = request.getParameter("renuncio").equals("on");
           }
           else{
               rb.renuncio = false;
           }
            ManejadorPostulanteDB mp = new ManejadorPostulanteDB();
            int b = mp.createPostulante(rb, Integer.valueOf(creadopor), Integer.valueOf(sesion.getAttribute("carrera").toString()),response.getWriter());
            if(b==1){
                mensaje="Postulante insertado sastisfactoriamente.";
            }
        } catch (Exception ex) {
            mensaje = "ERROR: " + ex.getMessage();

        } finally {
            // sets the message in request scope
            sesion.setAttribute("Mensaje", mensaje);
            response.sendRedirect("/agregar.jsp?ci="+request.getParameter("ci"));
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
