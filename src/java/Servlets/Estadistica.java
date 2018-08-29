/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Classes.ManejadorEstadisticasBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Gisel
 */
public class Estadistica extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static String getFechaActual() {
    Date ahora = new Date();
    Locale espanol = new Locale("es","ES");
    SimpleDateFormat formateador = new SimpleDateFormat("dd-MMMMM-yyyy",espanol);
    String[] s = formateador.format(ahora).split("-");
    return "Toledo, "+s[0]+" de "+s[1]+" de "+s[2];
}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession sesion = request.getSession();
        if (sesion.getAttribute("usuarioID")!=null){
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ManejadorEstadisticasBD me = new ManejadorEstadisticasBD();
            int anio = Integer.valueOf(request.getParameter("anio"));
            HashMap<Integer,Integer>hmTodosComando = me.getDatosEstadisticos(anio, 1);
            HashMap<Integer,Integer>hmTodosApoyo = me.getDatosEstadisticos(anio, 2);
            HashMap<Integer,Integer>hmEntranComando = me.getDatosEstadisticosEntran(anio, 1);
            HashMap<Integer,Integer>hmEntranApoyo = me.getDatosEstadisticosEntran(anio, 2);
            int todosPostComando = hmTodosComando.getOrDefault(-2, 0);
            int todosPostApoyo = hmTodosApoyo.getOrDefault(-2, 0);
            int entranPostComando = hmEntranComando.getOrDefault(-2, 0);
            int entranPostApoyo = hmEntranApoyo.getOrDefault(-2, 0);
            out.print("<head>\n" +
"<style>\n" +
                    "    #contenido {\n" +
"        width:100%;"
                    + "border-collapse:collapse;\n" +
"    }\n" +
"    #contenido td{\n" +
"        border:1px solid #000000;\n" +
"    }\n" +
"</style>\n" +
"</head>\n" +
"<body>");
            out.println("<table style=\"width: 100%; margin:auto;font-weight: bold\">");
            out.print("            <tr>\n" +
    "                <td colspan=\"3\">\n" +
    "                    <p align=\"left\">Escuela Militar</p>\n" +
    "                </td>\n" +
    "                <td colspan=\"3\">\n" +
    "                     <p align=\"right\">Jefatura de Estudios</p>\n" +
    "                </td>\n" +
    "            </tr>\n");
             out.print("            <tr>\n" +
    "                <td colspan=\"3\">\n" +
    "                </td>\n" +
    "                <td colspan=\"3\">\n" +
    "                     <p align=\"right\">"+getFechaActual()+"</p>\n" +
    "                </td>\n" +
    "            </tr>\n");
            out.print("            <tr>\n" +
    "                <td colspan=\"6\" style=\"text-align: center;\">\n" +
    "                    <h3>Estadísticas de postulantes para el año "+request.getParameter("anio")+"</h3>\n" +
    "                </td>\n" +
    "            </tr>\n");
            String s="<table id=\"contenido\" style=\"\">\n" +
"    <tr style=\"text-align: center; background-color:#e2d4d4;font-weight: bold\">\n" +
"        <td colspan=\"2\">Curso\n" +
"            \n" +
"        </td>\n" +
"        <td colspan=\"4\">\n" +
"            Cuerpo Comando\n" +
"        </td>\n" +
"        <td colspan=\"4\">\n" +
"            Curso de Apoyo de Servicios y Combate\n" +
"        </td>\n" +
"    </tr>\n" +
"    <tr style=\"text-align: center; background-color:#e2d4d4;font-weight: bold\">\n" +
"        <td colspan=\"2\">\n" +
"            Cantidad y porcentaje\n" +
"        </td>\n" +
"        <td>\n" +
"            Postulantes\n" +
"        </td>\n" +
"        <td>\n" +
"            Ingresos\n" +
"        </td>\n" +
"        <td colspan=\"2\">\n" +
"            Porcentaje en total\n" +
"        </td>\n" +
"        <td>\n" +
"            Postulantes\n" +
"        </td>\n" +
"        <td>\n" +
"            Ingresos\n" +
"        </td>\n" +
"        <td colspan=\"2\">\n" +
"            Porcentaje en total\n" +
"        </td>\n" +
"    </tr>\n" +
                            
"    <tr style=\"text-align: center; background-color:#e2d4d4;font-weight: bold\">\n" +
"        <td colspan=\"2\">\n" +
"            Total de la E.M.\n" +
"        </td>\n" +
"        <td>\n" + todosPostComando +
"        </td>\n" +
"        <td>\n" +entranPostComando +
"        </td>\n" +
"        <td colspan=\"2\">\n" +
"        </td>\n" +
"        <td>\n" + todosPostApoyo +
"        </td>\n" +
"        <td>\n" + entranPostApoyo +
"        </td>\n" +
"        <td colspan=\"2\">\n" +
"        </td>\n" +
"    </tr>\n" ;
            
            int i=7;
            s+=imprimirEstadisticaUnidad(true,"D.E.II", "Bn.I.4", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            
            i=22;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.I.5", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            
            i=21;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.I.6", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=27;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.I.13", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=28;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.I.15", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=8;
            s+=imprimirEstadisticaUnidad(true,"", "Reg.C.2", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=9;
            s+=imprimirEstadisticaUnidad(true,"", "Grp.A.2", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=10;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.Ing.2", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            int sumatotalComandoDIV=hmTodosComando.getOrDefault(7,0)+hmTodosComando.getOrDefault(22,0)+hmTodosComando.getOrDefault(21,0)+hmTodosComando.getOrDefault(27,0)+hmTodosComando.getOrDefault(28,0)+hmTodosComando.getOrDefault(8,0)+hmTodosComando.getOrDefault(9,0)+hmTodosComando.getOrDefault(10,0);
            int sumatotalApoyoDIV=hmTodosApoyo.getOrDefault(7,0)+hmTodosApoyo.getOrDefault(22,0)+hmTodosApoyo.getOrDefault(21,0)+hmTodosApoyo.getOrDefault(27,0)+hmTodosApoyo.getOrDefault(28,0)+hmTodosApoyo.getOrDefault(8,0)+hmTodosApoyo.getOrDefault(9,0)+hmTodosApoyo.getOrDefault(10,0);
            int sumaEntranComandoDIV=hmEntranComando.getOrDefault(7,0)+hmEntranComando.getOrDefault(22,0)+hmEntranComando.getOrDefault(21,0)+hmEntranComando.getOrDefault(27,0)+hmEntranComando.getOrDefault(28,0)+hmEntranComando.getOrDefault(8,0)+hmEntranComando.getOrDefault(9,0)+hmEntranComando.getOrDefault(10,0);
            int sumaEntranApoyoDIV=hmEntranApoyo.getOrDefault(7,0)+hmEntranApoyo.getOrDefault(22,0)+hmEntranApoyo.getOrDefault(21,0)+hmEntranApoyo.getOrDefault(27,0)+hmEntranApoyo.getOrDefault(28,0)+hmEntranApoyo.getOrDefault(8,0)+hmEntranApoyo.getOrDefault(9,0)+hmEntranApoyo.getOrDefault(10,0);
            
            int sumatotalComandoDIVTotal2 = sumatotalComandoDIV;
            int sumatotalApoyoDIVTotal2 = sumatotalApoyoDIV;
            int sumaEntranComandoDIVTotal2 = sumaEntranComandoDIV;
            int sumaEntranApoyoDIVTotal2 = sumaEntranApoyoDIV;
            
            s+=imprimirEstadisticaUnidad(true,"", "TOTAL", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, sumatotalComandoDIV, sumaEntranComandoDIV, sumatotalApoyoDIV, sumaEntranApoyoDIV);
            
            i=20;
            s+=imprimirEstadisticaUnidad(true,"D.E.III", "Bn.I.7", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=14;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.I.8", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=16;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.I.9", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=19;
            s+=imprimirEstadisticaUnidad(true,"", "Reg.C.3", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=23;
            s+=imprimirEstadisticaUnidad(true,"", "Reg.C.5", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=4;
            s+=imprimirEstadisticaUnidad(true,"", "Reg.C.10", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=24;
            s+=imprimirEstadisticaUnidad(true,"", "Gpr.A.3", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=29;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.Ing.3", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            
            sumatotalComandoDIV=hmTodosComando.getOrDefault(20,0)+hmTodosComando.getOrDefault(14,0)+hmTodosComando.getOrDefault(16,0)+hmTodosComando.getOrDefault(19,0)+hmTodosComando.getOrDefault(23,0)+hmTodosComando.getOrDefault(4,0)+hmTodosComando.getOrDefault(24,0)+hmTodosComando.getOrDefault(29,0);
            sumatotalApoyoDIV=hmTodosApoyo.getOrDefault(20,0)+hmTodosApoyo.getOrDefault(14,0)+hmTodosApoyo.getOrDefault(16,0)+hmTodosApoyo.getOrDefault(19,0)+hmTodosApoyo.getOrDefault(23,0)+hmTodosApoyo.getOrDefault(4,0)+hmTodosApoyo.getOrDefault(24,0)+hmTodosApoyo.getOrDefault(29,0);
            sumaEntranComandoDIV=hmEntranComando.getOrDefault(20,0)+hmEntranComando.getOrDefault(14,0)+hmEntranComando.getOrDefault(16,0)+hmEntranComando.getOrDefault(19,0)+hmEntranComando.getOrDefault(23,0)+hmEntranComando.getOrDefault(4,0)+hmEntranComando.getOrDefault(24,0)+hmEntranComando.getOrDefault(29,0);
            sumaEntranApoyoDIV=hmEntranApoyo.getOrDefault(20,0)+hmEntranApoyo.getOrDefault(14,0)+hmEntranApoyo.getOrDefault(16,0)+hmEntranApoyo.getOrDefault(19,0)+hmEntranApoyo.getOrDefault(23,0)+hmEntranApoyo.getOrDefault(4,0)+hmEntranApoyo.getOrDefault(24,0)+hmEntranApoyo.getOrDefault(29,0);
            
            int sumatotalComandoDIVTotal3 = sumatotalComandoDIV;
            int sumatotalApoyoDIVTotal3 = sumatotalApoyoDIV;
            int sumaEntranComandoDIVTotal3 = sumaEntranComandoDIV;
            int sumaEntranApoyoDIVTotal3 = sumaEntranApoyoDIV;
            s+=imprimirEstadisticaUnidad(true,"", "TOTAL", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, sumatotalComandoDIV, sumaEntranComandoDIV, sumatotalApoyoDIV, sumaEntranApoyoDIV);
            
            i=25;
            s+=imprimirEstadisticaUnidad(true,"D.E.VI", "Bn.I.10", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=26;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.I.11", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=3;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.I.12", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=15;
            s+=imprimirEstadisticaUnidad(true,"", "Reg.C.7", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=6;
            s+=imprimirEstadisticaUnidad(true,"", "Reg.C.8", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=30;
            s+=imprimirEstadisticaUnidad(true,"", "Reg.C.9", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=11;
            s+=imprimirEstadisticaUnidad(true,"", "Grp.A.11", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            i=12;
            s+=imprimirEstadisticaUnidad(true,"", "Bn.Ing.4", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            
            sumatotalComandoDIV=hmTodosComando.getOrDefault(25,0)+hmTodosComando.getOrDefault(26,0)+hmTodosComando.getOrDefault(3,0)+hmTodosComando.getOrDefault(15,0)+hmTodosComando.getOrDefault(6,0)+hmTodosComando.getOrDefault(30,0)+hmTodosComando.getOrDefault(11,0)+hmTodosComando.getOrDefault(12,0);
            sumatotalApoyoDIV=hmTodosApoyo.getOrDefault(25,0)+hmTodosApoyo.getOrDefault(26,0)+hmTodosApoyo.getOrDefault(3,0)+hmTodosApoyo.getOrDefault(15,0)+hmTodosApoyo.getOrDefault(6,0)+hmTodosApoyo.getOrDefault(30,0)+hmTodosApoyo.getOrDefault(11,0)+hmTodosApoyo.getOrDefault(12,0);
            sumaEntranComandoDIV=hmEntranComando.getOrDefault(25,0)+hmEntranComando.getOrDefault(26,0)+hmEntranComando.getOrDefault(3,0)+hmEntranComando.getOrDefault(15,0)+hmEntranComando.getOrDefault(6,0)+hmEntranComando.getOrDefault(30,0)+hmEntranComando.getOrDefault(11,0)+hmEntranComando.getOrDefault(12,0);
            sumaEntranApoyoDIV=hmEntranApoyo.getOrDefault(25,0)+hmEntranApoyo.getOrDefault(26,0)+hmEntranApoyo.getOrDefault(3,0)+hmEntranApoyo.getOrDefault(15,0)+hmEntranApoyo.getOrDefault(6,0)+hmEntranApoyo.getOrDefault(30,0)+hmEntranApoyo.getOrDefault(11,0)+hmEntranApoyo.getOrDefault(12,0);
            
            int sumatotalComandoDIVTotal4 = sumatotalComandoDIV;
            int sumatotalApoyoDIVTotal4 = sumatotalApoyoDIV;
            int sumaEntranComandoDIVTotal4 = sumaEntranComandoDIV;
            int sumaEntranApoyoDIVTotal4 = sumaEntranApoyoDIV;
            s+=imprimirEstadisticaUnidad(true,"", "TOTAL", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, sumatotalComandoDIV, sumaEntranComandoDIV, sumatotalApoyoDIV, sumaEntranApoyoDIV);
            
            s+="    <tr style=\"text-align: center;background-color: #e2d4d4;font-weight: bold\">\n" +
"        <td colspan=\"2\">Total Interior del país" +
"        </td>\n" +
"        <td>\n"+(sumatotalComandoDIVTotal2+sumatotalComandoDIVTotal3+sumatotalComandoDIVTotal4)+"        </td>\n" +
"        <td>\n" +(sumaEntranComandoDIVTotal2+sumaEntranComandoDIVTotal3+sumaEntranComandoDIVTotal4)    +"        </td>\n" +
                            
"        <td >\n" +fijarNumero((Double.valueOf((sumatotalComandoDIVTotal2+sumatotalComandoDIVTotal3+sumatotalComandoDIVTotal4))/todosPostComando)*100,1)+"%        </td>\n" + 
"        <td >\n"+fijarNumero((Double.valueOf((sumaEntranComandoDIVTotal2+sumaEntranComandoDIVTotal3+sumaEntranComandoDIVTotal4))/entranPostComando)*100,1)+"%        </td>\n" +
"        <td>\n"+(sumatotalApoyoDIVTotal2+sumatotalApoyoDIVTotal3+sumatotalApoyoDIVTotal4)+"        </td>\n" +
"        <td>\n"+ (sumaEntranApoyoDIVTotal2+sumaEntranApoyoDIVTotal3+sumaEntranApoyoDIVTotal4) +"        </td>\n" +
                            
"        <td >\n"+ fijarNumero((Double.valueOf((sumatotalApoyoDIVTotal2+sumatotalApoyoDIVTotal3+sumatotalApoyoDIVTotal4))/todosPostApoyo)*100,1) +"%       </td>\n" + 
"        <td >\n" +fijarNumero((Double.valueOf((sumaEntranApoyoDIVTotal2+sumaEntranApoyoDIVTotal3+sumaEntranApoyoDIVTotal4))/entranPostApoyo)*100,1)+"%        </td>\n" +
"    </tr>\n" ;
            
            s+="</table> </body>";
                    
            out.print(s);
            out.print("<h4>Cuadro resumen</h4>");
            s=   "<table id=\"contenido\" style=\"margin-top: 20px;\"><tr style=\"text-align: center; background-color:#e2d4d4;font-weight: bold\">\n" +
"        <td>Lugar de Inscripción\n" +
"            \n" +
"        </td>\n" +
"        <td colspan=\"4\">\n" +
"            Cuerpo Comando\n" +
"        </td>\n" +
"        <td colspan=\"4\">\n" +
"            Curso de Apoyo de Servicios y Combate\n" +
"        </td>\n" +
"    </tr>\n" +
"    <tr style=\"text-align: center; background-color:#e2d4d4;font-weight: bold\">\n" +
"        <td>\n" +
"            \n" +
"        </td>\n" +
"        <td>\n" +
"            Postulantes\n" +
"        </td>\n" +
"        <td>\n" +
"            Ingresos\n" +
"        </td>\n" +
"        <td colspan=\"2\">\n" +
"            Porcentaje en total\n" +
"        </td>\n" +
"        <td>\n" +
"            Postulantes\n" +
"        </td>\n" +
"        <td>\n" +
"            Ingresos\n" +
"        </td>\n" +
"        <td colspan=\"2\">\n" +
"            Porcentaje en total\n" +
"        </td>\n" +
"    </tr>\n";
            int sumatotalComandoEM=hmTodosComando.getOrDefault(5,0)+hmTodosComando.getOrDefault(31,0)+hmTodosComando.getOrDefault(2,0)+hmTodosComando.getOrDefault(32,0)+hmTodosComando.getOrDefault(33,0)+hmTodosComando.getOrDefault(34,0);
            int sumaEntranComandoEM=hmEntranComando.getOrDefault(5,0)+hmEntranComando.getOrDefault(31,0)+hmEntranComando.getOrDefault(2,0)+hmEntranComando.getOrDefault(32,0)+hmEntranComando.getOrDefault(33,0)+hmEntranComando.getOrDefault(34,0);
            int sumatotalApoyoEM=hmTodosApoyo.getOrDefault(5,0)+hmTodosApoyo.getOrDefault(31,0)+hmTodosApoyo.getOrDefault(2,0)+hmTodosApoyo.getOrDefault(32,0)+hmTodosApoyo.getOrDefault(33,0)+hmTodosApoyo.getOrDefault(34,0);
            int sumaEntranApoyoEM=hmEntranApoyo.getOrDefault(5,0)+hmEntranApoyo.getOrDefault(31,0)+hmEntranApoyo.getOrDefault(2,0)+hmEntranApoyo.getOrDefault(32,0)+hmEntranApoyo.getOrDefault(33,0)+hmEntranApoyo.getOrDefault(34,0);
            
            s+=imprimirEstadisticaUnidad(false,"", "E.M.", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, sumatotalComandoEM, sumaEntranComandoEM, sumatotalApoyoEM, sumaEntranApoyoEM);
            
            i=13;
            s+=imprimirEstadisticaUnidad(false,"", "L.M.G.A.", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, hmTodosComando.getOrDefault(i,0), hmEntranComando.getOrDefault(i,0), hmTodosApoyo.getOrDefault(i,0), hmEntranApoyo.getOrDefault(i,0));
            
            s+=imprimirEstadisticaUnidad(false,"", "D.E.II", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, sumatotalComandoDIVTotal2, sumaEntranComandoDIVTotal2, sumatotalApoyoDIVTotal2, sumaEntranApoyoDIVTotal2);
            s+=imprimirEstadisticaUnidad(false,"", "D.E.III", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, sumatotalComandoDIVTotal3, sumaEntranComandoDIVTotal3, sumatotalApoyoDIVTotal3, sumaEntranApoyoDIVTotal3);
            s+=imprimirEstadisticaUnidad(false,"", "D.E.IV", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, sumatotalComandoDIVTotal4, sumaEntranComandoDIVTotal4, sumatotalApoyoDIVTotal4, sumaEntranApoyoDIVTotal4);
            s+=imprimirEstadisticaUnidad(false,"", "TOTAL", todosPostComando, todosPostApoyo, entranPostComando, entranPostApoyo, todosPostComando, entranPostComando, todosPostApoyo, entranPostApoyo);
            out.print(s+"</table></body>");
        }
        }
        else{
            sesion.setAttribute("login", "vacio");
            response.sendRedirect("");
        }
    }
        
    public static double fijarNumero(double numero, int digitos) {
        double resultado;
        resultado = numero * Math.pow(10, digitos);
        resultado = Math.round(resultado);
        resultado = resultado/Math.pow(10, digitos);
        return resultado;
    }
    private String imprimirEstadisticaUnidad(boolean div,String division, String unidad,int todosPostComando,int todosPostApoyo,int entranPostComando,int entranPostApoyo, int TodosComando,int EntranComando,int TodosApoyo,int EntranApoyo){
        String color="";
        if(unidad.equals("TOTAL")){
            color="; background-color:#e2d4d4;font-weight: bold";
        }
        String border="";
        if(div && division.equals("")){
            border="; border:none;border-left:1px solid #000000;";
        }   
        String s="    <tr style=\"text-align: center"+color+"\">\n" ;
         if(div){  
s+="        <td style=\"background-color:#ffffff"+border+" \" >\n" +division+
"        </td>\n" ;
        }           
s+="        <td>\n" +unidad+
"        </td>\n" +
"        <td>\n"+TodosComando+"        </td>\n" +
"        <td>\n" +EntranComando+"        </td>\n" +
                            
"        <td >\n" +fijarNumero((Double.valueOf(TodosComando)/todosPostComando)*100,1)+"%        </td>\n" + 
"        <td >\n"+fijarNumero((Double.valueOf(EntranComando)/entranPostComando)*100,1)+"%        </td>\n" +
"        <td>\n"+TodosApoyo+"        </td>\n" +
"        <td>\n"+ EntranApoyo +"        </td>\n" +
                            
"        <td >\n"+ fijarNumero((Double.valueOf(TodosApoyo)/todosPostApoyo)*100,1) +"%       </td>\n" + 
"        <td >\n" +fijarNumero((Double.valueOf(EntranApoyo)/entranPostApoyo)*100,1)+"%        </td>\n" +
"    </tr>\n" ;
    return s;
    
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
