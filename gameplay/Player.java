// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package gameplay;

import java.lang.Math;

public abstract class Player {
    protected final String mType;
    protected final int mLevelHitPoints;
    protected final int mBaseHitPoints;
    protected int mHitPoints;

    protected int mExperiencePoints;
    protected int mLevel;

    public int mXCoordinate;
    public int mYCoordinate;
    public char mLocation;

    protected boolean mAllowedToMove;
    public int mPeriodicDamage;
    protected int mPeriod;

    protected int mNumHit;

    public Player() {
        this(0, 0, 0, 0, null);
    }

    public Player(final int levelHitPoints, final int baseHitPoints, final int x, final int y, final String type) {
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
    public String toString() {
        if (isAlive()) {
            //System.out.printf("%s %d %d %d %d %d%n", mType, mLevel, mExperiencePoints,
            //    mHitPoints, mXCoordinate, mYCoordinate);
            return mType + " " + String.valueOf(mLevel) + " " +
                                 String.valueOf(mExperiencePoints) + " " +
                                 String.valueOf(mHitPoints) + " " +
                                 String.valueOf(mXCoordinate) + " " +
                                 String.valueOf(mYCoordinate) + "\n";
        } else {
            //System.out.printf("%s dead%n", mType);
            return mType + " dead\n";
        }
    }

    public void incrementHits() {
        mNumHit++;
    }

    public boolean isAlive() {
        return mHitPoints != 0;
    }

    protected int getFullLife() {
        return mBaseHitPoints + mLevelHitPoints * mLevel;
    } 

    public void getExperiencePoints(final Player player) {
        if (!player.isAlive()) {
            mExperiencePoints += Math.max(0, 200 - (mLevel - player.mLevel) * 40);

            int level = 0;
            while (mExperiencePoints >= 250 + level * 50) {
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

    public boolean sameLocation(final Player player) {
        return mXCoordinate == player.mXCoordinate && mYCoordinate == player.mYCoordinate;
    }

    public void applyPeriodicEffectAndMove(final char move) {
        if (mAllowedToMove == true && mHitPoints != 0) {
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
                    throw new IllegalArgumentException("Move " + String.valueOf(move) +
                        " is not recognized.");
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

    abstract public void acceptAbilityFrom(final Player player);

    abstract protected void applyAbilityTo(final Pyromancer player);
    abstract protected void applyAbilityTo(final Knight player);
    abstract protected void applyAbilityTo(final Wizard player);
    abstract protected void applyAbilityTo(final Rogue player);

    protected int getDamage() {
        return Math.round(getDamage1()) + Math.round(getDamage2());
    }

    abstract protected float getDamage1();
    abstract protected float getDamage2();
    abstract protected float getPeriodicDamage();
}

final class Pyromancer extends Player {
    public Pyromancer(final int x, final int y, final String type) {
        super(50, 500, x, y, type);
    }

    public void acceptAbilityFrom(final Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(final Pyromancer player) {
        //System.out.println("Pyromancer apply ability to Pyromancer");
        int damage = Math.round(0.9f * getDamage1()); 
        damage += Math.round(0.9f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(0.9f * getPeriodicDamage());
        player.mPeriod = 2;
    }

    protected void applyAbilityTo(final Knight player) {
        //System.out.println("Pyromancer apply ability to Knight");
        int damage = Math.round(1.2f * getDamage1());
        damage += Math.round(1.2f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(1.2f * getPeriodicDamage());
        player.mPeriod = 2;
    }

    protected void applyAbilityTo(final Wizard player) {
        //System.out.println("Pyromancer apply ability to Wizard");
        int damage = Math.round(1.05f * getDamage1());
        damage += Math.round(1.05f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(0.9f * getPeriodicDamage());
        player.mPeriod = 2;
    }

    protected void applyAbilityTo(final Rogue player) {
        //System.out.println("Pyromancer apply ability to Rogue");
        int damage = Math.round(0.8f * getDamage1());
        damage += Math.round(0.8f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);


        //System.out.println("Pyroh " + damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(0.8f * getPeriodicDamage());
        player.mPeriod = 2;
    }

    protected float getDamage1() {
        float damage = 350 + 50 * mLevel;
        if (mLocation == 'V') { // Volcanic Bonus
            damage = 1.25f * damage;
        }

        return damage;
    }

    protected float getDamage2() {
        float damage = 150 + 20 * mLevel;
        if (mLocation == 'V') { // Volcanic Bonus
            damage = 1.25f * damage;
        }

        return damage; 
    }

    protected float getPeriodicDamage() {
        float damage = 50 + 30 * mLevel;
        if (mLocation == 'V') { // Volcanic Bonus
            damage = 1.25f * damage;
        }

        return damage; 
    }
}

final class Knight extends Player {
    public Knight(final int x, final int y, final String type) {
        super(80, 900, x, y, type);
    }

    public void acceptAbilityFrom(final Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(final Pyromancer player) {
        //System.out.println("Knight apply ability to Pyromancer");
        if (player.mHitPoints < Math.round(Math.min(0.2f + 0.01f * mLevel, 0.4f) * player.getFullLife())) {
            player.mHitPoints = 0;
            return;
        }

        int damage = Math.round(1.1f * getDamage1());
        damage += Math.round(0.9f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = false;
        player.mPeriodicDamage = Math.round(0.9f * getPeriodicDamage());
        player.mPeriod = 1;
    }

    protected void applyAbilityTo(final Knight player) {
        //System.out.println("Knight apply ability to Knight");
        if (player.mHitPoints < Math.round(Math.min(0.2f + 0.01f * mLevel, 0.4f) * player.getFullLife())) {
            player.mHitPoints = 0;
            return;
        }

        int damage = Math.round(1f * getDamage1());
        damage += Math.round(1.2f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = false;
        player.mPeriodicDamage = Math.round(1.2f * getPeriodicDamage());
        player.mPeriod = 1;
    }

    protected void applyAbilityTo(final Wizard player) {
        //System.out.println("Knight apply ability to Wizard");
        if (player.mHitPoints < Math.round(Math.min(0.2f + 0.01f * mLevel, 0.4f) * player.getFullLife())) {
            player.mHitPoints = 0;
            return;
        }

        int damage = Math.round(0.8f * getDamage1());
        damage += Math.round(1.05f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = false;
        player.mPeriodicDamage = Math.round(1.05f * getPeriodicDamage());
        player.mPeriod = 1;
    }

    protected void applyAbilityTo(final Rogue player) {
        //System.out.println("Knight apply ability to Rogue");
        if (player.mHitPoints < Math.round(Math.min(0.2f + 0.01f * mLevel, 0.4f) * player.getFullLife())) {
            player.mHitPoints = 0;
            return;
        }

        int damage = Math.round(1.15f * getDamage1());
        damage += Math.round(0.8f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = false;
        player.mPeriodicDamage = Math.round(0.8f * getPeriodicDamage());
        player.mPeriod = 1;
    }

    protected float getDamage1() {
        float damage = 200 + 30 * mLevel;
        if (mLocation == 'L') { // Land Bonus
            damage = 1.15f * damage;
        }

        return damage;
    }

    protected float getDamage2() {
        float damage = 100 + 40 * mLevel;
        if (mLocation == 'L') { // Land Bonus
            damage = Math.round(1.15f * damage);
        }

        return damage;
    }

    protected float getPeriodicDamage() {
        return 0;
    } 
}

final class Wizard extends Player {
    public Wizard(final int x, final int y, final String type) {
        super(30, 400, x, y, type);
    }

    public void acceptAbilityFrom(final Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(final Pyromancer player) {
        //System.out.println("Wizard apply ability to Pyromancer");
        float dmg_drain, dmg_deflect;
        int damage;

        dmg_drain = (0.2f + 0.05f * mLevel) * (float) Math.min(0.3f * player.getFullLife(), player.mHitPoints);
        if (mLocation == 'D') { // Desert Bonus
            dmg_drain = 1.1f * dmg_drain;
        }
        dmg_drain = Math.round(0.9f * dmg_drain);

        dmg_deflect = (float) Math.min((0.35f + 0.02f * mLevel), 0.7) * player.getDamage();
        if (mLocation == 'D') { // Desert Bonus
            dmg_deflect = 1.1f * dmg_deflect;
        }
        dmg_deflect = Math.round(1.3f * dmg_deflect);

        damage = (int) dmg_drain + (int) dmg_deflect;
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(1.3f * getPeriodicDamage());
        player.mPeriod = 0;
    }

    protected void applyAbilityTo(final Knight player) {
        //System.out.println("Wizard apply ability to Knight");
        float dmg_drain, dmg_deflect;
        int damage;

        dmg_drain = (0.2f + 0.05f * mLevel) * (float) Math.min(0.3f * player.getFullLife(), player.mHitPoints);
        if (mLocation == 'D') { // Desert Bonus
            dmg_drain = 1.1f * dmg_drain;
        }
        dmg_drain = Math.round(1.2f * dmg_drain);

        dmg_deflect = (float) Math.min((0.35f + 0.02f * mLevel), 0.7) * player.getDamage();
        if (mLocation == 'D') { // Desert Bonus
            dmg_deflect = 1.1f * dmg_deflect;
        }
        dmg_deflect = Math.round(1.4f * dmg_deflect);

        damage = (int) dmg_drain + (int) dmg_deflect;
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(1.4f * getPeriodicDamage());
        player.mPeriod = 0;
    }

    protected void applyAbilityTo(final Wizard player) {
        //System.out.println("Wizard apply ability to Wizard");
        float dmg_drain, dmg_deflect;
        int damage;

        dmg_drain = (0.2f + 0.05f * mLevel) * (float) Math.min(0.3f * player.getFullLife(), player.mHitPoints);
        if (mLocation == 'D') { // Desert Bonus
            dmg_drain = 1.1f * dmg_drain;
        }
        dmg_drain = Math.round(1.05f * dmg_drain);

        dmg_deflect = (float) Math.min((0.35f + 0.02f * mLevel), 0.7) * player.getDamage();
        if (mLocation == 'D') { // Desert Bonus
            dmg_deflect = 1.1f * dmg_deflect;
        }
        dmg_deflect = Math.round(0f * dmg_deflect);

        damage = (int) dmg_drain + (int) dmg_deflect;
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(0f * getPeriodicDamage());
        player.mPeriod = 0;
    }

    protected void applyAbilityTo(final Rogue player) {
        //System.out.println("Wizard apply ability to Rogue");
        float dmg_drain, dmg_deflect;
        int damage;

        dmg_drain = (0.2f + 0.05f * mLevel) * (float) Math.min(0.3f * player.getFullLife(), player.mHitPoints);
        if (mLocation == 'D') { // Desert Bonus
            dmg_drain = 1.1f * dmg_drain;
        }
        dmg_drain = Math.round(0.8f * dmg_drain);

        dmg_deflect = (float) Math.min((0.35f + 0.02f * mLevel), 0.7) * player.getDamage();
        if (mLocation == 'D') { // Desert Bonus
            dmg_deflect = 1.1f * dmg_deflect;
        }
        dmg_deflect = Math.round(1.2f * dmg_deflect);

        damage = (int) dmg_drain + (int) dmg_deflect;
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(1.2f * getPeriodicDamage());
        player.mPeriod = 0;
    }

    protected float getDamage1() {
        return 0;
    }

    protected float getDamage2() {
        return 0;
    }

    protected float getPeriodicDamage() {
        return 0;
    }
}

final class Rogue extends Player {
    public Rogue(final int x, int y, final String type) {
        super(40, 600, x, y, type);
    }

    public void acceptAbilityFrom(final Player player) {
        player.applyAbilityTo(this);
        //mHitPoints++;
    }

    protected void applyAbilityTo(final Pyromancer player) {
        //System.out.println("Rogue apply ability to Pyromancer");
        int damage = Math.round(1.25f * getDamage1());
        damage += Math.round(1.2f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = false;
        player.mPeriodicDamage = Math.round(1.2f * getPeriodicDamage());
        if (mLocation == 'W') {
            player.mPeriod = 6;
        } else {
            player.mPeriod = 3;
        }
    }

    protected void applyAbilityTo(final Knight player) {
        //System.out.println("Rogue apply ability to Knight");
        int damage = Math.round(0.9f * getDamage1());
        damage += Math.round(0.8f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = false;
        player.mPeriodicDamage = Math.round(0.8f * getPeriodicDamage());
        if (mLocation == 'W') {
            player.mPeriod = 6;
        } else {
            player.mPeriod = 3;
        }
    }

    protected void applyAbilityTo(final Wizard player) {
        //System.out.println("Rogue apply ability to Wizard");
        int damage = Math.round(1.25f * getDamage1());
        damage += Math.round(1.25f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = false;
        player.mPeriodicDamage = Math.round(1.25f * getPeriodicDamage());
        if (mLocation == 'W') {
            player.mPeriod = 6;
        } else {
            player.mPeriod = 3;
        }
    }

    protected void applyAbilityTo(final Rogue player) {
        //System.out.println("Rogue apply ability to Rogue");
        int damage = Math.round(1.2f * getDamage1());
        damage += Math.round(0.9f * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = false;
        player.mPeriodicDamage = Math.round(0.9f * getPeriodicDamage());
        if (mLocation == 'W') {
            player.mPeriod = 6;
        } else {
            player.mPeriod = 3;
        }
    }

    protected float getDamage1() {
        float damage = 200 + 20 * mLevel;
        if (mLocation == 'W' && (mNumHit - 1) % 3 == 0) { // Backstab Bonus
            damage = 1.5f * damage;
        }

        if (mLocation == 'W') { // Woods Bonus
            damage = 1.15f * damage;
        }

        return damage;
    }

    protected float getDamage2() {
        float damage = 40 + 10 * mLevel;
        if (mLocation == 'W') { // Woods Bonus
            damage = 1.15f * damage;
        }

        return damage;
    }

    protected float getPeriodicDamage() {
        float damage = 40 + 10 * mLevel;
        if (mLocation == 'W') { // Woods Bonus
            damage = Math.round(1.15f * damage);
        }

        return damage; 
    }
}
