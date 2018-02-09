/**
 *
 */
package edu.kit.informatik.five;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Christoph Kern
 * @version 1.0
 */
public class MainFrame extends Application  {
    private static final Text[][] FIELDS = new Text[15][15];
    private static final TextField EINGABE = new TextField();
    private static final TextField AUSGABE = new TextField();
    private static Scene scene;

    /**
     * Start method for java fx, which generats a 15*15 playing field in java fx
     * @param primaryStage ?
     */
    public void start(Stage primaryStage) {

        
        Group g = new Group();

        //Text[][] f = new Text[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                FIELDS[i][j] = new Text("-");
                FIELDS[i][j].setY(i * 25 + 50);
                FIELDS[i][j].setX(j * 25 + 25);
                g.getChildren().add(FIELDS[i][j]);
            }
        }
        
        Text[] beschriftung = new Text[30];
        for (int i = 0; i < 30; i++) {
            beschriftung[i] = new Text("");
            if (i < 15) {
                beschriftung[i].setY(i * 25 + 50);
                beschriftung[i].setX(0);
                beschriftung[i].setText("" + (i + 1) + "");
                
            } else {
                beschriftung[i].setY(25);
                beschriftung[i].setX((i - 15) * 25 + 25);
                beschriftung[i].setText("" + (i - 14) + "");
            }
            
            g.getChildren().add(beschriftung[i]);
        }
        
        
        EINGABE.setLayoutX(100);
        EINGABE.setLayoutY(480);
        g.getChildren().add(EINGABE);
        
        Label eingabeLabel = new Label("Eingabe");
        eingabeLabel.setLayoutX(10);
        eingabeLabel.setLayoutY(480);
        g.getChildren().add(eingabeLabel);
        
        
        AUSGABE.setLayoutX(100);
        AUSGABE.setLayoutY(420);
        g.getChildren().add(AUSGABE);
        AUSGABE.setDisable(true);        
        Label ausgabeLabel = new Label("Ausgabe");
        ausgabeLabel.setLayoutX(10);
        ausgabeLabel.setLayoutY(420);
        g.getChildren().add(ausgabeLabel);
        
        scene = new Scene(g, 450, 550);
        
        scene.setFill(Color.AQUA);
        
        scene.setOnKeyPressed(event  -> FiveRow.main(event));
        
        primaryStage.setTitle("Five Row!");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(event -> FiveRow.quit());
    }
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void setFrame(int player, int row, int column) {
        FIELDS[row][column].setText("" + player + "");
    }
    
    public static String getInput() {
        String i = EINGABE.getText(0, EINGABE.getLength());
        
        return i;
    }
    
    public static void setOutput(String output) {
        AUSGABE.setText(output);
    }
}
