package leetcode.数据结构.String;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2020/9/29 10:35
 */
public class Z字形变换_6 {

    /**
     * 参考官方题解的另一种解法：按行排序   虽然时间和空间都比较差，但挺有意思的
     * 7ms 73,30%  39.6 21.3%
     */
    public String convert2(String s, int numRows) {
        if(s == null || s.length() == 0) {
            return s;
        } else if(numRows == 1) {
            return s;
        }

        char[] sarr = s.toCharArray();
        int total = sarr.length;

        List<StringBuilder> list = new ArrayList<>(numRows);
        for(int i = 0; i < Math.min(numRows, total); i++) {
            list.add(new StringBuilder());
        }

        boolean goingDown = false;
        int curRow = 0;

        for(char c : sarr) {
            list.get(curRow).append(c);
            if(curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder res = new StringBuilder();
        for(StringBuilder sb : list) {
            res.append(sb);
        }
        return res.toString();
    }

    /**
     * 参照官方的“找规律”，其实是按行访问
     * 4ms 87.67%  38.8M 92.73%
     */
    public String convert(String s, int numRows) {
        if(s == null || s.length() == 0) {
            return s;
        } else if(numRows == 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        char[] sarr = s.toCharArray();
        int total = sarr.length;

        int circle = 2 * numRows - 2;

        for(int i = 0; i < numRows; i++) {
            for(int j = 0; i + j < total; j += circle) {
                sb.append(sarr[i + j]);
                if(i != 0 && i != numRows - 1 && j + circle - i < total) {
                    sb.append(sarr[j + circle - i]);
                }
            }
        }

        return sb.toString();

    }


    /**
     * 找规律，看了官方题解，才发现自己找规律的实在太菜了，虽然算法基本思路一样
     * 4ms 87.67%  38.9M 89.28%
     */
    public String convert1(String s, int numRows) {
        if(s == null || s.length() == 0) {
            return s;
        } else if(numRows == 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        char[] sarr = s.toCharArray();
        int total = sarr.length;

        int circle = 2 * numRows - 2;

        for(int i = 0; i < numRows; i++) {

            if(i >= total) {
                break;
            }
            sb.append(sarr[i]);

            int idx = i;
            int evenIncr = 2 * i;
            int oddIncr = circle - 2 * i;

            int j = 1;
            while(true) {
                int incr = (i == 0 || i == numRows - 1) ? circle : j % 2 == 0 ? evenIncr : oddIncr;
                idx += incr;
                if(idx >= total) {
                    break;
                }
                sb.append(sarr[idx]);
                j++;
            }
        }

        return sb.toString();

    }

    @Test
    public void test() {
        System.out.println(convert("LEETCODEISHIRING", 1));
        System.out.println(convert("LEETCODEISHIRING", 3));
        System.out.println(convert("LEETCODEISHIRING", 4));
        System.out.println(convert("A", 4));
    }
}
