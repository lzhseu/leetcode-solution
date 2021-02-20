package 剑指offer;

/**
 * @author lzh
 * @date 2021/2/19 20:25
 */
public class _58_2_左旋转字符串 {

    /**
     * 偷懒做法
     */
    public String reverseLeftWords1(String s, int n) {

        return s.substring(n) + s.substring(0, n);
    }

    /**
     * 实际考察的是字符串的翻转：先翻转前 n 个，再翻转后面部分，最后整个字符串翻转
     */
    public String reverseLeftWords(String s, int n) {

        char[] arr = s.toCharArray();
        int len = arr.length;
        reverse(arr, 0, n - 1);
        reverse(arr, n, len - 1);
        reverse(arr, 0, len - 1);
        return String.valueOf(arr);
    }

    private void reverse(char[] arr, int begin, int end) {

        while(begin < end) {
            char tmp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = tmp;
            begin++;
            end--;
        }

    }

}
