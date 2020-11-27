package leetcode.数据结构.String;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/11/22 18:48
 */
public class 有效的字母异位词_242 {

    /**
     * 方法1：HashMap
     * 17 ms 17.60%    39.5 MB 11.87%
     */
    public boolean isAnagram1(String s, String t) {

        if(s == null && t == null) {
            return true;
        } else if(s == null || t == null) {
            return false;
        }

        int len;
        if((len = s.length()) != t.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>(len);
        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for(char c : t.toCharArray()) {
            Integer count = map.get(c);
            if(count == null) {
                return false;
            } else if(--count == 0) {
                map.remove(c);
            } else {
                map.put(c, count);
            }
        }
        return map.isEmpty();
    }

    /**
     * 方法2：排序
     * 2 ms 99.91%    38.7 MB 78.80%
     */
    public boolean isAnagram2(String s, String t) {

        if(s == null && t == null) {
            return true;
        } else if(s == null || t == null) {
            return false;
        }

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);
        return Arrays.equals(sArr, tArr);
    }
}
