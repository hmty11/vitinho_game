package com.vitinho.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


class vitinho_feeding implements Screen {
    //camera
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;

    private Texture background;
    private Texture[] vitinhos;

    //Vitinho creation
    private Vitinho vitinho;

    int vitinho_state = 0;
    int pause = 0;
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    vitinho_feeding()
    {
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);


        vitinhos = new Texture[4];

        vitinhos[0] = new Texture("frame-1.png");
        vitinhos[1] = new Texture("frame-2.png");
        vitinhos[2] = new Texture("frame-3.png");
        vitinhos[3] = new Texture("frame-4.png");

        background = new Texture("mmbg.jpg");

        //set up vitinho
        vitinho = new Vitinho(0, 5,1,WORLD_WIDTH, WORLD_HEIGHT, vitinhos[0]);
        batch = new SpriteBatch();

    }

    @Override
    public void render(float delta)
    {
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
        //batch.draw(background,0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.draw(background,0,0, WORLD_WIDTH,WORLD_HEIGHT);

        //display vitinho
        //vitinho.draw(batch);
        //batch.draw(vitinhos[vitinho_state],WORLD_WIDTH/2,WORLD_HEIGHT/2);
        //batch.draw(vitinhos[vitinho_state],Gdx.graphics.getWidth()/2 - vitinhos[vitinho_state].getWidth()/2,Gdx.graphics.getHeight()/2);
        batch.end();

    }

    @Override
    public void resize(int width, int height)
    {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
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
    public void dispose()
    {
        //.dispose();
    }

    @Override
    public void show()
    {

    }

}
