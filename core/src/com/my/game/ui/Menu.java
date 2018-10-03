package com.my.game.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.my.game.helper.AssetLoader;
import com.my.game.helper.TextSize;
import com.my.game.screen.GameScreen;

import java.awt.Rectangle;

public class Menu {
    private Vector2 positionFirst, positionSecond;
    private AssetLoader assetLoader;
    private final String WIN_MESSAGE_FIRST = "YOU WIN!!!";
    private final String WIN_MESSAGE_SECOND = "CLICK SOMEWHERE TO RESTART";

    public Menu(AssetLoader assetLoader) {
        this.assetLoader = assetLoader;

        positionFirst = new Vector2();
        positionSecond = new Vector2();
        TextSize textSize = new TextSize();
        positionFirst.x = GameScreen.width / 2 - textSize.getWidth(assetLoader.getFont(), WIN_MESSAGE_FIRST) / 2;
        positionFirst.y = GameScreen.height / 3 - textSize.getHeight(assetLoader.getFont(), WIN_MESSAGE_SECOND) / 2;

        positionSecond.x = GameScreen.width / 2 - textSize.getWidth(assetLoader.getFont(), WIN_MESSAGE_SECOND) / 2;
        positionSecond.y = GameScreen.height / 3 - textSize.getHeight(assetLoader.getFont(), WIN_MESSAGE_SECOND) / 2
                + 2 * textSize.getHeight(assetLoader.getFont(), WIN_MESSAGE_SECOND);
    }

    public void draw(float runTime, SpriteBatch batcher) {
        assetLoader.getFont().draw(batcher, WIN_MESSAGE_FIRST, positionFirst.x, positionFirst.y);
        assetLoader.getFont().draw(batcher, WIN_MESSAGE_SECOND, positionSecond.x, positionSecond.y);
    }
}
