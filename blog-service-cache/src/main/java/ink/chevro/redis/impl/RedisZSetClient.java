package ink.chevro.redis.impl;

import exception.RpcException;
import ink.chevro.redis.IRedisZSetClient;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import ink.chevro.redis.BaseRedisClient;
import result.RspCode;

import java.util.Collection;
import java.util.Set;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  15:16 2019-08-20
 **/
@Component
public class RedisZSetClient extends BaseRedisClient implements IRedisZSetClient {

    @Override
    public Boolean add(String key, String value, double score) {
        try {
            return redisTemplate.opsForZSet().add(key, value, score);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long add(String key, Set<ZSetOperations.TypedTuple<Object>> tuples) {
        try {
            return redisTemplate.opsForZSet().add(key, tuples);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long remove(String key, Object... values) {
        try {
            return redisTemplate.opsForZSet().remove(key, values);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Double incrementScore(String key, String value, double delta) {
        try {
            return redisTemplate.opsForZSet().incrementScore(key, value, delta);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long rank(String key, String value) {
        try {
            return redisTemplate.opsForZSet().rank(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long reverseRank(String key, String value) {
        try {
            return redisTemplate.opsForZSet().reverseRank(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<String> range(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> rangeWithScores(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().rangeWithScores(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<String> rangeByScore(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<String> rangeByScore(String key, double min, double max, long offset, long count) {
        try {
            return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        try {
            return redisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, count);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<String> reverseRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().reverseRange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> reverseRangeWithScores(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<String> reverseRangeByScore(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<String> reverseRangeByScore(String key, double min, double max, long offset, long count) {
        try {
            return redisTemplate.opsForZSet().reverseRangeByScore(key, min, max, offset, count);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(String key, double min, double max, long offset, long count) {
        try {
            return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long count(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().count(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long size(String key) {
        try {
              return redisTemplate.opsForZSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long zCard(String key) {
        try {
            return redisTemplate.opsForZSet().zCard(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Double score(String key, String value) {
        try {
            return redisTemplate.opsForZSet().score(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long removeRange(String key, long start, long end) {
        try {
            return redisTemplate.opsForZSet().removeRange(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long removeRangeByScore(String key, double min, double max) {
        try {
            return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long unionAndStore(String key, String otherKey, String destKey) {
        try {
            return redisTemplate.opsForZSet().unionAndStore(key, otherKey, destKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long unionAndStore(String key, Collection<String> otherKeys, String destKey) {
        try {
            return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long intersectAndStore(String key, String otherKey, String destKey) {
        try {
            return redisTemplate.opsForZSet().intersectAndStore(key, otherKey, destKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey) {
        try {
            return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public Cursor<ZSetOperations.TypedTuple<String>> scan(String key, ScanOptions options) {
        try {
            return redisTemplate.opsForZSet().scan(key, ScanOptions.NONE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }
}