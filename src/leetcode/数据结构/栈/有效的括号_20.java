package leetcode.数据结构.栈;

import org.junit.Test;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/8/14 10:35
 */
public class 有效的括号_20 {

    /**
     * 方法一：用栈实现
     */
    public boolean isValid1(String s) {
        if(s == null || s.length() == 0) {
            return true;
        }

        // 方法1：用栈实现  2ms
        Deque<Character> stack = new LinkedList<>();
        int i = 0;

        while(i < s.length()) {
            char c = s.charAt(i);
            Character pc = '(';
            boolean flag = false;
            if(c == ')') {
                pc = '(';
                flag = true;
            } else if(c == '}') {
                pc = '{';
                flag = true;
            } else if(c == ']') {
                pc = '[';
                flag = true;
            }

            if(flag) {
                // 若栈为空，则lc=null
                Character lc = stack.peek();
                if(pc.equals(lc)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }

            i++;
        }

        return stack.isEmpty();
    }

    /**
     * 方法二：用栈实现，并进行优化  2ms
     */
    public boolean isValid2(String s) {

        int len;
        if(s == null || (len = s.length()) == 0) {
            return true;
        }

        if(len % 2 == 1) {
            return false;
        }

        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};

        Deque<Character> stack = new LinkedList<Character>();

        for(int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(pairs.containsKey(c)) {
                if(pairs.get(c).equals(stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }


    @Test
    public void test() {
        System.out.println(isValid1("()"));
        System.out.println(isValid1("()[]{}"));
        System.out.println(isValid1("(([)]"));
        System.out.println(isValid1("{[]}"));
        System.out.println(isValid1("}"));

    }
}
