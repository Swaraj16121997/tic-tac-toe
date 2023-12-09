package Factories;

import Strategies.BotPlayingStrategy;
import Strategies.EasyBotPlayingStrategy;

public class EasyBotPlayingStrategyFactory implements BotPlayingStrategyFactory{

    @Override
    public BotPlayingStrategy createBotPlayingStrategy() {
        return new EasyBotPlayingStrategy();
    }
}
