package fr.mrcubee.game.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.Converters;
import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameStats;
import fr.mrcubee.game.skript.event.GameSkriptEventRegister;
import fr.mrcubee.game.skript.expression.GameSkriptExpressionRegister;

public class GameSkriptRegister {

    public static void register() {

        Classes.registerClass(new ClassInfo<Game>(Game.class, "game")
                .user("games?")
                .name("Game"));
        Classes.registerClass(new ClassInfo<GameStats>(GameStats.class, "gamestat")
                .user("gamestats?")
                .name("Game Stat")
                .parser(new Parser<GameStats>() {
                    @Override
                    public String toString(GameStats gameStats, int flags) {
                        return gameStats.name();
                    }

                    @Override
                    public String toVariableNameString(GameStats gameStats) {
                        return gameStats.name();
                    }
                }));
        Converters.registerConverter(GameStats.class, String.class, Enum::name);
        Converters.registerConverter(GameStats.class, Integer.class, Enum::ordinal);
        Converters.registerConverter(String.class, GameStats.class, GameStats::fromName);
        Converters.registerConverter(Integer.class, GameStats.class, GameStats::fromOrdinal);
        GameSkriptExpressionRegister.register();
        GameSkriptEventRegister.register();
    }

}
