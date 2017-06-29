package com.xq.spstudy.kafka;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    /**
     * Receive AP data {@link ConsumerRecord} from kafka's topic partitions {@Link TopicPartition }.
     *
     * @param list
     */
    @KafkaListener(group = "${spring.kafka.consumer.group-id}",
            topics = "xq.topic",
            containerFactory = "kafkaListenerContainerFactory")
    public void onAPMessage(List<ConsumerRecord<Bytes, Bytes>> list) {
    	list.parallelStream().map(record -> {
            // decode ac from google protocol buffer
            byte[] protobufBytes = record.value().get();
            System.out.println("=================\bkafka receive:"+protobufBytes);
            
            return "aaa";
        });
    }

}