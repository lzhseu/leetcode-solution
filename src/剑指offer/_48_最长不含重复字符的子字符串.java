package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/17 17:16
 */
public class _48_最长不含重复字符的子字符串 {

    /**
     * 滑动窗口法（双指针）
     */
    public int lengthOfLongestSubstring(String s) {

        int len;
        if(s == null || (len = s.length()) == 0) {
            return 0;
        }

        int[] window = new int[128];

        // 定义成 [lp, rp)
        int lp = 0, rp = 0;

        int result = 0;

        while(rp < len) {

            char c = s.charAt(rp);
            rp++;
            window[c]++;

            while(window[c] > 1) {

                char d = s.charAt(lp);
                lp++;
                window[d]--;
            }

            result = Math.max(result, rp - lp);
        }

        return result;
    }
}
