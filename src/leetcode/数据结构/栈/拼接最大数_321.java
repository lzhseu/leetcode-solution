package leetcode.数据结构.栈;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/12/2 14:59
 */
public class 拼接最大数_321 {

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        int len1 = nums1.length, len2 = nums2.length;
        int start = Math.max(0, k - len2), end = Math.min(len1, k);
        int[] result = new int[k];

        for(int k1 = start; k1 <= end; k1++) {
            int k2 = k - k1;
            // 分别求两个子序列
            int[] newSubSequence = merge(maxSubSequenceByStack(nums1, k1), maxSubSequenceByStack(nums2, k2));
            if(compare(newSubSequence, 0, result, 0) > 0) {
                result = newSubSequence;
            }
        }

        return result;
    }

    /**
     * 单调栈求最大子序列
     * 不知道为什么，用我自己的 accept 不了，但是我在 IDE 中是可以的
     */
    private int[] maxSubSequenceByStack(int[] nums, int k) {

        int len = nums.length;
        int[] res = new int[k];
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < len; i++) {

            while (!deque.isEmpty() && deque.size() + len - i > k && deque.peekLast() <= nums[i]) {
                deque.removeLast();
            }

            deque.addLast(nums[i]);
        }

        for (int i = 0; i < k; i++) {
            res[i] = deque.removeFirst();
        }

        return res;
    }

    /**
     * 这是别人的
     */
    private int[] maxSubSequenceByStack2(int[] nums, int k) {

        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;

    }


    /**
     * 动态规划求最大子序列
     * 注意，这道题不适合用动态规划，算法本身没问题。注意是因为 dp 数组存放 int 类型会溢出
     * 除非改为 String[] dp，但这样会更加麻烦，比较大小等需要额外的处理
     */
    private int[] maxSubSequenceByDp(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1] * 10 + nums[i - 1]);
            }
        }

        int max = dp[n][k];
        int[] res = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            res[i] = max % 10;
            max /= 10;
        }

        return res;
    }

    /**
     * 动态规划求最大子序列 —— 降维打击
     */
    private int[] maxSubSequenceByDp2(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = k; j > 0; j--) {
                dp[j] = Math.max(dp[j], dp[j - 1] * 10 + nums[i - 1]);
            }
        }

        int max = dp[k];
        int[] res = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            res[i] = max % 10;
            max /= 10;
        }

        return res;
    }

    private int[] merge(int[] nums1, int[] nums2) {

        int len1 = nums1.length, len2 = nums2.length;
        if (len1 == 0) {
            return nums2;
        } else if (len2 == 0) {
            return nums1;
        }

        int len = len1 + len2;
        int[] res = new int[len];
        int p1 = 0, p2 = 0;
        for (int i = 0; i < len; i++) {
            if (compare(nums1, p1, nums2, p2) > 0) {
                res[i] = nums1[p1++];
            } else {
                res[i] = nums2[p2++];
            }
        }
        return res;
    }

    private int compare(int[] nums1, int index1, int[] nums2, int index2) {
        int len1 = nums1.length, len2 = nums2.length;
        int p1 = index1, p2 = index2;

        while (p1 < len1 && p2 < len2) {
            if (nums1[p1] < nums2[p2]) {
                return -1;
            } else if (nums1[p1] > nums2[p2]) {
                return 1;
            }
            p1++;
            p2++;
        }
        // 返回位数多的
        return (len1 - index1) - (len2 - index2);
    }

    @Test
    public void testMaxSubSequenceByDp2() {
        int[] s1 = maxSubSequenceByDp2(new int[]{9, 1, 2, 5, 8, 3}, 3);
        for (int n : s1) {
            System.out.print(n + " ");
        }
        System.out.println();

        int[] s2 = maxSubSequenceByDp2(new int[]{3, 4, 6, 5}, 2);
        int[] mergeArr = merge(s1, s2);
        for (int n : mergeArr) {
            System.out.print(n);
        }
        System.out.println();
    }

    @Test
    public void testMaxSubSequenceByStack() {
        int[] s1 = maxSubSequenceByStack(new int[]{9, 1, 2, 5, 8, 3}, 4);
        for (int n : s1) {
            System.out.print(n + " ");
        }
        System.out.println();

        int[] s2 = maxSubSequenceByStack(new int[]{3, 4, 6, 5}, 2);
        int[] mergeArr = merge(s1, s2);
        for (int n : mergeArr) {
            System.out.print(n);
        }
        System.out.println();
    }

    @Test
    public void testFinal() {
        int[] nums1 = new int[]{6, 5, 4, 6, 3, 7, 5, 6, 4, 5, 6, 4};
        int[] nums2 = new int[]{8, 8, 6, 0};
        int[] res = maxNumber(nums1, nums2, 16);
        for (int n : res) {
            System.out.print(n + " ");
        }
        System.out.println();

        nums1 = new int[]{5, 5, 1};
        nums2 = new int[]{5, 5, 4};
        res = maxNumber(nums1, nums2, 3);
        for (int n : res) {
            System.out.print(n + " ");
        }
        System.out.println();


//        int[] s1 = maxSubSequenceByDp2(nums1, 12);
//        for (int n : s1) {
//            System.out.print(n + " ");
//        }
//        System.out.println();
//
//        int[] s2 = maxSubSequenceByDp2(nums2, 4);
//        for (int n : s2) {
//            System.out.print(n + " ");
//        }
//        System.out.println();

    }
}
