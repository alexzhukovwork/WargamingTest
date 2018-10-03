package com.my.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.my.game.helper.AssetLoader;
import com.my.game.game_world.GameRenderer;
import com.my.game.game_world.GameWorld;


public class GameScreen implements Screen, InputProcessor {
    private GameRenderer gameRenderer;
    private GameWorld gameWorld;
    public static float width, height;

    public GameScreen() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
        GameScreen.width = Gdx.graphics.getWidth();
        GameScreen.height = Gdx.graphics.getHeight();

        gameWorld = new GameWorld(new AssetLoader());
        gameRenderer = new GameRenderer(gameWorld);
    }

    @Override
    public void render(float delta) {
       gameWorld.update(delta);
       gameRenderer.render(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean keyDown(int keycode) {
        gameWorld.onKeyDown(keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (gameWorld.getState() == GameWorld.State.WIN)
            gameWorld.restart();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
