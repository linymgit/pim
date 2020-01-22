package com.lym.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Date 2020/1/22
 * @auth linyimin
 * @Desc 邮件工具类
 **/
@Component
public class MailUtil {

    @Value("${mail163username}")
    private String mail163username;

    @Value("${mail163passwd}")
    private String mail163passwd;

    private Session session;

    public MailUtil() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail163username, mail163passwd);
            }
        });
    }

    public void sendEmail(String to, String subject, String content) throws AddressException, MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(mail163username));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setRecipient(Message.RecipientType.CC, new InternetAddress(mail163username));

        // 邮件主题
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=utf-8");

        Transport.send(message);
    }
}
