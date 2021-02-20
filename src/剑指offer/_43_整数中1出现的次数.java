package 剑指offer;

import org.junit.Test;

/**
 * @author lzh
 * @date 2021/2/17 12:05
 */
public class _43_整数中1出现的次数 {

    /**
     * （自己没想到）
     */
    public int countDigitOne(int n) {

        int weight = 1, res = 0;
        int high = n / 10, curr = n % 10, low = 0;

        while(high > 0 || curr > 0) {

            if(curr == 0) {
                res += high * weight;
            } else if(curr == 1) {
                res += high * weight + low + 1;
            } else {
                res += (high + 1) * weight;
            }

            low += curr * weight;
            curr = high % 10;
            high /= 10;
            weight *= 10;

        }

        return res;
    }

    @Test
    public void test() {
        System.out.println(countDigitOne(1234));
    }
}
