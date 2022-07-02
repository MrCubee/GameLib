package fr.mrcubee.game.kit;

import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class KitUpdater extends BukkitRunnable {

    private final KitManager kitManager;

    public KitUpdater(final KitManager kitManager) {
        this.kitManager = kitManager;
    }

    @Override
    public void run() {
        for (Kit kit : this.kitManager.kits)
            kit.update();
    }

}
