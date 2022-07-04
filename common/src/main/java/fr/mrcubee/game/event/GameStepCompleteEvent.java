package fr.mrcubee.game.event;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;
import fr.mrcubee.game.step.Step;
import org.bukkit.event.HandlerList;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
public class GameStepCompleteEvent<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> extends GameStepEvent<P, S, G> {

    private static final HandlerList HANDLERS = new HandlerList();

    public GameStepCompleteEvent(final G game, final Step<P, S, G> step) {
        super(game, step);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
