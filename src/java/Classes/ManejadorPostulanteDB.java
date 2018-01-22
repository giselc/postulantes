/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Gisel
 */
public class ManejadorPostulanteDB {
    private Connection connection;

    public ManejadorPostulanteDB() {
        connection = ConexionDB.GetConnection();
    }
    
    public Postulante getPostulante(int ci, int usuario, int anio){
        Postulante p= null;
        try {
            Statement s= connection.createStatement();
            ManejadorCodigoBD mc = new ManejadorCodigoBD();
            Usuario u = mc.getUsuario(usuario);
            String sql = "";
            if(u.isAdmin()){
                sql="Select * from postulantes.postulantes left join postulantes.documentos on postulantes.ci = documentos.ci and postulantes.anio = documentos.anio where postulantes.anio="+anio+" and postulantes.ci="+ ci;
            }
            else{
                sql="Select * from postulantes.postulantes left join postulantes.documentos on postulantes.ci = documentos.ci and postulantes.anio = documentos.anio where postulantes.anio="+anio+" and postulantes.ci="+ ci + " and UnidadInsc="+ usuario;
            }
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                p= new Postulante();
                mc= new ManejadorCodigoBD();
                //DATOS BASICOS
                p.setFoto(rs.getBlob("foto"));
                p.setFotoCIAnverso(rs.getBlob("fotoCIAnverso"));
                p.setFotoCIReverso(rs.getBlob("fotoCIReverso"));
                p.setFotoF69Hoja1(rs.getBlob("fotoF69Hoja1"));
                p.setFotoF69Hoja2(rs.getBlob("fotoF69Hoja2"));
                p.setFotoF69Hoja3(rs.getBlob("fotoF69Hoja3"));
                p.setFotoF1Hoja1(rs.getBlob("fotoF1Hoja1"));
                p.setFotoF1Hoja2(rs.getBlob("fotoF1Hoja2"));
                p.setCi(rs.getInt("CI"));
                p.setPrimerNombre(rs.getString("PrimerNombre"));
                p.setPrimerApellido(rs.getString("PrimerApellido"));
                p.setSegundoNombre(rs.getString("SegundoNombre"));
                p.setSegundoApellido(rs.getString("SegundoApellido"));
                p.setUnidadInsc(mc.getUsuario(rs.getInt("unidadInsc")));
                p.setFechaNac(rs.getDate("FechaNac"));
                p.setSexo(rs.getString("Sexo"));
                p.setDepartamentoNac(mc.getDepartamento(rs.getInt("DepartamentoNac")));
                p.setLocalidadNac(rs.getString("LocalidadNac"));
                p.setCc(rs.getString("CC"));
                p.setCcNro(rs.getInt("CCNro"));
                p.setEstadoCivil(mc.getEstadoCivil(rs.getInt("EstadoCivil")));
                p.setDomicilio(rs.getString("Domicilio"));
                p.setDepartamento(mc.getDepartamento(rs.getInt("Departamento")));
                p.setLocalidad(rs.getString("Localidad"));
                p.setTelefono(rs.getString("Telefono"));
                p.setEmail(rs.getString("Email"));
                p.setQuinto(rs.getBoolean("Quinto"));
                p.setOrientacion(mc.getOrientacion(rs.getInt("Orientacion")));
                p.setReingreso(rs.getBoolean("Reingreso"));
                p.setLmga(rs.getBoolean("Lmga"));
                p.setPaseDirecto(rs.getBoolean("PaseDirecto"));
                p.setNotaPaseDirecto(rs.getDouble("NotaPaseDirecto"));
                p.setMateriasPendientes(rs.getString("materiasPendientes"));
                p.setAlojamiento(rs.getBoolean("Alojamiento"));
                p.setNsp(rs.getBoolean("Nsp"));
                p.setRenuncio(rs.getBoolean("Renuncio"));
                p.setBuenaConducta(rs.getBoolean("BuenaConducta"));
                p.setPs(rs.getBoolean("PS"));
                p.setPsEjercito(rs.getBoolean("PSEjercito"));
                p.setHijos(rs.getInt("HIJOS"));
                p.setObservaciones(rs.getString("OBSERVACIONES"));
                
                //DATOS PATRONIMICOS
                //PADRE
                p.setpNombreComp(rs.getString("PNombreComp"));
                p.setpFechaNac(rs.getDate("PFechaNac"));
                p.setpDepartamentoNac(mc.getDepartamento(rs.getInt("PDepartamentoNac")));
                p.setpLocalidadNac(rs.getString("PLocalidadNac"));
                p.setpEstadoCivil(mc.getEstadoCivil(rs.getInt("PEstadoCivil")));
                p.setpDomicilio(rs.getString("PDomicilio"));
                p.setpDepartamento(mc.getDepartamento(rs.getInt("PDepartamento")));
                p.setpLocalidad(rs.getString("PLocalidad"));
                p.setptelefono(rs.getString("PTelefono"));
                p.setpProfesion(rs.getString("PProfesion"));
                p.setpLugarTrabajo(rs.getString("PLugarTrabajo"));
                //MADRE
                p.setmNombreComp(rs.getString("MNombreComp"));
                p.setmFechaNac(rs.getDate("MFechaNac"));
                p.setmDepartamentoNac(mc.getDepartamento(rs.getInt("MDepartamentoNac")));
                p.setmLocalidadNac(rs.getString("MLocalidadNac"));
                p.setmEstadoCivil(mc.getEstadoCivil(rs.getInt("MEstadoCivil")));
                p.setmDomicilio(rs.getString("MDomicilio"));
                p.setmDepartamento(mc.getDepartamento(rs.getInt("MDepartamento")));
                p.setmLocalidad(rs.getString("MLocalidad"));
                p.setmtelefono(rs.getString("MTelefono"));
                p.setmProfesion(rs.getString("MProfesion"));
                p.setmLugarTrabajo(rs.getString("MLugarTrabajo"));
                p.setCarrera(mc.getCarrera(rs.getInt("carrera")));
                Statement s1= connection.createStatement();
                String sql1="Select * from postulantes.comando where ci="+ ci;
                ResultSet rs1=s1.executeQuery(sql1);
                if (rs1.next()){
                    p.setId(rs1.getInt(1));
                }
                else{
                    sql1="Select * from postulantes.apoyo where ci="+ ci;
                    rs1=s1.executeQuery(sql1);
                    if (rs1.next()){
                        p.setId(rs1.getInt(1));
                    }
                    else{
                        p.setId(0);
                    }
                }
                p.setFechaAlta(this.getFechaAlta(ci));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
    
    public ArrayList<Postulante> getPostulantesPasaje(int ci, int carrera){
        ArrayList<Postulante> al= new ArrayList();
        
        try {
            Statement s= connection.createStatement();
            String sql="";
            if(ci==0){
                sql="Select postulantes.ci, carrera, primernombre, primerapellido, segundoNombre, segundoapellido, sexo, fechaNac, departamentoNac, localidadNac, cc, ccnro, estadoCivil, domicilio, departamento, localidad, telefono,"+
                    "pNombreComp, PFechaNac,PDepartamentoNac, PEstadoCivil, PDomicilio, PDepartamento, PLocalidad, PTelefono, PProfesion, PLugarTrabajo,"+
                    "mNombreComp, mFechaNac,mDepartamentoNac, mEstadoCivil, mDomicilio, mDepartamento, mLocalidad, mTelefono, mProfesion, mLugarTrabajo," +
                    "resultado from postulantes.postulantes left join postulantes.resultados on postulantes.ci = resultados.ci where postulantes.anio="+ManejadorPostulanteDB.getAnioPostula()+" and resultado=0 and carrera="+carrera;
            }
            else{
                sql="Select postulantes.ci, carrera, primernombre, primerapellido, segundoNombre, segundoapellido, sexo, fechaNac, departamentoNac, localidadNac, cc, ccnro, estadoCivil, domicilio, departamento, localidad, telefono,"+
                    "pNombreComp, PFechaNac,PDepartamentoNac, PEstadoCivil, PDomicilio, PDepartamento, PLocalidad, PTelefono, PProfesion, PLugarTrabajo,"+
                    "mNombreComp, mFechaNac,mDepartamentoNac, mEstadoCivil, mDomicilio, mDepartamento, mLocalidad, mTelefono, mProfesion, mLugarTrabajo," +
                    "resultado from postulantes.postulantes left join postulantes.resultados on postulantes.ci = resultados.ci where postulantes.anio="+ManejadorPostulanteDB.getAnioPostula()+" and postulantes.ci="+ci;
            }
            Postulante p= null;
            ManejadorCodigoBD  mc= new ManejadorCodigoBD();
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                p= new Postulante();
                //DATOS BASICOS
                p.setCi(rs.getInt("CI"));
                p.setPrimerNombre(rs.getString("primerNombre"));
                p.setPrimerApellido(rs.getString("primerApellido"));
                p.setSegundoNombre(rs.getString("segundoNombre"));
                p.setSegundoApellido(rs.getString("segundoApellido"));
                p.setFechaNac(rs.getDate("FechaNac"));
                p.setSexo(rs.getString("Sexo"));
                p.setDepartamentoNac(mc.getDepartamento(rs.getInt("DepartamentoNac")));
                p.setLocalidadNac(rs.getString("LocalidadNac"));
                p.setCc(rs.getString("CC"));
                p.setCcNro(rs.getInt("CCNro"));
                p.setEstadoCivil(mc.getEstadoCivil(rs.getInt("EstadoCivil")));
                p.setDomicilio(rs.getString("Domicilio"));
                p.setDepartamento(mc.getDepartamento(rs.getInt("Departamento")));
                p.setLocalidad(rs.getString("Localidad"));
                p.setTelefono(rs.getString("Telefono"));


                //DATOS PATRONIMICOS
                //PADRE
                p.setpNombreComp(rs.getString("PNombreComp"));
                p.setpFechaNac(rs.getDate("PFechaNac"));
                p.setpDepartamentoNac(mc.getDepartamento(rs.getInt("PDepartamentoNac")));
                p.setpEstadoCivil(mc.getEstadoCivil(rs.getInt("PEstadoCivil")));
                p.setpDomicilio(rs.getString("PDomicilio"));
                p.setpDepartamento(mc.getDepartamento(rs.getInt("PDepartamento")));
                p.setpLocalidad(rs.getString("PLocalidad"));
                p.setptelefono(rs.getString("PTelefono"));
                p.setpProfesion(rs.getString("PProfesion"));
                p.setpLugarTrabajo(rs.getString("PLugarTrabajo"));
                //MADRE
                p.setmNombreComp(rs.getString("MNombreComp"));
                p.setmFechaNac(rs.getDate("MFechaNac"));
                p.setmDepartamentoNac(mc.getDepartamento(rs.getInt("MDepartamentoNac")));
                p.setmEstadoCivil(mc.getEstadoCivil(rs.getInt("MEstadoCivil")));
                p.setmDomicilio(rs.getString("MDomicilio"));
                p.setmDepartamento(mc.getDepartamento(rs.getInt("MDepartamento")));
                p.setmLocalidad(rs.getString("MLocalidad"));
                p.setmtelefono(rs.getString("MTelefono"));
                p.setmProfesion(rs.getString("MProfesion"));
                p.setmLugarTrabajo(rs.getString("MLugarTrabajo"));

                p.setCarrera(mc.getCarrera(rs.getInt("carrera")));
                al.add(p);
            }


        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return al;
    } 
            
    public String getFiltroSQL(RecordPostulanteFiltro rf){ 
        ManejadorCodigoBD mc =  new ManejadorCodigoBD(); 
        String filtro = "";
        String filtroMostrar = "";
        if (rf!=null){
            if(rf.lmga.equals("S")){
                filtro+= " and lmga = 1";
                filtroMostrar += "LMGA = SI - ";
            }
            else{
                if (rf.lmga.equals("N")){
                    filtro+= " and lmga = 0";
                    filtroMostrar += "LMGA = NO - ";
                }
            }
            if(rf.alojamiento.equals("S")){
                filtro+= " and alojamiento = 1";
                filtroMostrar += "ALOJAMIENTO = SI - ";
            }
            else{
                if (rf.alojamiento.equals("N")){
                    filtro+= " and alojamiento = 0";
                    filtroMostrar += "ALOJAMIENTO = NO - ";
                }
            }
            if(rf.nsp.equals("S")){
                filtro+= " and nsp = 1";
                filtroMostrar += "NSP = SI - ";
            }
            else{
                if (rf.nsp.equals("N")){
                    filtro+= " and NSP = 0";
                    filtroMostrar += "NSP = NO - ";
                }
            }
            if(rf.renuncio.equals("S")){
                filtro+= " and renuncio = 1";
                filtroMostrar += "Renuncio = SI - ";
            }
            else{
                if (rf.renuncio.equals("N")){
                    filtro+= " and renuncio = 0";
                    filtroMostrar += "Renuncio = NO - ";
                }
            }
            if(rf.condicional.equals("S")){
                filtroMostrar += "CONDICIONAL = SI - ";
            }
            else{
                if (rf.condicional.equals("N")){
                    filtroMostrar += "condicional = NO - ";
                }
            }
            if(rf.anio_aprobado.equals("5")){
                filtro += " and quinto = 1";
                filtroMostrar += "A CURSAR EN EL INSTITUTO = Secundaria - ";
                if(rf.orientacion.equals("SH")){
                    filtro+= " and orientacion = 1";
                    filtroMostrar += "ORIENTACION = SH - ";
                }
                else{
                    if (rf.orientacion.equals("SE")){
                        filtro+= " and orientacion = 2";
                        filtroMostrar += "ORIENTACION = SE - ";
                    }
                }
            }
            else{
                if(rf.anio_aprobado.equals("6")){
                    filtro += " and quinto = 0";
                    filtroMostrar += "A CURSAR EN EL INSTITUTO = 6to Aprobado - ";
                }
            }
            if(rf.ps.equals("S")){
                filtro += " and ps = 1";
                filtroMostrar += "PS = SI - ";
                if(rf.psEjercito.equals("S")){
                    filtro+= " and psEjercito = 1";
                    filtroMostrar += "EJERCITO = SI - ";
                }
                else{
                    if (rf.psEjercito.equals("N")){
                        filtro+= " and psEjercito = 0";
                        filtroMostrar += "EJERCITO = NO - ";
                    }
                }
            }
            else{
                if(rf.ps.equals("N")){
                    filtro += " and ps = 0";
                    filtroMostrar += "PS = SI - ";
                }
            }
            if (rf.sexo.equals("M")){
                filtro+=" and sexo= 'M'";
                filtroMostrar += "SEXO = M - ";
            }
            else{
                if(rf.sexo.equals("F")){
                    filtro+=" and sexo= 'F'";
                    filtroMostrar += "SEXO = F - ";
                }
            }
            if (rf.canthijos!=null){
                filtroMostrar += "HIJOS = ";
                int i=0;
                for (String s : rf.canthijos){
                    if(s.equals("4")){
                        filtroMostrar += "+ de 3,";
                    }
                    else{
                        filtroMostrar += s+",";
                    }
                    if (i==0){
                        filtro += " and (hijos= "+s;
                    }
                    else{
                        filtro += " or hijos= "+s;
                    }
                    i++;
                    if(i==rf.canthijos.length){
                         filtro += ")";
                    }
                    
                    
                }
                filtroMostrar= filtroMostrar.substring(0, filtroMostrar.lastIndexOf(","));
                filtroMostrar += " - ";
            }
            Departamento d;
            if(rf.depNac!=null){
                filtroMostrar += "DEPARTAMENTO NACIMIENTO = ";
                int i= 0;
                for (String s : rf.depNac){
                    if (i==0){
                        filtro += " and (departamentoNac= "+s;
                    }
                    else{
                        filtro += " or departamentoNac= "+s;
                    }
                    i++;
                    if(i==rf.depNac.length){
                         filtro += ")";
                    }
                    d= mc.getDepartamento(Integer.valueOf(s));
                    filtroMostrar += d.getDescripcion()+",";
                }
                filtroMostrar= filtroMostrar.substring(0, filtroMostrar.lastIndexOf(","));
                filtroMostrar += " - ";
            }
            if(rf.depDom!=null){
                filtroMostrar += "DEPARTAMENTO DOMICILIO = ";
                int i=0;
                for (String s : rf.depDom){
                    if (i==0){
                        filtro += " and (departamento= "+s;
                    }
                    else{
                        filtro += " or departamento= "+s;
                    }
                    i++;
                    if(i==rf.depDom.length){
                         filtro += ")";
                    }
                    d= mc.getDepartamento(Integer.valueOf(s));
                    filtroMostrar += d.getDescripcion()+",";
                }
                filtroMostrar= filtroMostrar.substring(0, filtroMostrar.lastIndexOf(","));
                filtroMostrar += " - ";
            }
            Usuario u ;
            if(rf.unidadInsc!=null){
                filtroMostrar += "UNIDAD INSCRIPTORA = ";
                int i=0;
                for (String s : rf.unidadInsc){
                    if (i==0){
                        filtro += " and (unidadInsc= "+s;
                    }
                    else{
                        filtro += " or unidadInsc= "+s;
                    }
                    i++;
                    if(i==rf.unidadInsc.length){
                         filtro += ")";
                    }
                    u= mc.getUsuario(Integer.valueOf(s));
                    filtroMostrar += u.getNombreMostrar()+",";
                }
                filtroMostrar= filtroMostrar.substring(0, filtroMostrar.lastIndexOf(","));
                filtroMostrar += " - ";
            }
            
            if (filtroMostrar.contains(" - ")){
                rf.filtroMostrar = filtroMostrar.substring(0, filtroMostrar.lastIndexOf(" - "));
            }
            else{
                rf.filtroMostrar = "";
            }
        }
        return filtro;
    }
    public ArrayList<Postulante> getPostulantesListar(RecordPostulanteFiltro rf, int usuario,int carrera, int anio){
        ArrayList<Postulante> al= new ArrayList<>();
        ManejadorCodigoBD mc = new ManejadorCodigoBD();
        try {
            Statement s= connection.createStatement();
            Usuario u = mc.getUsuario(usuario);
            String sql = "";
            String filtro = getFiltroSQL(rf);
            if(u.isAdmin()){
                if(carrera==1){
                    sql="SELECT postulantes.ci, primerNombre, primerapellido, segundoNombre, segundoApellido, unidadInsc, numero, carrera, reingreso FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci where anio="+anio+" and carrera=" + carrera + filtro + " order by postulantes.comando.numero asc, primerapellido asc, segundoApellido asc";
                }
                else{
                    sql="SELECT postulantes.ci, primerNombre, primerApellido, segundoNombre, segundoApellido, unidadInsc, numero, carrera, reingreso FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci where anio="+anio+" and carrera=" + carrera + filtro + " order by postulantes.apoyo.numero asc, primerapellido asc, segundoApellido asc";
                }
            }
            else{
                if(carrera==1){
                    sql="SELECT postulantes.ci, primerNombre, primerApellido, segundoNombre, segundoApellido, unidadInsc, numero, carrera, reingreso FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci where anio="+anio+" and UnidadInsc="+ usuario + " and carrera=" + carrera + filtro + " order by postulantes.comando.numero asc, primerapellido asc, segundoApellido asc";
                }
                else{ 
                    sql="SELECT postulantes.ci, primerNombre, primerApellido, segundoNombre, segundoApellido, unidadInsc, numero, carrera, reingreso FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci where anio="+anio+" and UnidadInsc="+ usuario + " and carrera=" + carrera + filtro + " order by postulantes.apoyo.numero asc, primerapellido asc, segundoApellido asc";
                }
            }
            
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                int id=-1;
                if (rs.getInt("Numero")!=0){
                    id=rs.getInt("Numero");
                }
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
    
    public static int getAnioPostula(){
      java.util.Calendar fecha1 = java.util.Calendar.getInstance();
      int mes = fecha1.get(java.util.Calendar.MONTH)+1;  
      if(mes >= 3){
          return fecha1.get(java.util.Calendar.YEAR) +1;
      }
      else{
          return fecha1.get(java.util.Calendar.YEAR);
      }
    }
    
    public boolean existeDatosAniosAnteriores(){
        try {
            int anio = ManejadorPostulanteDB.getAnioPostula();
            Statement s = connection.createStatement();
            String sql="Select * from postulantes.postulantes where anio<"+anio;
            ResultSet rs = s.executeQuery(sql);
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorPostulanteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  
    }
            
    //b=-1 error
    //b=0 existen datos anios anteriores
    //b=1 ok
    public int createPostulante(RecordPostulanteBasico rb, int creadoPor, int Carrera,PrintWriter out){
        int b = -1;
        ManejadorCodigoBD  mc= new ManejadorCodigoBD();
        try {
           // connects to the database
           // constructs SQL statement
           //
            boolean archivos = false;
            java.util.Calendar fecha1 = java.util.Calendar.getInstance();
            int mes = fecha1.get(java.util.Calendar.MONTH)+1;
            String fecha=  fecha1.get(java.util.Calendar.YEAR)+"-"+mes+"-"+fecha1.get(java.util.Calendar.DATE); //usada para log
            String sql = "INSERT INTO postulantes.documentos (anio,ci, foto, fotociAnverso, fotociReverso, fotof69Hoja1, fotof69Hoja2, fotof69Hoja3, fotof1hoja1, fotof1hoja2) values (?,?,?,?,?,?,?,?,?,?)";
            String sql1 = "INSERT INTO postulantes.postulantes (ci, PrimerNombre, PrimerApellido, SegundoNombre, SegundoApellido, anio, unidadInsc, FechaNac, Sexo, DepartamentoNac, LocalidadNac, CC, CCNro, EstadoCivil, Domicilio, Departamento, Localidad, Telefono, Email, Quinto, Orientacion, Lmga, Reingreso, BuenaConducta, PS, PSEjercito, HIJOS, OBSERVACIONES, carrera,paseDirecto,notaPaseDirecto,materiasPendientes,alojamiento,nsp,renuncio) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String sql2 = "INSERT INTO postulantes.log(ci,  usuario, fecha, alta, sentenciaSQL, archivos) values(?,?,?,?,?,?)";
            PreparedStatement statement= connection.prepareStatement(sql); // sql a insertar en postulantes
            PreparedStatement statement1= connection.prepareStatement(sql1); // incluir sql sin archivos en log
            PreparedStatement statement2= connection.prepareStatement(sql2); // sql a insertar en log
            Usuario u = mc.getUsuario(creadoPor);
            int i=1;
            int i1=1;
            int i2=1;

            if(!rb.quinto){
                rb.orientacion = 0;
            }

            if(!rb.lmga){
                rb.paseDirecto = false;
                rb.notaPaseDirecto = 0;
            }

            if(!rb.ps){
                rb.psEjercito = false;
                rb.hijos = 0;
            }
            if(!rb.paseDirecto){
               rb.notaPaseDirecto = 0;
           }
            statement.setInt(i++, ManejadorPostulanteDB.getAnioPostula());
            statement.setInt(i++, rb.ci);
            if (rb.foto!=null && !rb.foto.equals("")){
                byte[] imageByte = Base64.getDecoder().decode(rb.foto);
                Blob blob = connection.createBlob();//Where connection is the connection to db object. 
                blob.setBytes(1, imageByte);
                statement.setBlob(i++, blob);
                archivos=true;
            }
            else{
                statement.setNull(i++, NULL);
            }

            if (rb.fotoCIAnverso!=null && !rb.fotoCIAnverso.equals("")){
                byte[] imageByte = Base64.getDecoder().decode(rb.fotoCIAnverso);
                Blob blob = connection.createBlob();//Where connection is the connection to db object. 
                blob.setBytes(1, imageByte);
                statement.setBlob(i++, blob);
                archivos=true;
            }
            else{
                statement.setNull(i++, NULL);
            }
            if (rb.fotoCIReverso!=null && !rb.fotoCIReverso.equals("")){
                byte[] imageByte = Base64.getDecoder().decode(rb.fotoCIReverso);
                Blob blob = connection.createBlob();//Where connection is the connection to db object. 
                blob.setBytes(1, imageByte);
                statement.setBlob(i++, blob);
                archivos=true;
            }
            else{
                statement.setNull(i++, NULL);
            }

            if (rb.fotoF69Hoja1!=null && !rb.fotoF69Hoja1.equals("")){
                byte[] imageByte = Base64.getDecoder().decode(rb.fotoF69Hoja1);
                Blob blob = connection.createBlob();//Where connection is the connection to db object. 
                blob.setBytes(1, imageByte);
                statement.setBlob(i++, blob);
                archivos=true;
            }
            else{
                statement.setNull(i++, NULL); 
            }
            if (rb.fotoF69Hoja2!=null && !rb.fotoF69Hoja2.equals("")){
                byte[] imageByte = Base64.getDecoder().decode(rb.fotoF69Hoja2);
                Blob blob = connection.createBlob();//Where connection is the connection to db object. 
                blob.setBytes(1, imageByte);
                statement.setBlob(i++, blob);
                archivos=true;
            }
            else{
                statement.setNull(i++, NULL); 
            }
            if (rb.fotoF69Hoja3!=null && !rb.fotoF69Hoja3.equals("")){
                byte[] imageByte = Base64.getDecoder().decode(rb.fotoF69Hoja3);
                Blob blob = connection.createBlob();//Where connection is the connection to db object. 
                blob.setBytes(1, imageByte);
                statement.setBlob(i++, blob);
                archivos=true;
            }
            else{
                statement.setNull(i++, NULL); 
            }
            if (rb.fotof1Hoja1!=null && !rb.fotof1Hoja1.equals("")){
                byte[] imageByte = Base64.getDecoder().decode(rb.fotof1Hoja1);
                Blob blob = connection.createBlob();//Where connection is the connection to db object. 
                blob.setBytes(1, imageByte);
                statement.setBlob(i++, blob);
                archivos=true;
            }
            else{
                statement.setNull(i++, NULL); 
            }
            if (rb.fotof1Hoja2!=null && !rb.fotof1Hoja2.equals("")){
                byte[] imageByte = Base64.getDecoder().decode(rb.fotof1Hoja2);
                Blob blob = connection.createBlob();//Where connection is the connection to db object. 
                blob.setBytes(1, imageByte);
                statement.setBlob(i++, blob);
                archivos=true;
            }
            else{
                statement.setNull(i++, NULL); 
            }

            statement1.setInt(i1++, rb.ci);
            statement1.setString(i1++, rb.primerNombre);
            statement1.setString(i1++, rb.primerApellido);
            statement1.setString(i1++, rb.segundoNombre);
            statement1.setString(i1++, rb.segundoApellido);
            statement1.setInt(i1++, ManejadorPostulanteDB.getAnioPostula());
            statement1.setInt(i1++, creadoPor);
            statement1.setString(i1++, rb.FechaNac);
            statement1.setString(i1++, rb.sexo);
            statement1.setInt(i1++, rb.departamentoNac);
            statement1.setString(i1++, rb.localidadNac);
            statement1.setString(i1++,rb.cc);
            statement1.setInt(i1++, rb.ccNro);
            statement1.setInt(i1++, rb.estadoCivil);
            statement1.setString(i1++, rb.domicilio);
            statement1.setInt(i1++, rb.departamento);
            statement1.setString(i1++, rb.localidad);
            statement1.setString(i1++, rb.telefono);
            statement1.setString(i1++, rb.email);
            statement1.setBoolean(i1++, rb.quinto);
            statement1.setInt(i1++, rb.orientacion);
            statement1.setBoolean(i1++, rb.lmga);
            statement1.setBoolean(i1++, rb.reingreso);
            statement1.setBoolean(i1++, rb.buenaConducta);
            statement1.setBoolean(i1++, rb.ps);
            statement1.setBoolean(i1++, rb.psEjercito);
            statement1.setInt(i1++, rb.hijos);
            statement1.setString(i1++, rb.observaciones);
            statement1.setInt(i1++,Carrera);
            statement1.setBoolean(i1++, rb.paseDirecto);
            statement1.setDouble(i1++, rb.notaPaseDirecto);
            statement1.setString(i1++, rb.materiasPendientes);
            statement1.setBoolean(i1++, rb.alojamiento);
            statement1.setBoolean(i1++, rb.nsp);
            statement1.setBoolean(i1++, rb.renuncio);

            statement2.setInt(i2++, rb.ci);
            statement2.setInt(i2++, creadoPor);
            statement2.setString(i2++, fecha);
            statement2.setBoolean(i2++, true); 
            statement2.setString(i2++, statement1.toString());
            statement2.setBoolean(i2++, archivos);

            int row=statement1.executeUpdate();
            if(row>0){
                row=statement.executeUpdate();
                if(row>0){
                    statement2.executeUpdate();
                }
            }
            if (row>0 && !rb.fotoF69Hoja1.equals("") && !rb.fotof1Hoja1.equals("")) {
                if (Carrera==1){
                    sql="INSERT INTO postulantes.comando (ci) values (?)";
                    PreparedStatement s = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    s.setInt(1, rb.ci);
                    row = s.executeUpdate();
                    ResultSet rs= s.getGeneratedKeys();
                    if ( row > 0){
                        b=1;
                        if(rs.next()){
                            Long llave = rs.getLong(1);
                            EnviarMail e = new EnviarMail(rb.email,true,true,llave,u.getEmail());
                            e.start();
                        }
                    }
                }
                else{
                    if (Carrera==2){
                        sql="INSERT INTO postulantes.apoyo (ci) values (?)";
                        PreparedStatement s = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                        s.setInt(1, rb.ci);
                        row = s.executeUpdate();
                        ResultSet rs = s.getGeneratedKeys();
                        if ( row > 0){
                            b=1;
                            if(rs.next()){
                                Long llave = rs.getLong(1);
                                EnviarMail e = new EnviarMail(rb.email,true,true,llave,u.getEmail());
                                e.start();
                            }
                        }
                    }
                }
            }
            else{
                Long llave = null;
                if (rb.fotoF69Hoja1.equals("") && rb.fotof1Hoja1.equals("")){
                    EnviarMail e = new EnviarMail(rb.email,false,false,llave,u.getEmail());
                    e.start();
                }
                else{
                    if (rb.fotoF69Hoja1.equals("") && !rb.fotof1Hoja1.equals("")){
                        EnviarMail e = new EnviarMail(rb.email,true,false,llave,u.getEmail());
                        e.start();
                    }
                    else{
                       if (rb.fotof1Hoja1.equals("") && !rb.fotoF69Hoja1.equals("")){
                            EnviarMail e = new EnviarMail(rb.email,false,true,llave,u.getEmail());
                            e.start();
                        } 
                    }
                }

               b=1;
            }

        } catch (Exception ex) {
            Logger.getLogger(ManejadorPostulanteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
    
    public boolean savePostulanteBasico(RecordPostulanteBasico rb, int creadoPor,int Carrera,PrintWriter out){
        
        try {
            // connects to the database
            String strfoto ="";
            if (!rb.foto.equals("")){
                    strfoto = ", foto = ?";
            }
            String strfotoCIAnverso ="";
            if (!rb.fotoCIAnverso.equals("")){
                    strfotoCIAnverso = ", fotoCIAnverso = ?";
            }
            String strfotoCIReverso ="";
            if (!rb.fotoCIReverso.equals("")){
                    strfotoCIReverso = ", fotoCIReverso = ?";
            }
            String strfotoF69Hoja1 ="";
            if (!rb.fotoF69Hoja1.equals("")){
                    strfotoF69Hoja1 = ", fotoF69Hoja1 = ?";
            }
            String strfotoF69Hoja2 ="";
            if (!rb.fotoF69Hoja2.equals("")){
                    strfotoF69Hoja2 = ", fotoF69Hoja2 = ?";
            }
            String strfotoF69Hoja3 ="";
            if (!rb.fotoF69Hoja3.equals("")){
                    strfotoF69Hoja3 = ", fotoF69Hoja3 = ?";
            }
            String strfotoF1Hoja1 ="";
            if (!rb.fotof1Hoja1.equals("")){
                    strfotoF1Hoja1 = ", fotoF1Hoja1 = ?";
            }
            String strfotoF1Hoja2 ="";
            if (!rb.fotof1Hoja2.equals("")){
                    strfotoF1Hoja2 = ", fotoF1Hoja2 = ?";
            }

           // constructs SQL statement
           boolean archivos = false;
           java.util.Calendar fecha1 = java.util.Calendar.getInstance();
           int mes = fecha1.get(java.util.Calendar.MONTH)+1;
           String fecha=  fecha1.get(java.util.Calendar.YEAR)+"-"+mes+"-"+fecha1.get(java.util.Calendar.DATE);
           String sql2 = "INSERT INTO postulantes.log(ci,  usuario, fecha, alta, sentenciaSQL, archivos) values(?,?,?,?,?,?)";
           String sql1 = "UPDATE postulantes.postulantes set ci=?,PrimerNombre=?, PrimerApellido=?,SegundoNombre=?, SegundoApellido=?,fechaNac=?,Sexo=?,DepartamentoNac=?,localidadNac=?,cc=?,ccNro=?,estadocivil=?,domicilio=?,departamento=?,localidad=?,telefono=?,email=?,Quinto=?,Orientacion=?,lmga=?,reingreso=?,BuenaConducta=?,PS=?,PSEjercito=?,Hijos=?,Observaciones=?,PaseDirecto=?,NotaPaseDirecto=?,materiasPendientes=?,Alojamiento=?, nsp=?, renuncio=? where ci = "+ rb.ci+" and anio="+ManejadorPostulanteDB.getAnioPostula();
           String sql = "UPDATE postulantes.documentos set ci=?"+strfoto+strfotoCIAnverso+strfotoCIReverso+strfotoF69Hoja1+strfotoF69Hoja2+strfotoF69Hoja3+strfotoF1Hoja1+strfotoF1Hoja2+" where ci = "+ rb.ci + " and anio= "+ManejadorPostulanteDB.getAnioPostula();
           PreparedStatement statement= connection.prepareStatement(sql);
           PreparedStatement statement1= connection.prepareStatement(sql1);
           PreparedStatement statement2= connection.prepareStatement(sql2);
           ManejadorCodigoBD mc = new ManejadorCodigoBD();
           Usuario u= mc.getUsuario(creadoPor);
           int i=1;
           int i1=1;
           int i2=1;
           if(!rb.quinto){
               rb.orientacion = 0;
           }
           if(!rb.lmga){
               rb.paseDirecto =false;
               rb.notaPaseDirecto = 0;
           }
           if(!rb.ps){
               rb.psEjercito = false;
               rb.hijos=0;
           }
           if(!rb.paseDirecto){
               rb.notaPaseDirecto = 0;
           }
           
           
           statement.setInt(i++,rb.ci);
           statement1.setInt(i1++,rb.ci);
           statement2.setInt(i2++,rb.ci);
           statement2.setInt(i2++,creadoPor);
           statement2.setString(i2++,fecha);
           statement2.setBoolean(i2++,false);
           statement1.setString(i1++,rb.primerNombre);
           statement1.setString(i1++,rb.primerApellido);
           statement1.setString(i1++,rb.segundoNombre);
           statement1.setString(i1++,rb.segundoApellido);
           statement1.setString(i1++, rb.FechaNac);
           statement1.setString(i1++, rb.sexo);
           statement1.setInt(i1++, rb.departamentoNac);
           statement1.setString(i1++, rb.localidadNac);
           statement1.setString(i1++, rb.cc);
           statement1.setInt(i1++, rb.ccNro);
           statement1.setInt(i1++,rb.estadoCivil);
           statement1.setString(i1++,rb.domicilio);
           statement1.setInt(i1++,rb.departamento);
           statement1.setString(i1++,rb.localidad);
           statement1.setString(i1++,rb.telefono);
           statement1.setString(i1++,rb.email);
           statement1.setBoolean(i1++,rb.quinto);
           statement1.setInt(i1++,rb.orientacion);
           statement1.setBoolean(i1++,rb.lmga);
           statement1.setBoolean(i1++,rb.reingreso);
           statement1.setBoolean(i1++,rb.buenaConducta);
           statement1.setBoolean(i1++,rb.ps);
           statement1.setBoolean(i1++,rb.psEjercito);
           statement1.setInt(i1++, rb.hijos);
           statement1.setString(i1++, rb.observaciones);
           statement1.setBoolean(i1++,rb.paseDirecto);
           statement1.setDouble(i1++, rb.notaPaseDirecto);
           statement1.setString(i1++, rb.materiasPendientes);
           statement1.setBoolean(i1++, rb.alojamiento);
           statement1.setBoolean(i1++, rb.nsp);
           statement1.setBoolean(i1++, rb.renuncio);
           
           if (!rb.foto.equals("")){
               byte[] imageByte = Base64.getDecoder().decode(rb.foto);
               Blob blob = connection.createBlob();//Where connection is the connection to db object. 
               blob.setBytes(1, imageByte);
               statement.setBlob(i++, blob);
               archivos=true;
           }
           if (!rb.fotoCIAnverso.equals("")){
               byte[] imageByte = Base64.getDecoder().decode(rb.fotoCIAnverso);
               Blob blob = connection.createBlob();//Where connection is the connection to db object. 
               blob.setBytes(1, imageByte);
               statement.setBlob(i++, blob);
               archivos=true;
           }
           if (!rb.fotoCIReverso.equals("")){
               byte[] imageByte = Base64.getDecoder().decode(rb.fotoCIReverso);
               Blob blob = connection.createBlob();//Where connection is the connection to db object. 
               blob.setBytes(1, imageByte);
               statement.setBlob(i++, blob);
               archivos=true;
           }
           if (!rb.fotoF69Hoja1.equals("")){
               byte[] imageByte = Base64.getDecoder().decode(rb.fotoF69Hoja1);
               Blob blob = connection.createBlob();//Where connection is the connection to db object. 
               blob.setBytes(1, imageByte);
               statement.setBlob(i++, blob);
               archivos=true;
           }
           if (!rb.fotoF69Hoja2.equals("")){
               byte[] imageByte = Base64.getDecoder().decode(rb.fotoF69Hoja2);
               Blob blob = connection.createBlob();//Where connection is the connection to db object. 
               blob.setBytes(1, imageByte);
               statement.setBlob(i++, blob);
               archivos=true;
           }
           if (!rb.fotoF69Hoja3.equals("")){
               byte[] imageByte = Base64.getDecoder().decode(rb.fotoF69Hoja3);
               Blob blob = connection.createBlob();//Where connection is the connection to db object. 
               blob.setBytes(1, imageByte);
               statement.setBlob(i++, blob);
               archivos=true;
           }
           if (!rb.fotof1Hoja1.equals("")){
               byte[] imageByte = Base64.getDecoder().decode(rb.fotof1Hoja1);
               Blob blob = connection.createBlob();//Where connection is the connection to db object. 
               blob.setBytes(1, imageByte);
               statement.setBlob(i++, blob);
               archivos=true;
           }
           if (!rb.fotof1Hoja2.equals("")){
               byte[] imageByte = Base64.getDecoder().decode(rb.fotof1Hoja2);
               Blob blob = connection.createBlob();//Where connection is the connection to db object. 
               blob.setBytes(1, imageByte);
               statement.setBlob(i++, blob);
               archivos=true;
           }
           
            statement2.setString(i2++, statement1.toString());
            statement2.setBoolean(i2++, archivos);
            
           boolean b = false;
           int row = statement1.executeUpdate();//datos basicos
           if(row>0){
               row = statement.executeUpdate();//documentos
               if (row>0){
                    statement2.executeUpdate(); // log
               }
           }
           
               
            
            if (row > 0) {
                Postulante p = this.getPostulante(rb.ci, rb.unidadInsc, ManejadorPostulanteDB.getAnioPostula());
                if (rb.id==0 && (p.getFotoF69Hoja1()!=null || !rb.fotoF69Hoja1.equals("")) && (p.getFotoF1Hoja1()!=null || !rb.fotof1Hoja1.equals(""))) {
                    if (Carrera==1){
                        sql="INSERT INTO postulantes.comando (ci) values (?)";
                        PreparedStatement s = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                        s.setInt(1, rb.ci);
                        row = s.executeUpdate();
                        ResultSet rs = s.getGeneratedKeys();
                        if ( row > 0){
                            b=true;
                            if(rs.next()){
                                Long llave = rs.getLong(1);
                                EnviarMail e = new EnviarMail(rb.email,true,true,llave,u.getEmail());
                                e.start();
                            }
                        }
                    }
                    else{
                        if (Carrera==2){
                            sql="INSERT INTO postulantes.apoyo (ci) values (?)";
                            PreparedStatement s = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
                            s.setInt(1, rb.ci);
                            row = s.executeUpdate();
                            ResultSet rs = s.getGeneratedKeys();
                            if ( row > 0){
                                b=true;
                                if(rs.next()){
                                    Long llave = rs.getLong(1);
                                    EnviarMail e = new EnviarMail(rb.email,true,true,llave,u.getEmail());
                                    e.start();
                                }
                            }
                        }
                    }
                }
                else{
                    b=true;
                }
                
            }
            return b;
            
        } catch (SQLException ex) {
            return false;
        }
    }
    public Date getFechaAlta(int ci){
         Date d= null;
        
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.log where ci="+ci+" and alta=1";
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                //System.out.print(rs.getDate("fecha"));
                return rs.getDate("fecha");
            }
            

        } catch (Exception ex) {
            System.out.print("ddd");
            Logger.getLogger(ManejadorPostulanteDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return d;
    }
    public boolean savePostulantePatronimico(RecordPostulantePatronim rb, int creadoPor, int Carrera,PrintWriter out){
        try {
            // connects to the database


           // constructs SQL statement
           String strPFechaNac="";
           if (!rb.pFechaNac.equals("")){
               strPFechaNac=", PFechaNac=?";
           }
           String strMFechaNac="";
           if (!rb.mFechaNac.equals("")){
               strMFechaNac=", MFechaNac=?";
           }
           java.util.Calendar fecha1 = java.util.Calendar.getInstance();
           int mes = fecha1.get(java.util.Calendar.MONTH)+1;
           String fecha=  fecha1.get(java.util.Calendar.YEAR)+"-"+mes+"-"+fecha1.get(java.util.Calendar.DATE);
           String sql2 = "INSERT INTO postulantes.log(ci,  usuario, fecha, alta, sentenciaSQL, archivos) values(?,?,?,?,?,?)";
           String sql = "UPDATE postulantes.postulantes set PNombreComp=?,PDepartamentoNac=?,PLocalidadNac=?,PEstadoCivil=?,PDomicilio=?,PDepartamento=?,PLocalidad=?,PTelefono=?,PProfesion=?,PLugarTrabajo=?,MNombreComp=?,MDepartamentoNac=?,MLocalidadNac=?,MEstadoCivil=?,MDomicilio=?,MDepartamento=?,MLocalidad=?,MTelefono=?,MProfesion=?,MLugarTrabajo=?"+ strPFechaNac + strMFechaNac+" where ci = "+ rb.ci;
           PreparedStatement statement= connection.prepareStatement(sql);
           PreparedStatement statement2= connection.prepareStatement(sql2);
           statement2.setInt(1,rb.ci);
           statement2.setInt(2,creadoPor);
           statement2.setString(3,fecha);
           statement2.setBoolean(4,false);
           statement.setString(1, rb.pNombreComp);
           statement.setInt(2, rb.pDepartamentoNac);
           statement.setString(3, rb.pLocalidadNac);
           statement.setInt(4, rb.pEstadoCivil);
           statement.setString(5, rb.pDomicilio);
           statement.setInt(6, rb.pDepartamento);
           statement.setString(7, rb.pLocalidad);
           statement.setString(8, rb.pTelefono);
           statement.setString(9, rb.pProfesion);
           statement.setString(10, rb.pLugarTrabajo);
           statement.setString(11, rb.mNombreComp);
           statement.setInt(12, rb.mDepartamentoNac);
           statement.setString(13, rb.mLocalidadNac);
           statement.setInt(14, rb.mEstadoCivil);
           statement.setString(15, rb.mDomicilio);
           statement.setInt(16, rb.mDepartamento);
           statement.setString(17, rb.mLocalidad);
           statement.setString(18, rb.mTelefono);
           statement.setString(19, rb.mProfesion);
           statement.setString(20, rb.mLugarTrabajo);
           int i=21;
           if (!rb.pFechaNac.equals("")){
                statement.setString(i, rb.pFechaNac);
                i++;
           }
           if (!rb.mFechaNac.equals("")){
                statement.setString(i, rb.mFechaNac);
           }
           String sentenciaSql= statement.toString();
           statement2.setString(5, sentenciaSql);
           statement2.setBoolean(6, false);
            int row = statement.executeUpdate();
            //AGERGAR A CARRERA CORRESPONDIENTE!!!!!!!!!!!!!!
            if(row > 0){
                statement2.executeUpdate();
            }
            return (row > 0);
        } catch (Exception ex) {
            
            return false;
        } 
    }
    public void imprimirFichaPostulante(int ci,int usuario, PrintWriter out, int anio){
        Postulante p = getPostulante(ci, usuario, anio);
        p.imprimirPostulante(out);
    }

    public boolean pasarDatosAHistorial() {
        try {
            if(existeDatosAniosAnteriores()){
                Statement s = connection.createStatement();
                int anio = ManejadorPostulanteDB.getAnioPostula(); //voy a pasar a historial todo menos lo de ahora
                String sql= "insert into postulantes.`historialPostulantes` select * from postulantes.postulantes where anio<"+anio;
                s.addBatch(sql);
                sql= "insert into postulantes.`historialNotas` select * from postulantes.notas where anio<"+anio;
                s.addBatch(sql);
                sql= "insert into postulantes.`historialResultados` select * from postulantes.resultados where anio<"+anio;
                s.addBatch(sql);
                sql= "delete from postulantes.postulantes where anio<"+anio;
                s.addBatch(sql);
                sql= "delete from postulantes.notas where anio<"+anio;
                s.addBatch(sql);
                sql= "delete from postulantes.resultados where anio<"+anio;
                s.addBatch(sql);
                sql= "delete from postulantes.documentos where anio<"+anio;
                s.addBatch(sql);
                sql= "delete from postulantes.comando";
                s.addBatch(sql);
                sql="ALTER TABLE postulantes.`comando` auto_increment = 1";
                s.addBatch(sql);
                sql= "delete from postulantes.apoyo";
                s.addBatch(sql);
                sql="ALTER TABLE postulantes.`apoyo` auto_increment = 1";
                s.addBatch(sql);
                sql= "delete from postulantes.log";
                s.addBatch(sql);
                int[] lista = s.executeBatch();
                for(int i : lista){
                    if(i<0){
                        return false;
                    }
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorPostulanteDB.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
}
