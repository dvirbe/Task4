import java.util.Random;
import java.util.Scanner;

public class Battle implements GameStats {
    final private Pokemon PLAYER_ONE;
    final private Pokemon PLAYER_TWO;
    private Pokemon playerTurn;
    private static Pokemon defensePokemon;
    private int turn;

    public Battle(Pokemon playerOne, Pokemon playerTwo) {
        this.PLAYER_ONE = playerOne;
        this.PLAYER_TWO = playerTwo;
        this.playerTurn = this.PLAYER_ONE;
        defensePokemon = this.PLAYER_TWO;
        this.turn = TURN_DEFAULT;
    }

    public boolean gameOver() {
        return (gameOverPlayerOne() || gameOverPlayerTwo());
    }

    public boolean gameOverPlayerOne() {
        return (this.PLAYER_ONE.getCurrentHP() <= HP_WIN_CONDITION);
    }

    public boolean gameOverPlayerTwo() {
        return (this.PLAYER_TWO.getCurrentHP() <= HP_WIN_CONDITION);
    }

    private String winner() {
        String winner = null;
        if (gameOverPlayerOne()) {
            winner = "player two";
        }
        if (gameOverPlayerTwo()) {
            winner = "player one";
        }
        return winner;
    }

    public Pokemon getPLAYER_ONE() {
        return this.PLAYER_ONE;
    }

    public Pokemon getPLAYER_TWO() {
        return this.PLAYER_TWO;
    }

    public void setTurn() {
        this.turn++;
    }

    public void changePlayerTurn() {
        setTurn();
        if (this.playerTurn == this.PLAYER_TWO) {
            this.playerTurn = this.PLAYER_ONE;
            defensePokemon = this.PLAYER_TWO;
        } else {
            this.playerTurn = this.PLAYER_TWO;
            defensePokemon = this.PLAYER_ONE;
        }
    }

    public void startGame() {
        int criticalAttackCounterPlayer1 = CRITICAL_ATTACK_COUNTER_DEFAULT;
        int criticalAttackCounterPlayer2 = CRITICAL_ATTACK_COUNTER_DEFAULT;

        while (!gameOver()) {
            criticalAttackCounterPlayer1 = criticalAttackDeterminate(criticalAttackCounterPlayer1);
            criticalAttackCounterPlayer2 = criticalAttackDeterminate(criticalAttackCounterPlayer2);
            versusString();
            menu();
            endTurnBonus();
            if (this.playerTurn instanceof ElectricityPokemon) {
                this.playerTurn.passiveAbility();
            }
            changePlayerTurn();
            if (criticalAttackCounterPlayer1 >= CRITICAL_ATTACK_DURATION) {
                this.PLAYER_ONE.setCriticalAttack();
                criticalAttackCounterPlayer1 = CRITICAL_ATTACK_COUNTER_DEFAULT;
            }
            if (criticalAttackCounterPlayer2 >= CRITICAL_ATTACK_DURATION) {
                this.PLAYER_TWO.setCriticalAttack();
                criticalAttackCounterPlayer2 = CRITICAL_ATTACK_COUNTER_DEFAULT;
            }
        }
        System.out.println("the winner is " + winner());
    }

    public int criticalAttackDeterminate(int criticalAttackCounter) {
        if (this.playerTurn.isCriticalAttack()) {
            criticalAttackCounter++;
        }
        return criticalAttackCounter;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choose;
        do {
            System.out.println("""
                    1 - List of attacks
                    2 - Rest
                    3 - Evolve Pokemon
                    4 - Special Ability
                    """);
            choose = scanner.nextInt();
        } while (choose < BATTLE_ATTACK_CASE || choose > BATTLE_SPECIAL_ABILITY_CASE);
        switch (choose) {
            case BATTLE_ATTACK_CASE -> attack();
            case BATTLE_REST_CASE -> this.playerTurn.rest();
            case BATTLE_EVOLVE_CASE -> evolve();
            case BATTLE_SPECIAL_ABILITY_CASE -> specialAttack();
        }
    }

