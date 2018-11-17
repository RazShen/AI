import java.util.*;

/**
 * Class for the AStar algorithm.
 * Uses best first search with f(n) = g(n) + h(n) and heuristics function of manhattan distance.
 */
public class AStar extends AbstractSolvingAlgorithms {
    public PriorityQueue<State> openList;

    /**
     * This function searches the graph for the goal state using a priority queue (sorts by f = g+h function for each node).
     *
     * @return true/false found/didn't found a goal state.
     */
    public boolean search() {
        this.openList.add(currentState);
        while (!openList.isEmpty()) {
            State top = this.openList.remove();
            this.currentState = top;
            this.closedList.add(this.currentState);
            if (top.isGoal()) {
                return true;
            }
            List<State> successors = this.currentState.getSuccessors();
            for (State state : successors) {
                this.openList.add(state);
            }
        }
        return false;
    }


    /**
     * AStar constructor.
     *
     * @param rootBoard from input.txt
     * @param size      of the board.
     */
    public AStar(Integer[][] rootBoard, int size) {
        State rootState = new State();
        rootState.size = size;
        rootState.board = rootBoard;
        rootState.depth = 0;
        rootState.parentBoard = null;
        rootState.initEmpty();
        this.openList = new PriorityQueue<>(new ComparatorByManhattan());
        this.closedList = new ArrayList<>();
        this.currentState = rootState;
        this.cost = 0;
    }


    @Override
    /**
     * By the f function of all the nodes in the path.
     */
    public int getSpecificCost() {
        State temp = this.currentState;
        while (temp != null) {
            this.cost += f(temp);
            temp = temp.getParent();
        }
        return this.cost;
    }

    /**
     * The f function to evaluate each node in AStar is f(n) = g(n) + h(n)
     *
     * @param board to evaluate.
     * @return f(board).
     */
    public int f(State board) {
        int sum = 0;
        int n = board.size;
        sum += board.depth;  //calculate g(n)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board.board[i][j] == 0) {
                    int supposedX = n - 1;
                    int supposedY = n - 1;
                    // sum += Math.abs(supposedX - i) + Math.abs(supposedY - j); //Suppose we shouldn't sum heuristics for empty spot
                } else {
                    int supposedX = (int) Math.ceil((float) board.board[i][j] / n) - 1;
                    int supposedY = ((board.board[i][j] % n) + n - 1) % n;
                    sum += Math.abs(supposedX - i) + Math.abs(supposedY - j);
                }
            }
        }
        return sum;
    }


    /**
     * Private class for implementing a comparator for the priority queue sort.
     */
    private class ComparatorByManhattan implements Comparator<State> {
        @Override
        /**
         * Compare 2 nodes by f function.
         */
        public int compare(State board1, State board2) {
            return f(board1) - f(board2);
        }

    }

}
