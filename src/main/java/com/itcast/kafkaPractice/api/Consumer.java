package com.itcast.kafkaPractice.api;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2019/9/28.
 */
public class Consumer{

    public static void main(String[] args) {
        Properties properties = new Properties();
        //连接kafka集群
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"172.16.200.129:9092");
        //指定消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "practice-consumer4");
        //设置 offset自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        //自动提交间隔时间
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //key反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerDeserializer");
        //value反序列化
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //创建消费者
        KafkaConsumer<Integer,String> consumer = new KafkaConsumer<Integer,String>(properties);

        consumer.subscribe(Collections.singleton("cyy123"));
        while(true){
            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(100));
            for (ConsumerRecord<Integer, String> record : records) {
                System.out.println(record.key() + " " + record.value() + " -> offset:" + record.offset());
            }
        }
    }
}
