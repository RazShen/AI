import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class represent a state (a node in the graph)
 * a state contains a board, parent, operator from parent to board and depth.
 */
public class State {
    Integer[][] board;
    int size;
    State parentBoard;
    Enums.Operators operationToMe;
    Integer x_empty;
    Integer y_empty;
    Integer depth;
    int timestamp;



    /**
     * Empty constructor (used for root)
     */
    public State() {

    }

    /**
     * Find the empty spot in the board.
     */
    public void initEmpty() {
        for (Integer i = 0; i < size; i++) {
            for (Integer j = 0; j < size; j++) {
                if (this.board[i][j] == 0) {
                    x_empty = i;
                    y_empty = j;
                }
            }
        }
    }

    /**
     * get the parent state.
     *
     * @return parent
     */
    public State getParent() {
        return parentBoard;
    }

    /**
     * Get the operation that was invoked from the parent to this state.
     *
     * @return
     */
    public Enums.Operators getOperationToMe() {
        return this.operationToMe;
    }

    /**
     * Constructor from parent state.
     *
     * @param parentBoard parent state.
     * @param operation   operation from parent to this state.
     */
    public State(State parentBoard, Enums.Operators operation) {
        this.size = parentBoard.size;
        this.depth = parentBoard.depth + 1;
        this.operationToMe = operation;
        this.parentBoard = parentBoard;
        copyBoardAndOperate(parentBoard.board);
    }

    /**
     * Copy the board of parent and do the operation (R/L/U/D)
     *
     * @param inputBoard of the parent.
     */
    private void copyBoardAndOperate(Integer[][] inputBoard) {
        board = new Integer[size][size];
        int xEmptyParent = 0;
        int yEmptyParent = 0;
        for (Integer i = 0; i < size; i++) {
            for (Integer j = 0; j < size; j++) {
                board[i][j] = inputBoard[i][j];
                if (inputBoard[i][j] == 0) {
                    xEmptyParent = i;
                    yEmptyParent = j;
                }
            }
        }
        ChangeBoardByOperation.MoveBoardByOperation(this, this.operationToMe, xEmptyParent, yEmptyParent);
        initEmpty();
    }

    /**
     * Return all the possible operators from this state.
     *
     * @return all the possible operators from this state.
     */
    public ArrayList<Enums.Operators> getValidOperators() {
        ArrayList<Enums.Operators> validOperator = new ArrayList<>();
        if (x_empty < size - 1) {
            validOperator.add(Enums.Operators.UP);
        }
        if (x_empty > 0) {
            validOperator.add(Enums.Operators.DOWN);
        }
        if (y_empty < size - 1) {
            validOperator.add(Enums.Operators.LEFT);
        }
        if (y_empty > 0) {
            validOperator.add(Enums.Operators.RIGHT);
        }
        return validOperator;
    }

    /**
     * Return the successors of this state. (by doing the possible operations, each operation leads to another son).
     *
     * @return the successors of this state.
     */
    public List<State> getSuccessors() {
        List<State> successors = new ArrayList<>();
        for (Enums.Operators operator : getValidOperators()) {
            successors.add(new State(this, operator));
        }
        return successors;
    }

    /**
     * Check if this is a goal state.
     *
     * @return true/false if this is a goal state.
     */
    public boolean isGoal() {
        boolean isGoal = true;
        int counter = 1;
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                if (this.board[i][j] != counter && !(i == this.size - 1 && j == this.size - 1)) {
                    return false;
                }
                counter++;
            }
        }
        if (this.x_empty != size - 1 || this.y_empty != size - 1) {
            isGoal = false;
        }
        return isGoal;
    }

    /**
     * Check if 2 states are equal.
     *
     * @param state1 state
     * @param state2 state
     * @return if equal.
     */
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
