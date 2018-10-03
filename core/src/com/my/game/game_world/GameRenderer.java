package com.my.game.game_world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.my.game.screen.GameScreen;

public class GameRenderer {

    private OrthographicCamera camera;
    private SpriteBatch batcher;
    private ShapeRenderer shapeRenderer;
    private float screenWidth = Gdx.graphics.getWidth();
    private float screenHeight = Gdx.graphics.getHeight();
    private float runTime;
    private GameWorld gameWorld;

    public GameRenderer(GameWorld gameWorld) {
        batcher = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        camera = new OrthographicCamera();
        camera.setToOrtho(true, screenWidth, screenHeight);

        batcher.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        this.gameWorld = gameWorld;
        runTime = 0;
    }

    public void render(float delta) {
        runTime += delta;

        Gdx.gl.glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        if (gameWorld.getState() == GameWorld.State.GAME) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            gameWorld.getField().draw(shapeRenderer, runTime);
            shapeRenderer.end();
        }


        if (gameWorld.getState() == GameWorld.State.WIN) {
            batcher.begin();
            gameWorld.getMenu().draw(runTime, batcher);
            batcher.end();
        }
    }
}
