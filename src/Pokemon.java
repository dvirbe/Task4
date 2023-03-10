import java.util.Random;

public abstract class Pokemon implements GameStats, PokemonStats {
    private String name;
    private double maxHp;
    private double currentHP;
    private double maxAttackPoints;
    private double currentAttackPoint;
    private int rank;
    private int specialAttackCount;
    private boolean criticalAttack;
    private final Attack KICK_ATTACK;
    private Attack[] attackList;
    //O(1)
    public Pokemon(String name, double maxHp, double maxAttackPoints) {
        this.name = name;
        this.maxHp = maxHp;
        this.currentHP = maxHp;
        this.maxAttackPoints = maxAttackPoints;
        this.currentAttackPoint = this.maxAttackPoints * ATTACK_POINT_MULTIPLY;
        this.specialAttackCount = SPECIAL_ATTACK_DEFAULT;
        this.rank = DEFAULT_RANK;
        this.criticalAttack = CRITICAL_ATTACK_DEFAULT;
        this.KICK_ATTACK = new Attack(KICK_ATTACK_NAME, KICK_ATTACK_DAMAGE, KICK_ATTACK_COST, KICK_ATTACK_RANK_REQUIREMENT);
    }
    //O(1)
    protected void setAttackList(Attack[] attackList) {
        this.attackList = attackList;
    }
    //O(1)
    private void changeName(String newName) {
        this.name = newName;
    }
    //O(1)
    private void changeMaxHp(double newMaxHp) {
        this.maxHp = newMaxHp;
    }
    //O(1)
    private void changeMaxAttackPoint(double newMaxAttackPoint) {
        this.maxAttackPoints = newMaxAttackPoint;
    }
    //O(1)
    protected boolean enoughAttackPoint(double attackPointCost) {
        return (this.currentAttackPoint + attackPointCost) >= 0;
    }

    protected abstract boolean specialAttack();

    protected abstract void passiveAbility();

    protected abstract int getMaxRank();
    //O(1)
    public double getMaxHp() {
        return this.maxHp;
    }
    //O(1)
    public double getCurrentHP() {
        return this.currentHP;
    }
    //O(1)
    public double getMaxAttackPoints() {
        return this.maxAttackPoints;
    }
    //O(1)
    public String getName() {
        return this.name;
    }
    //O(1)
    public int getSpecialAttackCount() {
        return this.specialAttackCount;
    }
    //O(1)
    protected void addToSpecialAttack() {
        this.specialAttackCount++;
    }
    //O(1)
    public double getCurrentAttackPoint() {
        return this.currentAttackPoint;
    }
    //O(1)
    public int getRank() {
        return this.rank;
    }
    //O(1)
    private void setRank() {
        if (canUpgrade(getMaxRank())) {
            this.rank++;
        }
    }
    //O(1)
    private boolean canUpgrade(int maxRank) {
        boolean canUpgrade = false;
        if (this.rank == maxRank) {
            System.out.println("max level reached");
            return false;
        }
        if (this.rank < maxRank) {
            if (this.rank == DEFAULT_RANK) {
                if ((getCurrentHP() > UPGRADE_HP_COST_LVL_TWO) && (getCurrentAttackPoint() >= UPGRADE_ATTACK_POINT_COST_LVL_TWO)) {
                    canUpgrade = true;
                } else {
                    System.out.println("your pokemon is weak , you need more HP or Attack Points");
                }
            } else if (this.rank == RANK_TWO) {
                if ((getCurrentHP() > UPGRADE_HP_COST_LVL_THREE) && (getCurrentAttackPoint() >= UPGRADE_ATTACK_POINT_COST_LVL_THREE)) {
                    canUpgrade = true;
                } else {
                    System.out.println("your pokemon is weak , you need more HP or Attack Points");
                }
            }
        }
        return canUpgrade;
    }
    //O(1)
    protected void rest() {
        Random random = new Random();
        int bonus;
        int waitRandom = random.nextInt(HEAL_CASE, CRITICAL_CASE + 1);
        switch (waitRandom) {
            case HEAL_CASE -> {
                bonus = random.nextInt(MIN_WAIT_HEAL, MAX_WAIT_HEAL);
                addCurrentHP(bonus);
                System.out.println("you healed " + bonus + " HP");
            }
            case ATTACK_POINT_CASE -> {
                bonus = random.nextInt(MIN_WAIT_DAMAGE, MAX_WAIT_DAMAGE);
                addCurrentAttackPoint(bonus);
                System.out.println("you got" + bonus + " bonus Attack point ");
            }
            case CRITICAL_CASE -> {
                System.out.println("next attack will be critical attack");
                this.criticalAttack = CRITICAL_ATTACK;
            }
        }
    }

