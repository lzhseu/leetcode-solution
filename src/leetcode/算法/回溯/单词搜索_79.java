package leetcode.算法.回溯;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lzh
 * @date 2020/10/16 19:50
 */
public class 单词搜索_79 {
    @Test
    public void test() {
        char[][] board = {{'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D','E', 'E'}};
        String word = "ABCESEEEFS";
        boolean flag = exist(board, word);
        System.out.println(flag);
    }

    public boolean exist(char[][] board, String word) {

        int rows = board.length;
        int cols = board[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(dfs(board, word, 0, visited, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int idx, boolean[][] visited, int r, int c) {

        if(word.charAt(idx) != board[r][c]) {
            return false;
        } else if(idx == word.length() - 1) {
            return true;
        }

        // 继续递归寻找
        boolean flag = false;

        visited[r][c] = true;

        if(r - 1 >= 0 && !visited[r-1][c]) {
            flag = flag || dfs(board, word, idx + 1, visited, r - 1, c);
        }
        if(r + 1 < board.length && !visited[r + 1][c]) {
            flag = flag || dfs(board, word, idx + 1, visited, r + 1, c);
        }
        if(c - 1 >= 0 && !visited[r][c - 1]) {
            flag = flag || dfs(board, word, idx + 1, visited, r, c - 1);
        }
        if(c + 1 < board[0].length && !visited[r][c + 1]) {
            flag = flag || dfs(board, word, idx + 1, visited, r, c + 1);
        }

        visited[r][c] = false;

        return flag;
    }

    @Test
    public void test2() {
        int[] arr = {5, 1, 8};

    }
}
