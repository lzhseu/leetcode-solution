package leetcode.数据结构.栈;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author lzh
 * @date 2021/1/7 19:22
 */
public class 字符串解码_394 {

    /**
     * 栈 —— 初始版本
     * 2 ms 47.24%    36.3 MB 90.95%
     */
    public String decodeString1(String s) {

        Deque<Character> deque = new ArrayDeque<>();

        StringBuilder resSb = new StringBuilder();
        StringBuilder tempSb = new StringBuilder();

        char[] arr = s.toCharArray();

        for(char c : arr) {

            if(isNum(c) || isLeft(c)) {

                deque.addLast(c);

            } else if(isRight(c)) {

                // 提取[]中的字符
                while(!deque.isEmpty()) {

                    char d = deque.removeLast();
                    if(isLeft(d)) {
                        break;
                    }
                    tempSb.append(d);
                }

                // 提取循环次数
                int times = 0;
                int k = 1;
                while(!deque.isEmpty()) {

                    char d = deque.peekLast();
                    if(!isNum(d)) {
                        break;
                    }

                    d -= '0';
                    times = times + d * k;
                    k *= 10;

                    deque.removeLast();
                }


                String tempStr = tempSb.reverse().toString();
                if(deque.isEmpty()) {
                    for(int i = 0; i < times; i++) {
                        resSb.append(tempStr);
                    }

                } else {
                    for(int i = 0; i < times; i++) {
                        for(int j = 0; j < tempStr.length(); j++) {
                            deque.addLast(tempStr.charAt(j));
                        }
                    }
                }

                tempSb.delete(0, tempSb.length());

            } else {

                if(deque.isEmpty()) {
                    resSb.append(c);
                } else {
                    deque.addLast(c);
                }
            }
        }

        return resSb.toString();
    }


    /**
     * 大佬的栈
     * 1 ms 87.05%    36.2 MB 96.46%
     */
    public String decodeString2(String s) {

        Deque<String> strDeque = new ArrayDeque<>();
        Deque<Integer> intDeque = new ArrayDeque<>();

        StringBuilder resSb = new StringBuilder();
        StringBuilder tempSb = new StringBuilder();

        char[] arr = s.toCharArray();

        int times = 0;

        for(char c : arr) {

            if(isNum(c)) {

                times = times * 10 + (c - '0');

            } else if(isLeft(c)) {

                strDeque.addLast(resSb.toString());
                intDeque.addLast(times);

                times = 0;
                resSb.delete(0, resSb.length());

            } else if(isRight(c)) {

                int currTimes = intDeque.removeLast();
                String lastStr = strDeque.removeLast();
                String currStr = resSb.toString();

                for(int i = 0; i < currTimes; i++) {
                    tempSb.append(currStr);
                }

                resSb.delete(0, resSb.length());
                resSb.append(lastStr).append(tempSb.toString());
                tempSb.delete(0, tempSb.length());

            } else {

                resSb.append(c);
            }
        }

        return resSb.toString();
    }


    /**
     * dfs
     * 1 ms 87.05%    36.5 MB 74.50%
     */
    public String decodeString3(String s) {

        return dfs(s);
    }

    private int ptr = 0;

    private String dfs(String s) {

        if(ptr == s.length() || isRight(s.charAt(ptr))) {
            return "";
        }

        int times = 0;
        String res = "";
        char c = s.charAt(ptr);

        if(isNum(c)) {
            // 获得数字
            while(ptr < s.length() && isNum(s.charAt((ptr)))) {
                times = times * 10 + (s.charAt(ptr++) - '0');
            }

            // 过滤 [
            ptr++;

            // 继续解析
            String str = dfs(s);

            // 过滤 ]
            ptr++;

            // 得到一个 [] 的字符串
            for(int i = 0; i < times; i++) {
                res += str;
            }

        } else {
            res = String.valueOf(c);
            ptr++;
        }

        return res + dfs(s);
    }



    private boolean isNum(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isLeft(char c) {
        return c == '[';
    }

    private boolean isRight(char c) {
        return c == ']';
    }

}
