import java.util.*;

/**
 * Class for the IDS algorithm.
 */
public class IDS extends AbstractSolvingAlgorithms {
    public Stack<State> openList;
    private State root;

    /**
     * This function searches the graph for the goal state using a stack.
     * this function actually operate the searchIteration() with bigger length each time.
     *
     * @return true/false found/didn't found a goal state.
     */
    public boolean search() {
        int limit = 0;
        while (!searchIteration(limit)) {
            this.currentState = root;
            this.openList.clear();
            this.closedList.clear();
            limit++;
        }
        cost = this.currentState.depth;
        return true;
    }

    /**
     * Search the grapth by DFS with a limit (might be called more than once in the algorithm).
     *
     * @param limit for search depth.
     * @return found/didn't found goal/
     */
    public boolean searchIteration(int limit) {
        this.openList.push(currentState);
        while (!openList.isEmpty()) {
            State top = this.openList.pop();
            this.currentState = top;
            this.closedList.add(this.currentState);
            if (top.isGoal()) {
                return true;
            }
            if (this.currentState.depth == limit)
                continue;
            List<State> successors = this.currentState.getSuccessors();
            for (int index = successors.size() - 1; index >= 0; index--) {
//                boolean existsInClosedList = false;
//                for (State exploredState : closedList) {
//                    if (State.compareStates(state, exploredState)) {
//                        existsInClosedList = true;
//                    }
//                }
//                if (!existsInClosedList) {
                this.openList.push(successors.get(index));
            }
        }
        return false;
    }

    /**
     * IDS constructor.
     *
     * @param rootBoard from input.txt
     * @param size      of the board.
     */
    public IDS(Integer[][] rootBoard, int size) {
        State rootState = new State();
        rootState.size = size;
        rootState.board = rootBoard;
        rootState.depth = 0;
        rootState.parentBoard = null;
        this.root = rootState;
        rootState.initEmpty();
        this.openList = new Stack<>();
        this.closedList = new ArrayList<>();
        this.currentState = rootState;
        this.cost = 0;
    }
}
