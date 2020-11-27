package leetcode.算法.并查集;

/**
 * @author lzh
 * @date 2020/11/26 12:41
 */
public class 等式方程的可满足性_990 {

    public boolean equationsPossible(String[] equations) {

        UnionFind uf = new UnionFind(26);

        for(String str : equations) {
            if(str.charAt(1) == '=') {
                uf.union(str.charAt(0)-'a', str.charAt(3)-'a');
            }
        }

        for(String str : equations) {
            if(str.charAt(1) == '!') {
                if(uf.connected(str.charAt(0) - 'a', str.charAt(3) - 'a')) {
                    return false;
                }
            }
        }

        return true;
    }
}
