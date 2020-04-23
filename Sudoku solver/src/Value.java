import java.util.ArrayList;
import java.util.Collections;

public class Value {

    public static ArrayList<Integer> rarestValue(int row, int col, ArrayList<ArrayList<Integer>> domains, ArrayList<Integer> chosenDomain) {
        ArrayList<Integer> rarestValue = new ArrayList<>();
        int [] howMany = new int[chosenDomain.size()];

        // Sprawdzenie dziedzin w wierszach
        for(int i = row * 9; i < (row * 9) + 9; i++) {
            ArrayList<Integer> domain = domains.get(i);
            for(int j = 0; j < domain.size(); j++) {
                int possibleValue = domain.get(j);
                for(int k = 0; k < chosenDomain.size(); k++) {
                    if(possibleValue == chosenDomain.get(k)) {
                        howMany[k]++;
                    }
                }
            }
        }
        // Sprawdzenie dziedzin w kolumnach
        for(int i = col; i < 81; i = i + 9) {
            ArrayList<Integer> domain = domains.get(i);
            for(int j = 0; j < domain.size(); j++) {
                int possibleValue = domain.get(j);
                for(int k = 0; k < chosenDomain.size(); k++) {
                    if(possibleValue == chosenDomain.get(k)) {
                        howMany[k]++;
                    }
                }
            }
        }
        // Sprawdzanie kwadratu
        int firstRowInSquare = row - row % 3;
        int firstColInSquare = col - col % 3;

        for(int i = firstRowInSquare; i < firstRowInSquare + 3; i++) {
            for(int j = firstColInSquare; j < firstColInSquare + 3; j++) {
                ArrayList<Integer> domain = domains.get(i * 9 + j);
                for(int k = 0; k < domain.size(); k++) {
                    int possibleValue = domain.get(k);
                    for(int l = 0; l < chosenDomain.size(); l++) {
                        if(possibleValue == chosenDomain.get(l)) {
                            howMany[l]++;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < howMany.length; i++) {
            int indexOfTheLowest = Tools.lowestFromTheTable(howMany);
            rarestValue.add(chosenDomain.get(indexOfTheLowest));
            howMany[indexOfTheLowest] = 10;
        }

        return rarestValue;
    }

    public static ArrayList<Integer> randomValue(ArrayList<Integer> domain) {
        Collections.shuffle(domain);
        return domain;
    }

    public static ArrayList<Integer> firstCreatedFirstTaken(ArrayList<Integer> domain) {
        return domain;
    }

    public static ArrayList<Integer> lastCreatedFirstTaken(ArrayList<Integer> domain) {
        ArrayList<Integer> result = new ArrayList<>();
        for(int i = domain.size() - 1; i >= 0; i--) {
            result.add(domain.get(i));
        }
        return result;
    }

}
