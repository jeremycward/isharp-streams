package com.isharp.ploniexfeed.connect;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.lang.reflect.Type;

public class PoloniexRawMessageRecordMessageConverter implements RecordMessageConverter {
    @Override
    public Message<?> toMessage(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer, Type payloadType) {
        throw new UnsupportedOperationException("do not support conversions this way round");
    }

    @Override
    public ProducerRecord<?, ?> fromMessage(Message<?> message, String defaultTopic) {
        MessageHeaders headers = message.getHeaders();
        return new ProducerRecord<String,String>(defaultTopic,message.getPayload().toString());
    }
}
