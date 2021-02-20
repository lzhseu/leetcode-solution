package 剑指offer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2021/2/19 10:56
 */
public class _57_2_和为s的连续正数序列 {

    /**
     * 双指针：滑动窗口
     */
    public int[][] findContinuousSequence(int target) {

        if(target < 3) {
            return new int[][] {};
        }

        int p1 = 1, p2 = 2;
        int middle = (target + 1) / 2;
        int sum = p1 + p2;

        List<int[]> result = new ArrayList<>();

        while(p1 < p2) {

            if(sum == target) {

                int[] tmp = new int[p2 - p1 + 1];
                for(int i = p1; i <= p2; i++) {
                    tmp[i - p1] = i;
                }
                result.add(tmp);
                sum -= p1;
                p1++;

            } else if(sum > target) {
                sum -= p1;
                p1++;
            } else {
                if(p2 < middle) {
                    p2++;
                    sum += p2;
                } else {
                    break;
                }
            }
        }

        return result.toArray(new int[][] {});
    }


    /**
     * 滑动窗口模板式写法
     */
    public int[][] findContinuousSequence2(int target) {

        if(target < 3) {
            return new int[][] {};
        }

        int p1 = 1, p2 = 2;
        int middle = (target + 1) / 2;
        int sum = p1;

        List<int[]> result = new ArrayList<>();

        while(p1 < p2) {

            // 定义成左闭右开

            sum += p2;
            p2++;
            if(p2 > middle + 1) {
                break;
            }

            while(sum > target) {
                sum -= p1;
                p1++;
            }

            if(sum == target) {
                int[] tmp = new int[p2 - p1];
                for(int i = p1; i < p2; i++) {
                    tmp[i - p1] = i;
                }
                result.add(tmp);
                sum -= p1;
                p1++;
            }

        }

        return result.toArray(new int[][] {});
    }


    @Test
    public void test() {

        findContinuousSequence2(9);
    }
}
