package tictactoe.model;

/**
 * Responds to <code>Controller</code>.
 */
public class Model {
    private Board board;
    private CPU cpu;

    /**
     * Initializes a new <code>Model</code>, <code>Board</code> and <code>CPU</code> as opponent player.
     */
    public Model() {
        board = new Board();;
        cpu = new CPU(board);
    }

    /**
     * If HUMAN won, increase search depth of CPU. If CPU won, decrease it.
     * Calls <code>Board</code> to set up a new game.
     * If it is CPUs turn, call <code>CPU</code> to calculate move.
     */
    public void newGame() {
        if (board.getBoardState() == BoardState.HUMANWON)
            cpu.depthInc();
        else if (board.getBoardState() == BoardState.CPUWON)
            cpu.depthDec();

        board.setupNewGame();

        if (!board.isHumansTurn())
            move(cpu.getMove());
    }

    /**
     * Returns the state of the board.
     *
     * @return state of the board
     */
    public BoardState getBoardState() {
        return board.getBoardState();
    }

    /**
     * Returns the depth of CPU search.
     *
     * @return CPU search depth
     */
    public int getCPUDepth() {
        return cpu.getDepth();
    }

    /**
     * Executes a move. If game is not over, call for move of next player.
     *
     * @param i
     */
    private void move(int i) {
        if (board.isHumansTurn())
            board.setToHuman(i);
        else
            board.setToCPU(i);

        if (!board.hasEnded())
            nextPlayer();
    }

    /**
     * If it is not HUMANs turn, call CPU for move.
     */
    private void nextPlayer() {
        if (!board.isHumansTurn())
            move(cpu.getMove());
    }

    /**
     * If game is over, start new game.
     */
    public void check() {
        if (board.hasEnded())
            newGame();
    }

    /**
     * If game has ended, start new game, else try to execute move.
     *
     * @param i move to execute
     */
    public void check(int i) {
        if (board.hasEnded())
            newGame();
        else if (board.isEmpty(i))
            move(i);
    }

    /**
     * Returns the owner of a space of the board.
     *
     * @param s space to get the owner of
     * @return owner of space
     */
    public Owner getOwner(int s) {
        return board.getOwner(s);
    }
}
