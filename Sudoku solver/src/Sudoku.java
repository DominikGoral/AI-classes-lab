import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class Sudoku extends Problem {

    int [][] sudokuTab;
    public static final int SIZE = 9;
    ArrayList<ArrayList<Integer>> domains;
    ArrayList<ArrayList<Integer>> results;
    int returnNumber = 0;
    int nodeNumber = 0;
    File file = new File("C:\\Users\\domin\\Desktop\\Studia\\VI semestr\\SI\\Zadanie 2\\Sudoku.csv");

    public Sudoku(int number) throws FileNotFoundException {
        this.sudokuTab = Loader.fromFileToSudoku(file, number);
        domains = createDomains();
    }

    @Override
    public int[][] getProblemData() {
        return sudokuTab;
    }

    public void setSudokuTab(int [][] sudokuTab) {
        this.sudokuTab = sudokuTab;
    }

    public int getReturnNumber() { return returnNumber; }

    public void setReturnNumber(int returnNumber) { this.returnNumber = returnNumber; }

    public int getNodeNumber() { return nodeNumber; }

    public void setNodeNumber(int nodeNumber) { this.nodeNumber = nodeNumber; }

    public ArrayList<ArrayList<Integer>> getDomains() { return domains; }

    public void setDomains(ArrayList<ArrayList<Integer>> domains) { this.domains = domains; }

    public void printSudoku() {
        for(int i = 0; i < SIZE; i++) {
            System.out.print("[ ");
            for(int j = 0; j < SIZE; j++) {
                System.out.print(sudokuTab[i][j] + " ");
            }
            System.out.println("]");
        }
    }

    public boolean isRowCorrect(int row, int number) {
        for(int i = 0; i < SIZE; i++) {
            if(sudokuTab[row][i] == number) {
                return false;
            }
        }
        return true;
    }

    public boolean isColCorrect(int col, int number) {
        for(int i = 0; i < SIZE; i++) {
            if(sudokuTab[i][col] == number) {
                return false;
            }
        }
        return true;
    }

    public boolean isSmallSquareCorrect(int row, int col, int number) {

        int numberOfFirstSquareRow = row - row % 3;
        int numberOfFirstSquareCol = col - col % 3;

        for(int i = numberOfFirstSquareRow; i < numberOfFirstSquareRow + 3; i++) {
            for(int j = numberOfFirstSquareCol; j < numberOfFirstSquareCol + 3; j++) {
                if(sudokuTab[i][j] == number) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Boolean isCorrect(int row, int col, int number) {
        if((isRowCorrect(row, number)) && (isColCorrect(col, number)) && (isSmallSquareCorrect(row, col, number))){
            return true;
        }
        return false;
    }

    @Override
    public ArrayList<ArrayList<Integer>> createDomains() {
        ArrayList<ArrayList<Integer>> domains = new ArrayList<>();

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                ArrayList<Integer> oneFieldDomain = new ArrayList<>();
                if(sudokuTab[i][j] == 0) {
                    for(int number = 1; number <= 9; number++) {
                        if(isCorrect(i, j, number)) {
                            oneFieldDomain.add(number);
                        }
                    }
                } else {
                    //oneFieldDomain.add();
                }
                domains.add(oneFieldDomain);
            }
        }
        return domains;
    }
    /*
    public Boolean solve2() {

        while(!Tools.solved(sudokuTab)) {
            ArrayList<Integer> temp = Tools.chooseVariable(domains);
            int row = temp.get(0);
            int col = temp.get(1);
            int domainIndex = temp.get(2);
            if(!(row == 100 && col == 100 && domainIndex == 100)) {
                for(int i = 0; i < domains.get(domainIndex).size(); i++) {
                    ArrayList<Integer> tempDomain = domains.get(domainIndex);
                    int number = tempDomain.get(i);

                    if(isCorrect(row, col, number)) {
                        //printDomain(domains);

                        sudokuTab[row][col] = number;
                        setDomains(createDomains());
//                    System.out.println("---- NOWE SUDOKU ----");
//                    printSudoku();
//                    System.out.println("---- NOWA DZIEDZINA ----");
//                    printDomain(domains);
                        if(solve2()) {
                            return true;
                        } else {
                            sudokuTab[row][col] = 0;
                            setDomains(createDomains());
                        }
                    }
                }
            } else {
                return false;
            }
            //System.out.println(row + " " + col + " " + domainIndex);
            // System.out.println(row + "  " + col + "  " + domainIndex);

        }

        return true;
    }
    */

    public Boolean solveBacktracking() {
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(sudokuTab[i][j] == 0) {
                    ArrayList<Integer> temp = Variable.firstFromTheMostFilledSquare(sudokuTab);
                    int row = temp.get(0);
                    int col = temp.get(1);
                    int domainIndex = temp.get(2);
                    //System.out.println("ROW: " + row + "COL: " + col + "DOMAIN INDEX: " + domainIndex);
                    if((row == -1) && (col == -1) & (domainIndex == -1)) {
                        return false;
                    } else {
                        ArrayList<Integer> domain = domains.get(domainIndex);
                        Value.firstCreatedFirstTaken(domain);
                        for(int k = 0; k < domains.get(domainIndex).size(); k++) {
                            int number = domain.get(k);
                            //System.out.println("********* " + row + "  " + col + " ***********");
                            if(isCorrect(row, col, number)) {
                                //System.out.println("ROW: " + row + " COL: " + col + " NUMBER: " + number);
                                this.sudokuTab[row][col] = number;
                                //System.out.println("NOWE SUDOKU:");
                                //printSudoku();
                                //nodeNumber++;
                                domains.set(domainIndex, new ArrayList<>());
                                if(solveBacktracking()) {
                                    return true;
                                } else {
                                    returnNumber++;
                                    this.sudokuTab[row][col] = 0;
                                    domains.set(domainIndex, domain);
                                }
                            }
                        }
                        return false;
                    }

                }
            }
        }
        if(Tools.solved(sudokuTab)) {
            System.out.println("ROZWIĄZANO");
            printSudoku();
        }
        return false;
    }

    public Boolean solveForward() {

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(sudokuTab[i][j] == 0) {
                    ArrayList<Integer> temp = Variable.firstFromTheMostFilledSquare(sudokuTab);
                    int row = temp.get(0);
                    int col = temp.get(1);
                    int domainIndex = temp.get(2);
                    //System.out.println("ROW: " + row + " COL: " + col + " DOMAIN INDEX: " + domainIndex);
                    if((row == -1) && (col == -1) & (domainIndex == -1)) {
                        return false;
                    } else {
                        ArrayList<Integer> domain = domains.get(domainIndex);
                        Value.firstCreatedFirstTaken(domain);
                        for(int k = 0; k < domains.get(domainIndex).size(); k++) {
                            int number = domain.get(k);
                            //System.out.println("********* " + row + "  " + col + " ***********");
                            if(isCorrect(row, col, number)) {
                                //System.out.println(number);
                                //System.out.println("ROW: " + row + " COL: " + col + " NUMBER: " + number + " DOMAIN NUMBER: " + domainIndex);
                                //System.out.println("DZIEDZINA:");
                                //printDomain(domains);
                                sudokuTab[row][col] = number;
                                //System.out.println("NOWE SUDOKU:");
                                //printSudoku();
                                ArrayList<ArrayList<Integer>> tempDomains = Tools.cloneDomains(domains);
                                //setDomains(Tools.removeFromDomains(domains, row, col, number));
                                setDomains(createDomains());
                                if(solveForward()) {
                                    return true;
                                } else {
                                    this.sudokuTab[row][col] = 0;
                                    domains = tempDomains;
                                    //setDomains(createDomains());
                                }
                            }
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void printDomain(ArrayList<ArrayList<Integer>> domains) {
        for(int i = 0; i < domains.size(); i++) {
            System.out.println(domains.get(i));
        }
    }
    /*
    public Boolean solve() {
        //int [][] filledSudokuTab = new int [9][9];

        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                if(sudokuTab[i][j] == 0) {
                    ArrayList<Integer> domain;
                    domain = domains.get((i * 9) + j);
                    // Losowy wybór wartości
                    Collections.shuffle(domain);

                    for(int k = 0; k < domain.size(); k++) {
                        int number = domain.get(k);
                        sudokuTab[i][j] = number;
                        setDomains(createDomains());

                        if(solve()) {
                            return true;
                        } else {
                            sudokuTab[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }

        return true;
    }
    */
}
