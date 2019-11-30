package com.itcast.kafkaPractice.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2019/9/28.
 */
@Component
public class GpKafkaProducer {

        @Autowired
        private KafkaTemplate<String,String> kafkaTemplate;

        public void send(){
            kafkaTemplate.send("test4","msgKey","msgData");
        }


}
