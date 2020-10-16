package leetcode.数据结构.树;

import java.util.*;

/**
 * @author lzh
 * @date 2020/9/24 19:32
 */
public class 二叉搜索树中的众数_501 {

    /**
     * 利用 BST 的中序遍历是一个不递减的序列
     * 0ms  100%    39.5M 45.69%
     */

    int currentNum = -1, currentNumCount = 0;
    int maxCount = 0;
    List<Integer> ans = new ArrayList<>();

    public int[] findMode(TreeNode root) {

        InOrder(root);

        int[] res = new int[ans.size()];

        for(int i = 0; i < ans.size(); i++) {
            res[i] = ans.get(i);
        }

        return res;

    }

    private void InOrder(TreeNode root) {

        if(root == null) {
            return;
        }

        InOrder(root.left);

        if(currentNum == root.val) {
            currentNumCount++;
        } else {
            currentNum = root.val;
            currentNumCount = 1;
        }

        if(currentNumCount > maxCount) {
            maxCount = currentNumCount;
            ans.clear();
            ans.add(root.val);
        } else if(currentNumCount == maxCount) {
            ans.add(root.val);
        }

        InOrder(root.right);

    }



    /**
     * 低级的做法，空间复杂度高
     * 12ms 8.04%     40.6M 16.32%
     */
    public int[] findMode1(TreeNode root) {

        Map<Integer, Integer> map = new HashMap<>();
        InOrder1(root, map);

        Queue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p2.v - p1.v;
            }
        });

        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.offer(new Pair(entry.getKey(), entry.getValue()));
        }

        Pair p = pq.poll();
        List<Integer> list = new ArrayList<>();
        list.add(p.k);
        while(!pq.isEmpty()) {
            Pair tmp = pq.poll();
            if(tmp.v == p.v) {
                list.add(tmp.k);
            } else {
                break;
            }
        }

        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;

    }

    private void InOrder1(TreeNode root, Map<Integer, Integer> map) {

        if(root == null) {
            return;
        }

        InOrder1(root.left, map);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        InOrder1(root.right, map);

    }

    static class Pair {

        int k;
        int v;

        public Pair(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

}
