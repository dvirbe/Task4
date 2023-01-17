public class Charmander extends FirePokemon implements Evolve {
    private final Attack SCRATCH;
    private final Attack FLAME_TAIL;
    private final Attack FIERY_BLAST;
    //O(1)
    public Charmander() {
        super(NAME_CHARMANDER, MAX_HP_CHARMANDER, MAX_ATTACK_POINT_CHARMANDER);
        this.SCRATCH = new Attack(SCRATCH_ATTACK_NAME, SCRATCH_ATTACK_MIN_DAMAGE, SCRATCH_ATTACK_MAX_DAMAGE, SCRATCH_ATTACK_COST, SCRATCH_ATTACK_RANK_REQUIREMENT);
        this.FLAME_TAIL = new Attack(FLAME_TAIL_ATTACK_NAME, FLAME_TAIL_MIN_DAMAGE, FLAME_TAIL_MAX_DAMAGE, FLAME_TAIL_COST, FLAME_TAIL_RANK_REQUIREMENT);
        this.FIERY_BLAST = new Attack(FIERY_BLAST_ATTACK_NAME, FIERY_BLAST_DAMAGE, FIERY_BLAST_COST, FIERY_BLAST_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }
    //O(1)
    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.SCRATCH, this.FLAME_TAIL, this.FIERY_BLAST};
    }
    //O(1)
    public boolean evolve() {
        boolean upgradeSuccessfully;
        if (getRank() == DEFAULT_RANK) {
            upgradeSuccessfully = super.evolvePokemon(NAME_CHARMELEON, MAX_HP_CHARMELEON, MAX_ATTACK_POINT_CHARMELEON);
        } else {
            upgradeSuccessfully = super.evolvePokemon(NAME_CHARIZRAD, MAX_HP_CHARIZRAD, MAX_ATTACK_POINT_CHARIZRAD);
        }
        return upgradeSuccessfully;
    }
    //O(1)
    @Override
    protected int getMaxRank() {
        return this.MAX_RANK_CHARMENDER;
    }
}
