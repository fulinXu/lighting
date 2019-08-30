//package com.lighting.business.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
//@Configuration
//public class RabbitConfig {
//
////    @Value("${spring.rabbitmq.host}")
////    private String host;
////
////    @Value("${spring.rabbitmq.port}")
////    private int port;
////
////    @Value("${spring.rabbitmq.username}")
////    private String username;
////
////    @Value("${spring.rabbitmq.password}")
////    private String password;
//
//
//    public static final String EXCHANGE_A = "exchange_test";
//
//
//    public static final String QUEUE_A = "queue_test";
//
//    public static final String ROUTINGKEY_A = "test";
////
////    @Bean
////    public ConnectionFactory connectionFactory() {
////        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
////        connectionFactory.setUsername(username);
////        connectionFactory.setPassword(password);
////        connectionFactory.setVirtualHost("/");
////        connectionFactory.setPublisherConfirms(true);
////        return connectionFactory;
////    }
//
////    @Bean
////    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
////    //必须是prototype类型
////    public RabbitTemplate rabbitTemplate() {
////        RabbitTemplate template = new RabbitTemplate(connectionFactory());
////        return template;
////    }
//
//    /**
//     * 针对消费者配置
//     * 1. 设置交换机类型
//     * 2. 将队列绑定到交换机
//     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
//     HeadersExchange ：通过添加属性key-value匹配
//     DirectExchange:按照routingkey分发到指定队列
//     TopicExchange:多关键字匹配
//     */
//    @Bean
//    public DirectExchange defaultExchange() {
//        return new DirectExchange(EXCHANGE_A,true,false);
//    }
//
//    /**
//     * 获取队列A
//     * @return
//     */
//    @Bean
//    public Queue queueA() {
//        return new Queue(QUEUE_A, true); //队列持久
//    }
//
//    @Bean
//    public Binding binding() {
//
//        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(RabbitConfig.ROUTINGKEY_A);
//    }
//
//
//
//
//    /*****************************************************************************/
////    @Autowired
////    private ConnectionFactory connectionFactory;
////
////    @Bean
////    public MessageConverter messageConverter() {
////        return new Jackson2JsonMessageConverter();
////    }
////
////    @Bean
////    public RabbitTemplate rabbitTemplate() {
////        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
////        rabbitTemplate.setMessageConverter(messageConverter());
////        return rabbitTemplate;
////    }
////
////    @Bean
////    public RabbitAdmin rabbitAdmin() {
////        return new RabbitAdmin(rabbitTemplate());
////    }
//
//
//    /**
//     * 消息交换机的名字
//     * */
//    private static final String DIRECT_EXCHANGE = "DirectExchange";
//
//    private static final String TOPIC_EXCHANGE = "TopicExchange";
//
//    private static final String FANOUT_EXCHANGE ="FanoutExchange" ;
//
//    private static final String HEADERS_EXCHANGE ="HeadersExchange" ;
//
//    /**
//     * 队列的名字
//     * */
//    private static final String DIRECT_QUEUE = "DirectQueue";
//
//    private static final String TOPIC_QUEUE = "TopicQueue";
//
//    private static final String FANOUT_QUEUE = "FanoutQueue";
//
//    private static final String HEADERS_QUEUE = "HeadersQueue";
//
//    /**
//     * key
//     * */
//    private static final String DIRECT_KEY = "DirectKey";
//
//    private static final String TOPIC_KEY = "Topic.#";
//
//
//    @Bean
//    public Binding bindingDirect() {
//        return BindingBuilder.bind(dirctQueue()).to(directExchange()).with(DIRECT_KEY);
//    }
//    @Bean
//    public Queue dirctQueue() {
//        return new Queue(DIRECT_QUEUE,true,false,false);
//    }
//
//    @Bean
//    public DirectExchange directExchange(){
//        return new DirectExchange(DIRECT_EXCHANGE,true,false);
//    }
//}
