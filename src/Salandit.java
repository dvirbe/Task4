public class Salandit extends FirePokemon implements Evolve {
    private final Attack LIVE_COAL;
    private final Attack FIRE_CLAWS;

    public Salandit() {
        super(NAME_SALANDIT, DEFAULT_HP_SALANDIT, DEFAULT_ATTACK_POINT_SALANDIT);
        this.LIVE_COAL = new Attack(LIVE_COAL_NAME, LIVE_COAL_MIN_DAMAGE, LIVE_COAL_MAX_DAMAGE, LIVE_COAL_COST, LIVE_COAL_RANK_REQUIREMENT);
        this.FIRE_CLAWS = new Attack(FIRE_CLAWS_NAME, FIRE_CLAWS_MIN_DAMAGE, FIRE_CLAWS_MAX_DAMAGE, FIRE_CLAWS_COST, FIRE_CLAW_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }

    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.LIVE_COAL, this.FIRE_CLAWS};
    }

    protected int getMaxRank() {
        return this.MAX_RANK_SALANDIT;
    }

    public boolean evolve() {
        boolean upgradeSuccessfully;
        upgradeSuccessfully = super.evolvePokemon(NAME_SALAZZLE, DEFAULT_HP_SALAZZLE, DEFAULT_ATTACK_POINT_SALAZZLE);
        return upgradeSuccessfully;
    }
}
