public class Enums {
    public enum SolvingAlgorithms {
        IDS, BFS, A_STAR
    }
    public enum Operators {
        UP, DOWN, LEFT, RIGHT
    }

    public static String getShortEnum(Enums.Operators operator) {
        return operator.name().substring(0,1);
    }
}
