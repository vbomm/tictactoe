package tictactoe.model;

/**
 * A space on the game board.
 */
public class Space {
    private Owner owner;

    /**
     * Initializes a new space.
     * Calls method to set <code>Owner</code> to NONE.
     */
    public Space() {
        reset();
    }

    /**
     * Sets owner.
     *
     * @param owner the new owner
     */
    public Space(Owner owner) {
        this.owner = owner;
    }

    /**
     * Returns yes if empty.
     *
     * @return returns yes if <code>Owner</code> is NONE
     */
    public boolean isEmpty() {
        return owner == Owner.NONE;
    }

    /**
     * Returns <code>Owner</code>.
     *
     * @return current <code>Owner</code>
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Sets <code>Owner</code> to NONE.
     */
    public void reset() {
        owner = Owner.NONE;
    }

    /**
     * Sets <code>Owner</code> to HUMAN
     */
    public void setToHuman() {
        owner = Owner.HUMAN;
    }

    /**
     * Sets <code>Owner</code> to CPU
     */
    public void setToCPU() {
        owner = Owner.CPU;
    }
}