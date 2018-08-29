/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author Gisel
 */
public class ManejadorEstadisticasBD {
    private Connection connection;

    public ManejadorEstadisticasBD() {
        connection = ConexionDB.GetConnection();
    }
    
    public HashMap<Integer,Integer> getDatosEstadisticos(int anio, int carrera){
        int total=0;
        HashMap<Integer,Integer> hm= new HashMap<>();
        String sql ="SELECT `unidadInsc`, COUNT(*) as cant FROM postulantes.`historialpostulantes` where anio="+anio+" and carrera="+carrera+" group by `unidadInsc`";
        try {
            Statement s= connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                hm.put(rs.getInt("unidadInsc"),rs.getInt("cant"));
                total+=rs.getInt("cant");
            }
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        hm.put(-2, total);
        return hm;
    }
    public HashMap<Integer,Integer> getDatosEstadisticosEntran(int anio, int carrera){
        HashMap<Integer,Integer> hm= new HashMap<>();
        int total=0;
        String sql ="SELECT `unidadInsc`, COUNT(*) as cant FROM postulantes.`historialpostulantes` left join  postulantes.historialresultados on historialpostulantes.ci = historialresultados.ci and historialpostulantes.anio = historialresultados.anio where `historialpostulantes`.anio="+anio+" and carrera="+carrera+" and resultado = 0 group by `unidadInsc`";
        try {
            Statement s= connection.createStatement();
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                hm.put(rs.getInt("unidadInsc"),rs.getInt("cant"));
                total+=rs.getInt("cant");
            }
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
        hm.put(-2, total);
        return hm;
    }
    
    
}
