package leetcode.数据结构.树;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/11/13 9:56
 */
public class 从前序与中序遍历序列构造二叉树_105 {

    /**
     * 改进版本
     * 使用 HashMap 查询优化
     * 3ms 71.21%
     */
    int preIdx = 0;
    public TreeNode buildTree2(int[] preorder, int[] inorder) {

        int len;
        if(preorder == null || (len = preorder.length) == 0) {
            return null;
        }

        // 使用 HashMap 优化查询
        Map<Integer, Integer> inMap = new HashMap<>(len);
        for(int i = 0; i < len; i++) {
            inMap.put(inorder[i], i);
        }

        return recursiveBuild(preorder, inMap, 0, len - 1);

    }

    private TreeNode recursiveBuild(int[] preorder, Map<Integer, Integer> inMap, int lo, int hi) {
        // 终止条件
        if(lo > hi) {
            return null;
        }


        int rootVal = preorder[preIdx++];
        TreeNode root = new TreeNode(rootVal);

        int rootIdx4Inorder = inMap.get(rootVal);

        root.left = recursiveBuild(preorder, inMap, lo, rootIdx4Inorder - 1);
        root.right = recursiveBuild(preorder, inMap, rootIdx4Inorder + 1, hi);

        return root;

    }


    /**
     * 初始版本
     * 4ms 47.54%    38.2M 98.55%
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder == null || preorder.length == 0) {
            return null;
        }

        return recursiveBuild(preorder, 0, inorder, 0, inorder.length - 1);

    }

    private TreeNode recursiveBuild(int[] preorder, int preIdx, int[] inorder, int lo, int hi) {
        // 终止条件
        if(lo > hi) {
            return null;
        }


        int rootVal = preorder[preIdx];
        TreeNode root = new TreeNode(rootVal);

        int rootIdx4Inorder = findRoot4Inorder(inorder, rootVal, lo, hi);

        root.left = recursiveBuild(preorder, preIdx + 1, inorder, lo, rootIdx4Inorder - 1);
        root.right = recursiveBuild(preorder, preIdx + rootIdx4Inorder - lo + 1, inorder, rootIdx4Inorder + 1, hi);

        return root;

    }

    private int findRoot4Inorder(int[] inorder, int rootVal, int lo, int hi) {

        while(lo <= hi) {
            if(inorder[lo] == rootVal) {
                return lo;
            }
            lo++;
        }
        return -1;
    }
}
