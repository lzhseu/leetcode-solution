package leetcode.数据结构.树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2020/11/17 15:45
 */
public class 寻找重复的子树_652 {

    /**
     * 框架式做法
     * 26ms 70%
     */
    List<TreeNode> resList = new ArrayList<>();
    Map<String, Integer> map = new HashMap<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        traverse(root);
        return resList;
    }

    private String traverse(TreeNode root) {

        if(root == null) {
            return "#";
        }

        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + ',' + right + ',' + root.val;

        int count = map.getOrDefault(subTree, 0);
        if(count == 1) {
            resList.add(root);
        }
        map.put(subTree, count + 1);

        return subTree;
    }
}
