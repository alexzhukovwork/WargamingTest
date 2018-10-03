package com.my.game.helper;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TextSize {
    private GlyphLayout glyphLayout;

    public TextSize() {
        glyphLayout = new GlyphLayout();
    }

    public float getWidth(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }

    public  float getHeight(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return -glyphLayout.height;
    }
}