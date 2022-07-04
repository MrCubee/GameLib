package fr.mrcubee.game.skript;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.Converters;
import fr.mrcubee.game.kit.Kit;
import fr.mrcubee.game.skript.event.KitGameSkriptEventRegister;
import fr.mrcubee.game.skript.expression.KitGameSkriptExpressionRegister;
import org.bukkit.inventory.ItemStack;

public class KitGameSkriptRegister {

    public static void register() {
        Classes.registerClass(new ClassInfo<Kit>(Kit.class, "kit")
                .user("kits?")
                .name("Kit"));
        Converters.registerConverter(Kit.class, String.class, Kit::getName);
        Converters.registerConverter(Kit.class, ItemStack.class, Kit::getKitMenuItemStack);
        KitGameSkriptExpressionRegister.register();
        KitGameSkriptEventRegister.register();
    }

}
