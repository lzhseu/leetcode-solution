package leetcode.数据结构.树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzh
 * @date 2021/1/7 21:07
 */
public class 路径总和III_437 {

    /**
     * 初始版本，不断用 List 来记录路径
     * 33 ms 17.77%    44.9 MB 5.03%
     */
    private List<Integer> sumList = new ArrayList<>();
    private int result = 0;

    public int pathSum1(TreeNode root, int sum) {
        helper(root, sum, sumList);
        return result;
    }

    private void helper(TreeNode root, int sum, List<Integer> sumList) {

        if(root == null) {
            return;
        }

        List<Integer> tempList = new ArrayList<>();

        for(int i = 0; i < sumList.size(); i++) {
            int tmp = sumList.get(i) + root.val;
            tempList.add(tmp);
            if(tmp == sum) {
                result++;
            }
        }

        tempList.add(root.val);
        if(root.val == sum) {
            result++;
        }

        helper(root.left, sum, tempList);
        helper(root.right, sum, tempList);
    }


    /**
     * 双重递归
     */
    public int pathSum2(TreeNode root, int sum) {

        if(root == null) {
            return result;
        }

        dfs(root, sum);

        pathSum2(root.left, sum);
        pathSum2(root.right, sum);

        return result;
    }

    private void dfs(TreeNode root, int sum) {

        if(root == null) {
            return;
        }

        sum -= root.val;
        if(sum == 0) {
            result++;
        }

        dfs(root.left, sum);
        dfs(root.right, sum);
    }


    /**
     * 前缀和
     * 3 ms 97.79%    38.4 MB 34.33%
     */
    public int pathSum3(TreeNode root, int sum) {

        Map<Integer, Integer> prefixSumCount = new HashMap<>();

        // 前缀和为 0 的路径有 1 条
        prefixSumCount.put(0, 1);

        return recursive(root, prefixSumCount, sum, 0);
    }

    /**
     * 来自：https://leetcode-cn.com/problems/path-sum-iii/solution/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/
     *
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     *
     * @param root 树节点
     * @param prefixSumCount 前缀和 Map，key 是前缀和，value 是该前缀和的个数
     * @param target 目标和
     * @param currSum 当前路径和
     * @return 满足题意的解
     */
    public int recursive(TreeNode root, Map<Integer, Integer> prefixSumCount, int target, int currSum) {

        if (root == null) {
            return 0;
        }

        int result = 0;

        // 当前路径上的和
        currSum += root.val;

        // 看看 root 到当前节点这条路上是否存在节点前缀和加 target 为 currSum 的路径
        // 当前节点->root 节点反推，有且仅有一条路径，如果此前有和为 currSum-target,而当前的和又为 currSum,两者的差就肯定为 target 了
        // currSum-target 相当于找路径的起点，起点的 sum+target=currSum，当前点到起点的距离就是 target
        result += prefixSumCount.getOrDefault(currSum - target, 0);

        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);

        // 进入下一层
        result += recursive(root.left, prefixSumCount, target, currSum);
        result += recursive(root.right, prefixSumCount, target, currSum);

        // 回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);

        return result;
    }
}
