package fr.mrcubee.game.skript.event;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameStats;
import fr.mrcubee.game.event.*;
import org.bukkit.entity.Player;

public class GameSkriptEventRegister {

    public static void register() {

        /* Game Stats Event & Value */
        Skript.registerEvent("game stats", SkGameStatsChangeEvent.class, GameStatsChangeEvent.class, "game stats")
            .description("Called each time the game state changes.")
            .since("1.0");
        EventValues.registerEventValue(GameStatsChangeEvent.class, GameStats.class, new Getter<GameStats, GameStatsChangeEvent>() {
            @Override
            public GameStats get(GameStatsChangeEvent event) {
                return event.getGameStats();
            }
        }, 1);

        /* Game Start Event */
        Skript.registerEvent("game end", SkGameEndEvent.class, GameEndEvent.class, "game end")
            .description("Called when I game is end.")
            .since("1.0");

        /* Game Player Join Event */
        Skript.registerEvent("game player join", SkGamePlayerJoinEvent.class, GamePlayerJoinEvent.class, "game player join")
            .description("Called each time player join game.")
            .since("1.0");

        /* Game Player Quit Event */
        Skript.registerEvent("game player quit", SkGamePlayerQuitEvent.class, GamePlayerQuitEvent.class, "game player quit")
            .description("Called each time player quit game.")
            .since("1.0");

        /* Game Start Event */
        Skript.registerEvent("game start", SkGameStartEvent.class, GameStartEvent.class, "game start")
            .description("Called each time the game start.")
            .since("1.0");

        /* Game Event Value */
        EventValues.registerEventValue(GameEvent.class, Game.class, new Getter<Game, GameEvent>() {
            @Override
            public Game get(GameEvent event) {
                return event.getGame();
            }
        }, 0);

        /* Game Player Event Value */
        EventValues.registerEventValue(GamePlayerEvent.class, Player.class, new Getter<Player, GamePlayerEvent>() {
            @Override
            public Player get(GamePlayerEvent event) {
                return event.getPlayer();
            }
        }, 0);
    }

}
