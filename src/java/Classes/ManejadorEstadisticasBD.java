/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.Connection;

/**
 *
 * @author Gisel
 */
public class ManejadorEstadisticasBD {
    private Connection connection;

    public ManejadorEstadisticasBD() {
        connection = ConexionDB.GetConnection();
    }
    
    
    
}
