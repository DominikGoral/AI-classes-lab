import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Sudoku sudoku;
//        ArrayList<ArrayList<Integer>> domains = sudoku.getDomains();
        //sudoku.printSudoku();
//        Variable.firstFromTheMostFilledSquare(domains);
        //sudoku.printDomain(sudoku.getDomains());
        //Tools.chooseVariable(sudoku.getDomains());
//        sudoku.printSudoku();
//        long start = System.currentTimeMillis();
//        sudoku.solveBacktracking();
//        long stop = System.currentTimeMillis();
//        System.out.println();
//        long executionTime = stop - start;
//        System.out.println("CZAS WYKONYWANIA: " + executionTime + " ms");
//        sudoku.printSudoku();

//        System.out.println("Odwiedzone węzły: " + sudoku.getNodeNumber());
//        System.out.println("Ilość powrotów: " + sudoku.getReturnNumber());
//
//        while(sudoku.solveBacktracking() != false) {
//
//            System.out.println("-------- ROZWIĄZANIE --------");
//            sudoku.solveBacktracking();
//            System.out.println();
//            sudoku.printSudoku();
//        }
        /*
        long allExecutionTime = 0;
        long allReturnNumber = 0;
        long allNodeNumber = 0;
        long allFullNodeNumber = 0;
        long allFullReturnNumber = 0;
        for(int i = 0; i < 10; i++) {
            sudoku = new Sudoku(43);
            sudoku.printSudoku();
            long start = System.currentTimeMillis();
            sudoku.solveForward();
            long stop = System.currentTimeMillis();
            long executionTime = stop - start;
            allExecutionTime = allExecutionTime + executionTime;
            //allNodeNumber = allNodeNumber + sudoku.nodeNumber;
            //allReturnNumber = allReturnNumber + sudoku.returnNumber;
            allFullReturnNumber = allFullReturnNumber + sudoku.allReturnNumber;
            allFullNodeNumber = allFullNodeNumber + sudoku.allNodeNumber;
            System.out.println("CZAS WYKONYWANIA: " + executionTime + " ms");
            //System.out.println("LICZBA NAWROTOW- 1 rozwiazanie: " + sudoku.returnNumber);
            //System.out.println("LICZBA ODWIEDZONYCH WEZLOW- 1 rozwiazanie: " + sudoku.nodeNumber);
            System.out.println("LICZBA NAWROTOW- wszystkie rozwiązania: " + sudoku.allNodeNumber);
            System.out.println("LICZBA ODWIEDZONYCH WEZLOW- wszystkie rozwiązania: " + sudoku.allReturnNumber);
            //sudoku.printSudoku();
            System.out.println("ILOSC ROZWIAZAN " + sudoku.numberOfSolution);
            System.out.println("*****************************************************************************");

        }

        System.out.println("PODSUMOWANIE");
        System.out.println("WSZYSTKIE POWROTY:" + allFullReturnNumber);
        System.out.println("WSZYSTKIE WEZLY:" + allFullNodeNumber);
        System.out.println("CALY CZAS:" + allExecutionTime);
        */
        long allExecutionTimes = 0;
        for(int i = 0; i < 10; i++) {
            sudoku = new Sudoku(7);
            long start = System.currentTimeMillis();
            sudoku.solveBacktracking();
            long stop = System.currentTimeMillis();
            long executionTime = stop - start;
            allExecutionTimes = allExecutionTimes + executionTime;
            System.out.println("CZAS WYKONYWANIA: " + executionTime + " ms");
        }

        System.out.println("SREDNI CZAS: " + allExecutionTimes / 10);

        // Szukanie wszystkich rozwiązań
//        ArrayList<ArrayList<Integer>> results = new ArrayList<>();
//        for(int i = 0; i < 500; i++) {
//            Sudoku sudoku = new Sudoku(41);
//            sudoku.solveBacktracking();
//            ArrayList<Integer> result = new ArrayList<>();
//            result = Tools.gridToList(sudoku.getProblemData());
//            if(!Tools.exist(result, results)) {
//                results.add(result);
//            }
//        }
//
//        System.out.print(results.size());


//        Jolka jolka = new Jolka();
//        jolka.printWords();

//        ArrayList<ArrayList<Integer>> results = sudoku.getDomains();
//        for(int i = 0; i < results.size(); i++) {
//            System.out.println(results.get(i));
//        }

        //File file = new File("C:\\Users\\domin\\Desktop\\Studia\\VI semestr\\SI\\Zadanie 2\\Jolka\\puzzle2.txt");
        /*
        ArrayList<ArrayList<String>> domains = sudoku.createDomains();
        for(int i = 0; i < domains.size(); i++) {
            System.out.println(domains.get(i));
        }
        */
    }

}
