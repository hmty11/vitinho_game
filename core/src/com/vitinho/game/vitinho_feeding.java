package com.vitinho.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Locale;

class vitinho_feeding implements Screen {

    vitinho_game_main game;

    //camera
    private Camera camera;
    private Viewport viewport;

    //graphics
    private SpriteBatch batch;
    private Texture background;
//    private Texture[] vitinhos;

    //Vitinho creation
    private Vitinho vitinho;
    int vitinho_state = 1;
    int pause = 0;
    private final float WORLD_WIDTH = 72;
    private final float WORLD_HEIGHT = 128;

    //Buttons
    //Button 1
    Texture vitinho_feed_buton_1;
    float vitinho_feed_buton_1_width = 100;
    float vitinho_feed_buton_1_height = 100;

    //Heads-Up Display
    BitmapFont font;
    float hudVerticalMargin, hudLeftX, hudRightX, hudCentreX, hudRow1Y, hudRow2y, hudSectionWidth;

    vitinho_feeding()
    {
        this.game = game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new Texture("mmbg.jpg");

        //set up vitinho
        vitinho = new Vitinho(0, 5,1,0, 0, vitinho_state, WORLD_WIDTH, WORLD_HEIGHT);
        batch = new SpriteBatch();

        vitinho_feed_buton_1 = new Texture("coin.png");
        prepareHUD();
    }

    private void prepareHUD()
    {
        //Create bitmapFont from our font file
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("EdgeOfTheGalaxyRegular-OVEa6.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        fontParameter.size = 72;
        fontParameter.borderWidth = 4f;
        fontParameter.color = new Color(1, 1, 1, 0.3f);
        fontParameter.borderColor = new Color(0, 0, 0, 0.3f);
        font = fontGenerator.generateFont(fontParameter);

        //Scale the font to fit world
        font.getData().setScale(0.1f);

        //Calculate hud margins, etc.
        hudVerticalMargin = font.getCapHeight()/2;
        hudLeftX = hudVerticalMargin;
        hudRightX = WORLD_WIDTH * 2 / 3 - hudLeftX;
        hudCentreX = WORLD_WIDTH / 3;
        hudRow1Y = WORLD_HEIGHT - hudVerticalMargin;
        hudRow2y = WORLD_HEIGHT - hudVerticalMargin - font.getCapHeight();
        hudSectionWidth = WORLD_WIDTH / 3;
    }

    @Override
    public void render(float delta)
    {
        batch.begin();
        batch.draw(background,0,0, WORLD_WIDTH,WORLD_HEIGHT);
        vitinho.draw(batch);
        batch.draw(vitinho_feed_buton_1,  WORLD_WIDTH/2, WORLD_HEIGHT/20, vitinho_feed_buton_1_width/10, vitinho_feed_buton_1_height/10);
        Vector3 vitinho_feed_buton_1_position = new Vector3(WORLD_WIDTH/2, WORLD_HEIGHT/20, 0);
        Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
//        if(Gdx.input.getX() < Gdx.graphics.getWidth()/2 + vitinho_feed_buton_1_width && Gdx.input.getX() > Gdx.graphics.getWidth()/2 &&
//                Gdx.graphics.getHeight() - Gdx.input.getY() < Gdx.graphics.getHeight()/20 + vitinho_feed_buton_1_height && Gdx.graphics.getHeight() - Gdx.input.getY() > Gdx.graphics.getHeight()/20 )


        if(touchPos == vitinho_feed_buton_1_position)
        {
            Gdx.app.log("MyTag", "my informative message");
            if (Gdx.input.isTouched())
            {
                this.dispose();
                Gdx.app.log("MyTag", "my informative message");
                Vitinho.vitinho_hungry = Vitinho.vitinho_hungry + 10;
            }
        }
        //batch.draw(vitinhos[vitinho_state],0,0, WORLD_WIDTH/2,WORLD_HEIGHT/2);
        //display vitinho


        //hud rendering
        updateAndRenderHUD();
        batch.end();

    }

    private void updateAndRenderHUD()
    {   //render top row with labels
        font.draw(batch,"FOME", hudLeftX, hudRow1Y, hudSectionWidth, Align.left, false);
        font.draw(batch,"IDADE", hudCentreX, hudRow1Y, hudSectionWidth, Align.center, false);
        font.draw(batch,"DOENCA", hudRightX, hudRow1Y, hudSectionWidth, Align.right, false);

        //render second row with values
        font.draw(batch, String.format(Locale.getDefault(), "%02d", Vitinho.vitinho_hungry), hudLeftX, hudRow2y, hudSectionWidth, Align.left, false);
        font.draw(batch, String.format(Locale.getDefault(), "%02d", Vitinho.vitinho_age), hudCentreX, hudRow2y, hudSectionWidth, Align.center, false);
        font.draw(batch,String.format(Locale.getDefault(), "%02d", Vitinho.vitinho_diseases), hudRightX, hudRow2y, hudSectionWidth, Align.right, false);
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
