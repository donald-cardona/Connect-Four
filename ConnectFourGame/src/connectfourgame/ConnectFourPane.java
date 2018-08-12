/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectfourgame;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 * This class is designed to create a spot where players can insert their chip.
 * Multiple panes will be organized into a board in the ConnectFourBoard class.
 * @author Donald Cardona
 */
public class ConnectFourPane extends StackPane{
    private Rectangle rect;                 //Used to set up background of board and allow space between each circle.
    private Circle c;                       //Circle where players will put their chips into during the game
    
    /**
     * Constructor is designed to instantiate the rectangle and circle object.
     * Circle object will also be disable and unable to use primarily until
     * organized into the Board.
     */
    public ConnectFourPane() {
        rect = new Rectangle(0, 0, 60, 60);
        rect.setFill(Color.BLUE);
        c = new Circle(25);
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        this.getChildren().addAll(rect, c);
        this.setDisable(true);
    }
    
    /**
     * Method designed to place red chip into the circle object when it is
     * Red's turn
     */
    public void paintRed() {
        c.setFill(Color.RED);
    }
    
    /**
     * Method designed to place red chip into the circle object when it is
     * Yellow's turn
     */
    public void paintYellow() {
        c.setFill(Color.YELLOW);
    }
    
    
}
