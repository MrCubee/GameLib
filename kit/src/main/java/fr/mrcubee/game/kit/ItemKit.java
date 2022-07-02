package fr.mrcubee.game.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public abstract class ItemKit extends Kit {

    protected final ItemStack[] kitItems;

    public ItemKit(final String name, final ItemStack kitMenuItemStack, final ItemStack... kitItems) {
        super(name, kitMenuItemStack);
        this.kitItems = kitItems;
    }

    @Override
    protected void givePlayerKit(Player player) {
        if (player == null)
            return;
        player.getInventory().addItem(this.kitItems);
    }

    @Override
    public void removePlayerKit(Player player) {
        if (player == null)
            return;
        player.getInventory().removeItem(this.kitItems);
    }

    @Override
    public boolean canLostItem(final ItemStack targetedItemStack) {
        if (targetedItemStack == null)
            return true;
        for (ItemStack itemStack : this.kitItems)
            if (targetedItemStack.isSimilar(itemStack))
                return false;
        return true;
    }

}
