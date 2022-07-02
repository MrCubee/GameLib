package fr.mrcubee.game.event;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class EventUtils {

    public static boolean callEvent(final Event event) {
        Bukkit.getPluginManager().callEvent(event);
        if (event instanceof Cancellable)
            return !((Cancellable) event).isCancelled();
        return true;
    }

}
