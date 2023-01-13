public class Moltres extends FirePokemon {
    private final Attack ASSISTING_HEATER;
    private final Attack FIRE_WING;

    public Moltres() {
        super(NAME_MOLTRES, DEFAULT_HP_MOLTRES, DEFAULT_ATTACK_POINT_MOLTRES);
        this.ASSISTING_HEATER = new Attack(ASSISITING_HEATER_NAME, ASSISITING_HEATER_MIN_DAMAGE, ASSISITING_HEATER_MAX_DAMAGE, ASSISITING_HEATER_COST, ASSISITING_HEATER_RANK_REQUIREMENT);
        this.FIRE_WING = new Attack(FIRE_WING_NAME, FIRE_WING_DAMAGE, FIRE_WING__COST, FIRE_WING_RANK_REQUIREMENT);
        setAttackList(setAttacks());
    }

    protected Attack[] setAttacks() {
        return new Attack[]{getKickAttack(), this.ASSISTING_HEATER, this.FIRE_WING};
    }

    protected int getMaxRank() {
        return MAX_RANK_MOLTRES;
    }
}