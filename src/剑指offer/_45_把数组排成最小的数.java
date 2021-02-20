package 剑指offer;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2021/2/17 16:17
 */
public class _45_把数组排成最小的数 {

    /**
     * 方法一：定义排序规则
     *
     * 12 ms 15.07%    38.3 MB  19.18%
     */
    public String minNumber1(int[] nums) {

        int len = nums.length;
        String[] arr = new String[len];

        for(int i = 0; i < len; i++) {
            arr[i] = nums[i] + "";
        }

        Arrays.sort(arr, (s1, s2) -> (s1 + s2).compareTo(s2 + s1));

        StringBuilder sb = new StringBuilder();

        for(String str : arr) {
            sb.append(str);
        }

        return sb.toString();
    }


}
