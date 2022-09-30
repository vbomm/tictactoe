package tictactoe.controller;

import tictactoe.view.Language;

public class Messages {

    private Language language;
    private String humanWon;
    private String humanWonBoxMessage;
    private String humanWonButtonMessage;
    private String CPUWon;
    private String CPUWonBoxMessage;
    private String CPUWonButtonMessage;
    private String draw;
    private String drawBoxMessage;
    private String drawButtonMessage;
    private String inGame;

    public Messages() {
        this(Language.ENGLISH);
    }

    public Messages(Language language) {
        switch (language) {
            case ENGLISH:
            default:
                humanWon = "You won! Click to continue!";
                humanWonBoxMessage = "You won! Search depth of the Computer will be increased.";
                humanWonButtonMessage = "Ok";
                CPUWon = "You lost! Click to continue!";
                CPUWonBoxMessage = "You lost! Search depth wii be decreased, lowest is 0.";
                CPUWonButtonMessage = "Ok";
                draw = "Draw! Click to continue!";
                drawBoxMessage = "A draw! Beginner will be swapped.";
                drawButtonMessage = "Ok";
                inGame = "Search-Depth: ";
                break;
        }
    }

    public String humanWon() {
        return humanWon;
    }

    public String getHumanWonBoxMessage() { return humanWonBoxMessage; }

    public String getHumanWonButtonMessage() { return humanWonButtonMessage; }
    public String cpuWon() {
        return CPUWon;
    }

    public String getCPUWonBoxMessage() { return CPUWonBoxMessage; }

    public String getCPUWonButtonMessage() { return CPUWonButtonMessage; }

    public String draw() {
        return draw;
    }

    public String getDrawBoxMessage() { return drawBoxMessage; }

    public String getDrawButtonMessage() { return drawButtonMessage; }

    public String inGame() {
        return inGame;
    }
}
