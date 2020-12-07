package com.vitinho.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class inventory implements Screen {

    vitinho_game_main game;
    //camera
    private Camera camera;
    private Viewport viewport;

    //Textures
    private SpriteBatch batch;
    private Texture background; //background of the inventory screen

    //world size
    private final float WORLD_WIDTH = Gdx.graphics.getWidth();
    private final float WORLD_HEIGHT = Gdx.graphics.getHeight();
    private final float margin = 10;

    public inventory() {
        this.game = this.game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new Texture("mmbg.jpg");
        batch = new SpriteBatch();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        //Draw background
        batch.draw(background,0,0, WORLD_WIDTH,WORLD_HEIGHT);
        batch.end();
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
