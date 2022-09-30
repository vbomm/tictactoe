package tictactoe.controller;

import tictactoe.model.Model;
import tictactoe.model.Owner;
import tictactoe.view.View;

import javax.swing.*;

/**
 * Gets user inputs from <code>View</code>, tells <code>View</code> what do do and gets new game states from <code>Model</code>.
 */
public class Controller {
    private String windowTitle;
    private Model model;
    private View view;
    private Messages messages;

    /**
     * Initializes a new <code>Controller</code>, sets the title of the window, creates new <code>Model</code> and <code>View</code> and <code>Messages</code>, latter to be used for localization.
     *
     * @param windowTitle the title of the window
     */
    public Controller(String windowTitle) {
        this.windowTitle = windowTitle;
        model = new Model();
        view = new View(this);
        messages = new Messages();
        initView();
        newGame();
        updateStatus();
    }

    /**
     * Run <code>View</code> in AWT Event dispatcher thread and show the frame.
     */
    private void initView() {
        SwingUtilities.invokeLater(view::show);
    }

    /**
     * Tells <code>Model</code> to start a new game.
     */
    private void newGame() {
        model.newGame();
    }

    /**
     * When status label gets clicked, call <code>Model</code>.
     */
    public void statusLabelClicked() {
        model.check();
    }

    /**
     * Sends <code>View</code> a string to update the status label to.
     */
    public void updateStatus() {
        String status;
        switch (model.getBoardState()) {
            case HUMANWON: {
                status = messages.humanWon();
                break;
            } case CPUWON: {
                status = messages.cpuWon();
                break;
            } case DRAW:
                status = messages.draw(); break;
            default:
                status = messages.inGame() + model.getCPUDepth(); break;
        }

        view.setStatusText(status);
    }

    /**
     * After a move, update the game board so <code>View</code> visually represents the current state of <code>Model</code>.
     * Show a message Box if game is over.
     *
     * @param index the index of the space that got clicked to execute a move
     */
    public void checkBox(int index) {
        model.check(index);

        updateStatus();

        for (int i = 0; i < 9; i++) {
            if (model.getOwner(i) == Owner.HUMAN)
                view.setSpaceToHuman(i);
            else if (model.getOwner(i) == Owner.CPU)
                view.setSpaceToCPU(i);
            else
                view.setSpaceToFree(i);
        }

        checkIfMessageShouldBeDisplayed();
    }

    /**
     * Tells <code>View</code> to display a message box if game is over.
     */
    public void checkIfMessageShouldBeDisplayed() {
        switch (model.getBoardState()) {
            case HUMANWON: {
                view.displayMessage(messages.getHumanWonBoxMessage(), messages.getHumanWonButtonMessage());
                break;
            } case CPUWON: {
                view.displayMessage(messages.getCPUWonBoxMessage(), messages.getCPUWonButtonMessage());
                break;
            } case DRAW: {
                view.displayMessage(messages.getDrawBoxMessage(), messages.getDrawButtonMessage());
                break;
            } default:
                break;
        }
    }
}
