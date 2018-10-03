package com.my.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class AssetLoader {
    private BitmapFont font;

    public AssetLoader() {
        font = new BitmapFont(Gdx.files.internal("text.fnt"));
        font.setUseIntegerPositions(false);
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(0.5f, -0.5f);
        font.setColor(Color.BLACK);
    }

    public BitmapFont getFont() {
        return font;
    }
}
