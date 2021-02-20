package 剑指offer;

import leetcode.数据结构.树.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzh
 * @date 2021/1/13 12:52
 */
public class _07_重建二叉树 {

    /**
     * 4 ms  43.26%    38.5 MB 64.82%
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return buildTree(preorder, 0, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree(int[] preorder, int preIndex, int[] inorder, int inStart, int inEnd) {

        // base case
        if(inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preIndex];
        TreeNode root = new TreeNode(rootVal);

        int index = inStart;

        while(index <= inEnd && inorder[index] != rootVal) {
            index++;
        }

        root.left = buildTree(preorder, preIndex + 1, inorder, inStart, index - 1);
        root.right = buildTree(preorder, preIndex + (index - inStart) + 1, inorder, index + 1, inEnd);

        return root;
    }

    /**
     * 用 HashMap 改良
     * 2 ms 97.68%    38.9 MB 24.37%
     */
    public TreeNode buildTree_map(int[] preorder, int[] inorder) {

        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, inorder, 0, inorder.length - 1, map);
    }

    private TreeNode buildTree(int[] preorder, int preIndex, int[] inorder, int inStart, int inEnd, Map<Integer, Integer> map) {

        // base case
        if(inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preIndex];
        TreeNode root = new TreeNode(rootVal);

        int index = map.get(rootVal);

        root.left = buildTree(preorder, preIndex + 1, inorder, inStart, index - 1, map);
        root.right = buildTree(preorder, preIndex + (index - inStart) + 1, inorder, index + 1, inEnd, map);

        return root;
    }
}
