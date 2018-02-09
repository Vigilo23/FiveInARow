/**
 * 
 */
package edu.kit.informatik.five;

import edu.kit.informatik.Terminal;

/**
 * @author Christoph Kern
 * @version 1.0
 */
public class PlayingField {
    private String[][] field;
    
    /**
     * Constructor for new Playingfield
     * @param x
     * @param y
     */
    public PlayingField() {
        this.field = new String[15][15];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = "-";
            }
        }
    }
    
    /**
     * Get the current Status from a specific field
     * @param row horizontal
     * @param column vertikal
     * @return the specific field
     */
    public String getField(int row, int column) {
        return field[row][column];
    }
    
    /**
     * setting field 
     * @param player player who is at turn
     * @param row horizontal
     * @param column vertical
     */
    public void setField(int player, int row, int column) {
        if (player == 1) {
            field[row][column] = "1";
            MainFrame.setFrame(player, row, column);
        } else {
            field[row][column] = "2";
            MainFrame.setFrame(player, row, column);
        }
    }
    
    /**
     * send the Field to terminal for print
     */
    public void printPlayingField() {
        String output = null;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                if (j == 0) {
                    output = field[i][j];
                } else {
                    output = output + " " + field[i][j];
                }
                
            }
            MainFrame.setOutput(output);
        }
    }
    
    

}
