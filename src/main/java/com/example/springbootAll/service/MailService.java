package com.example.springbootAll.service;

/**
 * @Author: sea
 * @Description: 发送邮件接口层
 * @Date: 14:55 2017/8/16
 */
public interface MailService {

    /**
     * 发送简单文本
     * @param to 发送至邮箱地址
     * @param subject 标题
     * @param content 内容
     */
     void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML格式邮件
     * @param to 发送至邮箱地址
     * @param subject 标题
     * @param content 内容
     */
     void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     * @param to 目标邮箱
     * @param subject 标题
     * @param content 内容
     * @param filePath 本地资源路径
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送静态资源的邮件
     * @param to 目标邮箱
     * @param subject 主题
     * @param content 内容
     * @param rscPath 资源路径
     * @param rscId 资源标识id
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
