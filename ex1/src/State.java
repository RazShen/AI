

import java.util.ArrayList;
import java.util.List;

public class State {
    Integer[][] board;
    int size;
    State parentBoard;
    Enums.Operators operationToMe;
    Integer x_empty;
    Integer y_empty;

    public State() {

    }

    public void initEmpty() {
        for (Integer i = 0; i < size; i++) {
            for (Integer j = 0; j <size; j++) {
                if (this.board[i][j] == 0) {
                    x_empty = i;
                    y_empty = j;
                }
            }
        }
    }

    public State getParent() {
        return parentBoard;
    }

    public Enums.Operators getOperationToMe() {
        return this.operationToMe;
    }

    public State(State parentBoard, Enums.Operators operation) {
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
        initEmpty();
    }

    public ArrayList<Enums.Operators> getValidOperators() {
        ArrayList<Enums.Operators> validOperator = new ArrayList<>();
        if (x_empty > 0) {
            validOperator.add(Enums.Operators.UP);
        }
        if (x_empty < size -1 ) {
            validOperator.add(Enums.Operators.DOWN);
        }
        if (y_empty < size -1) {
            validOperator.add(Enums.Operators.RIGHT);
        }
        if (y_empty > 0) {
            validOperator.add(Enums.Operators.LEFT);
        }
        return validOperator;
    }

    public List<State> getSuccessors() {
        List<State> successors = new ArrayList<>();
        for (Enums.Operators operator : getValidOperators()) {
            successors.add(new State(this, operator));
        }
        return successors;
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

    public static boolean compareStates(State state1, State state2) {
        for (int i = 0; i < state1.size; i++) {
            for (int j = 0; j < state2.size; j++) {
                if (state1.board[i][j] != state2.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
