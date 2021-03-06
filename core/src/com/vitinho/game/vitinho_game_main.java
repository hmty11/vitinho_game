package com.vitinho.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.mk5.gdx.fireapp.GdxFIRApp;



public class vitinho_game_main extends Game
{
	vitinho_feeding	vitinho_feeding;
	inventory inventory;
	vitinho_bath vitinho_bath;



	public SpriteBatch batch;

	@Override
	public void create ()
	{
		GdxFIRApp.inst().configure();
		//Calls vitinho_feeding when app starts, button creation still needed
        //		vitinho_feeding = new vitinho_feeding();
        //		setScreen(vitinho_feeding);

		batch = new SpriteBatch();
		this.setScreen(new main_menu_screen(this));
	}

	@Override
	public void render ()
	{
		super.render();
	}
	
	@Override
	public void dispose ()
	{
		vitinho_feeding.dispose();
		inventory.dispose();
		vitinho_bath.dispose();
	}
}
