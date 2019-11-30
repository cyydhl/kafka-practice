package com.itcast.kafkaPractice.api;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/9/28.
 */
public class Producer{

    public static void main(String[] args) {
        Properties properties = new Properties();
        //连接kafka集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.16.200.129:9092");
        //设置ack应答，分为0,1，-1（all）
        properties.put(ProducerConfig.ACKS_CONFIG,"all");//当 enable.idempotence 为 true，这里默认为 all
        //启用幂等性
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,"true");
        //key序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        //value序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //创建生产者
        KafkaProducer<Integer,String> producer = new KafkaProducer<Integer,String>(properties);

        int num = 0;
        while(num < 10){
            String msg = "lala kafka test" + num;
            try {
                producer.send(new ProducerRecord<Integer, String>("cyy123",msg)).get();
                TimeUnit.SECONDS.sleep(2);
                num++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
