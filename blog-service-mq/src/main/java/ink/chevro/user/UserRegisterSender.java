package ink.chevro.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  18:55 2019-11-11
 **/
@Component
public class UserRegisterSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private final static Logger logger = LoggerFactory.getLogger(UserRegisterSender.class);

    private final static Long DELAY_TIME = 3 * 60 *1000L;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     *  发送邮件到消息队列
     *
     * @param email  email
     */
    public void sendMail(String email) {
        logger.info("[x] Sent [{}] Data [email={}]", "Email" , email);
        UUID uuid = UUID.randomUUID();
        // 标识消息的唯一ID
        CorrelationData correlationData = new CorrelationData(uuid.toString());
        rabbitTemplate.convertAndSend(UserRegisterMQConfig.USER_REGISTER_EXCHANGE, UserRegisterMQConfig.KEY_MAIL_SENDER, email,
                (Message message) -> {
                    MessageProperties messageProperties = message.getMessageProperties();
                    messageProperties.setContentEncoding("utf-8");
                    return message;
                }, correlationData);
    }

    /**
     * 发送邮件到死信队列，实现了RabbitMQ延时队列的功能
     *
     * @param email  email
     */
    public void sendMailDelay(String email) {
        logger.info("[x] Sent [{}] Data [email={}] Delay Time [{}] Date [{}]", "Email" , email, DELAY_TIME, LocalDateTime.now());
        rabbitTemplate.convertAndSend(UserRegisterMQConfig.USER_REGISTER_DEAD_EXCHANGE_NAME,
                UserRegisterMQConfig.KEY_MAIL_SENDER_DEAD, email, message -> {
                    message.getMessageProperties().setExpiration(DELAY_TIME + "");
                    return message;
                });
    }

    /**
     * 实现了RabbitMQ基于生产者的消息确认机制
     *
     * @param phone 发送邮件
     */
    public void sendMsg(String phone) {
        logger.info("[x] Sent [{}] Data [phone={}]", "phone" , phone);
        UUID uuid = UUID.randomUUID();
        // 标识消息的唯一ID
        CorrelationData correlationData = new CorrelationData(uuid.toString());
        rabbitTemplate.convertAndSend(UserRegisterMQConfig.USER_REGISTER_EXCHANGE, UserRegisterMQConfig.KEY_MSG_SENDER, phone, correlationData);
        System.out.println("成功发送注册短信！");
    }

    /**
     * 发送消息到Exchange的回调
     * 注：非集群模式，集群模式下发送到集群代理
     *
     * @param correlationData  标识消息的唯一ID
     * @param ack 是否成功
     * @param cause 失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息发送到exchange成功，消息ID：{}",correlationData == null ? null : correlationData.getId());
        } else {
            logger.info("消息发送到exchange失败! "+ cause + correlationData.toString());
        }
    }

    /**
     * 消息从exchange投递到Queue,如果投递失败，将会返回一个returnCallback
     * 利用这个callback控制消息的最终一致性和部分纠错能力
     *
     * @param message 消息
     * @param replyCode 消息码
     * @param replyText 消息文本
     * @param exchange 交换机
     * @param routingKey 路由key
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.info(MessageFormat.format("消息发送到Queue的ReturnCallback:{0},{1},{2},{3},{4},{5}",
                message, replyCode, replyText, exchange, routingKey));
    }
}
