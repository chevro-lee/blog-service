package ink.chevro.user;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  18:58 2019-11-11
 **/
@Component
public class UserRegisterReceiver {

    private final static Logger logger = LoggerFactory.getLogger(UserRegisterSender.class);

    @RabbitListener(queues = UserRegisterMQConfig.QUEUE_MAIL_SENDER, containerFactory = "mailListenerContainer")
    public void mailSendReceiver(Message message, Channel channel) throws IOException {
        String mail = new String(message.getBody(), StandardCharsets.UTF_8);
        logger.info("[x] Received Action [{}] Data [mail={}] Date [{}]", "Send Mail", mail, LocalDateTime.now());
        System.out.println("成功发送邮件！");
        // 手动应答消息 (基于消费者消息确认机制)
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    @RabbitListener(queues = UserRegisterMQConfig.QUEUE_MSG_SENDER, containerFactory = "msgListenerContainer")
    public void msgSendReceiver(Message message) {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        logger.info("[x] Received Action [{}] Data [mail={}]", "Send Message", msg);
    }
}
