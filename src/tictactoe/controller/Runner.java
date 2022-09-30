package tictactoe.controller;

/**
 * Project is based on the Model–view–controller (MVC) pattern, <code>Controller</code> gets initialized here.
 */
public class Runner {

    /**
     * Initializes <code>Controller</code>.
     * The window title gets defined here.
     *
     * @param args an array of command-line arguments for the application, not used
     */
    public static void main(String... args) {
        String windowTitle = "TicTicToe";

        new Controller(windowTitle);
    }
}
