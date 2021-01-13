package leetcode.算法.DFS_BFS;

import org.junit.Test;

import java.util.*;

/**
 * @author lzh
 * @date 2021/1/4 9:59
 */
public class 删除无效的括号_301 {

    /**
     * DFS
     */
    public List<String> removeInvalidParentheses1(String s) {

        List<String> resList = new ArrayList<>();
        if(s == null) {
            return resList;
        }

        StringBuilder path = new StringBuilder();
        Set<String> resSet = new HashSet<>();

        dfs(resSet, s, path, 0, 0);
        resList.addAll(resSet);
        return resList;
    }

    private int maxLen = 0;

    /**
     * 第一版的 DFS 写得很随性
     * 163 ms 5.58%    38.7 MB 58.70%
     */
    private void dfs1(Set<String> resSet, String s, StringBuilder path, int index, int count) {

        if(index == s.length()) {

            if(count == 0) {
                if(resSet.isEmpty()) {
                    resSet.add(path.toString());
                    return;
                }

                int oldLen = maxLen;
                int newLen = path.length();
                if(oldLen < newLen) {
                    resSet.clear();
                    resSet.add(path.toString());
                    maxLen = newLen;
                } else if(oldLen == newLen) {
                    resSet.add(path.toString());
                }
            }

            return;
        }

        char c = s.charAt(index);

        // 选择删除
        if(c == '(' || c == ')') {
            dfs1(resSet, s, path, index + 1, count);
        } else {
            path.append(c);
            dfs1(resSet, s, path, index + 1, count);
            path.deleteCharAt(path.length() - 1);
        }



        // 不选择删除

        if(c == '(') {
            count++;
        } else if(c == ')') {
            count--;
        }

        if(count < 0) {
            return;
        }

        // 不删除
        path.append(c);
        dfs1(resSet, s, path, index + 1, count);

        // 删除
        path.deleteCharAt(path.length() - 1);
    }


    /**
     * 第二版 DFS
     * 51 ms 41.07%    38.5 MB 70.26%
     */
    private void dfs(Set<String> resSet, String s, StringBuilder path, int index, int count) {

        if(count < 0) {
            return;
        }

        if(index == s.length()) {

            if(count == 0) {
                int newLen = path.length();
                if(maxLen < newLen) {
                    resSet.clear();
                    resSet.add(path.toString());
                    maxLen = newLen;
                } else if(maxLen == newLen) {
                    resSet.add(path.toString());
                }
            }

            return;
        }

        char c = s.charAt(index);

        // 不选择删除
        path.append(c);
        if(c == '(') {
            dfs(resSet, s, path, index + 1, count + 1);
        } else if(c == ')') {
            dfs(resSet, s, path, index + 1, count - 1);
        } else {
            dfs(resSet, s, path, index + 1, count);
        }
        path.deleteCharAt(path.length() - 1);

        // 选择删除
        if(c == '(' || c == ')') {
            dfs(resSet, s, path, index + 1, count);
        }
    }


    /**
     * 改进 DFS，进行剪枝
     * 5 ms 69.94%    38.6 MB 68.67%
     */
    public List<String> removeInvalidParentheses2(String s) {

        List<String> resList = new ArrayList<>();
        if(s == null) {
            return resList;
        }

        int rmLeft = 0, rmRight = 0;
        for(char c : s.toCharArray()) {
            if(c == '(') {
                rmLeft++;
            } else if(c == ')') {
                if(rmLeft > 0) {
                    rmLeft--;
                } else {
                    rmRight++;
                }
            }
        }

        StringBuilder path = new StringBuilder();
        Set<String> resSet = new HashSet<>();

        dfs(resSet, s, path, 0, 0, rmLeft, rmRight);
        resList.addAll(resSet);
        return resList;
    }

    private void dfs(Set<String> resSet, String s, StringBuilder path, int index, int count, int rmLeft, int rmRight) {

        if(count < 0 || rmLeft < 0 || rmRight < 0) {
            return;
        }

        if(index == s.length()) {

            if(count == 0) {

                int newLen = path.length();
                if(maxLen < newLen) {
                    resSet.clear();
                    resSet.add(path.toString());
                    maxLen = newLen;
                } else if(maxLen == newLen) {
                    resSet.add(path.toString());
                }
            }

            return;
        }

        char c = s.charAt(index);

        // 不选择删除
        path.append(c);
        if(c == '(') {
            dfs(resSet, s, path, index + 1, count + 1, rmLeft, rmRight);
        } else if(c == ')') {
            dfs(resSet, s, path, index + 1, count - 1, rmLeft, rmRight);
        } else {
            dfs(resSet, s, path, index + 1, count, rmLeft, rmRight);
        }
        path.deleteCharAt(path.length() - 1);

        // 选择删除
        if(c == '(') {
            dfs(resSet, s, path, index + 1, count, rmLeft - 1, rmRight);
        } else if(c == ')') {
            dfs(resSet, s, path, index + 1, count, rmLeft, rmRight - 1);
        }
    }


    /**
     * BFS
     * 66 ms 26.40%    39.4 MB 21.68%
     */
    public List<String> removeInvalidParentheses3(String s) {

        List<String> resList = new ArrayList<>();
        if(s == null) {
            return resList;
        }

        Set<String> visited = new HashSet<>();
        Deque<String> deque = new ArrayDeque<>();

        deque.addLast(s);

        while(true) {

            int size = deque.size();

            // 考虑当前层
            for(int i = 0; i < size; i++) {

                String currStr = deque.removeFirst();

                if(isValid(currStr)) {
                    resList.add(currStr);
                } else if(resList.isEmpty()) {

                    // 生成下一层，多删除一个括号
                    for(int j = 0; j < currStr.length(); j++) {
                        char c = currStr.charAt(j);
                        if(c == '(' || c == ')') {
                            String nextStr = currStr.substring(0, j) + currStr.substring(j + 1);
                            if(!visited.contains(nextStr)) {
                                deque.addLast(nextStr);
                                visited.add(nextStr);
                            }
                        }
                    }
                }
            }

            if(!resList.isEmpty()) {
                break;
            }
        }

        return resList;
    }


    private boolean isValid(String str) {
        int count = 0;
        for(char c : str.toCharArray()) {
            if(c == '(') {
                count++;
            } else if(c == ')') {
                count--;
            }

            if(count < 0) {
                return false;
            }
        }

        return count == 0;
    }

    @Test
    public void test() {
        System.out.println(removeInvalidParentheses1("()"));
    }
}
