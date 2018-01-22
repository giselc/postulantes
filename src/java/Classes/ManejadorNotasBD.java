/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gcincunegui
 */
public class ManejadorNotasBD {
    private Connection connection;

    public ManejadorNotasBD() {
        connection = ConexionDB.GetConnection();
    }
    private double convertirEscala12a10(double nota){
        if(nota!=0){
            double d = 10*nota/12;
            String s= String.format("%.4g%n", d);
            s=s.replace(",", ".");
            return(Double.valueOf(s));
        }
        else{
            return 0;
        }
    }
    private static double redondearDecimales(double valorInicial, int numeroDecimales) {
        double parteEntera, resultado;
        resultado = valorInicial;
        parteEntera = Math.floor(resultado);
        resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
        resultado=Math.round(resultado);
        resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
        return resultado;
    }
    public double calcularPromedio(RecordPostulanteNota rn){
        if(rn.reingreso){
            String s= String.format("%.4g%n", rn.educacionFisica);
            s=s.replace(",", ".");
            return(Double.valueOf(s));
        }
        double promedio=(rn.educacionFisica+rn.idiomaEspaniol+rn.matematica+rn.historia)/4;
        if(promedio < 1){
            return 1;
        }
        else{
            String s= String.format("%.4g%n", promedio);
            s=s.replace(",", ".");
            return(Double.valueOf(s));
        }
        
        //return promedio;
    }
    public RecordPostulanteNota getNota(int ci, int carrera, int anio){
        RecordPostulanteNota rpn=null;
        try {
            Statement s= connection.createStatement();
            String sql = "";
            String aux = "matematica, matematicaObs,historia, historiaObs,IdiomaEspanol, IdEspanolObs,EducacionFisica, EdFisicaObs, arrojo, psicotecnica, psicoSeg, psicoObs,medico, medicoSeg, medicoObs, odontologico, odontSeg, odontObs";
            if(carrera==1){
                sql="SELECT postulantes.ci, paseDirecto, reingreso, notaPaseDirecto, primerNombre, primerApellido,segundoNombre, segundoApellido, numero, carrera, "+aux+" FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.notas ON postulantes.ci = notas.ci where carrera=" + carrera + " and postulantes.ci = "+ ci +" and postulantes.anio = "+ anio +" order by NUMERO asc";
            }
            else{
                sql="SELECT postulantes.ci, paseDirecto, reingreso, notaPaseDirecto, primerNombre, primerApellido,segundoNombre, segundoApellido, unidadInsc, numero, carrera, "+aux+" FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci LEFT JOIN postulantes.notas ON postulantes.ci = notas.ci where carrera=" + carrera + " and postulantes.ci = "+ ci +" and postulantes.anio = "+ anio +" order by NUMERO asc";
            }
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                rpn = new RecordPostulanteNota();
                rpn.anio = anio;
                rpn.id="COND.";
                if (rs.getInt("Numero")!=0){
                    rpn.id=String.valueOf(rs.getInt("Numero"));
                }
                rpn.ci= rs.getInt("CI");
                rpn.pd = rs.getBoolean("paseDirecto");
                rpn.reingreso = rs.getBoolean("reingreso");
                rpn.notapd = convertirEscala12a10(rs.getDouble("notaPaseDirecto"));
                rpn.primerNombre="";
                for (String nombre: rs.getString("primerNombre").split(" ")){
                    if(!nombre.equals("")){
                        rpn.primerNombre += nombre.substring(0,1).toUpperCase()+nombre.substring(1).toLowerCase()+" ";
                    }
                }
                rpn.primerApellido="";
                for (String apellido: rs.getString("primerApellido").split(" ")){
                    if(!apellido.equals("")){
                        rpn.primerApellido += apellido.substring(0,1).toUpperCase()+apellido.substring(1).toLowerCase()+" ";
                    }
                }
                rpn.segundoNombre="";
                for (String nombre: rs.getString("segundoNombre").split(" ")){
                    if(!nombre.equals("")){
                        rpn.segundoNombre += nombre.substring(0,1).toUpperCase()+nombre.substring(1).toLowerCase()+" ";
                    }
                }
                rpn.segundoApellido="";
                for (String apellido: rs.getString("segundoApellido").split(" ")){
                    if(!apellido.equals("")){
                        rpn.segundoApellido += apellido.substring(0,1).toUpperCase()+apellido.substring(1).toLowerCase()+" ";
                    }
                }
                rpn.matematica = rs.getDouble("Matematica");
                if(rs.getString("MatematicaObs")==null){
                    rpn.matematicaObs = "";
                }
                else{ 
                    rpn.matematicaObs = rs.getString("MatematicaObs"); 
                }
                rpn.historia = rs.getDouble("Historia");
                if(rs.getString("HistoriaObs")==null){
                    rpn.historiaObs = "";
                }
                else{ 
                    rpn.historiaObs = rs.getString("HistoriaObs");
                }
                rpn.idiomaEspaniol = rs.getDouble("IdiomaEspanol");
                if(rs.getString("IdEspanolObs")==null){
                    rpn.idiomaEspaniolObs = "";
                }
                else{ 
                    rpn.idiomaEspaniolObs = rs.getString("IdEspanolObs");
                }
                rpn.educacionFisica = rs.getDouble("EducacionFisica");
                if(rs.getString("EdFisicaObs")==null){
                    rpn.edFisicaObs = "";
                }
                else{ 
                    rpn.edFisicaObs = rs.getString("EdFisicaObs");
                }     
                rpn.arrojo = rs.getBoolean("arrojo");
                rpn.psicotecnica = rs.getInt("psicotecnica");
                rpn.psicoSeg=rs.getBoolean("psicoSeg");
                if(rs.getString("psicoObs")==null){
                    rpn.psicoObs = "";
                }
                else{ 
                    rpn.psicoObs = rs.getString("psicoObs");
                }  
                rpn.medico = rs.getInt("medico");
                rpn.medicoSeg=rs.getBoolean("medicoSeg");
                if(rs.getString("medicoObs")==null){
                    rpn.medicoObs = "";
                }
                else{ 
                    rpn.medicoObs = rs.getString("medicoObs");
                } 
                rpn.odontologico = rs.getInt("odontologico");
                rpn.odontSeg=rs.getBoolean("odontSeg");
                if(rs.getString("odontObs")==null){
                    rpn.odontObs = "";
                }
                else{ 
                    rpn.odontObs = rs.getString("odontObs");
                } 
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rpn;
    }
    public ArrayList<RecordPostulanteNota> getPasesDirectosYReingresos(int carrera, int anio){
        ArrayList<RecordPostulanteNota> an= new ArrayList<>();
        try {
            Statement s= connection.createStatement();
            String sql = "";
            if(carrera==1){
                sql="SELECT postulantes.ci, paseDirecto, reingreso, notaPaseDirecto,renuncio,nsp,  primerNombre, primerApellido,segundoNombre, segundoApellido, numero, carrera FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci where anio="+anio+" and carrera=" + carrera + " order by NUMERO asc";
            }
            else{
                sql="SELECT postulantes.ci, paseDirecto, reingreso, notaPaseDirecto,renuncio,nsp,  primerNombre, primerApellido,segundoNombre, segundoApellido, unidadInsc, numero, carrera FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci where anio="+anio+" and carrera=" + carrera + " order by NUMERO asc";
            }
            ResultSet rs=s.executeQuery(sql);
            RecordPostulanteNota rpn = null;
            while (rs.next()){
                rpn = new RecordPostulanteNota();
                rpn.id="COND.";
                if (rs.getInt("Numero")!=0){
                    rpn.id=String.valueOf(rs.getInt("Numero"));
                }
                rpn.ci= rs.getInt("CI");
                rpn.pd = rs.getBoolean("paseDirecto");
                rpn.reingreso = rs.getBoolean("reingreso");
                rpn.renuncio = rs.getBoolean("renuncio");
                rpn.nsp = rs.getBoolean("nsp");
                rpn.notapd = convertirEscala12a10(rs.getDouble("notaPaseDirecto"));
                rpn.primerNombre="";
                for (String nombre: rs.getString("primerNombre").split(" ")){
                    rpn.primerNombre += nombre.substring(0,1).toUpperCase()+nombre.substring(1).toLowerCase()+" ";
                }
                rpn.primerApellido="";
                for (String apellido: rs.getString("primerApellido").split(" ")){
                    rpn.primerApellido += apellido.substring(0,1).toUpperCase()+apellido.substring(1).toLowerCase()+" ";
                }
                rpn.segundoNombre="";
                for (String nombre: rs.getString("segundoNombre").split(" ")){
                    rpn.segundoNombre += nombre.substring(0,1).toUpperCase()+nombre.substring(1).toLowerCase()+" ";
                }
                rpn.segundoApellido="";
                for (String apellido: rs.getString("segundoApellido").split(" ")){
                    rpn.segundoApellido += apellido.substring(0,1).toUpperCase()+apellido.substring(1).toLowerCase()+" ";
                }
                an.add(rpn);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return an;
    }
    public ArrayList<RecordPostulanteNota> getNotas(int carrera, int anio){
        ArrayList<RecordPostulanteNota> an= new ArrayList<>();
        try {
            Statement s= connection.createStatement();
            String sql = "";
            String aux = "matematica, matematicaObs,historia, historiaObs,IdiomaEspanol, IdEspanolObs,EducacionFisica, EdFisicaObs, arrojo, psicotecnica, psicoSeg, psicoObs,medico, medicoSeg, medicoObs, odontologico, odontSeg, odontObs";
            if(carrera==1){
                sql="SELECT postulantes.ci, paseDirecto, reingreso, nsp, renuncio, notaPaseDirecto, primerNombre, primerApellido,segundoNombre, segundoApellido,  numero, carrera, "+aux+" FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.notas ON postulantes.ci = notas.ci where carrera=" + carrera + " and postulantes.anio = "+ anio +" order by NUMERO asc";
            }
            else{
                sql="SELECT postulantes.ci, paseDirecto, reingreso, nsp, renuncio, notaPaseDirecto, primerNombre, primerApellido,segundoNombre, segundoApellido,  unidadInsc, numero, carrera, "+aux+" FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci LEFT JOIN postulantes.notas ON postulantes.ci = notas.ci where carrera=" + carrera + " and postulantes.anio = "+ anio +" order by NUMERO asc";
            }
            ResultSet rs=s.executeQuery(sql);
            RecordPostulanteNota rpn = null;
            while (rs.next()){
                rpn = new RecordPostulanteNota();
                rpn.id="COND.";
                if (rs.getInt("Numero")!=0){
                    rpn.id=String.valueOf(rs.getInt("Numero"));
                }
                rpn.ci= rs.getInt("CI");
                rpn.pd = rs.getBoolean("paseDirecto");
                rpn.reingreso = rs.getBoolean("reingreso");
                rpn.nsp = rs.getBoolean("nsp");
                rpn.renuncio = rs.getBoolean("renuncio");
                rpn.notapd = convertirEscala12a10(rs.getDouble("notaPaseDirecto"));
                rpn.primerNombre="";
                for (String nombre: rs.getString("primerNombre").split(" ")){
                    if(!nombre.equals("")){
                        rpn.primerNombre += nombre.substring(0,1).toUpperCase()+nombre.substring(1).toLowerCase()+" ";
                    }
                }
                rpn.primerApellido="";
                for (String apellido: rs.getString("primerApellido").split(" ")){
                    if(!apellido.equals("")){
                       rpn.primerApellido += apellido.substring(0,1).toUpperCase()+apellido.substring(1).toLowerCase()+" ";
                    }
                }
                rpn.segundoNombre="";
                for (String nombre: rs.getString("segundoNombre").split(" ")){
                    if(!nombre.equals("")){
                        rpn.segundoNombre += nombre.substring(0,1).toUpperCase()+nombre.substring(1).toLowerCase()+" ";
                    }
                }
                rpn.segundoApellido="";
                for (String apellido: rs.getString("segundoApellido").split(" ")){
                    if(!apellido.equals("")){
                       rpn.segundoApellido += apellido.substring(0,1).toUpperCase()+apellido.substring(1).toLowerCase()+" ";
                    }
                }
                rpn.matematica = rs.getDouble("Matematica");
                if(rs.getString("MatematicaObs")==null){
                    rpn.matematicaObs = "";
                }
                else{ 
                    rpn.matematicaObs = rs.getString("MatematicaObs"); 
                }
                rpn.historia = rs.getDouble("Historia");
                if(rs.getString("HistoriaObs")==null){
                    rpn.historiaObs = "";
                }
                else{ 
                    rpn.historiaObs = rs.getString("HistoriaObs");
                }
                rpn.idiomaEspaniol = rs.getDouble("IdiomaEspanol");
                if(rs.getString("IdEspanolObs")==null){
                    rpn.idiomaEspaniolObs = "";
                }
                else{ 
                    rpn.idiomaEspaniolObs = rs.getString("IdEspanolObs");
                }
                rpn.educacionFisica = rs.getDouble("EducacionFisica");
                if(rs.getString("EdFisicaObs")==null){
                    rpn.edFisicaObs = "";
                }
                else{ 
                    rpn.edFisicaObs = rs.getString("EdFisicaObs");
                }     
                rpn.arrojo = rs.getBoolean("arrojo");
                if (rs.wasNull()){
                    rpn.arrojo=null;
                }
                rpn.psicotecnica = rs.getInt("psicotecnica");
                rpn.psicoSeg = rs.getBoolean("psicoSeg");
                if(rs.getString("psicoObs")==null){
                    rpn.psicoObs = "";
                }
                else{ 
                    rpn.psicoObs = rs.getString("psicoObs");
                }  
                rpn.medico = rs.getInt("medico");
                rpn.medicoSeg = rs.getBoolean("medicoSeg");
                if(rs.getString("medicoObs")==null){
                    rpn.medicoObs = "";
                }
                else{ 
                    rpn.medicoObs = rs.getString("medicoObs");
                } 
                rpn.odontologico = rs.getInt("odontologico");
                rpn.odontSeg = rs.getBoolean("odontSeg");
                if(rs.getString("odontObs")==null){
                    rpn.odontObs = "";
                }
                else{ 
                    rpn.odontObs = rs.getString("odontObs");
                } 
                an.add(rpn);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return an;
    }
    public boolean modificarNotas(int carrera, int anio,int materia, String[] ci, String[] notas, String[] observaciones, int cantidad, String[] arrojo,PrintWriter out) throws SQLException{
        String sqlInsert = "";
        String sqlUpdate = "";
        boolean ok = false;
        switch(materia){
            case 1: 
                sqlInsert="insert into postulantes.notas (matematica, matematicaObs, ci, anio) values (?,?,?,?)";
                sqlUpdate="update postulantes.notas set matematica=?, matematicaObs=? where ci=? and anio=?";
                break;
            case 2: 
                sqlInsert="insert into postulantes.notas (historia, historiaObs, ci, anio) values (?,?,?,?)";
                sqlUpdate="update postulantes.notas set historia=?, historiaObs=? where ci=? and anio=?";
                break;
            case 3:
                sqlInsert="insert into postulantes.notas (idiomaEspanol, idEspanolObs, ci, anio) values (?,?,?,?)";
                sqlUpdate="update postulantes.notas set idiomaEspanol=?, idEspanolObs=? where ci=? and anio=?";
                break;
            case 4:
                sqlInsert="insert into postulantes.notas (educacionFisica, edFisicaObs, ci, anio, arrojo) values (?,?,?,?,?)";
                sqlUpdate="update postulantes.notas set educacionFisica=?, edFisicaObs=?, arrojo=? where ci=? and anio=?";
                break;
            case 5: 
                sqlInsert="insert into postulantes.notas (psicotecnica, psicoObs, ci, anio, psicoSeg) values (?,?,?,?,?)";
                sqlUpdate="update postulantes.notas set psicotecnica=?, psicoObs=?, psicoSeg=? where ci=? and anio=?";
                break;
            case 6:
                sqlInsert="insert into postulantes.notas (medico, medicoObs, ci, anio, medicoSeg) values (?,?,?,?,?)";
                sqlUpdate="update postulantes.notas set medico=?, medicoObs=?, medicoSeg=? where ci=? and anio=?";
                break;
            case 7:
                sqlInsert="insert into postulantes.notas (odontologico, odontObs, ci, anio, odontSeg) values (?,?,?,?,?)";
                sqlUpdate="update postulantes.notas set odontologico=?, odontObs=?, odontSeg=? where ci=? and anio=?";
                break;
        }
        
        PreparedStatement psInsert;
        PreparedStatement psUpdate;
        psUpdate = connection.prepareStatement(sqlUpdate);
        psInsert = connection.prepareStatement(sqlInsert);
        for(int j=0;j<cantidad;j++){
                
                if(materia==5 || materia==6 || materia==7){
                    int nota=1;
                    boolean seg=false;
                    if(Integer.valueOf(notas[j])==2){ //APTO CON SEG
                        nota = 1;
                        seg = true;
                    }
                    if(Integer.valueOf(notas[j])==3){ //APTO
                        nota = 1;
                        seg = false;
                    }
                    if(Integer.valueOf(notas[j])==1){ //NO APTO
                        nota = 2;
                        seg = false;
                    }
                    psInsert.setInt(1, nota);
                    psInsert.setString(2, observaciones[j]);
                    psInsert.setInt(3, Integer.valueOf(ci[j]));
                    psInsert.setInt(4,anio);
                    psInsert.setBoolean(5, seg);
                    
                    psUpdate.setInt(1, nota);
                    psUpdate.setString(2, observaciones[j]);
                    psUpdate.setBoolean(3, seg);
                    psUpdate.setInt(4, Integer.valueOf(ci[j]));
                    psUpdate.setInt(5, anio);
                    
                }
                else{
                    psInsert.setDouble(1, Double.valueOf(notas[j]));
                    psInsert.setString(2, observaciones[j]);
                    psInsert.setInt(3, Integer.valueOf(ci[j]));
                    psInsert.setInt(4,anio);
                    //out.print(psInsert);

                    psUpdate.setDouble(1, Double.valueOf(notas[j]));
                    psUpdate.setString(2, observaciones[j]);
                    //out.print(arrojo[j]);
                    if(materia==4){
                        if(arrojo[j]!=null && arrojo[j].equals("on")){
                            psInsert.setBoolean(5, true);
                            psUpdate.setBoolean(3, true);
                            psUpdate.setInt(4, Integer.valueOf(ci[j]));
                            psUpdate.setInt(5, anio);
                      //      out.print("--------------ON--------------");
                        }
                        else{
                            psInsert.setBoolean(5, false);
                            psUpdate.setBoolean(3, false);
                            psUpdate.setInt(4, Integer.valueOf(ci[j]));
                            psUpdate.setInt(5, anio);
                        //    out.print("--------------OFF--------------");
                        }
                    }
                    else{
                        psUpdate.setInt(3, Integer.valueOf(ci[j]));
                        psUpdate.setInt(4, anio);
                    }
                }
                //out.print(psInsert);
                //out.print(psUpdate);
                psInsert.addBatch();
                psUpdate.addBatch();
        }
        try{
            psInsert.executeBatch();
        }
        catch(SQLException ex){
           // out.print(ex.getMessage());
            psUpdate.executeBatch();
            return true;
        }
        //psUpdate.executeBatch();
        
        connection.close();
        
        return true; 
    }
}
