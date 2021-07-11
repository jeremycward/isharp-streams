package com.isharp.ploniexfeed.connect;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;
import java.util.function.Supplier;

@Configuration
public class PoloniexFeedSender {
    private static final Logger logger = LoggerFactory.getLogger(PoloniexFeedSender.class);
    @Autowired
    private WebsocketLIstener websocketLIstener = new WebsocketLIstener();

   private final PoloniexRawMessageRecordMessageConverter rawMsgConverter = new PoloniexRawMessageRecordMessageConverter();

   @Bean
   public PoloniexRawMessageRecordMessageConverter getRawMsgConverter() {
        return rawMsgConverter;
    }

    public PoloniexFeedSender() {
        logger.info("Starting up feed sender");
    }


    @Bean
    public Supplier<Message<String>> poloniexRawMessageRecieved() {
        return () -> {
            try {
                String wsmsg = websocketLIstener.getQ().take();
                Message<String> msg = new Message<String>() {
                    @Override
                    public String getPayload() {
                        return wsmsg;
                    }

                    @Override
                    public MessageHeaders getHeaders() {
                        Map<String,Object> headers = Maps.newHashMap();
                        headers.put(KafkaHeaders.MESSAGE_KEY,Long.toString(System.currentTimeMillis()).getBytes());
                        headers.put(MessageHeaders.CONTENT_TYPE,"text/plain");
                        headers.put("spring_json_header_types", "{\"contentType\":\"java.lang.String\"}");
                        return new MessageHeaders(headers);
                    }
                };
                return msg;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
    }

}
