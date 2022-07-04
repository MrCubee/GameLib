package fr.mrcubee.game.skript.expression;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import fr.mrcubee.game.kit.Kit;
import org.bukkit.inventory.ItemStack;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class SkKitGetMenuItemExpression extends SimplePropertyExpression<Kit, ItemStack> {

    @Override
    protected String getPropertyName() {
        return "menu item";
    }

    @Override
    public ItemStack convert(Kit kit) {
        return kit.getKitMenuItemStack();
    }

    @Override
    public Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }
}
