/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gisel
 */
public class ManejadorHistorialBD {
    private Connection connection;

    public ManejadorHistorialBD() {
        connection = ConexionDB.GetConnection();
    }
    public ArrayList<Integer> getAniosHistorial(){
        ArrayList<Integer> al= new ArrayList<>();
        try {
            Statement s= connection.createStatement();
            String sql = "";
            sql="SELECT anio FROM postulantes.historialpostulantes group by anio order by anio desc";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                al.add(rs.getInt("anio"));
            }
        }
        catch(Exception e){
            
        }
        return al;
    }
    public ArrayList<Postulante> getPostulantesListarHistorial(RecordPostulanteFiltro rf, int usuario,int carrera, int anio){
         ArrayList<Postulante> al= new ArrayList<>();
        ManejadorCodigoBD mc = new ManejadorCodigoBD();
        try {
            Statement s= connection.createStatement();
            Usuario u = mc.getUsuario(usuario);
            String sql = "";
            String filtro = ManejadorPostulanteDB.getFiltroSQL(rf);
            
            if(u.isAdmin()){
                    sql="SELECT * FROM postulantes.historialpostulantes where anio="+anio+" and carrera=" + carrera + filtro + " order by primerapellido asc, segundoApellido asc";
                
                    //System.out.print(sql);
            }
            else{
                    sql="SELECT * FROM postulantes.historialpostulantes where anio="+anio+" and UnidadInsc="+ usuario + " and carrera=" + carrera + filtro + " order by primerapellido asc, segundoApellido asc";
               
            }
            
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                int id=-1;
                if (rf!=null){
                    if( rf.condicional.equals("S")&& id==-1){
                        al.add(new Postulante(rs.getInt("ci"), rs.getString("primerNombre"),rs.getString("segundoNombre"),rs.getString("primerApellido"),rs.getString("segundoApellido"),mc.getCarrera(rs.getInt("carrera")),id,mc.getUsuario(rs.getInt("unidadInsc"))));
                    }
                    else{
                        if (rf.condicional.equals("N")&& id!=-1){
                            al.add(new Postulante(rs.getInt("ci"), rs.getString("primerNombre"),rs.getString("segundoNombre"),rs.getString("primerApellido"),rs.getString("segundoApellido"),mc.getCarrera(rs.getInt("carrera")),id,mc.getUsuario(rs.getInt("unidadInsc"))));
                        } 
                        else{
                            al.add(new Postulante(rs.getInt("ci"), rs.getString("primerNombre"),rs.getString("segundoNombre"),rs.getString("primerApellido"),rs.getString("segundoApellido"),mc.getCarrera(rs.getInt("carrera")),id,mc.getUsuario(rs.getInt("unidadInsc"))));
                        }
                    }
                }
                else{
                    al.add(new Postulante(rs.getInt("ci"), rs.getString("primerNombre"),rs.getString("segundoNombre"),rs.getString("primerApellido"),rs.getString("segundoApellido"),mc.getCarrera(rs.getInt("carrera")),id,mc.getUsuario(rs.getInt("unidadInsc"))));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public String imprimirFichaPostulante(int ci,int usuario, int anio){
        String impr="";
        try {
            Statement s= connection.createStatement();
            ManejadorCodigoBD mc = new ManejadorCodigoBD();
            Usuario u = mc.getUsuario(usuario);
            String sql = "";
            if(u.isAdmin()){
                sql="Select * from postulantes.historialpostulantes left join postulantes.historialnotas on historialpostulantes.ci = historialnotas.ci left join  postulantes.historialresultados on historialpostulantes.ci = historialresultados.ci where historialpostulantes.anio="+anio+" and historialpostulantes.ci="+ ci;
            }
            else{
                sql="Select * from postulantes.historialpostulantes left join postulantes.historialnotas on historialpostulantes.ci = historialnotas.ci left join  postulantes.historialresultados on historialpostulantes.ci = historialresultados.ci where historialpostulantes.anio="+anio+" and historialpostulantes.ci="+ ci + " and UnidadInsc="+ usuario;
            }
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                       impr="<table class=\"resultsTable\" style=\"margin-left: 0%; margin-right: 0%;word-wrap: break-word\">\n" +
"            <tr>\n" +
"                <td>\n" +
"                    <p align=\"left\">Escuela Militar</p>\n" +
"                </td>\n" +
"                <td>\n" +
"                     <p align=\"right\">Jefatura de Estudios</p>\n" +
"                </td>\n" +
"            </tr>\n" +
"            <tr class=\"noExl\">\n" +
"                <td width=\"40%\" style=\"vertical-align: top;border: solid\">\n" +
"                    <table  style=\"table-layout: fixed; width: 100%\">\n" +
"                        <tr>\n" +
"                            <td>\n" +
"                            <h4 style=\"padding: 0px\">Datos Personales:</h4>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>CARRERA: </td>\n" +
"                            <td>"+ mc.getCarrera(rs.getInt("carrera")).getDescripcion() +"\n" +
"                            </td>\n" +
"\n" +
                            "                        <tr>\n" +
"                            <td>Primer nombre: </td>\n" +
"                            <td>"+ rs.getString("primerNombre") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Segundo nombre: </td>\n" +
"                            <td>"+ rs.getString("segundoNombre") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Primer apellido: </td>\n" +
"                            <td>"+ rs.getString("primerApellido") +"</td>\n" +
"                        </tr>\n" +
                            
"                        <tr>\n" +
"                            <td>Segundo apellido: </td>\n" +
"                            <td>"+ rs.getString("segundoApellido") +"</td>\n" +
"                        </tr>\n" +
"                        </tr>\n" +
                            "<tr>\n" +
"                            <td>Unidad Inscriptora: </td>\n" +
"                            <td>"+ mc.getUsuario(rs.getInt("unidadInsc")).getNombreMostrar() +
"                            </td>\n" +
"                        </tr>"+
"                        <tr>\n" +
"                            <td>C.I.: </td>\n" +
"                            <td>"+ ci +"</td>\n" +
"                        </tr>\n" +

                            
"                        <tr>\n" +
"                            <td>Sexo: </td>\n" +
"                            <td>"+ rs.getString("sexo") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Fecha de Nacimiento: </td>\n" +
"                            <td>"+ ManejadorCodigoBD.fechaFormatoDDMMAAAA(rs.getDate("FechaNac"))+"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento de Nacimiento: </td>\n" +
"                            <td>"+ mc.getDepartamento(rs.getInt("departamentoNac")).getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Localidad de Nacimiento: </td>\n" +
"                            <td>"+ rs.getString("localidadNac") +"</td>\n" +
"                        </tr>\n" +
"\n" +
"                        <tr>\n" +
"                            <td>Credencial: </td>\n" +
"                            <td>Serie: "+ rs.getString("cc") +"\n" +
"                                N&deg;: "+ rs.getInt("ccNro") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Estado Civil: </td>\n" +
"                            <td>"+ mc.getEstadoCivil(rs.getInt("estadoCivil")).getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Domicilio: </td>\n" +
"                            <td>"+ rs.getString("domicilio") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento: </td>\n" +
"                            <td>"+ mc.getDepartamento(rs.getInt("departamento")).getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Localidad: </td>\n" +
"                            <td>"+  rs.getString("localidad") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Tel&eacute;fono: </td>\n" +
"                            <td>"+  rs.getString("telefono") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Correo Electr&oacute;nico: </td>\n" +
"                            <td>"+  rs.getString("email") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Reingreso: </td>\n" +
"                            <td>";
                    if(rs.getBoolean("reingreso")){
                        impr+="SI";
                    }
                    else{
                        impr+="NO";
                    }
                    impr+="</td>\n" +
"                        <tr>\n" +
"                            <td>A cursar en el instituto: </td>\n" +
"                            <td>";
                    if(rs.getBoolean("quinto")){
                        impr+="Secundaria";
                    }
                    else{
                        impr+="6to. aprobado";
                    }
                    impr+="</td>\n" +
"\n" +
"                        </tr>\n" ;
                    if (rs.getBoolean("quinto")){
impr+="                  <tr>\n" +
"                            <td>Orientación: </td>\n" +
"                            <td>"+mc.getOrientacion(rs.getInt("orientacion")).getDescripcion()+"</td>\n" +
"                        </tr>\n" ;
                    }
impr+="                        <tr>\n" +
"                            <td>LMGA: </td>\n" +
"                            <td>";
                                if(rs.getBoolean("lmga")){
                                    impr+="SI";
                                }
                                else{
                                    impr+="NO";
                                } 
                                impr+="</td>\n" +
"                        </tr>\n" ;
                                if(rs.getBoolean("lmga")){
impr+="                        <tr>\n" +
"                            <td>PASE DIRECTO: </td>\n" +
"                            <td>";
                                if(rs.getBoolean("paseDirecto")){
                                    impr+="SI";
                                }
                                else{
                                    impr+="NO";
                                } 
                                impr+="</td>\n" +
"                        </tr>\n" ;
                                    if(rs.getBoolean("paseDirecto")){
impr+="                        <tr>\n" +
"                            <td>Nota Pase Directo: </td>\n" +
"                            <td>"+ rs.getInt("notaPaseDirecto") +"</td>\n" +
"                        </tr>\n" ;
                                    }
                                }
impr+="                        <tr>\n" +
"                            <td>Materias Pendientes: </td>\n" +
"                            <td><textarea readonly rows=" +(Postulante.contarCaracteres(rs.getString("materiasPendientes"), '\n')+1)+ " style=\"resize: none; overflow: hidden;\">"+ rs.getString("materiasPendientes") +"</textarea></td>\n" +
"                        </tr>\n" ;    
/*"                        <tr>\n" +
"                            <td>Presenta certificado de Buena Conducta: </td>\n" +
"                            <td>";
                             if(buenaConducta){
                                 impr+="SI";
                                }
                                else{
                                    impr+="NO";
                                } 
                            impr+="</td>\n" +
"                        </tr>\n" +*/
impr+="                        <tr>\n" +
"                            <td>Personal Subalterno: </td>\n" +
"                            <td>";
                             if(rs.getBoolean("ps")){
                                 impr+="SI";
                                }
                                else{
                                    impr+="NO";
                                } 
                            impr+="</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Personal Subalterno del Ejercito: </td>\n" +
"                            <td>";
                             if(rs.getBoolean("ps")){
                                 if(rs.getBoolean("psEjercito")){
                                     impr+="SI";
                                }
                                else{
                                    impr+="NO";
                                } 
                             }
                             else{
                                 impr+="-------";
                             } 
                             impr+="</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Cantidad de hijos: </td>\n" +
"                            <td>"+ rs.getInt("hijos") +"</td>\n" +
"\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Necesita Alojamiento: </td>\n" +
"                            <td>";
                                if(rs.getBoolean("alojamiento")){
                                    impr+="SI";
                                }
                                else{
                                    impr+="NO";
                                } 
                                impr+="</td>\n" +
"                        </tr>\n" +                                     
"                        \n" +
"                    </table>\n" +
"                </td>\n" +
"                <td width=\"40%\" style=\"vertical-align: top;border: solid\" >\n" +
"                    <table  style=\"width: 100%; table-layout: fixed\">\n" +
"                        <tr>\n" +
"                            <td>\n" +
"                                <h4 style=\"padding: 0px\">Datos Patron&iacute;micos:</h4>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>\n" +
"                                <b>Padre:</b>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Nombre Completo: </td>\n" +
"                            <td style=\"size: 50\">"+ rs.getString("pNombreComp") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Fecha de Nacimiento: </td>\n" +
"                            <td>";
                             if(rs.getDate("pFechaNac")!=null){
                                 impr+=ManejadorCodigoBD.fechaFormatoDDMMAAAA(rs.getDate("pFechaNac"));
                             }
                             impr+="</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento de Nacimiento: </td>\n" +
"                            <td>"+ mc.getDepartamento(rs.getInt("pDepartamentoNac")).getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Localidad de Nacimiento: </td>\n" +
"                            <td>"+ rs.getString("pLocalidadNac") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Estado Civil: </td>\n" +
"                            <td>"+ mc.getEstadoCivil(rs.getInt("pEstadoCivil")).getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Domicilio: </td>\n" +
"                            <td>"+ rs.getString("pDomicilio") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento: </td>\n" +
"                            <td>"+ mc.getDepartamento(rs.getInt("pDepartamento")).getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"\n" +
"                        <tr>\n" +
"                            <td>Localidad: </td>\n" +
"                            <td>"+ rs.getString("pLocalidad") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Tel&eacute;fono: </td>\n" +
"                            <td>"+ rs.getString("ptelefono") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Profesi&oacute;n: </td>\n" +
"                            <td>"+ rs.getString("pProfesion") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Lugar de trabajo: </td>\n" +
"                            <td>"+ rs.getString("pLugarTrabajo") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>\n" +
"                                <b>Madre:</b>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Nombre Completo: </td>\n" +
"                            <td style=\"size: 50\">"+ rs.getString("mNombreComp") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Fecha de Nacimiento: </td>\n" +
"                            <td>";
                             if(rs.getDate("mFechaNac")!=null){
                                 impr+=ManejadorCodigoBD.fechaFormatoDDMMAAAA(rs.getDate("mFechaNac"));
                             }
                             impr+="</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento de Nacimiento: </td>\n" +
"                            <td>"+ mc.getDepartamento(rs.getInt("mDepartamentoNac")).getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Localidad de Nacimiento: </td>\n" +
"                            <td>"+ rs.getString("mLocalidadNac") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Estado Civil: </td>\n" +
"                            <td>"+ mc.getEstadoCivil(rs.getInt("mEstadoCivil")).getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Domicilio: </td>\n" +
"                            <td>"+ rs.getString("mDomicilio") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento: </td>\n" +
"                            <td>"+ mc.getDepartamento(rs.getInt("mDepartamento")).getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"\n" +
"                        <tr>\n" +
"                            <td>Localidad: </td>\n" +
"                            <td>"+ rs.getString("mLocalidad") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Tel&eacute;fono: </td>\n" +
"                            <td>"+ rs.getString("mtelefono") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Profesi&oacute;n: </td>\n" +
"                            <td>"+ rs.getString("mProfesion") +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Lugar de trabajo: </td>\n" +
"                            <td>"+ rs.getString("mLugarTrabajo") +"</td>\n" +
"                        </tr>\n" +
"                    </table>\n" +
"                        \n" +
"                </td>\n" +
"            </tr>\n" +
"            <tr class=\"noExl\"> \n" +
"                <td colspan=\"2\">\n" +
"                    <table>\n" +
"                        <tr>\n" +
"                            <td>\n" +
"                                <b>Observaciones:</b>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <textarea readonly rows=" +(Postulante.contarCaracteres(rs.getString("observaciones"), '\n')+1)+ " style=\"resize: none\" cols=\"80\"  name=\"observaciones\">"+ rs.getString("observaciones") +"</textarea>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                        \n" +
"                    </table>\n" +
"                    \n" +
"                </td>\n" +
"            </tr>\n" +
"        </table> <h1 style='page-break-after:always' > </h1>";
       
            }
        }
        catch(Exception e){
             System.out.print(e.getMessage());
        }
        System.out.print(impr);
        return impr;
    }
}
