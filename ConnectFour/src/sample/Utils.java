package sample;

public class Utils {

    public static char[][] copyBoard(char [][] board) {
        char [][] clonedBoard = new char[6][7];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                clonedBoard[i][j] = board[i][j];
            }
        }
        return clonedBoard;
    }

    public static boolean providedColumnIsCorrect(int column) {
        if(column > 0 && column < 8) {
            return true;
        }
        return false;
    }

    public static boolean providedColumnIsAvailable(char [][] board, int column) {
        int row = Board.columnHeight(board, column);
        if(row < 6) {
            return true;
        }
        return false;
    }


}
