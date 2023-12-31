package com.game.slot;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.slot.screen.LoaderScreen;

public class BestSlot extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		setScreen( new LoaderScreen( this ) );
	}
}
