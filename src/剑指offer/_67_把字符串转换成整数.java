package 剑指offer;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/7/11 21:30
 */
public class _67_把字符串转换成整数 {

    /**
     * 思路非常简单，就一次遍历然后判断各种情况即可
     * 但是细节比较多，要非常注意
     * 这版代码写的很糟糕，虽然只用了 2ms，击败 99.91% 用户。但是代码写得很乱。。。
     */
    public int strToInt(String str) {

        if(str == null || str.length() == 0) {
            return 0;
        }

        char[] charArr = str.toCharArray();
        int len = charArr.length;
        int res = 0;
        int start = 0;
        int flag = 0;

        // 找起始位置
        for(int i = 0; i < len; i++) {
            if(charArr[i] != ' ') {
                start = i;
                break;
            }
        }

        // 判断起始位置的值
        if(charArr[start] == '+') {
            flag = 1;
        } else if(charArr[start] == '-') {
            flag = 2;
        } else if(!Character.isDigit(charArr[start])) {
            return 0;
        } else {
            res = charArr[start] - '0';
        }

        // 开始遍历
        int thresNum = Integer.MAX_VALUE / 10;
        for(int i = start+1; i < len; i++) {
            if(Character.isDigit(charArr[i])) {
                int temp = charArr[i] - '0';
                // 判断是否溢出
                if (thresNum < res) {
                    return flag == 2 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                } else if (thresNum == res) {
                    if (flag == 2 && temp >= Integer.MAX_VALUE % 10 + 1) {
                        return Integer.MIN_VALUE;
                    } else if (flag != 2 && temp >= Integer.MAX_VALUE % 10) {
                        return Integer.MAX_VALUE;
                    }
                }

                res = res * 10 + temp;
            } else {
                break;
            }
        }

        return flag == 2 ? -res : res;

    }


    /**
     * 别人的简洁版代码，自叹不如。。。
     */
    public int strToInt2(String str) {
        char[] c = str.trim().toCharArray();
        if(c.length == 0) return 0;
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if(c[0] == '-') sign = -1;
        else if(c[0] != '+') i = 0;
        for(int j = i; j < c.length; j++) {
            if(c[j] < '0' || c[j] > '9') break;
            if(res > bndry || res == bndry && c[j] > '7') return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }


    @Test
    public void test() {
        System.out.println(strToInt("    -412"));
        System.out.println(strToInt("  4193 with words"));
        System.out.println(strToInt(" words and 987"));
        System.out.println(strToInt("-91283472332"));
        System.out.println(strToInt("-2147483649"));
        System.out.println(strToInt("  2147483649"));
        System.out.println(strToInt("  0047483649"));
        System.out.println(strToInt("-2147483647"));
        System.out.println(strToInt("2147483646"));
        System.out.println(strToInt("-2147483648"));

    }
}
