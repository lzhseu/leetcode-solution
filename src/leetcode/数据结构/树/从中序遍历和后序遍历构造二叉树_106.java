package leetcode.数据结构.树;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/9/25 9:27
 */
public class 从中序遍历和后序遍历构造二叉树_106 {

    /**
     * (1)使用 HashMap 优化，查询 inorder 的根节点时间为 O(1)
     * (2)之前构造是先左后右，现在充分利用后序序列的特点，先右后左，省去计算后序遍历 leftIdx rightIdx
     * 2ms 97.53%  39.6 11.06%
     */

    private int postIdx;
    private Map<Integer, Integer> map;

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        map = new HashMap<>();
        int len = inorder.length;
        postIdx = len - 1;

        for(int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }

        return recursive(inorder, postorder, 0, len - 1);
    }

    private TreeNode recursive(int[] inorder, int[] postorder, int inLeft, int inRight) {

        // 终止条件
        if(inRight < inLeft) {
            return null;
        }

        int rootValue = postorder[postIdx--];
        int rootIdxInOrder = map.get(rootValue);

        TreeNode root = new TreeNode(rootValue);
        root.right = recursive(inorder, postorder, rootIdxInOrder + 1, inRight);
        root.left = recursive(inorder, postorder, inLeft, rootIdxInOrder - 1);

        return root;
    }



    /**
     * 朴素方法
     * 4ms 43.37%   39.2M 52.26
     */
    private TreeNode recursive1(int[] inorder, int inLeft, int inRight, int[] postorder, int poLeft, int poRight) {

        // 终止条件
        if(inRight < inLeft) {
            return null;
        }


        int rootValue = postorder[poRight];
        int rootIdxInOrder = -1;
        for(int i = inLeft; i <= inRight; i++) {
            if(inorder[i] == rootValue) {
                rootIdxInOrder = i;
            }
        }

        int leftNodeCountPostOrder = rootIdxInOrder - inLeft;
        int leftIndexPostOrder = poLeft + leftNodeCountPostOrder - 1;

        TreeNode root = new TreeNode(rootValue);
        root.left = recursive1(inorder, inLeft, rootIdxInOrder - 1, postorder, poLeft, leftIndexPostOrder);
        root.right = recursive1(inorder, rootIdxInOrder + 1, inRight, postorder, leftIndexPostOrder + 1, poRight - 1);

        return root;
    }

    public TreeNode buildTree1(int[] inorder, int[] postorder) {

        return recursive1(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }
}
