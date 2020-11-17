package leetcode.算法.双指针;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/11/3 9:40
 */
public class 最小覆盖子串_76 {

    /**
     * 双指针，滑动窗口。参考别人题解（好好学习）
     * 2ms 100%    38.7M 94.48%
     */
    public String minWindow(String s, String t) {
        int slen, tlen;
        if(s == null || s == "" || t == null || t == "" || (slen = s.length()) < (tlen = t.length())) {
            return "";
        }

        // 注意：
        // (1) t 是可能包含重复字符的

        int lp = 0, rp = 0;
        int count = 0;
        int minCount = slen;
        String res = "";

        // 存储 t 中的字符极其个数，用于快速判断某个字符是否在 t 中
        int[] need = new int[128];
        // 维护窗口中的字符
        int[] window = new int[128];

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        for(char c : tArr) {
            need[c]++;
        }

        while(rp < slen) {

            char c = sArr[rp];
            if(++window[c] <= need[c]) {
                count++;
            }

            // 已经包含T中所有
            if(count == tlen) {
                // 可以移动 lp
                while(lp < rp && window[sArr[lp]] > need[sArr[lp]]) {
                    window[sArr[lp]]--;
                    lp++;
                }

                // 更新最小子串
                if(rp - lp + 1 <= minCount) {
                    minCount = rp - lp + 1;
                    res = s.substring(lp, rp + 1);
                }

                // lp 再向右移动，使之不满足
                window[sArr[lp]]--;
                lp++;
                count--;
            }

            // 移动右指针
            rp++;
        }

        return res;

    }

}
