package leetcode.算法.并查集;

/**
 * @author lzh
 * @date 2020/9/17 16:55
 */
public class 冗余连接_684 {

    /**
     * 直接合并，不能合并的证明构成环，就将该边的值赋给 result
     * 1ms
     * @param edges
     * @return
     */
    public int[] findRedundantConnection2(int[][] edges) {

        int len = edges.length;
        int[] result = new int[2];

        UnionFind unionFind = new UnionFind(len + 1);

        for(int i = 0; i < len; i++) {
            if(!unionFind.union(edges[i][0], edges[i][1])) {
                result[0] = edges[i][0];
                result[1] = edges[i][1];
                return result;
            }
        }

        throw new RuntimeException("invalid input");
    }

    /**
     * 算法设计：从后往前遍历，每次都尝试删除一条边，判断剩下的是否构成环，否则返回改变
     * 每次都删除一边还要多出一层循环，思维僵化！  3ms
     * @param edges
     * @return
     */
    public int[] findRedundantConnection1(int[][] edges) {

        int len = edges.length;

        for(int i = len - 1; i >= 0; i--) {
            if(!judgeCircle(edges, len, i)) {
                return edges[i];
            }
        }

        throw new RuntimeException("invalid input");
    }

    public boolean judgeCircle(int[][] edges, int len, int removeIndex) {

        UnionFind unionFind = new UnionFind(len + 1);

        for(int i = 0; i < len; i++) {

            if(removeIndex == i) {
                continue;
            }

            if(!unionFind.union(edges[i][0], edges[i][1])) {
                return true;
            }
        }
        return false;
    }

    public static class UnionFind {

        int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while(x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
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
