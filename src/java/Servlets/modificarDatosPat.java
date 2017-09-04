/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;


import Classes.ManejadorPostulanteDB;
import Classes.RecordPostulantePatronim;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gisel
 */
@MultipartConfig
public class modificarDatosPat extends HttpServlet {

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
        String mensaje = "ERROR al modificar los datos patronímicos del postulante.";
        try {
            // connects to the database
            PrintWriter out = response.getWriter();
            RecordPostulantePatronim rp= new RecordPostulantePatronim();
            rp.ci=Integer.valueOf(request.getParameter("ci"));
           if (request.getParameter("PFechaNac")==null){
               rp.pFechaNac ="";
           }
           else{
               rp.pFechaNac = request.getParameter("PFechaNac");
           }
           if (request.getParameter("MFechaNac")==null){
               rp.mFechaNac ="";
           }
           else{
               rp.mFechaNac = request.getParameter("MFechaNac");
           }
           rp.pNombreComp=request.getParameter("PNombreComp");
           rp.pLocalidadNac = request.getParameter("PLocalidadNac");
           rp.pDepartamentoNac = Integer.valueOf(request.getParameter("PDepartamentoNac"));
           rp.pEstadoCivil=Integer.valueOf(request.getParameter("PEstadoCivil"));
           rp.pDomicilio = request.getParameter("PDomicilio");
           rp.pDepartamento = Integer.valueOf(request.getParameter("PDepartamento"));
           rp.pLocalidad=request.getParameter("PLocalidad");
           rp.pTelefono = request.getParameter("PTelefono");
           rp.pProfesion = request.getParameter("PProfesion");
           rp.pLugarTrabajo= request.getParameter("PLugarTrabajo");
           rp.mNombreComp=request.getParameter("MNombreComp");
           rp.mLocalidadNac = request.getParameter("MLocalidadNac");
           rp.mDepartamentoNac = Integer.valueOf(request.getParameter("MDepartamentoNac"));
           rp.mEstadoCivil=Integer.valueOf(request.getParameter("MEstadoCivil"));
           rp.mDomicilio = request.getParameter("MDomicilio");
           rp.mDepartamento = Integer.valueOf(request.getParameter("MDepartamento"));
           rp.mLocalidad=request.getParameter("MLocalidad");
           rp.mTelefono = request.getParameter("MTelefono");
           rp.mProfesion = request.getParameter("MProfesion");
           rp.mLugarTrabajo= request.getParameter("MLugarTrabajo");
           ManejadorPostulanteDB mp = new ManejadorPostulanteDB();
           String creadopor = sesion.getAttribute("usuarioID").toString();
           boolean b = mp.savePostulantePatronimico(rp,Integer.valueOf(creadopor), Integer.valueOf(sesion.getAttribute("carrera").toString()),out);
           if (b){
               mensaje = "Se ha modificado los datos patronimicos sastisfactoriamente";
           }
        } catch (Exception ex) {
            mensaje = "ERROR: " + ex.getMessage();
        } finally {
            
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
