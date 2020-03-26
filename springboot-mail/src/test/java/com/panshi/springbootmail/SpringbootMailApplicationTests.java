package com.panshi.springbootmail;

import com.panshi.springbootmail.bean.MailBean;
import com.panshi.springbootmail.util.MailUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SpringbootMailApplicationTests {
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private JavaMailSender javaMailSender;

    //接收人
    private static final String RECIPINET = "193772282@qq.com";

    @Test
    void contextLoads() {
    }

    @Test
    public void testSend() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("18255599540@163.com");  // From需要和配置文件中的username一致，否则会报错。
        message.setTo("193772282@qq.com");  // To为邮件接收者；
        message.setSubject("这是标题");     // Subject为邮件标题
        message.setText("这是内容!!!");    // Text 为邮件内容
        javaMailSender.send(message);
    }

    // 发送html邮件
    @Test
    public void testSend2() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
        messageHelper.setFrom("18255599540@163.com");
        messageHelper.setTo("193772282@qq.com");
        messageHelper.setSubject("标题");
        messageHelper.setText("<h1>标题</h1><br/><p>这是内容</p>", true);
        javaMailSender.send(messageHelper.getMimeMessage());
    }


    @Test
    public void test3() {
        MailBean mailBean = new MailBean();
        mailBean.setRecipient("193772282@qq.com");
        mailBean.setSubject("SpringBootMail之这是一封文本的邮件");
        mailBean.setContent("SpringBootMail发送一个简单格式的邮件，时间为：" + LocalDateTime.now());

        mailUtil.sendSimpleMail(mailBean);
    }

    @Test
    public void sendHTMLMail() {
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMailHTML之这是一封HTML格式的邮件");
        StringBuilder sb = new StringBuilder();
        sb.append("<h2>SpirngBoot测试邮件HTML</h2>")
                .append("<p style='text-align:left'>这是一封HTML邮件...</p>")
                .append("<p> 时间为："+LocalDateTime.now() +"</p>");
        mailBean.setContent(sb.toString());

        mailUtil.sendHTMLMail(mailBean);
    }

    // 发送附件
    @Test
    public void sendAttachmentMail() {
        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMail之这是一封有附件格式的邮件");
        mailBean.setContent("SpringBootMail发送一封有附件格式的邮件，时间为："+ LocalDateTime.now());

        mailUtil.sendAttachmentMail(mailBean);
    }

    @Test
    public void sendInlineMail() {
        MailBean mailBean = new MailBean();
        //id,目前写死了，可根据需要封装
        String rscId = "picture";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("SpringBootMail之这是一封有静态资源格式的邮件");
        mailBean.setContent(content);

        mailUtil.sendInlineMail(mailBean);
    }


    @Autowired
    private TemplateEngine templateEngine;


    @Test
    public void sendTemplate2Mail() {
        //注意：Context 类是在org.thymeleaf.context.Context包下的。
        Context context = new Context();
        //html中填充动态属性值
        context.setVariable("username", "码农用户");
        context.setVariable("url", "https://www.aliyun.com/?utm_content=se_1000301881");
        //注意：process第一个参数名称要和templates下的模板名称一致。要不然会报错
        //org.thymeleaf.exceptions.TemplateInputException: Error resolving template [email]
        String emailContent = templateEngine.process("email", context);

        MailBean mailBean = new MailBean();
        mailBean.setRecipient(RECIPINET);
        mailBean.setSubject("主题：这是模板邮件");
        mailBean.setContent(emailContent);

        mailUtil.sendHTMLMail(mailBean);
    }
}
