package darkchop.javamail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by zan on 04/11/2016.
 */

public class JavaMailGmail {

    /*
    ESTO ES PARA DARLE AUTORIZACION A LA APP EN GMAIL

    Cambiar el acceso a la cuenta para las aplicaciones menos seguras

    Cambia la configuración para permitir que las aplicaciones menos seguras accedan a tu cuenta. Esta opción no es recomendable porque podría facilitar el acceso a tu cuenta a otra persona. Si quieres permitirlo de todas formas, sigue estos pasos:
       1.En "Mi cuenta", ve a la sección Aplicaciones menos seguras (https://www.google.com/settings/security/lesssecureapps)
       2.Junto a "Acceso de aplicaciones menos seguras", selecciona Activar. Si eres usuario de Google Apps, ten en cuenta que esta opción puede estar oculta si tu administrador ha bloqueado el acceso de las aplicaciones menos seguras a la cuenta.

    * */

    final String username = "tu.correo@gmail.com";
    final String password = "1234";
    final String destinatario = "destinatario@hotmail.com";
    final String asunto = "Prueba Java Mail";
    final String textoMensaje = "Este es un mensaje, \nEstamos aprendiendo Java Mail. \nEnvio desde Gmail";

    public JavaMailGmail() {

        Properties props = new Properties();

        // Nombre del host de correo
        props.put("mail.smtp.host", "smtp.gmail.com");

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
