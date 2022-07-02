package fr.mrcubee.game.event;

import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameJavaPlugin;
import fr.mrcubee.game.GameSettings;
import fr.mrcubee.game.kit.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 * @param <P> Main plugin class.
 * @param <S> Plugin game's settings class.
 * @param <G> Plugin's game class.
 */
public class KitGiveEvent<P extends GameJavaPlugin<P, S, G>, S extends GameSettings, G extends Game<P, S>> extends KitPlayerEvent<P, S, G> implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private ItemStack[] items;
    private boolean cancelled;

    public KitGiveEvent(final G game, final Player player, final Kit kit, final ItemStack... items) {
        super(game, player, kit);
        this.items = items;
        this.cancelled = false;
    }

    public void setItems(ItemStack... items) {
        this.items = items;
    }

    public ItemStack[] getItems() {
        return Arrays.copyOf(this.items, this.items.length);
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}
