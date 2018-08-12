/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfourgame;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is designed to set up the connect four board game along with
 * two buttons to start a new game and exit the program. The board game is set 
 * up using Connect Four Panes.
 * @author Donald Cardona
 */
public class ConnectFourBoard extends VBox{
    private GridPane grid;              //Grid used to set up multiple ConnectFourPanes
    private String turn;                //String to determine who's turn it is
    private Label winner;               //Used to display outcome of current game
    private int count;                  //Used to determine how many moves were used and if the game results in a draw  
    private char[][] mark;              //Separate grid that records which spot holds a red chip or a yellow chip
    private HBox hbox;                  //Used to set a horizontal layout for the two buttons
    private Button newGame;             //Used to set up a new game
    private Button exit;                //Used to close the program
    
    /**
     * Constructor is used to instantiate and initialize all variables. It is 
     * also designed to set ActionEvents for the Buttons and ConnectFourPanes
     * used in this game.
     */
    public ConnectFourBoard() {
        grid = new GridPane();
        turn = "red";
        winner = new Label("");
        count = 0;
        mark = new char[][] {
            {'b', 'b', 'b', 'b', 'b', 'b', 'b'},
            {'b', 'b', 'b', 'b', 'b', 'b', 'b'},
            {'b', 'b', 'b', 'b', 'b', 'b', 'b'},
            {'b', 'b', 'b', 'b', 'b', 'b', 'b'},
            {'b', 'b', 'b', 'b', 'b', 'b', 'b'},
            {'b', 'b', 'b', 'b', 'b', 'b', 'b'},
                    
        };
        hbox = new HBox();
        
        //ActionEvent used to start a new game with a clean Connect Four Board
        newGame = new Button("New Game");
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ConnectFourBoard board = new ConnectFourBoard();
                Scene scene = new Scene(board, 500, 500);
                Stage primaryStage = new Stage();
                
                primaryStage.setTitle("Connect Four");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        });
        
        //ActionEvent used to exit the program
        exit = new Button("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.exit(-1);
            }
        });
        
        //Adding the two buttons to the Horizontal Layout
        hbox.getChildren().addAll(newGame, exit);
        
        //For Loop used to initialize a total of 42 ConnectFourPanes and 
        //organize it into a grid as well as setting ActionEvents for each pane.
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                ConnectFourPane connect = new ConnectFourPane();
                
                //Action Event to place yellow chip or red chip into the most
                //bottom unfilled space in a column and to check for a win
                connect.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        
                        //Scenario when it is Red's Turn
                        if(turn.equalsIgnoreCase("red")){
                            connect.paintRed();
                            mark[(int)grid.getRowIndex(connect)][(int)grid.getColumnIndex(connect)] = 'r';
                            turn = "yellow";
                            count++;
                            if(checkWin() == true) {
                                grid.setDisable(true);
                                winner.setText("Red Wins");
                            }
                            
                            //Allows spot where Red Chip is placed to not change
                            //and allow players a new spot to place their chips
                            //above it
                            connect.setDisable(true);
                            if(grid.getChildren().indexOf(connect) > 6)
                                grid.getChildren().get((grid.getChildren().indexOf(connect))-7).setDisable(false);
                        }
                        
                        //Scenario for when it is Yellow's Turn
                        else if(turn.equalsIgnoreCase("yellow")){
                            connect.paintYellow();
                            mark[(int)grid.getRowIndex(connect)][(int)grid.getColumnIndex(connect)] = 'y';
                            turn = "red";
                            count++;
                            checkWin();
                            if(checkWin() == true) {
                                grid.setDisable(true);
                                winner.setText("Yellow Wins");
                            }
                            
                            //Allows spot where Yellow Chip is placed to not change
                            //and allow players a new spot to place their chips
                            //above it
                            connect.setDisable(true);
                            if(grid.getChildren().indexOf(connect) > 6)
                                grid.getChildren().get((grid.getChildren().indexOf(connect))-7).setDisable(false);
                        }      
                    }
                });
                //Adding each ConnectFourPane onto the grid
                grid.add(connect, j, i);
            }
        }
        //Allowing only the bottom row of the board to be choices for the
        //players to place their chips. Since this is a Connect Four, it must
        //follow the rules of gravity taking each chip to most bottom spot in a
        //column.
        for(int k = 35; k < 42; k++) {
            grid.getChildren().get(k).setDisable(false);
        }
        grid.setPrefSize(410, 350);
        this.getChildren().addAll(grid, winner, hbox);
        
    }
    
    /**
     * This method is designed to check the board if there are any wins by 4 in
     * a row, column, diagonal, or anti-diagonal. It will also determine if the
     * game has led to a draw.
     * 
     * @return True if there is a win and stopping the game or false if there
     *          has not been a win declaration yet and continuing the game.
     */
    public boolean checkWin() {
        //for loop to check win by 4 in a row
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 4; j++) {
                if(mark[i][0+j] == mark[i][1+j] && mark[i][1+j] == mark[i][2+j] && mark[i][2+j] == mark[i][3+j] && mark[i][1+j] == 'r') {
                    return true;
                }
                else if(mark[i][0+j] == mark[i][1+j] && mark[i][1+j] == mark[i][2+j] && mark[i][2+j] == mark[i][3+j] && mark[i][1+j] == 'y') {
                    return true;
                }
            }
        }
        
        //for loop to check win by 4 in a column
        for(int n = 0; n < 7; n++ ) {
            for(int m = 0; m < 3; m++) {
                if(mark[0+m][n] == mark[1+m][n] && mark[1+m][n] == mark[2+m][n] && mark[2+m][n] == mark[3+m][n] && mark[1+m][n] == 'r') {
                    return true;
                }
                else if(mark[0+m][n] == mark[1+m][n] && mark[1+m][n] == mark[2+m][n] && mark[2+m][n] == mark[3+m][n] && mark[1+m][n] == 'y') {
                    return true;
                }
            }
        }
        
        //for loop to check win by 4 in a forward diagonal
        for(int y = 0; y < 4; y++ ) {
            for(int x = 0; x < 3; x++) {
                if(mark[x+3][y] == mark[x+2][y+1] && mark[x+2][y+1] == mark[x+1][y+2] && mark[x+1][y+2] == mark[x][y+3] && mark[x+2][y+1] == 'r') {
                    return true;
                }
                else if(mark[x+3][y] == mark[x+2][y+1] && mark[x+2][y+1] == mark[x+1][y+2] && mark[x+1][y+2] == mark[x][y+3] && mark[x+2][y+1] == 'y') {
                    return true;
                }
            }
        }
        
        //for loop to checl win by 4 in a backward diagonal
        for(int y = 3; y > -1; y-- ) {
            for(int x = 0; x < 3; x++) {
                if(mark[x][y] == mark[x+1][y+1] && mark[x+1][y+1] == mark[x+2][y+2] && mark[x+2][y+2] == mark[x+3][y+3] && mark[x+1][y+1] == 'r') {
                    return true;
                }
                else if(mark[x][y] == mark[x+1][y+1] && mark[x+1][y+1] == mark[x+2][y+2] && mark[x+2][y+2] == mark[x+3][y+3] && mark[x+1][y+1] == 'y') {
                    return true;
                }
            }
        }
        
        //If no other win is determined and hte board is filled, the game is a draw
        if(count == 42) {
            winner.setText("It's a Draw");
        }
        
        //If no win is determined and there are still moves to make, the method
        //will return false
        return false;
    }
}
