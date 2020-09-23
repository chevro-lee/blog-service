package ink.chevro.user;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  11:23 2019-11-11
 **/
@Configuration
public class UserRegisterMQConfig {

    // 交换机
    static final String USER_REGISTER_EXCHANGE = "register_exchange";
    // 死信队列交换机
    static final String USER_REGISTER_DEAD_EXCHANGE_NAME = "register_delay_exchange";

    // 路由KEY
    static final String KEY_MAIL_SENDER = "key_mail_sender";
    static final String KEY_MSG_SENDER = "key_msg_sender";
    // 死信队列路由KEY
    static final String KEY_MAIL_SENDER_DEAD = "key_mail_sender_dead";

    // 队列名字
    static final String QUEUE_MAIL_SENDER = "queue_mail_sender";
    static final String QUEUE_MSG_SENDER = "queue_msg_sender";
    // 邮件发送死信队列
    static final String QUEUE_MAIL_DEAD_SENDER = "queue_mail_delay_sender";

    /**
     * 构建ExchangeType为Direct方式的交换机
     * durable:是否持久化，持久化后再重启这个交换机还会存在，MQ默认持久化交换机
     * autoDelete:长期不使用这个交换机是否自动删除
     *
     * @return Exchange
     */
    @Bean
    public Exchange userRegisterExchange() {
        return ExchangeBuilder.directExchange(USER_REGISTER_EXCHANGE)
                .durable(true)
                .build();
    }

    /**
     * 死信队列交换机
     *
     * @return Exchange
     */
    @Bean
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange(USER_REGISTER_DEAD_EXCHANGE_NAME)
                .durable(true)
                .build();
    }

    /**
     * 邮件发送队列
     *
     * @return Queue
     */
    @Bean
    public Queue mailSenderQueue() {
        return QueueBuilder.durable(QUEUE_MAIL_SENDER)
                .build();
    }

    /**
     * 邮件发送延迟队列 实现的两种方式
     * TTL（设置消息过期时间）：
     * RabbitMQ 可以控制消息的生存时间，有以下两种方式：
     * 1.针对队列设置x-expires（设置了x-expires，则改队列的所有消息都有相同的过期时间）
     * 2.针对Message设置x-message-ttl来对每条消息单独设置，每条消息的TTL可以不同
     * 如果消息超过了设置的生存时间，则消息变为dead letter(私信)。注：如果上述两种方式同时设置，则以最先到期的时间为准
     *
     * Dead Letter Exchanges (DLX)（利用死信队列）：
     * RabbitMQ的Queue可以配置x-dead-letter-exchange和x-dead-letter-routing-key(可选)两个参数，如果队列内出现了dead letter，
     * 则按照这两个参数配置的交换机和路由key重新转发到指定的队列
     * x-dead-letter-exchange: 出现dead letter(死信)之后将dead letter重新发送到指定的exchange
     * x-dead-letter-routing-key: 出现dead letter之后将dead letter重新按照指定的routing-key发送
     *
     * @return Queue
     */
    @Bean
    public Queue mailSenderDelayQueue() {
        Map<String, Object> args = new HashMap<>();
        // 声明出现死信之后，将死信转发到交换机：USER_REGISTER_EXCHANGE
        args.put("x-dead-letter-exchange", USER_REGISTER_EXCHANGE);
        // 声明死信出现并转发时携带的routing-key
        args.put("x-dead-letter-routing-key", KEY_MAIL_SENDER);
        return QueueBuilder.durable(QUEUE_MAIL_DEAD_SENDER)
                .withArguments(args)
                .build();
    }

    /**
     * 短信发送队列
     *
     * @return Queue
     */
    @Bean
    public Queue msgSenderQueue() {
        return QueueBuilder.durable(QUEUE_MSG_SENDER)
                .build();
    }

    /**
     * 将邮件发送队列绑定到交换机USER_REGISTER_EXCHANGE
     *
     * @param userRegisterExchange 交换机
     * @param mailSenderQueue      队列
     * @return Binding
     */
    @Bean
    public Binding mailSenderBinding(Exchange userRegisterExchange, Queue mailSenderQueue) {
        return BindingBuilder.bind(mailSenderQueue)
                .to(userRegisterExchange)
                .with(KEY_MAIL_SENDER)
                .noargs();
    }

    /**
     * 绑定死信队列到交换机
     *
     * @param deadLetterExchange  deadLetterExchange
     * @param mailSenderDelayQueue mailSenderDelayQueue
     * @return Binding
     */
    @Bean
    public Binding deadQueueBinding(Exchange deadLetterExchange, Queue mailSenderDelayQueue) {
        return BindingBuilder.bind(mailSenderDelayQueue)
                .to(deadLetterExchange)
                .with(KEY_MAIL_SENDER_DEAD)
                .noargs();
    }

    /**
     * 将消息发送队列绑定到交换机USER_REGISTER_EXCHANGE
     *
     * @param userRegisterExchange 交换机
     * @param msgSenderQueue       队列
     * @return Binding
     */
    @Bean
    public Binding msgSenderBinding(Exchange userRegisterExchange, Queue msgSenderQueue) {
        return BindingBuilder.bind(msgSenderQueue)
                .to(userRegisterExchange)
                .with(KEY_MSG_SENDER)
                .noargs();
    }

    /**
     * 邮件发送消息队列个性化配置
     *
     * @return SimpleMessageListenerContainer
     */
    @Bean
    public SimpleRabbitListenerContainerFactory mailListenerContainer(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        // 设置创建的最小消费者的数量
        rabbitListenerContainerFactory.setConcurrentConsumers(1);
        // 设置创建最大消费者的数量
        rabbitListenerContainerFactory.setMaxConcurrentConsumers(1);
        // 设置消费者预读的数量，当消费者未应答的数量达到设置的prefetchCount这个数量，那么队列将不再对这个消费投递消息
        rabbitListenerContainerFactory.setPrefetchCount(5);
        // 设置手动确认消息应答
        rabbitListenerContainerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        configurer.configure(rabbitListenerContainerFactory, connectionFactory);
        return rabbitListenerContainerFactory;
    }

    /**
     * 消息发送队列个性化配置
     *
     * @return SimpleMessageListenerContainer
     */
    @Bean
    public SimpleRabbitListenerContainerFactory msgListenerContainer(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        rabbitListenerContainerFactory.setConcurrentConsumers(1);
        rabbitListenerContainerFactory.setMaxConcurrentConsumers(1);
        rabbitListenerContainerFactory.setPrefetchCount(5);
        configurer.configure(rabbitListenerContainerFactory, connectionFactory);
        return rabbitListenerContainerFactory;
    }
}
