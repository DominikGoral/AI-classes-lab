package sample;

import javafx.application.Platform;

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

    public static Boolean isEmpty(char [][] board) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] != '*') {
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

    public static int boardCheckForWin(char [][] board, Player p1) {
        // rows checking
        int numberOfTheSameValueInARow = 1;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length - 1; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j] != '*') {
                    numberOfTheSameValueInARow++;
                    if(numberOfTheSameValueInARow == 4) {
                        if(board[i][j] == p1.getSign()) {
                            return 1000;
                        } else {
                            return -1000;
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
                        if(board[j][i] == p1.getSign()) {
                            return 1000;
                        } else {
                            return -1000;
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
                    if(board[0 + i][0 + j] == p1.getSign()) {
                        return 1000;
                    } else {
                        return -1000;
                    }
                }
            }
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[5 - i][0 + j] == board[4 - i][1 + j] && board[4 - i][1 + j] == board[3 - i][2 + j] && board[3 - i][2 + j] == board[2 - i][3 + j] && board[5 - i][0 + j] != '*') {
                    // return, że równe
                    if(board[5 - i][0 + j] == p1.getSign()) {
                        return 1000;
                    } else {
                        return -1000;
                    }
                }
            }
        }
        return 0;
    }

    public static int checkBoardForThreeInRow(char [][] board, Player p1) {
        int numberOfTheSameValueInARow = 1;
        // Rows
        // if 3 in row and next/ previous field is empty
        for(int i = 0; i < board.length; i++) {
            for(int j = 1; j < board[0].length - 3; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2] && board[i][j - 1] == '*' && board[i][j + 3] == '*' && board[i][j] != '*') {
                    int firstColumnHeight = Board.columnHeight(board, j - 1);
                    int secondColumnHeight = Board.columnHeight(board, j + 3);
                    if(board[i][j] == p1.getSign()) {
                        return 119 - (i - firstColumnHeight) - (i - secondColumnHeight);
                    } else {
                        return -119 + (i - firstColumnHeight) + (i - secondColumnHeight);
                    }

                }
            }
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length - 3; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2] && board[i][j + 3] == '*' && board[i][j] != '*') {
                    int  columnHeight = Board.columnHeight(board, j + 3);
                    if(board[i][j] == p1.getSign()) {
                        return 117 - (i - columnHeight);
                    } else {
                        return -117 + (i - columnHeight);
                    }
                }
            }
        }

        for(int i = 0; i < board.length; i++) {
            for(int j = 1; j < board[i].length - 2; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j + 1] == board[i][j + 2] && board[i][j - 1] == '*' && board[i][j] != '*') {
                    int  columnHeight = Board.columnHeight(board, j - 1);
                    if(board[i][j] == p1.getSign()) {
                        return 117 - (i - columnHeight);
                    } else {
                        return -117 + (i - columnHeight);
                    }
                }
            }
        }
        // Cols
        for(int i = 0; i < board[0].length; i++) {
            int columnHeight = Board.columnHeight(board, i);
            if(columnHeight >= 3 && columnHeight < 6) {
                if(board[columnHeight - 3][i] == board[columnHeight - 2][i] && board[columnHeight - 2][i] == board[columnHeight - 1][i] && board[columnHeight][i] == '*' && board[columnHeight - 3][i] != '*') {
                    if(board[columnHeight - 3][i] == p1.getSign()) {
                        return 117;
                    } else {
                        return -117;
                    }
                }
            }
        }
        // Diagonals
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[0 + i][0 + j] == board[1 + i][1 + j] && board[1 + i][1 + j] == board[2 + i][2 + j] && board[3 + i][3 + j] == '*' && board[0 + i][0 + j] != '*') {
                    int columnHeight = Board.columnHeight(board, (3 + j));
                    if(board[0 + i][0 + j] == p1.getSign()) {
                        return 114 + (columnHeight - i);
                    } else {
                        return -114 - (columnHeight - i);
                    }
                }
            }
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[5 - i][0 + j] == '*' && board[4 - i][1 + j] == board[3 - i][2 + j] && board[3 - i][2 + j] == board[2 - i][3 + j] && board[2 - i][3 + j] != '*') {
                    int columnHeight = Board.columnHeight(board, (0 + j));
                    if(board[4 - i][1 + j] == p1.getSign()) {
                        return 112 + (columnHeight + i);
                    } else {
                        return -112 - (columnHeight + i);
                    }
                }
            }
        }
        return 0;
    }

    public static int checkBoardForTwoInRow(char [][] board, Player p1) {
        // ROWS
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length - 3; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j + 2] == '*' && board[i][j + 3] == '*') {
                    int firstColumnHeight = Board.columnHeight(board, j + 2);
                    int secondColumnHeight = Board.columnHeight(board, j + 3);
                    if(board[i][j] == p1.getSign()) {
                        return 14 - (i - firstColumnHeight) - (i - secondColumnHeight);
                    } else {
                        return -14 + (i - firstColumnHeight) + (i - secondColumnHeight);
                    }
                }
                if(board[i][j] == board[i][j + 1] && board[i][j + 2] == '*' && board[i][j] == board[i][j + 3]) {
                    int firstColumnHeight = Board.columnHeight(board, j + 2);
                    if(board[i][j] == p1.getSign()) {
                        return 19 - (i - firstColumnHeight);
                    } else {
                        return -19 + (i - firstColumnHeight);
                    }
                }
