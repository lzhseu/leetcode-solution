package leetcode.数据结构.数组;

/**
 * @author lzh
 * @date 2020/7/20 9:14
 */
public class 两数之和II_167 {

    /**
     * 双指针法 时间复杂度O(n)
     * 1ms 95.52%     39.9M 6.67%
     */
    public int[] twoSum(int[] numbers, int target) {

        int lp = 0, rp = numbers.length-1;
        while(lp < rp) {
            if(target - numbers[lp] == numbers[rp]) {
                return new int[] {lp+1, rp+1};
            } else if(target - numbers[lp] < numbers[rp]){
                rp--;
            } else {
                lp++;
            }
        }

        throw new RuntimeException("找不到结果");
    }
}
