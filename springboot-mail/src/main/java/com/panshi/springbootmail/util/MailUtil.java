package com.panshi.springbootmail.util;

import com.panshi.springbootmail.bean.MailBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @ClassName MailUtil
 * @Description
 * @Author guolongfei
 * @Date 2020/3/26  10:42
 * @Version
 */
@Component
public class MailUtil {

    @Value("${spring.mail.username}")
    private String MAIL_SENDER; // 邮件发送者

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private JavaMailSender javaMailSender;

    //普通邮件
    public void sendSimpleMail(MailBean mailBean) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(MAIL_SENDER);
            mailMessage.setTo(mailBean.getRecipient());
            mailMessage.setSubject(mailBean.getSubject());
            mailMessage.setText(mailBean.getContent());
//        mailMessage.copyTo(c);  抄送人
            javaMailSender.send(mailMessage);
        } catch (MailException e) {
            logger.error("邮件发送失败:{}",e.getMessage());
        }
    }

    // HTML邮件
    // 与文本格式邮件代码对比，富文本HTML邮件发送使用MimeMessageHelper类，
    // 把setText()方法的消息文本设置为html,并将第二个参数设置为true,表示这是html的富文本。
    // MimeMessageHelper支持发送复杂邮件模板，支持文本、附件、HTML、图片等。
    public void sendHTMLMail(MailBean mailBean) {
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = javaMailSender.createMimeMessage();
            //true 表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(MAIL_SENDER);
            messageHelper.setTo(mailBean.getRecipient());
            messageHelper.setSubject(mailBean.getSubject());
            //邮件抄送
            //messageHelper.addCc("抄送人");
            messageHelper.setText(mailBean.getContent(),true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("邮件发送失败:{}", e.getMessage());
        }

    }





    // 附件格式邮件发送
    //
    //发送附件需要用到FileSystemResource类对文件进行封装，再添加到MimeMessageHelper中。
    // 可以通过多个addAttachment方法发送多个附件,File.separator是用来分隔同一个路径字符串中的目录。
    public void sendAttachmentMail(MailBean mailBean) {
        MimeMessage mimeMessage = null;
        try {
            mimeMessage = javaMailSender.createMimeMessage();
            // true 表示需要创建一个multipart message
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(MAIL_SENDER);
            messageHelper.setTo(mailBean.getRecipient());
            messageHelper.setSubject(mailBean.getSubject());
            messageHelper.setText(mailBean.getContent());

            //文件路径 目前写死在代码中，之后可以当参数传过来，或者在MailBean中添加属性absolutePath
            String absolutePath = "D:\\900142\\桌面\\images\\1.jpg";
            FileSystemResource file = new FileSystemResource(new File(absolutePath));
            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/email.png"));

            String fileName = absolutePath.substring(absolutePath.lastIndexOf(File.separator));
            //添加附件,第一个参数表示添加到 Email 中附件的名称，第二个参数是图片资源
            messageHelper.addAttachment(fileName,file);
            //多个附件
            //mimeMessageHelper.addAttachment(fileName1, file1);

            javaMailSender.send(mimeMessage);


        } catch (MessagingException e) {
            logger.error("邮件发送失败:{}",e.getMessage());
        }
    }

    //静态资源格式邮件发送
    //
    //邮件格式的静态资源，需要用到MimeMessageHelper中的addInline方法。
    // 需要注意的是：添加内联资源，一个id对应一个资源，最终通过id来找到该资源。
    // 即<img src='cid:"+ rscId + "' >和addInline(rscId,res)中的rscId要一致。
    // 同时要添加多个图片，可以使用多条<img src='cid:"+ rscId + "' >和addInline(rscId,res)来实现。
    public void sendInlineMail(MailBean mailBean) {
        MimeMessage mimeMessage = null;

        try {
            mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,true);
            messageHelper.setFrom(MAIL_SENDER);
            messageHelper.setTo(mailBean.getRecipient());
            messageHelper.setSubject(mailBean.getSubject());
            messageHelper.setText(mailBean.getContent(), true);
            //文件路径
            //文件路径
            String absolutePath = "D:\\900142\\桌面\\images\\2.jpg";
            FileSystemResource file = new FileSystemResource(new File(absolutePath));
            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/image/email.png"))
            //添加多个图片可以使用多条 <img src='cid:" + rscId + "' > 和 mimeMessageHelper.addInline(rscId, res) 来实现
            messageHelper.addInline("picture", file);

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            logger.error("邮件发送失败:{}", e.getMessage());
        }
    }

}
