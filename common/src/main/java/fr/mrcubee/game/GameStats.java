package fr.mrcubee.game;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public enum GameStats {

    OPENING,
    WAITING,
    STARTING,
    DURING,
    STOPPING,
    CLOSING;

    public static GameStats fromName(final String name) {
        if (name == null)
            return null;
        for (GameStats gameStat : GameStats.values())
            if (gameStat.name().equalsIgnoreCase(name))
                return gameStat;
        return null;
    }

    public static GameStats fromOrdinal(final int ordinal) {
        final GameStats[] values;

        if (ordinal < 0)
            return null;
        values = GameStats.values();
        if (ordinal >= values.length)
            return null;
        return values[ordinal];
    }
}
