package leetcode.数据结构.树;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lzh
 * @date 2020/11/11 20:35
 */
public class 验证二叉搜索树_98 {

    /**
     * 做法一：中序遍历，然后再遍历一次结果，看先后顺序是否正确
     * 2ms 31.17%    38.3M 77.9%
     */
    public boolean isValidBST1(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        recursive1(list, root);

        int size = list.size();

        for(int i = 0; i < size - 1; i++) {
            if(list.get(i + 1) <= list.get(i)) {
                return false;
            }
        }

        return true;
    }

    private void recursive1(List<Integer> list, TreeNode root) {

        // 应该用中序遍历
        if(root == null) {
            return;
        }

        recursive1(list, root.left);
        list.add(root.val);
        recursive1(list, root.right);
    }


    /**
     * 做法二：使用栈。本来想着减少一次数组遍历，没想到用栈做中序遍历反而慢
     * 3ms 9.01%    38.2M 82.94%
     *
     */
    public boolean isValidBST2(TreeNode root) {

        // 使用栈来做中序遍历，省去一次遍历数组

        if(root == null) {
            return true;
        }

        Deque<TreeNode> stack = new LinkedList<>();

        stack.push(root);
        long lastValue = Long.MIN_VALUE;

        while(!stack.isEmpty()) {

            TreeNode tmpNode = stack.peek();
            if(tmpNode.left != null) {
                stack.push(tmpNode.left);
                tmpNode.left = null;
                continue;
            }

            int val = tmpNode.val;
            if(val <= lastValue) {
                stack.clear();
                return false;
            }
            lastValue = val;
            stack.pop();

            if(tmpNode.right != null) {
                stack.push(tmpNode.right);
            }

        }

        return true;

    }


    /**
     * 做法三：在递归中顺便判断了，减少一次数组的遍历
     * 1ms 35.73%
     */
    public boolean isValidBST3(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        return recursive2(list, root);
    }

    private boolean recursive2(List<Integer> list, TreeNode root) {

        boolean flag = true;

        // 应该用中序遍历
        if(root == null) {
            return true;
        }


        flag = flag && recursive2(list, root.left);

        // 直接在递归中判断大小，省去一次遍历数组
        list.add(root.val);
        int index = list.size() - 1;
        if(index > 0) {
            flag = flag && list.get(index) > list.get(index - 1);
        }

        flag = flag && recursive2(list, root.right);

        return flag;
    }

    /**
     * 做法4：思路一样，为啥编码总是比别人差
     * 0ms 100%    38.1M 88.24%
     */
    private long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {

        return recursive3(root);
    }

    private boolean recursive3(TreeNode root) {

        if(root == null) {
            return true;
        }

        if(!recursive3(root.left)) {
            return false;
        }

        if(root.val <= pre) {
            return false;
        }
        pre = root.val;

        return recursive3(root.right);

    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(5);
        TreeNode l1 = new TreeNode(1);
        TreeNode r1 = new TreeNode(4);
        TreeNode rl1 = new TreeNode(3);
        TreeNode rl2 = new TreeNode(6);
        root.left = l1;
        root.right = r1;
        r1.left = rl1;
        r1.right = rl2;

        System.out.println(isValidBST3(root));
    }
}
