package fr.mrcubee.game.timer;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;
import fr.mrcubee.game.GameStats;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
public class DefaultGameTimer<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> implements TimerListener<P, S, G> {

    private void waiting(final G game) {
        if (game.getNumberPlayer() >= game.getGameSettings().getMinPlayer())
            game.setGameStats(GameStats.STARTING);
    }

    private void starting(final G game) {
        if (game.getNumberPlayer() < game.getGameSettings().getMinPlayer())
            game.setGameStats(GameStats.WAITING);
    }

    @Override
    public void update(G game) {
        switch (game.getGameStats()) {
            case WAITING:
                waiting(game);
                break;
            case STARTING:
                starting(game);
                break;
        }
    }

}
