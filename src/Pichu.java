public class Pichu extends ElectricityPokemon implements Evolve {
    private final Attack QUICK_ATTACK;
    private final Attack ELECTRO_BALL;
    private final Attack ELECTRIC_SURFER;

    public Pichu() {
        super(NAME_PICHU, MAX_HP_PICHU, MAX_ATTACK_POINT_PICHU);
        this.QUICK_ATTACK = new Attack(QUICK_ATTACK_NAME, QUICK_ATTACK_DAMAGE, QUICK_ATTACK_COST , QUICK_ATTACK_RANK_REQUIREMENT);
        this.ELECTRO_BALL = new Attack(ELECTRO_BALL_NAME, ELECTRO_BALL_MIN_DAMAGE,ELECTRO_BALL_MAX_DAMAGE,ELECTRO_BALL__COST ,ELECTRO_BALL_RANK_REQUIREMENT );
        this.ELECTRIC_SURFER = new Attack(ELECTRIC_SURFER_NAME,ELECTRIC_SURFER_MIN_DAMAGE ,ELECTRIC_SURFER_MAX_DAMAGE ,ELECTRIC_SURFER_COST,ELECTRIC_SURFER_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }
    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.QUICK_ATTACK, this.ELECTRO_BALL, this.ELECTRIC_SURFER};
    }
    public boolean evolve() {
        boolean upgradeSuccessfully;
        if (getRank() == DEFAULT_RANK) {
            upgradeSuccessfully = super.evolvePokemon(NAME_PIKACHU, MAX_HP_PIKACHU, MAX_ATTACK_POINT_PIKACHU);
        } else {
            upgradeSuccessfully = super.evolvePokemon(NAME_RAICHU, MAX_HP_RAICHU, MAX_ATTACK_POINT_RAICHU);
        }
        return upgradeSuccessfully;
    }
    public int getMaxRank() {
        return this.MAX_RANK_PICHU;
    }

}
