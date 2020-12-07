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

    Vector3 touch_pos = new Vector3();

    //graphics
    private SpriteBatch batch;
    private Texture background;
//  private Texture[] vitinhos;

    //Vitinho creation
    Texture vitinho_hungry_bar_texture;
    private Vitinho vitinho;
    int vitinho_state = 1;
    int pause = 0;
    private final float WORLD_WIDTH = 72;
    private final float WORLD_HEIGHT = 128;
    private final float margin = 10;

    //Buttons
    //int first_touch = 0;
    //Button 1
    Texture vitinho_feed_buton_1;
    float vitinho_feed_buton_1_width = 10;
    float vitinho_feed_buton_1_height = 10;
    float vitinho_feed_buton_1_x_position = WORLD_WIDTH/2 - vitinho_feed_buton_1_width/2;
    float vitinho_feed_buton_1_y_position = WORLD_HEIGHT/20;

    //Button 2
    Texture vitinho_feed_buton_2;
    float vitinho_feed_buton_2_width = 10;
    float vitinho_feed_buton_2_height = 10;
    float vitinho_feed_buton_2_x_position = WORLD_WIDTH - vitinho_feed_buton_1_width/2 - margin;
    float vitinho_feed_buton_2_y_position = WORLD_HEIGHT/20;

    //Button 3
    Texture vitinho_feed_buton_3;
    float vitinho_feed_buton_3_width = 10;
    float vitinho_feed_buton_3_height = 10;
    float vitinho_feed_buton_3_x_position = WORLD_WIDTH/3 - vitinho_feed_buton_1_width/2 - margin;
    float vitinho_feed_buton_3_y_position = WORLD_HEIGHT/20;

    //Heads-Up Display
    BitmapFont font;
    float hudVerticalMargin, hudLeftX, hudRightX, hudCentreX, hudRow1Y, hudRow2y, hudRow2y_bar, hudSectionWidth;

    vitinho_feeding()
    {
        this.game = this.game;
        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        background = new Texture("mmbg.jpg");

        //set up vitinho
        vitinho = new Vitinho(0,5,1,0,0,0, vitinho_state, WORLD_WIDTH, WORLD_HEIGHT);
        batch = new SpriteBatch();

        vitinho_feed_buton_1 = new Texture("coin.png");
        vitinho_feed_buton_2 = new Texture("coin.png");
        vitinho_feed_buton_3 = new Texture("coin.png");
        vitinho_hungry_bar_texture = new Texture("blank.png");
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
        hudVerticalMargin = font.getCapHeight()/2 ;
        hudLeftX = hudVerticalMargin;
        hudRightX = WORLD_WIDTH * 2 / 3 - hudLeftX;
        hudCentreX = WORLD_WIDTH / 3;
        hudRow1Y = WORLD_HEIGHT - hudVerticalMargin;
        hudRow2y = WORLD_HEIGHT - hudVerticalMargin - font.getCapHeight() - 1;
        hudRow2y_bar =  WORLD_HEIGHT - hudVerticalMargin - 13;
        hudSectionWidth = WORLD_WIDTH / 3;
    }

    @Override
    public void render(float delta)
    {
        batch.begin();
        //Draw background
        batch.draw(background,0,0, WORLD_WIDTH,WORLD_HEIGHT);
        //Draw vitinho
        vitinho.draw(batch);
        touch_pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        //translate the position (pixels) to cartesian coordinates
        camera.unproject(touch_pos);

        //batch.draw(vitinho_hungry_bar_texture, 0, WORLD_HEIGHT/2, WORLD_WIDTH /* Vitinho.vitinho_hungry*/, 5);

        // render Button 1
        batch.draw(vitinho_feed_buton_1,  vitinho_feed_buton_1_x_position, vitinho_feed_buton_1_y_position, vitinho_feed_buton_1_width, vitinho_feed_buton_1_height);
        //Conditional to verify if the button 1 was pressed
        if(touched_button(touch_pos, vitinho_feed_buton_1_x_position, vitinho_feed_buton_1_y_position, vitinho_feed_buton_1_width, vitinho_feed_buton_1_height))
        {
            if (Vitinho.vitinho_hungry<1)
            {
                Vitinho.vitinho_hungry += 0.01;
            }
        }

        //render button 2
        //Draw vitinho_feed_buton_2
        batch.draw(vitinho_feed_buton_2,  vitinho_feed_buton_2_x_position, vitinho_feed_buton_2_y_position, vitinho_feed_buton_2_width, vitinho_feed_buton_2_height);
        if(touched_button(touch_pos, vitinho_feed_buton_2_x_position, vitinho_feed_buton_2_y_position, vitinho_feed_buton_2_width, vitinho_feed_buton_2_height))
        {
            Vitinho.vitinho_age = Vitinho.vitinho_age + 1;
        }

        //render button 3
        //Show vitinho's food supply
        batch.draw(vitinho_feed_buton_3,  vitinho_feed_buton_3_x_position, vitinho_feed_buton_3_y_position, vitinho_feed_buton_3_width, vitinho_feed_buton_3_height);
        if(touched_button(touch_pos, vitinho_feed_buton_3_x_position, vitinho_feed_buton_3_y_position, vitinho_feed_buton_3_width, vitinho_feed_buton_3_height))
        {
            game.setScreen(new inventory());
        }

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

        batch.draw(vitinho_hungry_bar_texture, 2, hudRow2y_bar, (float) Vitinho.vitinho_hungry * hudSectionWidth  , 5);
        font.draw(batch, String.format(Locale.getDefault(), "%02d", Vitinho.vitinho_age), hudCentreX, hudRow2y, hudSectionWidth, Align.center, false);
        font.draw(batch,String.format(Locale.getDefault(), "%02d", Vitinho.vitinho_diseases), hudRightX, hudRow2y, hudSectionWidth, Align.right, false);
    }

    private boolean touched_button(Vector3 touch_position,  float x_position, float y_position, float button_width, float button_height)
    {
        if((x_position < touch_position.x && touch_position.x < x_position + button_width) &&  //X
                (y_position  < touch_position.y && touch_position.y < y_position + button_height )) //Y
        {
            if (Gdx.input.isTouched())
            {
                return true;
            }
        }
        return false;
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
