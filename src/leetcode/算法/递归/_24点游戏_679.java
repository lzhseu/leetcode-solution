package leetcode.算法.递归;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2020/8/22 14:46
 */
public class _24点游戏_679 {

    /**
     * 所有方法均使用 递归/回溯 的算法，但不同实现，不同编码具有的效率不同
     */

    private static final int ADD = 0;
    private static final int MUL = 1;
    private static final int SUB = 2;
    private static final int DIV = 3;

    public boolean judgePoint24(int[] nums) {

        List<Double> list = new ArrayList<>(4);
        for(int n : nums) {
            list.add((double)n);
        }

        return recursive(list);
    }

    /**
     * 实现一：这种实现算了太多无用的过程
     * 11ms 25.78%     39.6 MB 61.70%
     */
    private boolean recursive1(List<Double> list) {

        int size = list.size();
        if(size == 1) {
            return Math.abs(list.get(0) - 24) <= 0.000001;
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {

                // 两个数不能相同
                if(i == j) {
                    continue;
                }

                double n1 = list.get(i);
                double n2 = list.get(j);
                boolean isValid = false;

                List<Double> list2 = new ArrayList<>();
                for(int k = 0; k < size; k++) {
                    if(k != i && k != j) {
                        list2.add(list.get(k));
                    }
                }

                // +
                list2.add(n1 + n2);
                isValid = isValid || recursive1(list2);
                // -
                list2.remove(list2.size() - 1);
                list2.add(n1 - n2);
                isValid = isValid || recursive1(list2);
                // *
                list2.remove(list2.size() - 1);
                list2.add(n1 * n2);
                isValid = isValid || recursive1(list2);
                // /
                if(n2 != 0) {
                    list2.remove(list2.size() - 1);
                    list2.add(n1 / n2);
                    isValid = isValid || recursive1(list2);
                }

                if(isValid) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 实现二：
     * 4ms 84.57%   39.9M 19.51%
     */
    private boolean recursive(List<Double> list) {

        int size = list.size();
        if(size == 0) {
            return false;
        }
        if(size == 1) {
            return Math.abs(list.get(0) - 24) <= 0.000001;
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {

                // 两个数不能相同
                if(i == j) {
                    continue;
                }

                double n1 = list.get(i);
                double n2 = list.get(j);

                List<Double> list2 = new ArrayList<>();
                for(int k = 0; k < size; k++) {
                    if(k != i && k != j) {
                        list2.add(list.get(k));
                    }
                }

                for(int k = 0; k < 4; k++) {
                    // 加法和乘法满足交换律，所以不用计算两次
                    if(k < 2 && i > j) {
                        continue;
                    }

                    if(k == ADD) {
                        list2.add(n1 + n2);
                    } else if(k == MUL) {
                        list2.add(n1 * n2);
                    } else if(k == SUB) {
                        list2.add(n1 - n2);
                    } else {
                        if(Math.abs(n2) >= 0.000001) {
                            list2.add(n1 / n2);
                        } else {
                            continue;
                        }
                    }

                    if(recursive(list2)) {
                        return true;
                    }
                    list2.remove(list2.size() - 1);
                }
            }
        }
        return false;
    }

    @Test
    public void test() {
        int[] nums = {1,7,8,8};
        int[] nums2 = {1,2,1,2};
        int[] nums3 = {1,2,1,8};
        int[] nums4 = {3,2,1,1};
        System.out.println(judgePoint24(nums4));
    }
}
