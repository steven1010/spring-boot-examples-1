package com.xq.spstudy.kafka;

import org.apache.kafka.common.utils.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.xq.spstudy.controller.HelloWorldController;

@Component
public class KafkaProduce {
	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);
	
	@Autowired
    private KafkaTemplate<Bytes, Bytes> kafkaTemplate;
	
	public String kafkaSend()
	{
		String topicAc="xq.topic";
		String content="this is xq send to kafka string";
		
		kafkaTemplate.partitionsFor(topicAc);
		sendData(topicAc,content.getBytes());
		return "kafka send success";
	}
	
	private void sendData(String topic, byte[] bytes) {
		ListenableFuture<SendResult<Bytes, Bytes>> future = kafkaTemplate.send(topic, Bytes.wrap(bytes));
		future.addCallback(new ListenableFutureCallback<SendResult<Bytes, Bytes>>() {
			@Override
			public void onFailure(Throwable ex) {
				LOGGER.error("Fail to send message", ex);
			}

			@Override
			public void onSuccess(SendResult<Bytes, Bytes> result) {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Send data successfully. topic:{}, partitions:{}, offset:{}", topic,
							result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
				}
			}
		});
	}
}
