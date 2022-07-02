package fr.mrcubee.game.event;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;
import org.bukkit.event.Event;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
public abstract class GameEvent<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S>> extends Event {

    private final G game;

    public GameEvent(final G game) {
        this.game = game;
    }

    public G getGame() {
        return this.game;
    }

}
