package com.my.game.game_world;

import com.badlogic.gdx.Input;
import com.my.game.helper.AssetLoader;
import com.my.game.game_object.Field;
import com.my.game.ui.Menu;

public class GameWorld {
    private Field field;
    private Menu menu;
    private boolean isWin;
    private State state;
    private AssetLoader assetLoader;

    public enum State{
        GAME, WIN
    }

    public GameWorld(AssetLoader assetLoader) {
        field = new Field();
        menu = new Menu(assetLoader);
        isWin = false;
        this.assetLoader = assetLoader;
        state = State.GAME;
    }

    public State getState() {
        return state;
    }

    public void restart() {
        field.restart();
        state = State.GAME;
        isWin = false;
    }

    public void update(float delta) {
        if (!isWin)
            isWin = field.isWin();
        else if (state != State.WIN)
            state = State.WIN;
    }

    public Menu getMenu() {
        return menu;
    }

    public Field getField() {
        return field;
    }

    public void onKeyDown(int keyCode) {
        switch (keyCode) {
            case Input.Keys.UP:
                field.moveUp();
                break;
            case Input.Keys.DOWN:
                field.moveDown();
                break;
            case Input.Keys.LEFT:
                field.moveLeft();
                break;
            case Input.Keys.RIGHT:
                field.moveRight();
                break;
            case Input.Keys.ENTER:
                field.onClickEnter();
                break;
        }
    }
}
