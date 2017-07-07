/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service;

import com.app.enties.Customer;
import com.app.enties.Users;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 *
 * @author dilsh
 */
@Service
public class EmailService {
    @Autowired
    private JavaMailSender sender;
 
    public String prepareMailRegister(Users user,Customer cust){
        String body="Dear "+cust.getFirstName()+" "+cust.getLastName()+",\n\n"
                +"Welcome aboard!\n"
                +"Thank you for choosing Adaala Na as your online banking partner."
                + "Your Account "+user.getUsername()+" awaits you. Please visit our website to access your account.\n"
                +" Our main mission is to live up to the trust you have given us and ensure all your banking needs"
                +" 24x7 365 availability with the best sequrity available\n\n"
                +"Thanks & Best Regards\n"
                +"@TeamNoNameYet";  
        return body;
    }
    public String prepareMailChangedPsssword(Users user,Customer cust){
        String body="Dear "+cust.getFirstName()+" "+cust.getLastName()+",\n\n"
                +"This is to notify you that the password for your user account "+user.getUsername()+ "was changed.\n"
                +"If you are not aware about such change please contact us immediately on Bank.TeamNoNameYet@gmail.com as soon as possible"
                +"Thanks & Best Regards\n"
                +"@TeamNoNameYet";  
        return body;
    }
    
    public void sendMails(String to,String subject,String body) throws MessagingException,MailException{
        try{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setTo(to);
        helper.setText(body);
        helper.setSubject(subject);
        
        sender.send(message);
        }catch(MailException | MessagingException me){
            throw me;
        }
    }
    
    
  
}
