package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/20 20:36
 */
public class _66_构建乘积数组 {


    /**
     * 写法一：通俗易懂
     *
     * 2 ms 78.80%    50.7 MB 97.42%
     */
    public int[] constructArr1(int[] a) {

        int len;
        if(a == null || (len = a.length) == 0) {
            return a;
        }

        int[] left = new int[len];
        int[] right = new int[len];

        left[0] = 1;
        right[len - 1] = 1;

        for(int i = 1; i < len; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }

        for(int i = len - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }

        for(int i = 0; i < len; i++) {
            left[i] *= right[i];
        }

        return left;
    }


    /**
     * 优化了以下写法，省去一个 for 循环和一个数组的空间
     *
     * 1 ms 100.00%    50.8 MB 95.38%
     */
    public int[] constructArr2(int[] a) {

        int len;
        if(a == null || (len = a.length) == 0) {
            return a;
        }

        int[] ans = new int[len];

        ans[0] = 1;
        int tmp = 1;

        for(int i = 1; i < len; i++) {
            ans[i] = ans[i - 1] * a[i - 1];
        }

        for(int i = len - 2; i >= 0; i--) {
            tmp *= a[i + 1];
            ans[i] *= tmp;
        }

        return ans;
    }
}
