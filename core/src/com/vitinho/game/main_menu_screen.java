package com.vitinho.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;



public class main_menu_screen implements Screen
{
    vitinho_game_main game;
    Texture vitinho_feeding_buton;

    int vitinho_feeding_buton_width = 100;
    int vitinho_feeding_buton_height = 100;
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;


    public main_menu_screen(vitinho_game_main game)
    {
        this.game = game;
        vitinho_feeding_buton = new Texture("bomb.png");


    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(vitinho_feeding_buton, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, vitinho_feeding_buton_width, vitinho_feeding_buton_height);

        if(Gdx.input.getX() < Gdx.graphics.getWidth()/2 + vitinho_feeding_buton_width && Gdx.input.getX() > Gdx.graphics.getWidth()/2 && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/2 + vitinho_feeding_buton_height && Gdx.graphics.getHeight() - Gdx.input.getY() >  Gdx.graphics.getHeight()/2)
        {
            if(Gdx.input.isTouched())
            {
                Gdx.app.log("MyTag", "my informative message");
                this.dispose();
                game.setScreen(new vitinho_feeding());
            }
        }
        game.batch.end();

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
}
