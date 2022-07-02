package fr.mrcubee.game.kit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public abstract class CoolDownItemKit extends ItemKit {

    private final long coolDownMillis;
    private final Map<Player, Long> playerCoolDown;

    public CoolDownItemKit(final String name, final ItemStack kitMenuItemStack, long coolDownMillis, final ItemStack... kitItems) {
        super(name, kitMenuItemStack, kitItems);
        this.coolDownMillis = coolDownMillis;
        this.playerCoolDown = new WeakHashMap<Player, Long>();
    }

    protected long getCoolDownTime(final Player player) {
        final Long time;
        final long result;

        if (player == null)
            return 0;
        time = this.playerCoolDown.get(player);
        if (time == null)
            return 0;
        result = this.coolDownMillis - (System.currentTimeMillis() - time);
        return result >= 0 ? result : 0;
    }

    protected long getCoolDownTimeSecond(final Player player) {
        return getCoolDownTime(player) / 1000;
    }

    protected abstract void sendCoolDownMessage(final Player player);

    protected boolean canUse(final Player player) {
        return getCoolDownTime(player) <= 0;
    }

    protected boolean use(final Player player) {
        if (canUse(player)) {
            this.playerCoolDown.put(player, System.currentTimeMillis());
            return true;
        }
        return false;
    }

    @Override
    public void removePlayerKit(Player player) {
        super.removePlayerKit(player);
        if (player != null)
            this.playerCoolDown.remove(player);
    }

}
