// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package gameplay;

public abstract class Player {
    private final int mBoostHitPoints = 0;
    private final int mBaseHitPoints = 0;
    private int mHitPoints;
    private int mExperiencePoints;
    private int mLevel;
    private int mXCoordinate;
    private int mYCoordinate;
    private char mLocation;

    public void acceptsAbilityFrom(Player player) {
        player.applyFirstAbilityTo(this);
    }

    protected abstract void applyFirstAbilityTo(Player player);

    public abstract double getPrice();
}

final class Pyromancer extends Player {
    @Override
    protected void applyFirstAbilityTo(Player player) {
    }

    private void applyFirstAbilityTo(Pyromancer player) {

    }

    private void applyFirstAbilityTo(Knight player) {

    }

    private void applyFirstAbilityTo(Wizard player) {

    }

    private void applyFirstAbilityTo(Rogue player) {

    }


    public double getPrice() {
        System.out.println("Pyromancer");
        return 8.5;
    }
}


final class Knight extends Player {
    @Override
    protected void applyFirstAbilityTo(Player player) {
    }

    private void applyFirstAbilityTo(Knight player) {

    }

    private void applyFirstAbilityTo(Wizard player) {

    }

    private void applyFirstAbilityTo(Rogue player) {

    }


    public double getPrice() {
        System.out.println("Knight");
        return 10.5;
    }
}

/*
final class Wizard extends Player {
    private void applyFirstAbilityTo(Pyromancer player) {

    }

    private void applyFirstAbilityTo(Knight player) {

    }

    private void applyFirstAbilityTo(Wizard player) {

    }

    private void applyFirstAbilityTo(Rogue player) {

    }
    

    public double getPrice() {
        System.out.println("Wizard");
        return 11.5;
    }
}

final class Rogue extends Player {
    private void applyFirstAbilityTo(Pyromancer player) {

    }

    private void applyFirstAbilityTo(Knight player) {

    }

    private void applyFirstAbilityTo(Wizard player) {

    }

    private void applyFirstAbilityTo(Rogue player) {

    }


    public double getPrice() {
        System.out.println("Rogue");
        return 20.5;
    }
}
*/
