package com.lmsf.org.config;

import com.lmsf.org.entity.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationWorker {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleBookCreatedMessage(Book book) {
        System.out.println("=======================================");
        System.out.println("BACKGROUND WORKER TRIGGERED!");
        System.out.println("Sending email to all users about new book: " + book.getTitle());
        System.out.println("=======================================");

    }
}
