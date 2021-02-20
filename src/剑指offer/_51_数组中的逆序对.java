package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/18 14:30
 */
public class _51_数组中的逆序对 {

    /**
     * 方法一：实际上是归并排序的方法，只不过在 merge 时需要统计逆序对的数量
     *
     * 38 ms 49.01%    48.4 MB 33.96%
     */
    public int reversePairs(int[] nums) {

        // 实际上借鉴了归并排序的思想

        if(nums == null || nums.length == 0) {
            return 0;
        }

        int[] aux = new int[nums.length];

        return reversePairsCore(nums, aux, 0, nums.length - 1);
    }

    private int reversePairsCore(int[] nums, int[] aux, int lo, int hi) {

        if(lo == hi) {
            return 0;
        }

        int mid = lo + ((hi - lo) >> 1);

        int leftCount = reversePairsCore(nums, aux, lo, mid);

        int rightCount = reversePairsCore(nums, aux, mid + 1, hi);

        int count = merge(nums, aux, lo, mid, hi);

        return leftCount + rightCount + count;

    }

    private int merge(int[] nums, int[] aux, int lo, int mid, int hi) {

        if(hi - lo + 1 > 0) {
            System.arraycopy(nums, lo, aux, lo, hi - lo + 1);
        }

        int i = mid;
        int j = hi;

        int count = 0;

        for(int k = hi; k >= lo; k--) {

            if(i < lo) {
                nums[k] = aux[j--];
            } else if(j < mid + 1) {
                nums[k] = aux[i--];
            } else if(aux[i] > aux[j]) {
                nums[k] = aux[i--];
                count += (j - mid);
            } else if(aux[i] <= aux[j]) { // 相等则移动右边的
                nums[k] = aux[j--];
            }
        }

        return count;
    }


}
