package com.vitinho.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.event.TextEvent;

public class vitinho_game_main extends ApplicationAdapter
{
	SpriteBatch batch;
	Texture background;
	Texture[] vitinhos;

	int vitinho_state = 0;
	int pause = 0;


	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		background = new Texture("mmbg.jpg");
		vitinhos = new Texture[4];
		vitinhos[0] = new Texture("frame-1.png");
		vitinhos[1] = new Texture("frame-2.png");
		vitinhos[2] = new Texture("frame-3.png");
		vitinhos[3] = new Texture("frame-4.png");
	}

	@Override
	public void render () {



		if(pause < 8)
		{
			pause++;
		}
		else
		{
			pause = 0;
			if(vitinho_state < 3)
			{
				vitinho_state++;
			}
			else
			{
				vitinho_state = 0;
			}
		}

		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		batch.draw(vitinhos[vitinho_state],Gdx.graphics.getWidth()/2 - vitinhos[vitinho_state].getWidth()/2,Gdx.graphics.getHeight()/2);
		batch.end();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
