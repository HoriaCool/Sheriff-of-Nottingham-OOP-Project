// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package gameplay;

import java.lang.Math;
import java.lang.Math;

public abstract class Player {
    protected final int mLevelHitPoints = 0;
    protected final int mBaseHitPoints = 0;
    protected int mHitPoints;

    protected int mExperiencePoints;
    protected int mLevel;

    protected int mXCoordinate;
    protected int mYCoordinate;
    protected char mLocation;

    protected int mRound;

    protected boolean mAllowedToMove;
    protected int mPeriodicDamage;
    protected int mPeriod;

    public int getHitPoints() {
        return mHitPoints;
    }

    public void applyPeriodicEffect() {
        // still have effect to apply and player is not dead
        if (mPeriod != 0 && mHitPoints != 0) {
            mHitPoints = Math.max(0, mHitPoints - mPeriodicDamage);
            mPeriod--;
        }
    }
    
    abstract public void acceptAbilityFrom(Player player);

    abstract protected void applyAbilityTo(Pyromancer player);
    abstract protected void applyAbilityTo(Knight player);
    abstract protected void applyAbilityTo(Wizard player);
    abstract protected void applyAbilityTo(Rogue player);

    abstract public double getPrice();
    abstract public int getDamage();
}

final class Pyromancer extends Player {
    public void acceptAbilityFrom(Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(Pyromancer player) {
        System.out.println("Pyromancer apply ability to Pyromancer");
    }

    protected void applyAbilityTo(Knight player) {
        System.out.println("Pyromancer apply ability to Knight");
    }

    protected void applyAbilityTo(Wizard player) {
        System.out.println("Pyromancer apply ability to Wizard");
    }

    protected void applyAbilityTo(Rogue player) {
        System.out.println("Pyromancer apply ability to Rogue");
    }

    public double getPrice() {
        System.out.println("Pyromancer");
        return 8.5;
    }

    public int getDamage() {
        int damage = 350 + 50 * mLevel + 150 + 20 * mLevel;
        if (mLocation == 'V') { // Volcanic Bonus
            damage = Math.round(1.25f * damage);
        }

        return damage; 
    }
}

final class Knight extends Player {
    public void acceptAbilityFrom(Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(Pyromancer player) {
        System.out.println("Knight apply ability to Pyromancer");
    }

    protected void applyAbilityTo(Knight player) {
        System.out.println("Knight apply ability to Knight");
    }

    protected void applyAbilityTo(Wizard player) {
        System.out.println("Knight apply ability to Wizard");
    }

    protected void applyAbilityTo(Rogue player) {
        System.out.println("Knight apply ability to Rogue");
    }


    public double getPrice() {
        System.out.println("Knight");
        return 10.5;
    }

    public int getDamage() {
        int damage = 200 + 30 * mLevel + 100 + 40 * mLevel;
        if (mLocation == 'L') { // Land Bonus
            damage = Math.round(1.15f * damage);
        }

        return damage;
    }
}

final class Wizard extends Player {
    public void acceptAbilityFrom(Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(Pyromancer player) {
        System.out.println("Wizard apply ability to Pyromancer");
    }

    protected void applyAbilityTo(Knight player) {
        System.out.println("Wizard apply ability to Knight");
    }

    protected void applyAbilityTo(Wizard player) {
        System.out.println("Wizard apply ability to Wizard");
    }

    protected void applyAbilityTo(Rogue player) {
        System.out.println("Wizard apply ability to Rogue");
    }
    
    public double getPrice() {
        System.out.println("Wizard");
        return 11.5;
    }

    public int getDamage() {
        return 0;
    }
}

final class Rogue extends Player {
    public void acceptAbilityFrom(Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(Pyromancer player) {
        System.out.println("Rogue apply ability to Pyromancer");
    }

    protected void applyAbilityTo(Knight player) {
        System.out.println("Rogue apply ability to Knight");
    }

    protected void applyAbilityTo(Wizard player) {
        System.out.println("Rogue apply ability to Wizard");
    }

    protected void applyAbilityTo(Rogue player) {
        System.out.println("Rogue apply ability to Rogue");
    }

    public double getPrice() {
        System.out.println("Rogue");
        return 20.5;
    }

    public int getDamage() {
        int damage = 200 + 20 * mLevel;
        if (mLocation == 'W' && mRound % 3 == 0) { // Backstab Bonus
            damage = Math.round(1.5f * damage);
        }

        damage += 40 + 10 * mLevel;
        if (mLocation == 'W') { // Woods Bonus
            damage = Math.round(1.15f * damage);
        }

        return damage;
    }
}
