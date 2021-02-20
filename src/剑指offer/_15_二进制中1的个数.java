package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/14 10:18
 */
public class _15_二进制中1的个数 {

    /**
     * 做法1：原数右移
     */
    public int hammingWeight1(int n) {

        int count = 0;

        // 注意这里的判断条件要是 != 而不能是 >
        // 因为 int 有可能是负数
        while(n != 0) {
            count += (n & 1);
            n = n >>> 1;
        }

        return count;
    }

    /**
     * 做法2：1 左移
     */
    public int hammingWeight2(int n) {

        int count = 0;
        int flag = 1;

        while(flag != 0) {
            if((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;
        }

        return count;
    }

    /**
     * 做法3：最高效的，消除最右边的 1
     */
    public int hammingWeight3(int n) {

        int count = 0;
        while(n != 0) {
            count++;
            n = n & (n - 1);
        }

        return count;
    }

}
