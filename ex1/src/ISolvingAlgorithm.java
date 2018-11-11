import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ISolvingAlgorithm {

    boolean search();

    String getPath();
    int getCloseListSize();
    int getSpecificCost();
}



