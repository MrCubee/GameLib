package fr.mrcubee.game.skript.expression;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import fr.mrcubee.game.Game;

public class SkGameEndTimeExpression extends SimplePropertyExpression<Game<?, ?>, Long> {

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }

    @Override
    protected String getPropertyName() {
        return "end time";
    }

    @Override
    public Long convert(Game<?, ?> game) {
        return game.getGameEndTime();
    }

}
