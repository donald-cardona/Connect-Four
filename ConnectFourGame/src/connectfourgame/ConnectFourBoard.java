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
    
    public ConnectFourBoard() {
        grid = new GridPane();
        turn = "red";
        winner = new Label("");
        count = 0;
        
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                ConnectFourPane connect = new ConnectFourPane();
                connect.setOnMouseClicked(new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent event) {
                        if(turn.equalsIgnoreCase("red")){
                            connect.paintRed();
                            turn = "yellow";
                            count++;
                            
                            connect.setDisable(true);
                            if(grid.getChildren().indexOf(connect) > 6)
                                grid.getChildren().get((grid.getChildren().indexOf(connect))-7).setDisable(false);
                        }
                        else if(turn.equalsIgnoreCase("yellow")){
                            connect.paintYellow();
                            turn = "red";
                            count++;
                            
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
}
