package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main extends Application {

    private static final int TILE_SIZE = 80;
    private static final int COLUMNS = 7;
    private static final int ROWS = 6;

    public int depth = 4;
    public int secondDepth = 4;
    public int evaluateFunctionValue = 2;
    public int gameModeInfo = 2;
    public int minmaxTypeInfo = 0;

    public boolean redMove = true;
    public boolean userMoveFirst = true;
    private Disc [][] grid = new Disc[ROWS][COLUMNS];
    //private Board gameBoard = new Board();
    public Player p1 = new Player('x');
    public Player p2 = new Player('o');
    public char [][] gameBoard =
            {
                        {'*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*'},
                        {'*', '*', '*', '*', '*', '*', '*'}
            };

    PrintWriter writer = new PrintWriter("wyniki.txt");

    private Pane discRoot = new Pane();

    public Main() throws FileNotFoundException {
    }

    private Parent createContent() {
        Pane root = new Pane();
        root.getChildren().add(discRoot);

        Button computerMoveButton = new Button();
        Button computerAgainstComputer = new Button();
        Button startGame = new Button();
        ChoiceBox<Integer> difficulty;
        ChoiceBox<String> evaluateFunction;
        ChoiceBox<Integer> secondDifficulty;
        ChoiceBox<String> minmaxType;

        computerMoveButton.setText("Ruch komputera");
        computerAgainstComputer.setText("Komputer vs komputer");
        computerMoveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(Board.isEmpty(gameBoard)) {
                    Random rand = new Random();
                    int drawnColumn = rand.nextInt(7);
                    Board.dropToken(gameBoard, 0, drawnColumn, 'x');
                    placeDisc(new Disc(redMove), drawnColumn);
                    userMoveFirst = false;
                    Board.printBoard(gameBoard);
                } else {
                    long startTime = System.currentTimeMillis();
                    Move actualMove = new Move();
                    if(userMoveFirst) {
                        actualMove = Move.findBestMove(gameBoard, depth, evaluateFunctionValue, minmaxTypeInfo, p2, p1);
                    } else {
                        actualMove = Move.findBestMove(gameBoard, depth, evaluateFunctionValue, minmaxTypeInfo, p1, p2);
                    }
                    long endTime = System.currentTimeMillis();
                    writer.println(endTime - startTime);
                    System.out.println(endTime - startTime);
                    System.out.println("Poziom: " + depth);
                    if(userMoveFirst) {
                        Board.dropToken(gameBoard, actualMove.row, actualMove.col, 'o');
                    } else {
                        Board.dropToken(gameBoard, actualMove.row, actualMove.col, 'x');
                    }


                    //grid[actualMove.col][actualMove.row] = new Disc(redMove);
                    placeDisc(new Disc(redMove), actualMove.col);
                    Board.printBoard(gameBoard);
                }

            }
        });
        computerAgainstComputer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!(Board.isFilled(gameBoard) || (Board.boardCheckForWin(gameBoard, p1) == 1000 || Board.boardCheckForWin(gameBoard, p1) == -1000))) {
                    if(redMove) {
                        Move actualMoveFirstPC = Move.findBestMove(gameBoard, depth, evaluateFunctionValue, minmaxTypeInfo, p1, p2);
                        Board.dropToken(gameBoard, actualMoveFirstPC.row, actualMoveFirstPC.col, p1.getSign());

                        //grid[actualMove.col][actualMove.row] = new Disc(redMove);
                        placeDisc(new Disc(redMove), actualMoveFirstPC.col);
                        Board.printBoard(gameBoard);

                    } else {
                        Move actualMoveSecondPC = Move.findBestMove(gameBoard, secondDepth, evaluateFunctionValue, minmaxTypeInfo, p2, p1);
                        Board.dropToken(gameBoard, actualMoveSecondPC.row, actualMoveSecondPC.col, p2.getSign());

                        //grid[actualMove.col][actualMove.row] = new Disc(redMove);
                        placeDisc(new Disc(redMove), actualMoveSecondPC.col);
                        Board.printBoard(gameBoard);

                    }

                    System.out.print(redMove);
                }
            }
        });

        computerMoveButton.setDisable(true);
        computerAgainstComputer.setDisable(true);

        Label gameMode = new Label("Tryb gry");
        gameMode.setTextFill(Color.WHITE);
        gameMode.setLayoutX(650);
        gameMode.setLayoutY(50);

        ToggleGroup gameModeRadioButton = new ToggleGroup();

        RadioButton PCvsPC = new RadioButton("PC vs PC");
        PCvsPC.setUserData(1);
        RadioButton UservsPC = new RadioButton("Użytkownik vs PC");
        UservsPC.setUserData(2);



        PCvsPC.setToggleGroup(gameModeRadioButton);
        UservsPC.setToggleGroup(gameModeRadioButton);

        PCvsPC.setTextFill(Color.WHITE);
        UservsPC.setTextFill(Color.WHITE);

        PCvsPC.setLayoutX(720);
        PCvsPC.setLayoutY(50);

        UservsPC.setLayoutX(720);
        UservsPC.setLayoutY(80);

        Label difficultyLabel = new Label("Poziom trudności (głębokość drzewa):");
        difficultyLabel.setTextFill(Color.WHITE);
        difficultyLabel.setLayoutX(650);
        difficultyLabel.setLayoutY(120);

        Label evaluateFunctionLabel = new Label("Funkcja oceny");
        evaluateFunctionLabel.setTextFill(Color.WHITE);
        evaluateFunctionLabel.setLayoutX(650);
        evaluateFunctionLabel.setLayoutY(184);

        evaluateFunction = new ChoiceBox<>();
        evaluateFunction.getItems().add("Czworki");
        evaluateFunction.getItems().add("Czworki i trojki");
        evaluateFunction.getItems().add("Czworki, trojki, dwojki");
        evaluateFunction.getItems().add("Czworki, trojki, dwojki- bez wplywu glebokosci");
        evaluateFunction.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                evaluateFunctionValue = newValue.intValue();
            }
        });
        evaluateFunction.setLayoutX(650);
        evaluateFunction.setLayoutY(210);

        Label minmaxTypeLabel = new Label("Typ minimaxa");
        minmaxTypeLabel.setTextFill(Color.WHITE);
        minmaxTypeLabel.setLayoutX(650);
        minmaxTypeLabel.setLayoutY(250);

        minmaxType = new ChoiceBox<>();
        minmaxType.getItems().add("Minmax");
        minmaxType.getItems().add("Minmax alpha beta");
        minmaxType.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                minmaxTypeInfo = newValue.intValue();
            }
        });
        minmaxType.setLayoutX(650);
        minmaxType.setLayoutY(280);

        difficulty = new ChoiceBox<>();
        difficulty.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
        difficulty.setValue(4);
        difficulty.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                depth = newValue.intValue() + 1;
            }
        });

        difficulty.setLayoutX(700);
        difficulty.setLayoutY(150);

        secondDifficulty = new ChoiceBox<>();
        secondDifficulty.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8);
        secondDifficulty.setValue(4);
        secondDifficulty.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                secondDepth = newValue.intValue() + 1;
            }
        });

        secondDifficulty.setDisable(true);

        secondDifficulty.setLayoutX(780);
        secondDifficulty.setLayoutY(150);

        gameModeRadioButton.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                gameModeInfo = (int)gameModeRadioButton.getSelectedToggle().getUserData();
                if((int)gameModeRadioButton.getSelectedToggle().getUserData() == 1) {
                    secondDifficulty.setDisable(false);
                } else {
                    secondDifficulty.setDisable(true);
                }
            }
        });

        startGame.setText("START");
        startGame.setLayoutX(650);
        startGame.setLayoutY(370);

        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PCvsPC.setDisable(true);
                UservsPC.setDisable(true);
                difficulty.setDisable(true);
                secondDifficulty.setDisable(true);
                evaluateFunction.setDisable(true);
                minmaxType.setDisable(true);
                if(PCvsPC.isSelected()) {
                    computerMoveButton.setDisable(true);
                    computerAgainstComputer.setDisable(false);
                } else {
                    computerMoveButton.setDisable(false);
                    computerAgainstComputer.setDisable(true);
                }
            }
        });

        computerMoveButton.setLayoutX(650);
        computerMoveButton.setLayoutY(450);

        computerAgainstComputer.setLayoutX(650);
        computerAgainstComputer.setLayoutY(410);

        Shape gridShape = makeGrid();
        root.getChildren().add(gridShape);
        root.getChildren().add(computerMoveButton);
        root.getChildren().add(computerAgainstComputer);
        root.getChildren().add(gameMode);
        root.getChildren().add(PCvsPC);
        root.getChildren().add(UservsPC);
        root.getChildren().add(difficultyLabel);
        root.getChildren().add(difficulty);
        root.getChildren().add(startGame);
        root.getChildren().add(evaluateFunction);
        root.getChildren().add(evaluateFunctionLabel);
        root.getChildren().add(minmaxTypeLabel);
        root.getChildren().add(minmaxType);
        root.getChildren().add(secondDifficulty);
        root.getChildren().addAll(makeColumns());

        return root;
    }

    private Shape makeGrid() {
        Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE + 400, (ROWS + 1) * TILE_SIZE);
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLUMNS; j++) {
                Circle circle = new Circle(TILE_SIZE / 2);
                circle.setCenterX(TILE_SIZE / 2);
                circle.setCenterY(TILE_SIZE / 2);
                circle.setTranslateX(j * (TILE_SIZE + 5) + TILE_SIZE / 4);
                circle.setTranslateY(i * (TILE_SIZE + 5) + TILE_SIZE / 4);

                shape = Shape.subtract(shape, circle);
            }
        }
        Light.Distant light = new Light.Distant();
        light.setAzimuth(45.0);
        light.setElevation(30.0);

        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        shape.setFill(Color.BLUE);
        shape.setEffect(lighting);




        return shape;
    }



    private List<Rectangle> makeColumns() {
        List<Rectangle> list = new ArrayList<>();

        for(int i = 0; i < COLUMNS; i++) {
            Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
            rect.setTranslateX(i * (TILE_SIZE + 5) + TILE_SIZE / 4);
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = i;
//            if(redMove) {
//                rect.setOnMouseClicked(e -> placeDisc(new Disc(redMove), column));
//            } else {
//                rect.setOnMouseClicked(e -> {
//
//                    placeDisc(new Disc(redMove), actualMove.col);
//                });
//
//            }
            rect.setOnMouseClicked(e -> placeDisc(new Disc(redMove), column));

            list.add(rect);
        }
        return list;
    }

    private void placeDisc(Disc disc, int column) {
        int row = ROWS - 1;
        do {
            if(!getDisc(column, row).isPresent()) {
                break;
            }
            row--;
        } while (row >= 0);

        if (row < 0) {
            return;
        }


        discRoot.getChildren().add(disc);
        disc.setTranslateX(column * (TILE_SIZE + 5) + TILE_SIZE / 4);
        System.out.println("ROW: " + row + "  COL: " + column);
        if(redMove) {
            Board.dropToken(gameBoard, 5 - row, column, 'x');
        } else {
            Board.dropToken(gameBoard, 5 - row, column, 'o');
        }
        grid[row][column] = disc;

        Board.printBoard(gameBoard);



        final int currentRow = row;

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
        animation.setToY(row * (TILE_SIZE + 5) + TILE_SIZE / 4);
        animation.setOnFinished(e -> {
            if(Board.isFilled(gameBoard) || (Board.boardCheckForWin(gameBoard, p1) == 1000 || Board.boardCheckForWin(gameBoard, p1) == -1000)) {
                gameOver();
                writer.close();
            }

            redMove = !redMove;
        });
        animation.play();
    }

    private boolean gameEnded(int column, int row) {
        List<Point2D> vertical = IntStream.rangeClosed(row - 3, row + 3).mapToObj(r -> new Point2D(column, r)).collect(Collectors.toList());
        List<Point2D> horizontal = IntStream.rangeClosed(column - 3, column + 3).mapToObj(c -> new Point2D(c, row)).collect(Collectors.toList());

        Point2D topLeft = new Point2D(column - 3, row - 3);
        List<Point2D> diagonal = IntStream.rangeClosed(0, 6).mapToObj(i -> topLeft.add(i, i)).collect(Collectors.toList());
        Point2D botLeft = new Point2D(column - 3, row - 3);
        List<Point2D> diagonal2 = IntStream.rangeClosed(0, 6).mapToObj(i -> botLeft.add(i, -i)).collect(Collectors.toList());

        return checkRange(vertical) || checkRange(horizontal) || checkRange(diagonal) || checkRange(diagonal2);
    }

    private boolean checkRange(List<Point2D> points) {
        int chain = 0;

        for(Point2D p: points) {
            int column = (int) p.getX();
            int row = (int) p.getY();

            Disc disc = getDisc(column, row).orElse(new Disc(!redMove));
            if(disc.red == redMove) {
                chain++;
                if(chain == 4) {
                    return true;
                }
            } else {
                chain = 0;
            }
        }
        return false;
    }

    private void gameOver() {
        System.out.println("WINNER: " + (redMove ? "RED" : "YELLOW"));
        Platform.exit();
    }

    private Optional<Disc> getDisc(int column, int row) {
        if(column < 0 || column >= COLUMNS || row < 0 || row >= ROWS) {
            return Optional.empty();
        }
        return Optional.ofNullable(grid[row][column]);
    }

    private static class Disc extends Circle {
        private final boolean red;

        public Disc(boolean red) {
            super(TILE_SIZE / 2, red ? Color.RED : Color.YELLOW);
            this.red = red;

            setCenterX(TILE_SIZE / 2);
            setCenterY(TILE_SIZE / 2);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception

    {
        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
