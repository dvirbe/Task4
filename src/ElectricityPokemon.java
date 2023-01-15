public abstract class ElectricityPokemon extends Pokemon {

    private double currentElectricity;

    public ElectricityPokemon(String name, double maxHp, double maxAttackPoints) {
        super(name, maxHp, maxAttackPoints);
        this.currentElectricity = BASE_ELECTRICITY;
    }

    private double getCurrentElectricity() {
        return currentElectricity;
    }

    protected void passiveAbility() {
        this.currentElectricity += ELECTRICITY_PER_TURN;
        if (lowHealth()) {
            this.currentElectricity = BASE_ELECTRICITY;
        }
    }

    private boolean lowHealth() {
        return getCurrentHP() < getMaxHp() * LOW_HEALTH;
    }

    public boolean specialAttack() {
        if (canSpecialAttack()) {
            addToSpecialAttack();
            addCurrentHP(getMaxHp() - getCurrentHP());
            addCurrentAttackPoint(getMaxAttackPoints() - getCurrentAttackPoint());
            return true;
        } else {
            return false;
        }
    }

    public double passiveAbilityElectricity(double damage) {
        if (lowHealth()){
            return damage;
        }else {
            return getCurrentElectricity() * damage;
        }
    }
}
