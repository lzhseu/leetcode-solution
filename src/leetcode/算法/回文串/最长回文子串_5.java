package leetcode.算法.回文串;

/**
 * @author lzh
 * @date 2020/7/9 9:12
 */
public class 最长回文子串_5 {

    /**
     * 方法一：暴力解法 1223ms 38.1M
     */
    public String longestPalindrome1(String s) {

        int len = s.length();
        if(len < 2) {
            return s;
        }

        int begin = 0;
        int maxLen = 1;

        char[] charArr = s.toCharArray();

        for(int i = 0; i < len-1; i++) {
            for(int j = i+1; j < len; j++) {
                if(palindromeHelper1(charArr, i, j) && (j-i+1) > maxLen) {
                    maxLen = j-i+1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);

    }

    /**
     * 判断是否是回文串
     */
    public boolean palindromeHelper1(char[] charArr, int i, int j) {
        while(i < j) {
            if(charArr[i] != charArr[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////


    /**
     * 方法二：中心扩散法 11ms 37.9M
     */
    public String longestPalindrome2(String s) {

        if(s.length() < 2) {
            return s;
        }

        int begin = 0;
        int maxLen = 1;

        char[] charArr = s.toCharArray();
        int len = charArr.length;

        for(int i = 0; i < len-1; i++) {
            int odd = palindromeHelper2(charArr, i, i);
            int even = palindromeHelper2(charArr, i, i+1);

            int tmpLen = Math.max(odd, even);
            if(tmpLen > maxLen) {
                maxLen = tmpLen;
                begin = i - (maxLen-1) / 2;
            }
        }

        return s.substring(begin, begin + maxLen);

    }

    /**
     * 从中心扩散找回文串
     */
    public int palindromeHelper2(char[] charArr, int i, int j) {
        int left = i, right = j;
        while(left >= 0 && right < charArr.length) {
            if(charArr[left] == charArr[right]) {
                left--;
                right++;
            } else {
                break;
            }
        }
        return right - left - 1;
    }

    ////////////////////////////////////////////////////////////////////////////////////


    /**
     * 方法三：动态规划  96ms  42.3M
     */
    public String longestPalindrome3(String s) {

        if(s.length() < 2) {
            return s;
        }

        int begin = 0;
        int maxLen = 1;

        char[] charArr = s.toCharArray();
        int len = charArr.length;
        boolean[][] dp = new boolean[len][len];

        // 对角线不会被参考到，但为了语义完整，还是初始化为 true
        for(int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for(int j = 1; j < len; j++) {
            for(int i = 0; i < j; i++) {
                if(j-i < 3 && charArr[i] == charArr[j]) {
                    dp[i][j] = true;
                } else if(charArr[i] == charArr[j]) {
                    dp[i][j] = dp[i+1][j-1];
                }

                if(dp[i][j] && (j-i+1) > maxLen) {
                    maxLen = j-i+1;
                    begin = i;
                }
            }
        }


        return s.substring(begin, begin + maxLen);

    }

}
