package com.valy.baselibrary.adpter.sticky;

/**
 * @Auther: valy
 * @datetime: 2020/3/31
 * @desc:
 */
public interface CacheInterface<T> {

    /**
     * 加入缓存
     * @param position
     * @param t
     */
    void put(int position, T t);

    /**
     * 从缓存中获取
     * @param position
     * @return
     */
    T get(int position);

    /**
     * 移除
     * @param position
     */
    void remove(int position);

    /**
     * 清空缓存
     */
    void clean();

}