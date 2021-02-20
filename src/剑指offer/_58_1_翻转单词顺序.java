package 剑指offer;

import org.junit.Test;

/**
 * @author lzh
 * @date 2021/2/19 19:42
 */
public class _58_1_翻转单词顺序 {


    /**
     * 解法一：使用 String 的 API — trim 和 split
     *
     * 1 ms 100.00%    8.6 MB 16.78%
     */
    public String reverseWords1(String s) {

        if(s == null || s.length() == 0) {
            return s;
        }

        s = s.trim();
        String[] arr = s.split(" ");

        StringBuilder sb = new StringBuilder();

        for(int i = arr.length - 1; i >= 0; i--) {
            String str = arr[i].trim();
            if(!str.equals("")) {
                sb.append(str).append(" ");
            }
        }

        return sb.toString().trim();
    }


    /**
     * 解法二：双指针
     *
     * 3 ms 66.64%    38.1 MB 88.39%
     */
    public String reverseWords2(String s) {

        if(s == null || s.length() == 0) {
            return s;
        }

        s = s.trim();

        int p1 = s.length() - 1;
        int p2 = p1;
        StringBuilder sb = new StringBuilder();

        while(p1 >= 0) {

            while(p1 >= 0 && s.charAt(p1) != ' ') {
                p1--;
            }

            sb.append(s, p1 + 1, p2 + 1).append(" ");

            while(p1 >= 0 && s.charAt(p1) == ' ') {
                p1--;
            }

            p2 = p1;
        }

        return sb.toString().trim();
    }


    /**
     * 解法三：（剑指的方法）两次翻转，先翻转整个字符串，再翻转每个单词
     * C++ 用这种方法有优势，因为可以通过操作指针来交换字符串中的任意两个字符
     * Java 用这种方法则很鸡肋，因为 Java 的 String 是不可变类型，需要变成数组 char[] 来操作，最后还需要再转换为 String 对象
     * 而且这种方法不适合「中间有多个空格需要只保留一个空格」的要求，时间复杂度也高，此处不写出来
     */


    @Test
    public void test() {

        String s = "  abc   d ".trim();
        System.out.println(s);
        String[] arr = s.split(" ");
        System.out.println(arr.length);
        for (String str : arr) {
            System.out.println(str);
        }
    }
}
