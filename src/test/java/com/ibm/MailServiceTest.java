package com.ibm;

import com.ibm.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testSimpleMail() throws Exception{
        mailService.sendSimpleMail("wkk52586@163.com", "这是一封简单邮件","大家好，这是我的第一封邮件！");
    }

    @Test
    public void testHtmlMail() throws Exception{
        String content ="<html>\n" +
                "<body>\n" +
                "   <h3>hello world !这是一封html邮件!<h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("wkk52586@163.com","这是一封HTML邮件", content);
    }

    @Test
    public void sendAttachmentsMail(){
        String filePath = "/Users/tony/Documents/FullStackDeveloper_Curriculum.xlsx";
        mailService.sendAttachmentsMail("wkk52586@163.com", "主题：带附件的邮件","有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail(){
        String rscId = "wkk52586";
        String content="<html><body>这是有图片的邮件: <img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "/Users/tony/Documents/Metlife/Credit Card app/cc_payment_smartlink_flow.jpg";
        mailService.sendInlineResourceMail("wkk52586@163.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

    @Test
    public void sendTemplateMail(){
        Context context = new Context();
        context.setVariable("id", "006");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("wkk52586@163.com", "主题：这是模板邮件", emailContent);
    }
}
