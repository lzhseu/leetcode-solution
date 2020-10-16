package leetcode.算法.数学;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author lzh
 * @date 2020/8/21 10:59
 */
public class 整数反转_7 {
    /**
     * 方法一：效率不高，先转成字符串，再转回int
     * 4ms 16.55%   37.3M 11.92%
     */
    public int reverse1(int x) {

        if(x < 10 && x > -10) {
            return x;
        }

        boolean isPositive = (x > 0);
        int xx = Math.abs(x);

        StringBuilder sb = new StringBuilder();
        String intStr = sb.append(xx).reverse().toString();

        String POS_THRES = String.valueOf(Integer.MAX_VALUE);
        String NEG_THRES = String.valueOf(Integer.MIN_VALUE).substring(1);

        if(intStr.length() > POS_THRES.length()) {
            return 0;
        } else if(intStr.length() < POS_THRES.length()) {
            int tmp = Integer.valueOf(intStr);
            return isPositive ? tmp : -tmp;
        } else {
            if(isPositive) {
                return intStr.compareTo(POS_THRES) > 0 ? 0 : Integer.valueOf(intStr);
            } else {
                return intStr.compareTo(NEG_THRES) > 0 ? 0 : -Integer.valueOf(intStr);
            }
        }
    }

    /**
     * 方法二：数学方法，垃圾编码
     * 3ms 24%   37.2M 22.44%
     */
    public int reverse2(int x) {

        if(x < 10 && x > -10) {
            return x;
        }

        Deque<Integer> xlist = new LinkedList<>();

        while(x != 0) {
            xlist.add(x % 10);
            x /= 10;
        }

        while(xlist.element() == 0) {
            xlist.poll();
        }

        int comNum = x > 0 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        if(xlist.size() >= 10) {
            int i = 9;
            for(int xn : xlist) {
                int tmp = comNum / (int)Math.pow(10, i);
                tmp %= 10;
                if(Math.abs(xn) > Math.abs(tmp)) {
                    return 0;
                } else if(Math.abs(xn) < Math.abs(tmp)) {
                    break;
                }
                i--;
            }
        }

        int res = 0;

        for(int i = xlist.size()-1; i >= 0; i--) {
            res += (int)(xlist.poll() * Math.pow(10, i));
        }
        return res;
    }

    /**
     * 方法三：数学方法，但是科学编码
     * 1ms 100%  36.9M 69.36%
     */
    public int reverse3(int x) {
        int res = 0;
        while(x != 0) {
            int pop = x % 10;
            x /= 10;
            if(res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            } else if(res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            res = res * 10 + pop;
        }
        return res;
    }


    @Test
    public void test() {
        System.out.println(reverse1(87654321));
        System.out.println(reverse1(-87654321));
        System.out.println(reverse1(-1234567890));
        System.out.println(reverse1(1234567890));
        System.out.println(reverse1(-1234567893));
        System.out.println(reverse1(1234567893));

        System.out.println(reverse2(87654321));
        System.out.println(reverse2(-87654321));
        System.out.println(reverse2(-1234567890));
        System.out.println(reverse2(1234567890));
        System.out.println(reverse2(-1234567893));
        System.out.println(reverse2(1234567893));
        System.out.println(reverse2(-2147483412));
    }
}
