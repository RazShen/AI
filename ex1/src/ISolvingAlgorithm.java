/**
 * Interface for every searching algorithm.
 */
public interface ISolvingAlgorithm {
    /**
     * Search the graph method.
     *
     * @return found/didn't found goal.
     */
    boolean search();

    /**
     * Return the path to goal.
     *
     * @return the path to goal.
     */
    String getPath();

    /**
     * Return close list size.
     *
     * @return close list size.
     */
    int getCloseListSize();

    /**
     * Returns the specific cost of the algorithm.
     *
     * @return the specific cost.
     */
    int getSpecificCost();
}



