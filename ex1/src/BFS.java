import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Class for the BFS algorithm.
 * Using a normal queue (fifo)
 */
public class BFS extends AbstractSolvingAlgorithms {
    public Queue<State> openList;


    /**
     * This function searches the graph for the goal state using a queue.
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
                // for checking closed list.
//                boolean existsInClosedList = false;
//                for (State exploredState : closedList) {
//                    if (State.compareStates(state, exploredState)) {
//                        existsInClosedList = true;
//                    }
//                }
//                if (!existsInClosedList) {
                this.openList.add(state);
            }
        }
        return false;
    }

    /**
     * BFS constructor.
     *
     * @param rootBoard from input.txt
     * @param size      of the board.
     */
    public BFS(Integer[][] rootBoard, int size) {
        State rootState = new State();
        rootState.size = size;
        rootState.board = rootBoard;
        rootState.depth = 0;
        rootState.parentBoard = null;
        rootState.initEmpty();
        this.openList = new LinkedList<>();
        this.closedList = new ArrayList<>();
        this.currentState = rootState;
        this.cost = 0;
    }


}
