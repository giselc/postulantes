/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Gisel
 */
public class ManejadorCodigoBD {
    private final Connection connection;

    public ManejadorCodigoBD() {
        connection = ConexionDB.GetConnection();
    }
    public static String fechaFormatoDDMMAAAA(Date fecha){
        String dia= String.valueOf(fecha.getDate());
        if (fecha.getDate()<10){
            dia="0"+dia;
        }
        String mes = String.valueOf(fecha.getMonth()+1);
        if(fecha.getMonth()+1<10){
            mes="0"+mes;
        }
        return dia+"-"+mes+"-"+(fecha.getYear()+1900);
    }
    
    public Departamento getDepartamento(int codigo){
        Departamento d= null;
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.departamentos where codigo="+ codigo;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                d = new Departamento(codigo, rs.getString("descripcion"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    public ArrayList<Departamento> getDepartamentos(){
        ArrayList<Departamento> al= new ArrayList<>();
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.departamentos order by descripcion asc";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                al.add(new Departamento(rs.getInt("codigo"), rs.getString("descripcion")));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public EstadoCivil getEstadoCivil(int codigo){
        EstadoCivil d= null;
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.estadocivil where codigo="+ codigo;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                d = new EstadoCivil(codigo, rs.getString("descripcion"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    public ArrayList<EstadoCivil> getEstadosCiviles(){
        ArrayList<EstadoCivil> al= new ArrayList<>();
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.estadocivil order by descripcion asc";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                al.add(new EstadoCivil(rs.getInt("codigo"), rs.getString("descripcion")));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public Orientacion getOrientacion(int codigo){
        Orientacion d= null;
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.orientacion where codigo="+ codigo;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                d = new Orientacion(codigo, rs.getString("descripcion"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    public ArrayList<Orientacion> getOrientaciones(){
        ArrayList<Orientacion> al= new ArrayList<>();
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.orientacion order by descripcion asc";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                al.add(new Orientacion(rs.getInt("codigo"), rs.getString("descripcion")));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public Usuario getUsuario(int id){
        Usuario d= null;
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.usuarios where id="+ id;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                d = new Usuario(id, rs.getString("nombre"), rs.getString("mostrar"),rs.getBoolean("superAdmin"), rs.getBoolean("admin"),rs.getString("email"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    public ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> al= new ArrayList<>();
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.usuarios order by mostrar asc";
            ResultSet rs=s.executeQuery(sql);
            while (rs.next()){
                al.add(new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("mostrar"),rs.getBoolean("superAdmin"), rs.getBoolean("admin"),rs.getString("email")));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }
    public Carrera getCarrera(int codigo){
        Carrera d= null;
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.carrera where codigo="+ codigo;
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                d = new Carrera(codigo, rs.getString("descripcion"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
    
    //retorna el userID; si no existe retorna -1
    public int loginCorrecto(String user, String pass){
        int b=-1;
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.usuarios where nombre='"+ user+"' and contrasena=MD5('"+pass+"')";
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
               b=rs.getInt("id");
            }
        } catch (Exception ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }
  /*  public int cambiarContrasena(int userId, String passOld, String passNew){
        int b=-1;
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.usuarios where id=" + String.valueOf(userId) + " and contrasena=MD5('" + passOld + "')";
            ResultSet rs=s.executeQuery(sql);
            if (rs.next()){
                String sql1="UPDATE postulantes.usuarios set contrasena=MD5('" + passNew + "') where id=" + String.valueOf(userId);
                Statement r= connection.createStatement();
                int a = r.executeUpdate(sql1);
                if(a>0){
                    b= 0;
                }
            }
        } catch (Exception ex) {
            //out.print(ex.getMessage());
        }
        return b;
    }*/
    
    //funcion creada para asegurar que no existe otro usuario con usuario='usuario'
    public boolean existeUsuario(String usuario){
        try {
            Statement s= connection.createStatement();
            String sql="Select * from postulantes.usuarios where nombre='"+usuario+"'";
            ResultSet rs= s.executeQuery(sql);
            if (rs.next()){
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //Retorna un Usuario con los parametros indicados persistiendolo en la base de datos si 'creador' es Admin.
    //retorna null si creador no es admin o se produjo algun error en la escritura de la base de datos.
    //PRECONDICIONES: - existeUsuario(usuario)==false
    public boolean crarUsuario(Usuario creador, String nombre, String nombreMostrar, String contrasena,boolean superAdmin, boolean admin, String email){
        if (creador.isSuperAdmin()){
            try {
                String sql= "insert into postulantes.usuarios (nombre, mostrar, contrasena,superAdmin,admin, email) values(?,?,MD5(?),?,?,?)";
                PreparedStatement s= connection.prepareStatement(sql);
                int i=1;
                s.setString(i++, nombre);
                s.setString(i++, nombreMostrar);
                s.setString(i++, contrasena);
                if(superAdmin){
                    admin=true;
                }
                s.setBoolean(i++, admin);
                s.setBoolean(i++, superAdmin);
                s.setString(i++, email);
                int result = s.executeUpdate();
                if(result>0){
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    //retorna false si 'creador' no es admin o se produjo algun error en la escritura de la base de datos.
    //atributo usuario no es modificable.
    //atributo contrasena modificable con la funcion cambiarContrasena
    public boolean ModificarUsuario(Usuario creador, int id, String nombreMostrar,boolean superAdmin, boolean admin, String email){
        if (creador.isSuperAdmin()){
            try {
                String sql= "Update postulantes.usuarios set mostrar=?, superAdmin=?, admin=?, email=? where id="+id;
                PreparedStatement s= connection.prepareStatement(sql);
                int i=1;
                s.setString(i++, nombreMostrar);
                s.setBoolean(i++, superAdmin);
                if(superAdmin){
                    admin=true;
                }
                s.setBoolean(i++, admin);
                s.setString(i++, email);
                int result = s.executeUpdate();
                if(result>0){
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    //retorna false si 'creador' no es admin o se produjo algun error en la escritura de la base de datos.
    public boolean eliminarUsuario(Usuario creador, int id){
        if (creador.isSuperAdmin()){
            try {
                String sql= "Select * from postulantes.postulantes where unidadInsc="+id;
                Statement s= connection.createStatement();
                ResultSet result1 = s.executeQuery(sql);
                if(result1.next()){
                    return false;
                }
                sql= "Select * from postulantes.historialpostulantes where unidadInsc="+id;
                result1 = s.executeQuery(sql);
                if(result1.next()){
                    return false;
                }
                sql= "Delete from postulantes.usuarios where id="+id;
                int result = s.executeUpdate(sql);
                if(result>0){
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    //retorna false si 'creador' no es admin(o no es contrasena propia) o su contrasena anterior es incorrecta o se produjo algun error en la escritura de la base de datos.
    public boolean cambiarContrasena(Usuario creador, int id, String contrasenaNueva, String contrasenaAnterior){
        if(creador.isSuperAdmin()){
            try {
                String sql= "Update postulantes.usuarios set contrasena=MD5('"+contrasenaNueva+"') where id="+id;
                Statement s= connection.createStatement();
                int result = s.executeUpdate(sql);
                if(result>0){
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            if(creador.getId()== id){
                try {
                    String sql= "Update postulantes.usuarios set contrasena=MD5('"+contrasenaNueva+"') where id="+id + " and contrasena=MD5('"+contrasenaAnterior+"')";
                    Statement s= connection.createStatement();
                    int result = s.executeUpdate(sql);
                    if(result>0){
                        return true;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    
    //Retorna la lista de Usuarios del sistema si el usuario 'creador' es admin
    public ArrayList<Usuario> getUsuarios(Usuario creador){
        ArrayList<Usuario> al= new ArrayList<>();
        if (creador.isAdmin()){
            try {
                String sql= "Select * from postulantes.usuarios";
                Statement s= connection.createStatement();
                ResultSet rs = s.executeQuery(sql);
                Usuario u;
                while(rs.next()){
                    u=new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("mostrar"), rs.getBoolean("superAdmin"), rs.getBoolean("admin"), rs.getString("email"));
                    al.add(u);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return al;
    }
    
    //retorna el usuario con id='id' si el usuario 'creador' es admin o es el mismo. 
    public Usuario getUsuario(Usuario creador, int id){
        Usuario u=null;
        if (creador.isAdmin() || creador.getId()==id){
            try {
                String sql= "Select * from postulantes.usuarios where id="+id;
                Statement s= connection.createStatement();
                ResultSet rs = s.executeQuery(sql);
                while(rs.next()){
                    u=new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("mostrar"), rs.getBoolean("superAdmin"), rs.getBoolean("admin"), rs.getString("email"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return u;
    }
    
    public Usuario getUsuario(String usuario, String pass){
       
        Usuario u= null;
        try {
                String sql= "Select * from postulantes.usuarios where usuario='"+usuario+"' and contrasena=MD5('"+pass+"')";
                Statement s= connection.createStatement();
                ResultSet rs = s.executeQuery(sql);
                while(rs.next()){
                    u=new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("mostrar"), rs.getBoolean("superAdmin"), rs.getBoolean("admin"), rs.getString("email"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ManejadorCodigoBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return u;
    }
    public void imprimirUsuarios(PrintWriter out, Usuario creador){
        out.print("<h1 align='center'>USUARIOS DEL SISTEMA</h1>");
        out.print("<table style=\"width: 100%;\">");
        ArrayList<Usuario> au = this.getUsuarios(creador);
        out.print("<tr style='background-color:#ffcc66'>");
                out.print("<td style='width: 20%' align='center'><h3 style='margin:2%;'>Usuario</h3></td>");
                out.print("<td style='width: 20%' align='center'><h3 style='margin:2%;'>Nombre para mostrar</h3></td>");
                out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>Super Admin</h3></td>");
                out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>Admin</h3></td>");
                out.print("<td style='width: 10%' align='center'><h3 style='margin:2%;'>Email</h3></td>");
        out.print("</tr></table>  <table style=\"width: 100%;\">" );
        int i=0;
        String color;
        for (Usuario u1: au){
            if ((i%2)==0){
                color=" #ccccff";
            }
            else{
                color=" #ffff99";
            }
            i++;

            out.print("<tr style='background-color:"+color+"'>");
                out.print("<td style='width: 20%' align='center'>"+u1.getNombre()+"</td>");
                out.print("<td style='width: 20%' align='center'>"+u1.getNombreMostrar()+"</td>");
                out.print("<td style='width: 10%' align='center'>"+u1.isSuperAdmin()+"</td>");
                out.print("<td style='width: 10%' align='center'>"+u1.isAdmin()+"</td>");
                out.print("<td style='width: 10%' align='center'>"+u1.getEmail()+"</td>"); 
            out.print("</tr>");
        }
        out.print("</table>");
    }

    
}
