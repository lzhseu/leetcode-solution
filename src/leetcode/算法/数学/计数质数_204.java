package leetcode.算法.数学;

import java.util.Arrays;

/**
 * @author lzh
 * @date 2020/12/3 10:51
 */
public class 计数质数_204 {

    /**
     * 方法1：暴力解
     * 712 ms 5.32%    35.2 MB 94.88%
     */
    public int countPrimes1(int n) {

        int ans = 0;
        for(int i = 2; i < n; i++) {
            if(isPrime(i)) {
                ans++;
            }
        }

        return ans;

    }

    private boolean isPrime(int n) {
        int end = (int) Math.sqrt(n);
        for(int i = 2; i <= end; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 埃氏筛
     * 16 ms 78.05%     37.3 MB 35.05%
     */
    public int countPrimes(int n) {

        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        int ans = 0;
        for(int i = 2; i < n; i++) {

            if(!isPrime[i]) {
                continue;
            }

            ans++;
            if((long) i * i < n) {
                for(int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }

        }
        return ans;
    }
}
