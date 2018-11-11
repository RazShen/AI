public class ChangeBoardByOperation {
    public static void MoveBoardByOperation(State board, Enums.Operators operation, int x_empty, int y_empty) {
        if (operation == Enums.Operators.UP) {
            board.board[x_empty][y_empty] = board.board[x_empty-1][y_empty];
            board.board[x_empty-1][y_empty] = 0;
        } else if (operation == Enums.Operators.LEFT) {
            board.board[x_empty][y_empty] = board.board[x_empty][y_empty-1];
            board.board[x_empty][y_empty-1] = 0;
        } else if (operation == Enums.Operators.RIGHT) {
            board.board[x_empty][y_empty] = board.board[x_empty][y_empty+1];
            board.board[x_empty][y_empty+1] = 0;
        } else if (operation == Enums.Operators.DOWN) {
            board.board[x_empty][y_empty] = board.board[x_empty+1][y_empty];
            board.board[x_empty+1][y_empty] = 0;
        }

    }
}
