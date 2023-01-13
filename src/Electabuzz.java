public class Electabuzz extends  ElectricityPokemon implements Evolve{
    private final Attack THUNDER;
    private final Attack THUNDER_PUNCH;

    public Electabuzz() {
        super(NAME_ELECTABUZZ, DEFAULT_HP_ELECTABUZZ, DEFAULT_ATTACK_POINT_ELECTABUZZ);
        this.THUNDER = new Attack(THUNDER_NAME, THUNDER_MIN_DAMAGE, THUNDER_MAX_DAMAGE, THUNDER_COST, THUNDER_RANK_REQUIREMENT);
        this.THUNDER_PUNCH = new Attack(THUNDER_PUNCH_NAME, THUNDER_PUNCH_MIN_DAMAGE, THUNDER_PUNCH_MAX_DAMAGE, THUNDER_PUNCH__COST, THUNDER_PUNCH_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }
    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.THUNDER, this.THUNDER_PUNCH};
    }
    protected int getMaxRank() {
        return this.MAX_RANK_ELECTABUZZ;
    }
    public boolean evolve() {
        boolean upgradeSuccessfully ;
            upgradeSuccessfully = super.evolvePokemon(NAME_ELECTIVIRE, DEFAULT_HP_ELECTIVIRE, DEFAULT_ATTACK_POINT_ELECTIVIRE);
        return upgradeSuccessfully;
    }
}
