package rabbitmq.test.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.test.ConnectionUtil;

import java.io.IOException;

/**
 * Author: Chevro.Lee <br>
 * Description:订阅模式
 * 一个生产者对应多个消费者，每一个消费者都有自己的一个队列，生产者没有将消息发送到队列，而是发送到了交换机
 * 每个队列都要绑定到交换机，生产者发送的消息，经过交换机，到达队列实现一个消息被多个消费者获取的目的，但是需要注意一点：
 * 一个消费者队列可以有多个消费者实例，但是只有其中一个消费者实例会被消费
 * Date: Create in  17:49 2019-10-15
 **/
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws IOException {
        // 获取到连接以及mq通道
        Connection connection =  ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 此处：声明exchange，而非队列
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        // 消息内容
        String message = "Hello World!";
        // 发送消息,注：消息发送到没有绑定队列的交换机时，消息将丢失，因为，交换机没有存储消息的能力，消息只能存储在队列中
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
