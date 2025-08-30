package com.beiramar.beiramar.api.service;

import com.beiramar.beiramar.api.entity.LogSenhaEntity;
import com.beiramar.beiramar.api.entity.StatusLogSenha;
import com.beiramar.beiramar.api.repository.LogSenhaRepository;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailService {
    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;

    @Autowired
    private LogSenhaRepository logSenhaRepository;

    public void enviarCodigo(String email, String codigo, LogSenhaEntity log){
        Email from = new Email("beiramar.estetica@gmail.com");
        String subject = "Código de recuperação se senha";
        Email to = new Email(email);
        String htmlContent = """
<html>
  <body style="font-family: Arial, sans-serif; text-align: center; padding: 20px;">
    <h2 style="color: #333;">Recuperação de Senha</h2>
    <p style="font-size: 16px;">Olá,</p>
    <p style="font-size: 16px;">Seu código de recuperação é:</p>
    <div style="font-size: 32px; font-weight: bold; color: #007BFF; margin: 20px 0;">
      """ + codigo + """
    </div>
    <p style="font-size: 14px; color: #777;">Se você não solicitou isso, ignore esta mensagem.</p>
  </body>
</html>
""";

        Content content = new Content("text/html", htmlContent);
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
            log.setStatus(StatusLogSenha.COD_ENVIADO);
            logSenhaRepository.save(log);
        }catch(IOException ex){
            log.setStatus(StatusLogSenha.COD_ERRO);
            logSenhaRepository.save(log);
            throw new RuntimeException("Erro ao enviar e-mail", ex);
        }

    }
    public void enviarAutenticacao(String email){
        Email from = new Email("beiramar.estetica@gmail.com");
        String subject = "Código de recuperação usado";
        Email to = new Email(email);

        Content content = new Content("text/plain", "Código autenticado com sucesso!");
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try{
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        }catch(IOException ex){
            throw new RuntimeException("Erro ao enviar e-mail", ex);
        }

    }




}

