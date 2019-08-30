package com.lighting.business.device.controller;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQ {

//    @Autowired
//    private RabbitAdmin rabbitAdmin;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void addQueue(String name) {
//        //System.out.println(rabbitAdmin.getRabbitTemplate());
//        //System.out.println(rabbitTemplate);
//        Queue queue = new Queue("queue_" + name);
//        DirectExchange exchange = new DirectExchange("exchange_" + name);
//        rabbitAdmin.declareQueue(queue);
//        rabbitAdmin.declareExchange(exchange);
//        rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(exchange).with(name));
//        //rabbitAdmin.getRabbitTemplate()
//        /*connectionFactory.createConnection().createChannel(false)
//                .queueDeclare("queue_" + name, true, false, false, null);*/
//    }
//
//    @Async
//    public void send(String name, Object o) {
//        System.out.println("mq[parse]-->" + name);
//        System.out.println("mq[parse]-->" + o);
//        //rabbitTemplate.setRoutingKey();
//        rabbitTemplate.convertAndSend("queue_" + name, o);
//    }
}