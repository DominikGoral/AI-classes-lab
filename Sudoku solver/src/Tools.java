import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tools {



    public static ArrayList<ArrayList<Integer>> removeFromDomains(ArrayList<ArrayList<Integer>> domains, int row, int col, int number) {
        int firstRowInSmallSquare = row - row % 3;
        int firstColInSmallSquare = col - col % 3;
        //System.out.println("**** WIERSZE ****");
        // Remove from domains in a row
        for(int i = row * 9; i < (row * 9) + 9; i++) {
            //domains.get(i).remove(new Integer(number));
            ArrayList<Integer> domain = domains.get(i);
            for(int j = 0; j < domain.size(); j++) {
                if(domain.get(j) == number) {
                    domain.remove(j);
                }
            }
            //System.out.println(i);
        }
        //System.out.println("**** KOLUMNY ****");
        // Remove from domains in a col
        for(int i = col; i < 81; i = i + 9) {
            //domains.get(i).remove(new Integer(number));
            ArrayList<Integer> domain = domains.get(i);
            for(int j = 0; j < domain.size(); j++) {
                if(domain.get(j) == number) {
                    domain.remove(j);
                }
            }
            //System.out.println(i);
        }
        //System.out.println("**** KWADRATY ****");
        // Remove from domains in a small square
        for(int i = firstRowInSmallSquare * 9 + firstColInSmallSquare; i < (firstRowInSmallSquare + 3) * 9; i = i + 9) {
            for(int j = 0; j < 3; j++) {
                domains.get(i + j).remove(new Integer(number));
                ArrayList<Integer> domain = domains.get(i);
                for(int k = 0; k < domain.size(); k++) {
                    if(domain.get(k) == number) {
                        domain.remove(k);
                    }
                }
                //System.out.println(i + j);
            }
        }
        return domains;
    }

    public static void removeFromRowsDomains(ArrayList<ArrayList<Integer>> domains, int row, int col, int number) {
        for(int i = row * 9; i < (row * 9) + 9; i++) {
            domains.get(i).remove(new Integer(number));
        }
    }

    public static void removeFromColsDomains(ArrayList<ArrayList<Integer>> domains, int row, int col, int number) {
        for(int j = col; j < 81; j = j + 9) {
            domains.get(j).remove(new Integer(number));
        }
    }

    public static void removeFromSmallSquareDomains(ArrayList<ArrayList<Integer>> domains, int row, int col, int number) {
        int firstRowInSmallSquare = row - row % 3;
        int firstColInSmallSquare = col - col % 3;

        for(int i = firstRowInSmallSquare * 9 + firstColInSmallSquare; i < (firstRowInSmallSquare + 3) * 9; i = i + 9) {
            for(int j = 0; j < 3; j++) {
                domains.get(i + j).remove(new Integer(number));
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> cloneDomains(ArrayList<ArrayList<Integer>> domains) {
        ArrayList<ArrayList<Integer>> domainsClone = new ArrayList<>();
        for(int i = 0; i < domains.size(); i++) {
            domainsClone.add(domains.get(i));
        }
        return domainsClone;
    }



    public static void addToDomains(ArrayList<ArrayList<Integer>> domains, int row, int col, int number) {

        int firstRowInSmallSquare = row - row % 3;
        int firstColInSmallSquare = col - col % 3;

        // Remove from domains in a row
        for(int i = row * 9; i < (row * 9) + 9; i++) {
            domains.get(i).add(new Integer(number));
            Collections.sort(domains.get(i));
        }
        // Remove from domains in a col
        for(int i = col; i < 81; i = i + 9) {
            domains.get(i).add(new Integer(number));
            Collections.sort(domains.get(i));
        }
        // Remove from domains in a small square
        for(int i = firstRowInSmallSquare * 9 + firstColInSmallSquare; i < (firstRowInSmallSquare + 3) * 9; i = i + 9) {
            for(int j = 0; j < 3; j++) {
                domains.get(i + j).add(new Integer(number));
                Collections.sort(domains.get(i));
            }
        }
    }



    public static int lowestFromTheTable(int [] array) {
        int indexOfTheLowest = 0;
        for(int i = 1; i < array.length; i++) {
            if(array[i] < array[i - 1]) {
                indexOfTheLowest = i;
            }
        }
        return indexOfTheLowest;
    }

    public static Boolean solved(int [][] sudokuTab) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(sudokuTab[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static ArrayList<Integer> gridToList(int [][] sudokuTab) {
        ArrayList<Integer> sudokuList = new ArrayList<>();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sudokuList.add(sudokuTab[i][j]);
            }
        }
        return sudokuList;
    }

    public static Boolean exist(ArrayList<Integer> sudokuList, ArrayList<ArrayList<Integer>> results) {
        for(int i = 0; i < results.size(); i++) {
            if(results.get(i).equals(sudokuList)) {
                return true;
            }
        }
        return false;
    }

    public static int indexOfDomainFromSquare(int squareIndex, int [][] sudokuTab) {
        int indexOfDomain = 0;
        if(squareIndex == 0) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(sudokuTab[i][j] == 0){
                        return i * 9 + j;
                    }
                }
            }
        } else if(squareIndex == 1) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(sudokuTab[i][j + 3] == 0){
                        return i * 9 + j + 3;
                    }
                }
            }
        } else if(squareIndex == 2) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(sudokuTab[i][j + 6] == 0){
                        return i * 9 + j + 6;
                    }
                }
            }
        } else if(squareIndex == 3) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(sudokuTab[i + 3][j] == 0){
                        return (i + 3) * 9 + j;
                    }
                }
            }
        } else if(squareIndex == 4) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(sudokuTab[i + 3][j + 3] == 0){
                        return (i + 3) * 9 + j + 3;
                    }
                }
            }
        } else if(squareIndex == 5) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(sudokuTab[i + 3][j + 6] == 0){
                        return (i + 3) * 9 + j + 6;
                    }
                }
            }
        } else if(squareIndex == 6) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(sudokuTab[i + 6][j] == 0){
                        return (i + 6) * 9 + j;
                    }
                }
            }
        } else if(squareIndex == 7) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(sudokuTab[i + 6][j + 3] == 0){
                        return (i + 6) * 9 + j + 3;
                    }
                }
            }
        } else if(squareIndex == 8) {
            for(int i = 0; i < 3; i++) {
                for(int j = 0; j < 3; j++) {
                    if(sudokuTab[i + 6][j + 6] == 0){
                        return (i + 6) * 9 + j + 6;
                    }
                }
            }
        }
        return -1;
    }

    public static int indexOfTheFirstDomainInRow(int row, ArrayList<ArrayList<Integer>> domains) {
        int indexOfTheDomain = -1;
        for(int i = row * 9; i < row * 9 + 9; i++) {
            if(!domains.get(i).isEmpty()) {
                return i;
            }
        }
        return indexOfTheDomain;
    }

}
