package leetcode.算法.双指针;

/**
 * @author lzh
 * @date 2020/11/23 15:33
 */
public class 字符串的排列_567 {

    /**
     * 滑动窗口算法
     * 4 ms 97.60%    38.6 MB 79.55%
     */
    public boolean checkInclusion(String s1, String s2) {

        // 框架式写法

        int[] need = new int[128], window = new int[128];

        char[] s1Arr = s1.toCharArray(), s2Arr = s2.toCharArray();
        int len1 = s1Arr.length, len2 = s2Arr.length;

        for(char c : s1Arr) {
            need[c]++;
        }

        int lp = 0, rp = 0;
        int count = 0;

        while(rp < len2) {
            // c 是将移入窗口的字符
            char c = s2Arr[rp];
            // 右移窗口
            rp++;
            // 进行窗口内数据的一系列更新
            if(++window[c] <= need[c]) {
                count++;
            }

            // 判断是否需要缩小窗口
            while(rp - lp >= len1) {

                // 判断是否满足
                if(count == len1) {
                    return true;
                }

                // d 是将要移出窗口的字符
                char d = s2Arr[lp];
                // 左移窗口
                lp++;
                // 进行窗口内一系列数据的更新
                if(need[d] > 0) {
                    window[d]--;
                    if(window[d] < need[d]) {
                        count--;
                    }
                }
            }
        }
        return false;
    }
}
