package 剑指offer;


/**
 * @author lzh
 * @date 2021/2/20 18:39
 */
public class _64_求和 {

    public int sumNums(int n) {

        int res = n;
        boolean tmp = n != 0 && (res += sumNums(n - 1)) > 0;
        return res;
    }

}
