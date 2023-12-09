package Models;

import Factories.BotPlayingStrategyFactory;
import Factories.BotPlayingStrategyFactoryCreator;
import Strategies.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String botName, char botSymbol, BotDifficultyLevel botDifficultyLevel) {
        super(botName,botSymbol,PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;

        // creating bot playing strategy using factory design pattern
        BotPlayingStrategyFactory botPlayingStrategyFactory = createBotPlayingStrategyFactory(botDifficultyLevel);
        this.botPlayingStrategy = botPlayingStrategyFactory.createBotPlayingStrategy();
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;

    }

    private BotPlayingStrategyFactory createBotPlayingStrategyFactory(BotDifficultyLevel botDifficultyLevel){
        return BotPlayingStrategyFactoryCreator.getBotPlayingStrategyFactoryForDifficultyLevel(botDifficultyLevel);
    }

    @Override
    public Move decideMove(Board board) {
        return botPlayingStrategy.decideMove(this,board);
    }
}
