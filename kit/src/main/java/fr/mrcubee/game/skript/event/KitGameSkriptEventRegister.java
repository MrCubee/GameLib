package fr.mrcubee.game.skript.event;

import ch.njol.skript.Skript;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import fr.mrcubee.game.event.*;
import fr.mrcubee.game.kit.Kit;
import org.bukkit.entity.Player;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class KitGameSkriptEventRegister {

    public static void register() {

        /* Kit Add Player Event */
        Skript.registerEvent("kit add player", SkKitAddPlayerEvent.class, KitAddPlayerEvent.class, "kit add player");

        /* Kit Give Event */
        Skript.registerEvent("kit give", SkKitGiveEvent.class, KitGiveEvent.class, "kit give");

        /* Kit Player Select Event */
        Skript.registerEvent("kit player select", SkKitPlayerSelectEvent.class, KitPlayerSelectEvent.class, "kit player select");

        /* Kit Remove Player Event */
        Skript.registerEvent("kit remove player", SkKitRemovePlayerEvent.class, KitRemovePlayerEvent.class, "kit remove player");

        /* Kit Player Event Value */
        EventValues.registerEventValue(KitPlayerEvent.class, Kit.class, new Getter<Kit, KitPlayerEvent>() {
            @Override
            public Kit get(KitPlayerEvent event) {
                return event.getKit();
            }
        }, 0);

    }

}
