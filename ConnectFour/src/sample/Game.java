package sample;

import java.util.Scanner;

public class Game {

    Player p1 = new Player('x');
    Player p2 = new Player('o');

    public void run() {

        Scanner in = new Scanner(System.in);

        Board grid = new Board();

        Move move = new Move();

        grid.initializeBoard();

        Board.printBoard(grid.getBoard());

        boolean playerOneMove = true;

        while(!(Board.isFilled(grid.getBoard()) || (Board.boardCheckForWin(grid.getBoard(), p1) == 20 || Board.boardCheckForWin(grid.getBoard(), p1) == 20))) {
            if(playerOneMove) {
                boolean chosenColumnIsCorrect = false;
                int chosenColumnNumber = -1;
                int row = 0;
                while(!chosenColumnIsCorrect) {
                    System.out.println("Podaj nr kolumny (1- 7) do której chcesz wrzucić żeton");
                    chosenColumnNumber = in.nextInt();
                    if(Utils.providedColumnIsCorrect(chosenColumnNumber)) {
                        if(Utils.providedColumnIsAvailable(grid.getBoard(), chosenColumnNumber - 1)) {
                            row = Board.columnHeight(grid.getBoard(), chosenColumnNumber - 1);
                            chosenColumnIsCorrect = true;
                        } else {
                            System.out.println("Wybrana kolumna jest już pełna!");
                        }
                    } else {
                        System.out.println("Wybrany numer kolumny jest niedostępny!");
                    }
                }

                Board.dropToken(grid.getBoard(), row, chosenColumnNumber - 1, p1.getSign());
                Board.printBoard(grid.getBoard());

                playerOneMove = false;
            } else {
                // KOMPUTER

//                Move actualMove = Move.findBestMove(grid.getBoard());
//                Board.dropToken(grid.getBoard(), actualMove.row, actualMove.col, 'o');
//                Board.printBoard(grid.getBoard());

                // DRUGI GRACZ
                /*
                boolean chosenColumnIsCorrect = false;
                int chosenColumnNumber = -1;
                int row = 0;
                while(!chosenColumnIsCorrect) {
                    System.out.println("Podaj nr kolumny (1- 7) do której chcesz wrzucić żeton");
                    chosenColumnNumber = in.nextInt();
                    if(Utils.providedColumnIsCorrect(chosenColumnNumber - 1)) {
                        if(Utils.providedColumnIsAvailable(grid.getBoard(), chosenColumnNumber - 1)) {
                            row = Board.columnHeight(grid.getBoard(), chosenColumnNumber - 1);
                            chosenColumnIsCorrect = true;
                        } else {
                            System.out.println("Wybrana kolumna jest już pełna!");
                        }
                    } else {
                        System.out.println("Wybrany numer kolumny jest niedostępny!");
                    }
                }

                Board.dropToken(grid.getBoard(), row, chosenColumnNumber - 1, p2.getSign());
                Board.printBoard(grid.getBoard());
                */

                playerOneMove = true;

            }
        }


    }


}