//                if(board[i][j] == board[i][j + 1] && board[i][j + 2] == '*' && board[i][j] != board[i][j + 3]) {
//                    int firstColumnHeight = Board.columnHeight(board, j + 2);
//                    if(board[i][j] == p.getSign()) {
//                        return 10 - (i - firstColumnHeight);
//                    } else {
//                        return -10 + (i - firstColumnHeight);
//                    }
//                }
            }
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 2; j < board[0].length - 1; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j - 1] == '*' && board[i][j - 2] == '*') {
                    int firstColumnHeight = Board.columnHeight(board, j - 1);
                    int secondColumnHeight = Board.columnHeight(board, j - 2);
                    if(board[i][j] == p1.getSign()) {
                        return 14 - (i - firstColumnHeight) - (i - secondColumnHeight);
                    } else {
                        return -14 + (i - firstColumnHeight) + (i - secondColumnHeight);
                    }
                }
                if(board[i][j] == board[i][j + 1] && board[i][j - 1] == '*' && board[i][j] == board[i][j - 2]) {
                    int firstColumnHeight = Board.columnHeight(board, j - 1);
                    if(board[i][j] == p1.getSign()) {
                        return 19 - (i - firstColumnHeight);
                    } else {
                        return -19 + (i - firstColumnHeight);
                    }
                }
//                if(board[i][j] == board[i][j + 1] && board[i][j - 1] == '*' && board[i][j] != board[i][j - 2]) {
//                    int firstColumnHeight = Board.columnHeight(board, j - 1);
//                    if(board[i][j] == p.getSign()) {
//                        return 10 - (i - firstColumnHeight);
//                    } else {
//                        return -10 + (i - firstColumnHeight);
//                    }
//                }
            }
        }
        for(int i = 0; i < board.length; i++) {
            for(int j = 1; j < board[0].length - 2; j++) {
                if(board[i][j] == board[i][j + 1] && board[i][j - 1] == '*' && board[i][j + 2] == '*') {
                    int firstColumnHeight = Board.columnHeight(board, j - 1);
                    int secondColumnHeight = Board.columnHeight(board, j + 2);
                    if(board[i][j] == p1.getSign()) {
                        return 14 - (i - firstColumnHeight) - (i - secondColumnHeight);
                    } else {
                        return -14 + (i - firstColumnHeight) + (i - secondColumnHeight);
                    }
                }
//                if(board[i][j] == board[i][j + 1] && board[i][j - 1] == '*' && board[i][j + 2] != p.getSign()) {
//                    int firstColumnHeight = Board.columnHeight(board, j - 1);
//                    if(board[i][j] == p.getSign()) {
//                        return 10 - (i - firstColumnHeight);
//                    }
//                }
//                if(board[i][j] == board[i][j + 1] && board[i][j - 1] != p.getSign() && board[i][j + 2] == '*') {
//                    int firstColumnHeight = Board.columnHeight(board, j + 2);
//                    if(board[i][j] == p.getSign()) {
//                        return 10 - (i - firstColumnHeight);
//                    }
//                }
            }
        }
        // COLS
        for(int i = 0; i < board[0].length; i++) {
            int columnHeight = Board.columnHeight(board, i);
            if(columnHeight >= 2 && columnHeight < 6) {
                if(board[columnHeight - 2][i] == board[columnHeight - 1][i] && board[columnHeight][i] == '*' && board[columnHeight - 2][i] != '*') {
                    if(board[columnHeight - 2][i] == p1.getSign()) {
                        return 13 ;
                    } else {
                        return -13;
                    }
                }
            }
        }
        // DIAGONALS
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[0 + i][0 + j] == board[1 + i][1 + j] && board[2 + i][2 + j] == '*' && board[3 + i][3 + j] == '*' && board[0 + i][0 + j] != '*') {
                    int firstColumnHeight = Board.columnHeight(board, (2 + j));
                    int secondColumnHeight = Board.columnHeight(board, (3 + j));
                    if(board[0 + i][0 + j] == p1.getSign()) {
                        return 9 + (firstColumnHeight - i) + (secondColumnHeight - i);
                    } else {
                        return -9 - (firstColumnHeight - i) - (secondColumnHeight - i);
                    }
                }
                if(board[0 + i][0 + j] == board[1 + i][1 + j] && board[2 + i][2 + j] == '*' && board[3 + i][3 + j] == board[1 + i][1 + j] && board[0 + i][0 + j] != '*') {
                    int firstColumnHeight = Board.columnHeight(board, (2 + j));
                    if(board[0 + i][0 + j] == p1.getSign()) {
                        return 16 + (firstColumnHeight - i);
                    } else {
                        return -16 - (firstColumnHeight - i);
                    }
                }
            }
        }
        for(int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[5 - i][0 + j] == '*' && board[4 - i][1 + j] == '*' && board[3 - i][2 + j] == board[2 - i][3 + j] && board[3 - i][2 + j] != '*') {
                    int firstColumnHeight = Board.columnHeight(board, (0 + j));
                    int secondColumnHeight = Board.columnHeight(board, (1 + j));
                    if (board[3 - i][2 + j] == p1.getSign()) {
                        return 5 + (firstColumnHeight + i) + (secondColumnHeight + i);
                    } else {
                        return -5 - (firstColumnHeight + i) - (secondColumnHeight + i);
                    }
                }
                if (board[5 - i][0 + j] == board[3 - i][2 + j] && board[4 - i][1 + j] == '*' && board[3 - i][2 + j] == board[2 - i][3 + j] && board[3 - i][2 + j] != '*') {
                    int firstColumnHeight = Board.columnHeight(board, (1 + j));
                    if (board[3 - i][2 + j] == p1.getSign()) {
                        return 16 + (firstColumnHeight + i);
                    } else {
                        return -16 - (firstColumnHeight + i);
                    }
                }
            }
        }
