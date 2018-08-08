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
 * 
 * @author Donald Cardona
 */
public class ConnectFourPane extends StackPane{
    private Rectangle rect;
    private Circle c;
    
    public ConnectFourPane() {
        rect = new Rectangle(0, 0, 60, 60);
        rect.setFill(Color.BLUE);
        c = new Circle(25);
        c.setFill(Color.WHITE);
        c.setStroke(Color.BLACK);
        this.getChildren().addAll(rect, c);
        this.setDisable(true);
    }
    
    public void paintRed() {
        c.setFill(Color.RED);
    }
    
    public void paintYellow() {
        c.setFill(Color.YELLOW);
    }
    
    
}
