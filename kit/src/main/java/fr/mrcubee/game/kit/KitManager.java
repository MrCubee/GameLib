package fr.mrcubee.game.kit;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.event.EventUtils;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class KitManager {

    private KitUpdater kitUpdaterTask;
    private final GameJavaPlugin<?, ?, ?> plugin;
    protected final Set<Kit> kits;

    public KitManager(GameJavaPlugin<?, ?, ?> plugin) {
        this.plugin = plugin;
        this.kits = new HashSet<Kit>();
    }

    public void cancelKitUpdater() {
        if (this.kitUpdaterTask != null) {
            this.kitUpdaterTask.cancel();
            this.kitUpdaterTask = null;
        }
    }

    public void createKitUpdater() {
        final Game<?, ?> game = plugin.getGame();

        if (game == null)
            return;
        cancelKitUpdater();
        this.kitUpdaterTask = new KitUpdater(this);
        this.kitUpdaterTask.runTaskTimer(plugin, 0L, 10L);
    }

    public boolean registerKit(final Kit kit) {
        if (kit == null)
            return false;
        return this.kits.add(kit);
    }

    public boolean unRegisterKit(final Kit kit) {
        if (kit == null)
            return false;
        return this.kits.remove(kit);
    }

    public boolean unRegisterKit(final String kitName) {
        return unRegisterKit(getKit(kitName));
    }

    public boolean hasKit(final Player player) {
        if (player == null)
            return false;
        for (Kit kit : this.kits)
            if (kit.containsPlayer(player))
                return true;
        return false;
    }

    public void removeKit(final Player player) {
        if (player == null)
            return;
        for (Kit kit : this.kits)
            kit.removePlayer(player);
    }

    public Kit[] getKits() {
        return this.kits.toArray(new Kit[this.kits.size()]);
    }

    public Kit getKit(final String kitName) {
        int hashCode;

        if (kitName == null)
            return null;
        hashCode = kitName.toLowerCase().hashCode();
        for (Kit kit : this.kits)
            if (kit.hashCode == hashCode)
                return kit;
        return null;
    }

    public Kit[] getKitByPlayer(final Player player) {
        final LinkedList<Kit> result;

        if (player == null)
            return null;
        result = new LinkedList<Kit>();
        for (Kit kit : this.kits)
            if (kit.containsPlayer(player))
                result.add(kit);
        if (result.size() <= 0)
            return null;
        return result.toArray(new Kit[result.size()]);
    }

    public boolean playerSelectKit(final Player player, final Kit kit) {
        if (player == null || kit == null)
            return false;
        if (!kit.canTakeKit(player)) {
            kit.canTakeKitReason(player);
            return false;
        }
        if (!EventUtils.callEvent(new KitPlayerSelectEvent(this.plugin.getGame(), player, kit)))
            return false;
        kit.addPlayer(player);
        return true;
    }

    public boolean addPlayerToKit(final Player player, final Kit kit) {
        if (player == null || kit == null)
            return false;
        if (!EventUtils.callEvent(new KitAddPlayerEvent(this.plugin.getGame(), player, kit)))
            return false;
        return kit.addPlayer(player);
    }

    public boolean addPlayerToKit(final Player player, final String kitName) {
        if (player == null || kitName == null)
            return false;
        return addPlayerToKit(player, getKit(kitName));
    }

    public boolean removePlayerFromKit(final Player player, final Kit kit) {
        if (player == null || kit == null)
            return false;
        if (!EventUtils.callEvent(new KitRemovePlayerEvent(this.plugin.getGame(), player, kit)))
            return false;
        return kit.removePlayer(player);
    }

    public boolean removePlayerFromKit(final Player player, final String kitName) {
        if (player == null || kitName == null)
            return false;
        return removePlayerFromKit(player, getKit(kitName));
    }

}
