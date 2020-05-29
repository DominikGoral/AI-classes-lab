package sample;

public class Move {

    int row;
    int col;

    public Move() {
        row = -1;
        col = -1;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public static Move findBestMove(char [][] board, int depth, int typeOfMethod, int minmaxType, Player p1, Player p2) {
        int bestVal = -10000;
        Move bestMove = new Move();
        for(int i = 0; i < board[0].length; i++) {
            int offset = Board.columnHeight(board, i);
            if(offset < 5) {
                //char [][] boardCopy = Utils.copyBoard(board);
                board[offset][i] = p1.getSign();
                int moveVal = 0;
                if(minmaxType == 0) {
                    moveVal = Algorithms.minmax(depth, board, false, typeOfMethod, p1, p2);
                } else {
                    moveVal = Algorithms.minmaxAlfaBeta(4, board, false, typeOfMethod, p1, p2, -1000, 1000);
                }
                board[offset][i] = '*';
                if (moveVal > bestVal) {
                    bestVal = moveVal;
                    bestMove.row = offset;
                    bestMove.col = i;
                }
            }
        }
        if(bestMove.col == -1 || bestMove.row == -1) {
            bestMove = firstAvailableMove(board);
        }
        System.out.println("BEST MOVE: " + bestMove.row + ", " + bestMove.col);
//        return bestMove;
        return bestMove;
    }

    public static Move firstAvailableMove(char [][] board) {
        Move availableMove = new Move();
        for(int i = 0; i < board[0].length; i++) {
            for(int j = 0; j < board.length; j++) {
                if(board[j][i] == '*') {
                    availableMove.row = j;
                    availableMove.col = i;
                    return availableMove;
                }
            }
        }
        return availableMove;
    }

//    public int findBestMoveRec(char [][] board, int depth, Player p) {
//        if(p.getSign() == 'x') {
//            p.setSign('o');
//        } else {
//            p.setSign('x');
//        }
//        if(depth <= 0) {
//            return Board.boardCheckForWin(board, p);
//        }
//        for(int i = 0; i < 7; i++) {
//            char [][] boardCopy = Utils.copyBoard(board);
//
//        }
//    return 0;
//    }

}

//            if(board[offset][i] == '*') {
//                    char [][] boardCopy = Utils.copyBoard(board);
//                    boardCopy[offset][i] = p1.getSign();
////                System.out.println();
////                Board.printBoard(boardCopy);
//                    int newResult = Algorithms.minmax(5, boardCopy, true, p1, p2);
//                    boardCopy[offset][i] = '*';
//                    if(newResult > bestVal) {
//                    bestVal = newResult;
//                    row = offset;
//                    col = i;
//                    }
//                    }