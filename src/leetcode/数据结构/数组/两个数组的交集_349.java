package leetcode.数据结构.数组;

import java.util.*;

/**
 * @author lzh
 * @date 2020/11/2 20:01
 */
public class 两个数组的交集_349 {

    /**
     * initial version 哈希表
     * 时间复杂度 O(m + n)
     * 3ms 95.82%    38.4M 94.71%
     *
     */
    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set = new HashSet<>(nums1.length);

        for(int num : nums1) {
            set.add(num);
        }


        int len = Math.min(nums1.length, nums2.length);
        int[] res = new int[len];
        int count = 0;

        for(int num : nums2) {
            if(set.contains(num)) {
                res[count++] = num;
                set.remove(num);
            }
        }

        return Arrays.copyOf(res, count);
    }

    /**
     * 排序 ＋ 双指针
     * 时间复杂度 O(mlogm+nlogn)
     * 3ms
     */
    public int[] intersection2(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int len1 = nums1.length, len2 = nums2.length;

        int count = 0, p1 = 0, p2 = 0;
        int[] res = new int[Math.min(len1, len2)];

        while(p1 < len1 && p2 < len2) {
            if(nums1[p1] == nums2[p2]) {
                if(count == 0 || res[count - 1] != nums1[p1]) {
                    res[count++] = nums1[p1];
                }
                ++p1;
                ++p2;
            } else if(nums1[p1] < nums2[p2]) {
                ++p1;
            } else {
                ++p2;
            }
        }

        return Arrays.copyOf(res, count);
    }
}
