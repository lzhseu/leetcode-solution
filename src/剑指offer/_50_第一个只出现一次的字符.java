package 剑指offer;

import org.junit.Test;

/**
 * @author lzh
 * @date 2021/2/17 19:41
 */
public class _50_第一个只出现一次的字符 {

    /**
     * 4 ms 99.31%    8.8 MB 40.85%
     */
    public char firstUniqChar(String s) {

        if(s == null || s.length() == 0) {
            return ' ';
        }

        int[] existed = new int[26];
        char[] sarr = s.toCharArray();

        for(char c : sarr) {
            existed[c - 'a']++;
        }

        for(char c : sarr) {
            if(existed[c - 'a'] == 1) {
                return c;
            }
        }

        return ' ';
    }


    /**
     * 滑动窗口
     * 9 ms 74.03%    38.8 MB  56.05%
     */
    public char firstUniqChar2(String s) {

        int len;
        if(s == null || (len = s.length()) == 0) {
            return ' ';
        }

        int[] existed = new int[128];

        int lp = 0, rp = 0;

        while(rp < len) {

            char c = s.charAt(rp);
            rp++;
            existed[c]++;

            char d = s.charAt(lp);
            while(existed[d] > 1) {
                lp++;
                if(lp >= rp) {
                    break;
                }
                d = s.charAt(lp);
            }

        }

        return lp < len ? s.charAt(lp) : ' ';
    }

    @Test
    public void test() {

        System.out.println(firstUniqChar2("abaccdeff"));

    }
}
