public class Blitzle extends ElectricityPokemon implements Evolve {
    private final Attack FLOP_ATTACK;
    private final Attack ZAP_KICK;

    public Blitzle() {
        super(NAME_BLITZLE, DEFAULT_HP_BLITZLE, DEFAULT_ATTACK_POINT_BLITZLE);
        this.FLOP_ATTACK = new Attack(FLOP_ATTACK_NAME, FLOP_MIN_DAMAGE, FLOP_MAX_DAMAGE, FLOP_COST, FLOP_RANK_REQUIREMENT);
        this.ZAP_KICK = new Attack(ZAP_KICK_NAME, ZAP_KICK_MIN_DAMAGE, ZAP_KICK_MAX_DAMAGE, ZAP_KICK__COST, ZAP_KICK_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }

    public boolean evolve() {
        boolean upgradeSuccessfully;
        upgradeSuccessfully = super.evolvePokemon(NAME_ZEBSTRIKA, DEFAULT_HP_ZEBSTRIKA, DEFAULT_ATTACK_POINT_ZEBSTRIKA);
        return upgradeSuccessfully;
    }

    protected int getMaxRank() {
        return this.MAX_RANK_BLITZLE;
    }

    @Override
    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.FLOP_ATTACK, this.ZAP_KICK};
    }
}
