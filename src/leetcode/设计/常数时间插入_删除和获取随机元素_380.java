package leetcode.设计;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author lzh
 * @date 2020/11/24 10:14
 */
public class 常数时间插入_删除和获取随机元素_380 {

    /**
     * 12 ms 83.83%    43.1 MB 87.84%
     */
    static class RandomizedSet {

        private ArrayList<Integer> nums;
        private Map<Integer, Integer> valToIndex;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            nums = new ArrayList<>();
            valToIndex = new HashMap<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if(valToIndex.containsKey(val)) {
                return false;
            }
            nums.add(val);
            valToIndex.put(val, nums.size() - 1);
            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            Integer index = valToIndex.get(val);
            if(index == null) {
                return false;
            }
            int last = nums.get(nums.size() - 1);
            nums.set(index, last);
            valToIndex.put(last, index);
            nums.remove(nums.size() - 1);
            valToIndex.remove(val);
            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int index = new Random().nextInt(nums.size());
            return nums.get(index);
        }
    }

    @Test
    public void test() {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.remove(0);
        randomizedSet.remove(0);
        randomizedSet.insert(0);
        randomizedSet.getRandom();
        randomizedSet.remove(0);
        randomizedSet.insert(0);
    }

}
