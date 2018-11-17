/**
 * This class purpose is to change the accepted board by the operation received.
 * Contains 1 static method.
 */
public class ChangeBoardByOperation {
    /**
     * Change the input board by the operation received.
     *
     * @param board     to change
     * @param operation to change by
     * @param x_empty   X coordinate of the empty cell in the board
     * @param y_empty   Y coordinate of the empty cell in the board
     */
    public static void MoveBoardByOperation(State board, Enums.Operators operation, int x_empty, int y_empty) {
        if (operation == Enums.Operators.DOWN) {
            board.board[x_empty][y_empty] = board.board[x_empty - 1][y_empty];
            board.board[x_empty - 1][y_empty] = 0;
        } else if (operation == Enums.Operators.RIGHT) {
            board.board[x_empty][y_empty] = board.board[x_empty][y_empty - 1];
            board.board[x_empty][y_empty - 1] = 0;
        } else if (operation == Enums.Operators.LEFT) {
            board.board[x_empty][y_empty] = board.board[x_empty][y_empty + 1];
            board.board[x_empty][y_empty + 1] = 0;
        } else if (operation == Enums.Operators.UP) {
            board.board[x_empty][y_empty] = board.board[x_empty + 1][y_empty];
            board.board[x_empty + 1][y_empty] = 0;
        }

    }
}
