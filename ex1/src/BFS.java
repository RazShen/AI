import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS extends AbstractSolivngAlgorithms {
    public Queue<State> openList;

    public boolean search() {
        this.openList.add(currentState);
        while (!openList.isEmpty()) {
            State top = this.openList.remove();
            this.currentState = top;
            this.closedList.add(top);
            if (top.isGoal()) {
                return true;
            }
            List<State> successors = this.currentState.getSuccessors();
            for (State state : successors) {
                boolean existsInClosedList = false;
                for (State exploredState : closedList) {
                    if (State.compareStates(state, exploredState)) {
                        existsInClosedList = true;
                    }
                }
                if (!existsInClosedList) {
                    this.openList.add(state);
                }
            }
        }
        return false;
    }

    public BFS(Integer[][] rootBoard, int size) {
        State rootState = new State();
        rootState.size = size;
        rootState.board = rootBoard;
        rootState.parentBoard = null;
        rootState.initEmpty();
        this.openList = new LinkedList<>();
        this.closedList = new ArrayList<>();
        this.currentState =rootState;
    }


}
