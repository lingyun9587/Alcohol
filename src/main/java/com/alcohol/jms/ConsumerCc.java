package com.alcohol.jms;

import com.alcohol.mapper.SkuMapper;
import com.alcohol.pojo.Commodity;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.List;

/**
 * 消费者如下
 */
@Component
public class ConsumerCc {

   /* private final static Logger logger = LoggerFactory
            .getLogger(ConsumerCc.class);
    @Resource
    private SkuMapper skuMapper;

    @JmsListener(destination = "queue1", containerFactory = "jmsQueueListener")
   // @SendTo("hehe")//返回到到另外一个队列进行处理
    public void receiveQueue(final Message message, Session session)
            throws JMSException {
        try {
            // 如果是文本消息
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
              List<Commodity> list = JSON.parseArray(tm.getText(),Commodity.class);
                System.out.println(list.size());
                System.out.println("xiaoxi");

                for (Commodity commodity: list  ) {
                    if(commodity.getOrderstatusId() == 1){ //待付款
                        skuMapper.updateInfo(commodity.getSkuId(),commodity.getNumber(),0);
                    } else if(commodity.getOrderstatusId() == 7){
                        skuMapper.updateInfo(commodity.getSkuId(),commodity.getNumber(),3);
                    }

                }
            }
            message.acknowledge();// 使用手动签收模式，需要手动的调用，如果不在catch中调用session.recover()消息只会在重启服务后重发
        } catch (Exception e) {
            session.recover();// 此不可省略 重发信息使用
        }
    }*/
}
