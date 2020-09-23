package rabbitmq.test.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.test.ConnectionUtil;

/**
 * Author: Chevro.Lee <br>
 * Description: 主题（通配符）模式
 *                       将路由键和某个模式进行匹配，此时队列需要绑定到一个模式上，符号“#”匹配一个或者多个从，符号“*”匹配一个词
 *                       例如：audit.#能够匹配audit.irs.corporate或者audit.irs,但是audit.*就只能匹配到audit.irs
 * Date: Create in  10:25 2019-10-16
 **/
public class Send {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        // 消息内容
        String message = "Hello World!!";
        channel.basicPublish(EXCHANGE_NAME, "routeKey.1", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        // 关闭
        channel.close();
        connection.close();
    }
}
