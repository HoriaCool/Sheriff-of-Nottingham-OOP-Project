// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package gameplay;

final class Rogue extends Player {
    private static final int BASE_HP = 600;
    private static final int LEVEL_HP = 40;

    private static final float WOOD_BONUS = 1.15f;

    private static final int DMG_BASE1 = 200;
    private static final int DMG_LEVEL1 = 20;

    private static final float P1_PYRO = 1.25f;
    private static final float P1_KNIG = 0.9f;
    private static final float P1_WIZZ = 1.25f;
    private static final float P1_ROGU = 1.2f;

    private static final int DMG_BASE2 = 40;
    private static final int DMG_LEVEL2 = 10;
    private static final int PER_DMG_BASE = 40;
    private static final int PER_DMG_LEVEL = 10;

    private static final float P2_PYRO = 1.2f;
    private static final float P2_KNIG = 0.8f;
    private static final float P2_WIZZ = 1.25f;
    private static final float P2_ROGU = 0.9f;

    private static final int PER_TMP1 = 3;
    private static final int PER_TMP2 = 6;
    private static final int PER_BACKSTAB = 3;
    private static final float P_BACKSTAB_BONUS = 1.5f;

    Rogue(final int x, final int y, final String type) {
        super(LEVEL_HP, BASE_HP, x, y, type);
    }

    private void doRogueAbility(final Player player,
            final float procent1, final float procent2) {
        int damage = Math.round(procent1 * getDamage1());
        damage += Math.round(procent2 * getDamage2());
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = false;
        player.mPeriodicDamage = Math.round(procent2 * getPeriodicDamage());
        if (mLocation == 'W') {
            player.mPeriod = PER_TMP2;
        } else {
            player.mPeriod = PER_TMP1;
        }
    }

    public void acceptAbilityFrom(final Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(final Pyromancer player) {
        doRogueAbility(player, P1_PYRO, P2_PYRO);
    }

    protected void applyAbilityTo(final Knight player) {
        doRogueAbility(player, P1_KNIG, P2_KNIG);
    }

    protected void applyAbilityTo(final Wizard player) {
        doRogueAbility(player, P1_WIZZ, P2_WIZZ);
    }

    protected void applyAbilityTo(final Rogue player) {
        doRogueAbility(player, P1_ROGU, P2_ROGU);
    }

    protected float getDamage1() {
        float damage = DMG_BASE1 + DMG_LEVEL1 * mLevel;
        if (mLocation == 'W' && (mNumHit - 1) % PER_BACKSTAB == 0) { // Backstab Bonus
            damage = P_BACKSTAB_BONUS * damage;
        }

        if (mLocation == 'W') { // Woods Bonus
            damage = WOOD_BONUS * damage;
        }

        return damage;
    }

    protected float getDamage2() {
        float damage = DMG_BASE2 + DMG_LEVEL2 * mLevel;
        if (mLocation == 'W') { // Woods Bonus
            damage = WOOD_BONUS * damage;
        }

        return damage;
    }

    protected float getPeriodicDamage() {
        float damage = PER_DMG_BASE + PER_DMG_LEVEL * mLevel;
        if (mLocation == 'W') { // Woods Bonus
            damage = WOOD_BONUS * damage;
        }

        return damage;
    }
}
