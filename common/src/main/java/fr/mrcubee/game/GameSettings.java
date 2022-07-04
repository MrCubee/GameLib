package fr.mrcubee.game;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public abstract class GameSettings {

    protected int minPlayer;
    protected int maxPlayer;
    protected long startTime;
    protected long restartTime;

    public int getMinPlayer() {
        return this.minPlayer;
    }

    public int getMaxPlayer() {
        return this.maxPlayer;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getRestartTime() {
        return this.restartTime;
    }

    public long getDuringTime() {
        return -1;
    }

}
