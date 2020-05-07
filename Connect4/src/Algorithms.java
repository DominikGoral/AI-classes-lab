public class Algorithms {

    public static int minmax(int depth, char [][] actualBoard, Boolean maximize, Player p1, Player p2) {
        int result = Board.checkBoards(actualBoard, p1, p2);

        // player 1 has won the game
        if(result == 20 || depth == 0) {
            return result;
        }

        // player 2 has won the game
        if(result == -20 || depth == 0) {
            return result;
        }

        // tie situation
        if(Board.isFilled(actualBoard) && result != 20 && result != -20) {
            return 0;
        }

        //int row = 0, col = 0;
        if (maximize) {
            int tempResult = -100000;
            for(int i = 0; i < actualBoard[0].length; i++) {
                int offset = Board.columnHeight(actualBoard, i);
                if(offset < 5) {
                    //System.out.println("OFFSET: " + offset);
                    actualBoard[offset][i] = 'x';
                    char [][] boardCopy = Utils.copyBoard(actualBoard);
                    //Board.printBoard(actualBoard);
                    int newResult = minmax(depth - 1, boardCopy, !maximize, p2, p1);
                    //System.out.println("NEW RESULT: " + newResult);
                    if(newResult >= tempResult) {
                        tempResult = newResult;
                        //row = offset;
                        //col = i;
                    }
                    actualBoard[offset][i] = '*';
                    //Board.removeToken(actualBoard, 5 - offset, i, p1.getSign());
                }
            }
//            System.out.println("RZAD: " + row);
//            System.out.println("KOLUMNA: " + col);
//            System.out.println("TEMP RESULT: " + tempResult);
            return tempResult;
        } else {
            //System.out.print(actualBoard[0].length);
            int tempResult = 100000;
            for (int i = 0; i < actualBoard[0].length; i++) {
                int offset = Board.columnHeight(actualBoard, i);
                if(offset < 5) {
                    //System.out.println("OFFSET: " + offset);
                    actualBoard[offset][i] = 'o';
                    char[][] boardCopy = Utils.copyBoard(actualBoard);
                    //Board.printBoard(actualBoard);
                    //Board.dropToken(actualBoard, 5 - offset, i, p2.getSign());
                    //tempResult = Math.min(tempResult, minmax(depth - 1, boardCopy, !maximize, p2, p1));
                    int newResult = minmax(depth - 1, boardCopy, !maximize, p2, p1);
                    //System.out.println("NEW RESULT: " + newResult);
                    if (newResult <= tempResult) {
                        tempResult = newResult;
                        //row = offset;
                        //col = i;
                    }
                    actualBoard[offset][i] = '*';
                }
                //Board.removeToken(actualBoard, 5 - offset, i, p2.getSign());
            }
//            System.out.println("RZAD: " + row);
//            System.out.println("KOLUMNA: " + col);
//            System.out.println("TEMP RESULT: " + tempResult);
            return tempResult;

        }
    }

}
