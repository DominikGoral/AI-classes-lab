public class Board {

    char board[][] = new char[6][7];

//    public Board() {
//        this.board = new char[6][7];
//    }

//    public Board(char [][] board) {
//        this.board = board;
//    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char [][] board) {
        this.board = board;
    }

    public static void printBoard(char [][] board) {
        for(int i = board.length - 1; i >= 0; i--) {
            System.out.print("| ");
            for(int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
        }
    }

    public static Boolean isFilled(char [][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == '*') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void dropToken(char[][] board, int row, int col, char sign) {
        board[row][col] = sign;
    }

    public static void removeToken(char[][] board, int row, int col, char sign) {
        board[row][col] = '*';
    }

    public static int boardCheckForWin(char [][] board, Player p) {
        // rows checking
        int numberOfTheSameValueInARow = 1;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length - 1; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j] != '*') {
                    numberOfTheSameValueInARow++;
                    if(numberOfTheSameValueInARow == 4) {
                        if(board[i][j] == p.getSign()) {
                            return 20;
                        } else {
                            return -20;
                        }
                    }
                } else {
                    numberOfTheSameValueInARow = 1;
                }
            }
            numberOfTheSameValueInARow = 1;
        }
        // cols checking
        int numberOfTheSameValueInACol = 1;
        for(int i = 0; i < board[0].length; i++) {
            for(int j = 0; j < board.length - 1; j++) {
                if(board[j][i] == board[j + 1][i] && board[j][i] != '*') {
                    numberOfTheSameValueInACol++;
                    if(numberOfTheSameValueInACol == 4) {
                        if(board[j][i] == p.getSign()) {
                            return 20;
                        } else {
                            return -20;
                        }
                    }
                } else {
                    numberOfTheSameValueInACol = 1;
                }
            }
            numberOfTheSameValueInACol = 1;
        }
        // diagonal checking
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[0 + i][0 + j] == board[1 + i][1 + j] && board[1 + i][1 + j] == board[2 + i][2 + j] && board[2 + i][2 + j] == board[3 + i][3 + j] && board[0 + i][0 + j] != '*') {
                    if(board[0 + i][0 + j] == p.getSign()) {
                        return 20;
                    } else {
                        return -20;
                    }
                }
            }
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[5 - i][0 + j] == board[4 - i][1 + j] && board[4 - i][1 + j] == board[3 - i][2 + j] && board[3 - i][2 + j] == board[2 - i][3 + j] && board[5 - i][0 + j] != '*') {
                    // return, że równe
                    if(board[5 - i][0 + j] == p.getSign()) {
                        return 20;
                    } else {
                        return -20;
                    }
                }
            }
        }
        return 0;
    }

    public static int checkBoardForThreeInRow(char [][] board, Player p) {
        int numberOfTheSameValueInARow = 1;
        // Rows
        // if 3 in row and next/ previous field is empty
        for(int i = 0; i < board.length; i++) {
            for(int j = 1; j < board[0].length - 3; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2] && board[i][j - 1] == '*' && board[i][j + 3] == '*' && board[i][j] != '*') {
                    int firstColumnHeight = Board.columnHeight(board, j - 1);
                    int secondColumnHeight = Board.columnHeight(board, j + 3);
                    if(board[i][j] == p.getSign()) {
                        return 19 - (i - firstColumnHeight) - (i - secondColumnHeight);
                    } else {
                        return -19 + (i - firstColumnHeight) - (i - secondColumnHeight);
                    }

                }
            }
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length - 3; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2] && board[i][j + 3] == '*' && board[i][j] != '*') {
                    int  columnHeight = Board.columnHeight(board, j + 3);
                    if(board[i][j] == p.getSign()) {
                        return 18 - (i - columnHeight);
                    } else {
                        return -18 + (i - columnHeight);
                    }
                }
            }
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 1; j < board[i].length - 2; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2] && board[i][j - 1] == '*' && board[i][j] != '*') {
                    int  columnHeight = Board.columnHeight(board, j - 1);
                    if(board[i][j] == p.getSign()) {
                        return 18 - (i - columnHeight);
                    } else {
                        return -18 + (i - columnHeight);
                    }
                }
            }
        }
        // Cols
        for(int i = 0; i < board[0].length; i++) {
            int columnHeight = Board.columnHeight(board, i);
            if(columnHeight >= 3 && columnHeight < 6) {
                for(int j = 0; j < 3; j++) {
                    if(board[columnHeight - 3][i] == board[columnHeight - 2][i] && board[columnHeight - 2][i] == board[columnHeight - 1][i] && board[columnHeight][i] == '*' && board[columnHeight - 3][i] != '*') {
                        if(board[columnHeight - 3][i] == p.getSign()) {
                            return 19;
                        } else {
                            return -19;
                        }
                    }
                }
            }
        }
        // Diagonals
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[0 + i][0 + j] == board[1 + i][1 + j] && board[1 + i][1 + j] == board[2 + i][2 + j] && board[3 + i][3 + j] == '*' && board[0 + i][0 + j] != '*') {
                    int columnHeight = Board.columnHeight(board, (3 + j));
                    if(board[0 + i][0 + j] == p.getSign()) {
                        return 16 + (columnHeight - i);
                    } else {
                        return -16 - (columnHeight - i);
                    }
                }
            }
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[5 - i][0 + j] == '*' && board[4 - i][1 + j] == board[3 - i][2 + j] && board[3 - i][2 + j] == board[2 - i][3 + j] && board[2 - i][3 + j] != '*') {
                    int columnHeight = Board.columnHeight(board, (0 + j));
                    if(board[4 - i][1 + j] == p.getSign()) {
                        return 14 + (columnHeight + i);
                    } else {
                        return -14 - (columnHeight + i);
                    }
                }
            }
        }
        return 0;
    }

    public static int columnHeight(char[][] board, int column) {
        int height = 0;
        for(int i = 5; i > -1; i--) {
            if(board[i][column] != '*') {
                height++;
            }
        }
        return height;
    }

    public static int checkBoards(char [][] board, Player p1, Player p2) {
        int numberOfFirstPlayerWins = 0;
        int numberOfSecondPlayerWins = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = p1.getSign();
                    numberOfFirstPlayerWins = numberOfFirstPlayerWins + boardCheckForWin(board, p1) + checkBoardForThreeInRow(board, p1);
                    board[i][j] = '*';
                }
            }
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = p2.getSign();
                    numberOfSecondPlayerWins = numberOfSecondPlayerWins + boardCheckForWin(board, p2) + checkBoardForThreeInRow(board, p2);
                    board[i][j] = '*';
                }
            }
        }
        int sub = numberOfFirstPlayerWins - numberOfSecondPlayerWins;
        return sub;
    }

    public void initializeBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = '*';
            }
        }
    }

}
