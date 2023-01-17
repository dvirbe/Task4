public abstract class ElectricityPokemon extends Pokemon {

    private double currentElectricity;
    //O(1)
    public ElectricityPokemon(String name, double maxHp, double maxAttackPoints) {
        super(name, maxHp, maxAttackPoints);
        this.currentElectricity = BASE_ELECTRICITY;
    }
    //O(1)
    private double getCurrentElectricity() {
        return currentElectricity;
    }
    //O(1)
    protected void passiveAbility() {
        this.currentElectricity += ELECTRICITY_PER_TURN;
        if (lowHealth()) {
            this.currentElectricity = BASE_ELECTRICITY;
        }
    }
    //O(1)
    private boolean lowHealth() {
        return getCurrentHP() < getMaxHp() * LOW_HEALTH;
    }
    //O(1)
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
    //O(1)
    public double passiveAbilityElectricity(double damage) {
        if (lowHealth()){
            return damage;
        }else {
            return getCurrentElectricity() * damage;
        }
    }
}
