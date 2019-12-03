// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package gameplay;

public final class Pyromancer extends Player {
    private static final int BASE_HP = 500;
    private static final int LEVEL_HP = 50;

    private static final float VOLC_BONUS = 1.25f;

    private static final int DMG_BASE1 = 350;
    private static final int DMG_LEVEL1 = 50;

    private static final float P1_PYRO = 0.9f;
    private static final float P1_KNIG = 1.2f;
    private static final float P1_WIZZ = 1.05f;
    private static final float P1_ROGU = 0.8f;

    private static final int DMG_BASE2 = 150;
    private static final int DMG_LEVEL2 = 20;
    private static final int PER_DMG_BASE = 50;
    private static final int PER_DMG_LEVEL = 30;

    private static final float P2_PYRO = 0.9f;
    private static final float P2_KNIG = 1.2f;
    private static final float P2_WIZZ = 1.05f;
    private static final float P2_ROGU = 0.8f;

    Pyromancer(final int x, final int y, final String type) {
        super(LEVEL_HP, BASE_HP, x, y, type);
    }

    private void doPyromancerAbility(final Player player,
            final float procent1, final float procent2) {
        int damage = Math.round(procent1 * getDamage1());
        damage += Math.round(procent2 * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(procent2 * getPeriodicDamage());
        player.mPeriod = 2;
    }

    public void acceptAbilityFrom(final Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(final Pyromancer player) {
        doPyromancerAbility(player, P1_PYRO, P2_PYRO);
    }

    protected void applyAbilityTo(final Knight player) {
        doPyromancerAbility(player, P1_KNIG, P2_KNIG);
    }

    protected void applyAbilityTo(final Wizard player) {
        doPyromancerAbility(player, P1_WIZZ, P2_WIZZ);
    }

    protected void applyAbilityTo(final Rogue player) {
        doPyromancerAbility(player, P1_ROGU, P2_ROGU);
    }

    protected float getDamage1() {
        float damage = DMG_BASE1 + DMG_LEVEL1 * mLevel;
        if (mLocation == 'V') { // Volcanic Bonus
            damage = VOLC_BONUS * damage;
        }

        return damage;
    }

    protected float getDamage2() {
        float damage = DMG_BASE2 + DMG_LEVEL2 * mLevel;
        if (mLocation == 'V') { // Volcanic Bonus
            damage = VOLC_BONUS * damage;
        }

        return damage;
    }

    protected float getPeriodicDamage() {
        float damage = PER_DMG_BASE + PER_DMG_LEVEL * mLevel;
        if (mLocation == 'V') { // Volcanic Bonus
            damage = VOLC_BONUS * damage;
        }

        return damage;
    }
}
