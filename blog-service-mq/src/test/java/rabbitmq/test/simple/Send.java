package rabbitmq.test.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.test.ConnectionUtil;

import java.io.IOException;

/**
 * Author: Chevro.Lee <br>
 * Description:  简单模式，消息直接发送到队列
 *                          生产者发送消息到队列
 * Date: Create in  15:23 2019-10-15
 **/
public class Send {

    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] args) throws IOException {
        // 获取连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中获取通道
        Channel channel = connection.createChannel();
        // 声明创建队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 消息队列
        String message = "Hello World";
        // 发布消息到队列
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        // 关闭通道和连接
        channel.close();
        connection.close();
    }
}
