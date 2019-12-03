// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package gameplay;

public abstract class Player {
    private static final int NEXT_XP = 200;
    private static final int STEP_XP = 40;
    private static final int BASE_XP = 250;
    private static final int LEVEL_XP = 50;

    protected final String mType;
    protected final int mLevelHitPoints;
    protected final int mBaseHitPoints;
    protected int mHitPoints;

    protected int mExperiencePoints;
    protected int mLevel;

    protected int mXCoordinate;
    protected int mYCoordinate;
    protected char mLocation;

    protected boolean mAllowedToMove;
    protected int mPeriodicDamage;
    protected int mPeriod;

    protected int mNumHit;

    public Player() {
        this(0, 0, 0, 0, null);
    }

    public Player(final int levelHitPoints, final int baseHitPoints,
                  final int x, final int y, final String type) {
        mType = type;

        mLevelHitPoints = levelHitPoints;
        mBaseHitPoints = baseHitPoints;
        mHitPoints = baseHitPoints;

        mExperiencePoints = 0;
        mLevel = 0;

        mXCoordinate = x;
        mYCoordinate = y;
        mLocation = Map.getInstance().getLocation(x, y);

        mAllowedToMove = true;
        mPeriodicDamage = 0;
        mPeriod = 0;

        mNumHit = 1;
    }

    @Override
    public final String toString() {
        if (isAlive()) {
            //    mHitPoints, mXCoordinate, mYCoordinate);
            return mType + " " + String.valueOf(mLevel) + " "
                    + String.valueOf(mExperiencePoints) + " "
                    + String.valueOf(mHitPoints) + " "
                    + String.valueOf(mXCoordinate) + " "
                    + String.valueOf(mYCoordinate) + "\n";
        } else {
            return mType + " dead\n";
        }
    }

    /*
     * Increment number of hits.
     */
    public final void incrementHits() {
        mNumHit++;
    }

    /*
     * Check if player is still alive.
     */
    public final boolean isAlive() {
        return mHitPoints != 0;
    }

    /*
     * Recover player life.
     */
    protected final int getFullLife() {
        return mBaseHitPoints + mLevelHitPoints * mLevel;
    }

    /*
     * Increase experience if player who battled is dead.
     */
    public final void getExperiencePoints(final Player player) {
        if (!player.isAlive()) {
            mExperiencePoints += Math.max(0, NEXT_XP - (mLevel - player.mLevel) * STEP_XP);

            int level = 0;
            while (mExperiencePoints >= BASE_XP + level * LEVEL_XP) {
                ++level;
            }

            if (mLevel < level) {
                mLevel = level;
                if (isAlive()) {
                    mHitPoints = getFullLife();
                }
            }
        }
    }

    public final boolean sameLocation(final Player player) {
        return mXCoordinate == player.mXCoordinate && mYCoordinate == player.mYCoordinate;
    }

    /*
     * Move the player if it is not stunned and apply periodic damage.
     */
    public final void applyPeriodicEffectAndMove(final char move) {
        if (mAllowedToMove && mHitPoints != 0) {
            switch (move) {
                case 'U': // MOVE UP
                    mXCoordinate--;
                    mLocation = Map.getInstance().getLocation(mXCoordinate, mYCoordinate);
                    break;
                case 'D': // MOVE DOWN
                    mXCoordinate++;
                    mLocation = Map.getInstance().getLocation(mXCoordinate, mYCoordinate);
                    break;
                case 'L': // MOVE LEFT
                    mYCoordinate--;
                    mLocation = Map.getInstance().getLocation(mXCoordinate, mYCoordinate);
                    break;
                case 'R': // MOVE RIGHT
                    mYCoordinate++;
                    mLocation = Map.getInstance().getLocation(mXCoordinate, mYCoordinate);
                    break;
                case '_': // NO MOVE
                    break;
                default:
                    throw new IllegalArgumentException("Move " + String.valueOf(move)
                            + " is not recognized.");
            }
        }

        // still have damage effect to apply and player is not dead
        if (mPeriod != 0 && mHitPoints != 0) {
            mHitPoints = Math.max(0, mHitPoints - mPeriodicDamage);
            mPeriod--;

            if (mPeriod == 0) {
                mAllowedToMove = true;
                mPeriodicDamage = 0;
            }
        }
    }

    /*
     * this accept ability from other player.
     */
    public abstract void acceptAbilityFrom(Player player);

    abstract void applyAbilityTo(Pyromancer player);
    abstract void applyAbilityTo(Knight player);
    abstract void applyAbilityTo(Wizard player);
    abstract void applyAbilityTo(Rogue player);

    /*
     * Get normal damage (not applyed to a type).
     */
    protected final int getDamage() {
        return Math.round(getDamage1()) + Math.round(getDamage2());
    }

    /*
     * Damage first ability.
     */
    abstract float getDamage1();

    /*
     * Damage second ability.
     */
    abstract float getDamage2();

    /*
     * Damage periodic ability.
     */
    abstract float getPeriodicDamage();
}
