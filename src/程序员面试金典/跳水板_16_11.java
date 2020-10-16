package 程序员面试金典;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lzh
 * @date 2020/7/8 8:45
 */
public class 跳水板_16_11 {

    public int[] divingBoard(int shorter, int longer, int k) {

        if(k == 0) {
            return new int[]{};
        }

        if (shorter == longer) {
            return new int[]{shorter * k};
        }

        int[] res = new int[k+1];
        for(int i = 0; i <= k; i++) {
            res[i] = shorter * (k-i) + longer * i;
        }
        return res;
    }

    public static void main(String[] args) {
        跳水板_16_11 c = new 跳水板_16_11();

        int[] res = c.divingBoard(1,1,3);
        List<Integer> list = Arrays.stream(res).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
}
