package com.woniumall.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {
    public static void sendMail(String email) {
        try {
            final Properties props = new Properties();
            props.put("mail.user","2424590457@qq.com");
            props.put("mail.password","xtjdlsgzunivebhf"); //授权码
            props.put("mail.smtp.auth","true");
            props.put("mail.transport.protocal","smtp");
            props.put("mail.smtp.host","smtp.qq.com");
            props.put("mail.smtp.prot","25");

            Session mailSession = Session.getDefaultInstance(props);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress("2424590457@qq.com"));
            msg.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject("用户注册激活邮件-蜗牛学院");
            msg.setContent("<h2>此邮件为蜗牛学院电商平台激活邮件！请点击下面链接完成激活操作！</h2><br>"+
                    "此邮件30分钟有效！"+"<h5><a href='http://localhost:80/maven-woniumall/front/user?opr=active"+
                    "&email="+email+"'>http://localhost:80/maven-woniumall/front/user?opr=active&email="+email+
                    "</a></h5>","text/html;charset=UTF-8");

            msg.saveChanges();

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(props.getProperty("mail.smtp.host"),props.getProperty("mail.user"),props.getProperty("mail.password"));
            transport.sendMessage(msg,msg.getAllRecipients());
            transport.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

