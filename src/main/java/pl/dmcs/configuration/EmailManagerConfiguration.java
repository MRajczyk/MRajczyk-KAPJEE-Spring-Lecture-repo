package pl.dmcs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static pl.dmcs.service.tempEnvironmentVars.GMAIL_SMTP_KEY;
import static pl.dmcs.service.tempEnvironmentVars.EMAIL_ADDRESS;

@Configuration
public class EmailManagerConfiguration {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(465); //SSL config

        mailSender.setUsername(EMAIL_ADDRESS);
        mailSender.setPassword(GMAIL_SMTP_KEY);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        return mailSender;
    }
}