//        for(int i = 0; i < 4; i++) {
//            for(int j = 0; j < 5; j++) {
//                if(board[0 + i][0 + j] == board[1 + i][1 + j] && board[2 + i][2 + j] == '*' && board[0 + i][0 + j] != '*') {
//                    int columnHeight = Board.columnHeight(board, (2 + j));
//                    if(board[0 + i][0 + j] == p.getSign()) {
//                        return 9 + (columnHeight - i);
//                    } else {
//                        return -9 - (columnHeight - i);
//                    }
//                }
//            }
//        }
//        for(int i = 0; i < 4; i++) {
//            for(int j = 0; j < 5; j++) {
//                if(board[5 - i][0 + j] == '*' && board[4 - i][1 + j] == board[3 - i][2 + j] && board[3 - i][2 + j] != '*') {
//                    int columnHeight = Board.columnHeight(board, (0 + j));
//                    if(board[4 - i][1 + j] == p.getSign()) {
//                        return 6 + (columnHeight + i);
//                    } else {
//                        return -6 - (columnHeight + i);
//                    }
//                }
//            }
//        }
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

    public static int checkBoards(char [][] board, int typeOfMethod, Player p1) {
        int numberOfFirstPlayerWins = 0;
        int numberOfSecondPlayerWins = 0;
        if(typeOfMethod == 0) {
            numberOfFirstPlayerWins = numberOfFirstPlayerWins + boardCheckForWin(board, p1);
        } else if(typeOfMethod == 1) {
            numberOfFirstPlayerWins = numberOfFirstPlayerWins + boardCheckForWin(board, p1) + checkBoardForThreeInRow(board, p1);
        } else {
            numberOfFirstPlayerWins = numberOfFirstPlayerWins + boardCheckForWin(board, p1) + checkBoardForThreeInRow(board, p1) + checkBoardForTwoInRow(board, p1);
        }

        //numberOfSecondPlayerWins = numberOfSecondPlayerWins + boardCheckForWin(board) + checkBoardForThreeInRow(board) + checkBoardForTwoInRow(board);
//        for(int i = 0; i < board.length; i++) {
//            for(int j = 0; j < board[i].length; j++) {
//                if (board[i][j] == '*') {
//                    //board[i][j] = p1.getSign();
//                    numberOfFirstPlayerWins = numberOfFirstPlayerWins + boardCheckForWin(board, p1) + checkBoardForThreeInRow(board, p1);
//                    //board[i][j] = '*';
//                }
//            }
//        }
//        for(int i = 0; i < board.length; i++) {
//            for(int j = 0; j < board[i].length; j++) {
//                if (board[i][j] == '*') {
//                    //board[i][j] = p2.getSign();
//                    numberOfSecondPlayerWins = numberOfSecondPlayerWins + boardCheckForWin(board, p2) + checkBoardForThreeInRow(board, p2);
//                    //board[i][j] = '*';
//                }
//            }
//        }
        //int sub = numberOfFirstPlayerWins - numberOfSecondPlayerWins;
        return numberOfFirstPlayerWins;
    }

    public void initializeBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] = '*';
            }
        }
    }

}

