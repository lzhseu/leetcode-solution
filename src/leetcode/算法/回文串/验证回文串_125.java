package leetcode.算法.回文串;

/**
 * @author lzh
 * @date 2020/7/8 15:49
 */
public class 验证回文串_125 {

    /**
     * 本题主要考察的是关于 String 的相关 API
     */

    /**
     * 官方解答
     */
    public boolean isPalindrome2(String s) {
        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                ++left;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                --right;
            }
            if (left < right) {
                if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                    return false;
                }
                ++left;
                --right;
            }
        }
        return true;
    }


    /**
     * 自己写的，没用 API，所以速度较慢。但思路相同
     */
    public boolean isPalindrome(String s) {

        if(s == null) {
            return false;
        } else if(s.length() == 0 || s.length() == 1) {
            return true;
        }

        s = s.toLowerCase();
        int left = 0, right = s.length()-1;

        while(left < right) {

            while(!isLetter(s.charAt(left)) && left < right) {
                left++;
            }

            while(!isLetter(s.charAt(right)) && left < right) {
                right--;
            }

            if(s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;

        }

        return !(left == 0 || right == s.length()-1);
    }

    public boolean isLetter(char c) {

        return ('a' <= c && c <= 'z') || ('0' <= c && c<= '9');
    }

    public static void main(String[] args) {

        验证回文串_125 c = new 验证回文串_125();

        String s = "A man, a plan, a canal: Panama";
        String s2 = "race a car";
        String s3 = "0P";


        System.out.println(c.isPalindrome(s));
        System.out.println(c.isPalindrome(s2));
        System.out.println(c.isPalindrome(s3));
    }
}
