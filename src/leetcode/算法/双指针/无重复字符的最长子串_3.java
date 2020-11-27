package leetcode.算法.双指针;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/9/29 15:44
 */
public class 无重复字符的最长子串_3 {

    public int lengthOfLongestSubstring(String s) {

        int len;
        if(s == null || (len = s.length()) == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();

        int pos = 0;
        int max = 0;

        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(map.containsKey(c) && map.get(c) >= pos) {
                pos = map.get(c) + 1;
            }
            map.put(c, i);
            if(i - pos + 1 > max) {
                max = i - pos + 1;
            }
        }

        return max;
    }

    /**
     * 滑动窗口算法
     * 2 ms 100.00%    38.3 MB 96.49%
     */
    public int lengthOfLongestSubstring_frame(String s) {

        int len;
        if(s == null || (len = s.length()) == 0) {
            return 0;
        }

        int[] window = new int[128];

        int lp = 0, rp = 0;
        int max = 0;

        while(rp < len) {
            char c = s.charAt(rp);
            rp++;
            window[c]++;

            // 缩小
            while(window[c] > 1) {
                char d = s.charAt(lp);
                lp++;
                window[d]--;
            }

            max = Math.max(max, rp - lp);
        }

        return max;
    }

    @Test
    public void test() {
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstring_frame(str));
    }
}
