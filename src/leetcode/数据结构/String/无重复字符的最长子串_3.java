package leetcode.数据结构.String;

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
}
