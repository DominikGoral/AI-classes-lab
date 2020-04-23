import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Problem<T> {

    public T problem;

    public abstract Boolean isCorrect(int row, int col, int number);
    public abstract int[][] getProblemData();
    public abstract ArrayList<ArrayList<Integer>> createDomains();


//    public Boolean solve() {
//        int [][] data = getProblemData();
//
//
//    }
}
