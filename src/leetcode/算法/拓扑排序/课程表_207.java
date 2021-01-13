package leetcode.算法.拓扑排序;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author lzh
 * @date 2020/12/24 10:31
 */
public class 课程表_207 {

    /**
     * 拓扑排序 BFS
     * 6ms
     */
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {

        int n = prerequisites.length;

        // 记录入度
        int[] inDegree = new int[numCourses];

        // 邻接表
        List<List<Integer>> adjacency = new ArrayList<>();

        for(int i = 0; i < numCourses; i++) {
            adjacency.add(new ArrayList<>());
        }

        for(int i = 0; i < n; i++) {
            // 统计入度
            inDegree[prerequisites[i][0]]++;

            // 填充邻接表
            adjacency.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        Deque<Integer> queue = new ArrayDeque<>();

        // 将入度为 0 的课程放入队列
        for(int i = 0; i < numCourses; i++) {
            if(inDegree[i] == 0) {
                queue.addLast(i);
            }
        }

        // 拓扑排序，BFS
        while(!queue.isEmpty()) {

            int course = queue.removeFirst();
            numCourses--;

            for(int nextCourse : adjacency.get(course)) {
                if(--inDegree[nextCourse] == 0) {
                    queue.addLast(nextCourse);
                }
            }
        }

        return numCourses == 0;
    }

    /**
     * DFS
     * 3 ms 99.29%    38.8 MB 87.83%
     */
    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {

        // 邻接表
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for(int[] pair : prerequisites) {
            adj.get(pair[1]).add(pair[0]);
        }

        int[] visited = new int[numCourses];

        // 对每一门课都看有没有环
        for(int i = 0; i < numCourses; i++) {
            if(!dfs(adj, visited, i)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<List<Integer>> adj, int[] visited, int courseNum) {

        // base case
        // 如果在当前轮次已经出现过该课，说明成环了
        if(visited[courseNum] == 1) {
            return false;
        }

        // 如果该课以前（不是本轮次，是其他轮次）遍历过，说明 ok 了
        if(visited[courseNum] == -1) {
            return true;
        }

        visited[courseNum] = 1;
        for(int nextCourse : adj.get(courseNum)) {
            if(!dfs(adj, visited, nextCourse)) {
                return false;
            }
        }
        visited[courseNum] = -1;

        return true;
    }
}
