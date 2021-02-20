package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/14 9:45
 */
public class _14_2_剪绳子II {

    /**
     * 这道题与剪绳子的唯一区别就是「大数」
     * 需要大数求余
     */
    public int cuttingRope(int n) {
        if(n <= 3) {
            return n - 1;
        }
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        for(int a = n / 3 - 1; a > 0; a /= 2) {
            if(a % 2 == 1) {
                rem = (rem * x) % p;
            }
            x = (x * x) % p;
        }
        if(b == 0) {
            return (int)(rem * 3 % p);
        }
        if(b == 1) {
            return (int)(rem * 4 % p);
        }
        return (int)(rem * 6 % p);
    }

    // 求 (x^a) % p —— 快速幂求余
    public int remainder(int x, int a, int p) {
        int rem = 1;
        while(a > 0) {
            if(a % 2 == 1) {
                rem = (rem * x) % p;
            }
            x = (int)(Math.pow(x, 2) % p);
            a /= 2;
        }
        return rem;
    }
}
