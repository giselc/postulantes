/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Date;
import java.util.Base64;

/**
 *
 * @author Gisel
 */
public class Postulante {
    private int id;
    private Carrera carrera;
    private int ci;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Usuario unidadInsc;
    private Date fechaNac;
    private String sexo;
    private Departamento departamentoNac;
    private String localidadNac;
    private String cc;
    private int ccNro;
    private EstadoCivil estadoCivil;
    private String domicilio;
    private Departamento departamento;
    private String localidad;
    private String telefono;
    private String email;
    private boolean quinto;
    private Orientacion orientacion;
    private boolean lmga;
    private boolean reingreso;
    private boolean paseDirecto;
    private double notaPaseDirecto;
    private String materiasPendientes;
    private boolean buenaConducta;
    private boolean ps;
    private boolean psEjercito;
    private int hijos;
    private String observaciones;
    private double promedio;
    private RecordPostulanteNota notas;
    private int resultado;
    private int precedencia;
    private Date fechaAlta;
    private boolean alojamiento;
    private boolean nsp;
    private boolean renuncio;
    private String talleOperacional; //S,M,L,XL,XXL,XXXL
    private int talleBotas; // entre 30 y 50
    private int talleQuepi;
    //patronimico
    //PADRE
    private String pNombreComp;
    private Date pFechaNac;
    private Departamento pDepartamentoNac;
    private String pLocalidadNac;
    private EstadoCivil pEstadoCivil;
    private String pDomicilio;
    private Departamento pDepartamento;
    private String pLocalidad;
    private String ptelefono;
    private String pProfesion;
    private String pLugarTrabajo;
    //MADRE
    private String mNombreComp;
    private Date mFechaNac;
    private Departamento mDepartamentoNac;
    private String mLocalidadNac;
    private EstadoCivil mEstadoCivil;
    private String mDomicilio;
    private Departamento mDepartamento;
    private String mLocalidad;
    private String mtelefono;
    private String mProfesion;
    private String mLugarTrabajo;
    private Blob foto;
    private Blob fotoCIAnverso;
    private Blob fotoCIReverso;
    private Blob fotoF69Hoja1;
    private Blob fotoF69Hoja2;
    private Blob fotoF69Hoja3;
    private Blob fotoF1Hoja1;
    private Blob fotoF1Hoja2;

    public Postulante(int ci, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, Carrera carrera,int id, Usuario u) {
        this.ci = ci;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.segundoNombre = segundoNombre;
        this.segundoApellido = segundoApellido;
        this.carrera = carrera;
        this.id=id;
        this.unidadInsc = u;
    }

    public boolean isNsp() {
        return nsp;
    }

    public void setNsp(boolean nsp) {
        this.nsp = nsp;
    }

    public boolean isRenuncio() {
        return renuncio;
    }

    public void setRenuncio(boolean renuncio) {
        this.renuncio = renuncio;
    }

    public String getTalleOperacional() {
        return talleOperacional;
    }

    public int getTalleBotas() {
        return talleBotas;
    }

    public int getTalleQuepi() {
        return talleQuepi;
    }

   

    
    public Postulante() {
    
    }
    
    
    public boolean isReingreso() {
        return reingreso;
    }

    public void setReingreso(boolean reingreso) {
        this.reingreso = reingreso;
    }

    public String getMateriasPendientes() {
        return materiasPendientes;
    }

    public void setMateriasPendientes(String materiasPendientes) {
        this.materiasPendientes = materiasPendientes;
    }

    public int getPrecedencia() {
        return precedencia;
    }

