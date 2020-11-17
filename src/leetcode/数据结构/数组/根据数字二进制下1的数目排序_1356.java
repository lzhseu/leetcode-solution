package leetcode.数据结构.数组;

import java.util.*;

/**
 * @author lzh
 * @date 2020/11/6 9:33
 */
public class 根据数字二进制下1的数目排序_1356 {

    /**
     * 更巧妙的方法：权值法
     * 用了 JDK 求 1 的个数
     * 3ms 100%
     */
    public int[] sortByBits3(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.bitCount(arr[i]) * 100000 + arr[i];
        }

        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] % 100000;
        }

        return arr;
    }

    /**
     * 递推预处理求1的个数
     * bit[i] = big[i >> 1] + (1&i)  【 bit[i] 表示数字 i 的二进制 1 的个数 】
     * 9ms 47.58%
     */
    public int[] sortByBits2(int[] arr) {

        List<Integer> list = new ArrayList<>();
        for (int n : arr) {
            list.add(n);
        }

        // 题目限制了 arr[i] <= 10000
        int[] bit = new int[10001];
        for (int i = 0; i < 10001; i++) {
            bit[i] = bit[i >> 1] + (1 & i);
        }

        list.sort((o1, o2) -> {
            if (bit[o1] == bit[o2]) {
                return o1 - o2;
            } else {
                return bit[o1] - bit[o2];
            }
        });

        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }

        return arr;

    }



    /**
     * 自己使用插入排序来写，暴力求1的个数
     * 139ms 5.07%
     *
     * 同样的思路，使用系统快排函数 sort 时间为 20ms
     */
    public int[] sortByBits1(int[] arr) {

        // 插入排序
        for(int i = 1; i < arr.length; i++) {

            int j;
            int tmp = arr[i];

            for(j = i; j > 0 && myCompare(tmp, arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }

            arr[j] = tmp;
        }

        return arr;
    }

    private int myCompare(int a, int b) {

        int na = 0, nb = 0;

        // 2^14 > 10^4
        for(int i = 0; i < 14; i++) {
            if((a & (1 << i)) != 0) {
                na++;
            }
            if((b & (1 << i)) != 0) {
                nb++;
            }
        }

        if(na == nb) {
            return a - b;
        } else {
            return na - nb;
        }
    }
}
