package com.lmsf.org.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "book_created_queue";
    public static final String EXCHANGE_NAME = "lms_exchange";
    public static final String ROUTING_KEY = "book.created";

    // 1. Create the Queue (The actual mailbox)
    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true); // true = durable (survives a server restart)
    }

    // 2. Create the Exchange (The post office sorting center)
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // 3. Bind the Queue to the Exchange using a Routing Key
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    // 4. Force Spring to send messages as beautiful JSON!
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
