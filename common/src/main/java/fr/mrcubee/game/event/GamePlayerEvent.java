package fr.mrcubee.game.event;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
public abstract class GamePlayerEvent<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> extends GameEvent<P, S, G> {

    private final Player player;

    public GamePlayerEvent(final G game, final Player player) {
        super(game);
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

}
