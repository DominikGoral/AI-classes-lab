import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Loader {

    public static int [][] fromFileToSudoku(File file, int sudokuNumber) throws FileNotFoundException {

        int [][] sudokuTab = new int[9][9];
        int counter = 0;

        Scanner sc = new Scanner(file);
        for(int i = 0; i < sudokuNumber; i++) {
            sc.nextLine();
        }

        String row = sc.nextLine();
        String [] data = row.split(";");
        String [] numbers = data[2].split("");

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(numbers[counter].equals(".")) {
                    sudokuTab[i][j] = 0;
                } else {
                    sudokuTab[i][j] = Integer.parseInt(numbers[counter]);
                }
                counter++;
            }
        }
        return sudokuTab;
    }
    /*
    public static ArrayList<Field> jolkaWords(File file) throws FileNotFoundException {

        ArrayList<Field> words = new ArrayList<>();
        Scanner sc = new Scanner(file);

        while(sc.hasNextLine()) {
            words.add(new Field(sc.nextLine()));
        }

        return words;
    }

    public static ArrayList<Integer> jolkaGaps(File file) throws FileNotFoundException {

        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<String> rowsFromFile = new ArrayList<>();
        int wordLength = 0;
        String data;

        Scanner sc = new Scanner(file);


        while(sc.hasNextLine()) {
            rowsFromFile.add(sc.nextLine());
        }

        // Checking length of the gaps in the rows
        for(int i = 0; i < rowsFromFile.size(); i++) {
            char[] row = rowsFromFile.get(i).toCharArray();
            for(int j = 0; j < row.length; j++) {
                if(row[j] == '_') {
                    wordLength++;
                } else if(row[j] == '#') {
                    if(wordLength > 1) {
                        results.add(wordLength);
                    }
                    wordLength = 0;
                }
            }
            if(wordLength > 1) {
                results.add(wordLength);
            }
            wordLength = 0;
        }
        /*
        int numberOfWordsInRows = results.size();
        wordLength = 0;

        // Checking length of the gaps in the cols
        for(int i = 0; i < rowsFromFile.get(0).length(); i++) {
            for(int j = 0; j < rowsFromFile.size(); j++) {
                if(rowsFromFile.get(j).toCharArray()[i] == '_') {
                    wordLength++;
                } else if(rowsFromFile.get(j).toCharArray()[i] == '#') {
                    if(wordLength > 1) {
                        results.add(wordLength);
                    }
                    wordLength = 0;
                }
            }
            if(wordLength > 1) {
                results.add(wordLength);
            }
            wordLength = 0;
        }

        results.add(numberOfWordsInRows);

        return results;
    }
    */
}
