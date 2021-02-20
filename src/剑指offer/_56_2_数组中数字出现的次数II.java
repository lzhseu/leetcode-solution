package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/19 10:16
 */
public class _56_2_数组中数字出现的次数II {

    /**
     *
     */
    public int singleNumber(int[] nums) {

        // int 总共 32 位
        int[] bitSum = new int[32];

        // 1.把每个元素的二进制各个位相加
        for(int num : nums) {

            int bitMask = 1;

            for(int i = 31; i >= 0; i--) {
                int bit = bitMask & num;
                if(bit != 0) {
                    bitSum[i] += 1;
                }
                bitMask <<= 1;
            }
        }

        // 2.将 bitSum 中每个和对 3 取余数
        int result = 0;
        for(int sum : bitSum) {
            result <<= 1;
            result += sum % 3;
        }

        return result;
    }
}
