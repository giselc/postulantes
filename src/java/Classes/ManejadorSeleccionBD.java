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
public class ManejadorSeleccionBD {
    private Connection connection;

    public ManejadorSeleccionBD() {
        connection = ConexionDB.GetConnection();
    }
    public boolean existenCasosAAnalizar(){
        java.util.Calendar fecha1 = java.util.Calendar.getInstance();
        int anio = fecha1.get(java.util.Calendar.YEAR);    
        String sql="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where resultado = 3 and notas.anio= "+ anio +" order by postulantes.resultados.promedio desc, numero asc";
        Statement s;
        try {
            s = connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            if(rs.next()){
                return true;
            }
            else{
                String sql1="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where (matematica<5 or historia<5 or idiomaEspanol<5 or educacionFisica<5 or medico<>1 or psicotecnica<>1 or odontologico<>1 or arrojo<>1 or psicoSeg<>0 or medicoSeg<>0 or odontSeg<>0) and resultado = -1 and notas.anio= "+ anio +" order by postulantes.resultados.promedio desc, numero asc";
                rs=s.executeQuery(sql1);
                if(rs.next()){
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorSeleccionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //precedencia es un campo en la base de datos que guarda el orden por promedio del cadete. La precedencia Final con la que ingresa a la escuela.
    public ArrayList<Postulante> getPostulantesSeleccion(int resultado, int usuario,int carrera){
            ArrayList<Postulante> al= new ArrayList<>();
            ManejadorCodigoBD mc = new ManejadorCodigoBD();
            try {
                Statement s= connection.createStatement();
                Usuario u = mc.getUsuario(usuario);
                String sql = "";
                String sql1 = "";
                int anio = ManejadorPostulanteDB.getAnioPostula();
                if(u.isAdmin()){ 
                    if(carrera==1){
                        if(resultado!=4){ // si no listo los promedios...
                            sql="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where postulantes.nsp<>1 and postulantes.renuncio<>1 and carrera=" + carrera + " and resultado = " + resultado + " and notas.anio= "+ anio +" order by postulantes.resultados.promedio desc, numero asc";
                            if(resultado==0){//entran
                                sql1="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where postulantes.nsp<>1 and postulantes.renuncio<>1 and ((reingreso=0 and matematica>=5 and historia>=5 and idiomaEspanol>=5) or reingreso=1) and educacionFisica>=5 and medico=1 and psicotecnica=1 and odontologico=1 and arrojo=1 and psicoSeg=0 and medicoSeg=0 and odontSeg=0 and carrera=" + carrera + " and resultado = -1 and notas.anio= "+ anio +" order by postulantes.resultados.promedio desc, numero asc";
                            }
                            else{
                                if(resultado==3){//casos a analizar
                                    sql1="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where postulantes.nsp<>1 and postulantes.renuncio<>1 and ((reingreso=0 and (matematica<5 or historia<5 or idiomaEspanol<5)) or educacionFisica<5 or medico<>1 or psicotecnica<>1 or odontologico<>1 or arrojo<>1 or psicoSeg<>0 or medicoSeg<>0 or odontSeg<>0) and carrera=" + carrera + " and resultado = -1 and notas.anio= "+ anio +" order by postulantes.resultados.promedio desc, numero asc";
                                }
                            }
                        }
                        else{
                            sql="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci where postulantes.anio="+anio+" and carrera=" + carrera + " order by numero asc";
                        }
                    }
                    else{
                        if(resultado!=4){ // si no listo los promedios...
                            sql="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where postulantes.nsp<>1 and postulantes.renuncio<>1 and carrera=" + carrera + " and resultado = " + resultado + " and notas.anio= "+ anio +" order by postulantes.resultados.promedio desc, numero asc";
                            if(resultado==0){//entran
                                sql1="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where postulantes.nsp<>1 and postulantes.renuncio<>1 and matematica>=5 and historia>=5 and idiomaEspanol>=5 and educacionFisica>=5 and medico=1 and psicotecnica=1 and odontologico=1 and arrojo=1 and psicoSeg=0 and medicoSeg=0 and odontSeg=0 and carrera=" + carrera + " and resultado = -1 and notas.anio= "+ anio +" order by postulantes.resultados.promedio desc, numero asc";
                            }
                            else{
                                if(resultado==3){//casos a analizar
                                    sql1="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where postulantes.nsp<>1 and postulantes.renuncio<>1 and (matematica<5 or historia<5 or idiomaEspanol<5 or educacionFisica<5  or medico<>1 or psicotecnica<>1 or odontologico<>1 or arrojo<>1 or psicoSeg<>0 or medicoSeg<>0 or odontSeg<>0) and carrera=" + carrera + " and resultado = -1 and notas.anio= "+ anio +" order by postulantes.resultados.promedio desc, numero asc";
                                }
                            }
                        }
                        else{
                            sql="SELECT postulantes.ci, primerNombre, primerApellido,segundoNombre,segundoApellido, numero, promedio, precedencia, unidadInsc FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci where postulantes.anio="+anio+" and carrera=" + carrera + " order by numero asc";
                        }
                    }
                }
                ResultSet rs=null;
                if(resultado==0||resultado==3){
                    rs=s.executeQuery(sql1);
                    if(rs.next()){
                        sql=sql1;
                    }
                }
                rs=s.executeQuery(sql);
                Postulante p = null;
                ManejadorNotasBD mn = new ManejadorNotasBD();
                while (rs.next()){
                    int id=-1;
                    if (rs.getInt("Numero")!=0){
                        id=rs.getInt("Numero");
                    }
                    RecordPostulanteNota n = mn.getNota(rs.getInt("ci"), carrera, anio);
                    p = new Postulante(rs.getInt("ci"), rs.getString("primerNombre"), rs.getString("segundoNombre"),rs.getString("primerApellido"), rs.getString("segundoApellido"),mc.getCarrera(carrera),id,mc.getUsuario(rs.getInt("unidadInsc")));
                    p.setResultado(resultado);
                    p.setNotas(n);
                    p.setPromedio(rs.getDouble("promedio"));
                    p.setPrecedencia(rs.getInt("precedencia"));
                    al.add(p);
                }
                
                
            } catch (Exception ex) {
                Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return al;
        }
    public void imprimirSabana(RecordPostulanteFiltro rf,String[]lista,int carrera,PrintWriter out){
        int anio = ManejadorPostulanteDB.getAnioPostula();
        ManejadorCodigoBD mc = new ManejadorCodigoBD();
        ManejadorNotasBD mn= new ManejadorNotasBD();
        String filtro = ManejadorPostulanteDB.getFiltroSQL(rf);
        String sql="";
        String sql1="";
        if(carrera==1){
            if(!mn.hayNotasCargadas(anio)){
                sql="SELECT numero, primerNombre, primerApellido,segundoNombre,segundoApellido, postulantes.ci, sexo, UnidadInsc, FechaNac, lmga, quinto, orientacion, departamento, observaciones FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci where carrera=1 "+filtro+" order by numero asc";
            }
            else{
                sql="SELECT edFisicaObs, numero, primerNombre, primerApellido,segundoNombre,segundoApellido, postulantes.ci, sexo, UnidadInsc, FechaNac, lmga, quinto, orientacion, departamento, observaciones, matematica, historia, idiomaEspanol, educacionfisica, arrojo, medico, medicoSeg, medicoobs,psicotecnica, psicoseg, psicoObs, odontologico, odontSeg, odontObs, promedio FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where matematica>=5 and historia>=5 and idiomaEspanol>=5 and educacionFisica>=5 and medico=1 and psicotecnica=1 and odontologico=1 and arrojo=1 and psicoSeg=0 and medicoSeg=0 and odontSeg=0 and carrera=1 and notas.anio= "+ anio +" "+ filtro +" order by postulantes.resultados.promedio desc";
                sql1="SELECT edFisicaObs, numero, primerNombre, primerApellido,segundoNombre,segundoApellido, postulantes.ci, sexo, UnidadInsc, FechaNac, lmga, quinto, orientacion, departamento, observaciones, matematica, historia, idiomaEspanol, educacionfisica, arrojo, medico, medicoSeg, medicoobs,psicotecnica, psicoseg, psicoObs, odontologico, odontSeg, odontObs, promedio FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where (matematica<5 or historia<5 or idiomaEspanol<5 or educacionFisica<5 or medico<>1 or psicotecnica<>1 or odontologico<>1 or arrojo<>1 or psicoSeg<>0 or medicoSeg<>0 or odontSeg<>0) and carrera=1 and notas.anio= "+ anio +" "+ filtro + " order by postulantes.resultados.promedio desc";
            }
        }
        else{
            if(mn.getNotas(2, anio).isEmpty()){
                sql="SELECT numero, primerNombre, primerApellido,segundoNombre,segundoApellido, postulantes.ci, sexo, UnidadInsc, FechaNac, lmga, quinto, orientacion, departamento, observaciones FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci where carrera=2 "+filtro+" order by numero asc";
            }
            else{
                sql="SELECT edFisicaObs, numero, primerNombre, primerApellido,segundoNombre,segundoApellido, postulantes.ci, sexo, UnidadInsc, FechaNac, lmga, quinto, orientacion, departamento, observaciones, matematica, historia, idiomaEspanol, educacionfisica, arrojo, medico, medicoSeg, medicoobs,psicotecnica, psicoseg, psicoObs, odontologico, odontSeg,odontObs, promedio FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where matematica>=5 and historia>=5 and idiomaEspanol>=5 and educacionFisica>=5 and medico=1 and psicotecnica=1 and odontologico=1 and arrojo=1 and psicoSeg=0 and medicoSeg=0 and odontSeg=0 and carrera=2 and notas.anio= "+ anio +" "+ filtro +" order by postulantes.resultados.promedio desc";   
                sql1="SELECT edFisicaObs, numero, primerNombre, primerApellido,segundoNombre,segundoApellido, postulantes.ci, sexo, UnidadInsc, FechaNac, lmga, quinto, orientacion, departamento, observaciones, matematica, historia, idiomaEspanol, educacionfisica, arrojo, medico, medicoSeg, medicoobs,psicotecnica, psicoseg, psicoObs, odontologico, odontSeg,odontObs, promedio FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where (matematica<5 or historia<5 or idiomaEspanol<5 or educacionFisica<5 or medico<>1 or psicotecnica<>1 or odontologico<>1 or arrojo<>1 or psicoSeg<>0 or medicoSeg<>0 or odontSeg<>0) and carrera=2 and notas.anio= "+ anio +" "+ filtro +" order by postulantes.resultados.promedio desc";   
            }
        }
        out.print("<html><head><script src=\"js/jquery-1.9.1.min.js\"></script>");
                        out.print("<script src=\"js/jquery.table2excel.js\"></script>");
                        out.print("<script> jQuery(document).ready(function() {\n" +
                                    "    $('#export-btn').on('click', function(e){\n" +
                                    "        e.preventDefault();\n" +
                                    "        ResultsToTable();\n" +
                                    "    });\n" +
                                    "    function ResultsToTable(){    \n" +
                                    "        $(\".resultsTable\").table2excel({\n" +
                                    "            exclude: \".noExl\",\n" +
                                    "            name: \"Results\",\n"+
    "					fileext: \".xls\",\n" +
                                    "        });\n" +
                                    "    }\n" +
                                    "}); </script></head><body>");
                        out.print("<BUTTON style='color:#0000ff' id=\"export-btn\">Export</BUTTON>");
        out.print("<style>\n" +
    "    tr{\n" +
    "        page-break-inside: avoid;\n" +
    "        page-break-after: auto;\n" +
    "    }    \n" +
    "</style><table class=\"resultsTable\" style=\"width: 100%; text-align: center\"><tr><td>"
        
                + "<table class=\"noExl\" style=\"width: 100%; text-align: center\">\n" +
    "    <tr>\n" +
    "        <td>Escuela Militar</td>\n" +
    "        <td></td>\n" +
    "        <td>Jefatura de Estudios</td>\n" +
    "    </tr>\n" +
    "    <tr>\n" +
    "        <td></td>\n" +
    "        <td><h3 align=\"center\">SÁBANA ");
        if(carrera==1){
            out.print("CUERPO COMANDO ");
        }
        else{
            out.print("APOYO DE SERVICIO Y COMBATE ");
        }
                out.print(anio+"</h3></td>\n" +
    "        <td></td>\n" +
    "    </tr>\n");
          out.print("<tr><td>"+rf.filtroMostrar+"</td></tr>");      
        out.print("<tr>");
        
        out.print("</table><table class=\"noExl\" style=\"width: 100%; text-align: center;page-break-inside: auto;\" border='1' cellspacing='0'></tr>");
        out.print("<td>Nº</td>");
        for(String l:lista){
            switch(l){
                case "ID": out.print("<td>ID</td>");break;
                case "PrimerNombre": out.print("<td>Primer Nombre</td>");break;
                case "SegundoNombre": out.print("<td>Segundo Nombre</td>");break;
                case "PrimerApellido": out.print("<td>Primer Apellido</td>");break;
                case "SegundoApellido": out.print("<td>Segundo Apellido</td>");break;
                case "CI": out.print("<td>CI</td>");break;
                case "Sexo": out.print("<td>Sexo</td>");break;
                case "UI": out.print("<td>UI</td>");break;
                case "FechaNac": out.print("<td>F.Nac.</td>");break;
                case "LM": out.print("<td>LM</td>");break;
                case "Aprobado": out.print("<td>Aprobado</td>");break;
                case "Orient": out.print("<td>Orient.</td>");break;
                case "DepDom": out.print("<td>Dep.Dom.</td>");break;
                case "Obs": out.print("<td>Observaciones de Ingreso</td>");break;
                case "Mat": out.print("<td>Mat.</td>");break;
                case "Hist": out.print("<td>Hist.</td>");break;
                case "IdEsp": out.print("<td>Id.Esp.</td>");break;
                case "EdFis": out.print("<td>Ed.Fis.</td>");break;
                case "Arrojo": out.print("<td>Arrojo</td>");break;
                case "Medico": out.print("<td>M</td>");break;
                case "ObsMedico": out.print("<td>Obs.Medico</td>");break;
                case "Psico": out.print("<td>P</td>");break;
                case "ObsPsico": out.print("<td>Obs.Psico</td>");break;
                case "Odont": out.print("<td>O</td>");break;
                case "ObsOdont": out.print("<td>Obs.Odont</td>");break;
                case "Promedio": out.print("<td>Promedio</td>");break;
                case "ObsEdFisica": out.print("<td>Obs.Ed.Fisica</td>");break;
            }
        }


        try{
            Statement s= connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            int i=1;
            //IMPRIMIR LOS QUE SALVARON TODAS LAS PRUEBAS ORDENADO POR PROMEDIO
          for(int j=0; j<=1;j++){
                while(rs.next()){
                    out.print("<tr>");
                    out.print("<td>"+i+"</td>");
                    i++;
                    for(String l:lista){
                        switch(l){
                            case "ID": 
                                    out.print("<td>");
                                    if(rs.getInt("Numero")==0){
                                        out.print("COND.");
                                    }
                                    else{
                                        out.print(rs.getInt("Numero"));
                                    }
                                    out.print("</td>");break;
                            case "PrimerNombre": out.print("<td>"+rs.getString("PrimerNombre")+"</td>");break;
                            case "SegundoNombre": out.print("<td>"+rs.getString("SegundoNombre")+"</td>");break;
                            case "PrimerApellido": out.print("<td>"+rs.getString("PrimerApellido")+"</td>");break;
                            case "SegundoApellido": out.print("<td>"+rs.getString("SegundoApellido")+"</td>");break;
                            case "CI": out.print("<td>"+rs.getInt("ci")+"</td>");break;
                            case "Sexo": out.print("<td>"+rs.getString("sexo")+"</td>");break;
                            case "UI":
                                Usuario u = mc.getUsuario(rs.getInt("unidadInsc"));
                                out.print("<td>"+u.getNombreMostrar()+"</td>");break;
                            case "FechaNac": out.print("<td>"+rs.getString("FechaNac")+"</td>");break;
                            case "LM": 
                                    out.print("<td>");
                                    if(rs.getBoolean("lmga")){
                                        out.print("SI");
                                    }else{
                                        out.print("NO");
                                    }
                                    out.print("</td>");break;
                            case "Aprobado": 
                                    out.print("<td>");
                                    if(rs.getBoolean("quinto")){
                                        out.print("5to.");
                                    }else{
                                        out.print("6to.");
                                    }
                                    out.print("</td>");break;
                            case "Orient": 
                                    out.print("<td>");
                                    if(rs.getInt("orientacion")==1){
                                        out.print("SH");
                                    }
                                    else{
                                        out.print("SE");
                                    }
                                    out.print("</td>");break;
                            case "DepDom": 
                                Departamento d = mc.getDepartamento(rs.getInt("DEPARTAMENTO"));
                                out.print("<td>"+d.getDescripcion()+"</td>");break;
                            case "Obs": out.print("<td>"+rs.getString("OBSERVACIONES")+"</td>");break;
                            case "ObsEdFisica": if(!sql1.equals("")){out.print("<td>"+rs.getString("edFisicaObs")+"</td>");}; break;
                            case "Mat": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getDouble("MATEMATICA")<5){
                                            out.print("<font color=\"red\">"+rs.getDouble("MATEMATICA")+"</font>");
                                        }
                                        else{
                                            out.print(rs.getDouble("MATEMATICA"));
                                        }
                                    }
                                    out.print("</td>");break;
                            case "Hist": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getDouble("historia")<5){
                                            out.print("<font color=\"red\">"+rs.getDouble("historia")+"</font>");
                                        }
                                        else{
                                            out.print(rs.getDouble("historia"));
                                        }
                                    }
                                    out.print("</td>");break;
                            case "IdEsp": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getDouble("idiomaEspanol")<5){
                                            out.print("<font color=\"red\">"+rs.getDouble("idiomaEspanol")+"</font>");
                                        }
                                        else{
                                            out.print(rs.getDouble("idiomaEspanol"));
                                        }
                                    }
                                    out.print("</td>");break;
                            case "EdFis": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getDouble("educacionFisica")<5){
                                            out.print("<font color=\"red\">"+rs.getDouble("educacionFisica")+"</font>");
                                        }
                                        else{
                                            out.print(rs.getDouble("educacionFisica"));
                                        }
                                    }
                                    out.print("</td>");break;
                            case "Arrojo": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getBoolean("arrojo")){
                                            out.print("SI");
                                        }else{
                                            out.print("<font color=\"red\">NO</font>");
                                        }
                                    }
                                    out.print("</td>");break;
                            case "Medico": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getInt("medico")==1){
                                            if(rs.getBoolean("medicoSeg")){
                                                out.print("<font color=\"red\">AC</font>");
                                            }
                                            else{
                                                out.print("A");
                                            }
                                        }
                                        else{
                                            out.print("<font color=\"red\">NA</font>");
                                        }
                                    }
                                    out.print("</td>");break;
                            case "ObsMedico":out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getString("medicoObs")!=null){
                                            rs.getString("medicoObs");
                                        }
                                    }
                                    out.print("</td>");break;
                            case "Psico": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getInt("psicotecnica")==1){
                                            if(rs.getBoolean("psicoSeg")){
                                                out.print("<font color=\"red\">AC</font>");
                                            }
                                            else{
                                                out.print("A");
                                            }

                                        }
                                        else{
                                            out.print("<font color=\"red\">NA</font>");
                                        }
                                    }
                                    out.print("</td>");break;
                            case "ObsPsico": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getString("psicoObs")!=null){
                                            rs.getString("psicoObs");
                                        }
                                    }
                                    out.print("</td>");break;
                            case "Odont": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getInt("odontologico")==1){
                                            if(rs.getBoolean("odontSeg")){
                                                out.print("<font color=\"red\">AC</font>");
                                            }
                                            else{
                                                out.print("A");
                                            }
                                        }
                                        else{
                                            out.print("<font color=\"red\">NA</font>");
                                        }
                                    }
                                    out.print("</td>");break;
                            case "ObsOdont": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getString("odontObs")!=null){
                                            rs.getString("odontObs");
                                        }
                                    }
                                    out.print("</td>");break;
                            case "Promedio": out.print("<td>");
                                    if(!sql1.equals("")){
                                        if(rs.getDouble("promedio")<5){
                                            out.print("<font color=\"red\">"+rs.getDouble("promedio")+"</font>");
                                        }
                                        else{
                                            out.print(rs.getDouble("promedio"));
                                        }
                                    }
                                    out.print("</td>");break;
                        }
                    }
                    out.print("</tr>");
                }
            //IMPRIMIR LOS QUE PERDIERON ALGUNA PRUEBA ORDENADO POR PROMEDIO
                
                rs=s.executeQuery(sql1);
            } 

        }
        catch(Exception ex){
                            Logger.getLogger(ManejadorSeleccionBD.class.getName()).log(Level.SEVERE, null, ex);

        }

       out.print("</table></td></tr></table></body></html>");
    }
    public void imprimirResultadosFinales(String[]lista,int entran,PrintWriter out){
        int anio = ManejadorPostulanteDB.getAnioPostula();
        ManejadorCodigoBD mc = new ManejadorCodigoBD();
        String sql="SELECT precedencia,numero, segundoNombre, segundoApellido, primerNombre, primerApellido, postulantes.ci, sexo, UnidadInsc, FechaNac, lmga, quinto, orientacion, cc, ccnro, telefono, domicilio, departamento, observaciones, matematica, historia, idiomaEspanol, educacionfisica, arrojo, medico, medicoSeg, medicoobs,psicotecnica, psicoseg, psicoObs, odontologico, odontSeg, odontObs, promedio FROM postulantes.postulantes LEFT JOIN postulantes.comando ON postulantes.ci = comando.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where carrera=1 and notas.anio= "+ anio +" and resultado="+ entran +" order by postulantes.resultados.promedio desc, numero asc";
        String sql1="SELECT precedencia,numero,  segundoNombre, segundoApellido, primerNombre, primerApellido, postulantes.ci, sexo, UnidadInsc, FechaNac, lmga, quinto, orientacion, cc, ccnro, telefono, domicilio, departamento, observaciones, matematica, historia, idiomaEspanol, educacionfisica, arrojo, medico, medicoSeg, medicoobs,psicotecnica, psicoseg, psicoObs, odontologico, odontSeg, odontObs, promedio FROM postulantes.postulantes LEFT JOIN postulantes.apoyo ON postulantes.ci = apoyo.ci LEFT JOIN postulantes.resultados ON postulantes.ci = resultados.ci LEFT JOIN postulantes.notas on postulantes.ci = notas.ci where carrera=2 and notas.anio= "+ anio +" and resultado="+ entran +" order by postulantes.resultados.promedio desc, numero asc";   
        try{       
            for(int k=0; k<=1;k++){
                out.print("<style>\n" +
                "    tr{\n" +
                "        page-break-inside: avoid;\n" +
                "        page-break-after: auto;\n" +
                "    }    \n" +
                "</style>"
                            + "<table style=\"width: 100%; text-align: center\">\n" +
                "    <tr>\n" +
                "        <td style='width:20%'>Escuela Militar</td>\n" +
                "        <td></td>\n" +
                "        <td style='width:20%'>Jefatura de Estudios</td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td style='width:20%'></td>\n" +
                "        <td><h3 align=\"center\">LISTADO DE POSTULANTES ");
                if(entran==0){
                    out.print("QUE ENTRAN ");
                }
                else{
                    if(entran==2){
                        out.print("QUE ESTAN EN LISTA DE ESPERA ");
                    }
                    else{
                        out.print("QUE NO ENTRAN ");
                    }
                }
                        out.print(anio+"</h3></td>\n" +
                "        <td style='width:20%'></td>\n" +
                "    </tr>\n"+
                "<tr><td></td><td><h3 align=\"center\">");
                if(k==0){
                    out.print("CUERPO COMANDO");
                }else{
                    out.print("APOYO DE SERVICIO Y COMBATE");    
                }
                out.print("</h3></td><td></td></tr>\n");
                out.print("<tr>");
                out.print("</table><table style=\"width: 100%; text-align: center;page-break-inside: auto;\" border='1' cellspacing='0'></tr>");
                out.print("<td>PRECEDENCIA</td>");
                for(String l:lista){
                    switch(l){
                        case "ID": out.print("<td>ID</td>");break;
                        case "PrimerNombre": out.print("<td>Primer Nombre</td>");break;
                        case "SegundoNombre": out.print("<td>Segundo Nombre</td>");break;
                        case "PrimerApellido": out.print("<td>Primer Apellido</td>");break;
                        case "SegundoApellido": out.print("<td>Segundo Apellido</td>");break;
                        case "CI": out.print("<td>CI</td>");break;
                            case "Telefono": out.print("<td>Tel.</td>");break;
                                case "Credencial": out.print("<td>Cred.</td>");break;
                        case "Sexo": out.print("<td>Sexo</td>");break;
                        case "UI": out.print("<td>UI</td>");break;
                        case "FechaNac": out.print("<td>F.Nac.</td>");break;
                        case "LM": out.print("<td>LM</td>");break;
                        case "Aprobado": out.print("<td>Aprobado</td>");break;
                        case "Orient": out.print("<td>Orient.</td>");break;
                            case "Dom": out.print("<td>Dom.</td>");break;
                        case "DepDom": out.print("<td>Dep.Dom.</td>");break;
                        case "Obs": out.print("<td>Observaciones de Ingreso</td>");break;
                        case "Mat": out.print("<td>Mat.</td>");break;
                        case "Hist": out.print("<td>Hist.</td>");break;
                        case "IdEsp": out.print("<td>Id.Esp.</td>");break;
                        case "EdFis": out.print("<td>Ed.Fis.</td>");break;
                        case "Arrojo": out.print("<td>Arrojo</td>");break;
                        case "Medico": out.print("<td>M</td>");break;
                        case "ObsMedico": out.print("<td>Obs.Medico</td>");break;
                        case "Psico": out.print("<td>P</td>");break;
                        case "ObsPsico": out.print("<td>Obs.Psico</td>");break;
                        case "Odont": out.print("<td>O</td>");break;
                        case "ObsOdont": out.print("<td>Obs.Odont</td>");break;
                        case "Promedio": out.print("<td>Promedio</td>");break;
                    }
                }
                Statement s= connection.createStatement();
                ResultSet rs=null;
                if(k==0){
                    rs=s.executeQuery(sql);
                }else{
                    rs=s.executeQuery(sql1);
                }
                int i=1;
                while(rs.next()){
                    out.print("<tr>");
                    out.print("<td>"+i+"</td>");
                    i++;
                    for(String l:lista){
                        switch(l){
                            case "ID": 
                                    out.print("<td>");
                                    if(rs.getInt("Numero")==0){
                                        out.print("COND.");
                                    }
                                    else{
                                        out.print(rs.getInt("Numero"));
                                    }
                                    out.print("</td>");break;
                            case "PrimerNombre": out.print("<td>"+rs.getString("PrimerNombre")+"</td>");break;
                            case "SegundoNombre": out.print("<td>"+rs.getString("SegundoNombre")+"</td>");break;
                            case "PrimerApellido": out.print("<td>"+rs.getString("PrimerApellido")+"</td>");break;
                            case "SegundoApellido": out.print("<td>"+rs.getString("SegundoApellido")+"</td>");break;
                            case "CI": out.print("<td>"+rs.getInt("ci")+"</td>");break;
                                case "Telefono": out.print("<td>"+rs.getString("telefono")+"</td>");break;
                                case "Credencial": out.print("<td>"+rs.getString("cc")+" "+rs.getInt("ccnro")+"</td>");break;
                            case "Sexo": out.print("<td>"+rs.getString("sexo")+"</td>");break;
                            case "UI": 
                                Usuario u = mc.getUsuario(rs.getInt("unidadInsc"));
                                out.print("<td>"+u.getNombreMostrar()+"</td>");break;
                            case "FechaNac": out.print("<td>"+rs.getString("FechaNac")+"</td>");break;
                            case "LM": 
                                    out.print("<td>");
                                    if(rs.getBoolean("lmga")){
                                        out.print("SI");
                                    }else{
                                        out.print("NO");
                                    }
                                    out.print("</td>");break;
                            case "Aprobado": 
                                    out.print("<td>");
                                    if(rs.getBoolean("quinto")){
                                        out.print("5to.");
                                    }else{
                                        out.print("6to.");
                                    }
                                    out.print("</td>");break;
                            case "Orient": 
                                    out.print("<td>");
                                    if(rs.getInt("orientacion")==1){
                                        out.print("SH");
                                    }
                                    else{
                                        out.print("SE");
                                    }
                                    out.print("</td>");break;
                            case "Dom": out.print("<td>"+rs.getString("domicilio")+"</td>");break;
                            case "DepDom": 
                                Departamento d = mc.getDepartamento(rs.getInt("DEPARTAMENTO"));
                                out.print("<td>"+d.getDescripcion()+"</td>");break;
                            case "Obs": out.print("<td>"+rs.getString("OBSERVACIONES")+"</td>");break;
                            case "Mat": out.print("<td>"+rs.getDouble("MATEMATICA")+"</td>");break;
                            case "Hist": out.print("<td>"+rs.getDouble("HISTORIA")+"</td>");break;
                            case "IdEsp": out.print("<td>"+rs.getDouble("IDIOMAESPANOL")+"</td>");break;
                            case "EdFis": out.print("<td>"+rs.getDouble("EDUCACIONFISICA")+"</td>");break;
                            case "Arrojo": 
                                        out.print("<td>");
                                        if(rs.getBoolean("arrojo")){
                                            out.print("SI");
                                        }else{
                                            out.print("NO");
                                        }
                                        out.print("</td>");break;
                            case "Medico": 
                                        out.print("<td>");
                                        if(rs.getInt("medico")==1){
                                            if(rs.getBoolean("medicoSeg")){
                                                out.print("AC");
                                            }
                                            else{
                                                out.print("A");
                                            }

                                        }
                                        else{
                                            out.print("NA");
                                        }
                                        out.print("</td>");break;
                            case "ObsMedico": 
                                        out.print("<td>");
                                        if(rs.getString("medicoObs")!=null){
                                            rs.getString("medicoObs");
                                        }
                                        out.print("</td>");break;
                            case "Psico": 
                                        out.print("<td>");
                                        if(rs.getInt("psicotecnica")==1){
                                            if(rs.getBoolean("psicoSeg")){
                                                out.print("AC");
                                            }
                                            else{
                                                out.print("A");
                                            }

                                        }
                                        else{
                                            out.print("NA");
                                        }
                                        out.print("</td>");break;
                            case "ObsPsico":
                                        out.print("<td>");
                                        if(rs.getString("psicoObs")!=null){
                                            rs.getString("psicoObs");
                                        }
                                        out.print("</td>");break;
                            case "Odont": 
                                        out.print("<td>");
                                        if(rs.getInt("odontologico")==1){
                                            if(rs.getBoolean("odontSeg")){
                                                out.print("AC");
                                            }
                                            else{
                                                out.print("A");
                                            }

                                        }
                                        else{
                                            out.print("NA");
                                        }
                                        out.print("</td>");break;
                            case "ObsOdont": 
                                        out.print("<td>");
                                        if(rs.getString("odontObs")!=null){
                                            rs.getString("odontObs");
                                        }
                                        out.print("</td>");break;
                            case "Promedio": out.print("<td>"+rs.getDouble("promedio")+"</td>");break;
                        }
                    }
                    out.print("</tr>");
                }
                out.print("</table>");
                if(k==0){
                   out.print(" <h1 style='page-break-after: always;'></h1>");
                }
            }

        }
        catch(Exception ex){
                            Logger.getLogger(ManejadorSeleccionBD.class.getName()).log(Level.SEVERE, null, ex);

        }

    out.print("</table>");
    }
    public boolean guardarPromediosAuto(PrintWriter out)throws SQLException{
        ManejadorNotasBD mn = new ManejadorNotasBD();
        int anio = ManejadorPostulanteDB.getAnioPostula();
        ArrayList<RecordPostulanteNota> rn= mn.getNotas(1, anio);
        ArrayList<RecordPostulanteNota> rn2= mn.getNotas(2, anio);
        String sqlInsert = "insert into postulantes.resultados (promedio, resultado, precedencia, ci, anio) values (?,?,?,?,?)";;
        String sqlUpdate = "update postulantes.resultados set promedio=?, resultado=?, precedencia=? where ci=? and anio=?";
        PreparedStatement psInsert;
        PreparedStatement psUpdate;
        psUpdate = connection.prepareStatement(sqlUpdate);
        psInsert = connection.prepareStatement(sqlInsert); 
        for(RecordPostulanteNota r:rn){

                    psInsert.setDouble(1, mn.calcularPromedio(r));
                    psInsert.setInt(2, -1);
                    psInsert.setInt(3, 0);
                    psInsert.setInt(4, r.ci);
                    psInsert.setInt(5,anio);
                    //out.print(psInsert);
                    psUpdate.setDouble(1, mn.calcularPromedio(r));
                    psUpdate.setInt(2, -1);
                    psUpdate.setInt(3, 0);
                    psUpdate.setInt(4, r.ci);
                    psUpdate.setInt(5,anio);
                    //out.print(psUpdate);

                    psInsert.addBatch();
                    psUpdate.addBatch();
        }
        for(RecordPostulanteNota r:rn2){

                    psInsert.setDouble(1, mn.calcularPromedio(r));
                    psInsert.setInt(2, -1);
                    psInsert.setInt(3, 0);
                    psInsert.setInt(4, r.ci);
                    psInsert.setInt(5,anio);
                    //out.print(psInsert);
                    psUpdate.setDouble(1, mn.calcularPromedio(r));
                    psUpdate.setInt(2, -1);
                    psUpdate.setInt(3, 0);
                    psUpdate.setInt(4, r.ci);
                    psUpdate.setInt(5,anio);
                    //out.print(psUpdate);

                    psInsert.addBatch();
                    psUpdate.addBatch();
        }
        out.print(psInsert);
        out.print(psUpdate);
        try{
            psInsert.executeBatch();
        }
        catch(SQLException ex){
            psUpdate.executeBatch();
            return true;
        }
        //psUpdate.executeBatch();

        connection.close();

        return true;
    }
    public boolean guardarPromedios(String[] ci, String[] promedios, PrintWriter out)throws SQLException{
        String sqlInsert = "insert into postulantes.resultados (promedio, resultado, ci, anio) values (?,?,?,?)";;
        String sqlUpdate = "update postulantes.resultados set promedio=? where ci=? and anio=?";
        PreparedStatement psInsert;
        PreparedStatement psUpdate;
        psUpdate = connection.prepareStatement(sqlUpdate);
        psInsert = connection.prepareStatement(sqlInsert);
        int anio = ManejadorPostulanteDB.getAnioPostula();
        int i=0;
        for(String s:ci){

                    psInsert.setDouble(1, Double.valueOf(promedios[i]));
                    psInsert.setInt(2, 0);
                    psInsert.setInt(3, Integer.valueOf(s));
                    psInsert.setInt(4,anio);
                    out.print(psInsert);
                    psUpdate.setDouble(1, Double.valueOf(promedios[i]));
                    psUpdate.setInt(2, Integer.valueOf(s));
                    psUpdate.setInt(3,anio);
                    out.print(psUpdate);

                    psInsert.addBatch();
                    psUpdate.addBatch();
                    i++;
        }

        try{
            psInsert.executeBatch();
        }
        catch(SQLException ex){
            psUpdate.executeBatch();
            return true;
        }
        //psUpdate.executeBatch();

        connection.close();

        return true;
    }
    public boolean guardarResultados(String[] ci, String[] resultados, String[] precedencia, int carrera) throws SQLException{
    String sqlUpdate = "update postulantes.resultados set resultado=?, precedencia=? where ci=? and anio=?";
    PreparedStatement psUpdate;
    psUpdate = connection.prepareStatement(sqlUpdate);
    int anio = ManejadorPostulanteDB.getAnioPostula();
    int i=0;
    for(String s:ci){
                psUpdate.setInt(1, Integer.valueOf(resultados[i]));
                psUpdate.setInt(2, Integer.valueOf(precedencia[i]));
                psUpdate.setInt(3, Integer.valueOf(s));
                psUpdate.setInt(4,anio);
                psUpdate.addBatch();
                i++;
    }
    
    try{
        psUpdate.executeBatch();
    }
    catch(SQLException ex){
        return false;
    }
    //psUpdate.executeBatch();

    connection.close();
    
    return true;    
}

}
