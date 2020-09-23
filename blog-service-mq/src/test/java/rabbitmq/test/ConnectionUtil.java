package rabbitmq.test;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

/**
 * Author: Chevro.Lee <br>
 * Description:MQ连接工具类
 * Date: Create in  15:16 2019-10-15
 **/
public class ConnectionUtil {

    public static Connection getConnection() throws IOException {
        // 定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务器地址
        factory.setHost("localhost");
        // 设置端口号
        factory.setPort(5672);
        // 设置账号信息，virtualHost，用户名，密码
        factory.setVirtualHost("testhost");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 通过连接工程获取连接
        return factory.newConnection();
    }
}
