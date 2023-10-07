package com.example.fingryd.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;
@Component
public class MyBeanClass {

    @Value(value = "${email}")
    private static String email;

    @Value(value = "${password}")
    private static String setPassword;

    @Value(value = "${host}")
    private static String host;

    @Value(value = "${protocol}")
    private static String protocol;

    @Value(value = "${port}")
    private static int port;

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
    public static String getEmail() {
        return email;
    }
    public static String getSetPassword() {
        return setPassword;
    }
    public static String getHost() {
        return host;
    }
    public static String getProtocol() {
        return protocol;
    }
    public static int getPort() {
        return port;
    }
}
