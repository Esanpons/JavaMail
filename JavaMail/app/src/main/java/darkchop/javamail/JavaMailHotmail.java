package darkchop.javamail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailHotmail {

    final String username = "tu.correo@hotmail.com";
    final String password = "123456";
    final String destinatario = "destinatario@gmail.com";
    final String asunto = "Prueba Java Mail";
    final String textoMensaje = "Este es un mensaje, \nEstamos aprendiendo Java Mail. \nEnvio desde Hotmail";


    public JavaMailHotmail() {

        Properties props = new Properties();

        props.put("mail.transport.protocol", "smtp");

        // Nombre del host de correo
        props.put("mail.smtp.host", "smtp.live.com");

        //estos no se que son pero son necesarios
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");

        // TLS si está disponible
        props.put("mail.smtp.starttls.enable", "true");

        // Si requiere o no usuario y password para conectarse.
        props.put("mail.smtp.auth", "true");

        // Puerto  para envio de correos
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        /** activar debub */
        session.setDebug(true);
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); //Remitente

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario)); //Destinatario
            message.setSubject(asunto);//Assunto
            message.setText(textoMensaje);

            //Enviamos el mensaje
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
