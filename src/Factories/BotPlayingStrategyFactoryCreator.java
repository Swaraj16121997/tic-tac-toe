package Factories;

import Models.BotDifficultyLevel;
import Strategies.EasyBotPlayingStrategy;

public class BotPlayingStrategyFactoryCreator {
    public static BotPlayingStrategyFactory getBotPlayingStrategyFactoryForDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        if(botDifficultyLevel == BotDifficultyLevel.EASY) {
            return new EasyBotPlayingStrategyFactory();
        }
        // else if Medium and Hard likewise implement it

        return null;
    }
}
