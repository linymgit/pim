package com.lym.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
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


    static Logger log = LoggerFactory.getLogger(MailUtil.class);

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

    public boolean sendEmail(String to, String subject, String content) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(mail163username));

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(mail163username));


            // 邮件主题
            message.setSubject(subject);
            message.setContent(content, "text/html;charset=utf-8");

            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            log.error("fail to send mail because : \r\n"+e.getMessage());
            return false;
        }
    }
}
