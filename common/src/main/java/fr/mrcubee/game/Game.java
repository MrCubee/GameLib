package fr.mrcubee.game;

import fr.mrcubee.game.event.EventUtils;
import fr.mrcubee.game.event.GameStatsChangeEvent;
import fr.mrcubee.game.event.GamePlayerJoinEvent;
import fr.mrcubee.game.skript.GameSkriptRegister;
import fr.mrcubee.game.skript.Skriptable;
import fr.mrcubee.game.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashSet;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 */
public class Game<P extends GameJavaPlugin<P, S, ? extends Game<P, S>>, S extends GameSettings> implements Skriptable {

    private final P plugin;
    private final S gameSetting;
    private Timer<P, S, ? extends Game<P, S>> timer;
    private World gameWorld;
    private GameStats gameStats;
    private long gameStartTime;
    private long gameEndTime;
    private final HashSet<Player> players;

    public Game(final P plugin, final S gameSetting) {
        this.plugin = plugin;
        this.gameSetting = gameSetting;
        this.players = new HashSet<Player>();
        this.gameStartTime = -1;
        this.gameEndTime = -1;
        this.timer = new Timer(this);
    }

    protected void cancelTimer() {
        if (this.timer == null) {
            if (this.timer.getSafeTaskId() != -1)
                this.timer.cancel();
            this.timer = new Timer(this);
        }
    }

    protected void createTimer() {
        cancelTimer();
        this.timer.runTaskTimer(this.plugin, 0L, 20L);
    }

    public S getGameSettings() {
        return this.gameSetting;
    }

    public void setGameStats(final GameStats newStats) {
        if (newStats == null || this.gameStats == newStats
        || (this.gameStats.ordinal() >= 2 && this.gameStats.ordinal() > newStats.ordinal()))
            return;
        Bukkit.getServer().getPluginManager().callEvent(new GameStatsChangeEvent(this, newStats));
        this.gameStats = newStats;
    }

    public GameStats getGameStats() {
        return this.gameStats;
    }

    public HashSet<Player> getPlayerInGame() {
        return (HashSet<Player>) this.players.clone();
    }

    public int getNumberPlayer() {
        return this.players.size();
    }

    public void addPlayer(final Player player) {
        if (player == null || !player.isOnline())
            return;
        if (this.players.add(player))
            EventUtils.callEvent(new GamePlayerJoinEvent(this, player));
    }

    public void addSpectator(final Player player) {

        if (player == null)
            return;
        if (this.players.remove(player))
            EventUtils.callEvent(new GamePlayerJoinEvent(this, player));
    }

    public boolean isSpectator(final Player player) {
        return player != null && !this.players.contains(player);
    }

    public int getNumberSpectator() {
        return Bukkit.getServer().getOnlinePlayers().size() - this.players.size();
    }

    public long getGameStartTime() {
        return this.gameStartTime;
    }

    public long getGameEndTime() {
        return this.gameEndTime;
    }

    public Timer<P, S, ? extends Game<P, S>> getTimer() {
        return this.timer;
    }

    public P getPlugin() {
        return this.plugin;
    }

    @Override
    public void skriptSetup() {
        GameSkriptRegister.register();
    }

}
