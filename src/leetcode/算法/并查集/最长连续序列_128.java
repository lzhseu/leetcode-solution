package leetcode.算法.并查集;

import java.util.*;

/**
 * @author lzh
 * @date 2020/12/17 20:14
 */
public class 最长连续序列_128 {

    /**
     * 做法1：排序
     */
    public int longestConsecutive1(int[] nums) {

        int len = nums.length;
        if(len < 2) {
            return len;
        }

        Arrays.sort(nums);
        int ans = 0;
        int tmp = 1;
        for(int i = 1; i < len; i++) {
            int diff = nums[i] - nums[i - 1];
            if(diff == 0) {
                continue;
            } else if(diff == 1) {
                tmp++;
            } else {
                ans = Math.max(ans, tmp);
                tmp = 1;
            }
        }
        return Math.max(ans, tmp);
    }

    /**
     * 哈希
     */
    public int longestConsecutive2(int[] nums) {

        if(nums.length < 2) {
            return nums.length;
        }

        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            set.add(num);
        }
        int ans = 1;

        for(int num : set) {
            if(set.contains(num - 1)) {
                continue;
            }
            int currNum = num;
            int tmp = 1;
            while(set.contains(num + 1)) {
                tmp++;
                num++;
            }
            ans = Math.max(ans, tmp);
        }

        return ans;

    }


    /**
     * 哈希表 + 动态规划
     */
    public int longestConsecutive3(int[] nums) {

        if(nums.length < 2) {
            return nums.length;
        }

        int ans = 1;

        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            if(!map.containsKey(num)) {
                int left = map.get(num - 1) == null ? 0 : map.get(num - 1);
                int right = map.get(num + 1) == null ? 0 : map.get(num + 1);
                int curr = left + right + 1;

                ans = Math.max(ans, curr);

                map.put(num, curr);
                map.put(num - left, curr);
                map.put(num + right, curr);
            }
        }

        return ans;

    }

    /**
     * 并查集
     */
    public int longestConsecutive4(int[] nums) {

        UnionFind uf = new UnionFind(nums);

        for(int num : nums) {
            uf.union(num, num + 1);
        }

        int ans = 0;
        for(int num : nums) {
            ans = Math.max(ans, uf.find(num) - num + 1);
        }
        return ans;
    }

    static class UnionFind {

        private Map<Integer, Integer> parent = new HashMap<>();

        public UnionFind(int[] nums) {
            for(int num : nums) {
                parent.put(num, num);
            }
        }

        public boolean union(int x, int y) {
            Integer rootX = find(x);
            Integer rootY = find(y);
            if(rootX == null || rootY == null) {
                return false;
            }
            if(rootX.equals(rootY)) {
                return false;
            }
            parent.put(rootX, rootY);
            return true;
        }

        public Integer find(int x) {

            if(!parent.containsKey(x)) {
                return null;
            }

            while(x != parent.get(x)) {
                int grandpa = parent.get(parent.get(x));
                parent.put(x, grandpa);
                x = grandpa;
            }

            return x;
        }
    }
}
