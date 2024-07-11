package com.example.fingryd.utils.findrydMail;

import com.example.fingryd.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class FingrydMail {

//    @Autowired
    private final EmailUtil emailService;

    public FingrydMail(EmailUtil emailService){
        this.emailService = emailService;
    }

    public void welcomeEmail(String email, String name){
        String subject = "FINGRYD WELCOME MESSAGE (NEW CUSTOMER)";
        String body = String.format("Dear %s, Welcome to Fingryd Bank. \n\nWe are glad to have you choose us. \n\n" +
                "We hope you enjoy our future services. \n\nKindly call 0810fingryd for Enquires. Again, You are highly Welcome", name);
        emailService.sendSimpleEmail(email,subject,body);
    }
    public void successfulTransferEmail(String email, String name, String amount, String desc, String balance, String to){
        String subject = "Transaction Successful";
        String body;
        if (to != null) {
            body = String.format("Dear %s, your transaction was successful.\n\n" +
                    "Amount: %s \n\nTo: %s \n\nDESC: %s \n\nBalance: %s.\n\n" +
                    "Thanks for banking with us. \n\nKindly call 0810fingryd for Enquires. Again Welcome", name, amount, to, desc, balance);
        } else{
            body = String.format("Dear %s, your transaction was successful.\n\n" +
                    "Amount: %s \n\nDESC: %s \n\nBalance: %s.\n\n" +
                    "Thanks for banking with us.", name, amount, desc, balance);
        }
        emailService.sendSimpleEmail(email, subject, body);
    }
    public void purchaseEmail(String email, String name, String amount, String desc){
        String subject = "Transaction Successful (Item Purchased)";
        String body = String.format("Dear %s, your transaction was successful.\n\n" +
                "Amount: %s \n\nDESC: %s\n\n" +
                "Thanks for using our service with us. \n\nKindly call 0810fingryd for Enquires. Again Welcome",name, amount, desc);
        emailService.sendSimpleEmail(email, subject, body);
        return;
    }
    public void unSuccessfulTransferEmail(String email, String name, String amount){
        String subject = "Transaction Unsuccessful";
        String body = String.format("Dear %s, your transaction was unsuccessful.\n\n" +
                "You tried to make a transaction of : %s  which failed.\n\n" +
                "Our team is currently trying all effort on it. Please be patient and try again later.\n\n"+
                "Thanks for using our service with us. \n\nKindly call 0810fingryd for Enquires. Again Welcome",name,amount);
        emailService.sendSimpleEmail(email, subject, body);
        return;
    }
    public void creditAlertEmail(String email, String amount, String name, String desc){
        String subject = "Transaction Successful (Credit Alert)";
        String body = String.format("Dear %s, your transaction was successful.\n\n" +
                "Amount: %s \n\nDESC: %s\n\n" +
                "Thanks for using our service with us. \n\nKindly call 0810fingryd for Enquires. Again Welcome",name, amount, desc);
        emailService.sendSimpleEmail(email, subject, body);
        return;
    }
    public void debitAlertEmail(String email, String name, String amount, String desc){
        String subject = "Transaction Successful (Debit Alert)";
        String body = String.format("Dear %s, your transaction was successful.\n\n" +
                "Amount: %s \n\nDESC: %s\n\n" +
                "Thanks for using our service with us. \n\nKindly call 0810fingryd for Enquires. Again Welcome",name, amount, desc);
        emailService.sendSimpleEmail(email, subject, body);
    }
    public void balanceEmail(String email, String name, String amount, String desc){
        String subject = "Balance Enquiry ";
        String body = String.format("Dear %s, \n\n" +
                "Your available balance is now : %s\n\n" +
                "Thanks for using our service with us. \n\nKindly call 0810fingryd for Enquires. Again Welcome",name, amount, desc);
        emailService.sendSimpleEmail(email, subject, body);
    }
}
