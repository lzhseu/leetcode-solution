package leetcode.算法.回溯;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/8/26 21:18
 */
public class 电话号码的字母组合_17 {

    public List<String> letterCombinations(String digits) {

        List<String> resList = new ArrayList<>();
        if(digits == null || digits.length() == 0) {
            return resList;
        }
        // 这里也可以用数组
        // String[] letters = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Map<Character, String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");

        helper(digits, 0, map, resList, new StringBuilder());
        return resList;
    }

    private void helper(String digits, int idx, Map<Character, String> map, List<String> resList, StringBuilder sb) {

        if(digits.length() == idx) {
            resList.add(sb.toString());
            return;
        }

        String letters = map.get(digits.charAt(idx));
        int count = letters.length();
        for(int i = 0; i < count; i++) {
            sb.append(letters.charAt(i));
            helper(digits, idx + 1, map, resList, sb);
            sb.deleteCharAt(sb.length()-1);
        }

    }
}
