public class Salandit extends FirePokemon implements Evolve {
    private final Attack LIVE_COAL;
    private final Attack FIRE_CLAWS;
    //O(1)
    public Salandit() {
        super(NAME_SALANDIT, MAX_HP_SALANDIT, MAX_ATTACK_POINT_SALANDIT);
        this.LIVE_COAL = new Attack(LIVE_COAL_NAME, LIVE_COAL_MIN_DAMAGE, LIVE_COAL_MAX_DAMAGE, LIVE_COAL_COST, LIVE_COAL_RANK_REQUIREMENT);
        this.FIRE_CLAWS = new Attack(FIRE_CLAWS_NAME, FIRE_CLAWS_MIN_DAMAGE, FIRE_CLAWS_MAX_DAMAGE, FIRE_CLAWS_COST, FIRE_CLAW_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }
    //O(1)
    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.LIVE_COAL, this.FIRE_CLAWS};
    }
    //O(1)
    protected int getMaxRank() {
        return this.MAX_RANK_SALANDIT;
    }
    //O(1)
    public boolean evolve() {
        boolean upgradeSuccessfully;
        upgradeSuccessfully = super.evolvePokemon(NAME_SALAZZLE, MAX_HP_SALAZZLE, MAX_ATTACK_POINT_SALAZZLE);
        return upgradeSuccessfully;
    }
}
