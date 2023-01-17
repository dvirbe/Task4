public class Electabuzz extends  ElectricityPokemon implements Evolve{
    private final Attack THUNDER;
    private final Attack THUNDER_PUNCH;
    //O(1)
    public Electabuzz() {
        super(NAME_ELECTABUZZ, MAX_HP_ELECTABUZZ, MAX_ATTACK_POINT_ELECTABUZZ);
        this.THUNDER = new Attack(THUNDER_NAME, THUNDER_MIN_DAMAGE, THUNDER_MAX_DAMAGE, THUNDER_COST, THUNDER_RANK_REQUIREMENT);
        this.THUNDER_PUNCH = new Attack(THUNDER_PUNCH_NAME, THUNDER_PUNCH_MIN_DAMAGE, THUNDER_PUNCH_MAX_DAMAGE, THUNDER_PUNCH__COST, THUNDER_PUNCH_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }
    //O(1)
    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.THUNDER, this.THUNDER_PUNCH};
    }
    //O(1)
    protected int getMaxRank() {
        return this.MAX_RANK_ELECTABUZZ;
    }
    //O(1)
    public boolean evolve() {
        boolean upgradeSuccessfully ;
            upgradeSuccessfully = super.evolvePokemon(NAME_ELECTIVIRE, MAX_HP_ELECTIVIRE, MAX_ATTACK_POINT_ELECTIVIRE);
        return upgradeSuccessfully;
    }
}
