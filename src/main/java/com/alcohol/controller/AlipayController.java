package com.alcohol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 第三方支付
 */
@Controller
public class AlipayController {

    /**
     * 后台首页
     * @return
     */
    @GetMapping("/a.html")
    public String index() { return "/a"; }

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2018070460548305";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "" +
            "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCawUBpDer2q1DShEncbU/" +
            "PgxwdAYhn8hgVN82el7t2xqMD91VT450q32vy9EUTwes6SCVufNqFeMksJnzGN6gPJ/" +
            "MdTPiLyjHmdvUTvBEZjuotxnAnbhvGryvH7tux3oj+EVdLLjGiUy6UDr+Gz6D64Yxr+PGZSPCGSIBUYuM/" +
            "cmJbQXhfu+3s94/1j8d6nAqf58rDy+yBC6u/" +
            "MtN7JxS9ZM3V1mqlmmyEJv+eennCKl4IUTCmsM3+VX74sr+Brk0EtObo7J3V2CRpUSrRLkGHc7oIB6kiSNN2AvdbXDOmXEC61vInlUXEsdXeLQj2sEir8XWJagcFf2a4g3wV5I1" +
            "hAgMBAAECggEAUB9cBMgy7ZFXIBdj0l86IhsklBP2FrMuKtYgfErcwSsovWXx5KQE/Gx+jGRR21/" +
            "cNOmjj6fOz6nwpg4rLcklFuu9IF94k4+U9FiEnLoTNGNc9WQ3LwMAGIcbKMGPN3EbBkfeJUt+YSemBvv3x4l6NEpQlJpoI8+y0CN7ZbVpKLB1SDrfOC09ZWGOF+fRwHs/" +
            "R3jEfXRWyRaF8R+0OJEt+h4h8tdngdQ/D81ZVZ3BHasyHcU/lDrJI1iBCs8QjjI4P98iA929xlVgtG1CNnmzSa94yKV+iwXCexR1Vks/" +
            "sTCn2hSjeAwMH5Z1xb8bz5SA6yOe+N8ukS+Y0E3T7ZmILQKBgQDir4AuLRaSXFO3uRQP9ttMLep7y/V6VOwVtR1M/" +
            "mqgd/nGb/ngDDZaGU7j2YBPvX2bEouuM6QbJs6ESaYLSXhg3qNyw/dmPukFbV6+dn4biwq9V+RWg5XgQTyj8IOGbbLz1" +
            "XIzMKvQYccHRL+FrLMCEi2C7k9wLgvUxnOhQpP/pwKBgQCuxHZ88XqLap9d+HXwr7GBYWIZRUkZ8O7FgYMeDRgHJkuW695" +
            "CS9z6GbCmBtl5kQ6iIhoAEq+GqqOMVRIJDOuzn3mYJ8Qvy5pF1Vshs4NEQKOLsJiaoWH2DywZ3EytR0PqAHfFBIqXBBc" +
            "6RqutWmtdV9fVsNbL7GrAFRCKI5JrtwKBgGZIcBrlx1CFDlVQ0medqDfIZAw17G6lUN0+mpj6FxyO5goOzn20pPWkg78" +
            "1jbrtMna9OH+C16JUsUxpet2O6h0zeCa5m7Dr4/LJ9b0U9DPzWgYzWVlcCE1mwjjqtj3604tIHT5R0MS2ybpS3+QuF5" +
            "twnk58vyVL8ZSxcs0poGPzAoGADNhx4RMar76hghWuDYfMYzcWL8dQ7TAxTKENUxshRf1BlxE1H+hVkKhsvHdKas4Hp" +
            "lEdWh4Fer92D+18yt+BP42cMVvS5rRvXWymKx73qf+aRb0wKkVsXtqUoj34ANB5JGzuYLPJv/nT/zuMiMxZkd3QBmve" +
            "DdUNr5qcwZqFu5cCgYACxw4hZzLghwOCW429xDMfe1peqjTeD1u7YsjuQz3wJrnn8QgFheestVXsC+QU6peisB6Y4aM" +
            "dLwXvcPXqM2sFOsDj+/i8sdPjg/K8WVFTcYIWzpOT7vz9YRcH37iYU1dMIRf+F0dmUiXZ+pnmlxCk161zEyn9ytWUe" +
            "JOaGjHxRw==";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj6uBsH/" +
            "Ye6ADyKbyEiHIpLf0v0HiYL7ZUnwygcnqOaUR6+ifuQpzbMlZi6Uu+V5Qp6GbmlX3Q0sqeENHtA0C2TenMn" +
            "YaqmA3UhUQ/vKoy5AO2xVSuxxzDoMYCAQmdm5HDdKw1lbygy0jIFTBJaMNld2UUlGJnHcxw+S1fJEJVKk8NDMV/" +
            "UP61Cm/q2ix1ehcKtFqNJy+XuuXaAStzSRRvpQwhMPyRsd1uCESVJszETM6Hwk8L73xYHI9MUMwBYBvBXscTV2gNQQG" +
            "0myn9AsVA1EpXFk9ykKopWtEI7XfP+adeROULIxarcyQq1FFcx6QVJLUqrIkGKYzt2efTsySfwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "https://openapi.alipay.com/gateway.do";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "https://openapi.alipay.com/gateway.do";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

    // 支付宝网关
    public static String log_path = "D:\\";


}

