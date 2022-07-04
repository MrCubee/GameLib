package fr.mrcubee.game.timer;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
public class Timer<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> extends BukkitRunnable {

    private final G game;
    private final Set<TimerListener<P, S, G>> listeners;

    public Timer(final G game) {
        this.game = game;
        this.listeners = new HashSet<TimerListener<P, S, G>>();
    }

    protected Timer(final Timer<P, S, G> gameTimer) {
        this.game = gameTimer.game;
        this.listeners = gameTimer.listeners;
    }

    @Override
    public void run() {
        for (TimerListener<P, S, G> timerListener : this.listeners)
            timerListener.update(this.game);
    }

    public boolean addTimerListener(final TimerListener<P, S, G> timerListener) {
        if (timerListener == null)
            return false;
        return this.listeners.add(timerListener);
    }

    public boolean removeTimerListener(final TimerListener<P, S, G> timerListener) {
        if (timerListener == null)
            return false;
        return this.listeners.remove(timerListener);
    }

    public void clearTimerListeners() {
        this.listeners.clear();
    }

    public int getSafeTaskId() {
        try {
            return super.getTaskId();
        } catch (IllegalStateException ignored) {}
        return -1;
    }
}
