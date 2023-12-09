import Controllers.GameController;
import Models.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("------WELCOME TO THE GAME OF TIC-TAC-TOE------");

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the dimensions of the board: ");
        int dimension = sc.nextInt();

        System.out.println("Enter the number of players who will play: ");
        int noOfPlayers = sc.nextInt();

        System.out.println("Do you want to play with a Bot ? [Y/N]:  ");
        String isBot = sc.next();

        List<Player> players = new LinkedList<>();

        // if there is a bot
        if(isBot.equals("Y") || isBot.equals("y")){
            noOfPlayers--;
            System.out.println("Enter a name for the BOT: ");
            String botName = sc.next();
            System.out.println("Enter a symbol for BOT: ");
            String botSymbol = sc.next();
            System.out.println("Select the Difficulty level of the BOT [EASY - 1 / MEDIUM - 2 / HARD - 3]: ");
            int botDifficultyLevel = sc.nextInt();

            players.add(new Bot(botName, botSymbol.charAt(0), BotDifficultyLevel.EASY));
        }

        // remaining human players
        for(int i=0;i<noOfPlayers;i++){
            System.out.println("Enter player " + (i+1) + "'s name: ");
            String playerName = sc.next();
            System.out.println("Enter a symbol for player " + (i+1) + ": ");
            String playerSymbol = sc.next();    // assuming a single character

            players.add(new Player(playerName, playerSymbol.charAt(0), PlayerType.HUMAN));
        }

        // to build the initial game object after all the validations.

        /* The below line of code should be hidden to the client as he doesn't know how the game is getting created hence, we need a controller

        Game game = Game.getBuilder().setDimension(dimension).setPlayers(players).build(); */

        // this is like the waiter in the restaurant
        GameController gameController = new GameController();

        // initial game object now built by the controller. Here we are playing with a single instance of the game class, we can also play multiple games using the one game controller now.
        Game game = gameController.createGame(dimension,players);

        while(gameController.getGameStatus(game) == GameStatus.IN_PROGRESS){
            // main logic of the game

            // display the current board
            gameController.display(game);

            // execute the next move
            gameController.executeNextMove(game);
        }

        if(gameController.getGameStatus(game) == GameStatus.DRAW){
            System.out.println("DRAW GAME");
        }
        else if(gameController.getGameStatus(game) == GameStatus.ENDED){
            List<Player> playersList = gameController.getPlayers(game);
            int nextPlayerIndex = gameController.getNextPlayerIndex(game);
            System.out.println("Congratulations " + playersList.get(nextPlayerIndex).getName() + " !! You've won the game");
        }
    }
}