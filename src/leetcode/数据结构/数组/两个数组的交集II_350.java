package leetcode.数据结构.数组;

import org.junit.Test;

import java.util.*;

/**
 * @author lzh
 * @date 2020/7/13 9:16
 */
public class 两个数组的交集II_350 {

    /**
     * 使用哈希表  6ms 22.31%
     */
    public int[] intersect_hash1(int[] nums1, int[] nums2) {

        List<Integer> list = new ArrayList<>();

        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[] {};
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++) {
            Integer value;
            if((value = map.get(nums1[i])) != null) {
                map.put(nums1[i], ++value);
            } else {
                map.put(nums1[i], 1);
            }
        }

        for(int i = 0; i < nums2.length; i++) {
            Integer value;
            if((value = map.get(nums2[i])) != null && value > 0) {
                list.add(nums2[i]);
                map.put(nums2[i], --value);
            }
        }

        return list.stream().mapToInt(Integer::valueOf).toArray();

    }

    /**
     * 就改了两句 3ms 87.23%
     * 数组和 List 的互转原来这么消耗性能
     */
    public int[] intersect_hash2(int[] nums1, int[] nums2) {

        if(nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[] {};
        }

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++) {
            Integer value;
            if((value = map.get(nums1[i])) != null) {
                map.put(nums1[i], ++value);
            } else {
                map.put(nums1[i], 1);
            }
        }

        int[] res = new int[nums2.length];
        int index = 0;
        for(int i = 0; i < nums2.length; i++) {
            Integer value;
            if((value = map.get(nums2[i])) != null && value > 0) {
                res[index++] = nums2[i];
                map.put(nums2[i], --value);
            }
        }

        return Arrays.copyOfRange(res, 0, index);

    }

    /**
     * 官方的双指针法
     */
    public int[] intersect_doublePointer(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[Math.min(length1, length2)];
        int index1 = 0, index2 = 0, index = 0;
        while (index1 < length1 && index2 < length2) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                intersection[index] = nums1[index1];
                index1++;
                index2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }

    @Test
    public void test() {

    }
}
