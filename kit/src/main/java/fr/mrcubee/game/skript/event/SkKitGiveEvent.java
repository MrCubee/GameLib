package fr.mrcubee.game.skript.event;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser;
import org.bukkit.event.Event;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
public class SkKitGiveEvent extends SkriptEvent {
    @Override
    public boolean init(Literal<?>[] literals, int matchedPattern, SkriptParser.ParseResult parseResult) {
        return true;
    }

    @Override
    public boolean check(Event event) {
        return true;
    }

    @Override
    public String toString(Event event, boolean debug) {
        return "kit give";
    }

}