    protected abstract Attack[] setAttacks();
    //O(1)
    protected void changeStats(String name, int hpCost, int attackCost, double newHp, double newAttackPoint) {
        lowerCurrentHP(hpCost);
        lowerCurrentAttackPoint(attackCost);
        changeName(name);
        changeMaxHp(newHp);
        changeMaxAttackPoint(newAttackPoint);
    }
    //O(1)
    protected boolean evolvePokemon(String name, double newHp, double newAttackPoint) {
        if (canUpgrade(getMaxRank())) {
            setRank();
            int hpCost = UPGRADE_HP_COST_LVL_TWO;
            int attackPointCost = UPGRADE_ATTACK_POINT_COST_LVL_TWO;
            if (getRank() == RANK_THREE) {
                hpCost = UPGRADE_HP_COST_LVL_THREE;
                attackPointCost = UPGRADE_ATTACK_POINT_COST_LVL_THREE;
            }
            System.out.println(getName() + " evolved into " + name);
            changeStats(name, hpCost, attackPointCost, newHp, newAttackPoint);
            return true;
        } else return false;
    }
    //O(1)
    protected boolean canSpecialAttack() {
        return (getSpecialAttackCount() < SPECIAL_ATTACK_MAX);
    }
    //O(1)
    public Pokemon getPokemon() {
        return Pokemon.this;
    }
    //O(1)
    public boolean isCriticalAttack() {
        return this.criticalAttack;
    }
    //O(1)
    public void setCriticalAttack() {
        this.criticalAttack = false;
    }
    //O(1)
    private boolean enoughRank(int rank) {
        return getRank() >= rank;
    }
    //O(1)
    protected boolean canAttack(int rank, int cost) {
        return enoughRank(rank) && enoughAttackPoint(cost);
    }

    //O(1)
    public boolean performAttacking(Pokemon enemy, Attack attack) {
        if (this.enoughAttackPoint(attack.getAttackPointCost())) {
            if (enoughRank(attack.getRankRequired())) {
                double damage = attack.calculateDamage(Pokemon.this);
                enemy.lowerCurrentHP(damage);
                lowerCurrentAttackPoint(attack.getAttackPointCost());
                System.out.println(getName() + " attacked with " + attack.getAttackName() + " and caused " + damage + " damage to " + enemy.getName());
                return true;
            } else {
                System.out.println("your pokemon is not at the required rank for this attack");
                return false;
            }
        } else {
            System.out.println("not enough Attack points ");
            return false;
        }
    }

    protected void lowerCurrentHP(double hp) {
        this.currentHP -= hp;
    }
    //O(1)
    protected void lowerCurrentAttackPoint(double attackPoint) {
        if (this.currentAttackPoint - attackPoint < 0) {
            this.currentAttackPoint = 0;
        } else {
            this.currentAttackPoint -= attackPoint;
        }
    }
    //O(1)
    public void addCurrentHP(double hp) {
        if (this.currentHP + hp > this.maxHp) {
            this.currentHP = this.maxHp;
        } else {
            this.currentHP += hp;
        }
        this.currentHP=(double) Math.round(this.currentHP * 100) / 100;
    }
    //O(1)
    public void addCurrentAttackPoint(double attackPoint) {
        if (this.currentAttackPoint + attackPoint > this.maxAttackPoints) {
            this.currentAttackPoint = this.maxAttackPoints;
        } else {
            this.currentAttackPoint += attackPoint;
        }
    }
    //O(1)
    public Attack[] getAttackList() {
        return this.attackList;
    }
    //O(1)
    protected Attack getKickAttack() {
        return this.KICK_ATTACK;
    }
}
