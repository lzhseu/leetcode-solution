package 剑指offer;

/**
 * @author lzh
 * @date 2021/1/13 19:32
 */
public class _12_矩阵中的路径 {

    public boolean exist(char[][] board, String word) {

        if(word == null) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;

        boolean[][] visited = new boolean[rows][cols];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(traceBack(board, i, j, word, 0, visited)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean traceBack(char[][] board, int ridx, int cidx, String word, int index, boolean[][] visited) {

        // 如果字符不相等，返回 false
        if(word.charAt(index) != board[ridx][cidx]) {
            return false;
        }

        if(word.length() - 1 == index) {
            return true;
        }

        // 到这里就说明此字符是相等的，可以进一步探索路径

        visited[ridx][cidx] = true;

        boolean flag = false;

        // go left
        if(cidx - 1 >= 0 && !visited[ridx][cidx - 1]) {
            flag = flag || traceBack(board, ridx, cidx - 1, word, index + 1, visited);
        }

        // go right
        if(cidx + 1 < board[0].length && !visited[ridx][cidx + 1]) {
            flag = flag || traceBack(board, ridx, cidx + 1, word, index + 1, visited);
        }

        // go up
        if(ridx - 1 >= 0 && !visited[ridx - 1][cidx]) {
            flag = flag || traceBack(board, ridx - 1, cidx, word, index + 1, visited);
        }

        // go down
        if(ridx + 1 < board.length && !visited[ridx + 1][cidx]) {
            flag = flag || traceBack(board, ridx + 1, cidx, word, index + 1, visited);
        }

        // 回溯
        visited[ridx][cidx] = false;

        return flag;

    }
}
