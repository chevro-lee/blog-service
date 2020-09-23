package ink.chevro.redis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Author: Chevro.Lee <br>
 * Description: Redis String操作集
 * Date: Create in  18:51 2019-08-14
 **/
public interface IRedisStringClient {

    /**
     * 添加缓存
     *
     * @param key   key
     * @param value value
     */
    void set(String key, String value);

    /**
     * 添加缓存并设置过期时间
     *
     * @param key     key
     * @param value   value
     * @param timeOut 过期时间
     * @param unit    设置时间的格式，详情见@see java.util.concurrent.TimeUnit
     */
    void set(String key, String value, long timeOut, TimeUnit unit);

    /**
     * 使用value参数覆写key所存储的字符串变量
     *
     * @param key    key
     * @param value  value
     * @param offset value对应的偏移量，从0开始
     */
    void set(String key, String value, long offset);

    /**
     * 设置键值对，如果不存在
     *
     * @param key   key
     * @param value value
     * @return 存在返回false，不存在返回true
     */
    boolean setIfAbsent(String key, String value);

    /**
     * 设置多个键值对
     *
     * @param map key集合
     */
    void multiSet(Map<String, String> map);


    /**
     * 设置多个键值对，如果不存在
     *
     * @param map key集合
     * @return 存在返回false，不存在返回true
     */
    boolean multiSetIfAbsent(Map<String, String> map);

    /**
     * 获取对应key的value
     *
     * @param key 取值的key
     * @return value
     */
    String get(String key);

    /**
     * 设置键值并返回旧值
     *
     * @param key   缓存中存在的key
     * @param value 新value
     * @return 缓存中原来的key对应的value
     */
    String getAndSet(String key, String value);

    /**
     * @param keys key集合
     * @return key集合对应的value集合
     */
    List<String> multiGet(List<String> keys);

    /**
     * 为指定key新增指定整数
     *
     * @param key   key
     * @param delta 新增的整数
     * @return 新增之后的value
     */
    Long increment(String key, long delta);

    /**
     * 为指定key新增指定浮点数
     *
     * @param key   key
     * @param delta 新增浮点数
     * @return 新增之后的value
     */
    Double increment(String key, Double delta);

    /**
     * 将指定value添加到对应key的value的末尾
     *
     * @param key   key
     * @param value 指定value
     * @return 如果key已经存在并且是一个字符串，则该命令将该值追加到字符串的末尾。如果键不存在，则它被创建并设置为空字符串
     */
    Integer append(String key, String value);

    /**
     * 截取key所对应的value字符串
     *
     * @param key   key
     * @param start 截取开始
     * @param end   截取结束
     * @return 截取后的字符串
     */
    String get(String key, long start, long end);

    /**
     * 返回key所对应的value值长度
     *
     * @param key key
     * @return value长度
     */
    Long size(String key);

    /**
     * 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)
     * key键对应的值value对应的ascii码的二进制,在offset的位置(从左向右数)变为value
     * <p>
     * 例如：
     * 'a' 的ASCII码是 97  转换为二进制是：01100001,
     * 'b' 的ASCII码是 98  转换为二进制是：01100010
     * setBit(key,6,true);
     * setBit(key,7,false);
     * 之后缓存中value值将会由a变为b
     * </p>
     *
     * @param key    key
     * @param offset 二进制偏移量
     * @param value  对应的值，取值false或者true
     * @return true or false
     */
    Boolean setBit(String key, long offset, boolean value);

    /**
     * 获取键对应值的ascii码的在offset处位值
     *
     * @param key    key
     * @param offset 二进制偏移量
     * @return true or false
     */
    Boolean getBit(String key, long offset);
}
