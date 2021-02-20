package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/19 10:15
 */
public class _56_1_数组中数字出现的次数 {

    /**
     * 位运算
     */
    public int[] singleNumbers(int[] nums) {

        // 1.先把所有数字异或一遍
        int xorRes = 0;
        for(int num : nums) {
            xorRes = xorRes ^ num;
        }

        // 2.找出最右边的一个1
        int bitIndex = 1;
        while((bitIndex & xorRes) == 0) {
            bitIndex = bitIndex << 1;
        }

        // 3.以最右边的1来对数组中的元素分类
        int[] result = new int[2];
        for(int num : nums) {
            if((num & bitIndex) == 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }

        return result;

    }
}
