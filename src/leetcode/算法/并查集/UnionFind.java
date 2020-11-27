package leetcode.算法.并查集;

/**
 * @author lzh
 * @date 2020/11/26 12:42
 */
public class UnionFind {

    private int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        return x == parent[x] ? x : (parent[x] = find(parent[x]));
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if(rootX == rootY) {
            return false;
        }
        parent[rootY] = rootX;
        return true;
    }

    public boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
