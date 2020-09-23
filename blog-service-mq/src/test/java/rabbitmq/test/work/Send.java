package rabbitmq.test.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.test.ConnectionUtil;

import java.io.IOException;

/**
 * Author: Chevro.Lee <br>
 * Description:work模式，消息直接发送到队列，且有多个消费者，并按照消费者能力分配消息
 *                      生产者，向队列中发送100条信息，一个生产者对应多个消费者
 * Date: Create in  16:21 2019-10-15
 **/
public class Send {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false, null);
        for (int i=0;i<100;i++){
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
            Thread.sleep(i * 10);
        }
        // 关闭通道以及连接
        channel.close();
        connection.close();
    }
}
