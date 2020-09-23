package ink.chevro.redis;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  11:46 2019-08-15
 **/
public interface IRedisLockClient {

    /**
     * 该加锁方法仅针对单实例 Redis 可实现分布式加锁
     * 对于 Redis 集群则无法使用
     *
     * @param lockKey  加锁name
     * @param clientId 客户端唯一ID，可以使用UUID
     * @param seconds  超时时间，超时自动释放锁
     * @return true or false
     */
    boolean tryLock(String lockKey, String clientId, long seconds);

    /**
     * 释放锁
     *
     * @param lockKey  锁名
     * @param clientId 客户端唯一ID
     * @return true or false
     */
    boolean releaseLock(String lockKey, String clientId);
}
