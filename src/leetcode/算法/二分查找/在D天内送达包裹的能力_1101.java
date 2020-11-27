package leetcode.算法.二分查找;

/**
 * @author lzh
 * @date 2020/11/23 10:00
 */
public class 在D天内送达包裹的能力_1101 {

    public int shipWithinDays(int[] weights, int D) {

        int[] arr = getMinMax(weights);
        int left = arr[0], right = arr[1] + 1;

        while(left < right) {
            int mid = left + ((right - left) >> 1);
            if(canLoad(weights, mid, D)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int[] getMinMax(int[] weights) {
        int sum = 0, max = 0;
        for(int i = 0; i < weights.length; i++) {
            sum += weights[i];
            max = Math.max(max, weights[i]);
        }

        return new int[] {max, sum};
    }

    private boolean canLoad(int[] weights, int load, int D) {
        int i = 0;
        int len = weights.length;
        for(int d = 0; d < D; d++) {
            int theLoad = load;
            while((theLoad -= weights[i]) >= 0) {
                i++;
                if(i == len) {
                    return true;
                }
            }
        }
        return false;
    }
}
