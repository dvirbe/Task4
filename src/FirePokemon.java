import java.util.Random;

public abstract class FirePokemon extends Pokemon {
    public FirePokemon(String name, double hp, double attackPoints) {
        super(name, hp, attackPoints);
    }

    @Override
    protected void passiveAbility() {
        Random random = new Random();
        if (random.nextInt(0, 4) == PASSIVE_ABILITY_FIRE) {
            int passiveAbilityDamage = random.nextInt(3, 11);
            lowerCurrentHP(passiveAbilityDamage);
            System.out.println(getName() + " hurt himself and lost " + passiveAbilityDamage + " HP");
        }
    }

    public boolean specialAttack() {
        Random random = new Random();
        if (canSpecialAttack()) {
            addToSpecialAttack();
            int count = 0;
            boolean attackSuccessfully;
            while (count < 2) {

                this.addCurrentAttackPoint(getMaxAttackPoints());
                int index;
                do {
                    index = random.nextInt(0, getAttackList().length);
                } while (!canAttack(getAttackList()[index].getRankRequired(), getAttackList()[index].getAttackPointCost()));
                attackSuccessfully = performAttacking(Battle.getDefensePokemon(), getAttackList()[index]);
                if (attackSuccessfully) {
                    count++;
                }
            }
            double healthCost = getCurrentHP() / 2;
            lowerCurrentHP(healthCost);
            lowerCurrentAttackPoint(this.getCurrentAttackPoint());


            return true;
        } else {
            return false;
        }
    }
}
