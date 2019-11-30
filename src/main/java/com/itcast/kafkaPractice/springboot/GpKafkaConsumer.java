package com.itcast.kafkaPractice.springboot;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by Administrator on 2019/9/28.
 */
@Component
public class GpKafkaConsumer{

    @KafkaListener(topics = {"test4"})
    public void listener(ConsumerRecord record){
        Optional<?> msg= Optional.ofNullable(record.value());
        if(msg.isPresent()){
            System.out.println(msg.get());
        }
    }
}
