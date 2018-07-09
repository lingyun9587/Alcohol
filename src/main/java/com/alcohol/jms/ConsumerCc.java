package com.alcohol.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * 消费者如下
 */
@Component
public class ConsumerCc {

    private final static Logger logger = LoggerFactory
            .getLogger(ConsumerCc.class);

    @JmsListener(destination = "queue1", containerFactory = "jmsQueueListener")
   // @SendTo("hehe")//返回到到另外一个队列进行处理
    public void receiveQueue(final TextMessage text, Session session)
            throws JMSException {
        try {
            logger.debug("Consumer收到的报文为:" + text.getText());
            System.out.println("收到的保温为"+text.getText());
            text.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
        } catch (Exception e) {
            session.recover();// 此不可省略 重发信息使用
        }

    }
}
