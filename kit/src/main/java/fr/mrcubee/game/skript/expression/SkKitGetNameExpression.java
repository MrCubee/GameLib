package fr.mrcubee.game.skript.expression;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import fr.mrcubee.game.kit.Kit;

/**
 * @author MrCubee
 * @since 1.0
 * @version 1.0
 */
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
