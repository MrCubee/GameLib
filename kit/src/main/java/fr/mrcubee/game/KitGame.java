package fr.mrcubee.game;

import fr.mrcubee.game.kit.KitManager;
import fr.mrcubee.game.skript.KitGameSkriptRegister;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class KitGame<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> extends Game<P, S, G> {

    private final KitManager kitManager;

    public KitGame(final P plugin, final S gameSetting) {
        super(plugin, gameSetting);
        this.kitManager = new KitManager(plugin);
    }

    public KitManager getKitManager() {
        return this.kitManager;
    }

    @Override
    public void skriptSetup() {
        super.skriptSetup();
        KitGameSkriptRegister.register();
    }
    
}
