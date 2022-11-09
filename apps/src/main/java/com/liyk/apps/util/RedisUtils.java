package com.liyk.apps.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author liyongkui
 * @version 1.0
 * @description: TODO
 * @date 2022/11/8 17:49
 */

public class RedisUtils {
    private RedisUtils() {
    }

    @SuppressWarnings("unchecked")
    private static RedisTemplate<String, Object> redisTemplate = SpringUtils.getBean("redisTemplate", RedisTemplate.class);

    /**
     * 设置有效时间
     * 单位默认秒
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(final String key, final long timeout) {

        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(final String key, final long timeout, final TimeUnit unit) {

        Boolean ret = redisTemplate.expire(key, timeout, unit);
        return ret != null && ret;
    }

    /**
     * 删除单个key
     *
     * @param key 键
     * @return true=删除成功；false=删除失败
     */
    public static boolean del(final String key) {

        Boolean ret = redisTemplate.delete(key);
        return ret != null && ret;
    }

    /**
     * 删除多个key
     *
     * @param keys 键集合
     * @return 成功删除的个数
     */
    public static long del(final Collection<String> keys) {

        Long ret = redisTemplate.delete(keys);
        return ret == null ? 0 : ret;
    }

    /**
     * 存入普通对象
     *
     * @param key   Redis键
     * @param value 值
     */
    public static void set(final String key, final Object value) {

        redisTemplate.opsForValue().set(key, value);
    }

    // 存储普通对象操作

    /**
     * 存入普通对象
     *
     * @param key     键
     * @param value   值
     * @param timeout 有效期，单位秒
     */
    public static void set(final String key, final Object value, final long timeout) {

        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取普通对象
     *
     * @param key 键
     * @return 对象
     */
    public static Object get(final String key) {

        return redisTemplate.opsForValue().get(key);
    }

    // 存储Hash操作

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param filed Hash filed键
     * @param value 值
     */
    public static void hPut(final String key, final String filed, final Object value) {

        redisTemplate.opsForHash().put(key, filed, value);
    }

    /**
     * 往Hash中存入多个数据
     *
     * @param key      Redis键
     * @param filedMap Hash键值对
     */
    public static void hPutAll(final String key, final Map<String, Object> filedMap) {

        redisTemplate.opsForHash().putAll(key, filedMap);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key   Redis键
     * @param filed Hash filed键
     * @return Hash中的对象
     */
    public static Object hGet(final String key, final String filed) {

        return redisTemplate.opsForHash().get(key, filed);
    }

    /**
     * 获取多个Hash中的数据
     *
     * @param key    Redis键
     * @param fileds Hash filed键集合
     * @return Hash对象集合
     */
    public static List<Object> hMultiGet(final String key, final Collection<Object> fileds) {

        return redisTemplate.opsForHash().multiGet(key, fileds);
    }

    // 存储Set相关操作

    /**
     * 往Set中存入数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 存入的个数
     */
    public static long sSet(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 删除Set中的数据
     *
     * @param key    Redis键
     * @param values 值
     * @return 移除的个数
     */
    public static long sDel(final String key, final Object... values) {
        Long count = redisTemplate.opsForSet().remove(key, values);
        return count == null ? 0 : count;
    }

    // 存储List相关操作

    /**
     * 往List左侧中存入数据
     *
     * @param key   Redis键
     * @param value 数据
     * @return 存入的个数
     */
    public static long lPush(final String key, final Object value) {
        Long count = redisTemplate.opsForList().leftPush(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 往List右侧中存入数据
     *
     * @param key   Redis键
     * @param value 数据
     * @return 存入的个数
     */
    public static long rPush(final String key, final Object value) {
        Long count = redisTemplate.opsForList().rightPush(key, value);
        return count == null ? 0 : count;
    }

    /**
     * 往List中左侧存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public static long lPushAll(final String key, final Collection<Object> values) {
        Long count = redisTemplate.opsForList().leftPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往List中左侧存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public static long lPushAll(final String key, final Object... values) {
        Long count = redisTemplate.opsForList().leftPushAll(key, values);
        return count == null ? 0 : count;
    }

    /**
     * 往List中右侧存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public static long rPushAll(final String key, final Collection<Object> values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }


    /**
     * 往List中右侧存入多个数据
     *
     * @param key    Redis键
     * @param values 多个数据
     * @return 存入的个数
     */
    public static long rPushAll(final String key, final Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        return count == null ? 0 : count;
    }


    /**
     * 从List中获取begin到end之间的元素
     *
     * @param key   Redis键
     * @param start 开始位置
     * @param end   结束位置（start=0，end=-1表示获取全部元素）
     * @return List对象
     */
    public static List<Object> listGetRange(final String key, final int start, final int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    /**
     * 从List左侧弹出数据
     *
     * @param key Redis键
     * @return 对象
     */
    public static Object listGetL(final String key) {
        return redisTemplate.opsForList().leftPop(key);
    }


    /**
     * 从List右侧弹出数据
     *
     * @param key Redis键
     * @return 对象
     */
    public static Object listGetR(final String key) {
        return redisTemplate.opsForList().rightPop(key);
    }
}
