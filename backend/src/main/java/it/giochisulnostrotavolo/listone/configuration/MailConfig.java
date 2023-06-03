// package it.giochisulnostrotavolo.listone.configuration;

// import java.security.GeneralSecurityException;
// import java.util.Properties;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.JavaMailSenderImpl;

// import com.sun.mail.util.MailSSLSocketFactory;

// @Configuration
// public class MailConfig {

//     @Value("${spring.mail.host}")
//     private String host;

//     @Value("${spring.mail.port}")
//     private Integer port;

//     @Value("${spring.mail.protocol}")
//     private String protocol;

//     @Value("${spring.mail.username}")
//     private String user;

//     @Value("${spring.mail.password}")
//     private String password;

//     @Bean
//     public JavaMailSender mailSender() throws GeneralSecurityException{
//         JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

//         Properties props = new Properties();
//         props.put("mail.smtp.auth", true);
//         props.put("mail.smtp.ssl.enable", true);
//         props.put("mail.smtp.timeout", 2000);
        
//         MailSSLSocketFactory sf = new MailSSLSocketFactory();
//         sf.setTrustAllHosts(true); 
//         props.put("mail.smtp.ssl.trust", "*");
//         props.put("mail.smtp.ssl.socketFactory", sf);
        
//         mailSender.setJavaMailProperties(props);
        
//         mailSender.setHost(host);
//         mailSender.setPort(port);
//         mailSender.setUsername(user);
//         mailSender.setPassword(password);
//         mailSender.setProtocol(protocol);

//         return mailSender;
//     }

// }