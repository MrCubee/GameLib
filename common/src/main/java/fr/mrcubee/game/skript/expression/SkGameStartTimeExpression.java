package fr.mrcubee.game.skript.expression;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import fr.mrcubee.game.Game;

public class SkGameStartTimeExpression extends SimplePropertyExpression<Game<?, ?>, Long> {

    @Override
    public Class<? extends Long> getReturnType() {
        return Long.class;
    }

    @Override
    protected String getPropertyName() {
        return "start time";
    }

    @Override
    public Long convert(Game<?, ?> game) {
        return game.getGameStartTime();
    }

}
