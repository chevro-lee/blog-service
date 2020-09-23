package rabbitmq.test.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import rabbitmq.test.ConnectionUtil;

import java.io.IOException;

/**
 * Author: Chevro.Lee <br>
 * Description:消费者1
 * Date: Create in  16:27 2019-10-15
 **/
public class Recv1 {

    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] args) throws IOException, InterruptedException {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 设置同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);
        // 定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，false表示手动返回完成状态，true表示自动确认消息：（无论消费者是否获取消息成功，都认为是已经成功消费）
        channel.basicConsume(QUEUE_NAME, false, consumer);
        // 获取消息
        while(true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [y] Received '" + message +"'");
            // 休眠
            Thread.sleep(3000);
            // 返回确认状态，注释掉表示使用自动确认状态（手动应答开启开启按劳分配），
            // 注：如果消费者从队列中获取消息之后，服务器会将该状态标记为不可用的状态，如果消费者一直没有反馈，那么该消息就一直处于不可用的状态
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
