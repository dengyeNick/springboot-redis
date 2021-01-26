package com.example.demo.listener;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 监听方法
 * @author dengye
 *
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

	 @Resource
	 private RedisTemplate<String, Object> redisTemplate;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 存在问题
     * redis 的定期检测过期 key 是随机抽取的 ，所以会有过期 key 并没有监听到
     * 这样会造成内存耗尽，需要设置 redis 的淘汰机制
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //获取失效的key
        String expiredKey = message.toString();
        System.out.println(expiredKey);
        //失效的key清空
        Object o = redisTemplate.opsForValue().get(expiredKey);
    }
}