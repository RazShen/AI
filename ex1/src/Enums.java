/**
 * This class contains all the enums.
 */
public class Enums {
    public enum SolvingAlgorithms {
        IDS, BFS, A_STAR
    }

    public enum Operators {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * For returning the path, this function makes the enum shorter.
     *
     * @param operator RIGHT/UP/LEFT/DOWN
     * @return R/U/L/D
     */
    public static String getShortEnum(Enums.Operators operator) {
        return operator.name().substring(0, 1);
    }
}
