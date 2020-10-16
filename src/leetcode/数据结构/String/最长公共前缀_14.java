package leetcode.数据结构.String;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/7/8 9:22
 */
public class 最长公共前缀_14 {

    public String longestCommonPrefix(String[] strs) {

        // 先检查 strs 元素的值是否合法
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (String s : strs) {
            if (s == null || s.equals("")) {
                return "";
            }
        }

        Arrays.sort(strs);

        String s1 = strs[0];
        String s2 = strs[strs.length-1];
        int len1 = s1.length();
        int len2 = s2.length();
        int len = Math.min(len1, len2);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                sb.append(s1.charAt(i));
            } else {
                break;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {

        最长公共前缀_14 c = new 最长公共前缀_14();

        String[] strs = {"flower","flow","flight"};

        System.out.println(c.longestCommonPrefix(strs));
    }
}
