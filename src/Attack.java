import java.util.Random;
public class Attack implements GameStats {
    private final String attackName;
    private final int maxDamage;
    private final int minDamage;
    private final int rankRequired;
    private final int attackPointCost;

    public Attack(String attackName, int damage, int attackPointCost, int rankRequired) {
        this.attackName = attackName;
        this.maxDamage = damage;
        this.minDamage = damage;
        this.rankRequired = rankRequired;
        this.attackPointCost = attackPointCost;
    }
    public Attack(String attackName, int minDamage, int maxDamage, int attackPointCost, int rankRequired) {
        this.attackName = attackName;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
        this.rankRequired = rankRequired;
        this.attackPointCost = attackPointCost;
    }
    public int getRankRequired() {
        return rankRequired;
    }
    public double getDamage() {
        Random random = new Random();
        if (minDamage==maxDamage){
            return maxDamage;
        }else {
            return random.nextInt(maxDamage-minDamage)+minDamage;
        }
    }
    private String getDamageToString() {
        if (minDamage==maxDamage){
            return  minDamage+"";
        }else
            return minDamage +"-"+(maxDamage-1);
    }
    public int getAttackPointCost() {
        return attackPointCost;
    }
    public String getAttackName() {
        return attackName;
    }

    public double calculateDamage(Pokemon pokemon){
        double damage=getDamage();
        if (pokemon instanceof ElectricityPokemon){
            damage=  ((ElectricityPokemon) pokemon).passiveAbilityElectricity(getDamage());
        }
        if (pokemon.isCriticalAttack()){
            damage*=CRITICAL_HIT_MULTIPLER;
        }
        return damage;
    }

    @Override
    public String toString() {
        return "" + this.attackName + "\n(" +
                "Damage: " + this.getDamageToString() + "\tAttack point cost: "+
                this.attackPointCost+ "\trank required: "+ this.rankRequired + ")";

    }
}
