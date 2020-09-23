package ink.chevro.redis.impl;

import exception.RpcException;
import ink.chevro.redis.IRedisStringClient;
import org.springframework.stereotype.Component;
import ink.chevro.redis.BaseRedisClient;
import result.RspCode;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author: Chevro.Lee <br>
 * Description:
 * Date: Create in  11:44 2019-08-15
 **/
@Component
public class RedisStringClient extends BaseRedisClient implements IRedisStringClient {

    @Override
    public void set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit unit) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public void set(String key, String value, long offset) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, offset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public boolean setIfAbsent(String key, String value) {
        boolean flag;
        try {
            //noinspection ConstantConditions
            flag = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return flag;
    }

    @Override
    public void multiSet(Map<String, String> map) {
        try {
            stringRedisTemplate.opsForValue().multiSet(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
    }

    @Override
    public boolean multiSetIfAbsent(Map<String, String> map) {
        boolean flag;
        try {
            //noinspection ConstantConditions
            flag = stringRedisTemplate.opsForValue().multiSetIfAbsent(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return flag;
    }

    @Override
    public String get(String key) {
        String value;
        try {
            value = stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return value;
    }

    @Override
    public String getAndSet(String key, String value) {
        String oldValue;
        try {
            oldValue = stringRedisTemplate.opsForValue().getAndSet(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return oldValue;
    }

    @Override
    public List<String> multiGet(List<String> keys) {
        List<String> values;
        try {
            values = stringRedisTemplate.opsForValue().multiGet(keys);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return values;
    }

    @Override
    public Long increment(String key, long delta) {
        Long value;
        try {
            value = stringRedisTemplate.opsForValue().increment(key,delta);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return value;
    }

    @Override
    public Double increment(String key, Double delta) {
        Double value;
        try {
            value = stringRedisTemplate.opsForValue().increment(key,delta);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return value;
    }

    @Override
    public Integer append(String key, String value) {
        Integer result;
        try {
            result = stringRedisTemplate.opsForValue().append(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return result;
    }

    @Override
    public String get(String key, long start, long end) {
        String result;
        try {
            result = stringRedisTemplate.opsForValue().get(key,start,end);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return result;
    }

    @Override
    public Long size(String key) {
        Long value;
        try {
            value = stringRedisTemplate.opsForValue().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return value;
    }

    @Override
    public Boolean setBit(String key, long offset, boolean value) {
        Boolean result;
        try {
            result = stringRedisTemplate.opsForValue().setBit(key,offset,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return result;
    }

    @Override
    public Boolean getBit(String key, long offset) {
        Boolean result;
        try {
            result = stringRedisTemplate.opsForValue().getBit(key,offset);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcException(RspCode.RPC_ERROR.code(), RspCode.RPC_ERROR.msg());
        }
        return result;
    }
}
