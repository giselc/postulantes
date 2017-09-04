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
public class Usuario {
    private int id;
    private String nombre;
    private String nombreMostrar;
    private boolean superAdmin;
    private boolean admin;
    private String email;

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    
    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombreMostrar() {
        return nombreMostrar;
    }

    public Usuario(int id, String nombre, String nombreMostrar,boolean superAdmin, boolean admin , String email) {
        this.id = id;
        this.nombre = nombre;
        this.nombreMostrar = nombreMostrar;
        this.admin = admin;
        this.superAdmin = superAdmin;
        this.email = email;
    }
    
}
