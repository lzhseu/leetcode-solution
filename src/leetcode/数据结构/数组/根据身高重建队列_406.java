package leetcode.数据结构.数组;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lzh
 * @date 2020/11/16 15:18
 */
public class 根据身高重建队列_406 {

    public int[][] reconstructQueue(int[][] people) {

        // 先按照身高降序排序，如果身高相同，则比较 k，k 小的在前面
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);

        List<int[]> list = new LinkedList<>();
        for(int[] arr : people) {
            list.add(arr[1], arr);
        }

        return list.toArray(new int[0][2]);
    }
}
