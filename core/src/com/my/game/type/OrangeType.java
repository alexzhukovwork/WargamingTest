package com.my.game.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public class OrangeType implements ICellType {
    private final String TYPE = "orange";
    private final Color color = Color.ORANGE;

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public Texture getTexture() {
        return null;
    }

    @Override
    public String getType() {
        return TYPE;
    }
}
