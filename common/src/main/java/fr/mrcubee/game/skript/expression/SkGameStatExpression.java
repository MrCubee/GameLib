package fr.mrcubee.game.skript.expression;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import fr.mrcubee.game.Game;
import fr.mrcubee.game.GameStats;

public class SkGameStatExpression extends SimplePropertyExpression<Game<?, ?>, GameStats> {

    @Override
    public Class<? extends GameStats> getReturnType() {
        return GameStats.class;
    }

    @Override
    protected String getPropertyName() {
        return "game stat";
    }

    @Override
    public GameStats convert(Game<?, ?> game) {
        return game.getGameStats();
    }

}
