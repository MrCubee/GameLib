package fr.mrcubee.game;

import ch.njol.skript.SkriptAddon;
import fr.mrcubee.langlib.Lang;
import fr.mrcubee.game.skript.GameSkriptRegister;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
public abstract class GameJavaPlugin<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S>> extends JavaPlugin {

    private G game;

    private boolean checkAndLoadSkriptPlugin() {
        final Plugin plugin = Bukkit.getPluginManager().getPlugin("Skript");

        return plugin != null;
    }

    protected void loadSkriptAddon(final SkriptAddon gameAddon) {
        GameSkriptRegister.register();
    }

    @Override
    public void onLoad() {
        final G game;

        saveDefaultConfig();
        Lang.setDefaultLang("EN_us");
        game = setupGame();
        if (game != null)
            this.game = game;
    }

    @Override
    public void onEnable() {
        if (checkAndLoadSkriptPlugin()) {
            getLogger().info("Load the internal Skript addon.");
            if (this.game != null)
                this.game.skriptSetup();
        } else
            getLogger().warning("This plugin can be used with Skript plugin.");
    }

    public abstract G setupGame();
    public abstract void onEnableGame(final G game);
    public abstract void onDisableGame(final G game);

    public G getGame() {
        return this.game;
    }

}
