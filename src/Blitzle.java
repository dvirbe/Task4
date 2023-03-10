public class Blitzle extends ElectricityPokemon implements Evolve {
    private final Attack FLOP_ATTACK;
    private final Attack ZAP_KICK;
    //O(1)
    public Blitzle() {
        super(NAME_BLITZLE, MAX_HP_BLITZLE, MAX_ATTACK_POINT_BLITZLE);
        this.FLOP_ATTACK = new Attack(FLOP_ATTACK_NAME, FLOP_MIN_DAMAGE, FLOP_MAX_DAMAGE, FLOP_COST, FLOP_RANK_REQUIREMENT);
        this.ZAP_KICK = new Attack(ZAP_KICK_NAME, ZAP_KICK_MIN_DAMAGE, ZAP_KICK_MAX_DAMAGE, ZAP_KICK__COST, ZAP_KICK_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }
    //O(1)
    public boolean evolve() {
        boolean upgradeSuccessfully;
        upgradeSuccessfully = super.evolvePokemon(NAME_ZEBSTRIKA, MAX_HP_ZEBSTRIKA, MAX_ATTACK_POINT_ZEBSTRIKA);
        return upgradeSuccessfully;
    }
    //O(1)
    protected int getMaxRank() {
        return this.MAX_RANK_BLITZLE;
    }
    //O(1)
    @Override
    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.FLOP_ATTACK, this.ZAP_KICK};
    }
}
