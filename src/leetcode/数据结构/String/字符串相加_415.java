package leetcode.数据结构.String;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/8/13 11:46
 */
public class 字符串相加_415 {

    /**
     * 思路一样，代码简洁，使用双指针
     * 3ms 慢了 1ms
     */
    public String addStrings1(String num1, String num2) {

        char[] num1Arr = num1.toCharArray();
        char[] num2Arr = num2.toCharArray();
        int p1 = num1Arr.length - 1;
        int p2 = num2Arr.length - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while(p1 >= 0 || p2 >= 0 || carry != 0) {
            char n1 = p1 >= 0 ? num1Arr[p1] : '0';
            char n2 = p2 >= 0 ? num2Arr[p2] : '0';
            int sum = n1 - '0' + n2 - '0' + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            p1--;
            p2--;
        }

        return sb.reverse().toString();
    }

    /**
     * 2ms
     */
    public String addStrings2(String num1, String num2) {

        char[] longArr = num1.length() >= num2.length() ? num1.toCharArray() : num2.toCharArray();
        char[] shortArr = num1.length() >= num2.length() ? num2.toCharArray() : num1.toCharArray();

        StringBuilder sb = new StringBuilder();

        int len = shortArr.length;
        int lenDiff = longArr.length - shortArr.length;
        int carry = 0;

        for(int i = len-1; i >= 0; i--) {
            int tmp = longArr[i + lenDiff] - '0' + shortArr[i] - '0' + carry;
            sb.append(tmp % 10);
            carry = tmp / 10;
        }


        for(int i = lenDiff-1; i >= 0; i--) {
            int tmp = longArr[i] - '0' + carry;
            sb.append(tmp % 10);
            carry = tmp / 10;
        }

        if(carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }

    @Test
    public void test() {
        System.out.println(addStrings1("98", "9"));
        System.out.println(addStrings1("1", "1"));
        Character c1 = 'a';
        Character c2 = 'a';
        char c3 = 'a';
        System.out.println(c1 == c3);
    }
}
