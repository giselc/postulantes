/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorPostulanteDB;
import Classes.Usuario;
import Classes.Postulante;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author gcincunegui
 */
public class Pasaje extends HttpServlet {

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
        response.setContentType("text/plain");
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("usuarioID")!=null){
            Usuario u= (Usuario)sesion.getAttribute("usuario");
            if(u.isAdmin()||u.isSuperAdmin()){
                try (ServletOutputStream out = response.getOutputStream();) {
                    /* TODO output your page here. You may use following sample code. */
                    ManejadorPostulanteDB mp = new ManejadorPostulanteDB();
                    if(request.getParameter("entran")!=null){
                        int entran = Integer.valueOf(request.getParameter("entran"));
                        ArrayList<Postulante> al1;
                        ArrayList<Postulante> al2;
                        if(entran==0){
                            int ci = Integer.valueOf(request.getParameter("ci"));
                            al1= mp.getPostulantesPasaje(ci,0);
                            if(al1.size()>0 && al1.get(0).getCarrera().getCodigo()==1){
                                response.setHeader("Content-Disposition","attachment;filename=pasajeComando.txt");
                            }
                            else{
                                if(al1.size()>0){
                                    response.setHeader("Content-Disposition","attachment;filename=pasajeApoyo.txt");
                                }
                                else{
                                    response.setHeader("Content-Disposition","attachment;filename=ErrorCedula.txt");
                                }
                            }
                        }
                        else{
                            int carrera = Integer.valueOf(request.getParameter("carrera"));
                            if (carrera==1){
                                response.setHeader("Content-Disposition","attachment;filename=pasajeComando.txt");
                            }
                            else{
                                response.setHeader("Content-Disposition","attachment;filename=pasajeApoyo.txt");
                            }
                            al1= mp.getPostulantesPasaje(0,carrera);
                        }
                        Postulante p;
                        for(int i=0; i<al1.size(); i++){
                            p= al1.get(i);
                            out.print(p.getCi()+"||"); //campos(0)
                            out.print(p.getPrimerNombre()+"||"); //1
                            out.print(p.getPrimerApellido()+"||"); //2
                            out.print(p.getSexo()+"||"); //3
                            out.print(p.getFechaNac()+"||"); //4
                            out.print(p.getDepartamentoNac().getCodigo()+"||"); //5
                            out.print(p.getCc()+"||"); //6
                            out.print(p.getCcNro()+"||"); //7
                            out.print(p.getEstadoCivil().getCodigo()+"||"); //8
                            out.print(p.getDomicilio()+"||"); //9
                            out.print(p.getDepartamento().getCodigo()+"||"); //10
                            out.print(p.getLocalidad()+"||"); //11
                            out.print(p.getTelefono()+"||"); //12

                            out.print(p.getpNombreComp()+"||"); //13
                            out.print(p.getpFechaNac()+"||"); //14
                            out.print(p.getpDepartamentoNac().getCodigo()+"||"); //15
                            out.print(p.getpEstadoCivil().getCodigo()+"||"); //16
                            out.print(p.getpDomicilio()+"||"); //17
                            out.print(p.getpDepartamento().getCodigo()+"||"); //18
                            out.print(p.getpLocalidad()+"||"); //19
                            out.print(p.getPtelefono()+"||");//20
                            out.print(p.getpProfesion()+"||");//21
                            out.print(p.getpLugarTrabajo()+"||");//22

                            out.print(p.getmNombreComp()+"||");//23
                            out.print(p.getmFechaNac()+"||");//24
                            out.print(p.getmDepartamentoNac().getCodigo()+"||");//25
                            out.print(p.getmEstadoCivil().getCodigo()+"||");//26
                            out.print(p.getmDomicilio()+"||");//27
                            out.print(p.getmDepartamento().getCodigo()+"||");//28
                            out.print(p.getmLocalidad()+"||");//29
                            out.print(p.getMtelefono()+"||");//30
                            out.print(p.getmProfesion()+"||");//31
                            out.print(p.getmLugarTrabajo()+"||");//32

                            out.print(p.getSegundoNombre()+"||"); //33
                            out.println(p.getSegundoApellido()); //34

                        }

                        out.flush();
                        out.close();
                    }
                    else{
                        if(request.getParameter("historial")!=null){
                            String mensaje= "";
                            boolean b  = mp.pasarDatosAHistorial();
                            if(b){
                                mensaje= "Datos pasados al historial sastifactoriamente.";
                            }
                            else{
                                mensaje =  "Error al pasar los datos. Confime en la base de datos que se haya reseteado los numeros de postulantes.";
                            }
                            sesion.setAttribute("Mensaje",mensaje);
                            response.sendRedirect("/postulantes.jsp");
                        }
                    }
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
