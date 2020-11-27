package leetcode.数据结构.树;

/**
 * @author lzh
 * @date 2020/11/17 15:01
 */
public class 最大二叉树_654 {

    /**
     * 常规，框架式做法
     * 3ms 34.92%
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null) {
            return null;
        }
        return build(nums, 0, nums.length - 1);
    }

    TreeNode build(int[] nums, int lo, int hi) {

        if(hi < lo) {
            return null;
        }

        int max = nums[lo];
        int idx = lo;
        for(int i = lo + 1; i <= hi; i++) {
            if(max < nums[i]) {
                max = nums[i];
                idx = i;
            }
        }
        TreeNode root = new TreeNode(max);

        root.left = build(nums, lo, idx - 1);
        root.right = build(nums, idx + 1, hi);

        return root;
    }

    /**
     * 只遍历一次的做法
     * 4ms
     */
    // 只遍历一次，不用找最大值
    public TreeNode constructMaximumBinaryTree2(int[] nums) {
        if(nums == null) {
            return null;
        }
        TreeNode root = null;
        for(int num : nums) {
            root = helper(root, num);
        }
        return root;
    }

    TreeNode helper(TreeNode root, int next) {

        TreeNode node = new TreeNode(next);
        if(root == null) {
            return node;
        }

        // 如果下一节点大于当前根节点，则把下一节点当作根节点，原来节点变为新根节点的左子树
        if(next > root.val) {
            node.left = root;
            return node;
        }

        // 否则，将下一节点交给根节点的右子树处理
        root.right = helper(root.right, next);
        return root;
    }
}
