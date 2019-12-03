// Copyright 2019 Nedelcu Horia (nedelcu.horia.alexandru@gmail.com)

package gameplay;

public final class Wizard extends Player {
    private static final int BASE_HP = 400;
    private static final int LEVEL_HP = 30;

    private static final float DESE_BONUS = 1.1f;

    private static final int DMG_BASE1 = 0;
    private static final int DMG_LEVEL1 = 0;

    private static final float P1_PYRO = 0.9f;
    private static final float P1_KNIG = 1.2f;
    private static final float P1_WIZZ = 1.05f;
    private static final float P1_ROGU = 0.8f;

    private static final int DMG_BASE2 = 0;
    private static final int DMG_LEVEL2 = 0;
    private static final int PER_DMG_BASE = 0;
    private static final int PER_DMG_LEVEL = 0;

    private static final float P2_PYRO = 1.3f;
    private static final float P2_KNIG = 1.4f;
    private static final float P2_WIZZ = 0f;
    private static final float P2_ROGU = 1.2f;

    private static final float P_DRAIN = 0.3f;
    private static final float P_BASE_HP = 0.2f;
    private static final float P_LEVEL_HP = 0.05f;

    private static final float P_BASE_DMG = 0.35f;
    private static final float P_LEVEL_DMG = 0.02f;
    private static final float P_MAXLIM_DMG = 0.7f;

    Wizard(final int x, final int y, final String type) {
        super(LEVEL_HP, BASE_HP, x, y, type);
    }

    private void doWizardAbility(final Player player,
            final float procent1, final float procent2) {
        float dmgDrain, dmgDeflect;
        int damage;

        dmgDrain = (P_BASE_HP + P_LEVEL_HP * mLevel) * (float) Math.min(P_DRAIN
                * player.getFullLife(), player.mHitPoints);
        if (mLocation == 'D') { // Desert Bonus
            dmgDrain = DESE_BONUS * dmgDrain;
        }
        dmgDrain = Math.round(procent1 * dmgDrain);

        dmgDeflect = (float) Math.min((P_BASE_DMG + P_LEVEL_DMG * mLevel), P_MAXLIM_DMG)
            * player.getDamage();
        if (mLocation == 'D') { // Desert Bonus
            dmgDeflect = DESE_BONUS * dmgDeflect;
        }
        dmgDeflect = Math.round(procent2 * dmgDeflect);

        damage = (int) dmgDrain + (int) dmgDeflect;
        player.mHitPoints = Math.max(0, player.mHitPoints - damage);

        player.mAllowedToMove = true;
        player.mPeriodicDamage = Math.round(procent2 * getPeriodicDamage());
        player.mPeriod = 0;
    }

    public void acceptAbilityFrom(final Player player) {
        player.applyAbilityTo(this);
    }

    protected void applyAbilityTo(final Pyromancer player) {
        doWizardAbility(player, P1_PYRO, P2_PYRO);
    }

    protected void applyAbilityTo(final Knight player) {
        doWizardAbility(player, P1_KNIG, P2_KNIG);
    }

    protected void applyAbilityTo(final Wizard player) {
        doWizardAbility(player, P1_WIZZ, P2_WIZZ);
    }

    protected void applyAbilityTo(final Rogue player) {
        doWizardAbility(player, P1_ROGU, P2_ROGU);
    }

    protected float getDamage1() {
        float damage = DMG_BASE1 + DMG_LEVEL1 * mLevel;
        if (mLocation == 'L') { // Desert Bonus
            damage = DESE_BONUS * damage;
        }

        return damage;
    }

    protected float getDamage2() {
        float damage = DMG_BASE2 + DMG_LEVEL2 * mLevel;
        if (mLocation == 'L') { // Desert Bonus
            damage = DESE_BONUS * damage;
        }

        return damage;
    }

    protected float getPeriodicDamage() {
        float damage = PER_DMG_BASE + PER_DMG_LEVEL * mLevel;
        if (mLocation == 'L') { // Desert Bonus
            damage = DESE_BONUS * damage;
        }

        return damage;
    }
}
