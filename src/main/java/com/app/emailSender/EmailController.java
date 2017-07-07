/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.emailSender;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ishanka Ranatunga
 */
@Controller
public class EmailController {
    @Autowired
    private JavaMailSender sender;
    
    private String toMail;
    private String body;
    private String Subject;
    @RequestMapping("/api/emailSender")
    @ResponseBody
    String home() {
        try {
            sendEmail();
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: " +ex;
        }
}
    private void sendEmail() throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setTo(getToMail()); //change email
        helper.setText(getBody());
        helper.setSubject(getSubject());
        
        sender.send(message);
    }

    public String getToMail() {
        return toMail;
    }

    public String getBody() {
        return body;
    }

    public String getSubject() {
        return Subject;
    }

    public void setToMail(String toMail) {
        this.toMail = toMail;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }
}