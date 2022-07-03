package fr.mrcubee.game.skript.expression;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import fr.mrcubee.game.kit.Kit;

public class SkKitGetNameExpression extends SimplePropertyExpression<Kit, String> {

    @Override
    protected String getPropertyName() {
        return "name";
    }

    @Override
    public String convert(Kit kit) {
        return kit.getName();
    }

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }
}
