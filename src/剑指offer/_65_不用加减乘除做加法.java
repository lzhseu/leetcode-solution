package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/20 18:59
 */
public class _65_不用加减乘除做加法 {

    /**
     * 位运算。这个编码有点厉害
     *
     * 不用加减乘除，那么应该想到可以使用「二进制的位运算」
     * 分为三个步骤：以 101 和 001 举例
     * 1.不考虑进位，将各个位相加：可以使用异或操作，101 ^ 001 = 100
     * 2.考虑进位：可以使用与操作，(101 ^ 001) << 1 = 010
     * 3.不进位的结果和进位的结果加起来：100 + 010 = 110
     */
    public int add(int a, int b) {

        int sum, carry;

        do {
            // 1.不考虑进位
            sum = a ^ b;

            // 2.考虑进位
            carry = (a & b) << 1;

            a = sum;
            b = carry;

        } while(carry != 0);

        return a;
    }
}
