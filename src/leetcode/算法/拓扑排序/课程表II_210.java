package leetcode.算法.拓扑排序;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author lzh
 * @date 2020/12/24 11:12
 */
public class 课程表II_210 {

    /**
     * 拓扑排序 BFS
     * 5 ms 79.77%    39.7 MB 54.69%
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int[] inDegree = new int[numCourses];

        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] pair : prerequisites) {
            inDegree[pair[0]]++;
            adj.get(pair[1]).add(pair[0]);
        }

        int ridx = 0;
        int[] result = new int[numCourses];
        Deque<Integer> dq = new ArrayDeque<>();

        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                dq.addLast(i);
            }
        }

        while(!dq.isEmpty()) {

            int curr = dq.removeFirst();

            result[ridx++] = curr;

            for(int next : adj.get(curr)) {

                if(--inDegree[next] == 0) {
                    dq.addLast(next);
                }
            }
        }

        return ridx == numCourses ? result : new int[] {};
    }
}
