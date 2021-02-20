package 剑指offer;

import leetcode.数据结构.树.TreeNode;

/**
 * @author lzh
 * @date 2021/1/15 19:34
 */
public class _26_树的子结构 {

    /**
     * 递归的魔力
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        if(A == null || B == null) {
            return false;
        }

        boolean result = false;

        if(A.val == B.val) {
            result = doesTreeAHaveTreeB(A, B);
        }

        if(!result) {
            result = isSubStructure(A.left, B);
        }

        if(!result) {
            result = isSubStructure(A.right, B);
        }

        return result;

    }

    private boolean doesTreeAHaveTreeB(TreeNode rootA, TreeNode rootB) {

        if(rootB == null) {
            return true;
        } else if(rootA == null) {
            return false;
        }

        return rootA.val == rootB.val &&
                doesTreeAHaveTreeB(rootA.left, rootB.left) &&
                doesTreeAHaveTreeB(rootA.right, rootB.right);
    }
}
