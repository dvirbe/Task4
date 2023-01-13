import java.util.Random;
import java.util.Scanner;

public class Battle implements GameStats{
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
        this.turn = 1;
    }

    public boolean gameOver() {
        return (gameOverPlayerOne() || gameOverPlayerTwo());
    }

    public boolean gameOverPlayerOne() {
        return (this.PLAYER_ONE.getCurrentHP() <= 0);
    }

    public boolean gameOverPlayerTwo() {
        return (this.PLAYER_TWO.getCurrentHP() <= 0);
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
        int criticalAttackCounterPlayer1 = 0;
        int criticalAttackCounterPlayer2 = 0;

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
            if (criticalAttackCounterPlayer1 >= 2) {
                this.PLAYER_ONE.setCriticalAttack();
                criticalAttackCounterPlayer1 = 0;
            }
            if (criticalAttackCounterPlayer2 >= 2) {
                this.PLAYER_TWO.setCriticalAttack();
                criticalAttackCounterPlayer2 = 0;
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
                    3 - Upgrade Pokemon
                    4 - Special Ability
                    """);
            choose = scanner.nextInt();
        } while (choose < 1 || choose > 4);
        switch (choose) {
            case 1 -> attack();
            case 2 -> this.playerTurn.rest();
            case 3 -> upgrade();
            case 4 -> specialAttack();
        }
    }

    private void upgrade() {
        boolean upgradeSuccessfully = false;
        if (this.playerTurn instanceof Evolve) {
            upgradeSuccessfully = ((Evolve) this.playerTurn).evolve();
        }
        if (!upgradeSuccessfully) {
            menu();
        }
    }

    private void endTurnBonus() {
        Random random = new Random();
        int randomHp = random.nextInt(0, 5);
        this.playerTurn.addCurrentHP(randomHp);
        int randomAttackPoint = random.nextInt(0, 5);
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
                        getPLAYER_TWO().getCurrentAttackPoint() + "/" + getPLAYER_TWO().getMaxAttackPoints() + " AP\n\n" + playerTurnName() + "\n"
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
        } while (index < 1 || index > this.playerTurn.getAttackList().length);
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
        if (!this.playerTurn.canSpecialAttack()) {
            System.out.println("your pokemon cant use his special ability");
            menu();
        } else {
            System.out.println(this.playerTurn.getName() + " used his special ability");
            this.playerTurn.specialAttack();
        }
    }

    public static Pokemon randomPokemon() {
        Random random = new Random();
        int rad = random.nextInt(0, 6);
        return switch (rad) {
            case 0 -> new Pichu();
            case 1 -> new Electabuzz();
            case 2 -> new Salandit();
            case 3 -> new Moltres();
            case 4 -> new Charmander();
            default -> new Blitzle();
        };
    }
}