    public void setPrecedencia(int precedencia) {
        this.precedencia = precedencia;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public RecordPostulanteNota getNotas() {
        return notas;
    }

    public void setNotas(RecordPostulanteNota notas) {
        this.notas = notas;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public boolean isAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(boolean alojamiento) {
        this.alojamiento = alojamiento;
    }
    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public void setUnidadInsc(Usuario unidadInsc) {
        this.unidadInsc = unidadInsc;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setDepartamentoNac(Departamento departamentoNac) {
        this.departamentoNac = departamentoNac;
    }

    public void setLocalidadNac(String localidadNac) {
        this.localidadNac = localidadNac;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public void setCcNro(int ccNro) {
        this.ccNro = ccNro;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQuinto(boolean quinto) {
        this.quinto = quinto;
    }

    public void setOrientacion(Orientacion orientacion) {
        this.orientacion = orientacion;
    }

    public void setLmga(boolean lmga) {
        this.lmga = lmga;
    }

    public void setBuenaConducta(boolean buenaConducta) {
        this.buenaConducta = buenaConducta;
    }

    public void setPs(boolean ps) {
        this.ps = ps;
    }

    public void setPsEjercito(boolean psEjercito) {
        this.psEjercito = psEjercito;
    }

    public void setHijos(int hijos) {
        this.hijos = hijos;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setTalleOperacional(String talleOperacional) {
        this.talleOperacional = talleOperacional;
    }

    public void setTalleBotas(int talleBotas) {
        this.talleBotas = talleBotas;
    }

    public void setTalleQuepi(int talleQuepi) {
        this.talleQuepi = talleQuepi;
    }

    public void setpNombreComp(String pNombreComp) {
        this.pNombreComp = pNombreComp;
    }

    public void setpFechaNac(Date pFechaNac) {
        this.pFechaNac = pFechaNac;
    }

    public void setpDepartamentoNac(Departamento pDepartamentoNac) {
        this.pDepartamentoNac = pDepartamentoNac;
    }

    public void setpLocalidadNac(String pLocalidadNac) {
        this.pLocalidadNac = pLocalidadNac;
    }

    public void setpEstadoCivil(EstadoCivil pEstadoCivil) {
        this.pEstadoCivil = pEstadoCivil;
    }

    public void setpDomicilio(String pDomicilio) {
        this.pDomicilio = pDomicilio;
    }

    public void setpDepartamento(Departamento pDepartamento) {
        this.pDepartamento = pDepartamento;
    }

    public void setpLocalidad(String pLocalidad) {
        this.pLocalidad = pLocalidad;
    }

    public void setptelefono(String ptelefono) {
        this.ptelefono = ptelefono;
    }

    public void setpProfesion(String pProfesion) {
        this.pProfesion = pProfesion;
    }

    public void setpLugarTrabajo(String pLugarTrabajo) {
        this.pLugarTrabajo = pLugarTrabajo;
    }

    public void setmNombreComp(String mNombreComp) {
        this.mNombreComp = mNombreComp;
    }

    public void setmFechaNac(Date mFechaNac) {
        this.mFechaNac = mFechaNac;
    }

    public void setmDepartamentoNac(Departamento mDepartamentoNac) {
        this.mDepartamentoNac = mDepartamentoNac;
    }

    public void setmLocalidadNac(String mLocalidadNac) {
        this.mLocalidadNac = mLocalidadNac;
    }

    public void setmEstadoCivil(EstadoCivil mEstadoCivil) {
        this.mEstadoCivil = mEstadoCivil;
    }

    public void setmDomicilio(String mDomicilio) {
        this.mDomicilio = mDomicilio;
    }

    public void setmDepartamento(Departamento mDepartamento) {
        this.mDepartamento = mDepartamento;
    }

    public void setmLocalidad(String mLocalidad) {
        this.mLocalidad = mLocalidad;
    }

    public void setmtelefono(String mtelefono) {
        this.mtelefono = mtelefono;
    }

    public void setmProfesion(String mProfesion) {
        this.mProfesion = mProfesion;
    }

    public void setmLugarTrabajo(String mLugarTrabajo) {
        this.mLugarTrabajo = mLugarTrabajo;
    }

    public void setFoto(Blob foto) {
        this.foto = foto;
    }

    public void setPtelefono(String ptelefono) {
        this.ptelefono = ptelefono;
    }

    public void setMtelefono(String mtelefono) {
        this.mtelefono = mtelefono;
    }

    public void setFotoCIAnverso(Blob fotoCIAnverso) {
        this.fotoCIAnverso = fotoCIAnverso;
    }

    public void setFotoCIReverso(Blob fotoCIReverso) {
        this.fotoCIReverso = fotoCIReverso;
    }

    public void setFotoF69Hoja1(Blob fotoF69Hoja1) {
        this.fotoF69Hoja1 = fotoF69Hoja1;
    }

    public void setFotoF69Hoja2(Blob fotoF69Hoja2) {
        this.fotoF69Hoja2 = fotoF69Hoja2;
    }

    public void setFotoF69Hoja3(Blob fotoF69Hoja3) {
        this.fotoF69Hoja3 = fotoF69Hoja3;
    }

    public void setFotoF1Hoja1(Blob fotoF1Hoja1) {
        this.fotoF1Hoja1 = fotoF1Hoja1;
    }

    public void setFotoF1Hoja2(Blob fotoF1Hoja2) {
        this.fotoF1Hoja2 = fotoF1Hoja2;
    }

    
    
    
    public Carrera getCarrera() {
        return carrera;
    }
     
    public Blob getFoto() {
        return foto;
    }

    public Blob getFotoCIAnverso() {
        return fotoCIAnverso;
    }

    public Blob getFotoCIReverso() {
        return fotoCIReverso;
    }

    public Blob getFotoF69Hoja1() {
        return fotoF69Hoja1;
    }

    public Blob getFotoF69Hoja2() {
        return fotoF69Hoja2;
    }

    public Blob getFotoF69Hoja3() {
        return fotoF69Hoja3;
    }

    public Blob getFotoF1Hoja1() {
        return fotoF1Hoja1;
    }

    public Blob getFotoF1Hoja2() {
        return fotoF1Hoja2;
    }

    

    public int getId() {
        return id;
    }

    public int getCi() {
        return ci;
    }

    public Usuario getUnidadInsc() {
        return unidadInsc;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public String getSexo() {
        return sexo;
    }

    public Departamento getDepartamentoNac() {
        return departamentoNac;
    }

    public String getLocalidadNac() {
        return localidadNac;
    }

    public String getCc() {
        return cc;
    }

    public int getCcNro() {
        return ccNro;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    public boolean isQuinto() {
        return quinto;
    }

    public Orientacion getOrientacion() {
        return orientacion;
    }

    public boolean isLmga() {
        return lmga;
    }

    public boolean isBuenaConducta() {
        return buenaConducta;
    }

    public boolean isPs() {
        return ps;
    }

    public boolean isPsEjercito() {
        return psEjercito;
    }

    public int getHijos() {
        return hijos;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getpNombreComp() {
        return pNombreComp;
    }

    public Date getpFechaNac() {
        return pFechaNac;
    }

    public Departamento getpDepartamentoNac() {
        return pDepartamentoNac;
    }

    public String getpLocalidadNac() {
        return pLocalidadNac;
    }

    public EstadoCivil getpEstadoCivil() {
        return pEstadoCivil;
    }

    public String getpDomicilio() {
        return pDomicilio;
    }

    public Departamento getpDepartamento() {
        return pDepartamento;
    }

    public String getpLocalidad() {
        return pLocalidad;
    }

    public String getPtelefono() {
        return ptelefono;
    }

    public String getpProfesion() {
        return pProfesion;
    }

    public String getpLugarTrabajo() {
        return pLugarTrabajo;
    }

    public String getmNombreComp() {
        return mNombreComp;
    }

    public Date getmFechaNac() {
        return mFechaNac;
    }

    public Departamento getmDepartamentoNac() {
        return mDepartamentoNac;
    }

    public String getmLocalidadNac() {
        return mLocalidadNac;
    }

    public EstadoCivil getmEstadoCivil() {
        return mEstadoCivil;
    }

    public String getmDomicilio() {
        return mDomicilio;
    }

    public Departamento getmDepartamento() {
        return mDepartamento;
    }

    public String getmLocalidad() {
        return mLocalidad;
    }

    public String getMtelefono() {
        return mtelefono;
    }

    public String getmProfesion() {
        return mProfesion;
    }

    public String getmLugarTrabajo() {
        return mLugarTrabajo;
    }

    public boolean isPaseDirecto() {
        return paseDirecto;
    }

    public void setPaseDirecto(boolean paseDirecto) {
        this.paseDirecto = paseDirecto;
    }

    public double getNotaPaseDirecto() {
        return notaPaseDirecto;
    }

    public void setNotaPaseDirecto(double notaPaseDirecto) {
        this.notaPaseDirecto = notaPaseDirecto;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }
    
    public static int contarCaracteres(String cadena, char caracter) {
        int posicion, contador = 0;
        //se busca la primera vez que aparece
        posicion = cadena.indexOf(caracter);
        while (posicion != -1) { //mientras se encuentre el caracter
            contador++;           //se cuenta
            //se sigue buscando a partir de la posición siguiente a la encontrada
            posicion = cadena.indexOf(caracter, posicion + 1);
        }
        return contador;
}

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    
   public void imprimirPostulante(PrintWriter out){
       String impr="<table class=\"resultsTable\" style=\"margin-left: 0%; margin-right: 0%;word-wrap: break-word\">\n" +
"            <tr>\n" +
"                <td>\n" +
"                    <p align=\"left\">Escuela Militar</p>\n" +
"                </td>\n" +
"                <td>\n" +
"                     <p align=\"right\">Jefatura de Estudios</p>\n" +
"                </td>\n" +
"            </tr>\n" +
"            <tr>\n" +
"                <td colspan=\"2\">\n";
       
                        Blob b = foto ;
                        String src = "images/silueta.jpg" ;
                        try{
                            if (b!=null && (int)b.length()!= 0){ ;
                                byte[] by=b.getBytes(1,(int)b.length()); ;
                                String imgDataBase64=new String(Base64.getEncoder().encode(by));
                                src = "data:image/jpg;base64," + imgDataBase64;
                            }
                        }
                        catch(Exception ex){
                            
                        };

                    impr+="<p align=\"center\"><img src=\""+ src +"\" width=\"15%\"/>\n" +
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
"                            <td>ID: </td>\n" +
"                            <td>"+ id +"\n" +
"                                "+ carrera.getDescripcion() +"\n" +
"                            </td>\n" +
"\n" +
                            "                        <tr>\n" +
"                            <td>Primer nombre: </td>\n" +
"                            <td>"+ primerNombre +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Segundo nombre: </td>\n" +
"                            <td>"+ segundoNombre +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Primer apellido: </td>\n" +
"                            <td>"+ primerApellido +"</td>\n" +
"                        </tr>\n" +
                            
"                        <tr>\n" +
"                            <td>Segundo apellido: </td>\n" +
"                            <td>"+ segundoApellido +"</td>\n" +
"                        </tr>\n" +
"                        </tr>\n" +
                            "<tr>\n" +
"                            <td>Unidad Inscriptora: </td>\n" +
"                            <td>"+ unidadInsc.getNombreMostrar() +
"                            </td>\n" +
"                        </tr>"+
"                        <tr>\n" +
"                            <td>C.I.: </td>\n" +
"                            <td>"+ ci +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Fecha Alta: </td>\n" +
"                            <td>"+ ManejadorCodigoBD.fechaFormatoDDMMAAAA(fechaAlta) +"</td>\n" +
"                        </tr>\n" +

                            
"                        <tr>\n" +
"                            <td>Sexo: </td>\n" +
"                            <td>"+ sexo +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Fecha de Nacimiento: </td>\n" +
"                            <td>"+ ManejadorCodigoBD.fechaFormatoDDMMAAAA(fechaNac)+"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento de Nacimiento: </td>\n" +
"                            <td>"+ departamentoNac.getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Localidad de Nacimiento: </td>\n" +
"                            <td>"+ localidadNac +"</td>\n" +
"                        </tr>\n" +
"\n" +
"                        <tr>\n" +
"                            <td>Credencial: </td>\n" +
"                            <td>Serie: "+ cc +"\n" +
"                                N&deg;: "+ ccNro +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Estado Civil: </td>\n" +
"                            <td>"+ estadoCivil.getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Domicilio: </td>\n" +
"                            <td>"+ domicilio +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento: </td>\n" +
"                            <td>"+ departamento.getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Localidad: </td>\n" +
"                            <td>"+ localidad +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Tel&eacute;fono: </td>\n" +
"                            <td>"+ telefono +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Correo Electr&oacute;nico: </td>\n" +
"                            <td>"+ email +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Reingreso: </td>\n" +
"                            <td>";
                    if(reingreso){
                        impr+="SI";
                    }
                    else{
                        impr+="NO";
                    }
                    impr+="</td>\n" +
"                        <tr>\n" +
"                            <td>A cursar en el instituto: </td>\n" +
"                            <td>";
                    if(quinto){
                        impr+="Secundaria";
                    }
                    else{
                        impr+="6to. aprobado";
                    }
                    impr+="</td>\n" +
"\n" +
"                        </tr>\n" ;
                    if (quinto){
impr+="                  <tr>\n" +
"                            <td>Orientación: </td>\n" +
"                            <td>"+orientacion.getDescripcion()+"</td>\n" +
"                        </tr>\n" ;
                    }
impr+="                        <tr>\n" +
"                            <td>LMGA: </td>\n" +
"                            <td>";
                                if(lmga){
                                    impr+="SI";
                                }
                                else{
                                    impr+="NO";
                                } 
                                impr+="</td>\n" +
"                        </tr>\n" ;
                                if(lmga){
impr+="                        <tr>\n" +
"                            <td>PASE DIRECTO: </td>\n" +
"                            <td>";
                                if(paseDirecto){
                                    impr+="SI";
                                }
                                else{
                                    impr+="NO";
                                } 
                                impr+="</td>\n" +
"                        </tr>\n" ;
                                    if(paseDirecto){
impr+="                        <tr>\n" +
"                            <td>Nota Pase Directo: </td>\n" +
"                            <td>"+ notaPaseDirecto +"</td>\n" +
"                        </tr>\n" ;
                                    }
                                }
impr+="                        <tr>\n" +
"                            <td>Materias Pendientes: </td>\n" +
"                            <td><textarea readonly rows=" +(contarCaracteres(materiasPendientes, '\n')+1)+ " style=\"resize: none; overflow: hidden;\">"+ materiasPendientes +"</textarea></td>\n" +
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
                             if(ps){
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
                             if(ps){
                                 if(psEjercito){
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
"                            <td>"+ hijos +"</td>\n" +
"\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Necesita Alojamiento: </td>\n" +
"                            <td>";
                                if(alojamiento){
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
"                            <td style=\"size: 50\">"+ pNombreComp +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Fecha de Nacimiento: </td>\n" +
"                            <td>";
                             if(pFechaNac!=null){
                                 impr+=ManejadorCodigoBD.fechaFormatoDDMMAAAA(pFechaNac);
                             }
                             impr+="</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento de Nacimiento: </td>\n" +
"                            <td>"+ pDepartamentoNac.getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Localidad de Nacimiento: </td>\n" +
"                            <td>"+ pLocalidadNac +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Estado Civil: </td>\n" +
"                            <td>"+ pEstadoCivil.getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Domicilio: </td>\n" +
"                            <td>"+ pDomicilio +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento: </td>\n" +
"                            <td>"+ pDepartamento.getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"\n" +
"                        <tr>\n" +
"                            <td>Localidad: </td>\n" +
"                            <td>"+ pLocalidad +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Tel&eacute;fono: </td>\n" +
"                            <td>"+ ptelefono +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Profesi&oacute;n: </td>\n" +
"                            <td>"+ pProfesion +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Lugar de trabajo: </td>\n" +
"                            <td>"+ pLugarTrabajo +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>\n" +
"                                <b>Madre:</b>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Nombre Completo: </td>\n" +
"                            <td>"+ mNombreComp +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Fecha de Nacimiento: </td>\n" +
"                            <td>";
                             if(mFechaNac!=null){
                                 impr+=ManejadorCodigoBD.fechaFormatoDDMMAAAA(mFechaNac);;
                              };
                              impr+="</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento de Nacimiento: </td>\n" +
"                            <td>"+ mDepartamentoNac.getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Localidad de Nacimiento: </td>\n" +
"                            <td>"+ mLocalidadNac +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Estado Civil: </td>\n" +
"                            <td>"+ mEstadoCivil.getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Domicilio: </td>\n" +
"                            <td>"+ mDomicilio +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Departamento: </td>\n" +
"                            <td>"+ mDepartamento.getDescripcion() +"</td>\n" +
"                        </tr>\n" +
"\n" +
"                        <tr>\n" +
"                            <td>Localidad: </td>\n" +
"                            <td>"+ mLocalidad +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Tel&eacute;fono: </td>\n" +
"                            <td>"+ mtelefono +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Profesi&oacute;n: </td>\n" +
"                            <td>"+ mProfesion +"</td>\n" +
"                        </tr>\n" +
"                        <tr>\n" +
"                            <td>Lugar de trabajo: </td>\n" +
"                            <td>"+ mLugarTrabajo +"</td>\n" +
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
"                                <textarea readonly rows=" +(contarCaracteres(observaciones, '\n')+1)+ " style=\"resize: none\" cols=\"80\"  name=\"observaciones\">"+ observaciones +"</textarea>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                        \n" +
"                    </table>\n" +
"                    \n" +
"                </td>\n" +
"            </tr>\n" +
"        </table> <h1 style='page-break-after:always' > </h1>";
       out.println(impr);
   }
    
    
}
