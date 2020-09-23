package rabbitmq.test.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.test.ConnectionUtil;

import java.io.IOException;

/**
 * Author: Chevro.Lee <br>
 * Description: 路由模式
 *                         通过指定key绑定到交换机，交换机通过这个路由key去路由对应队列
 *                         生产者发送信息步骤如下:
 *                              创建连接工厂 -> 创建连接以及信道 -> 发送消息到交换机 -> 交换机根据交换机类型（Exchange Type）去路由队列
 *                         消费者接收消息步骤如下：
 *                              创建连接工厂 -> 创建连接以及信道 -> 创建队列 -> 创建消费者 -> 设置信道 -> 获取消息内容
 * Date: Create in  10:00 2019-10-16
 **/
public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException {
        // 获取连接以及通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明exchange，direct为交换机类型
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        // 消息内容，delete为消息key
        String message = "删除内容";
        channel.basicPublish(EXCHANGE_NAME,"delete",null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        // 关闭流以及通道
        channel.close();
        connection.close();
    }
}
