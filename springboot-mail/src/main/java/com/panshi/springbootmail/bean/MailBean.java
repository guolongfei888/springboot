package com.panshi.springbootmail.bean;

import java.io.Serializable;

/**
 * @ClassName MailBean
 * @Description
 * @Author guolongfei
 * @Date 2020/3/26  10:40
 * @Version
 */
public class MailBean implements Serializable {

    private static final long serialVersionUID = -8751535420309424538L;
    private String recipient;//邮件接收人
    private String subject; //邮件主题
    private String content; //邮件内容

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}