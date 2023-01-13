public class Charmander extends FirePokemon implements Evolve {
    private final Attack scratch;
    private final Attack flameTail;
    private final Attack fieryBlast;

    public Charmander() {
        super(NAME_CHARMANDER, DEFAULT_HP_CHARMANDER, DEFAULT_ATTACK_POINT_CHARMANDER);
        this.scratch = new Attack(SCRATCH_ATTACK_NAME, SCRATCH_ATTACK_MIN_DAMAGE, SCRATCH_ATTACK_MAX_DAMAGE, SCRATCH_ATTACK_COST, SCRATCH_ATTACK_RANK_REQUIREMENT);
        this.flameTail = new Attack(FLAME_TAIL_ATTACK_NAME, FLAME_TAIL_MIN_DAMAGE, FLAME_TAIL_MAX_DAMAGE, FLAME_TAIL_COST, FLAME_TAIL_RANK_REQUIREMENT);
        this.fieryBlast = new Attack(FIERY_BLAST_ATTACK_NAME, FIERY_BLAST_DAMAGE, FIERY_BLAST_COST, FIERY_BLAST_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }

    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.scratch, this.flameTail, this.fieryBlast};
    }

    public boolean evolve() {
        boolean upgradeSuccessfully;
        if (getRank() == DEFAULT_RANK) {
            upgradeSuccessfully = super.evolvePokemon(NAME_CHARMELEON, DEFAULT_HP_CHARMELEON, DEFAULT_ATTACK_POINT_CHARMELEON);
        } else {
            upgradeSuccessfully = super.evolvePokemon(NAME_CHARIZRAD, DEFAULT_HP_CHARIZRAD, DEFAULT_ATTACK_POINT_CHARIZRAD);
        }
        return upgradeSuccessfully;
    }

    @Override
    protected int getMaxRank() {
        return MAX_RANK_CHARMENDER;
    }
}
