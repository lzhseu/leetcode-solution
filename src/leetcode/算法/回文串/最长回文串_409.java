package leetcode.算法.回文串;

/**
 * @author lzh
 * @date 2020/7/8 11:21
 */
public class 最长回文串_409 {

    /**
     * 方法一：借鉴位图算法
     * A~Za~z 共有 52 个字符，所以定义一个大小为 52 的数组，初始化全 0。
     * 数组元素为 0 表示没有与该字符相同的字符被记录，为 1 则表示已经有一个相同的字符遍历过。
     * 遍历字符串，得到每一个字符，并计算字符在数组中的位置（采用 ASCII 码作为映射），
     * 当字符对应的位置为 0，则把该位置设为 1；
     * 当该字符对应的位置为 1，则把该位置设为0，并将结果 +2
     * 还需要设置一个变量 num，用于记录当字符串遍历结束后数组中的元素个数，若 >0 ，则结果 +1
     */
    public int longestPalindrome1(String s) {

        if(s == null || "".equals(s)) {
            return 0;
        }

        int[] ascii = new int[52];
        int res = 0;
        int num = 0;

        for(int i = 0; i < s.length(); i++) {

            //用字符的 ASCII 码作映射 [a-z] --> [97~122]，[A-Z] --> [65~90]
            int idx = s.charAt(i);
            if(idx < 97) {
                idx -= 65;
            } else {
                idx -= (97 - 26);
            }

            if(ascii[idx] == 1) {
                ascii[idx] = 0;
                res+=2;
                num--;
            } else {
                ascii[idx] = 1;
                num++;
            }
        }

        if(num > 0) {
            res++;
        }
        return res;
    }

    /**
     * 方法二：统计各个字符出现的字数，偶数直接相加，奇数-1相加，最后再+1
     */
    public int longestPalindrome2(String s) {

        int[] count = new int[128];
        for (char c: s.toCharArray()) {
            count[c]++;
        }

        int ans = 0;
        for (int v: count) {
            ans += v / 2 * 2;
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;

    }


}
