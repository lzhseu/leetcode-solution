package leetcode.算法.回溯;

import org.junit.Test;

/**
 * @author lzh
 * @date 2020/9/15 17:52
 */
public class 解数独_37 {

    @Test
    public void test() {
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};

        solveSudoku(board);
    }

    private boolean isFound = false;

    public void solveSudoku(char[][] board) {

        if(board == null || board.length != 9 || board[0].length != 9) {
            throw new RuntimeException("invalid input");
        }

        boolean line[][] = new boolean[9][9];
        boolean column[][] = new boolean[9][9];
        boolean block[][][] = new boolean[3][3][9];

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                char c = board[i][j];
                if(c != '.') {
                    line[i][c - '0' - 1] = true;
                    column[j][c - '0' - 1] = true;
                    block[i / 3][j / 3][c - '0' - 1] = true;
                }
            }
        }

        dfs(board, line, column, block, 0);
    }

    private void dfs(char[][] board, boolean[][] line, boolean[][] column, boolean[][][] block,  int pos) {

        //终止条件：pos 达到了最大值
        if(pos == 81) {
            isFound = true;
            return;
        }

        int r = pos / 9;
        int c = pos - r * 9;

        if(board[r][c] != '.') {
            dfs(board, line, column, block, pos + 1);
        } else {

            for(int i = 0; i < 9; i++) {
                if(line[r][i] || column[c][i] || block[r / 3][c / 3][i]) {
                    continue;
                }

                board[r][c] = (char)('0' + i + 1);
                line[r][i] = true;
                column[c][i] = true;
                block[r / 3][c / 3][i] = true;

                dfs(board, line, column, block, pos + 1);

                if(isFound) {
                    break;
                }

                board[r][c] = '.';
                line[r][i] = false;
                column[c][i] = false;
                block[r / 3][c / 3][i] = false;
            }
        }

    }
}
