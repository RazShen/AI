import java.util.*;

public class AStar extends AbstractSolivngAlgorithms {
    public PriorityQueue<State> openList;

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

    public AStar(Integer[][] rootBoard, int size) {
        State rootState = new State();
        rootState.size = size;
        rootState.board = rootBoard;
        rootState.depth = 0;
        rootState.parentBoard = null;
        rootState.initEmpty();
        this.openList = new PriorityQueue<>(new ComparatorByManhattan());
        this.closedList = new ArrayList<>();
        this.currentState =rootState;
        this.cost = 0;
    }

    private class ComparatorByManhattan implements Comparator<State> {
        @Override
        public int compare(State board1, State board2) {
            return f(board1) - f(board2);
        }

        private int f(State board) {
            int sum = 0;
            int n = board.size;
            sum += board.depth;  //calculate g(n)
            for (int i = 0; i <n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board.board[i][j] == 0) {
                        int supposedX = n -1;
                        int supposedY = n-1;
                        sum += Math.abs(supposedX - i) + Math.abs(supposedY - j);
                    } else {
                        int supposedX = (int) Math.ceil((float) board.board[i][j] / n) - 1;
                        int supposedY = ((board.board[i][j] % n) + n - 1) % n;
                        sum += Math.abs(supposedX - i) + Math.abs(supposedY - j);
                    }
                }
            }
            return sum;
        }
    }

}
