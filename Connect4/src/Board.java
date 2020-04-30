public class Board {

    char board[][] = new char[6][7];

    public Board(char [][] board) {
        this.board = board;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char [][] board) {
        this.board = board;
    }

    public void printBoard() {
        for(int i = 0; i < board.length; i++) {
            System.out.print('|');
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + '|');
            }
            System.out.println();
        }
    }

}
