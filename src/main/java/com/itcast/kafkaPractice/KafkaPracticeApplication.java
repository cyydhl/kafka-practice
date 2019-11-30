package com.itcast.kafkaPractice;

import com.itcast.kafkaPractice.springboot.GpKafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by Administrator on 2019/9/28.
 */
@SpringBootApplication
public class KafkaPracticeApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(KafkaPracticeApplication.class, args);
        GpKafkaProducer kafkaProducer=context.getBean(GpKafkaProducer.class);
        for(int i=0;i<10;i++){
            kafkaProducer.send();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}