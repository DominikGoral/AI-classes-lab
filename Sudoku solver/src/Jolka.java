import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/*
public class Jolka {

    ArrayList<Field> words;
    ArrayList<Integer> gapsForWords;
    // public String
    File wordsData = new File("C:\\Users\\domin\\Desktop\\Studia\\VI semestr\\SI\\Zadanie 2\\Jolka\\words1.txt");
    File gapsData = new File("C:\\Users\\domin\\Desktop\\Studia\\VI semestr\\SI\\Zadanie 2\\Jolka\\puzzle1.txt");

    public Jolka() throws FileNotFoundException {
        this.words = Loader.jolkaWords(wordsData);
        this.gapsForWords = Loader.jolkaGaps(gapsData);
    }

    public void printWords() {
        for(int i = 0; i < words.size(); i++) {
            System.out.println(words.get(i).getValue());
        }
    }

    @Override
    public Boolean isCorrect(int row, int col, int number) {
        return null;


    //@Override
    public ArrayList<Field> getProblemData() {
        return words;
    }

    public ArrayList<ArrayList<String>> createDomains () {
        int numberOfWordsInRows = this.gapsForWords.get(this.gapsForWords.size() - 1);
        ArrayList<ArrayList<String>> domains = new ArrayList<>();

        for(int i = 0; i < numberOfWordsInRows; i++) {
            int lengthOfGap = this.gapsForWords.get(i);
            ArrayList<String> gapPossibleWords = new ArrayList<>();
            for(int j = 0; j < this.words.size(); j++) {
                if(this.words.get(j).getValue().length() == lengthOfGap) {
                    gapPossibleWords.add(this.words.get(j).getValue());
                }
            }
            domains.add(gapPossibleWords);
        }

        return domains;
    }



}
*/