package tictactoe.model;

/**
 * Evaluates the state of the game and determines if there is a winner or a draw.
 */
public class Evaluate {
    private Board board;

    /**
     * Entrance point for the board status check.
     *
     * @param board the game to be analyzed
     */
    public  void checkGameState(Board board) {
        this.board = board;
        if (checkDiagonal() || checkHorizontal() || checkVertical()) {
            System.out.println("won");
            if (board.isHumansTurn())
                board.setBoardState(BoardState.HUMANWON);
            else
                board.setBoardState(BoardState.CPUWON);
            board.end();
        } else if (board.getTurn() == 9) {
            board.setBoardState(BoardState.DRAW);
            board.end();
        }
    }

    /**
     * Checks the diagonals.
     *
     * @return true if game is won
     */
    private boolean checkDiagonal() {
        return areTheSame(0, 4, 8) || areTheSame(2, 4, 6);
    }

    /**
     * Checks the horizontals.
     *
     * @return true if won
     */
    private boolean checkHorizontal() {
        for (int i = 0; i < 3; i++)
            if (areTheSame(i * 3, i * 3 + 1, i * 3 + 2))
                return true;

        return false;
    }

    /**
     * Checks the verticals.
     *
     * @return true if won
     */
    private boolean checkVertical() {
        for (int i = 0; i < 3; i++)
            if (areTheSame(i, i + 3, i + 6))
                return true;

        return false;
    }

    /**
     * If no free space included, calls method in <code>Board</code> if the three parameters are the same.
     *
     * @param a one of the three values to be checked for equality
     * @param b one of the three values to be checked for equality
     * @param c one of the three values to be checked for equality
     * @return true if parameters are the same
     */
    private boolean areTheSame(int a, int b, int c) {
        if (board.atLeastOneFreeSpace(a, b, c))
            return false;

        return board.areSameType(a, b, c);
    }
}
