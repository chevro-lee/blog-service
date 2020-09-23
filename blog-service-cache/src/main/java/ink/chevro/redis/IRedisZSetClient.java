package ink.chevro.redis;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Collection;
import java.util.Set;

/**
 * Author: Chevro.Lee <br>
 * Description: 有序集合操作结果集
 * Date: Create in  11:40 2019-08-15
 **/
public interface IRedisZSetClient {

    /**
     * 新增一个有序集合，存在则更新此有序集合的score
     *
     * @param key   key
     * @param value value
     * @param score score分数，通过score来为集合中的成员从小到大排序
     * @return 新增有序集合，存在返回false，不存在返回true
     */
    Boolean add(String key, String value, double score);

    /**
     * 新增一个有序集合，存在则更新此有序集合的score
     *
     * @param key    key
     * @param tuples 祥见@see org.springframework.data.redis.core.ZSetOperations.TypedTuple
     * @return number
     */
    Long add(String key, Set<ZSetOperations.TypedTuple<Object>> tuples);

    /**
     * 从集合中移除一个或者多个元素
     *
     * @param key    key
     * @param values values
     * @return 返回移除元素的个数
     */
    Long remove(String key, Object... values);

    /**
     * 增加元素的score的值，并返回增加后的值
     *
     * @param key   key
     * @param value value
     * @param delta 增加的值
     * @return 返回增加值后的score
     */
    Double incrementScore(String key, String value, double delta);

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key   key
     * @param value value
     * @return 返回当前成员在集合中的排名情况
     */
    Long rank(String key, String value);

    /**
     * 返回有序集中指定成员的排名，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key   key
     * @param value value
     * @return 返回有序集中指定成员的排名
     */
    Long reverseRank(String key, String value);

    /**
     * 通过索引区间返回指定集合内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key   key
     * @param start 区间开始位
     * @param end   区间结束位
     * @return 返回指定集合指定区间内的成员
     */
    Set<String> range(String key, long start, long end);

    /**
     * 通过索引区间返回有序集合成指定区间内的成员“对象”，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key   key
     * @param start 区间开始位
     * @param end   区间结束位
     * @return 返回指定集合指定区间内的成员对象
     */
    Set<ZSetOperations.TypedTuple<String>> rangeWithScores(String key, long start, long end);

    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key key
     * @param min 分数最小值
     * @param max 分数最大值
     * @return 返回指定集合指定分数区间内的有序集
     */
    Set<String> rangeByScore(String key, double min, double max);

    /**
     * 通过分数返回有序集合指定区间内的成员“对象”，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key key
     * @param min 分数最小值
     * @param max 分数最大值
     * @return 返回指定分数集合的成员对象
     */
    Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(String key, double min, double max);

    /**
     * 通过分数返回有序集合指定区间内的成员，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key    key
     * @param min    分数最小值
     * @param max    分数最大值
     * @param offset 索引开始位
     * @param count  索引结束位
     * @return 指定分数，指定索引区间的有序集合
     */
    Set<String> rangeByScore(String key, double min, double max, long offset, long count);

    /**
     * 通过分数返回有序集合指定区间内的成员对象，并在索引范围内，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key    key
     * @param min    分数最小位
     * @param max    分数最大位
     * @param offset 索引开始位
     * @param count  索引结束位
     * @return 指定分数，指定索引区间的有序集合
     */
    Set<ZSetOperations.TypedTuple<String>> rangeByScoreWithScores(String key, double min, double max, long offset, long count);

    /**
     * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key   key
     * @param start 分数开始位
     * @param end   分数结束位
     * @return 指定分数区间内的有序集合
     */
    Set<String> reverseRange(String key, long start, long end);

    /**
     * 通过索引区间返回有序集合成指定区间内的成员“对象”，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key   key
     * @param start 分数开始位
     * @param end   分数结束位
     * @return 指定分数区间内的有序集合对象
     */
    Set<ZSetOperations.TypedTuple<String>> reverseRangeWithScores(String key, long start, long end);

    /**
     * 与rangeByScore调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key key
     * @param min 分数开始位
     * @param max 分数结束位
     * @return 指定区间有序集合
     */
    Set<String> reverseRangeByScore(String key, double min, double max);

    /**
     * 与rangeByScoreWithScores调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key key
     * @param min 分数开始位
     * @param max 分数结束位
     * @return 指定区间有序集合对象
     */
    Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(String key, double min, double max);

    /**
     * 与rangeByScore调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key    key
     * @param min    分数开始位
     * @param max    分数结束位
     * @param offset 区间开始位
     * @param count  区间结束位
     * @return 指定区间有序集合
     */
    Set<String> reverseRangeByScore(String key, double min, double max, long offset, long count);

    /**
     * 与rangeByScoreWithScores调用方法一样，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key    key
     * @param min    分数最小位
     * @param max    分数最大位
     * @param offset 区间开始位
     * @param count  区间结束位
     * @return 指定区间有序集合对象
     */
    Set<ZSetOperations.TypedTuple<String>> reverseRangeByScoreWithScores(String key, double min, double max, long offset, long count);

    /**
     * 通过分数返回有序集合指定区间的成员个数
     *
     * @param key key
     * @param min 分数最小位
     * @param max 分数最大位
     * @return 指定区间的成员个数
     */
    Long count(String key, double min, double max);

    /**
     * 获得有序集合的成员数
     *
     * @param key key
     * @return 有序集合的成员数
     */
    Long size(String key);

    /**
     * 获取有序集合的成员数，内部调用的就是zCard方法
     *
     * @param key key
     * @return
     */
    Long zCard(String key);

    /**
     * 获取指定成员的score值
     *
     * @param key   key
     * @param value value
     * @return score值
     */
    Double score(String key, String value);

    /**
     * 移除指定索引位置的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key   key
     * @param start 分数开始位
     * @param end   分数结束位
     * @return 移除的个数
     */
    Long removeRange(String key, long start, long end);

    /**
     * 根据指定的score值得范围来移除成员，其中>min && <=max的将被移除
     *
     * @param key key
     * @param min 分数开始位
     * @param max 分数结束
     * @return 被移除的个数
     */
    Long removeRangeByScore(String key, double min, double max);

    /**
     * 计算给定的一个有序集的并集，并存储在新的 destKey中，key相同的话会把score值相加
     *
     * @param key      key1
     * @param otherKey key2
     * @param destKey  新的key
     * @return 返回两个集合的并集
     */
    Long unionAndStore(String key, String otherKey, String destKey);

    /**
     * 计算多个集合的并集，并存储在新的destKey中，key相同的话会把score值相加
     *
     * @param key       key
     * @param otherKeys 其他key集合
     * @param destKey   目标key
     * @return 返回多个集合的并集
     */
    Long unionAndStore(String key, Collection<String> otherKeys, String destKey);

    /**
     * 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
     *
     * @param key      key1
     * @param otherKey key2
     * @param destKey  目标key
     * @return 新的有序集的交集
     */
    Long intersectAndStore(String key, String otherKey, String destKey);

    /**
     * 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 key 中
     *
     * @param key       key
     * @param otherKeys 其他key集合
     * @param destKey   目标key
     * @return 新的有序集的交集
     */
    Long intersectAndStore(String key, Collection<String> otherKeys, String destKey);

    /**
     * 遍历有序集合
     *
     * @param key     key
     * @param options options
     * @return Cursor对象  @code{ZSetOperations.TypedTuple<String> = cursor.next()}
     */
    Cursor<ZSetOperations.TypedTuple<String>> scan(String key, ScanOptions options);
}
