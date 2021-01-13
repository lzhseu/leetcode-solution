package leetcode.算法.动态规划;

import leetcode.数据结构.树.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/12/22 19:37
 */
public class 打家劫舍III_337 {

    Map<TreeNode, Integer> meno = new HashMap<>();

    public int rob(TreeNode root) {

        if(root == null) {
            return 0;
        }

        if(meno.containsKey(root)) {
            return meno.get(root);
        }

        int robbed = root.val +
                (root.left == null ? 0 : rob(root.left.left) + rob(root.left.right)) +
                (root.right == null ? 0 : rob(root.right.left) + rob(root.right.right));

        int unrobbed = rob(root.left) + rob(root.right);

        int res = Math.max(robbed, unrobbed);
        meno.put(root, res);
        return res;
    }
}
