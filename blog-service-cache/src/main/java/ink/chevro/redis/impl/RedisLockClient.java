package ink.chevro.redis.impl;

import ink.chevro.redis.IRedisLockClient;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;
import ink.chevro.redis.BaseRedisClient;
import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  16:10 2019-08-15
 **/
@Component
public class RedisLockClient extends BaseRedisClient implements IRedisLockClient {

    private static final Long RELEASE_SUCCESS = 1L;
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "EX";
    private static final String RELEASE_LOCK_SCRIPT = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

    @Override
    public boolean tryLock(String lockKey, String clientId, long seconds) {
        return (boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            String result = jedis.set(lockKey, clientId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, seconds);
            if (LOCK_SUCCESS.equals(result)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        });
    }

    @Override
    public boolean releaseLock(String lockKey, String clientId) {
        return (boolean) redisTemplate.execute((RedisCallback<Boolean>) redisConnection -> {
            Jedis jedis = (Jedis) redisConnection.getNativeConnection();
            Object result = jedis.eval(RELEASE_LOCK_SCRIPT, Collections.singletonList(lockKey),
                    Collections.singletonList(clientId));
            if (RELEASE_SUCCESS.equals(result)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        });
    }
}
