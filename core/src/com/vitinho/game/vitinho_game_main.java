package com.vitinho.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.awt.event.TextEvent;
import com.badlogic.gdx.Game;

public class vitinho_game_main extends Game
{
//	SpriteBatch batch;
//	Texture background;
//	Texture[] vitinhos;

//	int vitinho_state = 0;
////	int pause = 0;

	vitinho_feeding	vitinho_feeding;

	@Override
	public void create ()
	{
		//Calls vitinho_feeding when app starts, button creation still needed
		vitinho_feeding = new vitinho_feeding();
		setScreen(vitinho_feeding);

//		batch = new SpriteBatch();
//		background = new Texture("mmbg.jpg");
//		vitinhos = new Texture[4];
//		vitinhos[0] = new Texture("frame-1.png");
//		vitinhos[1] = new Texture("frame-2.png");
//		vitinhos[2] = new Texture("frame-3.png");
//		vitinhos[3] = new Texture("frame-4.png");
//
//		//Create Table
//		Table mainTable = new Table();
//		//Set table to fill stage
//		mainTable.setFillParent(true);
//		//Set alignment of contents in the table.
//		mainTable.top();

//		//Create buttons
//		TextButton playButton = new TextButton("Play", skin);
//		TextButton optionsButton = new TextButton("Options", skin);
//		TextButton exitButton = new TextButton("Exit", skin);

	}

	@Override
	public void render () {
		super.render();
//		if(pause < 8)
//		{
//			pause++;
//		}
//		else
//		{
//			pause = 0;
//			if(vitinho_state < 3)
//			{
//				vitinho_state++;
//			}
//			else
//			{
//				vitinho_state = 0;
//			}
//		}
//
//		batch.begin();
//		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
//		batch.draw(vitinhos[vitinho_state],Gdx.graphics.getWidth()/2 - vitinhos[vitinho_state].getWidth()/2,Gdx.graphics.getHeight()/2);
//		batch.end();


	}
	
	@Override
	public void dispose () {
	vitinho_feeding.dispose();
	}
}
