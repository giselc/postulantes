/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Gisel
 */
public class Carrera {
    private final int codigo;
    private final String descripcion;
    
    public String getDescripcion() {
        return descripcion;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public Carrera(int codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }
}
