public class Main {

    public static void main(String[] args) {

        Board gameBoard = new Board();
        Player p1 = new Player('x');
        Player p2 = new Player('o');

//        char [][] board =
//                {
//                        {'*', '*', '*', '*', '*', '*', '*'},
//                        {'*', '*', '*', '*', '*', '*', '*'},
//                        {'*', '*', '*', 'o', '*', '*', '*'},
//                        {'*', '*', '*', '*', 'o', '*', '*'},
//                        {'*', '*', '*', '*', '*', 'o', '*'},
//                        {'*', '*', '*', '*', '*', '*', 'o'}
//                };
//
//        gameBoard.setBoard(board);



        gameBoard.initializeBoard();

        //Board.printBoard(gameBoard.getBoard());
        //Game g = new Game();

//        Move m = new Move();
//        m.findBestMove(board, p2, p1);
//        Move.findBestMove(board, p1, p2);
//
        Game newGame = new Game();
        newGame.run();
        //System.out.print(Board.boardCheckForWin(board, p1));

    }
}
