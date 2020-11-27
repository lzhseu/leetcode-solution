package leetcode.算法.二分查找;

/**
 * @author lzh
 * @date 2020/11/23 9:53
 */
public class 爱吃香蕉的珂珂_875 {

    public int minEatingSpeed(int[] piles, int H) {

        int max = getMax(piles);

        int left = 1, right = max + 1;

        while(left < right) {
            int mid = left + ((right - left) >> 1);
            if(canFinish(piles, mid, H)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private int getMax(int[] piles) {
        int max = piles[0];
        for(int i = 1; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        return max;
    }

    private boolean canFinish(int[] piles, int speed, int H) {
        int time = 0;
        for (int n : piles) {
            time += timeOf(n, speed);
        }
        return time <= H;
    }

    private int timeOf(int n, int speed) {
        return (n / speed) + ((n % speed > 0) ? 1 : 0);
    }
}
