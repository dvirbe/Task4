import java.util.Random;

public class Attack implements GameStats {
    private final String ATTACK_NAME;
    private final int MAX_DAMAGE;
    private final int MIN_DAMAGE;
    private final int RANK_REQUIRED;
    private final int ATTACK_POINT_COST;
//O(1)
    public Attack(String attackName, int damage, int attackPointCost, int rankRequired) {
        this.ATTACK_NAME = attackName;
        this.MAX_DAMAGE = damage;
        this.MIN_DAMAGE = damage;
        this.RANK_REQUIRED = rankRequired;
        this.ATTACK_POINT_COST = attackPointCost;
    }
    //O(1)
    public Attack(String attackName, int minDamage, int maxDamage, int attackPointCost, int rankRequired) {
        this.ATTACK_NAME = attackName;
        this.MAX_DAMAGE = maxDamage;
        this.MIN_DAMAGE = minDamage;
        this.RANK_REQUIRED = rankRequired;
        this.ATTACK_POINT_COST = attackPointCost;
    }
    //O(1)
    public int getRankRequired() {
        return RANK_REQUIRED;
    }
    //O(1)
    private double getDamage() {
        Random random = new Random();
        if (MIN_DAMAGE == MAX_DAMAGE) {
            return MAX_DAMAGE;
        } else {
            return random.nextInt(MAX_DAMAGE - MIN_DAMAGE) + MIN_DAMAGE;
        }
    }
    //O(1)
    private String getDamageToString() {
        if (MIN_DAMAGE == MAX_DAMAGE) {
            return MIN_DAMAGE + "";
        } else
            return MIN_DAMAGE + "-" + (MAX_DAMAGE - 1);
    }
    //O(1)
    public int getAttackPointCost() {
        return ATTACK_POINT_COST;
    }
    //O(1)
    public String getAttackName() {
        return ATTACK_NAME;
    }
    //O(1)
    public double calculateDamage(Pokemon pokemon) {
        double damage = getDamage();
        if (pokemon instanceof ElectricityPokemon) {
            damage = ((ElectricityPokemon) pokemon).passiveAbilityElectricity(getDamage());
        }
        if (pokemon.isCriticalAttack()) {
            damage *= CRITICAL_HIT_MULTIPLY;
        }
       damage = (double) Math.round(damage * 100) / 100;
        return damage;
    }
    //O(1)
    @Override
    public String toString() {
        return "" + this.ATTACK_NAME + "\n(" +
                "Damage: " + this.getDamageToString() + "\tAttack point cost: " +
                this.ATTACK_POINT_COST + "\trank required: " + this.RANK_REQUIRED + ")";

    }
}
