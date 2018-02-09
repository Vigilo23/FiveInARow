/**
 * 
 */
package edu.kit.informatik.five;


import javafx.scene.input.KeyEvent;

/**
 * @author Christoph Kern
 * @version 1.0
 */
public class FiveRow {

    private static int roundCounter = 1;
    private static String command = "start";
    private static boolean win = false;
    private static boolean draw = false;
    private static PlayingField field = new PlayingField();
    private static String userinput = "";

    /**
     * @param event Keyevent
     */
    public static void main(KeyEvent event) {

        switch (event.getCode()) {
        case ENTER:
            userinput = MainFrame.getInput();
            command = inputToCommand(userinput);

            if (command.equals("quit")) {
                quit();
            } else if (command.equals("place")) {
                place(userinput);
            } else {
                MainFrame.setOutput("Unknown Command");
            }
            break;
        }

    }

    /**
     * Do a new Playing turn
     * 
     * @param string
     *            userinput
     * @throws NumberFormatException
     * @throws ArrayIndexOutOfBoundsException
     */
    static void place(String string) {
        if (win == true) {
            MainFrame.setOutput("The match has already ended!");
            return;
        }
        if (draw == true) {
            MainFrame.setOutput("There are no possible moves anymore!");
            return;
        }
        String input[];
        try {
            input = inputSplit(string);
        } catch (ArrayIndexOutOfBoundsException r) {
            MainFrame.setOutput("Please enter your command with Coordinates");
            return;
        }
        int row = -1;
        int column = -1;
        try {
            row = Integer.valueOf(input[0]);
            column = Integer.valueOf(input[1]);
        } catch (NumberFormatException e) {
            MainFrame.setOutput("Please try Again, your command was not in the right format.");
            return;
        } catch (ArrayIndexOutOfBoundsException b) {
            MainFrame.setOutput("Please try Again, your command was not in the right format.");
            return;
        }

        int player;
        if (roundCounter % 2 == 0) {
            player = 2;
        } else {
            player = 1;
        }

        try {
            if (checkField(row, column) == false) {
                MainFrame.setOutput("the field is already in use!");
                return;
            }
            field.setField(player, row, column);
        } catch (ArrayIndexOutOfBoundsException f) {
            MainFrame.setOutput("Please try Again, your turn wasn´t insight the playable field.");
            return;
        }

        checkWin(player);
        checkFull();

        if (win != true && draw != true) {
            MainFrame.setOutput("OK");
            roundCounter++;
        }
    }

    /**
     * return the status from one specific field
     * 
     * @param string
     *            userinput
     */
    static void state(String string) {
        String input[];
        try {
            input = inputSplit(string);
        } catch (ArrayIndexOutOfBoundsException a) {
            MainFrame.setOutput("Please enter your command with Coordinates");
            return;
        }
        int row;
        int column;
        try {
            row = Integer.valueOf(input[0]);
            column = Integer.valueOf(input[1]);
        } catch (ArrayIndexOutOfBoundsException a) {
            MainFrame.setOutput("Please try Again, your command was not in the right format.");
            return;
        } catch (NumberFormatException e) {
            MainFrame.setOutput("Please try Again, your command was not in the right format.");
            return;
        }
        String output = "0";
        try {
            output = field.getField(row, column);
        } catch (ArrayIndexOutOfBoundsException a) {
            MainFrame.setOutput("your Command was outside the area!");
            return;
        }

        MainFrame.setOutput(output);
    }

    /**
     * convert the userinput to a command
     * 
     * @param string
     *            userinput
     * @return the command from user
     */
    static String inputToCommand(String string) {
        String array[] = string.split(" ");
        return array[0];
    }

    /**
     * convert the userinput into seperat informations
     * 
     * @param string
     *            iserinput
     * @return a string array with the informations
     * @throws ArrayIndexOutOfBoundsException
     */
    static String[] inputSplit(String string) throws ArrayIndexOutOfBoundsException {
        String array[] = string.split(" ");
        String information[] = array[1].split(";");

        return information;
    }

    /**
     * checks the status of field
     * 
     * @return true or false
     */
    static boolean checkFull() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (field.getField(i, j).equals("-")) {
                    return false;
                }
            }
        }
        MainFrame.setOutput("draw");
        draw = true;
        return true;
    }

    /**
     * Checks if the player that has played his turn has won the game.
     * 
     * @param player
     *            that is on turn.
     */
    static void checkWin(int player) {
        String playerString = "";
        playerString = playerString + player;
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 11; j++) {
                if (playerString.equals(field.getField(i, j)) && playerString.equals(field.getField(i, j + 1))
                        && playerString.equals(field.getField(i, j + 2))
                        && playerString.equals(field.getField(i, j + 3))
                        && playerString.equals(field.getField(i, j + 4))) {
                    MainFrame.setOutput("P" + player + " wins");
                    win = true;
                    return;
                }
            }
        }

        for (int j = 0; j < 15; j++) {
            for (int i = 0; i < 11; i++) {
                if (playerString.equals(field.getField(i, j)) && playerString.equals(field.getField(i + 1, j))
                        && playerString.equals(field.getField(i + 2, j))
                        && playerString.equals(field.getField(i + 3, j))
                        && playerString.equals(field.getField(i + 4, j))) {
                    MainFrame.setOutput("P" + player + " wins");
                    win = true;
                    return;
                }
            }
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (playerString.equals(field.getField(i, j)) && playerString.equals(field.getField(i + 1, j + 1))
                        && playerString.equals(field.getField(i + 2, j + 2))
                        && playerString.equals(field.getField(i + 3, j + 3))
                        && playerString.equals(field.getField(i + 4, j + 4))) {
                    MainFrame.setOutput("P" + player + " wins");
                    win = true;
                    return;
                }
            }
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 4; j < 15; j++) {
                if (playerString.equals(field.getField(i, j)) && playerString.equals(field.getField(i + 1, j - 1))
                        && playerString.equals(field.getField(i + 2, j - 2))
                        && playerString.equals(field.getField(i + 3, j - 3))
                        && playerString.equals(field.getField(i + 4, j - 4))) {
                    MainFrame.setOutput("P" + player + " wins");
                    win = true;
                    return;
                }
            }
        }

    }

    /**
     * Checks that the field is not already use.
     * 
     * @param row
     *            horizontal
     * @param column
     *            vertical
     * @return true if the field is "empty"
     */
    static boolean checkField(int row, int column) {
        if (field.getField(row, column).equals("1") || field.getField(row, column).equals("2")) {
            return false;
        }
        return true;
    }

    /**
     * quit command
     */
    public static void quit() {
        System.exit(0);
    }

}
