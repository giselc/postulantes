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
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
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
