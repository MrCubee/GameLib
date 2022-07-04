package fr.mrcubee.game.event;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;
import fr.mrcubee.game.step.Step;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
public abstract class GameStepEvent<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> extends GameEvent<P, S, G> {

    private final Step<P, S, G> step;

    public GameStepEvent(final G game, final Step<P, S, G> step) {
        super(game);
        this.step = step;
    }

    public Step<P, S, G> getStep() {
        return this.step;
    }

}
