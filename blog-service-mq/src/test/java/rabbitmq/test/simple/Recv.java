package rabbitmq.test.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import rabbitmq.test.ConnectionUtil;

import java.io.IOException;

/**
 * Author: Chevro.Lee <br>
 * Description: 消费者从队列中获取消息
 * Date: Create in  16:01 2019-10-15
 **/
public class Recv {

    private final static String QUERY_NAME = "q_test_01";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取连接以及通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUERY_NAME, false, false, false, null);
        // 定义队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列
        channel.basicConsume(QUERY_NAME,true,consumer);
        // 获取消息
        while(true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}
