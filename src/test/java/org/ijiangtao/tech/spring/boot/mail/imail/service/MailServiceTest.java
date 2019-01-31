package org.ijiangtao.tech.spring.boot.mail.imail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import static org.junit.Assert.*;

/**
 * Created by admxj on 2018/10/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Resource
    private TemplateEngine templateEngine;

    @Test
    public void sendSimpleMail() {
        mailService.sendSimpleMail("ispringboot@163.com","测试spring boot imail-主题","测试spring boot imail - 内容");
    }

    @Test
    public void sendHtmlMail() throws MessagingException {

        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<body>\n" +
                "</html>\n";

        mailService.sendHtmlMail("ispringboot@163.com","这是一封HTML邮件",content);
    }

    @Test
    public void sendAttachmentsMail() throws MessagingException {
        String filePath = "/Users/admxj/Documents/软件开发前景.docx";
        String content = "<html>\n" +
                "<body>\n" +
                "<h3>hello world</h3>\n" +
                "<h1>html</h1>\n" +
                "<h1>附件传输</h1>\n" +
                "<body>\n" +
                "</html>\n";
        mailService.sendAttachmentsMail("ispringboot@163.com","这是一封HTML邮件",content, filePath);
    }

    @Test
    public void sendInlinkResourceMail() throws MessagingException {
        //TODO 改为本地图片目录
        String imgPath = "/ijiangtao/img/blob/dd9899b4cf95cbf074ddc4607007046c022564cb/blog/animal/dog/dog-at-work-with-computer-2.jpg?raw=true";
        String rscId = "admxj001";
        String content = "<html>" +
                "<body>" +
                "<h3>hello world</h3>" +
                "<h1>html</h1>" +
                "<h1>图片邮件</h1>" +
                "<img src='cid:"+rscId+"'></img>" +
                "<body>" +
                "</html>";

        mailService.sendInlinkResourceMail("ispringboot@163.com","这是一封图片邮件",content, imgPath, rscId);
    }

    @Test
    public void testTemplateMailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("id","ispringboot");

        String emailContent = templateEngine.process("emailTeplate", context);
        mailService.sendHtmlMail("ispringboot@163.com","这是一封HTML模板邮件",emailContent);

    }
}
