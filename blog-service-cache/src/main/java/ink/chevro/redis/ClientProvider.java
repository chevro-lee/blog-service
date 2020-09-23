package ink.chevro.redis;

import ink.chevro.redis.impl.RedisLockClient;
import ink.chevro.redis.impl.RedisStringClient;
import ink.chevro.redis.impl.RedisZSetClient;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  18:54 2019-08-14
 **/
public class ClientProvider {

    private static RedisStringClient stringClient  = new RedisStringClient();

    private static RedisZSetClient zSetClient = new RedisZSetClient();

    private static RedisLockClient lockClient = new RedisLockClient();

    public static RedisStringClient redisStringClient() {
        return stringClient;
    }

    public static RedisZSetClient redisZSetClient() {
        return zSetClient;
    }

    public static RedisLockClient redisLockClient() {
        return lockClient;
    }
}
