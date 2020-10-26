package leetcode.数据结构.String;

import org.junit.Test;

import java.util.*;

/**
 * @author lzh
 * @date 2020/10/20 19:41
 */
public class 字母异位词分组_49 {

    public List<List<String>> groupAnagrams(String[] strs) {

        // 方法1：每个字符串排序作为 key
        if(strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String key = String.valueOf(arr);
            if(!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }
}
