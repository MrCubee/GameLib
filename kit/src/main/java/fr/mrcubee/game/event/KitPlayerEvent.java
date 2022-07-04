package fr.mrcubee.game.event;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;
import fr.mrcubee.game.kit.Kit;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
public abstract class KitPlayerEvent<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> extends GamePlayerEvent<P, S, G> {

    private final Kit kit;

    public KitPlayerEvent(final G game, final Player player, final Kit kit) {
        super(game, player);
        this.kit = kit;
    }

    public Kit getKit() {
        return this.kit;
    }

}
