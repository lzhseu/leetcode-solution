package leetcode.算法.哈希;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author lzh
 * @date 2020/12/16 11:28
 */
public class 单词规律_290 {

    public boolean wordPattern(String pattern, String s) {

        if(pattern == null && s == null) {
            return true;
        } else if(pattern == null || s == null) {
            return false;
        }

        String[] strArr = s.split(" ");
        int len = strArr.length;
        if(len != pattern.length()) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        for(int i = 0; i < len; i++) {
            char key = pattern.charAt(i);
            if(map.containsKey(key)) {

                if(!map.get(key).equals(strArr[i])) {
                    return false;
                }

            } else if(set.contains(strArr[i])) {
                return false;
            } else {
                map.put(key, strArr[i]);
                set.add(strArr[i]);
            }
        }

        return true;

    }
}
