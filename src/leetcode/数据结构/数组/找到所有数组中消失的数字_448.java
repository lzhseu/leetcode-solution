package leetcode.数据结构.数组;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2021/1/7 21:02
 */
public class 找到所有数组中消失的数字_448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {

        for(int num : nums) {
            int index = Math.abs(num) - 1;
            if (nums[index] > 0) {
                nums[index] *= -1;
            }
        }

        List<Integer> resList = new ArrayList<>();

        for(int i = 1; i <= nums.length; i++) {
            if(nums[i - 1] > 0) {
                resList.add(i);
            }
        }

        return resList;
    }

}
