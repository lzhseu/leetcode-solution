package leetcode.数据结构.树;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author lzh
 * @date 2020/9/26 15:09
 */
public class 路径总和II_113 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> resList = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        recursive1(root, sum, resList, path);
        return resList;
    }


    /**
     * 2ms 49.57%  39.5M 16.11%
     */
    private void recursive1(TreeNode root, int target, List<List<Integer>> resList, Deque<Integer> path) {

        // 终止条件
        if(root == null) {
            return;
        }

        // 父节点处理逻辑
        int remain = target - root.val;
        path.addLast(root.val);

        if(isLeaf(root) && remain == 0) {
            resList.add(new ArrayList<>(path));
            return;
        }

        // 往左
        if(root.left != null) {
            recursive1(root.left, remain, resList, path);
            path.removeLast();
        }

        // 往右
        if(root.right != null) {
            recursive1(root.right, remain, resList, path);
            path.removeLast();
        }

    }

    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}
