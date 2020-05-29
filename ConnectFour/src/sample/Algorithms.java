package sample;

public class Algorithms {

    public static int minmax(int depth, char [][] actualBoard, Boolean maximize, int typeOfMethod, Player p1, Player p2) { // p1- me, p2- opponent
        int isWin = Board.boardCheckForWin(actualBoard, p1);
        if(typeOfMethod == 3) {
            if(isWin == 1000) {
                return isWin;
            }

            if(isWin == -1000) {
                return isWin;
            }

            if(depth == 0) {
                int result = Board.checkBoards(actualBoard, typeOfMethod, p1);
                return result;
            }
        } else {
            // player 1 has won the game
            if(isWin == 1000) {
                return isWin + depth;
            }

            if(isWin == -1000) {
                return isWin - depth;
            }

            if(depth == 0) {
                int result = Board.checkBoards(actualBoard, typeOfMethod, p1);
                if(result < 0) {
                    return result - depth;
                } else {
                    return result + depth;
                }
            }
        }
        // tie situation
        if(Board.isFilled(actualBoard) && isWin != 1000 && isWin != -1000) {
            return 0;
        }

        //int row = 0, col = 0;
        if (maximize) {
            int tempResult = -100000;
            for(int i = 0; i < actualBoard[0].length; i++) {
                int offset = Board.columnHeight(actualBoard, i);
                if(offset < 5) {
                    actualBoard[offset][i] = p1.getSign();

                    //char [][] boardCopy = Utils.copyBoard(actualBoard);

                    int newResult = minmax(depth - 1, actualBoard, false, typeOfMethod, p1, p2);

                    actualBoard[offset][i] = '*';

                    if(newResult >= tempResult) {
                        tempResult = newResult;
                    }

                }
            }
            return tempResult;
        } else {
            int tempResult = 100000;
            for (int i = 0; i < actualBoard[0].length; i++) {
                int offset = Board.columnHeight(actualBoard, i);
                if(offset < 5) {
                    actualBoard[offset][i] = p2.getSign();

                    //char[][] boardCopy = Utils.copyBoard(actualBoard);

                    int newResult = minmax(depth - 1, actualBoard, true, typeOfMethod, p1, p2);

                    actualBoard[offset][i] = '*';

                    if (newResult <= tempResult) {
                        tempResult = newResult;

                    }

                }
            }
            return tempResult;

        }
    }

    public static int minmaxAlfaBeta(int depth, char [][] actualBoard, Boolean maximize, int typeOfMethod, Player p1, Player p2, int alpha, int beta) {
        int isWin = Board.boardCheckForWin(actualBoard, p1);
        if(typeOfMethod == 3) {
            if(isWin == 1000) {
                return isWin;
            }

            if(isWin == -1000) {
                return isWin;
            }

            if(depth == 0) {
                int result = Board.checkBoards(actualBoard, typeOfMethod, p1);
                return result;
            }
        } else {
            // player 1 has won the game
            if(isWin == 1000) {
                return isWin + depth;
            }

            if(isWin == -1000) {
                return isWin - depth;
            }

            if(depth == 0) {
                int result = Board.checkBoards(actualBoard, typeOfMethod, p1);
                if(result < 0) {
                    return result - depth;
                } else {
                    return result + depth;
                }
            }
        }

        // tie situation
        if(Board.isFilled(actualBoard) && isWin != 1000 && isWin != -1000) {
            return 0;
        }

        //int row = 0, col = 0;
        if (maximize) {
            int best = -10000;
            int tempResult = -100000;
            for(int i = 0; i < actualBoard[0].length; i++) {
                int offset = Board.columnHeight(actualBoard, i);
                if(offset < 5) {
                    actualBoard[offset][i] = p1.getSign();
                    //char [][] boardCopy = Utils.copyBoard(actualBoard);
                    int newResult = minmaxAlfaBeta(depth - 1, actualBoard, false, typeOfMethod, p1, p2, alpha, beta);
                    actualBoard[offset][i] = '*';
                    best = Math.max(best, newResult);
                    alpha = Math.max(alpha, best);
                    if(beta <= alpha) {
                        return best;
                    }
                    if(newResult >= tempResult) {
                        tempResult = newResult;
                    };
                }
            }

            return tempResult;
        } else {
            int best = 10000;
            int tempResult = 100000;
            for (int i = 0; i < actualBoard[0].length; i++) {
                int offset = Board.columnHeight(actualBoard, i);
                if(offset < 5) {
                    actualBoard[offset][i] = p2.getSign();
                    //char[][] boardCopy = Utils.copyBoard(actualBoard);

                    int newResult = minmaxAlfaBeta(depth - 1, actualBoard, true, typeOfMethod, p1, p2, alpha, beta);
                    actualBoard[offset][i] = '*';

                    best = Math.min(best, newResult);
                    beta = Math.min(beta, best);
                    if(beta <= alpha) {
                        return best;
                    }
                    if (newResult <= tempResult) {
                        tempResult = newResult;
                    }

                }
            }
            return tempResult;

        }
    }


}


