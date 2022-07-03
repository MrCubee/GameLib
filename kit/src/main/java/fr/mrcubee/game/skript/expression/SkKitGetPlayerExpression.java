package fr.mrcubee.game.skript.expression;

import ch.njol.skript.expressions.base.PropertyExpression;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import fr.mrcubee.game.kit.Kit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.HashSet;
import java.util.Set;

public class SkKitGetPlayerExpression extends PropertyExpression<Kit, Player> {

    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parseResult) {
        if (expressions.length > 0 && expressions[0] != null){
            setExpr((Expression<? extends Kit>) expressions[0]);
            return true;
        }
        return false;
    }

    @Override
    protected Player[] get(Event event, Kit[] source) {
        final Set<Player> players = new HashSet<Player>();

        for (Kit kit : source)
            players.addAll(kit.getPlayers());
        return players.toArray(new Player[players.size()]);
    }

    @Override
    public Class<? extends Player> getReturnType() {
        return Player.class;
    }

    @Override
    public String toString(final Event event, final boolean debug) {
        return "the players of " + getExpr().toString(event, debug);
    }
}