    private void evolve() {
        boolean upgradeSuccessfully = false;
        if (this.playerTurn instanceof Evolve) {
            upgradeSuccessfully = ((Evolve) this.playerTurn).evolve();
        }
        if (!upgradeSuccessfully) {
            System.out.println("your pokemon can not evolve");
            menu();
        }
    }

    private void endTurnBonus() {
        Random random = new Random();
        int randomHp = random.nextInt(MIN_HEAL_BONUS, MAX_HEAL_BONUS);
        this.playerTurn.addCurrentHP(randomHp);
        int randomAttackPoint = random.nextInt(MIN_ATTACK_POINT_BONUS, MAX_ATTACK_POINT_BONUS);
        this.playerTurn.addCurrentAttackPoint(randomAttackPoint);
        System.out.println("end turn bonus : " + randomHp + " HP and " + randomAttackPoint + " AttackPoint");
    }

    public String playerTurnName() {
        if (this.playerTurn == this.PLAYER_ONE) {
            return "Player one turn";
        } else {
            return "Player Two turn";
        }
    }

    public void versusString() {
        System.out.println(
                "\t\t\t\t\t\tTurn " + this.turn + "\nplayer1\t\t\t\tVS\t\t\t\tplayer2" + "\n" + getPLAYER_ONE().getName() + "\t\t\t\t\t\t\t\t\t" +
                        getPLAYER_TWO().getName() + "\n" + getPLAYER_ONE().getCurrentHP() + "/" + getPLAYER_ONE().getMaxHp() +
                        " HP\t\t\t\t\t\t\t" + getPLAYER_TWO().getCurrentHP() + "/" + getPLAYER_TWO().getMaxHp() + " HP\n" +
                        getPLAYER_ONE().getCurrentAttackPoint() + "/" + getPLAYER_ONE().getMaxAttackPoints() + " AP\t\t\t\t\t\t\t" +
                        getPLAYER_TWO().getCurrentAttackPoint() + "/" + getPLAYER_TWO().getMaxAttackPoints() + " AP" + "\nrank " + getPLAYER_ONE().getRank() +
                        "\t\t\t\t\t\t\t\t\t\trank " + getPLAYER_TWO().getRank() + "\n\n" + playerTurnName()
        );
    }

    private void printAttackList() {
        for (int i = 0; i < this.playerTurn.getAttackList().length; i++) {
            System.out.println(i + 1 + ")  " + this.playerTurn.getAttackList()[i]);
        }
    }

    public void attack() {
        Scanner scanner = new Scanner(System.in);
        boolean attackSuccessfully;
        printAttackList();
        int index;
        do {
            index = scanner.nextInt();
        } while (index < KICK_ATTACK_CASE || index > this.playerTurn.getAttackList().length);
        attackSuccessfully = this.playerTurn.performAttacking(defensePokemon, this.playerTurn.getAttackList()[index - 1]);
        if (!attackSuccessfully) {
            menu();
        }
        if ((this.playerTurn.getPokemon() instanceof FirePokemon)) {
            this.playerTurn.passiveAbility();
        }
    }

    public static Pokemon getDefensePokemon() {
        return defensePokemon;
    }

    private void specialAttack() {
        if (!this.playerTurn.specialAttack()) {
            System.out.println("your pokemon can not use his special ability");
            menu();
        } else {
            System.out.println(this.playerTurn.getName() + " used his special ability");
            this.playerTurn.specialAttack();
        }
    }


    public static Pokemon randomPokemon() {
        Random random = new Random();
        int index = random.nextInt(PICHU_CASE, BLITZLE_CASE + 1);
        return switch (index) {
            case PICHU_CASE -> new Pichu();
            case ELECTABUZZ_CASE -> new Electabuzz();
            case SALANDIT_CASE -> new Salandit();
            case MOLTRES_CASE -> new Moltres();
            case CHARMANDER_CASE -> new Charmander();
            default -> new Blitzle();
        };
    }
}
