package darkchop.javamail;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by zan on 04/11/2016.
 */

public class JavaMailGamilConAdjunto {


    Context context;
    final String nameAdjunto = "imagen.jpg";

    final String username = "amigoinvisible.darkchop@gmail.com";
    final String password = "amigoinvisible1234";
    final String destinatario = "pruebas.darkchop@hotmail.com";
    final String asunto = "Prueba Java Mail";
    final String textoMensaje = "Este es un mensaje, \nEstamos aprendiendo Java Mail.\n Envio desde Gmail con archivo adjunto";
    final String ruta = "/data/data/darkchop.javamail/files/" + nameAdjunto;


    public JavaMailGamilConAdjunto(Context context) {
        this.context = context;

        guardar();

        EnviarCorreo();

        File f = new File(ruta);
        f.delete();



    }


    public void guardar() {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_main, null);

        Drawable myDrawable = view.getResources().getDrawable(R.drawable.foto);
        Bitmap bitmap = ((BitmapDrawable) myDrawable).getBitmap();


        try {
            FileOutputStream out = context.openFileOutput(nameAdjunto, Context.MODE_PRIVATE);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);


            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void EnviarCorreo() {

        Properties props = new Properties();

        // Nombre del host de correo, es smtp.gmail.com
        props.put("mail.smtp.host", "smtp.gmail.com");

        // TLS si está disponible
        props.setProperty("mail.smtp.starttls.enable", "true");

        // Puerto de gmail para envio de correos
        props.setProperty("mail.smtp.port", "587");

        // Nombre del usuario
        props.setProperty("mail.smtp.user", username);

        // Si requiere o no usuario y password para conectarse.
        props.setProperty("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);


        // Quien envia el correo
        try {

            BodyPart texto = new MimeBodyPart();
            texto.setText(textoMensaje);

            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(ruta)));
            adjunto.setFileName(nameAdjunto);

            MimeMultipart multiParte = new MimeMultipart();

            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);


            message.setFrom(new InternetAddress(username));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            //asunto y texto
            message.setSubject(asunto);
            message.setContent(multiParte);

            //Enviamos el mensaje
            Transport t = session.getTransport("smtp");
            t.connect(username, password);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


}
