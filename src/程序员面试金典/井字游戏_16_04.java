package 程序员面试金典;

/**
 * @author lzh
 * @date 2020/7/21 8:59
 */
public class 井字游戏_16_04 {

    /**
     * 相加判断
     * 5ms 61.37%    37.4M 100%
     */
    public String tictactoe(String[] board) {

        int row = 0, col = 0, left = 0, right = 0;
        boolean flag = false;
        int N = board.length;

        for(int i = 0; i < N; i++) {
            row = 0;
            col = 0;
            for(int j = 0; j < N; j++) {
                row += board[i].charAt(j);
                col += board[j].charAt(i);
                if(board[i].charAt(j) == ' ') {
                    flag = true;
                }
            }
            if(row == N*'X' || col == N*'X') return "X";
            if(row == N*'O' || col == N*'O') return "O";

            // 两条斜线相加
            left += board[i].charAt(i);
            right += board[i].charAt(N - i - 1);
        }

        if(left == N*'X' || right == N*'X') return "X";
        if(left == N*'O' || right == N*'O') return "O";

        if(flag) return "Pending";
        return "Draw";
    }
}
