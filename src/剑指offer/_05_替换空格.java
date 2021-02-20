package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/13 10:47
 */
public class _05_替换空格 {

    /**
     * 解法1：直接使用 JDK 的 replaceAll
     * 3 ms 16.36%    36.4 MB 35.35%
     */
    public String replaceSpace_jdk(String s) {
        return s.replaceAll(" ", "%20");
    }

    /**
     * 解法2：使用 StringBuilder
     * 0 ms 100.00%    36.4 MB 34.19%
     */
    public String replaceSpace(String s) {

        if(s == null || s.length() == 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ') {
                sb.append("%20");
            } else {
                sb.append(c);
            }

        }

        return sb.toString();

    }

}
