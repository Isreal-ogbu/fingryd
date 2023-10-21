package com.example.fingryd.utils;

import com.example.fingryd.utils.findrydMail.FingrydMail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;
@Component
public class MyBeanClass {

    @Value(value = "${email}")
    private String email;

    @Value(value = "${password}")
    private String setPassword;

    @Value(value = "${host}")
    private String host;

    @Value(value = "${protocol}")
    private String protocol;

    @Value(value = "${port}")
    private int port;

    @Bean
    public ReportUtil getReportService(){
        return new ReportUtil();
    }


    @Bean
    public JavaMailSender getSenderMail(){

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(getHost());
        javaMailSender.setPort(getPort());
        javaMailSender.setUsername(getEmail());
        javaMailSender.setPassword(getSetPassword());
        javaMailSender.setProtocol(getProtocol());
        Properties properties = javaMailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", true);

        return javaMailSender;
    }
    public String getEmail() {
        return email;
    }
    public String getSetPassword() {
        return setPassword;
    }
    public String getHost() {
        return host;
    }
    public String getProtocol() {
        return protocol;
    }
    public int getPort() {
        return port;
    }
}
