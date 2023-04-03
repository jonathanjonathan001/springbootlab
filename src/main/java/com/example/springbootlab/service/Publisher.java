package com.example.springbootlab.service;

import com.example.springbootlab.entity.Movie;
import com.example.springbootlab.repository.MovieRepository;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;


@Service
public class Publisher {


    RabbitTemplate template;

    final String MESSAGE_QUEUE = "messages";

    private MovieRepository repository;

    public Publisher(RabbitTemplate template, MovieRepository repository) {
        this.template = template;
        this.repository = repository;
    }

    public void publishMessage(Movie movie) {
            template.convertAndSend("my.topic", "message.data", movie);

    }

    @Bean
    public Queue messageQueue() {
        return new Queue(MESSAGE_QUEUE);
    }



    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("my.topic");
    }

    @Bean
    public Binding messages(Queue messageQueue, TopicExchange exchange){
        return BindingBuilder.bind(messageQueue).to(exchange).with("message.*");
    }

    @RabbitListener(queues = MESSAGE_QUEUE)
    public void movieListener (Movie movie){
        repository.save(movie);
    }



}
