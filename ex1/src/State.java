
import java.util.ArrayList;

public class State {
    Integer[][] board;
    int size;
    State parentBoard;
    Operators operationToMe;
    Integer x_empty;
    Integer y_empty;
    public State() {

    }

    public State(State parentBoard, Operators operation) {
        this.size = parentBoard.size;
        this.operationToMe = operation;
        this.parentBoard = parentBoard;
        copyBoardAndOperate(parentBoard.board);
    }

    private void copyBoardAndOperate(Integer[][] inputBoard) {
        board = new Integer[size][size];
        int x_empty_input = 0;
        int y_empty_input = 0;
        for (Integer i = 0; i < size; i++) {
            for (Integer j = 0; j <size; j++) {
                board[i][j] = inputBoard[i][j];
                if (inputBoard[i][j] == 0) {
                    x_empty_input = i;
                    y_empty_input = j;
                }
            }
        }
        ChangeBoardByOperation.MoveBoardByOperation(this, this.operationToMe, x_empty_input, y_empty_input);
    }

    public ArrayList<Operators> getValidOperators() {
        ArrayList<Operators> validOperator = new ArrayList<>();
        if (x_empty > 0) {
            validOperator.add(Operators.UP);
        }
        if (x_empty < size) {
            validOperator.add(Operators.DOWN);
        }
        if (y_empty < size) {
            validOperator.add(Operators.RIGHT);
        }
        if (y_empty > 0) {
            validOperator.add(Operators.LEFT);
        }
        return validOperator;
    }


    public boolean isGoal() {
        boolean isGoal = true;
        int counter = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] != counter && i != this.size -1 && j!= this.size -1) {
                    isGoal = false;
                    break;
                }
                counter++;
            }
        }
        if (this.x_empty!= size-1 || this.y_empty!=size-1) {
            isGoal = false;
        }
        return isGoal;
    }
}
