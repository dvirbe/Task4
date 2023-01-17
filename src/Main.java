public class Main {
    //O(1)
    public static void main(String[] args) {
        Pokemon pokemonOne = Battle.randomPokemon();
        Pokemon pokemonTwo = Battle.randomPokemon();
        Battle battle = new Battle(pokemonOne, pokemonTwo);
        battle.startGame();
    }
}
