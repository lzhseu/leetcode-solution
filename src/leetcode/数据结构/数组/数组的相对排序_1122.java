package leetcode.数据结构.数组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/11/14 10:25
 */
public class 数组的相对排序_1122 {

    /**
     * 使用自定义排序函数
     * 3ms
     */
    public int[] relativeSortArray3(int[] arr1, int[] arr2) {

        int len1, len2;
        if(arr1 == null || (len1 = arr1.length) == 0) {
            return new int[] {};
        } else if(arr2 == null || (len2 = arr2.length) == 0) {
            return arr1;
        }

        Map<Integer, Integer> arr2Map = new HashMap<>(len2);
        for(int i = 0; i < len2; i++) {
            arr2Map.put(arr2[i], i);
        }

        Integer[] arr = new Integer[len1];
        for(int i = 0; i < len1; i++) {
            arr[i] = arr1[i];
        }

        Arrays.sort(arr, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                if(arr2Map.containsKey(o1) && arr2Map.containsKey(o2)) {
                    return arr2Map.get(o1) - arr2Map.get(o2);
                }

                if(arr2Map.containsKey(o1)) {
                    return -1;
                }

                if(arr2Map.containsKey(o2)) {
                    return 1;
                }

                return o1 - o2;
            }
        });

        for(int i = 0; i < len1; i++) {
            arr1[i] = arr[i];
        }

        return arr1;

    }

    /**
     * 使用数组作为映射
     * 好像 1ms 就顶天了
     * 1ms 53.98%    38.5M 58.02%
     */
    public int[] relativeSortArray2(int[] arr1, int[] arr2) {

        if(arr1 == null || arr1.length == 0) {
            return new int[] {};
        } else if(arr2 == null || arr2.length == 0) {
            return arr1;
        }

        // 题目给出了范围
        int[] mapArr = new int[1001];

        for(int n : arr1) {
            mapArr[n]++;
        }

        int[] resArr = new int[arr1.length];
        int p = 0;

        // 先排arr2中的元素
        for(int n2 : arr2) {

            int count = mapArr[n2];
            Arrays.fill(resArr, p, p + count, n2);
            p += count;
            mapArr[n2] = 0;
        }

        // 对剩余的元素进行排序
        for(int i = 0; i < 1001; i++) {
            if(mapArr[i] > 0) {
                Arrays.fill(resArr, p, p + mapArr[i], i);
                p += mapArr[i];
            }
        }

        return resArr;

    }

    /**
     * 暴力解法
     * 4ms 30.08%    37.1M 96.51%
     * 在对剩余元素进行排序时，如果使用系统 Arrays.sort，时间将变为 2ms
     */
    public int[] relativeSortArray1(int[] arr1, int[] arr2) {

        if(arr1 == null || arr1.length == 0) {
            return new int[] {};
        } else if(arr2 == null || arr2.length == 0) {
            return arr1;
        }

        // 最暴力的解法
        int pl = 0, pr = 0;

        // 先排arr2中的元素
        for(int i = 0; i < arr2.length; i++) {

            int curr = arr2[i];
            pr = pl;

            while(pr < arr1.length) {

                if(arr1[pr] == curr) {
                    swap(arr1, pl, pr);
                    pl++;
                }

                pr++;
            }
        }

        // 对剩余的元素进行排序
        // 使用自己写的插入排序
        int j;
        for(int i = pl + 1; i < arr1.length; i++) {

            int tmp = arr1[i];
            for(j = i; j > pl && tmp < arr1[j - 1]; j--) {
                arr1[j] = arr1[j - 1];
            }
            arr1[j] = tmp;

        }

        // 使用JDK
        if (pl < arr1.length) {
            Arrays.sort(arr1, pl, arr1.length);
        }

        return arr1;

    }

    private void swap(int[] arr, int idx1, int idx2) {
        int tmp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }
}
