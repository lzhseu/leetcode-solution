package leetcode.算法.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzh
 * @date 2020/10/4 17:08
 */
public class 括号生成_22 {

    /**
     * 回溯
     * 1ms 96.7%  39M 51.37%
     */
    public List<String> generateParenthesis(int n) {

        List<String> resList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        // 以下两种都可
        //helper(resList, sb, n, 0, 0);
        helper(resList, sb, n, n);
        return resList;
    }

    public void helper(List<String> resList, StringBuilder sb, int n, int ln, int rn) {
        if(ln + rn == n * 2) {
            resList.add(sb.toString());
        }

        if(ln < n) {
            sb.append('(');
            helper(resList, sb, n, ln + 1, rn);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(rn < ln) {
            sb.append(')');
            helper(resList, sb, n, ln, rn + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public void helper(List<String> resList, StringBuilder sb, int ln, int rn) {
        if(ln == 0 && rn == 0) {
            resList.add(sb.toString());
        }

        if(ln > 0) {
            sb.append('(');
            helper(resList, sb, ln - 1, rn);
            sb.deleteCharAt(sb.length() - 1);
        }

        if(rn > ln) {
            sb.append(')');
            helper(resList, sb, ln, rn - 1);
            sb.deleteCharAt(sb.length() - 1);
        }

    }
}
