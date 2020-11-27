package leetcode.算法.双指针;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2020/11/23 16:00
 */
public class 找到字符串中所有字母异位词_438 {

    /**
     * 5 ms 93.47%    39.5 MB 87.30%
     */
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> resList = new ArrayList<>();
        int sLen, pLen;
        if(s == null || (sLen = s.length()) < (pLen = p.length())) {
            return resList;
        }

        // 框架从这里开始
        int[] need = new int[128], window = new int[128];
        for(int i = 0; i < pLen; i++) {
            need[p.charAt(i)]++;
        }

        int lp = 0, rp = 0;
        int count = 0;

        while(rp < sLen) {

            char c = s.charAt(rp);
            rp++;
            if(++window[c] <= need[c]) {
                count++;
            }

            // 收缩
            while(rp - lp >= pLen) {

                // 判断是否满足
                if(count == pLen) {
                    resList.add(lp);
                }

                char d = s.charAt(lp);
                lp++;
                if(need[d] > 0 && --window[d] < need[d]) {
                    count--;
                }
            }
        }

        return resList;
    }
}
