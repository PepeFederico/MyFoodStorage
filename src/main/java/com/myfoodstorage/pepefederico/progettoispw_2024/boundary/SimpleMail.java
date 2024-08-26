package com.myfoodstorage.pepefederico.progettoispw_2024.boundary;

import com.myfoodstorage.pepefederico.progettoispw_2024.bean.OrdineBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleMail {
    private String myAccountEmail;
    private String password;
    private FileOrdine fileOrdine;

    public void sendMail(OrdineBean ordineBean) throws MessagingException {

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "*");

        try(InputStream input = new FileInputStream("risorseDB/credenzialiSendEmailMittente.properties")){
            Properties properties = new Properties();
            properties.load(input);

            myAccountEmail = properties.getProperty("MY_ACCOUNT_EMAIL");
            password = properties.getProperty("MY_PASS_EMAIL");

        } catch (IOException e) {
            throw new RejectedExecutionException(e);
        }

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, ordineBean.getContattoFornitore(), ordineBean);
        if (message != null) {
            Transport.send(message);
        }
    }

    public void eliminaFile(){
        fileOrdine.eliminaFile();
    }

    private Message prepareMessage(
            Session session,
            String myAccountEmail,
            String recepient,
            OrdineBean ordineBean){

        //creazione file Ordine
        fileOrdine = new FileOrdine();
        fileOrdine.creaFileOrdine(ordineBean);

        try {
            //creazione del messaggio
            Message message = new MimeMessage(session);
            //setto chi è il mittente del messaggio
            message.setFrom(new InternetAddress(myAccountEmail));
            //setto il destinatario del messaggio
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            //oggetto del messaggio
            message.setSubject("Ordine dei Prodotti");

            MimeMultipart multipart = getMimeMultipart();
            message.setContent(multipart);

            return message;
        } catch (Exception e) {
            Logger.getLogger(SimpleMail.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    private MimeMultipart getMimeMultipart() throws IOException, MessagingException {
        MimeMultipart multipart = new MimeMultipart();

        //-------setting di un allegato---------//
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(new File("ordini/ordine.txt"));
        //------------------------------------//

        //-------setting del testo------------//
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("<h1>Ordine dei prodotti</h1>", "text/html");
        messageBodyPart.setContent("Hai ricevuto un ordine per una fornitura!! Apri MyFoodStorage per confermare o rifiutare l'ordine ricevuto.\n", "text/html");
        //------------------------------------//

        //--------costruzione del messaggio completo--------//
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(attachment);
        return multipart;
    }
}
