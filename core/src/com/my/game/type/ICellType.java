package com.my.game.type;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

public interface ICellType {
    public Color getColor();
    public Texture getTexture();
    public String getType();
}
