package leetcode.算法.贪心;

import org.junit.Test;
import org.w3c.dom.Node;

import java.util.*;

/**
 * @author lzh
 * @date 2021/1/8 18:59
 */
public class 任务调度器_621 {


    /**
     * 桶思想 来自 https://leetcode-cn.com/problems/task-scheduler/solution/tong-zi-by-popopop/
     * 2 ms 96.97%    39.6 MB 75.85%
     */
    public int leastInterval(char[] tasks, int n) {

        int[] taskCount = new int[26];

        for(char c : tasks) {
            taskCount[c - 'A']++;
        }

        Arrays.sort(taskCount);

        int maxCount = taskCount[25];
        int count = 1;
        for(int i = 24; i >= 0; i--) {
            if(taskCount[i] == maxCount) {
                count++;
            } else {
                break;
            }
        }

        return Math.max((maxCount - 1) * (n + 1) + count, tasks.length);
    }


    /**
     * 模拟 CPU 的时间片
     * 56 ms 17.14%    39.6 MB 73.65%
     */
    public int leastInterval2(char[] tasks, int n) {

        if(n == 0) {
            return tasks.length;
        }

        Map<Character, Integer> tasksMap = new HashMap<>();
        // 存放的是可以执行的时间
        List<Integer> nextExecTimeList = new ArrayList<>();
        // 存放的是该task剩余的数量
        List<Integer> restTaskList = new ArrayList<>();

        // 初始化
        for(char c : tasks) {
            tasksMap.put(c, tasksMap.getOrDefault(c, 0) + 1);
        }

        for(Map.Entry<Character, Integer> entry : tasksMap.entrySet()) {
            nextExecTimeList.add(1);
            restTaskList.add(entry.getValue());
        }

        // 任务种类数目
        int taskTypes = nextExecTimeList.size();

        // 模拟一个时间片
        int time = 0;

        for(int i = 0; i < tasks.length; i++) {

            ++time;

            // 先找到最小的有效执行时间
            int minNextValid = Integer.MAX_VALUE;
            for(int j = 0; j < taskTypes; j++) {
                if(restTaskList.get(j) != 0) {
                    minNextValid = Math.min(nextExecTimeList.get(j), minNextValid);
                }
            }

            time = Math.max(time, minNextValid);

            // 找到在有效执行之间前的所有任务中，任务数量最多的
            // 就是最佳的执行任务
            int bestTask = -1;

            for(int j = 0; j < taskTypes; j++) {
                if(restTaskList.get(j) != 0 && nextExecTimeList.get(j) <= time) {
                    if(bestTask == -1 || restTaskList.get(j) > restTaskList.get(bestTask)) {
                        bestTask = j;
                    }
                }
            }

            // 更新任务的信息
            nextExecTimeList.set(bestTask, time + n + 1);
            restTaskList.set(bestTask, restTaskList.get(bestTask) - 1);

        }

        return time;

    }

    /**
     * 傻逼的失败版本：模拟的思想，可惜选错了数据结构，选了优先级队列，然而它并没有可以实时调整元素的方法
     * 而且模拟的不够好，需要每次都遍历数据结构中的数据去更新
     */
    public int leastInterval_fail(char[] tasks, int n) {

        if(n == 0) {
            return tasks.length;
        }

        Map<Character, Integer> tasksMap = new HashMap<>();
        Queue<Node> coolTimeQueue = new PriorityQueue<>((n1, n2) -> {
            if(n1.coolTime == n2.coolTime) {
                return n2.count - n1.count;
            } else {
                return n1.coolTime - n2.coolTime;
            }
        });

        Set<Character> set = new HashSet<>();

        int result = 0;

        // 初始化
        for(char c : tasks) {
            tasksMap.put(c, tasksMap.getOrDefault(c, 0) + 1);
            set.add(c);
        }

        for(char c : set) {
            coolTimeQueue.add(new Node(c, 0, tasksMap.get(c)));
        }

        int count = tasks.length;
        while(count > 0) {

            Node currNode = coolTimeQueue.peek();
            char currTask = currNode.task;
            int coolTime = currNode.coolTime;

            if(coolTime == 0) {

                // 该任务是否还有
                int currTaskCount = tasksMap.get(currTask);
                if(currTaskCount > 0) {

                    // 执行此任务
                    count--;

                    // 时间结果加1
                    result++;

                    // 将队列中冷却时间不为 0 的都减一
                    Iterator<Node> it = coolTimeQueue.iterator();
                    while(it.hasNext()) {
                        Node temp = it.next();
                        if (temp.coolTime > 0) {
                            --temp.coolTime;
                        }
                    }

                    // 减少任务池中的任务数
                    tasksMap.put(currTask, --currTaskCount);

                    // 将该任务的冷却时间置为 n
                    coolTimeQueue.poll();
                    if(currTaskCount > 0) {
                        currNode.coolTime = n;
                        --currNode.count;
                        coolTimeQueue.add(currNode);
                    }

                }
            } else {

                result++;
                // 将队列中冷却时间不为 0 的都减一
                Iterator<Node> it = coolTimeQueue.iterator();
                while(it.hasNext()) {
                    --it.next().coolTime;
                }

            }
        }

        return result;
    }

    class Node {

        private char task;
        private int coolTime;
        private int count;

        public Node(char task, int coolTime, int count) {
            this.task = task;
            this.coolTime = coolTime;
            this.count = count;
        }

    }


    @Test
    public void test() {

        // ["A","A","A","A","A","A","B","C","D","E","F","G"]
        char[] tasks = new char[]{'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        System.out.println(leastInterval(tasks, 2));


    }
}
