package tictactoe.model;

/**
 * Responsible for the CPU moves.
 * Search depth can be set.
 */

public class CPU {
    private int depth;
    private Board board;
    private Evaluate evaluate;

    /**
     * Initializes a new <code>CPU</code>.
     * Creates a new <code>Evaluate</code>, which is used for evaluation.
     * Search depth starts at 0.
     *
     * @param board the state of the game to be used
     */
    public CPU(Board board) {
        evaluate = new Evaluate();
        depth = 0;
        this.board = board;
    }

    /**
     * Increases the depth of the search.
     */
    public void depthInc() {
        depth++;
    }

    /**
     * Decreases the depth of the search.
     */
    public void depthDec() {
        if (depth > 0)
            depth--;
    }

    /**
     * Returns the depth of the search.
     *
     * @return the depth of the search
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Is called to create a CPU move.
     * If depth is 0, returns a random move. Otherwise depth determines the result of the calculation.
     *
     * @return the move of the CPU
     */
    public int getMove() {
        int move;

        if (depth == 0)
            move = randomMove();
        else
            move = findMove(board);

        return move;
    }

    /**
     * Creates a random move.
     *
     * @return random move
     */
    private int randomMove() {
        int move;

        do {
            move = (int) (Math.random() * 9);
        } while (!board.isEmpty(move));

        return move;
    }

    /**
     * Entry point of the search, determines best move by score. Starts the recursive search.
     *
     * @param board the board to be used
     * @return best move found, -1 if no move was found
     */
    private int findMove(Board board) {
        int bestMove = -1;
        int bestScore = board.isHumansTurn() ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;

        for (int i = 0; i < 9; i++) {
            if (board.isEmpty(i)) {
                Board newBoard = board.getCopy();
                if (newBoard.isHumansTurn())
                    newBoard.setToHuman(i);
                else
                    newBoard.setToCPU(i);
                evaluate.checkGameState(newBoard);
                currentScore = findMove(newBoard, depth);
                if (board.isHumansTurn() && currentScore > bestScore
                        || currentScore == bestScore && ((int) (Math.random() * 2)) == 0
                        || !board.isHumansTurn() && currentScore < bestScore) {
                    bestScore = currentScore;
                    bestMove = i;
                }
            }
        }
        
        return bestMove;
    }

    /**
     * Recursive move search.
     *
     * @param board the used board
     * @param depth the used depth, gets decreased after each step until it is 0
     * @return the score of the best move
     */
    private int findMove(Board board, int depth) {
        if (board.hasEnded())
            if (board.isHumansTurn())
                return depth;
            else
                return -depth;

        if (depth == 0)
            return 0;

        int bestScore = board.isHumansTurn() ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;

        for (int i = 0; i < 9; i++) {
            if (board.isEmpty(i)) {
                Board newBoard = board.getCopy();
                if (newBoard.isHumansTurn())
                    newBoard.setToHuman(i);
                else
                    newBoard.setToCPU(i);
                evaluate.checkGameState(newBoard);
                currentScore = findMove(newBoard, depth - 1);
                if (board.isHumansTurn() && currentScore > bestScore
                        || !board.isHumansTurn() && currentScore < bestScore) {
                    bestScore = currentScore;
                }
            }
        }

        return bestScore;
    }
}
