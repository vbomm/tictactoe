package tictactoe.model;


/**
 * Contains the state of the game/board.
 */
public class Board {
    private byte turn;
    private boolean isHumansTurn;
    private Space[] space;
    private boolean hasEnded;
    private Evaluate evaluate;
    private BoardState boardState;

    /**
     * Initializes a new <code>Board</code>. Initializes a new <code>Evaluate</code> to evaluate the state of the game.
     * Creates an array to represent state of the game.
     */
    public Board() {
        boardState = BoardState.JUSTCREATED;
        evaluate = new Evaluate();
        space = new Space[9];
        for (int i = 0; i < 9; i++)
            space[i] = new Space();
    }

    /**
     * Initializes a new <code>Board</code>. Used to create a new instance already existing <code>Board</code>.
     *
     * @param turn         number of moves made
     * @param isHumansTurn yes if it is the players turn, no if CPUs
     * @param space        array that holds the state of the game
     * @param hasEnded     true of the game is over
     */
    private Board(byte turn, boolean isHumansTurn, Space[] space, boolean hasEnded) { //copy
        evaluate = new Evaluate();
        this.turn = turn;
        this.isHumansTurn = isHumansTurn;
        this.space = space;
        this.hasEnded = hasEnded;
    }

    /**
     * Sets up a new game. Losing player from last game will begin, in case of a draw beginner from last game is swapped.
     * Board spaces are reset.
     */
    public void setupNewGame() {
        if (boardState == BoardState.JUSTCREATED || boardState == BoardState.CPUWON)
            isHumansTurn = true;
        else if (boardState == BoardState.HUMANWON)
            isHumansTurn = false;
        //in case of a  draw it will be automatically a swap of the beginning player

        boardState = BoardState.UNFINISHED;
        hasEnded = false;
        turn = 0;

        for (int i = 0; i < 9; i++) {
            space[i].reset();
        }
    }

    /**
     * Sets the state of the board.
     *
     * @param state the state to be set
     */
    public void setBoardState(BoardState state) {
        boardState = state;
    }

    /**
     * Returns the state of the board.
     *
     * @return state of the board
     */
    public BoardState getBoardState() {
        return boardState;
    }

    /**
     * Gets the number of the turn.
     *
     * @return number of the turn
     */
    public byte getTurn() {
        return turn;
    }

    /**
     * Returns the <code>Owner</code> of a space.
     *
     * @param s the space
     * @return the owner of the space
     */
    public Owner getOwner(int s) {
        return space[s].getOwner();
    }

    /**
     * Returns if it is the turn of the player/human.
     *
     * @return yes if it is the players turn, else no
     */
    public boolean isHumansTurn() {
        return isHumansTurn;
    }


    /**
     * Sets the player/human as the owner of a space.
     *
     * @param s the space to be set
     */
    public void setToHuman(int s) {
        space[s].setToHuman();
        newTurn();
    }

    /**
     * Sets the CPU as the owner of a space.
     *
     * @param s the space to be set
     */
    public void setToCPU(int s) {
        if (s ==  -1)
            return;
        space[s].setToCPU();
        newTurn();
    }

    /**
     * If game is not over, increases turn and switches to next player.
     */
    private void newTurn() {
        if (!hasEnded()) {
            turn++;
            isHumansTurn = !isHumansTurn;
        }
    }

    /**
     * Returns of a space is empty/free.
     *
     * @param s the space
     * @return true if space is empty/free
     */
    public boolean isEmpty(int s) {
        return space[s].isEmpty();
    }

    /**
     * Checks three parameters for at least one free space.
     *
     * @param a one of the parameters to be checker for equality
     * @param b one of the parameters to be checker for equality
     * @param c one of the parameters to be checker for equality
     * @return true of at least one space is free
     */
    public boolean atLeastOneFreeSpace(int a, int b, int c) {
        return isEmpty(a) || isEmpty(b) || isEmpty(c);
    }

    /**
     * Checks if two parameters are equal.
     *
     * @param a one of the parameters to be checked for equality
     * @param b one of the parameters to be checked for equality
     * @return true if they are equal
     */
    public boolean areSameType(int a, int b) {
        return space[a].getOwner() == space[b].getOwner();
    }

    /**
     * Checks if three parameters are the same.
     *
     * @param a one of the parameters to be checked for equality
     * @param b one of the parameters to be checked for equality
     * @param c one of the parameters to be checked for equality
     * @return true if all three parameters are equal
     */
    public boolean areSameType(int a, int b, int c) {
        return areSameType(a, b) && areSameType(a, c);
    }

    /**
     * Sets the board state to "has ended".
     */
    public void end() {
        hasEnded = true;
    }

    /**
     * Calls method to evaluate state of the game, then returns boolean representation of the game has ended or not.
     *
     * @return yrd if game has ended
     */
    public boolean hasEnded() {
        evaluate.checkGameState(this);
        return hasEnded;
    }

    /**
     * Returns a copy of this board.
     *
     * @return copy of this board
     */
    public Board getCopy() {
        Space[] spaceCopy = new Space[9];
        for (int s = 0; s < 9; s++)
            spaceCopy[s] = new Space(space[s].getOwner());

        return new Board(turn, isHumansTurn, spaceCopy, hasEnded);
    }


    /**
     * Prints the board to the console. Used in the early stages of development.
     */
    public void print() {
        for (int i = 0; i < 9; i++) {
            if (space[i].getOwner() == Owner.HUMAN)
                System.out.print("X");
            else if (space[i].getOwner() == Owner.CPU)
                System.out.print("O");
            else
                System.out.print("#");

            if ((i + 1) % 3 == 0)
                System.out.println();
        }
        System.out.println("");
    }
}
