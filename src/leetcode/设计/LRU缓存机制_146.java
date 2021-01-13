package leetcode.设计;

import java.util.LinkedHashMap;

/**
 * @author lzh
 * @date 2020/12/21 16:39
 */
public class LRU缓存机制_146 {

    static class LRUCache {

        private LinkedHashMap<Integer, Integer> cache;

        private int capacity;

        public LRUCache(int capacity) {
            cache = new LinkedHashMap<>(capacity);
            this.capacity = capacity;
        }

        public int get(int key) {
            if(!cache.containsKey(key)) {
                return -1;
            }

            return makeRecently(key);
        }

        public void put(int key, int value) {

            // 先判断是否有这个 key
            if(cache.containsKey(key)) {

                // 修改 key 的值
                cache.put(key, value);

                // 使之最新
                makeRecently(key);

                return;
            }

            // 如果缓存满了
            if(cache.size() == capacity) {
                // 删除首部数据
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }

            // 添加新数据
            cache.put(key, value);
        }

        private int makeRecently(int key) {
            int value = cache.remove(key);
            cache.put(key, value);
            return value;
        }

    }

    /**
     * 这个快得多
     * 因为 accessOrder 为 true 时，LinkedHashMap 本身就是用 最近最少使用算法
     */
    class LRUCache2 {

        private LinkedHashMap<Integer, Integer> cache;

        private int capacity;

        public LRUCache2(int capacity) {
            cache = new LinkedHashMap<>(capacity, (float) 0.75, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            if(!cache.containsKey(key)) {
                return -1;
            }

            return cache.get(key);
        }

        public void put(int key, int value) {

            // 先判断是否有这个 key
            if(cache.containsKey(key)) {

                // 修改 key 的值
                cache.put(key, value);

                return;
            }

            // 如果缓存满了
            if(cache.size() == capacity) {
                // 删除首部数据
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }

            // 添加新数据
            cache.put(key, value);
        }

    }
}
