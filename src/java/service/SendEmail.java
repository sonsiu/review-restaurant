
package service;

import consts.IConstants;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    
    public boolean sendEmail(String toEmail, String verifyCode){
        boolean test = false;
        
        
        final String fromEmail = IConstants.CONTACT_EMAIL;
        final String password = IConstants.CONTACT_EMAIL_PASS;
        
        
        try {
            Properties p = new Properties();
            p.setProperty("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.auth", "true");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.socketFactory.port", "587");
            p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
            Session session = Session.getInstance(p, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            
            Message mess = new MimeMessage(session);
            
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        
            mess.setSubject("User Email Verification");
            
            mess.setText("Registered successfully. Please verify your account using this code: " + verifyCode);
        
            Transport.send(mess);
            
            test = true;
        } catch (Exception e) {
            
        }
        return test;
    }
    
    public boolean sendEmailAfterChangeUserStatus(String toEmail, String userID,String title, String content){
        boolean test = false;
        
        
        final String fromEmail = IConstants.CONTACT_EMAIL;
        final String password = IConstants.CONTACT_EMAIL_PASS;
        
        
        try {
            Properties p = new Properties();
            p.setProperty("mail.smtp.host", "smtp.gmail.com");
            p.setProperty("mail.smtp.port", "587");
            p.setProperty("mail.smtp.auth", "true");
            p.setProperty("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.socketFactory.port", "587");
            p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
            Session session = Session.getInstance(p, new Authenticator(){
                @Override
                protected PasswordAuthentication getPasswordAuthentication(){
                    return new PasswordAuthentication(fromEmail, password);
                }
            });
            
            Message mess = new MimeMessage(session);
            
            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        
            mess.setSubject(title);
            
            mess.setText(content);
        
            Transport.send(mess);
            
            test = true;
        } catch (Exception e) {
            
        }
        return test;
    }
}
