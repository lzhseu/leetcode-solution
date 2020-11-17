package leetcode.数据结构.栈;

import org.junit.Test;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author lzh
 * @date 2020/11/16 19:28
 */
public class 去除重复字母_316 {

    /**
     * 单调栈
     * 3ms 98.07%    38.1 MB 86.49%
     */
    public String removeDuplicateLetters1(String s) {

        // 使用数组来存储每个字符出现的次数（当然也可以使用map）
        int[] map = new int[26];

        char[] sArr = s.toCharArray();
        for(char c : sArr) {
            map[c - 'a']++;
        }

        Deque<Character> deque = new LinkedList<>();
        boolean[] isInDeque = new boolean[26];

        for(char c : sArr) {

            char top;
            while(!deque.isEmpty() && (top = deque.peekLast()) >= c && map[top - 'a'] > 1) {
                if(isInDeque[c - 'a']) {
                    map[c - 'a']--;
                    break;
                }
                map[top - 'a']--;
                isInDeque[top - 'a'] = false;
                deque.removeLast();
            }

            if(!isInDeque[c - 'a']) {
                deque.addLast(c);
                isInDeque[c - 'a'] = true;
            }

        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.append(deque.removeFirst());
        }

        return sb.toString();
    }


    /**
     * 稍微修改了编码，使之更加简洁
     */
    public String removeDuplicateLetters(String s) {

        // 使用数组来存储每个字符出现的次数（当然也可以使用map）
        int[] map = new int[26];

        char[] sArr = s.toCharArray();
        for(char c : sArr) {
            map[c - 'a']++;
        }

        Deque<Character> deque = new LinkedList<>();
        boolean[] isInDeque = new boolean[26];

        for(char c : sArr) {

            map[c - 'a']--;
            if (isInDeque[c - 'a']) {
                continue;
            }

            char top;
            while(!deque.isEmpty() && (top = deque.peekLast()) >= c && map[top - 'a'] > 0) {
                isInDeque[top - 'a'] = false;
                deque.removeLast();
            }

            deque.addLast(c);
            isInDeque[c - 'a'] = true;
        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.append(deque.removeFirst());
        }

        return sb.toString();
    }

    @Test
    public void test() {
        String s1 = "cbabc";
        System.out.println(removeDuplicateLetters(s1));

        String s2 = "cbacdcbc";
        System.out.println(removeDuplicateLetters(s2));

        String s3 = "abacb";
        System.out.println(removeDuplicateLetters(s3));

        String s4 = "bbcaac";
        System.out.println(removeDuplicateLetters(s4));
    }
}
