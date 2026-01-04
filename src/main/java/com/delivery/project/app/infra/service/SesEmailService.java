package com.delivery.project.app.infra.service;

import com.delivery.project.app.core.email.SesEmailConfiguration;
import com.delivery.project.app.domain.service.TransactionalEmailService;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import jakarta.mail.Message;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class SesEmailService implements TransactionalEmailService {
    @Autowired
    private SesEmailConfiguration sesEmailConfiguration;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Configuration freemarkerConfiguration;
    @Override
    public void enviarEmail(Menssagem menssagem) {
        try {
            MimeMessageHelper mimeMessage = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            mimeMessage.setFrom(sesEmailConfiguration.getRemetente());
            mimeMessage.setTo(menssagem.getTo());
            mimeMessage.setSubject(menssagem.getSubject());
            mimeMessage.setText(getBodyEmail(menssagem), true);
            javaMailSender.send(mimeMessage.getMimeMessage());
        } catch (Exception e) {
            throw new RuntimeException("nao foi possivel enviar o email" + e.getMessage() + e.getCause());
        }
    }

    private String getBodyEmail(Menssagem menssagem) {
        try {
            var body = freemarkerConfiguration.getTemplate(menssagem.getTemplate());
            return FreeMarkerTemplateUtils.processTemplateIntoString(body, menssagem.getTemplateData());
        }catch (IOException e){
            throw new RuntimeException("nao foi possivel abrir o arquivo" + e.getMessage() + e.getCause());
        } catch (TemplateException e) {
            throw new RuntimeException("nao foi possivel processar o template" + e.getMessage() + e.getCause());
        }
    }
}
