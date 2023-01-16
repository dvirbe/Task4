import java.util.Random;

public abstract class FirePokemon extends Pokemon {
    public FirePokemon(String name, double hp, double attackPoints) {
        super(name, hp, attackPoints);
    }

    @Override
    protected void passiveAbility() {
        Random random = new Random();
        if (random.nextInt(PASSIVE_ABILITY_FIRE, PASSIVE_ABILITY_FIRE_MAX) == PASSIVE_ABILITY_FIRE) {
            int passiveAbilityDamage = random.nextInt(PASSIVE_ABILITY_FIRE_MIN_DAMAGE, PASSIVE_ABILITY_FIRE_MAX_DAMAGE);
            lowerCurrentHP(passiveAbilityDamage);
            System.out.println(getName() + " hurt himself and lost " + passiveAbilityDamage + " HP");
        }
    }

    public boolean specialAttack() {
        Random random = new Random();
            int count = 0;

            while (count < SPECIAL_ABILITY_REPEAT_ATTACK) {
                this.addCurrentAttackPoint(getMaxAttackPoints());
                int index;
                do {
                    index = random.nextInt(RANDOM_ATTACK_BASE, getAttackList().length);
                } while (!canAttack(getAttackList()[index].getRankRequired(), getAttackList()[index].getAttackPointCost()));
               performAttacking(Battle.getDefensePokemon(), getAttackList()[index]);
                    count++;
            }
            double healthCost = getCurrentHP() / SPECIAL_ABILITY_HEALTH_COST;
            lowerCurrentHP(healthCost);
            lowerCurrentAttackPoint(this.getCurrentAttackPoint());
            return true;
        }
    }

