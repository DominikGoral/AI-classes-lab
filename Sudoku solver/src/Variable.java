import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Variable {

    public static ArrayList<Integer> chooseVariableWithTheSmallestDomain(ArrayList<ArrayList<Integer>> domains) {
        ArrayList<Integer> result = new ArrayList<>();
        int minDomain = 10;
        int minDomainIndex = 100;

        result.add(-1);
        result.add(-1);
        result.add(-1);

        for(int i = 0; i < domains.size(); i++) {
            if(!(domains.get(i).isEmpty())) {
                if((domains.get(i).size() < minDomain)) {
                    minDomain = domains.get(i).size();
                    minDomainIndex = i;

                    result.set(0, minDomainIndex / 9);
                    result.set(1, minDomainIndex % 9);
                    result.set(2, minDomainIndex);

                }

            }

        }

        //System.out.println(result[0] + " " + result[1] + " " + result[2]);

        return result;
    }

    public static ArrayList<Integer> chooseVariableRandomDomain(ArrayList<ArrayList<Integer>> domains) {
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> notEmptyDomains = new ArrayList<>();

        for(int i = 0; i < domains.size(); i++) {
            if(!domains.get(i).isEmpty()) {
                notEmptyDomains.add(i);
            }
        }

        if(!notEmptyDomains.isEmpty()) {
            Collections.shuffle(notEmptyDomains);
            int drawnDomainIndex = notEmptyDomains.get(0);
            result.add(drawnDomainIndex / 9);
            result.add(drawnDomainIndex % 9);
            result.add(drawnDomainIndex);
        } else {
            result.add(-1);
            result.add(-1);
            result.add(-1);
        }

        return result;
    }

    public static ArrayList<Integer> firstCreatedFirstTaken(int [][] sudokuTab) {

        ArrayList<Integer> result = new ArrayList<>();

        result.add(-1);
        result.add(-1);
        result.add(-1);

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(sudokuTab[i][j] == 0) {
                    int domainIndex = i * 9 + j;
                    result.set(0, domainIndex / 9);
                    result.set(1, domainIndex % 9);
                    result.set(2, domainIndex);
                    return  result;
                }
            }
        }

        return result;
    }
    // My own heuristics
    public static ArrayList<Integer> firstFromTheMostFilledSquare(int [][] sudokuTab) {
        ArrayList<Integer> result = new ArrayList<>();
        int [] squares = new int[9];
        Boolean [] availableSquares = new Boolean[9];

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(sudokuTab[i][j] != 0) {
                    squares[0]++;
                }
                if(sudokuTab[i][j + 3] != 0) {
                    squares[1]++;
                }
                if(sudokuTab[i][j + 6] != 0) {
                    squares[2]++;
                }
                if(sudokuTab[i + 3][j] != 0) {
                    squares[3]++;
                }
                if(sudokuTab[i + 3][j + 3] != 0) {
                    squares[4]++;
                }
                if(sudokuTab[i + 3][j + 6] != 0) {
                    squares[5]++;
                }
                if(sudokuTab[i + 6][j] != 0) {
                    squares[6]++;
                }
                if(sudokuTab[i + 6][j + 3] != 0) {
                    squares[7]++;
                }
                if(sudokuTab[i + 6][j + 6] != 0) {
                    squares[8]++;
                }
            }
        }
        //System.out.println();
        //System.out.println(squares[0]);
        // Removing filled subgrids from available subgrids
        for(int i = 0; i < availableSquares.length; i++) {
            if(squares[i] == 9) {
                availableSquares[i] = false;
            } else {
                availableSquares[i] = true;
            }
        }

        int indexOfSquareWithTheMostAssignedValue = 0;
        int numberOfAssignedValue = 0;

        for(int i = 0; i < squares.length; i++) {
            if((squares[i] > numberOfAssignedValue) && availableSquares[i] == true) {
                indexOfSquareWithTheMostAssignedValue = i;
                numberOfAssignedValue = squares[i];
            }
        }

        //System.out.println(indexOfSquareWithTheMostAssignedValue);
        if(numberOfAssignedValue == 0) {
            result.add(-1);
            result.add(-1);
            result.add(-1);
            //System.out.println(result.get(0) + " --- " + result.get(1) + " --- " + result.get(2));
        } else {
            int indexOfDomain = Tools.indexOfDomainFromSquare(indexOfSquareWithTheMostAssignedValue, sudokuTab);
            //System.out.println("INDEX OF DOMAIN: " + indexOfDomain);
            //System.out.print(indexOfDomain);
            if(indexOfDomain == -1) {

            } else {
                result.add(indexOfDomain / 9);
                result.add(indexOfDomain % 9);
                result.add(indexOfDomain);
            }

        }


        return result;
    }

    public static ArrayList<Integer> firstFromTheMostFilledRow(ArrayList<ArrayList<Integer>> domains) {
        ArrayList<Integer> result = new ArrayList<>();
        int [] howManyInRow = new int[9];
        Boolean [] availableRows = new Boolean[9];

        for(int i = 0; i < 81; i++) {
            if(i < 9 && domains.get(i).isEmpty()) {
                howManyInRow[0]++;
            } else if(i >=9 && i < 18 && domains.get(i).isEmpty()) {
                howManyInRow[1]++;
            } else if(i >=18 && i < 27 && domains.get(i).isEmpty()) {
                howManyInRow[2]++;
            } else if(i >=27 && i < 36 && domains.get(i).isEmpty()) {
                howManyInRow[3]++;
            } else if(i >=36 && i < 45 && domains.get(i).isEmpty()) {
                howManyInRow[4]++;
            } else if(i >=45 && i < 54 && domains.get(i).isEmpty()) {
                howManyInRow[5]++;
            } else if(i >=54 && i < 63 && domains.get(i).isEmpty()) {
                howManyInRow[6]++;
            } else if(i >=63 && i < 72 && domains.get(i).isEmpty()) {
                howManyInRow[7]++;
            } else if(i >=72 && domains.get(i).isEmpty()) {
                howManyInRow[8]++;
            }
        }

        for(int i = 0; i < availableRows.length; i++) {
            if (howManyInRow[i] == 9) {
                availableRows[i] = false;
            } else {
                availableRows[i] = true;
            }
        }

        int indexOfTheMostFilledRow = -1;
        int numberOfValuesInRow = 0;

        for(int i = 0; i < howManyInRow.length; i++) {
            if(howManyInRow[i] > numberOfValuesInRow && availableRows[i] == true) {
                indexOfTheMostFilledRow = i;
                numberOfValuesInRow = howManyInRow[i];
            }
        }

        int domainIndex = 0;
        if(indexOfTheMostFilledRow != -1) {
            domainIndex = Tools.indexOfTheFirstDomainInRow(indexOfTheMostFilledRow, domains);
        }

        if(domainIndex == -1 || numberOfValuesInRow == 0) {
            result.add(-1);
            result.add(-1);
            result.add(-1);
        } else {
            result.add(domainIndex / 9);
            result.add(domainIndex % 9);
            result.add(domainIndex);
        }

        return result;
    }

}
