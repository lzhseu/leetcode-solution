package 剑指offer;

import org.junit.Test;

/**
 * @author lzh
 * @date 2021/1/14 21:36
 */
public class _20_表示数值的字符串 {

    /**
     * 这题要考虑全面真的挺费劲的。我自己写不出这么优雅的代码。
     */

    private int p = 0;

    public boolean isNumber(String s) {

        if(s == null || s.length() == 0) {
            return false;
        }

        s = s.trim();

        int len = s.length();

        boolean numeric = scanInteger(s);

        // 判断有没有出现 .
        if(p < len && s.charAt(p) == '.') {

            p++;

            // 下面一行代码用 || 的原因
            // 1. 小数可以没有整数部分，如 .123 表示 0.123
            // 2. 小数点后面可以没有数字，如 123. 表示 123.0
            // 3. 小数点前和小数点后都可以有数字
            numeric = scanUnsignedInteger(s) || numeric;
        }

        // 判断有没有出现 e 或者 E
        if(p < len && (s.charAt(p) == 'e' || s.charAt(p) == 'E')) {

            p++;

            // 下面一行代码使用 && 的原因
            // 1. 当 e 或 E 前面没有数字时，整个串不能当成数字，如 e123
            // 2. 当 e 或 E 后面没有数字时，整个串不能当成数字，如 123E
            numeric = scanInteger(s) && numeric;
        }

        return numeric && p == len;
    }

    /**
     * 扫描带符号的整数
     */
    private boolean scanInteger(String s) {

        if (p >= s.length()) {
            return false;
        }

        if(s.charAt(p) == '+' || s.charAt(p) == '-') {
            p++;
        }

        return scanUnsignedInteger(s);
    }

    /**
     * 扫描正数，存在则返回 true
     */
    private boolean scanUnsignedInteger(String s) {

        final int before = p;

        int len = s.length();

        while(p < len && s.charAt(p) >= '0' && s.charAt(p) <= '9') {
            p++;
        }

        return p > before;
    }

    @Test
    public void test() {

        System.out.println(isNumber("1 2"));
    }
}
