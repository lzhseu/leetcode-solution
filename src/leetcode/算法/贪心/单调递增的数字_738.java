package leetcode.算法.贪心;

/**
 * @author lzh
 * @date 2020/12/15 16:32
 */
public class 单调递增的数字_738 {

    /**
     * 1 ms 97.96%    35.4 MB 60.92%
     */
    public int monotoneIncreasingDigits(int N) {

        // 贪心

        char[] arr = String.valueOf(N).toCharArray();

        for(int i = 1; i < arr.length; i++) {

            int j = i;

            if(arr[j] < arr[j - 1]) {

                while(j > 0 && arr[j] < arr[j - 1]) {
                    // 要一直往回走，保证前面部分单调递增
                    arr[j] = '9';
                    arr[j - 1] -= 1;
                    j--;
                }

                for(int k = i + 1; k < arr.length; k++) {
                    arr[k] = '9';
                }

                break;
            }

        }

        return Integer.parseInt(String.valueOf(arr));

    }
}
