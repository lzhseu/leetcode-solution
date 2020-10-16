package leetcode.算法.并查集;

/**
 * @author lzh
 * @date 2020/9/17 16:51
 */
public class 冗余连接II_685 {

    public int[] findRedundantDirectedConnection(int[][] edges) {

        int len = edges.length;

        // 因为节点是在 1~N 之间，方便编码
        int[] inDegree = new int[len + 1];

        // 计算每个节点的入度
        for(int i = 0; i < len; i++) {
            inDegree[edges[i][1]]++;
        }

        // 先尝试删除构成入度为 2 的边，看看是否形成环
        // 删除边的时候从后往前遍历，因为题目要求返回最后出现在给定二维数组的答案
        for(int i = len - 1; i >= 0; i--) {
            if(inDegree[edges[i][1]] == 2) {
                // 如果不构成环，这条边就是要去掉的那条边
                if(!judgeCircle(edges, len, i)) {
                    return edges[i];
                }
            }
        }

        // 再尝试删除构成入度为 1 的边，看看是否形成环
        for(int i = len - 1; i >= 0; i--) {
            if(inDegree[edges[i][1]] == 1) {
                if(!judgeCircle(edges, len, i)) {
                    return edges[i];
                }
            }
        }

        throw new RuntimeException("invalid input");

    }

    public boolean judgeCircle(int[][] edges, int len, int removeIndex) {

        UnionFind unionFind = new UnionFind(len + 1);

        for(int i = 0; i < len; i++) {
            // 移除的边不要加进来
            if(i == removeIndex) {
                continue;
            }

            if(!unionFind.union(edges[i][1], edges[i][0])) {
                // 合并失败，表示 edges[i][1] 和 edges[i][0] 在一个连通分量里，即构成了环
                return true;
            }
        }

        return false;
    }

    public static class UnionFind {

        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if(x == parent[x]){
                return x;
            }
            parent[x] = find(parent[x]);
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) {
                return false;
            }
            parent[rootX] = rootY;
            return true;
        }

    }
}
