import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSolivngAlgorithms implements ISolvingAlgorithm {
    protected List<State> closedList;
    protected int cost;
    protected State currentState;

    abstract public boolean search();
    public String getPath() {
        StringBuilder stringBuilder = new StringBuilder();
        while (currentState!=null && currentState.getParent()!=null) {
            stringBuilder.insert(0,Enums.getShortEnum(currentState.getOperationToMe()));
            currentState = currentState.getParent();
        }
        return stringBuilder.toString();
    }


    public int getCloseListSize(){
        return closedList.size();
    }
    public int getSpecificCost() {
        return cost;
    }





}
