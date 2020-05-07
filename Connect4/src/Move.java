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

    public static Move findBestMove(char [][] board, Player p1, Player p2) {
        int bestVal = -1000;
        Move bestMove = new Move();
        for(int i = 0; i < board[0].length; i++) {
            int offset = Board.columnHeight(board, i);
            if(offset < 5) {
                board[offset][i] = 'o';
                int moveVal = Algorithms.minmax(4, board, false, p2, p1);
                board[offset][i] = '*';
                if (moveVal > bestVal) {
                    bestVal = moveVal;
                    bestMove.row = offset;
                    bestMove.col = i;
                }
            }
        }
        System.out.println("BEST MOVE: " + bestMove.row + ", " + bestMove.col);
//        return bestMove;
        return bestMove;
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