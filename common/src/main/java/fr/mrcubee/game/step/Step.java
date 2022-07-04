package fr.mrcubee.game.step;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;

import java.util.Objects;

public abstract class Step<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S, G>> {

    private final G game;
    private final String name;
    private long startTime;
    private long duringTime;

    protected Step(final G game, final String name) {
        this.game = game;
        this.name = name;
        this.startTime = 0;
        this.duringTime = -1;
    }

    protected void setSecondDuring(final long seconds) {
        if (seconds < 0) {
            this.duringTime = -1;
            return;
        }
        this.duringTime = seconds * 1000;
    }

    protected void start() {
        this.startTime = System.currentTimeMillis();
    }

    protected void complete() {
        this.startTime = 0;
    }

    protected void remove() {
        this.startTime = 0;
    }

    public abstract void update();

    public long getStartTime() {
        return this.startTime;
    }

    public long getDuringTime() {
        return this.duringTime / 1000;
    }

    public long getEndSeconds() {
        final long time;

        if (this.startTime == 0 || this.duringTime < 0)
            return -1;
        time = System.currentTimeMillis() - this.startTime;
        if (time > this.duringTime)
            return 0;
        return (this.duringTime - time) / 1000;
    }

    public G getGame() {
        return this.game;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.name);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof Step && obj.hashCode() == hashCode();
    }

}
