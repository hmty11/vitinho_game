package com.vitinho.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;



public class main_menu_screen implements Screen
{
    vitinho_game_main game;


    int vitinho_feeding_buton_width = 100;
    int vitinho_feeding_buton_height = 100;

    int vitinho_bath_buton_width = 100;
    int vitinho_bath_buton_height = 100;

    private final float WORLD_WIDTH = Gdx.graphics.getWidth();
    private final float WORLD_HEIGHT = Gdx.graphics.getHeight();

    //Graphics
    private Texture background;
    Texture vitinho_feeding_buton;
    Texture vitinho_bath_buton;


    public main_menu_screen(vitinho_game_main game)
    {
        this.game = game;
        vitinho_feeding_buton = new Texture("bomb.png");
        vitinho_bath_buton = new Texture("bomb.png");
        background = new Texture("mmbg.jpg");

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta)
    {
        game.batch.begin();
        //Draw background
        game.batch.draw(background,0,0,WORLD_WIDTH,WORLD_HEIGHT);


        game.batch.draw(vitinho_feeding_buton, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, vitinho_feeding_buton_width, vitinho_feeding_buton_height);
        game.batch.draw(vitinho_bath_buton, Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/3, vitinho_bath_buton_width, vitinho_bath_buton_height);

        if(Gdx.input.getX() < Gdx.graphics.getWidth()/2 + vitinho_feeding_buton_width && Gdx.input.getX() > Gdx.graphics.getWidth()/2 && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/2 + vitinho_feeding_buton_height && Gdx.graphics.getHeight() - Gdx.input.getY() >  Gdx.graphics.getHeight()/2)
        {
            if(Gdx.input.isTouched())
            {
                Gdx.app.log("MyTag", "my informative message");
                this.dispose();
                game.setScreen(new vitinho_feeding());
                //game.setScreen(new inventory());
            }
        }
        if(Gdx.input.getX() < Gdx.graphics.getWidth()/2 + vitinho_bath_buton_width && Gdx.input.getX() > Gdx.graphics.getWidth()/2 && Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/3 + vitinho_bath_buton_height && Gdx.graphics.getHeight() - Gdx.input.getY() >  Gdx.graphics.getHeight()/3)
          {
               if(Gdx.input.isTouched())
               {
                    Gdx.app.log("MyTag", "my informative message");
                   this.dispose();
                  game.setScreen(new vitinho_bath());
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
