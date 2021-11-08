package com.example.hw10;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class Listener {
    private final Logger logger = Logger.getLogger(Listener.class.getName());

    @StreamListener(ConsumerChannels.BROADCASTS)
    public void broadcasted(String message) {
        logger.info(message);
    }
}
