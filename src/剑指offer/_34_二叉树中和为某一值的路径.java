package 剑指offer;

import leetcode.数据结构.树.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author lzh
 * @date 2021/1/16 15:29
 */
public class _34_二叉树中和为某一值的路径 {

    /**
     * 第一版代码
     * 2 ms 28.91%     38.8 MB  59.85%
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> resList = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();

        if(root == null) {
            return resList;
        }

        pathSum(resList, root, sum, path);
        return resList;
    }

    private void pathSum(List<List<Integer>> resList, TreeNode root, int sum, Deque<Integer> path) {

        path.addLast(root.val);
        sum -= root.val;

        if(isLeaf(root) && sum == 0) {
            resList.add(new ArrayList<>(path));
        }

        if(root.left != null) {
            pathSum(resList, root.left, sum, path);
        }

        if(root.right != null) {
            pathSum(resList, root.right, sum, path);
        }

        path.removeLast();

    }

    private boolean isLeaf(TreeNode root) {
        return root.left == null && root.right == null;
    }
}
