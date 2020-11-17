package leetcode.数据结构.数组;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/11/3 10:42
 */
public class 有效的山脉数组_941 {

    /**
     * 不优雅版本
     * 2ms 41.65%
     */
    public boolean validMountainArray1(int[] A) {

        int len;
        if(A == null || (len = A.length) < 3) {
            return false;
        }

        int lp = 0, rp = len - 1;

        while(lp < len - 1) {
            if(A[lp] == A[lp + 1]) {
                return false;
            } else if(A[lp] < A[lp + 1]) {
                lp++;
            } else {
                break;
            }
        }

        while(rp > 0) {
            if(A[rp] == A[rp - 1]) {
                return false;
            } else if(A[rp] < A[rp - 1]) {
                rp--;
            } else {
                break;
            }
        }

        if(lp == len - 1 || rp == 0) {
            return false;
        }

        return lp == rp;
    }

    /**
     * 优雅版本
     * 1ms 100%
     */
    public boolean validMountainArray(int[] A) {

        int len;
        if(A == null || (len = A.length) < 3) {
            return false;
        }

        int lp = 0, rp = len - 1;
        while(lp + 1 < len && A[lp] < A[lp + 1]) lp++;
        while(rp - 1 >= 0 && A[rp] < A[rp - 1]) rp--;

        return lp < len - 1 && rp > 0 && lp == rp;
    }

    @Test
    public void test() {
        System.out.println(validMountainArray(new int[]{3, 5, 5}));
        System.out.println(validMountainArray(new int[]{0, 3, 2, 1}));
        System.out.println(validMountainArray(new int[]{0, 2, 3, 4, 5, 2, 1, 0}));
        System.out.println(validMountainArray(new int[]{0, 2, 3, 3, 5, 2, 1, 0}));
        System.out.println(validMountainArray(new int[]{2, 0, 2}));
        System.out.println(validMountainArray(new int[]{0, 1, 2, 3, 4, 5}));
    }
}
