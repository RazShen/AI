public class ChangeBoardByOperation {
    public static  void MoveBoardByOperation(State board, Operators operation, int x_empty, int y_empty) {
        if (operation == Operators.UP) {
            board.board[x_empty][y_empty] = board.board[x_empty-1][y_empty];
            board.board[x_empty-1][y_empty] = 0;
            board.x_empty = x_empty;
            board.y_empty = y_empty + 1;
        } else if (operation == Operators.LEFT) {
            board.board[x_empty][y_empty] = board.board[x_empty][y_empty-1];
            board.board[x_empty][y_empty-1] = 0;
            board.x_empty = x_empty;
            board.y_empty = y_empty -1;
        } else if (operation == Operators.RIGHT) {
            board.board[x_empty][y_empty] = board.board[x_empty][y_empty+1];
            board.board[x_empty][y_empty+1] = 0;
        } else if (operation == Operators.DOWN) {
            board.board[x_empty][y_empty] = board.board[x_empty+1][y_empty];
            board.board[x_empty+1][y_empty] = 0;
            board.x_empty = x_empty +1;
            board.y_empty = y_empty;
        }

    }
}
