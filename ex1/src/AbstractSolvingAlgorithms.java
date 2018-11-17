import java.util.List;


/**
 * Class for abstract searching algorithm.
 * Contains all methods that each searching algorithm has.
 */
public abstract class AbstractSolvingAlgorithms implements ISolvingAlgorithm {
    protected List<State> closedList;
    protected int cost = 0;
    protected State currentState;

    /**
     * This function searches the graph for the goal state.
     *
     * @return true/false found/didn't found a goal state.
     */
    abstract public boolean search();

    /**
     * This function returns the path from the goal state to the parents, easy implemented because
     * each state saves the operation from its parent to it.
     *
     * @return the path as a string.
     */
    public String getPath() {
        State temp = this.currentState;
        StringBuilder stringBuilder = new StringBuilder();
        while (temp != null && temp.getParent() != null) {
            stringBuilder.insert(0, Enums.getShortEnum(temp.getOperationToMe()));
            temp = temp.getParent();
        }
        return stringBuilder.toString();
    }

    /**
     * Return close list size.
     *
     * @return close list size.
     */
    public int getCloseListSize() {
        return closedList.size();
    }

    /**
     * Returns the specific cost of the algorithm.
     *
     * @return the specific cost.
     */
    public int getSpecificCost() {
        return cost;
    }


}
