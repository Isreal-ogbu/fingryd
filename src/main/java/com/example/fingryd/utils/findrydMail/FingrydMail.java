package com.example.fingryd.utils.findrydMail;

import com.example.fingryd.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class FingrydMail {
    private static EmailService emailService;
    @Autowired
    public FingrydMail(EmailService emailService){
        this.emailService = emailService;
    }

    public static void welcomeEmail(String email, String name){
        String subject = "FINGRYD WELCOME MESSAGE (NEW CUSTOMER)";
        String body = String.format("Dear %s, Welcome to Fingryd Bank. We are glad to have you choose us. " +
                "We hope you enjoy our future services. Kindly call 0810fingryd for Enquires. Again Welcome", name);
        emailService.sendSimpleEmail(email,subject,body);
    }
    public static void successfulTransferEmail(String email, String amount, String balance, String to){

    }
    public static void purchaseEmail(String email){}
    public static void unSuccessfulTransferEmail(String email){}
    public static void creditAlertEmail(String email){}
    public static void debitAlertEmail(String email){}
    public static void balanceEmail(String email){}
    public static void randomFingrydEmail(String email){}
}
