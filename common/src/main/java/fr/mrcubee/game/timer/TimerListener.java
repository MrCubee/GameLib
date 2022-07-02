package fr.mrcubee.game.timer;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
@FunctionalInterface
public interface TimerListener<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S>> {

    public void update(final G game);

}
