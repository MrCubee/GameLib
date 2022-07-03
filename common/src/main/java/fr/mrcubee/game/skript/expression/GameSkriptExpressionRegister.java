package fr.mrcubee.game.skript.expression;

import ch.njol.skript.expressions.base.PropertyExpression;
import fr.mrcubee.game.GameStats;

public class GameSkriptExpressionRegister {

    public static void register() {
        PropertyExpression.register(SkGameEndTimeExpression.class, Long.class, "end[ ]time", "games");
        PropertyExpression.register(SkGameStartTimeExpression.class, Long.class, "start[ ]time", "games");
        PropertyExpression.register(SkGameStatExpression.class, GameStats.class, "game[ ]stat", "games");
    }

}
