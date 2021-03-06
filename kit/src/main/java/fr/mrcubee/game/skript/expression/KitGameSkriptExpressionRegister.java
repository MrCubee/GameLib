package fr.mrcubee.game.skript.expression;

import ch.njol.skript.expressions.base.PropertyExpression;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class KitGameSkriptExpressionRegister {

    public static void register() {
        PropertyExpression.register(SkKitGetPlayerExpression.class, Player.class, "players", "kits");
        PropertyExpression.register(SkKitGetNameExpression.class, String.class, "name", "kits");
        PropertyExpression.register(SkKitGetMenuItemExpression.class, ItemStack.class, "menu item", "kits");
    }

}
