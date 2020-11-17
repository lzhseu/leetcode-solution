package leetcode.数据结构.栈;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/11/16 16:13
 */
public class 移除k位数字_402 {

    /**
     * 解法一：k*O(N)
     * 5ms 93.97%    38.5M 88.43%
     */
    public String removeKdigits(String num, int k) {

        StringBuilder sb = new StringBuilder(num);

        for(int i = 0; i < k; i++) {

            int j;
            int size = sb.length();
            for(j = 0; j < size - 1; j++) {
                if(sb.charAt(j) > sb.charAt(j + 1)) {
                    sb.deleteCharAt(j);
                    // 删除前导0
                    while(0 < sb.length() && sb.charAt(0) == '0') {
                        sb.deleteCharAt(0);
                    }

                    break;
                }
            }
            // 如果到最后的元素还没有删除
            if(j == size - 1) {
                sb.deleteCharAt(j);
            }
        }

        if(sb.length() == 0) {
            return "0";
        }

        return sb.toString();
    }


    /**
     * 使用栈  这种方法是一类题目（单调栈）
     * 5ms    38.6M
     */
    public String removeKdigits2(String num, int k) {

        // 解法二：使用栈
        int len;
        if(num == null || (len = num.length()) == 0) {
            throw new RuntimeException("Invalid input");
        } else if(k == 0) {
            return num;
        }

        Deque<Character> stack = new LinkedList<>();
        stack.push(num.charAt(0));
        int deleteCount = 0;

        int i;
        for(i = 1; i < len; i++) {
            char c = num.charAt(i);
            while(!stack.isEmpty() && deleteCount < k && c < stack.peek()) {
                stack.pop();
                deleteCount++;
            }

            // 如果删除的个数到了
            if(deleteCount == k) {
                break;
            }

            stack.push(c);
        }

        // 如果没有删除 k 个元素
        while(deleteCount < k) {
            stack.pop();
            deleteCount++;
        }

        // 处理栈中剩余的元素
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }

        if(i < len) {
            sb.append(num.substring(i));
        }

        // 删除前导0
        while(sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        if(sb.length() == 0) {
            return "0";
        }

        return sb.toString();

    }

    @Test
    public void test() {
        String num1 = "1173";
        System.out.println(removeKdigits(num1, 2));
        System.out.println(removeKdigits2(num1, 2));
    }

    @Test
    public void test1() {
        Deque<Integer> stack = new LinkedList<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        int size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(stack.removeLast());
        }
    }
}
