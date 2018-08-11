/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfourgame;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Donald
 */
public class ConnectFourBoard extends BorderPane{
    private GridPane grid;
    private String turn;
    private Label winner;
    private int count;
    private char[][] mark;
    
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
        
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                ConnectFourPane connect = new ConnectFourPane();
                connect.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        if(turn.equalsIgnoreCase("red")){
                            connect.paintRed();
                            mark[(int)grid.getRowIndex(connect)][(int)grid.getColumnIndex(connect)] = 'r';
                            turn = "yellow";
                            count++;
                            checkWin();
                            
                            connect.setDisable(true);
                            if(grid.getChildren().indexOf(connect) > 6)
                                grid.getChildren().get((grid.getChildren().indexOf(connect))-7).setDisable(false);
                        }
                        else if(turn.equalsIgnoreCase("yellow")){
                            connect.paintYellow();
                            mark[(int)grid.getRowIndex(connect)][(int)grid.getColumnIndex(connect)] = 'y';
                            turn = "red";
                            count++;
                            checkWin();
                            
                            connect.setDisable(true);
                            if(grid.getChildren().indexOf(connect) > 6)
                                grid.getChildren().get((grid.getChildren().indexOf(connect))-7).setDisable(false);
                        }      
                    }
                });
                grid.add(connect, j, i);
            }
        }
        for(int k = 35; k < 42; k++) {
            grid.getChildren().get(k).setDisable(false);
        }
        grid.setPrefSize(410, 350);
        this.getChildren().add(grid);
        
    }
    
    public void checkWin() {
        boolean isWin = false;
        
        //for loop to check win by 4 in a row
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 4; j++) {
                if(mark[i][0+j] == mark[i][1+j] && mark[i][1+j] == mark[i][2+j] && mark[i][2+j] == mark[i][3+j] && mark[i][1+j] == 'r') {
                    System.out.println("Red wins");
                }
                else if(mark[i][0+j] == mark[i][1+j] && mark[i][1+j] == mark[i][2+j] && mark[i][2+j] == mark[i][3+j] && mark[i][1+j] == 'y') {
                    System.out.println("Yellow wins");
                }
            }
        }
        
        //for loop to check win by 4 in a column
        for(int n = 0; n < 7; n++ ) {
            for(int m = 0; m < 3; m++) {
                if(mark[0+m][n] == mark[1+m][n] && mark[1+m][n] == mark[2+m][n] && mark[2+m][n] == mark[3+m][n] && mark[1+m][n] == 'r') {
                    System.out.println("Red wins");
                }
                else if(mark[0+m][n] == mark[1+m][n] && mark[1+m][n] == mark[2+m][n] && mark[2+m][n] == mark[3+m][n] && mark[1+m][n] == 'y') {
                    System.out.println("Yellow wins");
                }
            }
        }
        
        //for loop to check win by 4 in a forward diagonal
        for(int y = 0; y < 4; y++ ) {
            for(int x = 0; x < 3; x++) {
                if(mark[x+3][y] == mark[x+2][y+1] && mark[x+2][y+1] == mark[x+1][y+2] && mark[x+1][y+2] == mark[x][y+3] && mark[x+2][y+1] == 'r') {
                    System.out.println("Red wins");
                }
                else if(mark[x+3][y] == mark[x+2][y+1] && mark[x+2][y+1] == mark[x+1][y+2] && mark[x+1][y+2] == mark[x][y+3] && mark[x+2][y+1] == 'y') {
                    System.out.println("Yellow wins");
                }
            }
        }
        
        //for loop to checl win by 4 in a backward diagonal
        for(int y = 3; y > -1; y-- ) {
            for(int x = 0; x < 3; x++) {
                if(mark[x][y] == mark[x+1][y+1] && mark[x+1][y+1] == mark[x+2][y+2] && mark[x+2][y+2] == mark[x+3][y+3] && mark[x+1][y+1] == 'r') {
                    System.out.println("Red wins");
                }
                else if(mark[x][y] == mark[x+1][y+1] && mark[x+1][y+1] == mark[x+2][y+2] && mark[x+2][y+2] == mark[x+3][y+3] && mark[x+1][y+1] == 'y') {
                    System.out.println("Yellow wins");
                }
            }
        }
    }
}
