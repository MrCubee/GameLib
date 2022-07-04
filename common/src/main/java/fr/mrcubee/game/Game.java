package fr.mrcubee.game;

import fr.mrcubee.game.event.EventUtils;
import fr.mrcubee.game.event.GamePlayerQuitEvent;
import fr.mrcubee.game.event.GameStatsChangeEvent;
import fr.mrcubee.game.event.GamePlayerJoinEvent;
import fr.mrcubee.game.scoreboard.ScoreboardManager;
import fr.mrcubee.game.skript.GameSkriptRegister;
import fr.mrcubee.game.skript.Skriptable;
import fr.mrcubee.game.step.StepManager;
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
public class Game<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> implements Skriptable {

    private final P plugin;
    private final S gameSetting;
    private final StepManager<P, S, G> stepManager;
    private Timer<P, S, G> timer;
    private World gameWorld;
    private GameStats gameStats;
    private int totalPlayers;
    private long gameStartTime;
    private long gameEndTime;
    private long nextStatTime;
    private final HashSet<Player> players;

    public Game(final P plugin, final S gameSetting) {
        this.plugin = plugin;
        this.gameSetting = gameSetting;
        this.stepManager = new StepManager(this);
        this.players = new HashSet<Player>();
        this.gameStartTime = -1;
        this.gameEndTime = -1;
        this.timer = new Timer(this);
        this.totalPlayers = -1;
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

    public StepManager<P, S, G> getStepManager() {
        return this.stepManager;
    }

    public void setGameStats(final GameStats newStats) {
        final long duringTime;

        if (newStats == null || this.gameStats == newStats
        || (this.gameStats.ordinal() >= 2 && this.gameStats.ordinal() > newStats.ordinal()))
            return;
        Bukkit.getServer().getPluginManager().callEvent(new GameStatsChangeEvent(this, newStats));
        this.gameStats = newStats;
        switch (this.gameStats) {
            case STARTING:
                this.nextStatTime = System.currentTimeMillis() + (this.gameSetting.getStartTime() * 1000);
                break;
            case DURING:
                this.gameStartTime = System.currentTimeMillis();
                duringTime = this.gameSetting.getDuringTime();
                if (duringTime == -1)
                    this.gameEndTime = -1;
                else
                    this.gameEndTime = System.currentTimeMillis() + (duringTime * 1000);
                this.totalPlayers = this.players.size();
                // this.getStepManager().nextStep();
                break;
            case STOPPING:
                this.nextStatTime = System.currentTimeMillis() + (this.gameSetting.getRestartTime() * 1000);
                break;
        }
    }

    public int getTotalPlayers() {
        return this.totalPlayers;
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
            EventUtils.callEvent(new GamePlayerQuitEvent(this, player));
    }

    public boolean isSpectator(final Player player) {
        return player != null && !this.players.contains(player);
    }

    public int getNumberSpectator() {
        return Bukkit.getServer().getOnlinePlayers().size() - this.players.size();
    }

    public long getNextStatTime() {
        return this.nextStatTime;
    }

    public long getGameStartTime() {
        return this.gameStartTime;
    }

    public long getGameEndTime() {
        return this.gameEndTime;
    }

    public World getGameWorld() {
        return this.gameWorld;
    }

    public Timer<P, S, G> getTimer() {
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
