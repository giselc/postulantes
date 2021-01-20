/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.sun.mail.util.MailSSLSocketFactory;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Gisel
 */
public class EnviarMail extends Thread{
    private String to;
    private boolean f1;
    private boolean f69;
    private Long id;
    private String mailUsuario;
    public EnviarMail(String to, boolean f1, boolean f69,Long id, String mailUsuario) {
        this.to = to;
        this.f1 = f1;
        this.id = id;
        this.f69 = f69;
        this.mailUsuario = mailUsuario;
    }
    @Override
    public void run() {
        Properties props = new Properties();
        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.socketFactory", sf);
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.office365.com");
            props.put("mail.smtp.port", "587");
            String Username = "inscripciones@escuelamilitar.edu.uy";
            String PassWord= "EM2017++";
            Session session = Session.getInstance(props,new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, PassWord);
                    }
                });
 //           BodyPart adjunto = new MimeBodyPart();
//adjunto.setDataHandler(new DataHandler(new FileDataSource("d:/futbol.gif")));
//adjunto.setFileName("futbol.gif");
            String Subject = "Inscripción a Escuela Militar";
            String Mensage = "";
            if(this.id==null){
                if(!this.f69 && !this.f1){
                  Mensage = "Su inscripción ha sido procesada en forma condicional. \nPara que su inscripción sea completada debe de presentar la Fórmula 69 y la Fórmula Nro 1 (Solicitud de ingreso).";
                }
                else{
                    if(!this.f69){
                       Mensage = "Su inscripción ha sido procesada en forma condicional. \nPara que su inscripción sea completada debe de presentar la Fórmula 69."; 
                    }
                    else{
                        Mensage = "Su inscripción ha sido procesada en forma condicional. \nPara que su inscripción sea completada debe de presentar la Fórmula Nro 1 (Solicitud de ingreso).";
                    }
                }
                
            }
            else{
                Mensage = "Su inscripción se ha realizado con éxito. \nNúmero de postulante: "+ String.valueOf(id);
            }
//            
//            Mensage = Mensage+"\n"+"Estimado postulante, \n" +
//"A continuación detallamos la documentación que deberá presentar para las pruebas de ingreso:\n" +
//"\n" +
//"- Cédula de identidad vigente.\n" +
//"\n" +
//"- Credencial cívica.\n" +
//"\n" +
//"- Formulario 1A completo (menores de 18 años) o formulario 1B completo (mayores de 18 años).\n" +
//"\n" +
//"- Formula 69 o escolaridad que acredite los estudios cursados.\n" +
//"\n" +
//"- Constancia de haber iniciado tramite de certificado policial de buena conducta (tirilla).\n" +
//"\n" +
//"- Carné de salud básico con copia de resultados de los análisis clínicos del mismo y examen de VIH, con fecha de realizados posterior al 1° de octubre del 2020. Para las postulantes mujeres, Beta HCG en orina (examen de embarazo) con menos de 1 mes de realizado a la fecha del examen médico de ingreso. Para las postulantes mayores de 21 años, PAP con menos de 1 año de realizado.\n" +
//"\n" +
//"- Partida de nacimiento.\n" +
//"\n" +
//"- Certificado de Juramento de Fidelidad a la Bandera.\n" +
//"\n" +
//"- Carné de vacunas vigente.\n" +
//"\n" +
//"- 3 fotos carné (a color y de frente).\n" +
//"\n" +
//"Recuerde que deberá presentar toda la documentación original y fotocopias en un sobre manila. Así mismo recordamos que se debe presentar de vestimenta formal (camisa, chaqueta, pantalón de vestir o pollera por debajo de la rodilla, y zapatos de vestir). Para las pruebas físicas debe concurrir con short negro, remera blanca, gorro de sol discreto (opcional), calzado deportivo; para las pruebas de natación en el caso de los postulantes masculinos short de baño, y las postulantes femeninas malla entera y gorra de baño.  Los postulantes que se alojen en el instituto deberán traer además ropa de cama y artículos de higiene personal."
//                    + "/n"+ "Calendario de exámenes de ingreso: http://www.escuelamilitar.edu.uy/calendario.html";
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(this.to));
            message.setSubject(Subject);
            message.setText(Mensage);
            Transport.send(message);
            if(!this.mailUsuario.equals("")){
                message = new MimeMessage(session);
                message.setFrom(new InternetAddress(Username));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(this.mailUsuario));
                message.setSubject(Subject);
                message.setText("El siguiente mail fue enviado a: "+this.to+"\n"+Mensage);
                Transport.send(message);
            }
        } catch (Exception ex) {
            Logger.getLogger(EnviarMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
