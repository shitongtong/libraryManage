package com.stt;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import java.util.List;

/**
 * @ Author     ：xumw.
 * @ Date       ：Created in
 * @ Description：${description}
 * @ Modified By：
 */

public class MailUtil {
    private static final String DEFAULT_CHARSET = "GBK";
    private static Object lock = new Object();
    private String hostName = null;
    private String fromMail = null;
    private String fromName = null;
    private String authenticationName = null;
    private String authenticationPassword = null;
    private String charset = null;
    private boolean debug = false;
    private HtmlEmail mail=null;


    public MailUtil(String hostName ,String fromMail,String fromName,String authenticationName,String authenticationPassword,String charset) throws EmailException {


        this.hostName=hostName;
        this.fromMail=fromMail;
        this.fromName=fromName;
        this.authenticationName=authenticationName;
        this.authenticationPassword=authenticationPassword;
        this.charset=charset;

        mail=initMail();
    }



    /**
     * 普通邮件
     * @param subject
     * @param targets
     * @param htmlMsg
     * @return
     */
    public boolean sendMail(String subject, List targets, String htmlMsg) {
        return this.sendMail(subject, targets, (List)null, htmlMsg);
    }

    /**
     * 带附件
     * @param subject
     * @param targets
     * @param attached
     * @param htmlMsg
     * @return
     */
    public boolean sendMail(String subject, List targets, List attached, String htmlMsg) {
        try {

            mail.setSubject(subject == null ? "" : subject);

            int i;
            for(i = 0; i < targets.size(); ++i) {
                mail.addTo((String)targets.get(i));
            }

            if (attached != null && attached.size() > 0) {
                for(i = 0; i < attached.size(); ++i) {
                    EmailAttachment attach = new EmailAttachment();
                    String path = (String)attached.get(i);

                    attach.setPath(path);
                    attach.setDisposition("attachment");
                    mail.attach(attach);
                }
            }

            mail.setHtmlMsg(htmlMsg);
            mail.send();
            return true;
        } catch (EmailException var9) {

            return false;
        }
    }

    private HtmlEmail initMail() throws EmailException {
        HtmlEmail mail = new HtmlEmail();
        mail.setHostName(this.hostName);
        mail.setFrom(this.fromMail, this.fromName);
        mail.setAuthentication(this.authenticationName, this.authenticationPassword);
        mail.setCharset(this.charset);
        mail.setDebug(this.debug);
        return mail;
    }

    public static void main(String[] args) {
        try {
            MailUtil  util=new MailUtil("sh.mail.chinaunicom.cn","sh-xumw@chinaunicom.cn","","","","GBK");
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
