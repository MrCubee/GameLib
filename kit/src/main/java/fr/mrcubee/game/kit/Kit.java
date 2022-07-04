package fr.mrcubee.game.kit;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public abstract class Kit implements Listener {

    protected final int hashCode;
    private final String name;
    private final ItemStack kitMenuItemStack;
    private final Set<Player> players;

    protected Kit(final String name, final ItemStack kitMenuItemStack) {
        this.name = name;
        this.hashCode = (name != null) ? name.toLowerCase().hashCode() : 0;
        this.kitMenuItemStack = kitMenuItemStack;
        this.players = new HashSet<Player>();
    }

    protected boolean addPlayer(final Player player) {
        if (player == null || !player.isOnline())
            return false;
        return this.players.add(player);
    }

    protected boolean removePlayer(final Player player) {
        if (player == null)
            return false;
        return this.players.remove(player);
    }

    public boolean containsPlayer(final Player player) {
        if (player == null)
            return false;
        return this.players.contains(player);
    }

    protected void givePlayersKit() {
        for (Player player : this.players)
            if (player != null)
                givePlayerKit(player);
    }

    protected abstract boolean canTakeKit(final Player player);

    protected abstract void canTakeKitReason(final Player player);

    protected abstract void givePlayerKit(final Player player);

    protected void removePlayersKit() {
        for (Player player : this.players)
            if (player != null)
                removePlayerKit(player);
    }

    public abstract void removePlayerKit(final Player player);

    public abstract boolean canLostItem(final ItemStack itemStack);

    public String getName() {
        return this.name;
    }

    public ItemStack getKitMenuItemStack() {
        return this.kitMenuItemStack;
    }

    public Set<Player> getPlayers() {
        return new HashSet<Player>(this.players);
    }

    public int getNumberPlayer() {
        return this.players.size();
    }

    public abstract void update();

    public int hashCode() {
        return this.hashCode;
    }
}
