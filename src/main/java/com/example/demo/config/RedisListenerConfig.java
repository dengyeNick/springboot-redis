package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Redis消息监听器容器
 * @author dengye
 *
 */
@Configuration
public class RedisListenerConfig {
	  @Bean
	    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
	        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
	        container.setConnectionFactory(connectionFactory);
	        return container;
	    }
}