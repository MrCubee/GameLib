package fr.mrcubee.game;

import fr.mrcubee.game.kit.KitManager;
import fr.mrcubee.game.skript.KitGameSkriptRegister;

public class KitGame<P extends GameJavaPlugin<P, S, ? extends Game<P, S>>, S extends GameSettings> extends Game<P, S> {

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
