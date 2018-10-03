package com.my.game;

import com.badlogic.gdx.Game;
import com.my.game.screen.GameScreen;

public class Main extends Game {
	@Override
	public void create() {
		GameScreen gameScreen = new GameScreen();
		setScreen(gameScreen);
	}

	@Override
	public void dispose() {
	}
}
